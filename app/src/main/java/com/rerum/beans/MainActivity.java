package com.rerum.beans;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity implements OnTabSelectListener,
        FragmentManager.OnBackStackChangedListener, ProfileFragment.OnFragmentInteractionListener,
        ProfileFavoritesFragment.OnFavoriteSelectedListener{

    private BottomBar bottomBar;
    private boolean isMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (findViewById(R.id.container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            /*// Create a new Fragment to be placed in the activity layout
            Fragment mainFragment= new RecentPostsFragment();
            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            mainFragment.setArguments(getIntent().getExtras());

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Add the fragment to the 'fragment_container' FrameLayout
            transaction.add(R.id.container, mainFragment, "main");

            transaction.commit();
            isMainFragment = true;*/
            Log.d("BACK", ""+getSupportFragmentManager().getBackStackEntryCount());
        }
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
//            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    int id1 = 0;
    int id2 = 0;
    @Override
    public void onTabSelected(@IdRes int tabId) {
        if (tabId == R.id.tab_search) {
            // The tab with id R.id.tab_favorites was selected,
            // change your content accordingly.

            // Check that the activity is using the layout version with
            // the fragment_container FrameLayout
            if (findViewById(R.id.container) != null) {

                /*MyTopPostsFragment fragment = new MyTopPostsFragment();
                Bundle args = new Bundle();
                fragment.setArguments(args);*/
                /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                getSupportFragmentManager().popBackStackImmediate();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.replace(R.id.container, fragment, "MY_TOP_POST_FRAGMENT");
                // Commit the transaction
                transaction.commit();
                isMainFragment = false;*/
            }
        }
        if (tabId == R.id.tab_profile) {
            // The tab with id R.id.tab_favorites was selected,
            // change your content accordingly.

            // Check that the activity is using the layout version with
            // the fragment_container FrameLayout
            if (findViewById(R.id.container) != null) {

                ProfileFragment fragment = new ProfileFragment();
                Bundle args = new Bundle();
                fragment.setArguments(args);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                getSupportFragmentManager().popBackStackImmediate();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.replace(R.id.container, fragment, "PROFILE_FRAGMENT");
                // Commit the transaction
                transaction.commit();
                isMainFragment = false;
            }
        }
    }

    @Override
    public void onBackStackChanged() {
        Log.d("BACK", ""+getSupportFragmentManager().getBackStackEntryCount());

    }

    @Override
    public void onBackPressed() {
        Log.d("BACK", "Back Pressed");
        if(isMainFragment ){
            Log.d("BACK", "Pause");
            super.onPause();
        }
        bottomBar.selectTabAtPosition(0);

        isMainFragment = true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
