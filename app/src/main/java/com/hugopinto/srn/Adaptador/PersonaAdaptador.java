package com.hugopinto.srn.Adaptador;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hugopinto.srn.R;
import com.hugopinto.srn.Datos.Persona;
import com.hugopinto.srn.DBHelper.DBHelper;

import java.util.ArrayList;

public abstract class PersonaAdaptador extends RecyclerView.Adapter<PersonaAdaptador.PersonaViewHolder> {
    private ArrayList<Persona> person;

    public PersonaAdaptador(ArrayList<Persona> person) {
        this.person = person;
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView carnet;
        TextView nota;
        TextView materia;
        TextView catedratico;
        Context cxt;




        public PersonaViewHolder(View itemView){
            super(itemView);
            cxt=itemView.getContext();
            cardview=itemView.findViewById(R.id.card_view);
            nota=itemView.findViewById(R.id.cardnota);
            carnet=itemView.findViewById(R.id.cardcarnet);
            catedratico=itemView.findViewById(R.id.cardcatedratico);
            materia = itemView.findViewById(R.id.cardmateria);



        }

    }


    @Override
    public PersonaViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return (new PersonaViewHolder(v));
    }

    @Override
    public void onBindViewHolder(PersonaViewHolder holder, final int position){
        holder.carnet.setText(person.get(position).getCarnet());
        holder.materia.setText(person.get(position).getMateria());
        holder.nota.setText(person.get(position).getNota());
        holder.catedratico.setText(person.get(position).getCatedratico());

    }



    @Override
    public int getItemCount(){
        return person.size();
    }

    public abstract void onVerClick(View v,int pos);
    public abstract void Contador(int cont);
}
