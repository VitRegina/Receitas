package com.example.receitas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.receitas.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class segundaActivityEscolha extends AppCompatActivity {

    TextInputEditText TextInputDigite;
    Button BtnOk, BtnReceitas;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundaescolha);

        TextInputDigite = findViewById(R.id.TextInputDigite);
        BtnOk = findViewById(R.id.BtnOk);
        BtnReceitas = findViewById(R.id.BtnReceitas);


        BtnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String receitas = TextInputDigite.getText().toString();

                if(!receitas.isEmpty()){
                    Intent intent = new Intent(getApplicationContext(), terceitaActivityReceitas.class);
                    intent.putExtra("FiltroReceitas", "receitas");
                    intent.putExtra("ingrediente", receitas);
                    startActivity(intent);
                }
            }
        });
        BtnReceitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), terceitaActivityReceitas.class);
                intent.putExtra("FiltroReceitas","todas");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // A Activity está visível, você pode inicializar recursos aqui se necessário
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Aqui, caso o usuário tenha interações com o EditText ou outras animações, podemos iniciar
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pausar processos que não precisam ficar rodando enquanto a Activity não está em primeiro plano
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Libere qualquer recurso que não seja mais necessário (como animações, conexões de rede, etc)
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Aqui você pode limpar qualquer dado pesado ou tarefa que precisa ser finalizada
    }
}


