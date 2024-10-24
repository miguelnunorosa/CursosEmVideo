package devandroid.maddo.appgaseta.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "veiculo")
public class Veiculo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String placa;
    public int anoFabricacao;
    public String modelo;
    public String kilometragem;

}
