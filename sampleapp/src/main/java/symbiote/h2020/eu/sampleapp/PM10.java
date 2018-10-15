package symbiote.h2020.eu.sampleapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PM10 extends AppCompatActivity {

    Button one,two,three;
    TextView setTitle;

    ArrayList<Float> pm10Vienna = new ArrayList<Float>();
    ArrayList <Float> pm10Zagreb = new ArrayList<Float>();
    ArrayList <Float> pm10Limassol = new ArrayList<Float>();

    SharedPreferences sharedPrefs;
    Gson gson = new Gson();

    private BarChart chart;
    float barWidth;
    float barSpace;
    float groupSpace;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PM10.this, Charts.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pm10);

        barWidth = 0.7f;
        barSpace = 1.5f;
        groupSpace = 0.4f;

        setTitle = (TextView)findViewById(R.id.showTitle);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String formatted = format1.format(calendar.getTime());
        setTitle.setText("Σύγκριση Δεδομένων για: \n" +formatted);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(PM10.this);
        Type type = new TypeToken<List<String>>() {
        }.getType();

        String json12 = sharedPrefs.getString("array_vienna_pm10", "");
        List<String> arrayList12 = gson.fromJson(json12, type);

        //for(int counter = 0;counter<arrayList12.size();counter++){
        for(int counter = 0;counter<1;counter++){

            String value = arrayList12.get(counter);
            if(value.contains(",")){
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if(val.contains(",")){
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            pm10Vienna.add(final_float_total);

        }

        System.out.println("Length is: "+pm10Vienna.size());


        String json13 = sharedPrefs.getString("array_zagreb_pm10", "");
        List<String> arrayList13 = gson.fromJson(json13, type);

        for(int counter = 0;counter<1;counter++){

            String value = arrayList13.get(counter);
            if(value.contains(",")){
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if(val.contains(",")){
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            pm10Zagreb.add(final_float_total);

        }

        System.out.println("Length is: "+pm10Zagreb.size());



        String json14 = sharedPrefs.getString("array_limassol_pm10", "");
        List<String> arrayList14 = gson.fromJson(json14, type);

        for(int counter = 0;counter<1;counter++){

            String value = arrayList14.get(counter);
            if(value.contains(",")){
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if(val.contains(",")){
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            pm10Limassol.add(final_float_total);

        }

        System.out.println("Length is: "+pm10Limassol.size());

        float value1 = pm10Limassol.get(0);
        float value2 = pm10Vienna.get(0);
        float value3 = pm10Zagreb.get(0);

        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);

        chart = (BarChart)findViewById(R.id.barChart);
        chart.setDescription(null);
        chart.setPinchZoom(true);
        chart.setScaleEnabled(true);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        int groupCount = 3;

        ArrayList xVals = new ArrayList();

        //xVals.add("Jan");

        ArrayList yVals1 = new ArrayList();
        ArrayList yVals2 = new ArrayList();
        ArrayList yVals3 = new ArrayList();

        yVals1.add(new BarEntry(1, value1));
        yVals2.add(new BarEntry(2, value2));
        yVals3.add(new BarEntry(3, value3));

        BarDataSet set1, set2, set3;
        set1 = new BarDataSet(yVals1, "Λεμεσός");
        set1.setColor(Color.RED);
        set1.setValueTextSize(12f);
        set2 = new BarDataSet(yVals2, "Βιέννη");
        set2.setColor(Color.BLUE);
        set2.setValueTextSize(12f);
        set3 = new BarDataSet(yVals3, "Ζάγκρεμπ");
        set3.setColor(Color.GREEN);
        set3.setValueTextSize(12f);
        BarData data = new BarData(set1, set2, set3);
        //data.setValueFormatter(new LargeValueFormatter());
        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);
        chart.getData().setHighlightEnabled(false);
        chart.invalidate();

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(20f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(11f);


        //X-axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(6);
        xAxis.setTextSize(11f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
//Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        //leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setTextSize(11f);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);




        one = (Button)findViewById(R.id.pm25);
        two = (Button)findViewById(R.id.pm10);
        three = (Button)findViewById(R.id.no2);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PM10.this, Comparison.class));
            }
        });


        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PM10.this, NO2.class));
            }
        });



    }
}
