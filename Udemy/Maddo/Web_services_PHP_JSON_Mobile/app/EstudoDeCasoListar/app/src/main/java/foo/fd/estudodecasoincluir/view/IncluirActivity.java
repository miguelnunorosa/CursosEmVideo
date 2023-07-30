package foo.fd.estudodecasoincluir.view;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

import foo.fd.estudodecasoincluir.R;
import foo.fd.estudodecasoincluir.model.Cidade;
import foo.fd.estudodecasoincluir.model.Estado;

public class IncluirActivity extends AppCompatActivity {


    List<Estado> estadoList;
    List<String> ufSiglas;

    Button btnIncluir;

    EditText editNomeCidade;

    Spinner spSiglas;

    String token;

    ArrayAdapter<String> siglasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluir);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        token = "fabricadedesenvolvedor";

        editNomeCidade = findViewById(R.id.editNomeCidade);
        btnIncluir = findViewById(R.id.btnIncluir);
        spSiglas = findViewById(R.id.spSiglas);

        Intent iMain = getIntent();

        estadoList = (List<Estado>) iMain.getSerializableExtra("estados");

        ufSiglas = new ArrayList<>();

        for (int i = 0; i < estadoList.size(); i++) {

            ufSiglas.add(estadoList.get(i).getSigla());

        }

        siglasAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ufSiglas);
        siglasAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spSiglas.setAdapter(siglasAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int estadoID = estadoList.get(spSiglas.getSelectedItemPosition()).getId();

                Cidade obj = new Cidade();

                obj.setEstadoID(estadoID);
                obj.setNome(editNomeCidade.getText().toString());

                IncluirCidadeAsyncTask task = new IncluirCidadeAsyncTask(token, obj);
                task.execute();
            }
        });
    }


    public class IncluirCidadeAsyncTask
            extends
            AsyncTask<String, String, String> {

        String api_token, query;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = "http://192.168.0.12/cursoudemy/APIIncluirCidade.php";

        final int READ_TIMEOUT = 10000;
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;

        // Contrutor recebe um objeto Cidade
        public IncluirCidadeAsyncTask(String token, Cidade obj) {

            this.api_token = token;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_estadoID", String.valueOf(obj.getEstadoID()));
            builder.appendQueryParameter("api_nome", String.valueOf(obj.getNome()));

        }

        @Override
        protected void onPreExecute() {

            Log.i("APIListar", "onPreExecute()");

        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("APIListar", "doInBackground()");

            // Gerar o conteúdo para a URL

            try {

                url = new URL(URL_WEB_SERVICES);

            } catch (MalformedURLException e) {

                Log.i("APIListar", "MalformedURLException --> " + e.getMessage());

            } catch (Exception e) {

                Log.i("APIListar", "doInBackground() --> " + e.getMessage());
            }

            // Gerar uma requisição HTTP - POST - Result será um ArrayJson

            // conn

            try {

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("charset", "utf-8");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.connect();

            } catch (Exception e) {

                Log.i("APIListar", "HttpURLConnection --> " + e.getMessage());

            }

            // Adicionar o TOKEN e/ou outros parâmetros como por exemplo
            // um objeto a ser incluido, deletado ou alterado.
            // CRUD completo

            try {

                query = builder.build().getEncodedQuery();

                OutputStream stream = conn.getOutputStream();

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(stream, "utf-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                stream.close();

                conn.connect();

            } catch (Exception e) {

                Log.i("APIListar", "BufferedWriter --> " + e.getMessage());

            }

            // receber o response - arrayJson
            // http - código do response | 200 | 404 | 503

            try {

                response_code = conn.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK) {


                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(

                            new InputStreamReader(input)

                    );

                    StringBuilder result = new StringBuilder();

                    String linha = null;

                    while ((linha = reader.readLine()) != null) {

                        result.append(linha);
                    }

                    return result.toString();

                } else {

                    return "HTTP ERRO: " + response_code;
                }

            } catch (Exception e) {

                Log.i("APIListar", "StringBuilder --> " + e.getMessage());

                return "Exception Erro: " + e.getMessage();

            } finally {

                conn.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String result) { // Objeto Json

            Log.i("APIListar", "onPostExecute()--> Result: " + result);

            try {

                JSONObject jsonObject = new JSONObject(result);

                if (jsonObject.getBoolean("adicionado")) {

                    Toast toast  = Toast.makeText(getBaseContext(),"Cidade adicionada com sucesso",Toast.LENGTH_LONG);

                    toast.setGravity(Gravity.TOP,0,250);

                    toast.show();
                    Log.i("APIListar", "onPostExecute()--> Adicionado com Sucesso");

                }else{

                    Toast toast  = Toast.makeText(getBaseContext(),"Falha ao adicionar Cidade...",Toast.LENGTH_LONG);


                    toast.setGravity(Gravity.TOP,0,250);

                    toast.show();


                    Log.i("APIListar", "onPostExecute()--> Falha ao Adicionar");
                    Log.i("APIListar", "onPostExecute()--> "+jsonObject.getString("SQL"));

                }


            }catch (Exception e){

                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }

        }
    }


}
