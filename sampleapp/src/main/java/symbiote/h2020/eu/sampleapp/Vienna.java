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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Vienna extends AppCompatActivity {

    TextView txt, txt1, txt2, txt3, txt4, txt5;

    ArrayList <Float> nitrogenVienna = new ArrayList<Float>();//for putting the data
    ArrayList <Float> nitrogenZagreb = new ArrayList<Float>();
    ArrayList <Float> nitrogenLimassol = new ArrayList<Float>();
    ArrayList <Float> sulphurVienna = new ArrayList<Float>();
    ArrayList <Float> sulphurZagreb = new ArrayList<Float>();
    ArrayList <Float> sulphurLimassol = new ArrayList<Float>();
    ArrayList <Float> ozoneVienna = new ArrayList<Float>();
    ArrayList <Float> ozoneZagreb = new ArrayList<Float>();
    ArrayList <Float> ozoneLimassol = new ArrayList<Float>();
    ArrayList <Float> pm10Vienna = new ArrayList<Float>();
    ArrayList <Float> pm10Zagreb = new ArrayList<Float>();
    ArrayList <Float> pm10Limassol = new ArrayList<Float>();
    ArrayList <Float> pm25Vienna = new ArrayList<Float>();
    ArrayList <Float> pm25Zagreb = new ArrayList<Float>();
    ArrayList <Float> pm25Limassol = new ArrayList<Float>();

    SharedPreferences sharedPrefs;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vienna);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));


        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(Vienna.this);
        Type type = new TypeToken<List<String>>() {
        }.getType();


        //Nitrogen - Vienna
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

        }


        //Nitrogen - Zagreb
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

        }


        //Nitrogen - Limassol
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

        }


        //Sulphur - Vienna
        String json3 = sharedPrefs.getString("array_vienna_sulphur", "");
        List<String> arrayList3 = gson.fromJson(json3, type);

        for(int counter = 0;counter<arrayList3.size();counter++){

            String value = arrayList3.get(counter);
            if(value.contains(",")){
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if(val.contains(",")){
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            sulphurVienna.add(final_float_total);

        }

        System.out.println("Length is: "+sulphurVienna.size());




        //Sulphur - Zagreb
        String json4 = sharedPrefs.getString("array_zagreb_sulphur", "");
        List<String> arrayList4 = gson.fromJson(json4, type);

        for(int counter = 0;counter<arrayList4.size();counter++){

            String value = arrayList4.get(counter);
            if(value.contains(",")){
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if(val.contains(",")){
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            sulphurZagreb.add(final_float_total);

        }

        System.out.println("Length is: "+sulphurZagreb.size());




        //Sulphur - Limassol
        String json5 = sharedPrefs.getString("array_limassol_sulphur", "");
        List<String> arrayList5 = gson.fromJson(json5, type);

        for(int counter = 0;counter<arrayList5.size();counter++){

            String value = arrayList5.get(counter);
            if(value.contains(",")){
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if(val.contains(",")){
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            sulphurLimassol.add(final_float_total);

        }

        System.out.println("Length is: "+sulphurLimassol.size());




        //Ozone - Vienna
        String json6 = sharedPrefs.getString("array_vienna_ozone", "");
        List<String> arrayList6 = gson.fromJson(json6, type);

        for(int counter = 0;counter<arrayList6.size();counter++){

            String value = arrayList6.get(counter);
            if(value.contains(",")){
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if(val.contains(",")){
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            ozoneVienna.add(final_float_total);

        }

        System.out.println("Length is: "+ozoneVienna.size());




        //Ozone - Zagreb
        String json7 = sharedPrefs.getString("array_zagreb_ozone", "");
        List<String> arrayList7 = gson.fromJson(json7, type);

        for(int counter = 0;counter<arrayList7.size();counter++){

            String value = arrayList7.get(counter);
            if(value.contains(",")){
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if(val.contains(",")){
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            ozoneZagreb.add(final_float_total);

        }

        System.out.println("Length is: "+ozoneZagreb.size());




        //Ozone - Limassol
        String json8 = sharedPrefs.getString("array_limassol_ozone", "");
        List<String> arrayList8 = gson.fromJson(json8, type);

        for(int counter = 0;counter<arrayList8.size();counter++){

            String value = arrayList8.get(counter);
            if(value.contains(",")){
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if(val.contains(",")){
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            ozoneLimassol.add(final_float_total);

        }

        System.out.println("Length is: "+ozoneLimassol.size());



        //PM10 - Vienna
        String json9 = sharedPrefs.getString("array_vienna_pm10", "");
        List<String> arrayList9 = gson.fromJson(json9, type);

        for(int counter = 0;counter<arrayList9.size();counter++){

            String value = arrayList9.get(counter);
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




        //PM10 - Zagreb
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

        System.out.println("Length is: "+pm10Zagreb.size());




        //PM10 - Limassol
        String json11 = sharedPrefs.getString("array_limassol_pm10", "");
        List<String> arrayList11 = gson.fromJson(json11, type);

        for(int counter = 0;counter<arrayList11.size();counter++){

            String value = arrayList11.get(counter);
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



        //PM25 - Vienna
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

        System.out.println("Length is: "+pm25Vienna.size());




        //PM25 - Zagreb
        String json13 = sharedPrefs.getString("array_zagreb_pm25", "");
        List<String> arrayList13 = gson.fromJson(json13, type);

        for(int counter = 0;counter<arrayList13.size();counter++){

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
            pm25Zagreb.add(final_float_total);

        }

        System.out.println("Length is: "+pm25Zagreb.size());




        //PM25 - Limassol
        String json14 = sharedPrefs.getString("array_limassol_pm25", "");
        List<String> arrayList14 = gson.fromJson(json14, type);

        for(int counter = 0;counter<arrayList14.size();counter++){

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
            pm25Limassol.add(final_float_total);

        }

        System.out.println("Length is: "+pm25Limassol.size());



        txt = (TextView) findViewById(R.id.calendar);
        txt1 = (TextView) findViewById(R.id.pm1);
        txt2 = (TextView) findViewById(R.id.pm2);
        txt3 = (TextView) findViewById(R.id.azoto);
        txt4 = (TextView) findViewById(R.id.ozon);
        txt5 = (TextView) findViewById(R.id.theio);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String formatted = format1.format(calendar.getTime());
        txt.setText(formatted);

        float var = pm10Vienna.get(0);
        if(var<=50){
            txt1.setBackgroundColor(Color.GREEN);
        }else if(var>50&&var<=100){
            txt1.setBackgroundColor(Color.YELLOW);
        }else if(var>100&&var<=200){
            txt1.setBackgroundColor(Color.RED);
        }else if(var>200){
            txt1.setBackgroundColor(Color.MAGENTA);
        }
        String s = String.format("%.2f", var);
        txt1.setText(s+" μg/m3");

        float var2 = pm25Vienna.get(0);
        if(var2<=25){
            txt2.setBackgroundColor(Color.GREEN);
        }else if(var2>25&&var2<=50){
            txt2.setBackgroundColor(Color.YELLOW);
        }else if(var2>50&&var2<=100){
            txt2.setBackgroundColor(Color.RED);
        }else if(var2>100){
            txt2.setBackgroundColor(Color.MAGENTA);
        }
        String s2 = String.format("%.2f", var2);
        txt2.setText(s2+" μg/m3");

        float var3 = nitrogenVienna.get(0);
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
        txt3.setText(s3+" μg/m3");

        float var4 = ozoneVienna.get(0);
        if(var4<=100){
            txt4.setBackgroundColor(Color.GREEN);
        }else if(var4>100&&var4<=140){
            txt4.setBackgroundColor(Color.YELLOW);
        }else if(var4>140&&var4<=180){
            txt4.setBackgroundColor(Color.RED);
        }else if(var4>180){
            txt4.setBackgroundColor(Color.MAGENTA);
        }
        String s4 = String.format("%.2f", var4);
        txt4.setText(s4+" μg/m3");

        float var5 = sulphurVienna.get(0);
        if(var5<=150){
            txt5.setBackgroundColor(Color.GREEN);
        }else if(var5>150&&var5<=250){
            txt5.setBackgroundColor(Color.YELLOW);
        }else if(var5>250&&var5<=350){
            txt5.setBackgroundColor(Color.RED);
        }else if(var5>350){
            txt5.setBackgroundColor(Color.MAGENTA);
        }
        String s5 = String.format("%.2f", var5);
        txt5.setText(s5+" μg/m3");


    }
}
