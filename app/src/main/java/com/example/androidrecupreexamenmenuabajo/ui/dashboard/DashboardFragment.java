package com.example.androidrecupreexamenmenuabajo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidrecupreexamenmenuabajo.NotaItemClickListener;
import com.example.androidrecupreexamenmenuabajo.NotaRecyclerAdapter;
import com.example.androidrecupreexamenmenuabajo.R;
import com.example.androidrecupreexamenmenuabajo.data.Nota;
import com.example.androidrecupreexamenmenuabajo.data.RoomDB;
import com.example.androidrecupreexamenmenuabajo.databinding.FragmentDashboardBinding;

import java.util.List;
import java.util.Objects;

// FRAGMENTO DE FILTRAR
public class DashboardFragment extends Fragment implements NotaItemClickListener {
    private FragmentDashboardBinding binding;
    private RoomDB db;
    private EditText etFilter;
    private Button bFilter;
    private RecyclerView recycler;
    private NotaRecyclerAdapter adapter;
    private LinearLayoutManager manager;
    private List<Nota> notas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = RoomDB.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext());
        notas = db.notaDao().findAll();

        etFilter = root.findViewById(R.id.et_title_filter);
        bFilter = root.findViewById(R.id.b_filter);
        recycler = root.findViewById(R.id.recycler_filter);
        adapter = new NotaRecyclerAdapter(this, notas, getActivity());
        manager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(manager);

        bFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etFilter.getText().toString().isEmpty()) {
                    notas.clear();
                    notas.addAll(db.notaDao().findByTitle(etFilter.getText().toString()));
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Filter by something.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onListItemClick(int position) {
        Nota nota = notas.get(position);

        db.notaDao().delete(nota);

        notas.clear();
        if (!etFilter.getText().toString().isEmpty()) {
            notas.clear();
            notas.addAll(db.notaDao().findByTitle(etFilter.getText().toString()));
        } else {
            notas.clear();
            notas.addAll(db.notaDao().findAll());
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLongClick(int position) {
        notas.clear();
        notas.addAll(db.notaDao().findAll());

        db.notaDao().reset(notas);

        notas.clear();
        notas.addAll(db.notaDao().findAll());
        adapter.notifyDataSetChanged();
    }
}