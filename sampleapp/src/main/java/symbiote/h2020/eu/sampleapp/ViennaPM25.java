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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViennaPM25 extends AppCompatActivity {

    ArrayList<Float> pm25Vienna = new ArrayList<Float>();

    TextView txt, txt1;

    SharedPreferences sharedPrefs;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vienna_pm25);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .6), (int) (height * .4));


        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ViennaPM25.this);
        Type type = new TypeToken<List<String>>() {
        }.getType();


        //PM25 - Vienna
        String json = sharedPrefs.getString("array_vienna_pm25", "");
        List<String> arrayList = gson.fromJson(json, type);
        System.out.println("Length is: " + arrayList.size());

        for (int counter = 0; counter < arrayList.size(); counter++) {

            String value = arrayList.get(counter);
            if (value.contains(",")) {
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if (val.contains(",")) {
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            pm25Vienna.add(final_float_total);

        }


        txt = (TextView) findViewById(R.id.calendar);
        txt1 = (TextView) findViewById(R.id.azoto);

        float val2 = pm25Vienna.get(0);
        if (val2 <= 25) {
            txt1.setBackgroundColor(Color.GREEN);
        } else if (val2 > 25 && val2 <= 50) {
            txt1.setBackgroundColor(Color.YELLOW);
        } else if (val2 > 50 && val2 <= 100) {
            txt1.setBackgroundColor(Color.RED);
        } else if (val2 > 100) {
            txt1.setBackgroundColor(Color.MAGENTA);
        }
        String s2 = String.format("%.2f", val2);
        txt1.setText(s2 + " μg/m3");



        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String formatted = format1.format(calendar.getTime());
        txt.setText(formatted);

    }
}
