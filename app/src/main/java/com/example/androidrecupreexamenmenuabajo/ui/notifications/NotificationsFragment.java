package com.example.androidrecupreexamenmenuabajo.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.androidrecupreexamenmenuabajo.R;
import com.example.androidrecupreexamenmenuabajo.data.Nota;
import com.example.androidrecupreexamenmenuabajo.data.Prio;
import com.example.androidrecupreexamenmenuabajo.data.RoomDB;
import com.example.androidrecupreexamenmenuabajo.databinding.FragmentNotificationsBinding;

import java.util.Objects;

// FRAGMENTO ADD
public class NotificationsFragment extends Fragment {
    private FragmentNotificationsBinding binding;
    private RoomDB db;
    private EditText etTitle, etDescription;
    private RadioButton rbAlta, rbMedia, rbBaja;
    private Button bOk;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = RoomDB.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext());

        etTitle = root.findViewById(R.id.et_title_add);
        etDescription = root.findViewById(R.id.et_descripcion_add);
        rbAlta = root.findViewById(R.id.rb_alta_add);
        rbMedia = root.findViewById(R.id.rb_media_add);
        rbBaja = root.findViewById(R.id.rb_baja_add);
        bOk = root.findViewById(R.id.b_ok_add);

        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etTitle.getText().toString().isEmpty() && !etDescription.getText().toString().isEmpty()) {
                    if (rbAlta.isChecked() || rbBaja.isChecked() || rbMedia.isChecked()) {
                        Nota nota = new Nota();
                        nota.setTitle(etTitle.getText().toString());
                        nota.setDescription(etDescription.getText().toString());
                        if (rbBaja.isChecked()) {
                            nota.setPrio(Prio.BAJA);
                        } else if (rbMedia.isChecked()) {
                            nota.setPrio(Prio.MEDIA);
                        } else if (rbAlta.isChecked()) {
                            nota.setPrio(Prio.ALTA);
                        }
                        db.notaDao().insert(nota);
                    }
                } else {
                    Toast.makeText(getContext(), "Invalid fields", Toast.LENGTH_SHORT).show();
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
}