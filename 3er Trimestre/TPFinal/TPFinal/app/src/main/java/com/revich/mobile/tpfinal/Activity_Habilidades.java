package com.revich.mobile.tpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Habilidades extends AppCompatActivity {
    Button btnConfirmarHabilidades;
    TextView tvNombreJanijHabilidades, tvFechaHabilidades;
    EditText edtObservaciones;
    ListView lstHabilidades;
    ArrayList<Habilidades> ListaHabilidades;
    int idGrupo,idAmbito,idJanij;
    String Fecha;
    JanijimYGruposManager janijimYGruposManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__habilidades);
        ObtenerReferenciasYSetearListeners();
        janijimYGruposManager= new JanijimYGruposManager(this);
        ListaHabilidades= janijimYGruposManager.SelectHabilidades();
        lstHabilidades.setAdapter(new adapterLstHabilidades(this,ListaHabilidades));
        Intent ElIntentQueVino= getIntent();
        Bundle ElBundleQueVino= ElIntentQueVino.getExtras();
        String NombreJanij=ElBundleQueVino.getString("NombreJanij");
        String ApellidoJanij=ElBundleQueVino.getString("ApellidoJanij");
        tvNombreJanijHabilidades.setText(NombreJanij+" "+ApellidoJanij);
        Fecha= ElBundleQueVino.getString("Fecha");
        idAmbito= ElBundleQueVino.getInt("idAmbito");
        idJanij= ElBundleQueVino.getInt("idJanij");
        idGrupo= ElBundleQueVino.getInt("idGrupo");
        tvFechaHabilidades.setText(Fecha);

    }

    private void ObtenerReferenciasYSetearListeners()
    {
        lstHabilidades=(ListView) findViewById(R.id.lstHabilidades);
        btnConfirmarHabilidades = (Button) findViewById(R.id.btnConfirmarHabilidades);
        btnConfirmarHabilidades.setOnClickListener( btnConfirmarHabilidades_click);
        //lstHabilidades.setOnItemClickListener(lstHabilidades_itemclick);
        tvNombreJanijHabilidades = (TextView) findViewById(R.id.tvNombreJanijHabilidades);
        edtObservaciones= (EditText) findViewById(R.id.edtObservaciones);
        tvFechaHabilidades = (TextView) findViewById(R.id.tvFechaHabilidades);
    }

    private View.OnClickListener btnConfirmarHabilidades_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            View v;
            if(lstHabilidades.getCount()>0)
            {
                for(int i=0;i<lstHabilidades.getCount();i++)
                {
                    HabilidadxJanij MiHabilidadxJanij;
                    try
                    {

                        //lstHabilidades.isItemChecked()
                        v = lstHabilidades.getAdapter().getView(i, null, null);
                        TextView tvNombreHabilidad= (TextView) v.findViewById(R.id.tvNombreHabilidad);
                        CheckBox chbHabilidad= (CheckBox) v.findViewById(R.id.chbHabilidad);
                        MiHabilidadxJanij=new HabilidadxJanij();
                        MiHabilidadxJanij.idGrupo=idGrupo;
                        MiHabilidadxJanij.idAmbito=idAmbito;
                        MiHabilidadxJanij.idFecha=janijimYGruposManager.SelectIdAmbitoOFecha("",Fecha,false);
                        MiHabilidadxJanij.idJanij=idJanij;
                        MiHabilidadxJanij.Observaciones= edtObservaciones.getText().toString();
                        if(chbHabilidad.isChecked())
                        {
                            String NombreHabilidad= tvNombreHabilidad.getText().toString();
                            MiHabilidadxJanij.idHabilidad=janijimYGruposManager.SelectIdHabilidad(NombreHabilidad);
                            janijimYGruposManager.InsertarOModificarHabilidadxJanij(MiHabilidadxJanij);
                            Toast msg= Toast.makeText(getApplicationContext(),"Se inserto la habilidad al janij correspondiente con exito",Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    }
                    catch (Exception ex)
                    {
                        Log.e("Exception", ex.getMessage() );
                    }
                }

            }
        }
    };
}
