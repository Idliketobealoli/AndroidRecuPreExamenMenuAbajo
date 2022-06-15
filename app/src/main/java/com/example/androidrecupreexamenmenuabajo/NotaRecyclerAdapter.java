package com.example.androidrecupreexamenmenuabajo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidrecupreexamenmenuabajo.data.Nota;
import com.example.androidrecupreexamenmenuabajo.data.Prio;
import com.example.androidrecupreexamenmenuabajo.data.RoomDB;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotaRecyclerAdapter extends RecyclerView.Adapter<NotaRecyclerAdapter.ViewHolder> {
    private RoomDB db;
    private final NotaItemClickListener listener;
    private List<Nota> notas;
    private Activity context;

    public NotaRecyclerAdapter(NotaItemClickListener listener, List<Nota> notas, Activity context) {
        this.listener = listener;
        this.notas = notas;
        this.context = context;

        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public NotaRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nota_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NotaRecyclerAdapter.ViewHolder holder, int position) {
        Nota nota = notas.get(position);

        db = RoomDB.getInstance(context);

        holder.tvTitle.setText(nota.getTitle());
        holder.tvDescription.setText(nota.getDescription());
        if (nota.getPrio() == Prio.BAJA) {
            holder.iv.setImageResource(R.drawable.ic_baseline_circle_green_24);
        } else if (nota.getPrio() == Prio.MEDIA) {
            holder.iv.setImageResource(R.drawable.ic_baseline_circle_orange_24);
        } else {
            holder.iv.setImageResource(R.drawable.ic_baseline_circle_red_24);
        }
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ConstraintLayout layout;
        TextView tvTitle, tvDescription;
        ImageView iv;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout_item);
            tvTitle = itemView.findViewById(R.id.tv_title_item);
            tvDescription = itemView.findViewById(R.id.tv_descripcion_item);
            iv = itemView.findViewById(R.id.iv_item);

            layout.setOnClickListener(this);
            layout.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            listener.onListItemClick(position);
        }

        @Override
        public boolean onLongClick(View view) {
            int position = getAdapterPosition();
            listener.onLongClick(position);
            return true;
        }
    }
}
