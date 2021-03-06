package com.revich.mobile.tpn01;

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
    EditText edtNombre;
    EditText edtEmail;
    RadioGroup radio_Genero;
    SeekBar skNivelSatisfaccion;
    CheckBox chbPracticaDeportes;
    RadioButton radio_sexo;
    String strSexo;
    int SelectedId;
    TextView tvProgressSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("TPNO1");
        btnText=(Button)findViewById(R.id.btnText);
        radio_Genero=(RadioGroup) findViewById(R.id.radio_Genero);
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
        skNivelSatisfaccion=(SeekBar) findViewById(R.id.skNivelSatisfaccion);
        tvProgressSeekBar=(TextView)findViewById(R.id.tvProgressSeekBar);
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
        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNombre;
                String strEmail;
                int SeekBarProgress;
                String strPorcentajeSeekBar;
                String PracticaNoPracticaDeportes;
                edtNombre= (EditText) findViewById(R.id.edtNombre);
                edtEmail= (EditText) findViewById(R.id.edtEmail);
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



            }
        });
    }



}
