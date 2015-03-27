package com.example.amado.instacam;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class NewPhotoActivity extends ActionBarActivity {
    public static final int CAMARA = 666;
    private Photo mPhoto;
    private ImageView mPreview;
    private EditText mCaption;
    public static final String PHOTO_EXTRA= "PHOTO_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_photo);
        mPreview = (ImageView)findViewById(R.id.photo_preview);
        mCaption = (EditText)findViewById(R.id.new_photo_caption);
        Button saveButton = (Button)findViewById(R.id.save_new_photo);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mPhoto.setCaption(mCaption.getText().toString());
             Intent i = new Intent();
             i.putExtra(PHOTO_EXTRA, mPhoto);
             setResult(RESULT_OK, i);
                finish();
            }
        });
        LaunchCamera();
    }

    private void LaunchCamera(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mPhoto = new Photo();
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPhoto.getFile()));
        startActivityForResult(i,CAMARA);
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
            Picasso.with(this).load(mPhoto.getFile()).into(mPreview);
        }
    }
}
