package com.example.happyenglish;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SPEECH extends Activity {

    float pitch = -1;
    float speechRate = -1;
    Button btnBack;
    Button btnClear;
    TextToSpeech ttobj;
    private EditText write;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech);

        write = (EditText) findViewById(R.id.txtInput);


        ttobj = new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    public void onInit(int status) {

                        if (status != TextToSpeech.ERROR) {
                            ttobj.setLanguage(Locale.US);
                        }
                    }
                });



        addButtonListener();

    }

    private void addButtonListener() {

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent e = new Intent(SPEECH.this, MainActivity.class);
                startActivity(e);

            }

        });

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                write.setText("");
                ttobj.stop();

            }

        });
    }


    public void onPause() {
        if (ttobj != null) {
            ttobj.stop();
        }
        super.onPause();
    }

    public void speakText(View view) {

        ttobj.setPitch(pitch);
        ttobj.setSpeechRate(speechRate);
        String toSpeak = write.getText().toString();
        ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);


    }

}
