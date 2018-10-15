package symbiote.h2020.eu.sampleapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Limassol extends AppCompatActivity {

    ArrayList<Float> limassolData = new ArrayList<Float>();
    ArrayList<Float> limassolData2 = new ArrayList<Float>();//pm10
    ArrayList<Float> limassolData3 = new ArrayList<Float>();
    TextView txt, txt1, txt2, txt3, txt4, txt5;

    SharedPreferences sharedPrefs;
    Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limassol);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));


        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(Limassol.this);
        Type type = new TypeToken<List<String>>() {
        }.getType();


        /*//Nitrogen - Vienna
        String json = sharedPrefs.getString("array_vienna_nitrogen", "");
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
            nitrogenVienna.add(final_float_total);

        }*/


        /*//Nitrogen - Zagreb
        String json1 = sharedPrefs.getString("array_zagreb_nitrogen", "");
        List<String> arrayList1 = gson.fromJson(json1, type);
        System.out.println("Length is: "+arrayList1.size());

        for(int counter = 0;counter<arrayList1.size();counter++){

            String value = arrayList1.get(counter);
            if(value.contains(",")){
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if(val.contains(",")){
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            nitrogenZagreb.add(final_float_total);

        }*/


        /*//Nitrogen - Limassol
        String json2 = sharedPrefs.getString("array_limassol_nitrogen", "");
        List<String> arrayList2 = gson.fromJson(json2, type);
        System.out.println("Length is: "+arrayList2.size());

        for(int counter = 0;counter<arrayList2.size();counter++){

            String value = arrayList2.get(counter);
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

        }*/





        /*//PM10 - Zagreb
        String json10 = sharedPrefs.getString("array_zagreb_pm10", "");
        List<String> arrayList10 = gson.fromJson(json10, type);

        for(int counter = 0;counter<arrayList10.size();counter++){

            String value = arrayList10.get(counter);
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

        System.out.println("Length is: "+pm10Zagreb.size());*/



        /*//PM25 - Vienna
        String json12 = sharedPrefs.getString("array_vienna_pm25", "");
        List<String> arrayList12 = gson.fromJson(json12, type);

        for(int counter = 0;counter<arrayList12.size();counter++){

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
            pm25Vienna.add(final_float_total);

        }

        System.out.println("Length is: "+pm25Vienna.size());*/


        //Limassol
        String json13 = sharedPrefs.getString("array_limassol_nitrogen", "");
        List<String> arrayList13 = gson.fromJson(json13, type);

        for (int counter = 0; counter < arrayList13.size(); counter++) {

            String value = arrayList13.get(counter);
            if (value.contains(",")) {
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if (val.contains(",")) {
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            limassolData.add(final_float_total);

        }


        //Limassol
        String json14 = sharedPrefs.getString("array_limassol_pm10", "");
        List<String> arrayList14 = gson.fromJson(json14, type);

        for (int counter = 0; counter < arrayList14.size(); counter++) {

            String value = arrayList14.get(counter);
            if (value.contains(",")) {
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if (val.contains(",")) {
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            limassolData2.add(final_float_total);

        }


        //Limassol
        String json15 = sharedPrefs.getString("array_limassol_pm25", "");
        List<String> arrayList15 = gson.fromJson(json15, type);

        for (int counter = 0; counter < arrayList15.size(); counter++) {

            String value = arrayList15.get(counter);
            if (value.contains(",")) {
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if (val.contains(",")) {
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            limassolData3.add(final_float_total);

        }




        txt = (TextView) findViewById(R.id.calendar);
        txt1 = (TextView) findViewById(R.id.pm1);
        txt2 = (TextView) findViewById(R.id.pm2);
        txt3 = (TextView) findViewById(R.id.azoto);
        /*txt4 = (TextView) findViewById(R.id.ozon);
        txt5 = (TextView) findViewById(R.id.theio);*/

        float val1 = limassolData.get(0);
        if (val1 <= 50) {
            txt1.setBackgroundColor(Color.GREEN);
        } else if (val1 > 50 && val1 <= 100) {
            txt1.setBackgroundColor(Color.YELLOW);
        } else if (val1 > 100 && val1 <= 200) {
            txt1.setBackgroundColor(Color.RED);
        } else if (val1 > 200) {
            txt1.setBackgroundColor(Color.MAGENTA);
        }
        String s1 = String.format("%.2f", val1);
        txt1.setText(s1 + " μg/m3");


        float val2 = limassolData2.get(0);
        if (val2 <= 25) {
            txt2.setBackgroundColor(Color.GREEN);
        } else if (val2 > 25 && val2 <= 50) {
            txt2.setBackgroundColor(Color.YELLOW);
        } else if (val2 > 50 && val2 <= 100) {
            txt2.setBackgroundColor(Color.RED);
        } else if (val2 > 100) {
            txt2.setBackgroundColor(Color.MAGENTA);
        }
        String s2 = String.format("%.2f", val2);
        txt2.setText(s2 + " μg/m3");

        float val3 = limassolData3.get(0);
        if (val3 <= 100) {
            txt3.setBackgroundColor(Color.GREEN);
        } else if (val3 > 100 && val3 <= 150) {
            txt2.setBackgroundColor(Color.YELLOW);
        } else if (val3 > 150 && val3 <= 200) {
            txt2.setBackgroundColor(Color.RED);
        } else if (val3 > 200) {
            txt3.setBackgroundColor(Color.MAGENTA);
        }
        String s3 = String.format("%.2f", val3);
        txt3.setText(s3 + " μg/m3");


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String formatted = format1.format(calendar.getTime());
        txt.setText(formatted);



        /*float var3 = nitrogenLimassol.get(0);
        if(var3<=100){
            txt3.setBackgroundColor(Color.GREEN);
        }else if(var3>100&&var3<=150){
            txt3.setBackgroundColor(Color.YELLOW);
        }else if(var3>150&&var3<=200){
            txt3.setBackgroundColor(Color.RED);
        }else if(var3>200){
            txt3.setBackgroundColor(Color.MAGENTA);
        }
        String s3 = String.format("%.2f", var3);
        txt3.setText(s3+" μg/m3");*/


    }
}
