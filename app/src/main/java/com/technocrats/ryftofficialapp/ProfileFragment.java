package com.technocrats.ryftofficialapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class ProfileFragment extends Fragment {
    private FirebaseAuth mauth;
    TextView nameTextView,emailTextView;
    ImageView photoImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mauth=FirebaseAuth.getInstance();
        FirebaseUser user=mauth.getCurrentUser();
        nameTextView=getView().findViewById(R.id.nameTextView);
        emailTextView=getView().findViewById(R.id.emailTextView);
        photoImageView=getView().findViewById(R.id.imageView2);
        if(user==null)
        {
            Intent intent=new Intent(getContext(),MainActivity.class);
            intent.putExtra("fromActivity","profileFragment");
            startActivity(intent);
        }
        else
            {   Bitmap bitmap;
                CustomAdapter.ImageDownloader task= new CustomAdapter.ImageDownloader();
                try {
                    bitmap=task.execute(user.getPhotoUrl().toString()).get();
                } catch (ExecutionException | InterruptedException e) {
                    bitmap=null;
                    e.printStackTrace();
                }
                nameTextView.setText(user.getDisplayName());
                emailTextView.setText(user.getEmail());
                if(bitmap!=null)
                {
                photoImageView.setImageBitmap(bitmap);
            }

            }
    }

    public static class ImageDownloader extends AsyncTask<String,Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try
            {
                URL url=new URL(urls[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                return BitmapFactory.decodeStream(inputStream);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }

        }

    }

}