package com.example.kiwi.mypizzaclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private String value = null;
    private Location location = new Location();
    private EditText edtText;
    private String idrepartidor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void FillLocation(String s){
        GetHttpLocations wsLocations = new GetHttpLocations(location, MainActivity.this, s);
        wsLocations.execute();
        this.location.setLatitud(wsLocations.getLatitud());
        this.location.setLongitud(wsLocations.getLongitud());
    }

    public void buttonClicked(View view){
        edtText  = (EditText) findViewById(R.id.idrepartidor);
        idrepartidor = edtText.getText().toString();
        FillLocation(this.idrepartidor);
        value = String.valueOf(location.getLatitud()).trim()+","+String.valueOf(location.getLongitud());
        System.out.println("valueeeeeeeeee: "+location.getLongitud());
        Intent intent = new Intent(MainActivity.this,MapsActivity.class);
        intent.putExtra("LOCVAL", value);
        startActivity(intent);
    }
}
