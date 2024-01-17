package com.example.rent.ui.rentador;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rent.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Model> dataList;
    private OnItemClickListener listener;

    private Context context; // Agrega una variable de instancia para el contexto

    public MyAdapter(Context context, List<Model> dataList){
        this.context = context;
        this.dataList = dataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Model ob = dataList.get(position);

        //Cargar img mediante el URI
        Uri imageUri = Uri.parse(ob.getImagen());
        Glide.with(context)
                .load(imageUri)
                .into(holder.img);

        //recibe las variables del item_layout
        holder.textView.setText(dataList.get(position).getTextoPrincipal());
        holder.textView2.setText(dataList.get(position).getPrecio());
        holder.textView3.setText(dataList.get(position).getStock());
        holder.textView4.setText(dataList.get(position).getDescripcion());





        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), MainActivity2.class);
                intent.putExtra("item", ob.getId());
                intent.putExtra("logo", ob.getImagen());
                intent.putExtra("identificador", ob.getId());
                intent.putExtra("titulo", ob.getTextoPrincipal());
                intent.putExtra("precio", ob.getPrecio());
                intent.putExtra("stock", ob.getStock());
                intent.putExtra("descripcion", ob.getDescripcion());
                holder.itemView.getContext().startActivity(intent);


            }
        });
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView textView;

        public TextView textView2;

        public TextView textView3;

        public TextView textView4;
        public TextView textView5;
        public ViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.txtTitulo);
            textView2 = itemView.findViewById(R.id.txtPrecio);
            textView3 = itemView.findViewById(R.id.txtStock);
            textView4 = itemView.findViewById(R.id.descripcion);

        }
    }
}
