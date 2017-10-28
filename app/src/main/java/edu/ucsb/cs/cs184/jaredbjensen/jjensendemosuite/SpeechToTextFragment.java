package edu.ucsb.cs.cs184.jaredbjensen.jjensendemosuite;


import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


public class SpeechToTextFragment extends Fragment {

    static final int SPEECH_TO_TEXT = 1;

    View rootView;
    ImageButton button;
    TextView textView;

    public SpeechToTextFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_speech_to_text, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        textView = (TextView) rootView.findViewById(R.id.text_from_speech);
        button = (ImageButton) rootView.findViewById(R.id.button_record);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something...");

                startActivityForResult(intent, SPEECH_TO_TEXT);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        ArrayList<String> list = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        String spokenText =	list.get(0);

        textView.setText(spokenText);
    }

}
