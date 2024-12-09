package com.example.receitas.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostagemService {

    @GET("/receitas/todas")
    Call<List<Postagem>> getTodasReceitas();

    @GET("/receitas/{id}")
    Call<List<Postagem>> getReceitaPorId(@Path("id") String id);

    @GET("/ingredientes")
    Call<List<Postagem>> getTodosIngredientes();

    @GET("/ingredientes/{id}")
    Call<List<Postagem>> getReceitasPorIngrediente(@Path("id") int id);
}
