package edu.ucsb.cs.cs184.jaredbjensen.jjensendemosuite;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;


public class PictureFragment extends Fragment implements AdapterView.OnItemSelectedListener,
        ImageRetriever.ImageResultListener, ImageRetriever.ImageListResultListener {

    Spinner spinner;

    public PictureFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        spinner = (Spinner) getActivity().findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ImageRetriever.getImageList(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picture, container, false);
    }

    public void onImageList(ArrayList<String> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    public void onImage(Bitmap image) {
        ((ImageView)getActivity().findViewById(R.id.picture)).setImageBitmap(image);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ImageRetriever.getImageByIndex(position, this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {}
}
