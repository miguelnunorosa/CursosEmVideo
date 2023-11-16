package pt.miguelrosa.primeironivelamento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import pt.miguelrosa.primeironivelamento.model.Cliente;

public class MainActivity extends AppCompatActivity {

    String TAG = "APP_Nivelamento";
    Cliente objCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objCliente = new Cliente("Maddo", "teste@asd.as", "123123", 55, true);

        Log.e(TAG, "onCreate: objCliente: Nome - " + objCliente.getNome());
        Log.e(TAG, "onCreate: objCliente: email - " + objCliente.getEmail());
        Log.e(TAG, "onCreate: objCliente: telefone - " + objCliente.getTelefone());
        Log.e(TAG, "onCreate: objCliente: idade - " + objCliente.getIdade());
        Log.e(TAG, "onCreate: objCliente: sexo - " + objCliente.isSexo());
    }
}