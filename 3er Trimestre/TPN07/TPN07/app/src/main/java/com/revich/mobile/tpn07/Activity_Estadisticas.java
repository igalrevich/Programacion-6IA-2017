package com.revich.mobile.tpn07;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Estadisticas extends AppCompatActivity {
    TextView tvInvocacionesApi, tvCantHombres, tvCantMujeres, tvPromEdadHombres, tvPromEdadMujeres ;
    LinearLayout MiLayout;
    Float EdadVaronesSharedPreferences;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        ObtenerReferencias();
        prefs = getSharedPreferences("EstadisticasFotos", MODE_PRIVATE);
        EdadVaronesSharedPreferences=prefs.getFloat("EdadVarones", -1);
        if(EdadVaronesSharedPreferences==-1)
        {
            LeerSharedPreferences(false);
        }
        else
        {
            LeerSharedPreferences(true);
        }

    }

    private void ObtenerReferencias()
    {
        tvInvocacionesApi = (TextView) findViewById(R.id.tvInvocacionesApi);
        tvCantHombres = (TextView) findViewById(R.id.tvCantHombres);
        tvCantMujeres = (TextView) findViewById(R.id.tvCantMujeres);
        tvPromEdadHombres = (TextView) findViewById(R.id.tvPromEdadHombres);
        tvPromEdadMujeres= (TextView) findViewById(R.id.tvPromEdadMujeres);
        MiLayout= (LinearLayout) findViewById(R.id.MiLayout);
    }

    private  void LeerSharedPreferences (boolean HaySharedPreferences)
    {
        if(HaySharedPreferences)
        {
            float PromedioEdadMujeres=prefs.getFloat("EdadMujeres",0)/prefs.getInt("CantHombres",0);
            float PromedioEdadVarones= EdadVaronesSharedPreferences/prefs.getInt("CantHombres",0);
            tvCantHombres.setText(String.valueOf(prefs.getInt("CantHombres",0)));
            tvCantMujeres.setText(String.valueOf(prefs.getInt("CantMujeres",0)));
            tvPromEdadHombres.setText(String.valueOf(PromedioEdadVarones));
            tvPromEdadMujeres.setText(String.valueOf(PromedioEdadMujeres));
            tvInvocacionesApi.setText(String.valueOf(prefs.getInt("InvocacionesApi",0)));
        }
        else
        {
            tvCantHombres.setText("-");
            tvCantMujeres.setText("-");
            tvPromEdadHombres.setText("-");
            tvPromEdadMujeres.setText("-");
            tvInvocacionesApi.setText("-");
            Toast msg = Toast.makeText(getApplicationContext(),"No se hicieron invocaciones a la Api",Toast.LENGTH_SHORT);
            msg.show();
        }

        if(prefs.getBoolean("Sonrie",false))
        {
          MiLayout.setBackgroundColor(Color.parseColor("#5EFF33"));
        }
        else
        {
            MiLayout.setBackgroundColor(Color.parseColor("#E5300B"));
        }

    }
}
