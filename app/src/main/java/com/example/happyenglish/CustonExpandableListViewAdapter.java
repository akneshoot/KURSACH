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
           englishTenses.add("Present simple");
           englishTenses.add("Present continuous");
           englishTenses.add("Present perfect");
           englishTenses.add("Present perfect continuous");

           englishTenses.add("Past simple");
           englishTenses.add("Past continuous");
           englishTenses.add("Past perfect");
           englishTenses.add("Past perfect continuous");


           englishTenses.add("Future simple");
           englishTenses.add("Future continuous");
           englishTenses.add("Future perfect");
           englishTenses.add("Future perfect continuous");





        List <String> englishGrammarTest = new ArrayList<String>();
            englishGrammarTest.add("Level A1");
            englishGrammarTest.add("Level A2");
            englishGrammarTest.add("Level B1");
            englishGrammarTest.add("Level B2");
            englishGrammarTest.add("Level C1");
            englishGrammarTest.add("Level C2");



        List<String> idioms = new ArrayList<String>();
        idioms.add("Idioms");
        idioms.add("Metaphors");
        idioms.add("Similes");

        List <String> relativeClauses = new ArrayList<>();
        relativeClauses.add("Defining Relative Clauses");
        relativeClauses.add("Non-defining Relative Clauses");

        List <String> activePassive = new ArrayList<>();
        activePassive.add("Active/Passive");
        activePassive.add("Personal/Impersonal Passive");

        List<String> conditionals = new ArrayList<>();
        conditionals.add("Zero Conditional");
        conditionals.add("1st Conditional");
        conditionals.add("2nd Conditional");
        conditionals.add("3rd Conditional");
        conditionals.add("Mix Conditional");

        List<String> ReportedSpeech = new ArrayList<>();
        ReportedSpeech.add("Direct/Indirect Speech ");
        ReportedSpeech.add("Reporting Questions");
        ReportedSpeech.add("Reporting Commands/Requests ");

        List<String> infinitivesGerund = new ArrayList<>();
        infinitivesGerund.add("Gerunds");
        infinitivesGerund.add("Infinitives");
        infinitivesGerund.add("Infinitives or Gerund");

        List<String> verbs = new ArrayList<>();
        verbs.add("Full Verb");
        verbs.add("Auxiliary Verbs");
        verbs.add("Modal Auxiliary Verbs");

        List<String> articles = new ArrayList<>();
        articles.add("Definite & Indefinite Article");

        List<String> possessives = new ArrayList<>();
        possessives.add("adjectives & pronoun");


        List <String> partsofSpeech= new ArrayList<String>();
        partsofSpeech.add("noun, pronoun, adjective,verb ...");

        List<String> adverbs = new ArrayList<>();
        adverbs.add("Adverbs of Manner");
        adverbs.add("Adverbs of Place");
        adverbs.add("Adverbs of Time");
        adverbs.add("Adverbs of Frequency");



        expandableList.put("PARTS OF SPEECH", partsofSpeech);
        expandableList.put("TWELVE VERB TENSES", englishTenses);
        expandableList.put("ARTICLES", articles);
        expandableList.put("POSSESSIVES", possessives);
        expandableList.put("CONDITIONALS", conditionals);
        expandableList.put("INFINITIVE & GERUNDS", infinitivesGerund);
        expandableList.put("RELATIVE CLAUSES", relativeClauses);
        expandableList.put("REPORTED SPEECH", ReportedSpeech);
        expandableList.put("VERBS", verbs);
        expandableList.put("IDIOMS", idioms);
        expandableList.put("GRAMMAR QUIZ", englishGrammarTest);



        return expandableList;

    }
}