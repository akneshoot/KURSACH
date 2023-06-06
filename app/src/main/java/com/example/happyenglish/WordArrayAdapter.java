package com.example.happyenglish;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class WordArrayAdapter extends ArrayAdapter<String> implements View.OnClickListener {

    private final Context context;
    private final String[] values;
    private final HashMap<String, Integer> imageResourceMap;
    private TextToSpeech ttobj;

    public WordArrayAdapter(Context context, String[] values, TextToSpeech tts) {
        super(context, R.layout.word, values);
        this.context = context;
        this.values = values;
        this.ttobj = tts;
        imageResourceMap = new HashMap<>();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View rowView = inflater.inflate(R.layout.word, parent, false);
        TextView textView = rowView.findViewById(R.id.txt);
        ImageView imageView = rowView.findViewById(R.id.logo);
        textView.setText(values[position]);

        imageView.setImageResource(R.drawable.speaker);

        rowView.setOnClickListener(this);

        return rowView;
    }

    @Override
    public void onClick(View v) {
        String word = ((TextView) v.findViewById(R.id.txt)).getText().toString();
        playSound(word);
    }

    private void playSound(String word) {
        ttobj.speak(word, TextToSpeech.QUEUE_FLUSH, null);
    }
}
