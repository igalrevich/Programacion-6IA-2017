package com.revich.mobile.tpn06;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class Activity_Juego2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int [] IndicesCiudades;
    double Lat, Lng;
    boolean IndicesCiudadesIguales;
    Geonames [] Ciudades;
    Button btnCiudad1, btnCiudad2, btnCiudad3, btnCiudad4;
    Button [] VecBotones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__juego2);
        IndicesCiudades= new int[] {0,0,0,0};
        Lat=0;
        Lng=0;
        IndicesCiudadesIguales=false;
        Ciudades= new Geonames[4];
        ObtenerReferencias();
        Obtener4CiudadesRandom();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Lat, Lng);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    private void ObtenerReferencias()
    {

        btnCiudad1= (Button) findViewById(R.id.btnCiudad1);
        btnCiudad2= (Button) findViewById(R.id.btnCiudad2);
        btnCiudad3= (Button) findViewById(R.id.btnCiudad3);
        btnCiudad4= (Button) findViewById(R.id.btnCiudad4);
        VecBotones= new Button[] {btnCiudad1,btnCiudad2,btnCiudad3,btnCiudad4};

    }

    private void Obtener4CiudadesRandom()
    {
        Random rand= new Random();
        for(int i=0; i<4; i++)
        {
          int Indice= rand.nextInt(DatosJuego.GetGeonames().size());
          IndicesCiudades[i]=Indice;
          if(i!=0)
          {
            for(int j=0;j<i;j++)
            {
                if (IndicesCiudades[j]==IndicesCiudades[i])
                {
                  IndicesCiudadesIguales=true;
                  Obtener1CiudadRandom(i);
                }
            }

          }
        }
        if(IndicesCiudadesIguales==false)
        {
          AsignarCiudadesAlMapa();
        }
    }

    private void Obtener1CiudadRandom(int Indice)
    {
        Random rand= new Random();
        int NumIndice= rand.nextInt(DatosJuego.GetGeonames().size());
        IndicesCiudades[Indice]=NumIndice;
        for(int k=0;k<Indice;k++)
        {
            if (IndicesCiudades[k]==IndicesCiudades[Indice])
            {
              Obtener1CiudadRandom(Indice);
            }
        }
    }

    private void AsignarCiudadesAlMapa()
    {
        for(int i=0;i<IndicesCiudades.length;i++)
        {
            int Indice= IndicesCiudades[i];
            Ciudades[i]=DatosJuego.GetGeonames().get(Indice);
            VecBotones[i].setText(Ciudades[i].name);
            if(i==0)
            {
                Lat=Ciudades[i].lat;
                Lng=Ciudades[i].lng;
            }
        }
    }


}
