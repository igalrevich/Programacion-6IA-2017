package com.revich.mobile.tpfinal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 42426410 on 7/11/2017.
 */
public class adapterLstJanijimEnGrupos extends BaseAdapter {
    Context context;
    ArrayList<Janij> Janijim;
    Boolean [] checkedVino;
    Boolean [] checkedTarde;
    int Indice=0;
    private static LayoutInflater inflater = null;

    public adapterLstJanijimEnGrupos(Context context,  ArrayList<Janij> Janijim) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.Janijim = Janijim;
        this.checkedVino= new Boolean[Janijim.size()];
        this.checkedTarde= new Boolean[Janijim.size()];
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
        checkedVino[position]=chbVinoJanij.isChecked();
        chbVinoJanij.setTag(position);
        chbLlegoTardeJanij.setTag(position);
        checkedTarde[position]=chbLlegoTardeJanij.isChecked();
        chbVinoJanij.setOnCheckedChangeListener(chbVinoJanij_changelistener);
        Indice=position;
        chbLlegoTardeJanij.setOnCheckedChangeListener(chbLlegoTardeJanij_changelistener);
        final Janij MiJanij = getItem(position);
        tvNombreJanijPresentismo.setText(MiJanij.Nombre);
        tvApellidoJanijPresentismo.setText(MiJanij.Apellido);
        tvDNIJanijPresentismo.setText(String.valueOf(MiJanij.DNI));
        //text.setText(data[position]);
        return vi;
    }

    private CheckBox.OnCheckedChangeListener chbVinoJanij_changelistener= new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int position = (Integer) buttonView.getTag();
            checkedVino[position]=isChecked;
            Log.d("CheckedVinoadapter_pos "+String.valueOf(position), String.valueOf(checkedVino[position]));
        }
    };

    private CheckBox.OnCheckedChangeListener chbLlegoTardeJanij_changelistener= new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int position = (Integer)buttonView.getTag();
            checkedTarde[position]=isChecked;
            Log.d("CheckTardeadapter_pos "+String.valueOf(position), String.valueOf(checkedTarde[position]));
        }
    };

}
