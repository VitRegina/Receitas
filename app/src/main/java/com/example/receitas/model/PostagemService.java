package com.example.receitas.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostagemService {

    @GET("receitas/{ingrediente}")
    Call<List<Postagem>> getReceitasPorIngrediente(@Path("ingrediente") String ingrediente);

    @GET("/receitas/todas")
    Call<List<Postagem>> getTodasReceitas();

    @GET("/receitas/{id}")
    Call<Postagem> getReceitaPorId(@Path("id") int id);

    @GET("/ingredientes")
    Call<List<Postagem>> getTodosIngredientes();

    @GET("/ingredientes/{id}")
    Call<List<Postagem>> getReceitasPorIngredienteId(@Path("id") int id);
}
