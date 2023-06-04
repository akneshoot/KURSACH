package com.example.happyenglish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WordArrayAdapter extends ArrayAdapter {


    private final Context context;
    private final String[] values;


    public WordArrayAdapter(Context context, String[] values) {
        super(context, R.layout.word, values);
        this.context = context;
        this.values = values;

    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.word, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        textView.setText(values[position]);

        String s = values[position];

        if (s.equals("accessary, accessory")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("ad, add")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("ail, ale")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("air, heir")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("aisle, I'll, isle")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("all, awl")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("allowed, aloud")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("alms, arms")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("altar, alter")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("arc, ark")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("aren't, aunt")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("ate, eight")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("auger, augur")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("auk, orc")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("aural, oral")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("away, aweigh")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("awe, oar, or, ore")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("axel, axle")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("aye, eye, I")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bail, bale")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bait, bate")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("baize, bays")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bald, bawled")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("ball, bawl")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("band, banned")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bard, barred")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bare, bear")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bark, barque")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("baron, barren")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("base, bass")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bay, bey")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bazaar, bizarre")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("be, bee")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("beach, beech")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bean, been")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("beat, beet")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("beau, bow")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("beer, bier")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bel, bell, belle")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("berry, bury")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("berth, birth")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bight, bite, byte")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("billed, build")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bitten, bittern")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("blew, blue")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bloc, block")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("boar, bore")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("board, bored")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("boarder, border")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bold, bowled")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("boos, booze")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("born, borne")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bough, bow")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("boy, buoy")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("brae, bray")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("braid, brayed")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("braise, brays, braze")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("brake, break")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bread, bred")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("brews, bruise")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bridal, bridle")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("broach, brooch")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("bur, burr")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("but, butt")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("buy, by, bye")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("buyer, byre")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("calendar, calender")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("call, caul")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("canvas, canvass")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("cast, caste")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("caster, castor")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("caught, court")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("caw, core, corps")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("cede, seed")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("ceiling, sealing")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("cell, sell")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("censer, censor, sensor")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("cent, scent, sent")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("cereal, serial")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("cheap, cheep")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("check, cheek")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("choir, quire")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("chord, cord")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("cite, sight, site")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("clack, claque")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("clew, clue")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("climb, clime")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("close, cloze")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("coal, kohl")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("coarse, course")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("cooing, coin")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("colonel, kernel")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("complacent, complaisant")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("complement, compliment")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("coo, coup")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("cops, copse")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("council, counsel")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("cousin, cozen")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("creak, creek")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("crews, cruise")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("cue, key, queue")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("curb, herb")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("currant, current")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("cymbal, symbol")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("dam, damn")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("days, daze")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("dear, deer")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("descent, dissent")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("desert, dessert")) {
            imageView.setImageResource(R.drawable.speaker);

        } else if (s.equals("deviser, divisor")) {
            imageView.setImageResource(R.drawable.speaker);

        }


        return rowView;
    }


}

