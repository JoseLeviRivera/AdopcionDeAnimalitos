package com.example.adopciondeanimales.Adaptadores;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class AdaptadorAnimales  extends AppCompatActivity {
    private ListView lista;
    private int datosImg[];
    private String datos[][];

    public ListView getLista() {
        return lista;
    }

    public void setLista(ListView lista) {
        this.lista = lista;
    }

    public int[] getDatosImg() {
        return datosImg;
    }

    public void setDatosImg(int[] datosImg) {
        this.datosImg = datosImg;
    }

    public String[][] getDatos() {
        return datos;
    }

    public void setDatos(String[][] datos) {
        this.datos = datos;
    }
}
