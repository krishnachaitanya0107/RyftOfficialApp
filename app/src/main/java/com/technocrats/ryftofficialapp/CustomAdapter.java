package com.technocrats.ryftofficialapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {
    FeedFragment feedItem;
    List<Model> modelList;
    Context context;

    public CustomAdapter(FeedFragment feedItem,List<Model> modelList)
    {
        this.feedItem=feedItem;
        this.modelList=modelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        context=itemView.getContext();
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick() {

            }

            @Override
            public void onItemClick(View view, int position) {

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Bitmap bitmap;
        ImageDownloader task= new ImageDownloader();
        try {
            bitmap=task.execute(modelList.get(position).getProjectImageUrl()).get();
        } catch (ExecutionException | InterruptedException e) {
            bitmap=null;
            e.printStackTrace();
        }
        holder.mTitle.setText(modelList.get(position).getTitle());
        holder.TechUsed1.setText(modelList.get(position).getTechUsed1());
        holder.TechUsed2.setText(modelList.get(position).getTechUsed2());
        String description=modelList.get(position).getDescription();
        if(description.length() > 20)
            holder.description.setText(String.format("%s...", description.substring(0, 9)));
        else
            holder.description.setText(description);
        holder.mProjectImg.setImageBitmap(bitmap);
        holder.mSeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ProjectDetailActivity.class);
                intent.putExtra("tech used 3",modelList.get(position).getTechUsed3());
                intent.putExtra("tech used 4",modelList.get(position).getTechUsed4());
                intent.putExtra("step 1",modelList.get(position).getStep1());
                intent.putExtra("step 2",modelList.get(position).getStep2());
                intent.putExtra("step 3",modelList.get(position).getStep3());
                intent.putExtra("step 4",modelList.get(position).getStep4());
                intent.putExtra("title",modelList.get(position).getTitle());
                intent.putExtra("tech used 1",modelList.get(position).getTechUsed1());
                intent.putExtra("tech used 2",modelList.get(position).getTechUsed2());
                intent.putExtra("description",modelList.get(position).getDescription());
                intent.putExtra("image url", modelList.get(position).getProjectImageUrl());
                intent.putExtra("projectId",modelList.get(position).getProjectId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
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
