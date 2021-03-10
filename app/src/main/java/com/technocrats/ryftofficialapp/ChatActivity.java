package com.technocrats.ryftofficialapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ChatsAdapter chatsAdapter;
    List<String> messageList=new ArrayList<>();
    List<String> userNamesList=new ArrayList<>();
    ImageButton sendMessage;
    EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent=getIntent();
        final String key=intent.getStringExtra("key");
        FirebaseDatabase.getInstance().getReference().child("chat groups")
                .child(key).child("messages")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        String message,user;

                        try
                        {
                            message=snapshot.child("message").getValue().toString();
                        } catch (Exception e)
                        {
                            message="";
                            e.printStackTrace();
                        }
                        try
                        {
                            user=snapshot.child("user name").getValue().toString();
                        } catch(Exception e)
                        {
                            user="";
                            e.printStackTrace();
                        }
                        messageList.add(message);
                        userNamesList.add(user);
                        chatsAdapter.notifyDataSetChanged();
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
        sendMessage=findViewById(R.id.chat_send_btn);
        setProdItemRecycler(messageList);
        messageEditText=findViewById(R.id.chat_message_view);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempMessage=messageEditText.getText().toString();
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                String userName="";
                String userId="";
                if (user != null) {
                    userName=user.getDisplayName();
                    userId=user.getUid();
                }
                else
                    {
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                if (userName != null && !tempMessage.equals("") && !userName.equals("")) {
                    HashMap<String,String> message=new HashMap<>();
                    message.put("message",tempMessage);
                    message.put("user name",userName);
                    message.put("user id",userId);
                    FirebaseDatabase.getInstance().getReference().child("chat groups")
                            .child(key).child("messages").push()
                            .setValue(message);
                    Toast.makeText(getApplicationContext(),"Message sent",Toast.LENGTH_SHORT).show();

                    messageList.add(tempMessage);
                    userNamesList.add(userName);
                    chatsAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void setProdItemRecycler(List<String> messageList) {

        mRecyclerView= findViewById(R.id.messages_list);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        chatsAdapter = new ChatsAdapter(this, messageList,userNamesList);
        mRecyclerView.setAdapter(chatsAdapter);
    }
}