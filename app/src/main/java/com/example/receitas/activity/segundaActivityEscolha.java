package com.example.receitas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.receitas.R;
import com.google.android.material.textfield.TextInputEditText;

public class segundaActivityEscolha extends AppCompatActivity {

    TextInputEditText textInputEscreva;
    Button BtnOk, BtnReceitas;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundaescolha);

        textInputEscreva = findViewById(R.id.textInputEscreva);
        BtnOk = findViewById(R.id.BtnOk);
        BtnReceitas = findViewById(R.id.BtnReceitas);


        BtnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String receitas = textInputEscreva.getText().toString();

                if(!receitas.isEmpty()){
                    Intent intent = new Intent(getApplicationContext(), terceitaActivityReceitas.class);
                    intent.putExtra("FiltroReceitas", receitas);
                    startActivity(intent);
                }



            }
        });
        BtnReceitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), terceitaActivityReceitas.class);
                intent.putExtra("FiltroReceitas","");
                startActivity(intent);
            }
        });
    }
}

