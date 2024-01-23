package com.example.texttospeech_joelmartin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech tts;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.buttonTalk);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                tts.setLanguage(Locale.UK);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak = editText.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_LONG).show();
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
        public void onPause(){
            if(tts !=null){
                tts.stop();
                tts.shutdown();
            }
            super.onPause();
        }

}