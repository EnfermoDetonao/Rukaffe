package com.example.rukaffe;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rukaffe.Models.Inventory;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolderDatos> {

    List<Inventory> listaDatos;

    public Adaptador(String listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //enlazar el adaptador con el XML de items.
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ingreso_productos,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.asignarDatos(listaDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView name;
        TextView cantidad;
        TextView fecha;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.editTextNombre);
            cantidad=itemView.findViewById(R.id.editTextCantidad);
            fecha=itemView.findViewById(R.id.editTextDate);
        }

        public void asignarDatos(Inventory u) {
            name.setText(u.getName());
            cantidad.setText(u.getCantidad());

            //PREGUNTAR: ES NECESARIO EN MI CASO ESTA PARTE? QUÉ HACE

       /*     if(u.getActivo()==0)
                activo.setChecked(false);
            else
                activo.setChecked(true);
            //activo.setText();
            edad.setText(u.getEdad());*/


        }
    }
}