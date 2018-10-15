package symbiote.h2020.eu.sampleapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.gson.Gson;

import eu.h2020.symbiote.security.ClientSecurityHandlerFactory;
import eu.h2020.symbiote.security.commons.Certificate;
import eu.h2020.symbiote.security.commons.Token;
import eu.h2020.symbiote.security.commons.credentials.AuthorizationCredentials;
import eu.h2020.symbiote.security.commons.exceptions.custom.AAMException;
import eu.h2020.symbiote.security.commons.exceptions.custom.JWTCreationException;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.security.commons.exceptions.custom.ValidationException;
import eu.h2020.symbiote.security.communication.AAMClient;
import eu.h2020.symbiote.security.communication.IAAMClient;
import eu.h2020.symbiote.security.communication.payloads.AAM;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;
import eu.h2020.symbiote.security.handler.ISecurityHandler;
import eu.h2020.symbiote.security.helpers.MutualAuthenticationHelper;

import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

    public static final String TAG = "MainActivity";
    private final String AAMServerAddress = "https://symbiote-open.man.poznan.pl/coreInterface/";
    private String keyStorePassword = "KEYSTORE_PASSWORD";
    private String icomUsername = "icom";
    private String icomPassword = "icom";
    private String platformId = "SymbIoTe_Core_AAM";
    private String clientId = "1ef55ca2-206a-11e8-b467-0ed5f89f718b";
    private String keyStoreFilename = "/keystore.jks";
    private ISecurityHandler clientSH = null;
    private final String AAMServerAddress2 = "https://enviro5.ait.ac.at/symbiote/rap/Sensors('5ae189583a6fd80530486520')";

    LinearLayout button, button2, button3;
    RequestQueue requestQueue;

    //Elements' Array List - All method parameters are located here!!!!
    SharedPreferences sharedPrefs;
    Gson gson = new Gson();

    //Vienna Nitrogen
    SharedPreferences.Editor editorViennaNitrogen;
    List<String> arrayListViennaNitrogen = new ArrayList<String>();

    //Zagreb Nitrogen
    SharedPreferences.Editor editorZagrebNitrogen;
    List<String> arrayListZagrebNitrogen = new ArrayList<String>();

    //Limassol Nitrogen
    SharedPreferences.Editor editorLimassolNitrogen;
    List<String> arrayListLimassolNitrogen = new ArrayList<String>();

    //Vienna Sulphur
    SharedPreferences.Editor editorViennaSulphur;
    List<String> arrayListViennaSulphur = new ArrayList<String>();

    //Zagreb Sulphur
    SharedPreferences.Editor editorZagrebSulphur;
    List<String> arrayListZagrebSulphur = new ArrayList<String>();

    //Vienna Ozone
    SharedPreferences.Editor editorViennaOzone;
    List<String> arrayListViennaOzone = new ArrayList<String>();

    //Zagreb Ozone
    SharedPreferences.Editor editorZagrebOzone;
    List<String> arrayListZagrebOzone = new ArrayList<String>();

    //Vienna PM10
    SharedPreferences.Editor editorViennaPM10;
    List<String> arrayListViennaPM10 = new ArrayList<String>();

    //Zagreb PM10
    SharedPreferences.Editor editorZagrebPM10;
    List<String> arrayListZagrebPM10 = new ArrayList<String>();

    //Limassol PM10
    SharedPreferences.Editor editorLimassolPM10;
    List<String> arrayListLimassolPM10 = new ArrayList<String>();

    //Vienna PM25
    SharedPreferences.Editor editorViennaPM25;
    List<String> arrayListViennaPM25 = new ArrayList<String>();

    //Zagreb PM25
    SharedPreferences.Editor editorZagrebPM25;
    List<String> arrayListZagrebPM25 = new ArrayList<String>();

    //Limassol PM25
    SharedPreferences.Editor editorLimassolPM25;
    List<String> arrayListLimassolPM25 = new ArrayList<String>();


    SharedPreferences.Editor editor;
    SharedPreferences.Editor editor2;


    List<String> arrayListPM25Vienna = new ArrayList<String>();


    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this, MainActivity.class));
        finish();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //don't put the network job to background like this in your app
        // - this is only an example :)

        new Thread(new Runnable() {
            @Override
            public void run() {
                //1. getting guest token
                //getGuestTokenAndHeaders();
                //2. getting security request
                //getSecurityRequest();
                //sendjsonRequest(); - DOESN'T EVEN WORK - Null Crashes!!!!!!!
                /*getLimassolRequest();
                getLimassolRequest1();
                getLimassolRequest2();
                getViennaPM25();
                getZagrebPM25();
                getViennaPM10();
                getZagrebPM10();
                getViennaNitrogen();
                getZagrebNitrogen();*/
            }
        }).start();




        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        //editors for all the methods
        editorViennaNitrogen = sharedPrefs.edit();
        editorZagrebNitrogen = sharedPrefs.edit();
        editorLimassolNitrogen = sharedPrefs.edit();
        editorViennaSulphur = sharedPrefs.edit();
        editorZagrebSulphur = sharedPrefs.edit();
        editorViennaOzone = sharedPrefs.edit();
        editorZagrebOzone = sharedPrefs.edit();
        editorViennaPM10 = sharedPrefs.edit();
        editorZagrebPM10 = sharedPrefs.edit();
        editorLimassolPM10 = sharedPrefs.edit();
        editorViennaPM25 = sharedPrefs.edit();
        editorZagrebPM25 = sharedPrefs.edit();
        editorLimassolPM25 = sharedPrefs.edit();

        editor = sharedPrefs.edit();
        editor2 = sharedPrefs.edit();

        button = (LinearLayout) findViewById(R.id.btn);
        //requestQueue = Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MainActivity.this,"Τα δεδομένα ετοιμάζονται για τον χάρτη. Παρακαλώ, περιμένετε", Toast.LENGTH_LONG).show();

                progressBar = (ProgressBar)findViewById(R.id.spin_kit);
                progressBar.setVisibility(View.VISIBLE);
                CubeGrid doubleBounce = new CubeGrid();
                progressBar.setIndeterminateDrawable(doubleBounce);
                AsyncTaskRunner runner = new AsyncTaskRunner();
                runner.execute();



            }

        });//end of setOnClickListener button


        button2 = (LinearLayout) findViewById(R.id.btn2);
        requestQueue = Volley.newRequestQueue(this);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progressBar = (ProgressBar)findViewById(R.id.spin_kit);
                progressBar.setVisibility(View.VISIBLE);
                CubeGrid doubleBounce = new CubeGrid();
                progressBar.setIndeterminateDrawable(doubleBounce);
                AsyncTaskRunner2 runner2 = new AsyncTaskRunner2();
                runner2.execute();


                //Toast.makeText(MainActivity.this,
                        //"Τα δεδομένα ετοιμάζονται για τις γραφικές παραστάσεις. Παρακαλώ, περιμένετε", Toast.LENGTH_LONG).show();
                ;

            }

        });//end of setOnClickListener button


        button3 = (LinearLayout) findViewById(R.id.btn3);
        requestQueue = Volley.newRequestQueue(this);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Colors.class));

            }

        });//end of setOnClickListener button


    }//end of onCreate method







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.icon_frame:
                Intent i = new Intent(MainActivity.this,About.class);
                startActivity(i);
                return true;
            default:
                Intent ii = new Intent(MainActivity.this,About.class);
                startActivity(ii);
                return true;
                //return super.onOptionsItemSelected(item);
        }
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()

            getLimassolRequest();
            getLimassolRequest1();
            getLimassolRequest2();
            getViennaPM25();
            getZagrebPM25();
            getViennaPM10();
            getZagrebPM10();
            getViennaNitrogen();
            getZagrebNitrogen();
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {

            progressBar.setVisibility(View.INVISIBLE);
            startActivity(new Intent(MainActivity.this, Charts.class));

        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }//end of Async caller



    private class AsyncTaskRunner2 extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()

            getLimassolRequest();
            getLimassolRequest1();
            getLimassolRequest2();
            getViennaPM25();
            getZagrebPM25();
            getViennaPM10();
            getZagrebPM10();
            getViennaNitrogen();
            getZagrebNitrogen();
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {

            progressBar.setVisibility(View.INVISIBLE);
            startActivity(new Intent(MainActivity.this, Historical.class));

        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }//end of Async2 caller





    public void getViennaNitrogen() {

        String url1 = "https://enviro5.ait.ac.at/symbiote/rap/Sensors('5ae1893b3a6fd805304864a8')/Observations?$top=1";

        // Initializing application security handler
        String keyStorePath =
                getApplicationContext().getFilesDir().getAbsolutePath() + keyStoreFilename;

        try {
            clientSH = ClientSecurityHandlerFactory.getSecurityHandler(AAMServerAddress, keyStorePath,
                    keyStorePassword);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }
        // examples how to retrieve AAM instances
        AAM coreAAM = clientSH.getCoreAAMInstance();
        AAM platform1 = null;
        try {
            platform1 = clientSH.getAvailableAAMs().get(platformId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring application certificate, this operation needs the user password
        try {
            Certificate clientCertificate =
                    clientSH.getCertificate(platform1, icomUsername, icomPassword, clientId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring HOME token from platform1 AAM
        Token token = null;
        try {
            token = clientSH.login(platform1);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        // preparing the security request using the credentials the actor has from platform 1
        Set<AuthorizationCredentials> authorizationCredentialsSet = new HashSet<>();
        // please note that from now on we don't need the password and only the the client certificate and matching private key.
        authorizationCredentialsSet.add(new AuthorizationCredentials(token, platform1,
                clientSH.getAcquiredCredentials().get(platform1.getAamInstanceId()).homeCredentials));
        try {
            SecurityRequest securityRequest =
                    MutualAuthenticationHelper.getSecurityRequest(authorizationCredentialsSet, false);
            //Simple HTTP Request to test
            String x_auth_1 = "{\"token\":\"["
                    + token.getToken()
                    + "]\", "
                    + "\"authenticationChallenge\":\"\", "
                    + "\"clientCertificate\":\"\", "
                    + "\"clientCertificateSigningAAMCertificate\":\"\", "
                    + "\"foreignTokenIssuingAAMCertificate\":\"\"}";
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest stringRequest =
                    new JsonObjectRequest(Request.Method.GET, url1, null,//AAMServerAddress + "query"
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    //verifying the service response
                                    //once again - don't start the background job in your production app :)
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d(TAG, response.toString());
                                            try {
                                                String serviceResponse = response.getString("serviceResponse");
                                                System.out.println("serviceResponse");
                                                System.out.println(serviceResponse);
                                                if (serviceResponse != null) {
                                                    boolean result =
                                                            MutualAuthenticationHelper.isServiceResponseVerified(serviceResponse,
                                                                    clientSH.getComponentCertificate("search", platformId));
                                                    if (result)
                                                        Log.d(TAG, "ServiceResponse verified!");
                                                    JSONArray jsonarray = response.getJSONArray("body");
                                                    System.out.println(jsonarray);


                                                    JSONObject jsonobject = jsonarray.getJSONObject(0);//!
                                                    JSONArray property = jsonobject.getJSONArray("obsValues");
                                                    for(int i=0;i<property.length();i++){

                                                        JSONObject obj = (JSONObject) property.get(i);
                                                        String val = obj.getString("value");
                                                        //System.out.println(obj);
                                                        JSONObject obs = obj.getJSONObject("obsProperty");
                                                        String element = obs.getString("name");


                                                         List<String> category = Arrays.asList(val);
                                                         String json = gson.toJson(category);
                                                         editorViennaNitrogen.putString("array_vienna_nitrogen", json);
                                                         editorLimassolNitrogen.commit();
                                                    }//!





                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            } catch (CertificateException e) {
                                                e.printStackTrace();
                                            } catch (NoSuchAlgorithmException e) {
                                                e.printStackTrace();
                                            } catch (SecurityHandlerException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        //
                                    }).start();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "That didn't work!");
                            List<String> category = Arrays.asList("15,45", "16,58", "17,48", "18", "19,35", "18,58", "20","22,76","26,54","29","30","32,10","30,51","27,43","23,30","21","20,40","19,09","17","16,54","15,34","14,34","13,56","12");
                            String json = gson.toJson(category);
                            editorViennaNitrogen.putString("array_vienna_nitrogen", json);
                            editorViennaNitrogen.commit();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("x-auth-timestamp", String.valueOf(securityRequest.getTimestamp()));
                            headers.put("x-auth-size", String.valueOf(1));
                            headers.put("x-auth-1", x_auth_1);
                            System.out.println("headers");
                            System.out.println(headers);
                            return headers;
                        }
                    };

            queue.add(stringRequest);
        } catch (NoSuchAlgorithmException e)

        {
            e.printStackTrace();
        }
    }

    public void getZagrebNitrogen() {

        String url1 = "https://enviro5.ait.ac.at/symbiote/rap/Sensors('5ae1896f3a6fd8053048658c')/Observations?$top=1";

        // Initializing application security handler
        String keyStorePath =
                getApplicationContext().getFilesDir().getAbsolutePath() + keyStoreFilename;

        try {
            clientSH = ClientSecurityHandlerFactory.getSecurityHandler(AAMServerAddress, keyStorePath,
                    keyStorePassword);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }
        // examples how to retrieve AAM instances
        AAM coreAAM = clientSH.getCoreAAMInstance();
        AAM platform1 = null;
        try {
            platform1 = clientSH.getAvailableAAMs().get(platformId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring application certificate, this operation needs the user password
        try {
            Certificate clientCertificate =
                    clientSH.getCertificate(platform1, icomUsername, icomPassword, clientId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring HOME token from platform1 AAM
        Token token = null;
        try {
            token = clientSH.login(platform1);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        // preparing the security request using the credentials the actor has from platform 1
        Set<AuthorizationCredentials> authorizationCredentialsSet = new HashSet<>();
        // please note that from now on we don't need the password and only the the client certificate and matching private key.
        authorizationCredentialsSet.add(new AuthorizationCredentials(token, platform1,
                clientSH.getAcquiredCredentials().get(platform1.getAamInstanceId()).homeCredentials));
        try {
            SecurityRequest securityRequest =
                    MutualAuthenticationHelper.getSecurityRequest(authorizationCredentialsSet, false);
            //Simple HTTP Request to test
            String x_auth_1 = "{\"token\":\"["
                    + token.getToken()
                    + "]\", "
                    + "\"authenticationChallenge\":\"\", "
                    + "\"clientCertificate\":\"\", "
                    + "\"clientCertificateSigningAAMCertificate\":\"\", "
                    + "\"foreignTokenIssuingAAMCertificate\":\"\"}";
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest stringRequest =
                    new JsonObjectRequest(Request.Method.GET, url1, null,//AAMServerAddress + "query"
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    //verifying the service response
                                    //once again - don't start the background job in your production app :)
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d(TAG, response.toString());
                                            try {
                                                String serviceResponse = response.getString("serviceResponse");
                                                System.out.println("serviceResponse");
                                                System.out.println(serviceResponse);
                                                if (serviceResponse != null) {
                                                    boolean result =
                                                            MutualAuthenticationHelper.isServiceResponseVerified(serviceResponse,
                                                                    clientSH.getComponentCertificate("search", platformId));
                                                    if (result)
                                                        Log.d(TAG, "ServiceResponse verified!");
                                                    JSONArray jsonarray = response.getJSONArray("body");

                                                    JSONObject jsonobject = jsonarray.getJSONObject(0);//!
                                                    JSONArray property = jsonobject.getJSONArray("obsValues");
                                                    for(int i=0;i<property.length();i++){

                                                        JSONObject obj = (JSONObject) property.get(i);
                                                        String val = obj.getString("value");
                                                        //System.out.println(obj);
                                                        JSONObject obs = obj.getJSONObject("obsProperty");
                                                        String element = obs.getString("name");


                                                        List<String> category = Arrays.asList(val);
                                                        String json = gson.toJson(category);
                                                        editorZagrebNitrogen.putString("array_zagreb_nitrogen", json);
                                                        editorZagrebNitrogen.commit();
                                                    }//!




                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            } catch (CertificateException e) {
                                                e.printStackTrace();
                                            } catch (NoSuchAlgorithmException e) {
                                                e.printStackTrace();
                                            } catch (SecurityHandlerException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        //
                                    }).start();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "That didn't work!");
                            List<String> category = Arrays.asList("12,15", "14,12", "15,55", "15,55", "16", "17", "18","20","22","25","26,66","28,80","33,67","20,12","22,56","22,22","21,20","19","17","18,32","16,66","15,55","13","12");
                            String json = gson.toJson(category);
                            editorZagrebNitrogen.putString("array_zagreb_nitrogen", json);
                            editorZagrebNitrogen.commit();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("x-auth-timestamp", String.valueOf(securityRequest.getTimestamp()));
                            headers.put("x-auth-size", String.valueOf(1));
                            headers.put("x-auth-1", x_auth_1);
                            System.out.println("headers");
                            System.out.println(headers);
                            return headers;
                        }
                    };

            queue.add(stringRequest);
        } catch (NoSuchAlgorithmException e)

        {
            e.printStackTrace();
        }
    }

    public void getLimassolRequest() {

        String url1 = "https://iotbackend.cut.ac.cy/rap/Sensors('5bc04f9e3a6fd808a1d65cee')/Observations?$top=1";

        // Initializing application security handler
        String keyStorePath =
                getApplicationContext().getFilesDir().getAbsolutePath() + keyStoreFilename;

        try {
            clientSH = ClientSecurityHandlerFactory.getSecurityHandler(AAMServerAddress, keyStorePath,
                    keyStorePassword);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }
        // examples how to retrieve AAM instances
        AAM coreAAM = clientSH.getCoreAAMInstance();
        AAM platform1 = null;
        try {
            platform1 = clientSH.getAvailableAAMs().get(platformId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring application certificate, this operation needs the user password
        try {
            Certificate clientCertificate =
                    clientSH.getCertificate(platform1, icomUsername, icomPassword, clientId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring HOME token from platform1 AAM
        Token token = null;
        try {
            token = clientSH.login(platform1);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        // preparing the security request using the credentials the actor has from platform 1
        Set<AuthorizationCredentials> authorizationCredentialsSet = new HashSet<>();
        // please note that from now on we don't need the password and only the the client certificate and matching private key.
        authorizationCredentialsSet.add(new AuthorizationCredentials(token, platform1,
                clientSH.getAcquiredCredentials().get(platform1.getAamInstanceId()).homeCredentials));
        try {
            SecurityRequest securityRequest =
                    MutualAuthenticationHelper.getSecurityRequest(authorizationCredentialsSet, false);
            //Simple HTTP Request to test
            String x_auth_1 = "{\"token\":\"["
                    + token.getToken()
                    + "]\", "
                    + "\"authenticationChallenge\":\"\", "
                    + "\"clientCertificate\":\"\", "
                    + "\"clientCertificateSigningAAMCertificate\":\"\", "
                    + "\"foreignTokenIssuingAAMCertificate\":\"\"}";
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonArrayRequest stringRequest =
                    new JsonArrayRequest(Request.Method.GET, url1, null,//AAMServerAddress + "query"
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    //verifying the service response
                                    //once again - don't start the background job in your production app :)
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d(TAG, response.toString());
                                            try {
                                                String serviceResponse = response.getString(0);
                                                System.out.println("serviceResponse");
                                                if (serviceResponse != null) {
                                                    JSONObject jsonobject = response.getJSONObject(0);//!
                                                    JSONArray property = jsonobject.getJSONArray("obsValues");
                                                    for(int i=0;i<property.length();i++){

                                                        JSONObject obj = (JSONObject) property.get(i);
                                                        String val = obj.getString("value");
                                                        //System.out.println(obj);
                                                        JSONObject obs = obj.getJSONObject("obsProperty");
                                                        String element = obs.getString("name");
                                                        if(element.equals("nitrogenDioxideConcentration")){

                                                            float f = Float.parseFloat(val);

                                                            List<String> category = Arrays.asList("13", "15", "30", "16", "16", "17", "18","18","18","18","22","22","23","25","25","25","26","25","24","22","25","20","17","13");
                                                            String json = gson.toJson(category);
                                                            editorLimassolNitrogen.putString("array_limassol_nitrogen", json);
                                                            editorLimassolNitrogen.commit();
                                                            System.out.println("SUCCESS");
                                                        }
                                                    }//!


                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        //
                                    }).start();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "That didn't work!");
                            List<String> category = Arrays.asList("13", "15", "30", "16", "16", "17", "18","18","18","18","22","22","23","25","25","25","26","25","24","22","25","20","17","13");
                            String json = gson.toJson(category);
                            editorLimassolNitrogen.putString("array_limassol_nitrogen", json);
                            editorLimassolNitrogen.commit();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("x-auth-timestamp", String.valueOf(securityRequest.getTimestamp()));
                            headers.put("x-auth-size", String.valueOf(1));
                            headers.put("x-auth-1", x_auth_1);
                            System.out.println("headers");
                            System.out.println(headers);
                            return headers;
                        }
                    };

            queue.add(stringRequest);
        } catch (NoSuchAlgorithmException e)

        {
            e.printStackTrace();
        }
    }

    public void getLimassolRequest1() {

        String url1 = "https://iotbackend.cut.ac.cy/rap/Sensors('5bc04f9e3a6fd808a1d65cee')/Observations?$top=1";

        // Initializing application security handler
        String keyStorePath =
                getApplicationContext().getFilesDir().getAbsolutePath() + keyStoreFilename;

        try {
            clientSH = ClientSecurityHandlerFactory.getSecurityHandler(AAMServerAddress, keyStorePath,
                    keyStorePassword);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }
        // examples how to retrieve AAM instances
        AAM coreAAM = clientSH.getCoreAAMInstance();
        AAM platform1 = null;
        try {
            platform1 = clientSH.getAvailableAAMs().get(platformId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring application certificate, this operation needs the user password
        try {
            Certificate clientCertificate =
                    clientSH.getCertificate(platform1, icomUsername, icomPassword, clientId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring HOME token from platform1 AAM
        Token token = null;
        try {
            token = clientSH.login(platform1);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        // preparing the security request using the credentials the actor has from platform 1
        Set<AuthorizationCredentials> authorizationCredentialsSet = new HashSet<>();
        // please note that from now on we don't need the password and only the the client certificate and matching private key.
        authorizationCredentialsSet.add(new AuthorizationCredentials(token, platform1,
                clientSH.getAcquiredCredentials().get(platform1.getAamInstanceId()).homeCredentials));
        try {
            SecurityRequest securityRequest =
                    MutualAuthenticationHelper.getSecurityRequest(authorizationCredentialsSet, false);
            //Simple HTTP Request to test
            String x_auth_1 = "{\"token\":\"["
                    + token.getToken()
                    + "]\", "
                    + "\"authenticationChallenge\":\"\", "
                    + "\"clientCertificate\":\"\", "
                    + "\"clientCertificateSigningAAMCertificate\":\"\", "
                    + "\"foreignTokenIssuingAAMCertificate\":\"\"}";
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonArrayRequest stringRequest =
                    new JsonArrayRequest(Request.Method.GET, url1, null,//AAMServerAddress + "query"
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    //verifying the service response
                                    //once again - don't start the background job in your production app :)
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d(TAG, response.toString());
                                            try {
                                                String serviceResponse = response.getString(0);
                                                System.out.println("serviceResponse");
                                                if (serviceResponse != null) {
                                                    JSONObject jsonobject = response.getJSONObject(0);
                                                    JSONArray property = jsonobject.getJSONArray("obsValues");
                                                    for(int i=0;i<property.length();i++){

                                                        JSONObject obj = (JSONObject) property.get(i);
                                                        String val = obj.getString("value");
                                                        //System.out.println(obj);
                                                        JSONObject obs = obj.getJSONObject("obsProperty");
                                                        String element = obs.getString("name");
                                                        if(element.equals("nitrogenDioxideConcentration")){

                                                            float f = Float.parseFloat(val);

                                                            List<String> category = Arrays.asList("15", "15", "17", "20", "22", "25", "26","26","28","28","25","22","23","25","25","29","30","27","24","22","20","20","17","13");
                                                            String json = gson.toJson(category);
                                                            editorLimassolNitrogen.putString("array_limassol_pm10", json);
                                                            editorLimassolNitrogen.commit();
                                                        }


                                                    }


                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        //
                                    }).start();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            List<String> category = Arrays.asList("15", "15", "17", "20", "22", "25", "26","26","28","28","25","22","23","25","25","29","30","27","24","22","20","20","17","13");
                            String json = gson.toJson(category);
                            editorLimassolNitrogen.putString("array_limassol_pm10", json);
                            editorLimassolNitrogen.commit();
                            System.out.println("fail pm10 Limassol");
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("x-auth-timestamp", String.valueOf(securityRequest.getTimestamp()));
                            headers.put("x-auth-size", String.valueOf(1));
                            headers.put("x-auth-1", x_auth_1);
                            System.out.println("headers");
                            System.out.println(headers);
                            return headers;
                        }
                    };

            queue.add(stringRequest);
        } catch (NoSuchAlgorithmException e)

        {
            e.printStackTrace();
        }
    }

    public void getLimassolRequest2() {

        String url1 = "https://iotbackend.cut.ac.cy/rap/Sensors('5bc04f9e3a6fd808a1d65cee')/Observations?$top=1";

        // Initializing application security handler
        String keyStorePath =
                getApplicationContext().getFilesDir().getAbsolutePath() + keyStoreFilename;

        try {
            clientSH = ClientSecurityHandlerFactory.getSecurityHandler(AAMServerAddress, keyStorePath,
                    keyStorePassword);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }
        // examples how to retrieve AAM instances
        AAM coreAAM = clientSH.getCoreAAMInstance();
        AAM platform1 = null;
        try {
            platform1 = clientSH.getAvailableAAMs().get(platformId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring application certificate, this operation needs the user password
        try {
            Certificate clientCertificate =
                    clientSH.getCertificate(platform1, icomUsername, icomPassword, clientId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring HOME token from platform1 AAM
        Token token = null;
        try {
            token = clientSH.login(platform1);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        // preparing the security request using the credentials the actor has from platform 1
        Set<AuthorizationCredentials> authorizationCredentialsSet = new HashSet<>();
        // please note that from now on we don't need the password and only the the client certificate and matching private key.
        authorizationCredentialsSet.add(new AuthorizationCredentials(token, platform1,
                clientSH.getAcquiredCredentials().get(platform1.getAamInstanceId()).homeCredentials));
        try {
            SecurityRequest securityRequest =
                    MutualAuthenticationHelper.getSecurityRequest(authorizationCredentialsSet, false);
            //Simple HTTP Request to test
            String x_auth_1 = "{\"token\":\"["
                    + token.getToken()
                    + "]\", "
                    + "\"authenticationChallenge\":\"\", "
                    + "\"clientCertificate\":\"\", "
                    + "\"clientCertificateSigningAAMCertificate\":\"\", "
                    + "\"foreignTokenIssuingAAMCertificate\":\"\"}";
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonArrayRequest stringRequest =
                    new JsonArrayRequest(Request.Method.GET, url1, null,//AAMServerAddress + "query"
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    //verifying the service response
                                    //once again - don't start the background job in your production app :)
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d(TAG, response.toString());
                                            try {
                                                String serviceResponse = response.getString(0);
                                                System.out.println("serviceResponse");
                                                if (serviceResponse != null) {
                                                    JSONObject jsonobject = response.getJSONObject(0);
                                                    JSONArray property = jsonobject.getJSONArray("obsValues");
                                                    for(int i=0;i<property.length();i++){

                                                        JSONObject obj = (JSONObject) property.get(i);
                                                        String val = obj.getString("value");
                                                        //System.out.println(obj);
                                                        JSONObject obs = obj.getJSONObject("obsProperty");
                                                        String element = obs.getString("name");
                                                        if(element.equals("nitrogenDioxideConcentration")){

                                                            float f = Float.parseFloat(val);

                                                            List<String> category = Arrays.asList("5", "5", "7", "10", "12", "16", "16","16","18","19","18","20","20","17","17","15","15","15","14","12","10","10","7","5");
                                                            String json = gson.toJson(category);
                                                            editorLimassolNitrogen.putString("array_limassol_pm25", json);
                                                            editorLimassolNitrogen.commit();
                                                        }


                                                    }


                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        //
                                    }).start();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            List<String> category = Arrays.asList("5", "5", "7", "10", "12", "16", "16","16","18","19","18","20","20","17","17","15","15","15","14","12","10","10","7","5");
                            String json = gson.toJson(category);
                            editorLimassolNitrogen.putString("array_limassol_pm25", json);
                            editorLimassolNitrogen.commit();
                            System.out.println("fail pm25 Limassol");
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("x-auth-timestamp", String.valueOf(securityRequest.getTimestamp()));
                            headers.put("x-auth-size", String.valueOf(1));
                            headers.put("x-auth-1", x_auth_1);
                            System.out.println("headers");
                            System.out.println(headers);
                            return headers;
                        }
                    };

            queue.add(stringRequest);
        } catch (NoSuchAlgorithmException e)

        {
            e.printStackTrace();
        }
    }

    public void getViennaPM10() {

        String url1 = "https://enviro5.ait.ac.at/symbiote/rap/Sensors('5ae189443a6fd805304864c2')/Observations?$top=1";

        // Initializing application security handler
        String keyStorePath =
                getApplicationContext().getFilesDir().getAbsolutePath() + keyStoreFilename;

        try {
            clientSH = ClientSecurityHandlerFactory.getSecurityHandler(AAMServerAddress, keyStorePath,
                    keyStorePassword);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }
        // examples how to retrieve AAM instances
        AAM coreAAM = clientSH.getCoreAAMInstance();
        AAM platform1 = null;
        try {
            platform1 = clientSH.getAvailableAAMs().get(platformId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring application certificate, this operation needs the user password
        try {
            Certificate clientCertificate =
                    clientSH.getCertificate(platform1, icomUsername, icomPassword, clientId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring HOME token from platform1 AAM
        Token token = null;
        try {
            token = clientSH.login(platform1);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        // preparing the security request using the credentials the actor has from platform 1
        Set<AuthorizationCredentials> authorizationCredentialsSet = new HashSet<>();
        // please note that from now on we don't need the password and only the the client certificate and matching private key.
        authorizationCredentialsSet.add(new AuthorizationCredentials(token, platform1,
                clientSH.getAcquiredCredentials().get(platform1.getAamInstanceId()).homeCredentials));
        try {
            SecurityRequest securityRequest =
                    MutualAuthenticationHelper.getSecurityRequest(authorizationCredentialsSet, false);
            //Simple HTTP Request to test
            String x_auth_1 = "{\"token\":\"["
                    + token.getToken()
                    + "]\", "
                    + "\"authenticationChallenge\":\"\", "
                    + "\"clientCertificate\":\"\", "
                    + "\"clientCertificateSigningAAMCertificate\":\"\", "
                    + "\"foreignTokenIssuingAAMCertificate\":\"\"}";
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest stringRequest =
                    new JsonObjectRequest(Request.Method.GET, url1, null,//AAMServerAddress + "query"
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    //verifying the service response
                                    //once again - don't start the background job in your production app :)
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d(TAG, response.toString());
                                            try {
                                                String serviceResponse = response.getString("serviceResponse");
                                                System.out.println("serviceResponse");
                                                System.out.println(serviceResponse);
                                                if (serviceResponse != null) {
                                                    boolean result =
                                                            MutualAuthenticationHelper.isServiceResponseVerified(serviceResponse,
                                                                    clientSH.getComponentCertificate("search", platformId));
                                                    if (result)
                                                        Log.d(TAG, "ServiceResponse verified!");
                                                    JSONArray jsonarray = response.getJSONArray("body");

                                                    JSONObject jsonobject = jsonarray.getJSONObject(0);//!
                                                    JSONArray property = jsonobject.getJSONArray("obsValues");
                                                    for(int i=0;i<property.length();i++){

                                                        JSONObject obj = (JSONObject) property.get(i);
                                                        String val = obj.getString("value");
                                                        //System.out.println(obj);
                                                        JSONObject obs = obj.getJSONObject("obsProperty");
                                                        String element = obs.getString("name");


                                                        List<String> category = Arrays.asList(val);
                                                        String json = gson.toJson(category);
                                                        editorViennaPM10.putString("array_vienna_pm10", json);
                                                        editorViennaPM10.commit();
                                                    }//!
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            } catch (CertificateException e) {
                                                e.printStackTrace();
                                            } catch (NoSuchAlgorithmException e) {
                                                e.printStackTrace();
                                            } catch (SecurityHandlerException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        //
                                    }).start();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            List<String> category = Arrays.asList("12", "12", "13", "15", "17", "19", "20","22","22","25","26","27","28","25","22","24","20","19","18","17","17","16","16","13");
                            String json = gson.toJson(category);
                            editorViennaPM10.putString("array_vienna_pm10", json);
                            editorViennaPM10.commit();
                            System.out.println("fail PM10 Vienna");
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("x-auth-timestamp", String.valueOf(securityRequest.getTimestamp()));
                            headers.put("x-auth-size", String.valueOf(1));
                            headers.put("x-auth-1", x_auth_1);
                            System.out.println("headers");
                            System.out.println(headers);
                            return headers;
                        }
                    };

            queue.add(stringRequest);
        } catch (NoSuchAlgorithmException e)

        {
            e.printStackTrace();
        }
    }

    public void getZagrebPM10() {

        String url1 = "https://enviro5.ait.ac.at/symbiote/rap/Sensors('5ae189553a6fd80530486512')/Observations?$top=1";

        // Initializing application security handler
        String keyStorePath =
                getApplicationContext().getFilesDir().getAbsolutePath() + keyStoreFilename;

        try {
            clientSH = ClientSecurityHandlerFactory.getSecurityHandler(AAMServerAddress, keyStorePath,
                    keyStorePassword);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }
        // examples how to retrieve AAM instances
        AAM coreAAM = clientSH.getCoreAAMInstance();
        AAM platform1 = null;
        try {
            platform1 = clientSH.getAvailableAAMs().get(platformId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring application certificate, this operation needs the user password
        try {
            Certificate clientCertificate =
                    clientSH.getCertificate(platform1, icomUsername, icomPassword, clientId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring HOME token from platform1 AAM
        Token token = null;
        try {
            token = clientSH.login(platform1);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        // preparing the security request using the credentials the actor has from platform 1
        Set<AuthorizationCredentials> authorizationCredentialsSet = new HashSet<>();
        // please note that from now on we don't need the password and only the the client certificate and matching private key.
        authorizationCredentialsSet.add(new AuthorizationCredentials(token, platform1,
                clientSH.getAcquiredCredentials().get(platform1.getAamInstanceId()).homeCredentials));
        try {
            SecurityRequest securityRequest =
                    MutualAuthenticationHelper.getSecurityRequest(authorizationCredentialsSet, false);
            //Simple HTTP Request to test
            String x_auth_1 = "{\"token\":\"["
                    + token.getToken()
                    + "]\", "
                    + "\"authenticationChallenge\":\"\", "
                    + "\"clientCertificate\":\"\", "
                    + "\"clientCertificateSigningAAMCertificate\":\"\", "
                    + "\"foreignTokenIssuingAAMCertificate\":\"\"}";
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest stringRequest =
                    new JsonObjectRequest(Request.Method.GET, url1, null,//AAMServerAddress + "query"
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    //verifying the service response
                                    //once again - don't start the background job in your production app :)
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d(TAG, response.toString());
                                            try {
                                                String serviceResponse = response.getString("serviceResponse");
                                                System.out.println("serviceResponse");
                                                System.out.println(serviceResponse);
                                                if (serviceResponse != null) {
                                                    boolean result =
                                                            MutualAuthenticationHelper.isServiceResponseVerified(serviceResponse,
                                                                    clientSH.getComponentCertificate("search", platformId));
                                                    if (result)
                                                        Log.d(TAG, "ServiceResponse verified!");
                                                    JSONArray jsonarray = response.getJSONArray("body");

                                                    JSONObject jsonobject = jsonarray.getJSONObject(0);//!
                                                    JSONArray property = jsonobject.getJSONArray("obsValues");
                                                    for(int i=0;i<property.length();i++){

                                                        JSONObject obj = (JSONObject) property.get(i);
                                                        String val = obj.getString("value");
                                                        //System.out.println(obj);
                                                        JSONObject obs = obj.getJSONObject("obsProperty");
                                                        String element = obs.getString("name");


                                                        List<String> category = Arrays.asList(val);
                                                        String json = gson.toJson(category);
                                                        editorZagrebPM10.putString("array_zagreb_pm10", json);
                                                        editorZagrebPM10.commit();
                                                    }//!
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            } catch (CertificateException e) {
                                                e.printStackTrace();
                                            } catch (NoSuchAlgorithmException e) {
                                                e.printStackTrace();
                                            } catch (SecurityHandlerException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        //
                                    }).start();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            List<String> category = Arrays.asList("15", "16", "17", "18", "17", "20", "24","27","28","30","34","33","32","31","30","29","28","27","26","25","24","20","18","16");
                            String json = gson.toJson(category);
                            editorZagrebPM10.putString("array_zagreb_pm10", json);
                            editorZagrebPM10.commit();
                            System.out.println("fail PM10 Zagreb");
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("x-auth-timestamp", String.valueOf(securityRequest.getTimestamp()));
                            headers.put("x-auth-size", String.valueOf(1));
                            headers.put("x-auth-1", x_auth_1);
                            System.out.println("headers");
                            System.out.println(headers);
                            return headers;
                        }
                    };

            queue.add(stringRequest);
        } catch (NoSuchAlgorithmException e)

        {
            e.printStackTrace();
        }
    }

    public void getViennaPM25() {

        String url1 = "https://enviro5.ait.ac.at/symbiote/rap/Sensors('5ae1893e3a6fd805304864ae')/Observations?$top=1";

        // Initializing application security handler
        String keyStorePath =
                getApplicationContext().getFilesDir().getAbsolutePath() + keyStoreFilename;

        try {
            clientSH = ClientSecurityHandlerFactory.getSecurityHandler(AAMServerAddress, keyStorePath,
                    keyStorePassword);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }
        // examples how to retrieve AAM instances
        AAM coreAAM = clientSH.getCoreAAMInstance();
        AAM platform1 = null;
        try {
            platform1 = clientSH.getAvailableAAMs().get(platformId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring application certificate, this operation needs the user password
        try {
            Certificate clientCertificate =
                    clientSH.getCertificate(platform1, icomUsername, icomPassword, clientId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring HOME token from platform1 AAM
        Token token = null;
        try {
            token = clientSH.login(platform1);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        // preparing the security request using the credentials the actor has from platform 1
        Set<AuthorizationCredentials> authorizationCredentialsSet = new HashSet<>();
        // please note that from now on we don't need the password and only the the client certificate and matching private key.
        authorizationCredentialsSet.add(new AuthorizationCredentials(token, platform1,
                clientSH.getAcquiredCredentials().get(platform1.getAamInstanceId()).homeCredentials));
        try {
            SecurityRequest securityRequest =
                    MutualAuthenticationHelper.getSecurityRequest(authorizationCredentialsSet, false);
            //Simple HTTP Request to test
            String x_auth_1 = "{\"token\":\"["
                    + token.getToken()
                    + "]\", "
                    + "\"authenticationChallenge\":\"\", "
                    + "\"clientCertificate\":\"\", "
                    + "\"clientCertificateSigningAAMCertificate\":\"\", "
                    + "\"foreignTokenIssuingAAMCertificate\":\"\"}";
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest stringRequest =
                    new JsonObjectRequest(Request.Method.GET, url1, null,//AAMServerAddress + "query"
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    //verifying the service response
                                    //once again - don't start the background job in your production app :)
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d(TAG, response.toString());
                                            try {
                                                String serviceResponse = response.getString("serviceResponse");
                                                System.out.println("serviceResponse");
                                                System.out.println(serviceResponse);
                                                if (serviceResponse != null) {
                                                    boolean result =
                                                            MutualAuthenticationHelper.isServiceResponseVerified(serviceResponse,
                                                                    clientSH.getComponentCertificate("search", platformId));
                                                    if (result)
                                                        Log.d(TAG, "ServiceResponse verified!");
                                                    JSONArray jsonarray = response.getJSONArray("body");

                                                    JSONObject jsonobject = jsonarray.getJSONObject(0);//!
                                                    JSONArray property = jsonobject.getJSONArray("obsValues");
                                                    for(int i=0;i<property.length();i++){

                                                        JSONObject obj = (JSONObject) property.get(i);
                                                        String val = obj.getString("value");
                                                        //System.out.println(obj);
                                                        JSONObject obs = obj.getJSONObject("obsProperty");
                                                        String element = obs.getString("name");


                                                        List<String> category = Arrays.asList(val);
                                                        String json = gson.toJson(category);
                                                        editorViennaPM25.putString("array_vienna_pm25", json);
                                                        editorViennaPM25.commit();
                                                    }//!
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            } catch (CertificateException e) {
                                                e.printStackTrace();
                                            } catch (NoSuchAlgorithmException e) {
                                                e.printStackTrace();
                                            } catch (SecurityHandlerException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        //
                                    }).start();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            List<String> category = Arrays.asList("9", "6", "10", "11", "9", "10", "11","12","13","17","20","19","18","16","15","14","17","19","15","14","12","11","10","9");
                            String json = gson.toJson(category);
                            editorViennaPM25.putString("array_vienna_pm25", json);
                            editorViennaPM25.commit();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("x-auth-timestamp", String.valueOf(securityRequest.getTimestamp()));
                            headers.put("x-auth-size", String.valueOf(1));
                            headers.put("x-auth-1", x_auth_1);
                            System.out.println("headers");
                            System.out.println(headers);
                            return headers;
                        }
                    };

            queue.add(stringRequest);
        } catch (NoSuchAlgorithmException e)

        {
            e.printStackTrace();
        }
    }

    public void getZagrebPM25() {

        String url1 = "https://enviro5.ait.ac.at/symbiote/rap/Sensors('5ae189493a6fd805304864da')/Observations?$top=1";

        // Initializing application security handler
        String keyStorePath =
                getApplicationContext().getFilesDir().getAbsolutePath() + keyStoreFilename;

        try {
            clientSH = ClientSecurityHandlerFactory.getSecurityHandler(AAMServerAddress, keyStorePath,
                    keyStorePassword);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }
        // examples how to retrieve AAM instances
        AAM coreAAM = clientSH.getCoreAAMInstance();
        AAM platform1 = null;
        try {
            platform1 = clientSH.getAvailableAAMs().get(platformId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring application certificate, this operation needs the user password
        try {
            Certificate clientCertificate =
                    clientSH.getCertificate(platform1, icomUsername, icomPassword, clientId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring HOME token from platform1 AAM
        Token token = null;
        try {
            token = clientSH.login(platform1);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        // preparing the security request using the credentials the actor has from platform 1
        Set<AuthorizationCredentials> authorizationCredentialsSet = new HashSet<>();
        // please note that from now on we don't need the password and only the the client certificate and matching private key.
        authorizationCredentialsSet.add(new AuthorizationCredentials(token, platform1,
                clientSH.getAcquiredCredentials().get(platform1.getAamInstanceId()).homeCredentials));
        try {
            SecurityRequest securityRequest =
                    MutualAuthenticationHelper.getSecurityRequest(authorizationCredentialsSet, false);
            //Simple HTTP Request to test
            String x_auth_1 = "{\"token\":\"["
                    + token.getToken()
                    + "]\", "
                    + "\"authenticationChallenge\":\"\", "
                    + "\"clientCertificate\":\"\", "
                    + "\"clientCertificateSigningAAMCertificate\":\"\", "
                    + "\"foreignTokenIssuingAAMCertificate\":\"\"}";
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest stringRequest =
                    new JsonObjectRequest(Request.Method.GET, url1, null,//AAMServerAddress + "query"
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    //verifying the service response
                                    //once again - don't start the background job in your production app :)
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d(TAG, response.toString());
                                            try {
                                                String serviceResponse = response.getString("serviceResponse");
                                                System.out.println("serviceResponse");
                                                System.out.println(serviceResponse);
                                                if (serviceResponse != null) {
                                                    boolean result =
                                                            MutualAuthenticationHelper.isServiceResponseVerified(serviceResponse,
                                                                    clientSH.getComponentCertificate("search", platformId));
                                                    if (result)
                                                        Log.d(TAG, "ServiceResponse verified!");
                                                    JSONArray jsonarray = response.getJSONArray("body");

                                                    JSONObject jsonobject = jsonarray.getJSONObject(0);//!
                                                    JSONArray property = jsonobject.getJSONArray("obsValues");
                                                    for(int i=0;i<property.length();i++){

                                                        JSONObject obj = (JSONObject) property.get(i);
                                                        String val = obj.getString("value");
                                                        //System.out.println(obj);
                                                        JSONObject obs = obj.getJSONObject("obsProperty");
                                                        String element = obs.getString("name");


                                                        List<String> category = Arrays.asList(val);
                                                        String json = gson.toJson(category);
                                                        editorZagrebPM25.putString("array_zagreb_pm25", json);
                                                        editorZagrebPM25.commit();
                                                    }//!
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            } catch (CertificateException e) {
                                                e.printStackTrace();
                                            } catch (NoSuchAlgorithmException e) {
                                                e.printStackTrace();
                                            } catch (SecurityHandlerException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        //
                                    }).start();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            List<String> category = Arrays.asList("6", "6", "8", "9", "10", "12", "13","14","16","18","21","19","16","16","16","15","14","13","11","10","9","8","7","6");
                            String json = gson.toJson(category);
                            editorZagrebPM25.putString("array_zagreb_pm25", json);
                            editorZagrebPM25.commit();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("x-auth-timestamp", String.valueOf(securityRequest.getTimestamp()));
                            headers.put("x-auth-size", String.valueOf(1));
                            headers.put("x-auth-1", x_auth_1);
                            System.out.println("headers");
                            System.out.println(headers);
                            return headers;
                        }
                    };

            queue.add(stringRequest);
        } catch (NoSuchAlgorithmException e)

        {
            e.printStackTrace();
        }
    }




    private void sendjsonRequest() {

        String x_auth_1 = "{\"token\":\"[eyJhbGciOiJFUzI1NiJ9.eyJ0dHlwIjoiR1VFU1QiLCJzdWIiOiJndWVzdCIsImlwayI6Ik1Ga3dFd1lIS29aSXpqMENBUVlJS29aSXpqMERBUWNEUWdBRXQwVExQODBZUDFHWHhiVXErYkd5ZGdFZzRuNzFqVkRVTVdBYXoxbTBkam5LL0lldUlSL3lWNGNLSnZnTzlST3pMZUVNODBzbThMQ0JkTCtzYW9wdzZRPT0iLCJpc3MiOiJTeW1iSW9UZV9Db3JlX0FBTSIsImV4cCI6MTUzNjU2NTcxNywiaWF0IjoxNTM2NTY1NjU3LCJqdGkiOiItMjI3OTIyMjg3Iiwic3BrIjoiTUZrd0V3WUhLb1pJemowQ0FRWUlLb1pJemowREFRY0RRZ0FFd2V3YkxLMVNFUDErS1VzQ2swRmNxSlIrNm94QVFUUHZPS3dWY0FrNkJkSHVLSzRWT1MxNEI3TlB1UHY5TGZvSUNUcEhQbG1kNzNTWXdmSmZiZXdEamc9PSJ9.eOycvLx3WPtnmmJJwTDFC8gXtMBMkz6yFKTtbKKE9dfEsO69SlI_GcZ7dM3nQJYNrcdGR7OJG4QHizsi5p-wgg]\", "
                + "\"authenticationChallenge\":\"\", "
                + "\"clientCertificate\":\"\", "
                + "\"clientCertificateSigningAAMCertificate\":\"\", "
                + "\"foreignTokenIssuingAAMCertificate\":\"\"}";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://symbiote.tel.fer.hr/rap/Sensors('5b34aa393a6fd861b91b1c05')/Observations?$top=1", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println("success");
                //System.out.println(response);

                for (int counter = 0; counter < response.length(); counter++) {

                    try {
                        JSONObject jsonObject = response.getJSONObject(counter);//get element (one by one)
                        //System.out.println(jsonObject);
                        JSONArray values = jsonObject.getJSONArray("obsValues");
                        JSONObject elementDescription = values.getJSONObject(0);
                        String value = elementDescription.getString("value");
                        JSONObject property = elementDescription.getJSONObject("obsProperty");
                        String nameParticle = property.getString("name");
                        //if(nameParticle.contains("no2")){

                        arrayListPM25Vienna.add(value);
                        //System.out.println(value);
                        //}

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                String json = gson.toJson(arrayListPM25Vienna);
                ;
                editor.putString("array_id", json);
                editor.commit();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-auth-timestamp", "1536565639439");
                headers.put("x-auth-size", String.valueOf(1));
                headers.put("x-auth-1", x_auth_1);
                return headers;
            }
        };

        requestQueue.add(jsonArrayRequest);
    }





    public void getSecurityRequest() {

        String url4 = "https://symbiote.tel.fer.hr/rap/Sensors('5b34aa393a6fd861b91b1c03')/Observations?$top=1";
        String url2 = "https://enviro5.ait.ac.at/symbiote/rap/Sensors('5ae189543a6fd80530486510')/Observations?$top=1";
        String url1 = "https://symbiote-open.man.poznan.pl/coreInterface/query";
        String url3 ="";

        // Initializing application security handler
        String keyStorePath =
                getApplicationContext().getFilesDir().getAbsolutePath() + keyStoreFilename;

        try {
            clientSH = ClientSecurityHandlerFactory.getSecurityHandler(AAMServerAddress, keyStorePath,
                    keyStorePassword);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }
        // examples how to retrieve AAM instances
        AAM coreAAM = clientSH.getCoreAAMInstance();
        AAM platform1 = null;
        try {
            platform1 = clientSH.getAvailableAAMs().get(platformId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring application certificate, this operation needs the user password
        try {
            Certificate clientCertificate =
                    clientSH.getCertificate(platform1, icomUsername, icomPassword, clientId);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        }

        // Acquiring HOME token from platform1 AAM
        Token token = null;
        try {
            token = clientSH.login(platform1);
        } catch (SecurityHandlerException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        // preparing the security request using the credentials the actor has from platform 1
        Set<AuthorizationCredentials> authorizationCredentialsSet = new HashSet<>();
        // please note that from now on we don't need the password and only the the client certificate and matching private key.
        authorizationCredentialsSet.add(new AuthorizationCredentials(token, platform1,
                clientSH.getAcquiredCredentials().get(platform1.getAamInstanceId()).homeCredentials));
        try {
            SecurityRequest securityRequest =
                    MutualAuthenticationHelper.getSecurityRequest(authorizationCredentialsSet, false);
            //Simple HTTP Request to test
            String x_auth_1 = "{\"token\":\"["
                    + token.getToken()
                    + "]\", "
                    + "\"authenticationChallenge\":\"\", "
                    + "\"clientCertificate\":\"\", "
                    + "\"clientCertificateSigningAAMCertificate\":\"\", "
                    + "\"foreignTokenIssuingAAMCertificate\":\"\"}";
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest stringRequest =
                    new JsonObjectRequest(Request.Method.GET, url4, null,//AAMServerAddress + "query"
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    //verifying the service response
                                    //once again - don't start the background job in your production app :)
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d(TAG, response.toString());
                                            try {
                                                String serviceResponse = response.getString("serviceResponse");
                                                System.out.println("serviceResponse");
                                                System.out.println(serviceResponse);
                                                if (serviceResponse != null) {
                                                    boolean result =
                                                            MutualAuthenticationHelper.isServiceResponseVerified(serviceResponse,
                                                                    clientSH.getComponentCertificate("search", platformId));
                                                    if (result)
                                                        Log.d(TAG, "ServiceResponse verified!");
                                                    JSONArray jsonobject = response.getJSONArray("body");
                                                    System.out.println(jsonobject);
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            } catch (CertificateException e) {
                                                e.printStackTrace();
                                            } catch (NoSuchAlgorithmException e) {
                                                e.printStackTrace();
                                            } catch (SecurityHandlerException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        //
                                    }).start();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "That didn't work!");
                            System.out.println(error);
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("x-auth-timestamp", String.valueOf(securityRequest.getTimestamp()));
                            headers.put("x-auth-size", String.valueOf(1));
                            headers.put("x-auth-1", x_auth_1);
                            System.out.println("headers");
                            System.out.println(headers);
                            return headers;
                        }
                    };

            queue.add(stringRequest);
        } catch (NoSuchAlgorithmException e)

        {
            e.printStackTrace();
        }
    }

    public void getGuestTokenAndHeaders() {
        //this code is from the readme.md ss github
        // creating REST client communicating with SymbIoTe Authorization Services
        // AAMServerAddress can be acquired from SymbIoTe web page
        IAAMClient restClient = new AAMClient(AAMServerAddress);

        // acquiring Guest Token
        String guestToken = null;
        try {
            guestToken = restClient.getGuestToken();
            Log.d(MainActivity.class.getSimpleName(), String.format("Got token: %s", guestToken));
        } catch (JWTCreationException e) {
            e.printStackTrace();
        } catch (AAMException e) {
            e.printStackTrace();
        }

        // creating securityRequest using guest Token
        SecurityRequest securityRequest = new SecurityRequest(guestToken);

        // converting the prepared request into communication ready HTTP headers.
        Map<String, String> securityHeaders = new HashMap<>();
        try {
            securityHeaders = securityRequest.getSecurityRequestHeaderParams();
            Log.d(MainActivity.class.getSimpleName(),
                    String.format("Got headers: %s", securityHeaders.values().toString()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}