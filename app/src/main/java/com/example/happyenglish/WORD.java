package com.example.happyenglish;
import java.util.Locale;

import android.app.ListActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ListView;

public class WORD extends ListActivity {


    static final String[] word_list =
            new String[] {
                    "accessary, accessory",
                    "ad, add",
                    "ail, ale",
                    "air, heir",
                    "aisle, I'll, isle",
                    "all, awl",
                    "allowed, aloud",
                    "alms, arms",
                    "altar, alter",
                    "arc, ark",
                    "aren't, aunt",
                    "ate, eight",
                    "auger, augur",
                    "auk, orc",
                    "aural, oral",
                    "away, aweigh",
                    "awe, oar, or, ore",
                    "axel, axle",
                    "aye, eye, I",
                    "bail, bale",
                    "bait, bate",
                    "baize, bays",
                    "bald, bawled",
                    "ball, bawl",
                    "band, banned",
                    "bard, barred",
                    "bare, bear",
                    "bark, barque",
                    "baron, barren",
                    "base, bass",
                    "bay, bey",
                    "bazaar, bizarre",
                    "be, bee",
                    "beach, beech",
                    "bean, been",
                    "beat, beet",
                    "beau, bow",
                    "beer, bier",
                    "bel, bell, belle",
                    "berry, bury",
                    "berth, birth",
                    "bight, bite, byte",
                    "billed, build",
                    "bitten, bittern",
                    "blew, blue",
                    "bloc, block",
                    "boar, bore",
                    "board, bored",
                    "boarder, border",
                    "bold, bowled",
                    "boos, booze",
                    "born, borne",
                    "bough, bow",
                    "boy, buoy",
                    "brae, bray",
                    "braid, brayed",
                    "braise, brays, braze",
                    "brake, break",
                    "bread, bred",
                    "brews, bruise",
                    "bridal, bridle",
                    "broach, brooch",
                    "bur, burr",
                    "but, butt",
                    "buy, by, bye",
                    "buyer, byre",
                    "calendar, calender",
                    "call, caul",
                    "canvas, canvass",
                    "cast, caste",
                    "caster, castor",
                    "caught, court",
                    "caw, core, corps",
                    "cede, seed",
                    "ceiling, sealing",
                    "cell, sell",
                    "censer, censor, sensor",
                    "cent, scent, sent",
                    "cereal, serial",
                    "cheap, cheep",
                    "check, cheek",
                    "choir, quire",
                    "chord, cord",
                    "cite, sight, site",
                    "clack, claque",
                    "clew, clue",
                    "climb, clime",
                    "close, cloze",
                    "coal, kohl",
                    "coarse, course",
                    "cooing, coin",
                    "colonel, kernel",
                    "complacent, complaisant",
                    "complement, compliment",
                    "coo, coup",
                    "cops, copse",
                    "council, counsel",
                    "cousin, cozen",
                    "creak, creek",
                    "crews, cruise",
                    "cue, key, queue",
                    "curb, herb",
                    "currant, current",
                    "cymbal, symbol",
                    "dam, damn",
                    "days, daze",
                    "dear, deer",
                    "descent, dissent",
                    "desert, dessert",
                    "deviser, divisor"



            };




    TextToSpeech ttobj;
    float pitch = -5;
    float speechRate = -5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new WordArrayAdapter(this, word_list ));


        ttobj=new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    public void onInit(int status) {

                        ttobj.setPitch(pitch);
                        ttobj.setSpeechRate(speechRate);

                        if(status != TextToSpeech.ERROR){
                            ttobj.setLanguage(Locale.US);


                        }
                    }
                });

    }


    public void onPause(){
        if(ttobj !=null){
            ttobj.stop();
            ttobj.shutdown();
        }
        super.onPause();
    }


    protected void onListItemClick(ListView l, View v, int position, long id) {

        String selectedValue = (String) getListAdapter().getItem(position);

        ttobj.speak(selectedValue, TextToSpeech.QUEUE_FLUSH, null);


    }




}
