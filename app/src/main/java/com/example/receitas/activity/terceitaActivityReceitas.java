package com.example.receitas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.receitas.R;
import com.example.receitas.adapter.AdapterCard;
import com.example.receitas.model.Postagem;
import com.example.receitas.model.PostagemService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class terceitaActivityReceitas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterCard adapterCard;
    private List<Postagem> listaPostagem = new ArrayList<>();
    private String FiltroReceitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terceirareceitas);

        recyclerView = findViewById(R.id.recyclerView);
        adapterCard = new AdapterCard(listaPostagem);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterCard);

        Intent intent = getIntent();
        FiltroReceitas = intent.getStringExtra("FiltroReceitas");
        String ingrediente = intent.getStringExtra("ingrediente");

        // Adicionando log para verificar os dados
        Log.d("TerceitaActivity", "FiltroReceitas: " + FiltroReceitas);
        Log.d("TerceitaActivity", "Ingrediente: " + ingrediente);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-receitas-pi.vercel.app/") // A URL da API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostagemService api = retrofit.create(PostagemService.class);

        if ("ingrediente".equals(FiltroReceitas)) {
            // Buscar todas as receitas e depois filtrar localmente
            api.getTodasReceitas().enqueue(new Callback<List<Postagem>>() {
                @Override
                public void onResponse(Call<List<Postagem>> call, Response<List<Postagem>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("API_RESPONSE", "Postagens recebidas: " + response.body().size());
                        List<Postagem> postagensFiltradas = filtrarPostagensPorIngrediente(response.body(), ingrediente);
                        Log.d("FILTER_LOG", "Postagens filtradas: " + postagensFiltradas.size());
                        if (postagensFiltradas.isEmpty()) {
                            Log.d("API_RESPONSE", "Nenhuma postagem encontrada com o ingrediente: " + ingrediente);
                        }
                        adapterCard.updateList(postagensFiltradas);
                        Log.d("ERROR", "AdapterCard is null, cannot update the list.");
                    } else {
                        Log.d("API_RESPONSE", "Resposta vazia ou erro na API");
                        adapterCard.updateList(new ArrayList<>()); // Atualiza com lista vazia em caso de erro
                        Log.d("ERROR", "AdapterCard is null, cannot update the list.");
                    }
                }

                @Override
                public void onFailure(Call<List<Postagem>> call, Throwable t) {
                    Log.d("API_RESPONSE", "Falha na requisição: " + t.getMessage());
                    adapterCard.updateList(new ArrayList<>()); // Atualiza com lista vazia em caso de falha
                }
            });

        } else if ("todas".equals(FiltroReceitas)) {
            api.getTodasReceitas().enqueue(new Callback<List<Postagem>>() {
                @Override
                public void onResponse(Call<List<Postagem>> call, Response<List<Postagem>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("API_RESPONSE", "Postagens recebidas: " + response.body().size());
                        if (response.body().isEmpty()) {
                            Log.d("API_RESPONSE", "Nenhuma postagem encontrada.");
                        }
                        adapterCard.updateList(response.body());
                    } else {
                        Log.d("API_RESPONSE", "Resposta vazia ou erro na API");
                        adapterCard.updateList(new ArrayList<>()); // Atualiza com lista vazia em caso de erro
                    }
                }

                @Override
                public void onFailure(Call<List<Postagem>> call, Throwable t) {
                    Log.d("API_RESPONSE", "Falha na requisição: " + t.getMessage());
                    adapterCard.updateList(new ArrayList<>()); // Atualiza com lista vazia em caso de falha
                }
            });
        }
    }

    private List<Postagem> filtrarPostagensPorIngrediente(List<Postagem> postagens, String ingrediente) {
        List<Postagem> postagensFiltradas = new ArrayList<>();
        if (postagens != null && !postagens.isEmpty()) {
            for (Postagem postagem : postagens) {
                if (postagem.getIngredientesBase() != null && !postagem.getIngredientesBase().isEmpty()) {
                    for (Postagem.IngredienteBase ingredienteBase : postagem.getIngredientesBase()) {
                        if (ingredienteBase.getNomesIngrediente() != null && !ingredienteBase.getNomesIngrediente().isEmpty()) {
                            for (String nomeIngrediente : ingredienteBase.getNomesIngrediente()) {
                                if (nomeIngrediente != null && nomeIngrediente.trim().toLowerCase().contains(ingrediente.trim().toLowerCase())) {
                                    postagensFiltradas.add(postagem);
                                    break; // Adiciona uma vez e sai do loop
                                }
                            }
                        }
                    }
                }
            }
        }
        return postagensFiltradas;
    }




    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
