package com.example.receitas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.receitas.R;

public class MainActivity extends AppCompatActivity {

    Button btnComecar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnComecar = findViewById(R.id.btnComecar);

        btnComecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Indo para a próxima tela", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), segundaActivityEscolha.class);

                startActivity(intent);
            }
        });
    }

}