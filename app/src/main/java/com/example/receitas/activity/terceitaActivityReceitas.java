package com.example.receitas.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.receitas.R;
import com.example.receitas.model.Postagem;

import java.util.ArrayList;
import java.util.List;

public class terceitaActivityReceitas  extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Postagem> listaPostagem = new ArrayList<Postagem>();
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terceirareceitas);

        recyclerView = findViewById(R.id.recyclerView);

    }
}
