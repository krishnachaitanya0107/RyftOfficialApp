package com.technocrats.ryftofficialapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class FeedFragment extends Fragment {
    List<Model> ModelList=new ArrayList<>();
    RecyclerView mRecyclerView;
    CustomAdapter adapter;
    View view;
    String category;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_feed, container, false);
        context=getContext();
        setProdItemRecycler(ModelList);
        try
        {
            SharedPreferences preferences=context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
            category=preferences.getString("filter",null);
        } catch (Exception e)
        {
            category=null;
        }

        FirebaseDatabase.getInstance().getReference().child("posts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String tempCategory;
                try{
                    tempCategory=snapshot.child("category").getValue().toString();
                }
                catch (Exception e)
                {   tempCategory="";
                    e.printStackTrace();
                }
                if(category==null)
                { createFeedItem(snapshot); }
                else if(tempCategory.equals(category))
                    { createFeedItem(snapshot); }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor=context.getSharedPreferences("preferences", Context.MODE_PRIVATE).edit();
        editor.remove("filter");
        editor.apply();
    }

    private void setProdItemRecycler(List<Model> productsList){

        mRecyclerView= view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new CustomAdapter(this, productsList);
        mRecyclerView.setAdapter(adapter);

    }

    private void createFeedItem(DataSnapshot snapshot)
    {
        String techUsed1,techUsed2,title,projectImageUrl,Description,techUsed3,techUsed4,step1
                ,step2,step3,step4,projectId;
        try
        {
            projectId=snapshot.child("Id").getValue().toString();
        } catch (Exception e)
        {
            projectId="";
            e.printStackTrace();
        }
        try
        {
            techUsed1=snapshot.child("tech used 1").getValue().toString();
        } catch(Exception e)
        {
            techUsed1="";
        }
        try
        {
            techUsed2=snapshot.child("tech used 2").getValue().toString();
        } catch(Exception e)
        {
            techUsed2="";
        }
        try
        {
            techUsed3=snapshot.child("tech used 3").getValue().toString();
        } catch(Exception e)
        {
            techUsed3="";
        }
        try
        {
            techUsed4=snapshot.child("tech used 4").getValue().toString();
        } catch(Exception e)
        {
            techUsed4="";
        }
        try
        {
            title=snapshot.child("title").getValue().toString();
        } catch(Exception e)
        {
            title="";
        }
        try
        {
            Description=snapshot.child("description").getValue().toString();
        } catch(Exception e)
        {
            Description="";
        }
        try
        {
            step1=snapshot.child("step 1").getValue().toString();
        } catch(Exception e)
        {
            step1="";
        }
        try
        {
            step2=snapshot.child("step 2").getValue().toString();
        } catch(Exception e)
        {
            step2="";
        }
        try
        {
            step3=snapshot.child("step 3").getValue().toString();
        } catch(Exception e)
        {
            step3="";
        }
        try
        {
            step4=snapshot.child("step 4").getValue().toString();
        } catch(Exception e)
        {
            step4="";
        }
        try
        {
            projectImageUrl=snapshot.child("projectImageUrl").getValue().toString();
        } catch(Exception e)
        {
            projectImageUrl="";
        }

        Model model=new Model(techUsed1,techUsed2,title,projectImageUrl,Description,
                techUsed3,techUsed4,step1,step2,step3,step4,projectId);
        ModelList.add(model);
        adapter.notifyDataSetChanged();
    }
}