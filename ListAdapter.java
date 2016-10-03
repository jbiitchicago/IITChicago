package com.example.root.lab8test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;


public class ListAdapter extends ArrayAdapter<Stock> {

    private List<Stock> items;
    public HashMap<String,String> sectors = new HashMap<String, String>();


    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }
    public ListAdapter(Context context, int resource, List<Stock> items) {
        super(context, resource, items);

        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        sectors.put("INFY","IT");
        sectors.put("ABB", "Metals");
        sectors.put("AXISBANK","Banking");
        sectors.put("RELIANCE","FMCG");
        sectors.put("ADANIPORTZ","Infrastructure");
        sectors.put("GLENPHARMA","Pharma");


        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }

        Stock p = getItem(position);


        if (p != null) {

            ImageView tt = (ImageView) v.findViewById(R.id.imageStock);
            TextView tt1 = (TextView) v.findViewById(R.id.name);
            TextView tt3 = (TextView) v.findViewById(R.id.stockInfo);
            RatingBar rb = (RatingBar) v.findViewById(R.id.rating);

            if (tt != null) {
                if (sectors.get(p.getName()).equals("Banking")){
                    tt.setImageResource(R.drawable.bank);
                }
                else if (sectors.get(p.getName()).equals("IT")){
                    tt.setImageResource(R.drawable.it);
                }
                else if (sectors.get(p.getName()).equals("Metals")){
                    tt.setImageResource(R.drawable.metals);
                }
                else if (sectors.get(p.getName()).equals("Infrastructure")){
                    tt.setImageResource(R.drawable.infrastructure);
                }
               else if (sectors.get(p.getName()).equals("Pharma")) {
                    tt.setImageResource(R.drawable.pharma);
                }
            }
            if (tt1 != null) {
                tt1.setText(p.getName());
            }
            if (tt3 != null) {
                tt3.setText("Vol:"+String.valueOf(p.getVolume())+"  LTP:"+String.valueOf(p.getPrice())+"  " +
                        "Change:"+String.valueOf(p.getPercentageChange())+"  Experts Say: "+p.getBuySellCall());
            }
            if (rb != null) {
                float rating = (float)(p.getRating());
                rb.setRating(rating);
            }


        }

        return v;
    }
}





