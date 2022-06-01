package com.example.m2trabalho2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Activity actvity;
    ArrayList <Contatos> arrayList;


    public MainAdapter(Activity actvity, ArrayList<Contatos> arrayList){

        this.actvity = actvity;
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nome,parent, false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Contatos contato = arrayList.get(position);

        holder.nome.setText(contato.getNome());
        holder.telefone.setText(contato.getTelefone());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nome, telefone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nomeContato);
            telefone = itemView.findViewById(R.id.telefone);

        }
    }
}
