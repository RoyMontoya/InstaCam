package com.example.amado.instacam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Amado on 10/03/2015.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView mCaption;
        private final ImageView mPhotoView;
        private final ImageView mAvatar;
        private final TextView mUserName;

        public ViewHolder(View itemView) {
            super(itemView);
            mCaption = (TextView)itemView.findViewById(R.id.feed_item_caption);
            mPhotoView = (ImageView)itemView.findViewById(R.id.feed_item_photo);
            mAvatar = (ImageView)itemView.findViewById(R.id.feed_user_avatar);
            mUserName = (TextView)itemView.findViewById(R.id.feed_user_name);
        }
    }

    private List<Photo> mPhoto;
    private Context mContext;

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Photo photo = mPhoto.get(i);
        Picasso.with(mContext).load(photo.getFile()).into(viewHolder.mPhotoView);
        viewHolder.mCaption.setText(photo.getCaption());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mPhoto.size();
    }

    public FeedAdapter(Context context, List<Photo> photo) {
        mContext = context;
        mPhoto = photo;

    }
}

