package tso.foodandbeverages;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import tso.foodandbeverages.SubjectAdapter;
import tso.foodandbeverages.SubjectModel;

public class MainActivity extends AppCompatActivity {

    ArrayList<SubjectModel> list;
    SubjectAdapter adapter;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu;
    View header;
    AdView mAdView;

    private InterstitialAd mInterstitialAd;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recycleSubject);
        menu = findViewById(R.id.menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navView);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        new Handler().postDelayed(new Runnable() {
            private InterstitialAd mInterstitialAd;

            @Override
            public void run() {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }


            }
        }, 2000);


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        list = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        list.add(new SubjectModel("About"));
        list.add(new SubjectModel("About"));
        list.add(new SubjectModel("Module-1 (Unit-1)"));
        list.add(new SubjectModel("Module-1 (Unit-2)"));
        list.add(new SubjectModel("Module-1 (Unit-3)"));
        list.add(new SubjectModel("Module-1 (Unit-3)"));
        list.add(new SubjectModel("Module-1 (Unit-4)"));
        list.add(new SubjectModel("Module-2 (Unit-1)"));
        list.add(new SubjectModel("Module-2 (Unit-2)"));
        list.add(new SubjectModel("Module-2 (Unit-2)"));
        list.add(new SubjectModel("Module-2 (Unit-3)"));
        list.add(new SubjectModel("Module-2 (Unit-4)"));
        list.add(new SubjectModel("Module-3 (Unit-1)"));
        list.add(new SubjectModel("Module-3 (Unit-1)"));
        list.add(new SubjectModel("Module-3 (Unit-2)"));
        list.add(new SubjectModel("Module-3 (Unit-3)"));
        list.add(new SubjectModel("Module-3 (Unit-4)"));
        list.add(new SubjectModel("Module-3 (Unit-4)"));
        list.add(new SubjectModel("Module-4 (Unit-1)"));

        list.add(new SubjectModel("About"));



        adapter = new SubjectAdapter(this, list);

        // Edited

        recyclerView.setAdapter(adapter);

        header = navigationView.getHeaderView( 0);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.rate:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=tso.foodandbeverages")));
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;

                    case R.id.home:
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;

                    case R.id.share:
                        String shareBody= "Hey, i am using Best Computer TextBook App. CLICK THE LINK TO DOWNLOAD:https://play.google.com/store/apps/details?id=tso.foodandbeverages ";
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT,shareBody);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);


                }

                return true;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }


    @Override
    public void onBackPressed() {
        loadInterstitial();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }


    private void loadInterstitial() {

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, "ca-app-pub-9163926251753325/4314352129", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });



    }

}