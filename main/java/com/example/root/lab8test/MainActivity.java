package com.example.root.lab8test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private Spinner spinner;
    public final static String EXTRA_MESSAGE = "com.example.root.lab8test.MESSAGE";
    public SqlHelpers sdb = new SqlHelpers(this);
    Button button, button1, button2;
    Dialog dialog;
    static String operation;
    Context context = this;

    ArrayList<String> stocknames = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Log.d("Name----------- ", "Jigisha Aryya");
        Log.d("TimeStamp:", "Cur dt=" + timeStamp);

        Intent intent = getIntent();
      //  String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

      //  String[] creds = message.split(":");
      //  if(!sdb.addCredentials(creds[0],creds[1])){
         //   finish();
     //   }
        /** CRUD Operations **/
        stocknames.add("INFY");stocknames.add("ABB");stocknames.add("AXISBANK");stocknames.add("RELIANCE");stocknames.add("ADANIPORTZ");

        sdb.addStock(new Stock("INFY", 1290.75, 2518205, "BUY", 0.34,  4));
        sdb.addStock(new Stock("ABB", 431.20, 58910, "SELL", -1.80, 3));
        sdb.addStock(new Stock("AXISBANK", 520.65, 1002289, "BUY", 2.6, 5));
        sdb.addStock(new Stock("RELIANCE", 1098.90, 55171, "SELL", -3.78,  3));
        sdb.addStock(new Stock("ADANIPORTZ", 61.45, 109916, "BUY", 5.9,  4));

       /* sdb.addStock(new Stock("ONGC", 217, 4058956, "BUY", -0.07, "Commodities", 2));
        sdb.addStock(new Stock("VEDL", 103.80, 68991972, "BUY", 3.1, "Metals", 5));
        sdb.addStock(new Stock("GLENPHARMA", 1029, 10010, "SELL", -0.57, "Pharma", 4));
        sdb.addStock(new Stock("SBI", 198.40, 120018881, "BUY", 0.65, "PSU", 5));
        sdb.addStock(new Stock("TATAPOWER", 41, 8816252, "SELL", 2.60, "Energy", 3));
        sdb.addStock(new Stock("HDIL", 780.30, 626511, "BUY", 1.2, "Realty", 2));
        sdb.addStock(new Stock("ICICIBNK", 220, 14919927, "BUY", 0.28, "Banking", 3));*/

        // get all books
        List<Stock> list = sdb.getAllStocksWithBuyCall();
        List<Stock> list1 = sdb.getAllStocksWithSellCall();

        final List<Stock> allStocks = new ArrayList<Stock>();
        allStocks.addAll(list);allStocks.addAll(list1);

        ListView listContent = (ListView) findViewById(R.id.list);
        List<Stock> stocks;


        //get data from the table by the ListAdapter
        ListAdapter customAdapter = new ListAdapter(this, R.layout.itemlistrow, allStocks);
        listContent.setAdapter(customAdapter);

        //to print the count
        sdb.getIds();
        addListenerOnSpinnerItemSelection();
        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ArrayList<String> resultStrings = new ArrayList<String>();
                int count;
                if (operation.contains("Highest Rated")) {
                    resultStrings = sdb.getHighestRatedNames();


                } else if (operation.contains("Lowest Rated")) {
                    resultStrings = sdb.getLowestRatedNames();

                } else if (operation.contains("Total")) {
                    count = sdb.getIds();
                    resultStrings.add(String.valueOf(count));

                } else if (operation.contains("Highest Gaining")) {

                    resultStrings = sdb.getHighestPositiveChangedStocks();
                } else if (operation.contains("Highest Losing")) {
                    resultStrings = sdb.getHighestNegativeChangedStocks();
                } else if (operation.contains("BUY")) {
                    List<Stock> list = sdb.getAllStocksWithBuyCall();
                    for (Stock stk : list) {
                        resultStrings.add(stk.getName());
                    }

                } else if (operation.contains("SELL")) {
                    List<Stock> list = sdb.getAllStocksWithSellCall();
                    for (Stock stk : list) {
                        resultStrings.add(stk.getName());
                    }
                }

                final ArrayList<String> res = resultStrings;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                //Uncomment the below code to Set the message and title from the strings.xml file
                //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("RESULT SET::: "+res.toString())
                        .setCancelable(false)
                        .setPositiveButton("QUIT APP", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("CONTINUE", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Analytics Result");
                alert.show();
            }});


        ListView lists = (ListView)findViewById(R.id.list);
        lists.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Intent intent = new Intent(context, StockActivity.class);
                intent.putExtra(EXTRA_MESSAGE, allStocks.get(position).toString());
                Log.d("get(position).99()",allStocks.get(position).toString());
              //  intent.putExtra(EXTRA_MESSAGE1,allStocks.get(position).getName());
                startActivity(intent);
            }
        });
       //// button1 = (Button) findViewById(R.id.button1);//refresh
       // button2 = (Button) findViewById(R.id.button2);//set alert


    }

    public void addListenerOnSpinnerItemSelection() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {


        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            Toast.makeText(parent.getContext(),
                    "Operation Selected : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();
            operation = String.valueOf(parent.getItemAtPosition(pos));


        }




        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }
}
