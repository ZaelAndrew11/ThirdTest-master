package com.example.paulapariselias.thirdtest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulapariselias.thirdtest.R;
import com.example.paulapariselias.thirdtest.data.Nodes;
import com.example.paulapariselias.thirdtest.models.Pokemon;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by paulapariselias on 21-09-17.
 */

public class PokemonsAdapter extends FirebaseRecyclerAdapter<Pokemon, PokemonsAdapter.PokemonsHolder> {

    private Context context;
    private Pokemon pokemon;
    private String key;

    public PokemonsAdapter(DatabaseReference reference, Context context) {
        super(Pokemon.class, R.layout.list_item_pokemons, PokemonsHolder.class, reference);
        this.context = context;
    }


    @Override
    protected void populateViewHolder(final PokemonsHolder viewHolder, Pokemon model, int position) {
        TextView textView = viewHolder.name;
        textView.setText(model.getName());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemon = getItem(viewHolder.getAdapterPosition());
                key = pokemon.getName().replace(" ", "").toLowerCase();
                DatabaseReference dbref = new Nodes().favorites().child(key);
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Pokemon pm = dataSnapshot.getValue(Pokemon.class);
                        if (pm == null) {
                            new Nodes().favorites().child(key).setValue(pokemon);
                            Toast.makeText(context, "Pokemon Añadido a Favoritos", Toast.LENGTH_SHORT).show();
                        } else {
                            new Nodes().favorites().child(key).removeValue();
                            Toast.makeText(context, "Pokemon Removido de Favoritos", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    static class PokemonsHolder extends RecyclerView.ViewHolder {

        private TextView name;


        public PokemonsHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.pokemonsTv);
        }
    }
}

