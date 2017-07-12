package com.revich.mobile.tpn06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Activity_Ranking extends AppCompatActivity {

    ListView lstRanking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ranking);
        lstRanking= (ListView) findViewById(R.id.lstRanking);
        UsuariosManager usuariosManager= new UsuariosManager(this);
        ArrayList<Usuario> ListaUsuarios= usuariosManager.SelectRegistros();
        ArrayAdapter<Usuario> adapterAlumnos= new ArrayAdapter<Usuario>(this,android.R.layout.simple_list_item_1,ListaUsuarios);
        lstRanking.setAdapter(adapterAlumnos);
    }
}
