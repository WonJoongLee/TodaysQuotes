package com.example.todaysquotes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.style.QuoteSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todaysquotes.card.CardItem;
import com.example.todaysquotes.viewCount.ViewItem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    public int love_vc;//love page view count
    private DatabaseReference databaseReference;
    private DatabaseReference refcount_love, refcount_study, refcount_success, refcount_friendship, refcount_breakup, refcount_effort;
    //long childrenCount = snapshot.
    TextView ranquote, viewcount_love, viewcount_study, viewcount_success, viewcount_friendship, viewcount_breakup, viewcount_effort;
    LinearLayout love, study, success, friendship, breakup, effort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v2);

        love = findViewById(R.id.main_love);
        study = findViewById(R.id.main_study);
        success = findViewById(R.id.main_success);
        friendship = findViewById(R.id.main_friendship);
        breakup = findViewById(R.id.main_breakup);
        effort = findViewById(R.id.main_effort);
        firebaseDatabase = FirebaseDatabase.getInstance();
        viewcount_love = findViewById(R.id.viewcount_love);
        viewcount_study = findViewById(R.id.viewcount_study);
        viewcount_success = findViewById(R.id.viewcount_success);
        viewcount_friendship=findViewById(R.id.viewcount_friendship);
        viewcount_breakup=findViewById(R.id.viewcount_breakup);
        viewcount_effort=findViewById(R.id.viewcount_effort);

        ranquote = findViewById(R.id.ranquote);

        databaseReference = firebaseDatabase.getReference("Quotes").child("All");//add
        //refcount_love = firebaseDatabase.getReference("Count");
        refcount_love = firebaseDatabase.getReference("Count").child("Love");
        refcount_study = firebaseDatabase.getReference("Count").child("Study");
        refcount_success = firebaseDatabase.getReference("Count").child("Success");
        refcount_breakup = firebaseDatabase.getReference("Count").child("Breakup");
        refcount_effort = firebaseDatabase.getReference("Count").child("Effort");
        refcount_friendship = firebaseDatabase.getReference("Count").child("Friendship");

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Random random = new Random();
                int index = random.nextInt((int)dataSnapshot.getChildrenCount());
                int count = 0;
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if(count == index){
                        CardItem cardItem1 = dataSnapshot1.getValue(CardItem.class);
                        ranquote.setText((String)cardItem1.getQuote());
                        Log.d("@@@@@Gotquote", cardItem1.toString());
                        return;
                    }
                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        show_viewcount();



        if(firstStart){
            showStartDialog();
        }

        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refcount_love.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            Long a = dataSnapshot1.getValue(Long.class);
                            a++;
                            refcount_love.child("vc").setValue(a);
                            //ViewItem viewItem = dataSnapshot1.getValue(ViewItem.class);
                            //viewcount.setText(String.valueOf(a));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(MainActivity.this, LoveActivity.class);
                startActivity(intent);
            }
        });

        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refcount_study.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            Long a = dataSnapshot1.getValue(Long.class);
                            a++;
                            refcount_study.child("vc").setValue(a);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent intent = new Intent(MainActivity.this, StudyActivity.class);
                startActivity(intent);
            }
        });

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refcount_success.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            Long a = dataSnapshot1.getValue(Long.class);
                            a++;
                            refcount_success.child("vc").setValue(a);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                startActivity(intent);
            }
        });

        friendship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refcount_friendship.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            Long a = dataSnapshot1.getValue(Long.class);
                            a++;
                            refcount_friendship.child("vc").setValue(a);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(MainActivity.this, FriendshipActivity.class);
                startActivity(intent);
            }
        });

        breakup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refcount_breakup.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            Long a = dataSnapshot1.getValue(Long.class);
                            a++;
                            refcount_breakup.child("vc").setValue(a);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(MainActivity.this, BreakupActivity.class);
                startActivity(intent);
            }
        });

        effort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refcount_effort.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            Long a = dataSnapshot1.getValue(Long.class);
                            a++;
                            refcount_effort.child("vc").setValue(a);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(MainActivity.this, EffortActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showStartDialog(){
        setContentView(R.layout.activity_firstrun);

        Button button_start = findViewById(R.id.firstrun_startbutton);

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor  = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    public void show_viewcount(){
        refcount_love.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Long a = dataSnapshot1.getValue(Long.class);
                    viewcount_love.setText(a+"");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refcount_study.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Long a = dataSnapshot1.getValue(Long.class);
                    viewcount_study.setText(a+"");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refcount_success.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Long a = dataSnapshot1.getValue(Long.class);
                    viewcount_success.setText(a+"");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

      refcount_friendship.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Long a = dataSnapshot1.getValue(Long.class);
                    viewcount_friendship.setText(a+"");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refcount_breakup.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Long a = dataSnapshot1.getValue(Long.class);
                    viewcount_breakup.setText(a+"");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        refcount_effort.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Long a = dataSnapshot1.getValue(Long.class);
                    viewcount_effort.setText(a+"");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
