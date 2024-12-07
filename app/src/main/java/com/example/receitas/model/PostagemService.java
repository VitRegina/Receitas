package com.example.receitas.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostagemService {

    @GET("/receitas/todas")
    Call<List<Postagem>> getTodasReceitas();

    @GET("/receitas/id")
    Call<List<Postagem>> getReceitaPorId(@Path("tipo") String tipo);

    @GET ("/ingredientes")
    Call<Postagem> getTodosIngredientes();

    @GET("/ingredientes/{id}")
    Call<List<Postagem>> getReceitasPorIngrediente(@Path("tipo") int id);


}
