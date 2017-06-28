package com.revich.mobile.tpn06;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Activity_Nombre extends AppCompatActivity {
    Button btnContinuar;
    EditText edtNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__nombre);
        setTitle("SITIS");
        ObtenerReferencias();
        SetearListeners();
    }

    private void ObtenerReferencias()
    {
      btnContinuar= (Button) findViewById(R.id.btnContinuar) ;
      edtNombre= (EditText) findViewById(R.id.edtNombre);
    }

    private void SetearListeners()
    {
        btnContinuar.setOnClickListener(btnContinuar_click);
    }

    private View.OnClickListener btnContinuar_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String Nombre= edtNombre.getText().toString();
            if(Nombre.isEmpty()==false)
            {
                DatosJuego.SetNombre(Nombre);
                String url= "https://tp4ort.firebaseio.com/countries.json";
                new ObtenerPaisesYCiudades().execute(url,"Paises");
            }
            else
            {
                Toast msg= Toast.makeText(getApplicationContext(),"Ingrese su nombre",Toast.LENGTH_SHORT);
                msg.show();
            }
        }
    };

    private void IrAActivityObjetivos()
    {
        Intent MiIntent= new Intent(Activity_Nombre.this,Activity_Objetivos.class);
        startActivity(MiIntent);
    }

    public class ObtenerPaisesYCiudades extends AsyncTask <String,Integer,Integer>
    {
        @Override

        protected Integer doInBackground(String... params) {

            OkHttpClient client = new OkHttpClient();
            String url= params[0];
            String QueParsea= params[1];
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String resultado = response.body().string();
                if(QueParsea.equals("Paises"))
                {
                  return parsearResultadoPaises(resultado);
                }
                else
                {
                    return parsearResultadoGeonames(resultado);
                }

            } catch (IOException | JSONException e) {
                Log.d("Error",e.getMessage());
                return 0;
            }


        }

        Integer parsearResultadoPaises(String result) throws JSONException {
            JSONArray jsonPaises = new JSONArray(result);
            for (int i=0; i<jsonPaises.length(); i++)
            {
                JSONObject jsonResultado = jsonPaises.getJSONObject(i);
                String ObjetoPais= jsonResultado.toString();
                Gson gson = new Gson();
                Pais MiPais = gson.fromJson(ObjetoPais,Pais.class);
                DatosJuego.SetPaises(MiPais);
            }
            return 1;
        }

        Integer parsearResultadoGeonames(String result) throws JSONException {
            JSONArray jsonGeonames = new JSONArray(result);
            for (int i=0; i<jsonGeonames.length(); i++)
            {
                JSONObject jsonResultado = jsonGeonames.getJSONObject(i);
                String ObjetoGeonames= jsonResultado.toString();
                Gson gson = new Gson();
                Geonames MiGeonames = gson.fromJson(ObjetoGeonames,Geonames.class);
                DatosJuego.SetGeonames(MiGeonames);
            }
            return 2;
        }



        @Override
        protected void onPostExecute(Integer num) {

            switch (num)
            {
                case 0:
                    break;
                case 1:
                    String url= "https://tp4ort.firebaseio.com/geonames.json";
                    new ObtenerPaisesYCiudades().execute(url,"Geonames");
                    break;
                case 2:
                    IrAActivityObjetivos();
                    break;
            }
        }

    }

}
