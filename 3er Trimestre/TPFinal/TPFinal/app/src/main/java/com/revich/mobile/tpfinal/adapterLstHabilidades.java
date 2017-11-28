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
public class adapterLstHabilidades extends BaseAdapter {
    Context context;
    ArrayList<Habilidades> Habilidades;
    private static LayoutInflater inflater = null;

    public adapterLstHabilidades(Context context, ArrayList<Habilidades> Habilidades) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.Habilidades = Habilidades;
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
        tvNombreHabilidad.setText(Habilidades.get(position).Nombre);
        return vi;
    }

}
