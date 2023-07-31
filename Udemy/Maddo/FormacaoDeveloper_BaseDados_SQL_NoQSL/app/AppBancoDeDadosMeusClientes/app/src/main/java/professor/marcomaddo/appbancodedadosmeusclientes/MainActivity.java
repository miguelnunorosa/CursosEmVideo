package professor.marcomaddo.appbancodedadosmeusclientes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Cliente objCliente;
    // INSERT INTO cliente
    // VALUES('Maddo', 999.99, 99.9987, 18, 1,"2020-12-01","12:12:12:12");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDataBase appDataBase = new AppDataBase(this);


       /* for (int i = 0; i < 20; i++) {
            objCliente = new Cliente();
            //objCliente.setId(2);

            objCliente.setNome("Cliente " + i);
            objCliente.setSalario((1000 * i) + 1000);
            objCliente.setPreco((100 * i) + 100);
            objCliente.setIdade(18 + i);
            objCliente.setAtivo(false);
            objCliente.setDataCadastro("2020-12-10");
            objCliente.setHoraCadastro("20:10:10:12");

            appDataBase.insert("cliente", objCliente);
        }*/

        // INSERT INTO TABLE values (c1,c2,c3)
        //appDataBase.insert("cliente", objCliente);

        // UPDATE table SET colunaA = novo_conteudoA, colunaN = novo_conteudoN WHERE condição;
        //appDataBase.update("cliente", objCliente);

        // DELETE FROM table WHERE condição;
        //appDataBase.delete("cliente", objCliente.getId());

        //SELECT * FROM cliente;
        appDataBase.select("cliente");

        for (Cliente objRetorno : appDataBase.select("cliente")) {
            Log.i("SELECT_DB", "Cliente: "+objRetorno.getId()+" "+objRetorno.getNome()+" "+objRetorno.getSalario());
        }


    }
}