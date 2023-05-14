package com.example.happyenglish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

class ExpandableListData{

    public static HashMap <String, List<String>> getData(){

        HashMap <String, List<String>> expandableList = new LinkedHashMap<>();

        List <String> englishTenses = new ArrayList<String>();
           englishTenses.add("Simple Present");
           englishTenses.add("Simple past");
           englishTenses.add("Present perfect");
           englishTenses.add("Future Simple");
        englishTenses.add("Future Continuous");

        List <String> relativeClauses = new ArrayList<String>();
            relativeClauses.add("Define Relative");
            relativeClauses.add("Non-define Relative");

        List <String> grammarQuiz = new ArrayList<String>();
            grammarQuiz.add("Level-1");
            grammarQuiz.add("Level-2");
            grammarQuiz.add("Level-3");
            grammarQuiz.add("Level-4");

            List <String> partsofSpeech= new ArrayList<String>();
            partsofSpeech.add("Verb");
            partsofSpeech.add("Noun");
            partsofSpeech.add("Pronoun");

            expandableList.put("Parts of Speech".toUpperCase(), partsofSpeech);
        expandableList.put("12 English Tenses".toUpperCase(), englishTenses);
        expandableList.put("Relative Clauses".toUpperCase(), relativeClauses);
        expandableList.put("Grammar Quiz".toUpperCase(), grammarQuiz);



        return expandableList;

    }
}