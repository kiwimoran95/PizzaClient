package com.example.kiwi.mypizzaclient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by kiwi_ on 18/1/2018.
 */

public class GetHttpLocations extends AsyncTask<Void, Void, String>{

    private Location location;
    private Context httpContext;
    ProgressDialog progressDialog;
    private String idparametro;

    public GetHttpLocations(Location locationprev, Context httpContext, String idparam) {
        this.location = locationprev;
        this.httpContext = httpContext;
        this.idparametro = idparam;
    }

    protected  void onPreExecute(){
        super.onPreExecute();
        progressDialog = ProgressDialog.show(httpContext, "Descargando", "por favor, espere");
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        try {
            String wsURL = "http://puceing.edu.ec:15000/MartinMoran/REST/api/Location/"+this.idparametro;
            URL url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = inputStreamToString(in);
        }catch (Exception e){e.printStackTrace();}
        return result;
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        progressDialog.dismiss();
        try{
            JSONArray jsonarray = new JSONArray(URLDecoder.decode(s, "UTF-8"));
            JSONObject jsonObject = jsonarray.getJSONObject(0);
            int id = Integer.parseInt(jsonObject.getString("ID"));
            double LATITUD = Double.parseDouble(jsonObject.getString("LATITUD"));
            double LONGITUD = Double.parseDouble(jsonObject.getString("LONGITUD"));
            this.location.setId(id);
            this.location.setLatitud(LATITUD);
            this.location.setLongitud(LONGITUD);
            System.out.println("Lat: "+this.location.getLatitud() + " Long:" + this.location.getLongitud());
        }catch (JSONException | UnsupportedEncodingException e){e.printStackTrace();}
    }

    private String inputStreamToString (InputStream is){
        String rLine = "";
        StringBuilder answer = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader rd = new BufferedReader(isr);
        try{
            while ((rLine = rd.readLine()) != null){
                answer.append(rLine);
            }
        }catch (Exception e){e.printStackTrace();}
        return  answer.toString();
    }

    public double getLatitud(){
        return this.location.getLatitud();
    }

    public double getLongitud(){
        return this.location.getLongitud();
    }

}
