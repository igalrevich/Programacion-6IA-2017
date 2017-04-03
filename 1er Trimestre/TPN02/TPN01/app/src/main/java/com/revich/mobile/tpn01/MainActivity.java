package com.revich.mobile.tpn01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button btnText;
    EditText edtNombre, edtEmail;
    RadioGroup radio_Genero;
    SeekBar skNivelSatisfaccion;
    CheckBox chbPracticaDeportes;
    RadioButton radio_sexo;
    TextView tvProgressSeekBar;


    String strSexo;
    int SelectedId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("TPNO2");

        ObtenerReferencias();

        SetearListeners();

    }


    private void ObtenerReferencias()
    {
        btnText             = (Button)findViewById(R.id.btnText);
        radio_Genero        = (RadioGroup) findViewById(R.id.radio_Genero);
        skNivelSatisfaccion = (SeekBar) findViewById(R.id.skNivelSatisfaccion);
        tvProgressSeekBar   = (TextView)findViewById(R.id.tvProgressSeekBar);
        edtNombre           = (EditText) findViewById(R.id.edtNombre);
        edtEmail            = (EditText) findViewById(R.id.edtEmail);
        chbPracticaDeportes = (CheckBox) findViewById(R.id.chbPracticaDeportes);

        //SelectedId          = radio_Genero.getCheckedRadioButtonId();
        //if(SelectedId!=-1)
        //{radio_sexo=(RadioButton) findViewById(SelectedId);}

    }

    private void     SetearListeners()
    {
        btnText.setOnClickListener(btnText_Click);
    }

    private View.OnClickListener btnText_Click = new View.OnClickListener(){
        public void onClick(View v){
            Toast.makeText(getApplicationContext(), "Click" , Toast.LENGTH_SHORT).show();
        }
    };






    private Boolean DatosValidos()
    {
        boolean EsValido=true;
        String strErrores= "";
        if(edtNombre.getText().toString().isEmpty())
        {
            EsValido=false;
            strErrores= strErrores + " Complete el nombre.";
        }
        if(edtEmail.getText().toString().isEmpty())
        {
            EsValido=false;
            strErrores= strErrores + " Complete el mail.";
        }
        if(SelectedId==-1)
        {
            EsValido=false;
            strErrores= strErrores + " Seleccione su genero.";
        }
        return  EsValido;

    }

    private void IniciarSegundaActividad()
    {
        String strNombre= edtNombre.getText().toString();
        String strEmail = edtEmail.getText().toString();
        strSexo=radio_sexo.getText().toString();
        int SeekBarProgress=skNivelSatisfaccion.getProgress();
        String strPorcentajeSeekBar=SeekBarProgress+" %";
        String strPracticaNoPracticaDeportes;
        if(chbPracticaDeportes.isChecked())
        {
            strPracticaNoPracticaDeportes="SI te gusta el deporte" ;
        }
        else
        {
            strPracticaNoPracticaDeportes="NO te gusta el deporte";
        }
        Intent intent= new Intent(MainActivity.this,Main2Activity.class);
        Bundle ElBundle= new Bundle();
        ElBundle.putString("Nombre",strNombre);
        ElBundle.putString("Email",strEmail);
        ElBundle.putString("Genero",strSexo);
        ElBundle.putString("PorcentajeSatisfaccion",strPorcentajeSeekBar);
        ElBundle.putString("PracticaDeportes",strPracticaNoPracticaDeportes);
        intent.putExtras(ElBundle);
        startActivity(intent);
    }



    radio_Genero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup Group, int CheckedId)
        {
            SelectedId=CheckedId;
            radio_sexo=(RadioButton) findViewById(SelectedId);
            strSexo=radio_sexo.getText().toString();
            Toast msg = Toast.makeText(getApplicationContext(),"Seleccionaste "+strSexo,Toast.LENGTH_SHORT);
            msg.show();
        }
    });

    skNivelSatisfaccion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int Progress, boolean fromUser)
        {
            tvProgressSeekBar.setText(String.valueOf(Progress)+" %");

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });
    private void setButtonOnClick(int IdDelBoton, View.OnClickListener ElListener )
    {
        Button elBoton = (Button)this.findViewById(IdDelBoton);
        elBoton.setOnClickListener(ElListener);
    }
    private OnClickListener ElListener = new OnClickListener()
    {
        public void onClick(View v)
        {}
    }
        /*btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNombre;
                String strEmail;
                int SeekBarProgress;
                String strPorcentajeSeekBar;
                String PracticaNoPracticaDeportes;

                strNombre = edtNombre.getText().toString();
                strEmail = edtEmail.getText().toString();
                SelectedId=radio_Genero.getCheckedRadioButtonId();
                radio_sexo=(RadioButton) findViewById(SelectedId);
                strSexo=radio_sexo.getText().toString();
                SeekBarProgress=skNivelSatisfaccion.getProgress();
                strPorcentajeSeekBar=SeekBarProgress+" %";
                chbPracticaDeportes=(CheckBox) findViewById(R.id.chbPracticaDeportes);
                if(chbPracticaDeportes.isChecked())
                {
                   PracticaNoPracticaDeportes="SI te gusta el deporte" ;
                }
                else
                {
                    PracticaNoPracticaDeportes="NO te gusta el deporte";
                }
                Toast msg = Toast.makeText(getApplicationContext(),"Hola, "+strNombre+"\n"+"("+strEmail+")"+"\n"+"sos "+strSexo+"\n"+PracticaNoPracticaDeportes+"\n"+" y tu nivel de satisfaccion es de ("+strPorcentajeSeekBar+")",Toast.LENGTH_SHORT);
                msg.show();



            }*/
});
        }



        }
