package com.technocrats.ryftofficialapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class CategoryFragment extends Fragment {
    CardView ml,blockChain,Ios,Game,web,android;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ml=getView().findViewById(R.id.mark);
        blockChain=getView().findViewById(R.id.teach);
        Ios=getView().findViewById(R.id.med);
        Game=getView().findViewById(R.id.eng);
        web=getView().findViewById(R.id.it);
        android=getView().findViewById(R.id.design);

        ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilter("ml");
            }
        });
        Ios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilter("ios");
            }
        });
        Game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilter("game");
            }
        });
        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilter("android");
            }
        });
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilter("web");
            }
        });
        blockChain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFilter("blockChain");
            }
        });
    }

    public void addFilter(String filter)
    {
        SharedPreferences.Editor editor= (SharedPreferences.Editor)
                getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE).edit();
        editor.putString("filter",filter);
        editor.apply();
        Toast.makeText(getContext(),"Filter applied successfully !!",Toast.LENGTH_SHORT).show();
    }
}