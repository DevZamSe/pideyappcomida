package com.devzamse.appoficial.InicioNavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Contacts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devzamse.appoficial.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class InicioNavigation extends Fragment {

    private RecyclerView myContactsList;
    private DatabaseReference ContactsRef;

    public InicioNavigation() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ContactsView = inflater.inflate(R.layout.fragment_inicio_navigation, container, false);

        myContactsList = ContactsView.findViewById(R.id.contacts_list);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        myContactsList.setLayoutManager(linearLayoutManager);

        ContactsRef = FirebaseDatabase.getInstance().getReference().child("pideyapp").child("almuerzo");

        return ContactsView;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Almuerzos>().setQuery(ContactsRef, Almuerzos.class).build();
        FirebaseRecyclerAdapter<Contacts, ContactsViewHolder> adapter = new FirebaseRecyclerAdapter<Contacts, ContactsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ContactsViewHolder holder, int position, @NonNull Contacts contacts) {
                String userIDs = getRef(position).getKey();
                ContactsRef.child(userIDs).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("image")){
                            String image = dataSnapshot.child("image").getValue().toString();
                            String info = dataSnapshot.child("info").getValue().toString();
                            String nombre = dataSnapshot.child("nombre").getValue().toString();

                            holder.nombre.setText(nombre);
                            holder.porciones.setText(info);
                            Picasso.get().load(image).placeholder(R.drawable.comida).into(holder.imagen);
                        } else {
                            String info = dataSnapshot.child("info").getValue().toString();
                            String nombre = dataSnapshot.child("nombre").getValue().toString();

                            holder.nombre.setText(nombre);
                            holder.porciones.setText(info);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }

            @NonNull
            @Override
            public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popular,viewGroup,false);
                ContactsViewHolder viewHolder = new ContactsViewHolder(view);
                return viewHolder;
            }
        };
        myContactsList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        TextView porciones;
        ImageView imagen;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombre);
            porciones = itemView.findViewById(R.id.porciones);
            imagen = itemView.findViewById(R.id.imagen);
        }
    }
}