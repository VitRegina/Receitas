package com.example.receitas.activity;

import android.content.Intent;
import android.os.Bundle;

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

public class terceitaActivityReceitas  extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterCard adapterCard;
    private List<Postagem> listaPostagem = new ArrayList<Postagem>();
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terceirareceitas);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapterCard = new AdapterCard(listaPostagem);
        recyclerView.setAdapter(adapterCard);


        Intent intent = getIntent();
        String FiltroReceitas = intent.getStringExtra("FiltroReceitas");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-receitas-pi.vercel.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

                PostagemService api = retrofit.create(PostagemService.class);

                if("ingrediente".equals(FiltroReceitas)){
                    String ingrediente = intent.getStringExtra("Receitas");
                    int idIngrediente;

                    idIngrediente = Integer.parseInt(ingrediente);
                    api.getReceitasPorIngrediente(idIngrediente).enqueue(new Callback<List<Postagem>>() {
                        @Override
                        public void onResponse(Call<List<Postagem>> call, Response<List<Postagem>> response) {
                            if (response.isSuccessful() && response.body() != null){
                                adapterCard.updateList(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Postagem>> call, Throwable t) {

                        }
                    });

                }else if("todas".equals(FiltroReceitas)){
                    api.getTodasReceitas().enqueue(new Callback<List<Postagem>>() {
                        @Override
                        public void onResponse(Call<List<Postagem>> call, Response<List<Postagem>> response) {
                            if(response.isSuccessful() && response.body() != null){
                                adapterCard.updateList(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Postagem>> call, Throwable t) {

                        }
                    });
                }
    }

}
