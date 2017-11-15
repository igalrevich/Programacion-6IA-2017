package com.revich.mobile.tpfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 42426410 on 7/11/2017.
 */
public class adapterLstJanijimEnGrupos extends BaseAdapter {
    Context context;
    ArrayList<Janij> Janijim;
    private static LayoutInflater inflater = null;

    public adapterLstJanijimEnGrupos(Context context,  ArrayList<Janij> Janijim) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.Janijim = Janijim;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Janijim.size();
    }

    @Override
    public Janij getItem(int position) {
        // TODO Auto-generated method stub
        return Janijim.get(position);
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
            vi = inflater.inflate(R.layout.rowpresentismojanijim, null);
        TextView tvNombreJanijPresentismo = (TextView) vi.findViewById(R.id.tvNombreJanijPresentismo);
        TextView tvApellidoJanijPresentismo= (TextView) vi.findViewById(R.id.tvApellidoJanijPresentismo);
        TextView tvDNIJanijPresentismo = (TextView) vi.findViewById(R.id.tvDNIJanijPresentismo);
        CheckBox chbVinoJanij = (CheckBox) vi.findViewById(R.id.chbVinoJanij);
        CheckBox chbLlegoTardeJanij = (CheckBox) vi.findViewById(R.id.chbLlegoTardeJanij);
        final Janij MiJanij = getItem(position);
        tvNombreJanijPresentismo.setText(MiJanij.Nombre);
        tvApellidoJanijPresentismo.setText(MiJanij.Apellido);
        tvDNIJanijPresentismo.setText(String.valueOf(MiJanij.DNI));
       
        //text.setText(data[position]);
        return vi;
    }

}
