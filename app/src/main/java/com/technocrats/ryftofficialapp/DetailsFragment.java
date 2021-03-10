package com.technocrats.ryftofficialapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class DetailsFragment extends Fragment {
    TextView techUsed2TextView,techUsed1TextView,descriptionTextView,titleTextView;
    Intent intent;
    Button enrollNowButton;

    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        intent=getActivity().getIntent();
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        descriptionTextView=getView().findViewById(R.id.dlongDescription);
        final String projectId=intent.getExtras().getString("projectId");
        final String projectTitle=intent.getExtras().getString("title");
        titleTextView=getView().findViewById(R.id.dProjectTitle);
        techUsed1TextView=getView().findViewById(R.id.dtechUsed1);
        techUsed2TextView=getView().findViewById(R.id.dtechUsed2);
        imageView=getView().findViewById(R.id.dprojectImg);
        FirebaseAuth mauth=FirebaseAuth.getInstance();
        final FirebaseUser user=mauth.getCurrentUser();
        String userId = null;
        try
        {
            userId= user.getUid();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        enrollNowButton=getView().findViewById(R.id.enroll);
        final String finalUserId=userId;
        enrollNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null)
                {   final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

                    HashMap<String,String> enrollment=new HashMap<>();
                    enrollment.put(projectId, projectTitle);
                    reference.child("users")
                            .child(finalUserId)
                            .child("enrolled groups").setValue(enrollment);
                    Toast.makeText(getContext(),"enrolled successfully",Toast.LENGTH_SHORT).show();

                }
                else
                    {
                        Intent intent=new Intent(getContext(),MainActivity.class);
                        startActivity(intent);
                    }
            }
        });

        descriptionTextView.setText(intent.getExtras().getString("description"));
        titleTextView.setText(projectTitle);
        techUsed1TextView.setText(intent.getExtras().getString("tech used 1"));
        techUsed2TextView.setText(intent.getExtras().getString("tech used 2"));
        String imageUrl=intent.getExtras().getString("image url");
        Bitmap bitmap;
        CustomAdapter.ImageDownloader task= new CustomAdapter.ImageDownloader();
        try {
            bitmap=task.execute(imageUrl).get();
        } catch (ExecutionException |InterruptedException | NullPointerException e ) {
            bitmap=null;
            e.printStackTrace();
        }
        if(bitmap!=null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public static class ImageDownloader extends AsyncTask<String,Void,Bitmap>
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