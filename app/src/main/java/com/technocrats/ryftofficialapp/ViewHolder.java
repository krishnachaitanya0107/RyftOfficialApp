package com.technocrats.ryftofficialapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView mTitle, TechUsed1,TechUsed2,description;
    ImageView mProjectImg;
    Button mSeeDetails;
    View mView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView=itemView;

        mTitle=itemView.findViewById(R.id.ProjectTitle);
        TechUsed1 =itemView.findViewById(R.id.techUsed1);
        TechUsed2=itemView.findViewById(R.id.techUsed2);
        description=itemView.findViewById(R.id.longDescription);
        mProjectImg=itemView.findViewById(R.id.projectImg);

        mSeeDetails=itemView.findViewById(R.id.seeDetails);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
    }
    private com.technocrats.ryftofficialapp.ViewHolder.ClickListener mClickListener;
    public interface  ClickListener{
        void onItemClick();

        void onItemClick(View view, int position);
    }
    public void setOnClickListener(com.technocrats.ryftofficialapp.ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }

}
