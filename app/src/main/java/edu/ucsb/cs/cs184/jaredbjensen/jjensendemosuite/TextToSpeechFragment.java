package edu.ucsb.cs.cs184.jaredbjensen.jjensendemosuite;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;


public class TextToSpeechFragment extends Fragment {

    View rootView;
    TextToSpeech speech;
    EditText editText;
    Button button;

    public TextToSpeechFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_text_to_speech, container, false);
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        editText = (EditText) rootView.findViewById(R.id.text_to_speech_text);
        button = (Button) rootView.findViewById(R.id.button_speak);

        speech = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    speech.setLanguage(Locale.US);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                speech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    @Override
    public void onPause() {
        if (speech != null) {
            speech.stop();
            speech.shutdown();
        }

        super.onPause();
    }

}
