package com.revich.mobile.tpfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
            vi = inflater.inflate(R.layout.row_lstjanijimengrupo, null);
        TextView tvNombreJanijLst = (TextView) vi.findViewById(R.id.tvNombreJanij);
        TextView tvApellidoJanijLst = (TextView) vi.findViewById(R.id.tvApellidoJanij);
        TextView tvDNIJanijLst = (TextView) vi.findViewById(R.id.tvDNIJanij);
        Button btnEliminarJanijLista = (Button) vi.findViewById(R.id.btnEliminarJanijLista);
        Janij MiJanij = getItem(position);
        tvNombreJanijLst.setText(MiJanij.Nombre);
        tvApellidoJanijLst.setText(MiJanij.Apellido);
        tvDNIJanijLst.setText(String.valueOf(MiJanij.DNI));
        btnEliminarJanijLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Grupo MiGrupo= new Grupo();
            }
        });
        //text.setText(data[position]);
        return vi;
    }

}
