package com.angel.sqliteprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txt_imei;
    EditText txt_gama;
    EditText txt_modelo;
    EditText txt_marca;
    EditText txt_compania;
    Button guardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guardar = (Button)findViewById(R.id.btn_proceder);

        txt_imei = (EditText)findViewById(R.id.imei);
        txt_gama = (EditText)findViewById(R.id.gama);
        txt_modelo = (EditText)findViewById(R.id.modelo);
        txt_marca = (EditText)findViewById(R.id.marca);
        txt_compania = (EditText)findViewById(R.id.companiatelf);


    }

    public void Registrar (View view){


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "SQL_Admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String imei = txt_imei.getText().toString();
        String gama = txt_gama.getText().toString();
        String marca= txt_marca.getText().toString();
        String modelo = txt_modelo.getText().toString();
        //String comp = txt_compania.getText().toString();

        if(!imei.isEmpty() && !gama.isEmpty() && !marca.isEmpty() && !modelo.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("IMEI", imei);
            registro.put("Gama", gama);
            registro.put("marca", marca);
            registro.put("modelo", modelo);
            //egistro.put("comp", comp);

            bd.insert("telef", null, registro);
            bd.close();
            txt_imei.setText("");
            txt_gama.setText("");
            txt_marca.setText("");
            txt_modelo.setText("");
            //txt_compania.setText("");

            Toast.makeText(this,"Guardado exitoso", Toast.LENGTH_SHORT).show();


        } else{


            Toast.makeText(this,"Existen los campos vacíos", Toast.LENGTH_SHORT).show();
        }
    }



    public void Modificar(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"SQL_Admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String imei = txt_imei.getText().toString();
        String gama = txt_gama.getText().toString();
        String marca= txt_marca.getText().toString();
        String modelo = txt_modelo.getText().toString();
        //String comp = txt_compania.getText().toString();



        if(!imei.isEmpty() && !gama.isEmpty() && !marca.isEmpty() && !modelo.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("IMEI", imei);
            registro.put("Gama", gama);
            registro.put("marca", marca);
            registro.put("modelo", modelo);
            //registro.put("comp", comp);

            int cantidad = bd.update("telef ", registro, "IMEI =" +imei, null);
            bd.close();

            if(cantidad == 1){
                Toast.makeText(this,"Se han actualizado los datos", Toast.LENGTH_SHORT).show();


            } else{

                Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();

            }
        }else{

            Toast.makeText(this,"Existen campos vacíos", Toast.LENGTH_SHORT).show();
        }

    }


    public void Consulta (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String imei = txt_imei.getText().toString();

        if (!imei.isEmpty()){

            Cursor fila = bd.rawQuery
                    ("select gama, marca, modelo from telef where imei =" + imei, null);

            if(fila.moveToFirst()){
                txt_gama.setText(fila.getString(0));
                txt_marca.setText(fila.getString(1));
                txt_modelo.setText(fila.getString(2));
                bd.close();

            }
            else {

                Toast.makeText(this, "sin resultados", Toast.LENGTH_SHORT).show();
                bd.close();
            }

        }else{

            Toast.makeText(this, "no dejar vacio el campo IMEI", Toast.LENGTH_SHORT).show();
        }


    }


    public void Eliminar(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String aaa = txt_imei.getText().toString();

        if (!aaa.isEmpty()){

            int borrado  = bd.delete("telef", "imei = " + aaa,null );
            bd.close();
            txt_imei.setText("");
            txt_marca.setText("");
            txt_gama.setText("");
            txt_modelo.setText("");
            if(borrado == 1){

                Toast.makeText(this,"datos eliminados", Toast.LENGTH_SHORT).show();

            }else{

                Toast.makeText(this,"No hay coincidencias", Toast.LENGTH_SHORT).show();
            }

        } else{

            Toast.makeText(this, "no deje el campo imei vacío", Toast.LENGTH_SHORT).show();
        }

    }





}
