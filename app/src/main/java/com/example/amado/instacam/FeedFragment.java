package com.example.amado.instacam;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {


    private List<Photo> mPhotos;
    private FeedAdapter mFeedAdapter;

    public FeedFragment() {
        mPhotos = new ArrayList<Photo>();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_feed, container, false);
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.feed_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFeedAdapter = new FeedAdapter(getActivity(), mPhotos);
        recyclerView.setAdapter(mFeedAdapter);
        return v;
    }

        public void addPhoto(Photo photo){
            mPhotos.add(0, photo);
            mFeedAdapter.notifyDataSetChanged();
        }
}
