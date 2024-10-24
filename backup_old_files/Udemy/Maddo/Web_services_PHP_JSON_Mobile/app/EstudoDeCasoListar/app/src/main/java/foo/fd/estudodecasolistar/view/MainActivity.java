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
import java.util.ArrayList;
import java.util.List;

import foo.fd.estudodecasolistar.controller.ControllerCidade;
import foo.fd.estudodecasolistar.model.Cidade;
import foo.fd.estudodecasolistar.model.Estado;
import foo.fd.estudodecasolistar.settings.Settings;

import foo.fd.estudodecasolistar.R;

public class MainActivity extends AppCompatActivity {

    // CRUD - Só falta incluir
    Button btnListarEstados, btnListarCidades, btnDeletarCidade, btnAlterarCidade, btnPesquisar;
    EditText editIdCidade, editNomeCidade;
    TextView txtResultado;
    List<Cidade> cidadeList;
    int idCidade;
    Cidade obj;
    String token;
    ControllerCidade controllerCidade;
    ListarCidadesAsyncTask task;
    Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        token = settings.APITOKEN;
        createFields();

        task = new ListarCidadesAsyncTask(token); //create Array with a list of cities
        task.execute();


        btnListarEstados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListarEstadosAsyncTask task = new ListarEstadosAsyncTask(token);
                task.execute();
            }
        });

        btnListarCidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cidadeList.clear();

                task = new ListarCidadesAsyncTask(token);
                task.execute();
            }
        });

        btnDeletarCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idCidade = Integer.parseInt(editIdCidade.getText().toString());

                DeletarCidadeAsyncTask task = new DeletarCidadeAsyncTask(token, idCidade);
                task.execute();
            }
        });

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj = new Cidade();
                controllerCidade = new ControllerCidade();

                // Não estamos validadando as entrada de dados.
                obj = controllerCidade.buscarObjeto(cidadeList,editNomeCidade.getText().toString());

                try {
                    txtResultado.setText("Nome " + obj.getNome());
                    editIdCidade.setText(String.valueOf(obj.getId()));
                }catch (Exception e){
                    txtResultado.setText("Cidade Não localizada...");
                }
            }
        });

        btnAlterarCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj.setNome(editNomeCidade.getText().toString()); //change the city name on object Cidade

                AlterarCidadeAsyncTask task = new AlterarCidadeAsyncTask(token, obj);
                task.execute();
            }
        });

    }

    private void createFields(){
        btnListarEstados = findViewById(R.id.btnListarEstados);
        btnListarCidades = findViewById(R.id.btnListarCidades);
        btnDeletarCidade = findViewById(R.id.btnDeletarCidade);
        btnAlterarCidade = findViewById(R.id.btnAlterarCidade);
        btnPesquisar = findViewById(R.id.btnPesquisar);
        editNomeCidade = findViewById(R.id.editNomeCidade);
        txtResultado = findViewById(R.id.txtResultado);
        editIdCidade = findViewById(R.id.editIdCidade);

        editIdCidade.setEnabled(false);
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

        String api_token, query, line;
        int responseCode;
        HttpURLConnection urlConnection;
        URL url = null;
        Uri.Builder builder;

        final String API_QUERY_URL = settings.APIURL + settings.API_LISTARESTADO;


        public ListarEstadosAsyncTask(String token) {
            this.api_token = token;
            this.builder = new Uri.Builder();

            builder.appendQueryParameter("api_token", api_token);
        }

        @Override
        protected void onPreExecute() { Log.i("APIListar", "onPreExecute()"); }

        @Override
        protected String doInBackground(String... strings) {
            Log.i("APIListar", "doInBackground()");

            //Create content for URL
            try {
                url = new URL(API_QUERY_URL);
            } catch (MalformedURLException e) {
                Log.i("APIListar", "MalformedURLException --> " + e.getMessage());
            } catch (Exception e) {
                Log.i("APIListar", "doInBackground() --> " + e.getMessage());
            }

            //Create HTTP request - POST - Result will be an ArrayJson
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
                Log.i("APIListar", "HttpURLConnection --> " + e.getMessage());
            }

            //Add token and/or another param, for example, include object, delete object ou updated object
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
                Log.i("APIListar", "BufferedWriter --> " + e.getMessage());
            }

            //Receive the response response - arrayJson
            //http - response code | 200 | 404 | 503
            try {
                responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream input = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader( new InputStreamReader(input) );

                    StringBuilder result = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    return result.toString();
                } else {
                    return "HTTP ERROR: " + responseCode;
                }
            } catch (Exception e) {
                Log.i("APIListar", "StringBuilder --> " + e.getMessage());

                return "Exception Error: " + e.getMessage();
            } finally {
                urlConnection.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("APIListar", "onPostExecute()--> Result: " + result);

            try {
                Estado estado;

                JSONArray jsonArray = new JSONArray(result);

                if (jsonArray.length() != 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        estado = new Estado(jsonObject.getInt("id"),
                                            jsonObject.getString("sigla"),
                                            jsonObject.getString("nome"));
                        Log.i("APIListar", "Estado: -> " + estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
                    }
                }

            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }

    }


    public class ListarCidadesAsyncTask extends AsyncTask<String, String, String> {

        String api_token, query, line;
        int responseCode;
        HttpURLConnection urlConnection;
        URL url = null;
        Uri.Builder builder;
        final String API_QUERY_URL = settings.APIURL + settings.API_LISTARCIDADE;


        public ListarCidadesAsyncTask(String token) {
            this.api_token = token;

            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
        }

        @Override
        protected void onPreExecute() { Log.i("APIListar", "onPreExecute()"); }

        @Override
        protected String doInBackground(String... strings) {
            Log.i("APIListar", "doInBackground()");

            //Create content for URL
            try {
                url = new URL(API_QUERY_URL);
            } catch (MalformedURLException e) {
                Log.i("APIListar", "MalformedURLException --> " + e.getMessage());
            } catch (Exception e) {
                Log.i("APIListar", "doInBackground() --> " + e.getMessage());
            }

            //Create HTTP request - POST - Result will be an ArrayJson
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
                Log.i("APIListar", "HttpURLConnection --> " + e.getMessage());
            }

            //Add token and/or another param, for example, include object, delete object ou updated object
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
                Log.i("APIListar", "BufferedWriter --> " + e.getMessage());
            }

            //Receive the response response - arrayJson
            //http - response code | 200 | 404 | 503
            try {
                responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream input = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader( new InputStreamReader(input) );

                    StringBuilder result = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    return result.toString();
                } else {
                    return "HTTP ERROR: " + responseCode;
                }
            } catch (Exception e) {
                Log.i("APIListar", "StringBuilder --> " + e.getMessage());

                return "Exception Erro: " + e.getMessage();
            } finally {
                urlConnection.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("APIListar", "onPostExecute()--> Result: " + result);

            try {
                Cidade cidade;

                JSONArray jsonArray = new JSONArray(result);

                if (jsonArray.length() != 0) {
                    cidadeList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        cidade = new Cidade();

                        cidade.setId(jsonObject.getInt("id"));
                        cidade.setNome(jsonObject.getString("nome"));

                        cidadeList.add(cidade);

                        //Log.i("APIListar", cidade.toString(jsonObject.getString("sigla")));
                    }
                }
            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }

    }


    public class DeletarCidadeAsyncTask extends AsyncTask<String, String, String> {

        String api_token, query, line;
        int responseCode;
        HttpURLConnection urlConnection;
        URL url = null;
        Uri.Builder builder;

        final String API_QUERY_URL = settings.APIURL + settings.API_DELETECIDADE;


        public DeletarCidadeAsyncTask(String token, int idCidade) {
            this.api_token = token;

            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_idCidade", String.valueOf(idCidade));
        }

        @Override
        protected void onPreExecute() { Log.i("APIListar", "onPreExecute()"); }

        @Override
        protected String doInBackground(String... strings) {
            Log.i("APIListar", "doInBackground()");

            //Create content for URL
            try {
                url = new URL(API_QUERY_URL);
            } catch (MalformedURLException e) {
                Log.i("APIListar", "MalformedURLException --> " + e.getMessage());
            } catch (Exception e) {
                Log.i("APIListar", "doInBackground() --> " + e.getMessage());
            }

            //Create HTTP request - POST - Result will be an ArrayJson
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
                Log.i("APIListar", "HttpURLConnection --> " + e.getMessage());
            }

            //Create HTTP request - POST - Result will be an ArrayJson
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
                Log.i("APIListar", "BufferedWriter --> " + e.getMessage());
            }

            //Create HTTP request - POST - Result will be an ArrayJson
            try {
                responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream input = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader( new InputStreamReader(input) );

                    StringBuilder result = new StringBuilder();

                    while ((line = reader.readLine()) != null) { result.append(line); }

                    return result.toString();
                } else {
                    return "HTTP ERROR: " + responseCode;
                }
            } catch (Exception e) {
                Log.i("APIListar", "StringBuilder --> " + e.getMessage());

                return "Exception Error: " + e.getMessage();
            } finally {
                urlConnection.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String result) { // Object Json
            Log.i("APIListar", "onPostExecute()--> Result: " + result);

            try {
                JSONObject jsonObject = new JSONObject(result);

                if (jsonObject.getBoolean("deleted")) {
                    txtResultado.setText("Registro Deletado: "+idCidade);

                    Log.i("APIListar", "onPostExecute()--> Deletado com Sucesso");
                }else{
                    txtResultado.setText("Falha ao Deletar: "+idCidade);

                    Log.i("APIListar", "onPostExecute()--> Falha ao Deletar");
                    Log.i("APIListar", "onPostExecute()--> "+jsonObject.getString("SQL"));
                }
            }catch (Exception e){
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }

    }


    public class AlterarCidadeAsyncTask extends AsyncTask<String, String, String> {

        String api_token, query, line;
        int responseCode;
        HttpURLConnection urlConnection;
        URL url = null;
        Uri.Builder builder;

        final String API_QUERY_URL = settings.APIURL + settings.API_UPDATECIDADE;

        // Contrutor receive object Cidade
        public AlterarCidadeAsyncTask(String token, Cidade obj) {
            this.api_token = token;

            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_idCidade", String.valueOf(obj.getId()));
            builder.appendQueryParameter("api_nome", String.valueOf(obj.getNome()));
        }

        @Override
        protected void onPreExecute() { Log.i("APIListar", "onPreExecute()"); }

        @Override
        protected String doInBackground(String... strings) {
            Log.i("APIListar", "doInBackground()");

            //Create content for URL
            try {
                url = new URL(API_QUERY_URL);
            } catch (MalformedURLException e) {
                Log.i("APIListar", "MalformedURLException --> " + e.getMessage());
            } catch (Exception e) {
                Log.i("APIListar", "doInBackground() --> " + e.getMessage());
            }

            //Create HTTP request - POST - Result will be an ArrayJson
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
                Log.i("APIListar", "HttpURLConnection --> " + e.getMessage());
            }

            //Add token and/or another param, for example, include object, delete object ou updated object
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
                Log.i("APIListar", "BufferedWriter --> " + e.getMessage());
            }

            //Create HTTP request - POST - Result will be an ArrayJson
            try {
                responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream input = urlConnection.getInputStream();

                    BufferedReader reader = new BufferedReader( new InputStreamReader(input) );

                    StringBuilder result = new StringBuilder();

                    while ((line = reader.readLine()) != null) {

                        result.append(line);
                    }

                    return result.toString();
                } else {
                    return "HTTP ERROR: " + responseCode;
                }
            } catch (Exception e) {
                Log.i("APIListar", "StringBuilder --> " + e.getMessage());

                return "Exception Error: " + e.getMessage();
            } finally {
                urlConnection.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String result) { // Object Json
            Log.i("APIListar", "onPostExecute()--> Result: " + result);

            try {
                JSONObject jsonObject = new JSONObject(result);

                if (jsonObject.getBoolean("updated")) {
                    txtResultado.setText("Registro Alterado: "+obj.getId()+" "+obj.getNome());

                    Log.i("APIListar", "onPostExecute()--> Alterado com Sucesso");
                }else{
                    txtResultado.setText("Falha ao Alterar: "+obj.getId());

                    Log.i("APIListar", "onPostExecute()--> Falha ao Alterar");
                    Log.i("APIListar", "onPostExecute()--> "+jsonObject.getString("SQL"));
                }
            }catch (Exception e){
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }

    }


}
