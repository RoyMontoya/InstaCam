package com.example.amado.instacam;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends ActionBarActivity implements MaterialTabListener, ShareActionProvider.OnShareTargetSelectedListener{

    public static final int NEW_PHOTO_REQUEST = 6;
    private Photo mPhoto;
    private FeedFragment mFeedFragment;
    private MaterialTabHost mTabs;
    private ProfileFragment mProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
         ImageButton cameraFAB = (ImageButton)findViewById(R.id.camara_fab);
        cameraFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i  = new Intent(MainActivity.this, NewPhotoActivity.class);
            startActivityForResult(i , NEW_PHOTO_REQUEST);
            }
        });

        mTabs = (MaterialTabHost)findViewById(R.id.tab_bar);
        mTabs.addTab(mTabs.newTab().setIcon(getResources().getDrawable(R.drawable.ic_social)).setTabListener(this));
        mTabs.addTab(mTabs.newTab().setIcon(getResources().getDrawable(R.drawable.ic_profile)).setTabListener(this));


        mFeedFragment = (FeedFragment)getFragmentManager().findFragmentById(R.id.feed_container);
        if(mFeedFragment == null){
            mFeedFragment = new FeedFragment();
            getFragmentManager().beginTransaction().add(R.id.feed_container, mFeedFragment)
                    .commit();
        }



    }


    @Override
    public void onTabSelected(MaterialTab materialTab) {
        int position = materialTab.getPosition();
        mTabs.setSelectedNavigationItem(position);
        Fragment fragment = null;
        switch (position){
            case 1:
                if(mProfileFragment == null){
                    mProfileFragment = new ProfileFragment();
                }
              fragment = mProfileFragment;
                break;
            case 0:
              fragment = mFeedFragment;
                break;

        }
        getFragmentManager().beginTransaction()
                .replace(R.id.feed_container, fragment).commit();
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }



    private void Capture() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode== NEW_PHOTO_REQUEST && resultCode == RESULT_OK){
            mPhoto =(Photo) data.getSerializableExtra(NewPhotoActivity.PHOTO_EXTRA);
            mFeedFragment.addPhoto(mPhoto);
        }
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onShareTargetSelected(ShareActionProvider shareActionProvider, Intent intent) {
        return false;
    }
}
