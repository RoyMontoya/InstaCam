package com.example.amado.instacam;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class NewPhotoActivity extends ActionBarActivity implements NewPhotoFragment.Contract {
    public static final int CAMARA = 666;
    private Photo mPhoto;
    public static final String PHOTO_EXTRA= "PHOTO_EXTRA";
    private NewPhotoFragment mNewPhotoFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_photo);
        mNewPhotoFragment = (NewPhotoFragment)getFragmentManager().findFragmentById(R.id.new_photo_fragment_container);
        if(mNewPhotoFragment == null) {
            mNewPhotoFragment = new NewPhotoFragment();
            getFragmentManager().beginTransaction().add(R.id.new_photo_fragment_container, mNewPhotoFragment)
                    .commit();
        }

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_photo, menu);
        return true;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode== CAMARA && resultCode == RESULT_OK){
            mNewPhotoFragment.updatePhoto(mPhoto);
        }
    }


    @Override
    public void launchCamara() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mPhoto = new Photo();
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPhoto.getFile()));
        startActivityForResult(i,CAMARA);
    }

    @Override
    public void finishedPhoto(Photo photo) {

        Intent i = new Intent();
        i.putExtra(PHOTO_EXTRA, photo);
        setResult(RESULT_OK, i);
        finish();
    }
}
