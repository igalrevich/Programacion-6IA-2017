package com.revich.mobile.tpfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 42426410 on 7/11/2017.
 */
public class adapterLstEstadisticas extends BaseAdapter {
    Context context;
    int ContHabilidadesBuenas=0,ContHabilidadesMalas=0;
    ArrayList<Presentismo> ListaPresentismo;
    boolean BuscaHabilidadesxJanij=false;
    ArrayList<HabilidadxJanij> ListaHabilidadesxJanij;
    private static LayoutInflater inflater = null;

    public adapterLstEstadisticas(Context context, ArrayList<Presentismo> listaPresentismo, ArrayList<HabilidadxJanij> listaHabilidadesxJanij) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.ListaPresentismo = listaPresentismo;
        this.ListaHabilidadesxJanij = listaHabilidadesxJanij;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if(BuscaHabilidadesxJanij)
        {
            return ListaHabilidadesxJanij.size();
        }
        else
        {
            return ListaPresentismo.size();
        }
    }



    @Override
    public Presentismo getItem(int position) {
        // TODO Auto-generated method stub
        return ListaPresentismo.get(position);
    }

    public HabilidadxJanij getItemHabilidadesxJanij(int position)
    {
        return ListaHabilidadesxJanij.get(position);
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
            vi = inflater.inflate(R.layout.rowestadisticas, null);
        TextView tvFechaEstadisticas= (TextView) vi.findViewById(R.id.tvFechaEstadisticas);
        TextView tvCosasBienJanij= (TextView) vi.findViewById(R.id.tvCosasBienJanij);
        TextView tvCosasMalJanij= (TextView) vi.findViewById(R.id.tvCosasMalJanij);
        TextView tvVinoJanij= (TextView) vi.findViewById(R.id.tvVinoJanij);
        TextView tvTardeJanij= (TextView) vi.findViewById(R.id.tvTardeJanij);
        TextView tvAmbitoJanij= (TextView) vi.findViewById(R.id.tvAmbitoJanij);
        JanijimYGruposManager janijimYGruposManager= new JanijimYGruposManager(context);
        Presentismo MiPresentismo= getItem(position);
        tvFechaEstadisticas.setText(janijimYGruposManager.SelectNombreFechaOAmbitoById(MiPresentismo.Fecha,true));
        tvAmbitoJanij.setText(janijimYGruposManager.SelectNombreFechaOAmbitoById(MiPresentismo.idAmbito,false));
        if(MiPresentismo.asistio)
        {
            tvVinoJanij.setText("Si");
        }
        else
        {
            tvVinoJanij.setText("No");
        }
        if(MiPresentismo.tarde)
        {
            tvTardeJanij.setText("Si");
        }
        else
        {
            tvTardeJanij.setText("No");
        }
        for(int i=0;i<ListaHabilidadesxJanij.size();i++)
        {
            HabilidadxJanij MiHabilidadxJanij= ListaHabilidadesxJanij.get(i);
            if(MiHabilidadxJanij.idFecha==MiPresentismo.Fecha)
            {
               Habilidades MiHabilidad= janijimYGruposManager.SelectHabilidadById(MiHabilidadxJanij.idHabilidad);
               if(MiHabilidad.esPositiva)
               {
                   ContHabilidadesBuenas++;
               }
               else
               {
                   ContHabilidadesMalas++;
               }
            }
        }
        tvCosasBienJanij.setText(String.valueOf(ContHabilidadesBuenas));
        tvCosasMalJanij.setText(String.valueOf(ContHabilidadesMalas));
        ContHabilidadesMalas=0;
        ContHabilidadesBuenas=0;
        return vi;
    }

}
