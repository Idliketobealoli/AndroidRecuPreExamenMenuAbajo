package com.example.androidrecupreexamenmenuabajo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.example.androidrecupreexamenmenuabajo.databinding.FragmentHomeBinding;

import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements NotaItemClickListener {
    private FragmentHomeBinding binding;
    private RoomDB db;
    private RecyclerView recyclerView;
    private NotaRecyclerAdapter adapter;
    private LinearLayoutManager manager;
    private List<Nota> notas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = RoomDB.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext());
        notas = db.notaDao().findAll();

        recyclerView = root.findViewById(R.id.recycler_home);
        adapter = new NotaRecyclerAdapter(this, notas, getActivity());
        manager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

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
        notas.addAll(db.notaDao().findAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLongClick(int position) {
        db.notaDao().reset(notas);

        notas.clear();
        notas.addAll(db.notaDao().findAll());
        adapter.notifyDataSetChanged();
    }
}