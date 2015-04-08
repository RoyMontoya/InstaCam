package com.example.amado.instacam;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment
        TextView fullName = (TextView)v.findViewById(R.id.profile_name);
        TextView birthday = (TextView)v.findViewById(R.id.profile_birthday);
        ImageView profileImage= (ImageView)v.findViewById(R.id.avatar_profile);
        GridView gridCollection = (GridView)v.findViewById(R.id.grid_collection);

        User user = User.getCurrentUser();

        fullName.setText(user.getFirstName()+" "+user.getLastName());
        String date = user.getBirthday().toString();
        String trim =date.substring(3,10);
        birthday.setText("Birthday: " + trim);
        Picasso.with(getActivity()).load(user.getAvatarURL()).into(profileImage);

        if(GridAdapter.sNewPhotos != null) {
            Log.d("ProfileFragment", "las fotos no eran null");
            GridAdapter mGridAdapter = new GridAdapter(getActivity());
            gridCollection.setAdapter(mGridAdapter);
        }


        return v;
    }


}
