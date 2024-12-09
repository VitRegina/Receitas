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

    }
    public void comecar(View View){
   /*     Toast.makeText(getApplicationContext(),"Indo para a próxima tela", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), segundaActivityEscolha.class);

        startActivity(intent); */
        try {
            Toast.makeText(getApplicationContext(), "Indo para a próxima tela", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), segundaActivityEscolha.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        // A Activity está visível, você pode inicializar recursos aqui se necessário
    }

    @Override
    protected void onResume() {
        super.onResume();
        // A Activity interage com o usuário, pode ser o momento de iniciar animações ou áudio, por exemplo
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Se estiver pausando animações ou processos, faça isso aqui
    }

    @Override
    protected void onStop() {
        super.onStop();
        // A Activity não está mais visível, libere recursos que não são mais necessários
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Aqui você pode liberar recursos pesados ou processos que devem ser encerrados
    }
}

