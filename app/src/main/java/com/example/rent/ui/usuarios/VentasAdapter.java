package com.example.rent.ui.usuarios;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rent.R;

import java.util.List;

public class VentasAdapter extends RecyclerView.Adapter<VentasAdapter.ViewHolder> {


    private List<Model> dataList;
    private AdapterView.OnItemClickListener listener;

    private Context context;

    public VentasAdapter(Context context, List<Model> dataList) {
        this.dataList = dataList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ventas_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Model ob = dataList.get(position);

        Uri imageUri = Uri.parse(ob.getImagen());
        Glide.with(context)
                .load(imageUri)
                .into(holder.imagen);
        holder.txtVenta.setText(dataList.get(position).getNombre());
        holder.txtPrecio.setText(dataList.get(position).getPrecio());
        holder.txtStook.setText(dataList.get(position).getStook());


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), detallesProducto.class);
                intent.putExtra("item", ob.getId());
                intent.putExtra("nombre", ob.getNombre());
                intent.putExtra("tipo", ob.getTipo());
                intent.putExtra("precio", ob.getPrecio());
                intent.putExtra("descripcion", ob.getDescripcion());
                intent.putExtra("stook", ob.getStook());
                intent.putExtra("imagen", ob.getImagen());

                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;

        TextView txtVenta;
        TextView txtPrecio;
        TextView txtStook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen= itemView.findViewById(R.id.img);
            txtVenta = itemView.findViewById(R.id.txtVenta);
            txtPrecio = itemView.findViewById(R.id.txtPrecioP);
            txtStook = itemView.findViewById(R.id.txtStookP);

        }
    }
}