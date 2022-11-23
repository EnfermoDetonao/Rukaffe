package com.example.rukaffe;

import static com.example.rukaffe.Util.MyApp.context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rukaffe.Database.DatabaseHelper;
import com.example.rukaffe.Database.DatabaseQueryClass;
import com.example.rukaffe.Models.Inventory;
import com.example.rukaffe.Util.Constants;

import java.util.ArrayList;
import java.util.List;

public class IngresoProductos extends AppCompatActivity {

    private EditText name;
    private EditText cantidad;
    private EditText date;
    private Button agregarBtn;
    List<Inventory> listaDatos;//datos con los que se construirá el reciclador
    RecyclerView reciclador;
    ArrayList<String> listaDatos2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_productos);
        name = this.findViewById(R.id.editTextNombre);
        cantidad= this.findViewById(R.id.editTextCantidad);
        date= this.findViewById(R.id.editTextDate);
        agregarBtn=this.findViewById(R.id.button10);
        this.obtenerListaInventario();


        agregarBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Inventory productoNuevo= new Inventory();
                productoNuevo.setName(name.getText().toString());
                productoNuevo.setCantidad(Integer.valueOf(cantidad.getText().toString()));
                productoNuevo.setFecha(date.getText().toString());

                DatabaseQueryClass dbQueryClass = new DatabaseQueryClass(getBaseContext());

                dbQueryClass.insertarProducto();
            }
        });
    }

    public List<Inventory> obtenerListaInventario(){

        DatabaseQueryClass dbQeryInventario1 = new DatabaseQueryClass(getBaseContext());
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor=null;
        try{
            cursor=  sqLiteDatabase.query(Constants.TABLE_INVENTORY,null,null,null,null,null,null,null);
            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Inventory> listaObtenida= new ArrayList<>();
                    do{
                        Inventory productoObtenido=new Inventory();
                        //obtengo valores de la BD
                        String name = cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_NAME));
                        int cantidad = cursor.getInt(cursor.getColumnIndex(Constants.INVENTORY_CANTIDAD));
                        String fecha = cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_FECHA));
                        //se los asigno al usuario
                        productoObtenido.setName(name);
                        productoObtenido.setCantidad(cantidad);
                        productoObtenido.setFecha(fecha);
                        //agrego el usuario a la lista
                        listaObtenida.add(productoObtenido);

                    }while(cursor.moveToNext());
                    return listaObtenida;
                }
        }
        catch(SQLiteException e){
            Toast.makeText(context, "Error al listar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            sqLiteDatabase.close();
        }
        return new ArrayList<Inventory>();
    }

}