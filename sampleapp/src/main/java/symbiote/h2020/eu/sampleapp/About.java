package symbiote.h2020.eu.sampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class About extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        txt = (TextView) findViewById(R.id.txt);
        String nodata = "<br/>&#8226;Συντελεστές:<br/>" +
                "&#x25e6;Δρ. Ελένη Κύζα<br/>" +
                "&#x25e6;Δρ. Λάμπρος Λαμπρινός<br/>" +
                "&#x25e6;Παναγιώτης Ταλιάνος<br/>" +
                "&#x25e6;Άντρια Αγησιλάου<br/>" +
                "&#x25e6;Μάρκος Σουροπέτσης<br/>" +
                "&#x25e6;Παναγιώτης Ταλιάνος<br/>" +
                "&#x25e6;Λουκάς Κων/νου";

        txt.setText(Html.fromHtml(nodata));



    }
}
