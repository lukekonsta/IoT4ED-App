package symbiote.h2020.eu.sampleapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hadiidbouk.charts.ChartProgressBar;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Lim extends AppCompatActivity {

    private ChartProgressBar mChart;
    Button no,so2,pm10,pm25;

    SharedPreferences sharedPrefs;
    Gson gson = new Gson();
    ArrayList <Float> nitrogenLimassol = new ArrayList<Float>();//for putting the data


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lim);


        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(Lim.this);
        Type type = new TypeToken<List<String>>() {}.getType();


        //Nitrogen - Limassol
        String json = sharedPrefs.getString("array_limassol_nitrogen", "");
        List<String> arrayList = gson.fromJson(json, type);
        System.out.println("Length is: "+arrayList.size());

        for(int counter = 0;counter<arrayList.size();counter++){

            String value = arrayList.get(counter);
            if(value.contains(",")){
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if(val.contains(",")){
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            nitrogenLimassol.add(final_float_total);

        }

        System.out.println("nitrogenLimassol.size(): "+nitrogenLimassol.size());



        BarChart mBarChart = (BarChart) findViewById(R.id.chart);

        List<BarEntry> entries = new ArrayList<>();

        for(int c=0;c<7;c++){

            float value = nitrogenLimassol.get(c);
            entries.add(new BarEntry(c, value));

        }

        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        mBarChart.setData(data);
        mBarChart.setFitBars(true); // make the x-axis fit exactly all bars
        mBarChart.invalidate(); // refresh

        /*
                BarChart mBarChart = (BarChart) findViewById(R.id.barchart);

        mBarChart.addBar(new BarModel(2.3f, 0xFF123456));
        mBarChart.addBar(new BarModel(2.f,  0xFF343456));
        mBarChart.addBar(new BarModel(3.3f, 0xFF563456));
        mBarChart.addBar(new BarModel(1.1f, 0xFF873F56));
        mBarChart.addBar(new BarModel(2.7f, 0xFF56B7F1));
        mBarChart.addBar(new BarModel(2.f,  0xFF343456));
        mBarChart.addBar(new BarModel(0.4f, 0xFF1FF4AC));
        mBarChart.addBar(new BarModel(4.f,  0xFF1BA4E6));

        mBarChart.startAnimation();*/


    }//onCreate


}
