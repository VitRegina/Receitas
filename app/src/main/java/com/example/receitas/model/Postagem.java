package com.example.receitas.model;

import java.util.List;

public class Postagem {
    private int id;
    private String receita;  // Título da receita
    private String ingredientes;  // Ingredientes como uma string
    public String modo_preparo;  // Modo de preparo
    private String link_imagem;  // URL da imagem
    private String tipo;  // Tipo da receita
    private List<IngredienteBase> ingredientesBase;  // Ingredientes detalhados


    public Postagem(int id, String receita, String ingredientes, String modo_preparo, String link_imagem, String tipo, List<IngredienteBase> ingredientesBase) {
        this.id = id;
        this.receita = receita;
        this.ingredientes = ingredientes;
        this.modo_preparo = modo_preparo;
        this.link_imagem = link_imagem;
        this.tipo = tipo;
        this.ingredientesBase = ingredientesBase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceita() {
        return receita;
    }

    public void setReceita(String receita) {
        this.receita = receita;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public  String getmodo_preparo() {
        return modo_preparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modo_preparo = modoPreparo;
    }
    public String getResumo() {
        if (modo_preparo == null) {
            return "";  // Retorna uma string vazia caso modoPreparo seja null
        }
        return modo_preparo.length() > 50 ? modo_preparo.substring(0, 50) + "..." : modo_preparo;
    }
    public String getLinkImagem() {
        return link_imagem;
    }

    public void setLinkImagem(String linkImagem) {
        this.link_imagem = linkImagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<IngredienteBase> getIngredientesBase() {
        return ingredientesBase;
    }

    public void setIngredientesBase(List<IngredienteBase> ingredientesBase) {
        this.ingredientesBase = ingredientesBase;
    }

    public static class IngredienteBase {
        private List<String> nomesIngrediente;  // Lista dos nomes dos ingredientes
        private int receitaId;

        // Getters e Setters
        public List<String> getNomesIngrediente() {
            return nomesIngrediente;
        }

        public void setNomesIngrediente(List<String> nomesIngrediente) {
            this.nomesIngrediente = nomesIngrediente;
        }

        public int getReceitaId() {
            return receitaId;
        }

        public void setReceitaId(int receitaId) {
            this.receitaId = receitaId;
        }

    }
}
