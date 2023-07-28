package foo.fd.estudodecasolistar.view;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    Button btnListarEstados;
    Button btnListarCidades;
    Button btnDeletarCidade;

    EditText editIdCidade;
    TextView txtResultado;

    int idCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String token = settings.APITOKEN;
        createFields();


        btnListarEstados.setOnClickListener(v -> {
            ListarEstadosAsyncTask task = new ListarEstadosAsyncTask(token);
            task.execute(); //start async process
        });

        //TODO
        btnListarCidades.setOnClickListener(v -> {
            ListarCidadesAsyncTask task = new ListarCidadesAsyncTask(token);
            task.execute();
        });

        //TODO
        btnDeletarCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idCidade = Integer.parseInt(editIdCidade.getText().toString());

                DeleteCidadesAsyncTask task = new DeleteCidadesAsyncTask(token, idCidade);
                task.execute();
            }
        });



    }
    private void createFields(){
        btnListarEstados = findViewById(R.id.btnListarEstados);
        btnListarCidades = findViewById(R.id.btnListarCidades);
        btnDeletarCidade = findViewById(R.id.btnDeletarCidade);
        editIdCidade = findViewById(R.id.editIdCidade);
        txtResultado = findViewById(R.id.txtResultado);
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


    public class ListarCidadesAsyncTask extends AsyncTask<String, String, String> {

        String api_token, query;
        HttpURLConnection urlConnection;
        URL url = null;
        Uri.Builder builder;
        int responseCode;



        public ListarCidadesAsyncTask(String token) {
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
                url = new URL(settings.APIURL + settings.API_LISTARCIDADE);
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
                Cidade cidade;
                JSONArray jsonArray = new JSONArray(result);

                if (jsonArray.length() != 0){
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        cidade = new Cidade(jsonObject.getInt("id"),
                                jsonObject.getInt("estadoID"),
                                jsonObject.getString("nome"));

                        Log.i("API-Listar: ", "Cidade " + cidade.getId() + ": " + cidade.getEstadoID() + " - " + cidade.getNome() );
                    }
                }

            }catch (Exception e){
                Log.i("API-Listar", "onPostExecute() => " + e.getMessage());
            }
        }

    }


    public class DeleteCidadesAsyncTask extends AsyncTask<String, String, String>{

        String api_token, query;

        HttpURLConnection urlConnection;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = settings.APIURL + settings.API_DELETECIDADE;
        int responseCode;

        public DeleteCidadesAsyncTask(String token, int idCidade) {

            this.api_token = token;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_idCidade", String.valueOf(idCidade));

        }

        @Override
        protected void onPreExecute() {
            Log.i("API-Listar", "onPreExecute()");
        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("APIListar", "doInBackground()");

            //create data for URL
            try {
                url = new URL(URL_WEB_SERVICES);
            } catch (MalformedURLException e) {
                Log.i("API-Listar", "MalformedURLException --> " + e.getMessage());
            } catch (Exception e) {
                Log.i("API-Listar", "doInBackground() --> " + e.getMessage());
            }

            // Create HTTP Request - POST - Result will be an ArrayJson
            // connection
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(settings.READ_TIMEOUT);
                urlConnection.setConnectTimeout(settings.CONNECTION_TIMEOUT);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("charset", settings.CHARSET);

                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                urlConnection.connect();
            } catch (Exception e) {
                Log.i("API-Listar", "HttpURLConnection --> " + e.getMessage());
            }

            // Add TOKEN and/or another params
            try {
                query = builder.build().getEncodedQuery();

                OutputStream stream = urlConnection.getOutputStream();

                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(stream, settings.CHARSET) );

                writer.write(query);
                writer.flush();
                writer.close();
                stream.close();

                urlConnection.connect();
            } catch (Exception e) {
                Log.i("API-Listar", "BufferedWriter --> " + e.getMessage());
            }

            // get response - arrayJson
            // http - response code | 200 | 404 | 503
            try {
                responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream input = urlConnection.getInputStream();

                    BufferedReader reader = new BufferedReader( new InputStreamReader(input)  );
                    StringBuilder result = new StringBuilder();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    return result.toString();
                } else {
                    return "HTTP ERRO: " + responseCode;
                }
            } catch (Exception e) {
                Log.i("API-Listar", "StringBuilder --> " + e.getMessage());

                return "Exception Error: " + e.getMessage();
            } finally {
                urlConnection.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String result) { // Object Json

            Log.i("API-Listar", "onPostExecute()--> Result: " + result);

            try {
                JSONObject jsonObject = new JSONObject(result);

                if (jsonObject.getBoolean("deletado")) {
                    txtResultado.setText("Registo Eliminado: " + idCidade);
                    Log.i("API-Listar", "onPostExecute()--> Eliminado com Sucesso");
                }else{
                    txtResultado.setText("Falha ao Eliminar: " + idCidade);

                    Log.i("API-Listar", "onPostExecute()--> Falha ao Deletar");
                    Log.i("API-Listar", "onPostExecute()--> " + jsonObject.getString("SQL"));
                }
            }catch (Exception e){
                Log.i("API-Listar", "onPostExecute()--> " + e.getMessage());
            }

        }

    }



}
