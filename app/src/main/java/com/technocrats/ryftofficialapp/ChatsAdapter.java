package com.technocrats.ryftofficialapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatViewHolder> {
    List<String> messages;
    List<String> userNames;
    ChatActivity chatItem;

    public ChatsAdapter(ChatActivity chatItem,List<String> messages,List<String> userNames)
    {   this.userNames=userNames;
        this.messages=messages;
        this.chatItem=chatItem;
    }
    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list, parent, false);
        ChatViewHolder chatViewHolder = new ChatViewHolder(itemView);

        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.mName.setText(userNames.get(position));
        holder.mMessage.setText(messages.get(position));
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView mName,mMessage;
        View mView;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            mName=itemView.findViewById(R.id.chatFromNameTextView);
            mMessage=itemView.findViewById(R.id.messageTextView);

        }
    }
}
