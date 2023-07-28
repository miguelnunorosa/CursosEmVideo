package foo.fd.estudodecasolistar.view;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import foo.fd.estudodecasolistar.model.Cidade;
import foo.fd.estudodecasolistar.model.Estado;
import foo.fd.estudodecasolistar.settings.Settings;

import foo.fd.estudodecasolistar.R;

public class MainActivity extends AppCompatActivity {
    Settings settings = new Settings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String token = settings.APITOKEN;

        ListarEstadosAsyncTask task = new ListarEstadosAsyncTask(token);
        task.execute(); //start async process

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
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



    public class ListarEstadosAsyncTask extends AsyncTask<String, String, String> {

        String api_token, query;
        HttpURLConnection urlConnection;
        URL url = null;
        Uri.Builder builder;
        int responseCode;



        public ListarEstadosAsyncTask(String token) {
            this.api_token = token;

            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
        }

        @Override
        protected void onPreExecute(){
            Log.i("API-Listar", "OnPreExecute() pass");
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.i("API-Listar", "doInBackground() pass");

            //create content for URL
            try{
                url = new URL(settings.APIURL + settings.API_LISTARESTADO);
            }catch (MalformedURLException e){
                Log.i("API-Listar", "doInBackground() => " + e.getMessage());
            }

            //create HTTP Request (Post) and the result will be an arrayJson
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(settings.READ_TIMEOUT);
                urlConnection.setConnectTimeout(settings.CONNECTION_TIMEOUT);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("charset", settings.CHARSET);
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                urlConnection.connect();
            }catch (Exception e){
                Log.i("API-Listar", "doInBackground() => " + e.getMessage());
            }


            //add token or another params
            try{
                query = builder.build().getEncodedQuery();

                OutputStream stream = urlConnection.getOutputStream();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream, settings.CHARSET));
                writer.write(query);
                writer.flush();
                writer.close();

                stream.close();

                urlConnection.connect();
            }catch (Exception e){
                Log.i("API-Listar", "doInBackground() => " + e.getMessage());
            }

            //get response (JSON format) and response code (200 / 404 / 503)
            try {
                responseCode = urlConnection.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    InputStream input = urlConnection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while( (line = reader.readLine()) != null){
                        result.append(line);
                    }

                    return result.toString();
                }else{
                    //return http error
                    return "HTTP ERROR: " + responseCode;
                }
            }catch (Exception e){
                Log.i("API-Listar", "doInBackground() => " + e.getMessage());
            }finally {
                urlConnection.disconnect();
            }

            return "Process complete!";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("API-Listar", "onPostExecute() result: " + result);
            //super.onPostExecute(result);

            try{
                Estado estado;
                JSONArray jsonArray = new JSONArray(result);

                if (jsonArray.length() != 0){
                    for (int i = 0; i < jsonArray.length(); i++) {
                       JSONObject jsonObject = jsonArray.getJSONObject(i);
                       estado = new Estado(jsonObject.getInt("id"),
                                           jsonObject.getString("sigla"),
                                           jsonObject.getString("nome"));

                       Log.i("API-Listar: ", "Estado " + estado.getId() + ": " + estado.getSigla() + " - " + estado.getNome() );
                    }
                }

            }catch (Exception e){
                Log.i("API-Listar", "onPostExecute() => " + e.getMessage());
            }
        }

    }


}
