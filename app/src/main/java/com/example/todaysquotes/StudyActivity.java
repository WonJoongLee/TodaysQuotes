package com.example.todaysquotes;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.todaysquotes.card.CardItem;
import com.example.todaysquotes.card.ShadowTransformer;
import com.example.todaysquotes.cardview.CardFragmentPagerAdapter;
import com.example.todaysquotes.cardview.CardPagerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudyActivity extends AppCompatActivity {

    private CardPagerAdapter mCardAdapter;
    private ViewPager mViewPager;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference_friendship;


    private DatabaseReference mPostReference;

    private AdView mAdView;
    public int count=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);

        MobileAds.initialize(this, getString(R.string.admob_app_id));

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Quotes").child("Study");
        databaseReference_friendship = database.getReference("Quotes").child("Friendship");

        mCardAdapter = new CardPagerAdapter();
        mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, this));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    CardItem cardItem = dataSnapshot1.getValue(CardItem.class);
                    mCardAdapter.addCardItem(cardItem);
                    Log.d("CONTENTISOKAY", cardItem.toString());
                }
                mCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Database Error", String.valueOf(databaseError.toException()));
            }
        });



    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }


}
