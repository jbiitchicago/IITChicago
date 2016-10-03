package com.example.root.lab8test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StockActivity extends AppCompatActivity {
    String name="";
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.d("Message",message);

        ImageView img = (ImageView) findViewById(R.id.companylogo);

        if(message.contains("INFY")){
            name = "INFY";
        img.setImageResource(R.drawable.infy);}
        else if(message.contains("GLENPHARMA")){
            name = "GLENPHARMA";
            img.setImageResource(R.drawable.glenpharma);}
        else if(message.contains("ADANIPORTZ")){
            name = "ADANIPORTZ";
            img.setImageResource(R.drawable.adaniportz);}
        else if(message.contains("AXISBANK")){
            name = "AXISBANK";
            img.setImageResource(R.drawable.axisbank);}
        else if(message.contains("RELIANCE")){
            name = "RELIANCE";
            img.setImageResource(R.drawable.reliance);}


      //  img.setImageResource(getResources().getIdentifier(name, "drawable", "com.example.root.lab8test"));
        TextView tx = (TextView) findViewById(R.id.stockDetail);
        tx.setText(message);



        Button done = (Button) findViewById(R.id.doneBtn);
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText edtxt = (EditText) findViewById(R.id.editbox);
                if (edtxt.getText().toString() != null) {
                    SqlHelperv2 sdb = new SqlHelperv2(context);

                    try {
                        sdb.addAlert(name, Double.parseDouble(edtxt.getText().toString()));

                        Toast.makeText(StockActivity.this,
                                "Alert Set for Stock: "+name+" at price "+edtxt.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }catch(Exception ex){
                        ex.printStackTrace();
                        Log.d("Price is bad",edtxt.getText().toString());
                    }


                }
               // Intent intent1 = new Intent(StockActivity.this,MainActivity.class);
               // startActivity(intent1);
                finish();
            }
        });
    }

}
