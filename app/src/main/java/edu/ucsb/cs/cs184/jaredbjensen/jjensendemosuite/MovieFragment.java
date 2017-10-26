package edu.ucsb.cs.cs184.jaredbjensen.jjensendemosuite;


import android.content.Context;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;


public class MovieFragment extends Fragment {

    VideoView videoView;
    View rootView;

    public MovieFragment() {}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = getActivity();
        videoView = (VideoView) rootView.findViewById(R.id.video_view);
        Uri video = Uri.parse("android.resource://" + activity.getPackageName() + "/" + R.raw.bigbuck);

        activity.getWindow().setFormat(PixelFormat.TRANSLUCENT);
        videoView.setMediaController(new MediaController(activity));
        videoView.setVideoURI(video);
        videoView.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        return rootView;
    }

}
