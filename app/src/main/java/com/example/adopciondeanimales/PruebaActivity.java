package com.example.adopciondeanimales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.adopciondeanimales.DAO.DAOAnimal;

public class PruebaActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAOAnimal dal = new DAOAnimal();
                dal.listQuerys();
            }
        });
    }
}