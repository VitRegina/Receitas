package com.example.receitas.model;

import android.widget.ImageView;

public class Postagem {
    String titulo;
    String resumo;
    int imgreceita;
    public Postagem(String titulo, String resumo) {
        this.titulo = titulo;
        this.resumo = resumo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public int getImgreceita() {
        return imgreceita;
    }

    public void setImgreceita(int imgreceita) {
        this.imgreceita = imgreceita;
    }


}
