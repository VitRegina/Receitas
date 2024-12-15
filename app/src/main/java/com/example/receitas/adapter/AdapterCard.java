package com.example.receitas.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.receitas.R;
import com.example.receitas.activity.quartaActivityReceitas;
import com.example.receitas.model.Postagem;

import java.util.ArrayList;
import java.util.List;

public class AdapterCard extends RecyclerView.Adapter<AdapterCard.MyViewHolder> {

    private List<Postagem> listaPostagem;
    public AdapterCard(List<Postagem> lista){
        this.listaPostagem = lista;
    }
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.postagem_detalhe, parent, false);

        return new MyViewHolder(itemLista);
    }
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Postagem postagem = listaPostagem.get(position);

        holder.textTitulo.setText(postagem.getReceita());
        holder.textResumo.setText(postagem.getResumo());
        Glide.with(holder.imagePostagem.getContext())
                .load(postagem.getLinkImagem())
                .into(holder.imagePostagem);
        Log.d("AdapterCard", "Lista de postagens: " + listaPostagem.size());


        holder.btnAcessar.setOnClickListener(v -> {

            Intent intent = new Intent(holder.itemView.getContext(), quartaActivityReceitas.class);
            intent.putExtra("id", postagem.getId());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.itemView.getContext().startActivity(intent);
        });
    }
    public int getItemCount(){
        return listaPostagem.size();
    }

    public void updateList(List<Postagem> novaLista) {
        Log.d("AdapterCard", "Atualizando lista com " + novaLista.size() + " itens");
        listaPostagem.clear();
        listaPostagem.addAll(novaLista);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textTitulo;
        private TextView textResumo;
        private ImageView imagePostagem;
        private Button btnAcessar;

        public MyViewHolder (@NonNull View itemView){
            super(itemView);
            this.textTitulo = itemView.findViewById(R.id.textTitulo);
            this.textResumo = itemView.findViewById(R.id.textResumo);
            this.imagePostagem = itemView.findViewById(R.id.imgreceita);
            this.btnAcessar = itemView.findViewById(R.id.BtnAcessar);
        }
        }
    public void filtrarPostagens(String query) {
        List<Postagem> listaFiltrada = new ArrayList<>();
        for (Postagem postagem : listaPostagem) {
            if (postagem.getReceita().toLowerCase().contains(query.toLowerCase())) {
                listaFiltrada.add(postagem);
            }
        }
        updateList(listaFiltrada);
    }
    }


