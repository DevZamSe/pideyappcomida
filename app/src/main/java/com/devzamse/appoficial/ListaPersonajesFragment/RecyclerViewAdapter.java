package com.devzamse.appoficial.ListaPersonajesFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devzamse.appoficial.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private OnItemClickListener mListener;
    Context ctx;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvprecio, tvnombre;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvprecio = (TextView) itemView.findViewById(R.id.porciones2);
            tvnombre = (TextView) itemView.findViewById(R.id.nombre2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


    public List<PersonajeVo> Lista;

    public RecyclerViewAdapter(){}

    public RecyclerViewAdapter (List<PersonajeVo> Lista){
        this.Lista = Lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desayuno,parent,false);

        ctx = parent.getContext();

        ViewHolder viewHolder = new ViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        holder.tvnombre.setText(Lista.get(i).getNombre());
        holder.tvprecio.setText(Lista.get(i).getInfo());

    }

    @Override
    public int getItemCount() {
        return Lista.size();
    }


}

