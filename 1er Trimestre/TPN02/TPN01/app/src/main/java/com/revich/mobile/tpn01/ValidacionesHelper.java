package com.revich.mobile.tpn01;

/**
 * Created by 42426410 on 3/4/2017.
 */
public class ValidacionesHelper {
    public static boolean EsUnInteger(String Texto)
    {
        boolean blnEsInteger=false;
        try
        {
            int TextoNumerico = Integer.parseInt(Texto);
            blnEsInteger=true;
        }
        catch (Exception MiExcepcion)
        {

        }
        return  blnEsInteger;
    }
}
