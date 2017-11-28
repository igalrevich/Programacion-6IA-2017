package com.revich.mobile.tpfinal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 42426410 on 7/11/2017.
 */
public class adapterLstHabilidades extends BaseAdapter {
    Context context;
    ArrayList<Habilidades> Habilidades;
    ArrayList<HabilidadxJanij> HabilidadesxJanij;
    Boolean [] TieneHabilidad;
    private static LayoutInflater inflater = null;

    public adapterLstHabilidades(Context context, ArrayList<Habilidades> Habilidades,ArrayList<HabilidadxJanij> habilidadesxJanij) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.Habilidades = Habilidades;
        this.HabilidadesxJanij=habilidadesxJanij;
        this.TieneHabilidad= new Boolean[Habilidades.size()];
        for(int i=0;i<TieneHabilidad.length;i++)
        {
            TieneHabilidad[i]=false;
        }
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Habilidades.size();
    }

    @Override
    public Habilidades getItem(int position) {
        // TODO Auto-generated method stub
        return Habilidades.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.rowhabilidades, null);
        TextView tvNombreHabilidad= (TextView) vi.findViewById(R.id.tvNombreHabilidad);
        CheckBox chbHabilidad = (CheckBox) vi.findViewById(R.id.chbHabilidad);
        chbHabilidad.setTag(position);
        chbHabilidad.setOnCheckedChangeListener(chbHabilidad_changelistener);
        tvNombreHabilidad.setText(Habilidades.get(position).Nombre);
        if(HabilidadesxJanij.size()>0)
        {
            Boolean EncontroNombreHabilidad=false;
            JanijimYGruposManager janijimYGruposManager= new JanijimYGruposManager(context);
            int Indice=0;
            while(EncontroNombreHabilidad==false && Indice<HabilidadesxJanij.size())
            {
                int IdHabilidad= janijimYGruposManager.SelectIdHabilidad(tvNombreHabilidad.getText().toString());
                if(IdHabilidad==HabilidadesxJanij.get(Indice).idHabilidad)
                {
                    EncontroNombreHabilidad=true;
                    chbHabilidad.setChecked(true);
                }
                else
                {
                    Indice++;
                }
            }
        }
        return vi;
    }

    private CheckBox.OnCheckedChangeListener chbHabilidad_changelistener= new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int position = (Integer) buttonView.getTag();
            TieneHabilidad[position]=isChecked;
        }
    };

}
