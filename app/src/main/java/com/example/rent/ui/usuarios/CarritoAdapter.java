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


public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {

    private List<ModelCarrito> dataList;
    private AdapterView.OnItemClickListener listener;
    private Context context;

    public CarritoAdapter(Context context, List<ModelCarrito> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carrito_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ModelCarrito ob = dataList.get(position);

        Uri imageUri = Uri.parse(ob.getImagen());
        Glide.with(context)
                .load(imageUri)
                .into(holder.imagen);
        holder.txtVenta.setText(dataList.get(position).getNombre());
        holder.txtPrecio.setText(dataList.get(position).getPrecio());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), detallesCarrito.class);
                intent.putExtra("item", ob.getId());
                intent.putExtra("nombre", ob.getNombre());
                intent.putExtra("imagen", ob.getImagen());
                intent.putExtra("precio", ob.getPrecio());
                intent.putExtra("cantidad", ob.getCantidad());
                intent.putExtra("pago", ob.getPago());
                intent.putExtra("direccion", ob.getDireccion());
                intent.putExtra("telefono", ob.getTelefono());

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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.carritoimg);
            txtVenta = itemView.findViewById(R.id.txtnomcarrito);
            txtPrecio = itemView.findViewById(R.id.txtpreciocarrito);
        }
    }
}

