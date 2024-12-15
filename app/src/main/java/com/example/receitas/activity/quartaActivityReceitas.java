package com.example.receitas.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.receitas.R;
import com.example.receitas.model.Postagem;
import com.example.receitas.model.PostagemService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class quartaActivityReceitas extends AppCompatActivity {
    private ImageView imgReceita;
    private TextView txtTituloReceita, txtResumoReceita, txtDetalhesReceita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quartareceita);

        // Referenciando os elementos do layout
        imgReceita = findViewById(R.id.imageView3);
        txtTituloReceita = findViewById(R.id.textTituloReceita);
        txtResumoReceita = findViewById(R.id.textResumoReceita);
        txtDetalhesReceita = findViewById(R.id.textDetalhesReceita);


        // Obtendo o ID da receita via Intent
        int receitaId = getIntent().getIntExtra("id", -1);

        if (receitaId != -1) {
            carregarReceita(receitaId);
        }
    }

    private void carregarReceita(int receitaId) {
        // Construindo o Retrofit diretamente sem a necessidade de getInstance()
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-receitas-pi.vercel.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Criando o serviço da API
        PostagemService apiService = retrofit.create(PostagemService.class);

        // Fazendo a chamada à API
        Call<Postagem> call = apiService.getReceitaPorId(receitaId);
        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Postagem postagem = response.body();


                    txtTituloReceita.setText(postagem.getReceita());


                    String modoPreparo = postagem.getmodo_preparo(); // Supondo que getModo_preparo retorna uma String com os passos


                    StringBuilder modoPreparoFormatted = new StringBuilder();
                    String[] passosArray = modoPreparo.split("\\d+\\. "); // Dividindo apenas com o número e o ponto


                    for (int i = 0; i < passosArray.length; i++) {
                        // Adicionando a numeração de volta
                        if (!passosArray[i].trim().isEmpty()) {
                            modoPreparoFormatted.append(i + 1).append(". ").append(passosArray[i].trim()).append("\n\n"); // Cada passo com uma quebra de linha
                        }
                    }
                    txtDetalhesReceita.setText(modoPreparoFormatted.toString());
                    String ingredientes = postagem.getIngredientes(); // Supondo que getIngredientes retorna uma String com todos os ingredientes
                    String[] ingredientesArray = ingredientes.split(","); // Dividindo os ingredientes por vírgulas (ajuste o separador conforme necessário)


                    StringBuilder ingredientesFormatted = new StringBuilder();
                    for (String ingrediente : ingredientesArray) {
                        ingredientesFormatted.append("• ").append(ingrediente.trim()).append("\n"); // Adiciona o bullet e remove espaços extras
                    }


                    txtResumoReceita.setText(ingredientesFormatted.toString());
                    // Carregar imagem com Glide
                    Glide.with(quartaActivityReceitas.this)
                            .load(postagem.getLinkImagem())
                            .into(imgReceita);
                } else {
                    Log.e("API_ERROR", "Erro na resposta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {
                Log.e("API_ERROR", "Erro na chamada à API", t);
            }
        });
    }
}