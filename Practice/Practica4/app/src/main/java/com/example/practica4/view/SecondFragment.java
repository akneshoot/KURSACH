package com.example.practica4.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.practica4.R;
import com.example.practica4.view.Adapter;
import com.example.practica4.viewmodel.List;

import java.util.ArrayList;



public class SecondFragment extends Fragment {



    private static final String TAG = "MyApp";

    private String[] set_of_titles = new String[] {
            "fabaceous — бобовый",
            "fabulate — придумывать",
            "face-mould — шаблон",
            "goa — газель",
            "goal-keeper — вратарь",
            "goat-chafer — усач",
            "goat-fish — барабулька",
            "hider — закрывающий многоугольник",
            "hiemal — зимний",
            "hierarch — иерарх",
            "hierolatry — культ святых"
    };

    private Integer[] set_of_images = new Integer[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h

    };
    private Integer[] set_of_buttons = new Integer[]{
            R.id.button0,
            R.id.button0,
            R.id.button0,
            R.id.button0,
            R.id.button0,
            R.id.button0,
            R.id.button0,
            R.id.button0
    };





    private ListView listView1;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String myArg = getArguments().getString("myArg");
        Toast.makeText(getContext(), myArg, Toast.LENGTH_SHORT).show();

        int random_number;
        ArrayList<List> arrayList = new ArrayList<>();

        listView1 = getView().findViewById(R.id.list_view_1);

        for (int i = 0; i <= 199; i++) {
            random_number = (int) (Math.random() * 8);
            arrayList.add(new List(set_of_images[random_number], set_of_titles[random_number], set_of_buttons[random_number]));
        }



        Adapter customAdapter = new Adapter(getActivity(), R.layout.list_view, arrayList);
        listView1.setAdapter(customAdapter);



        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = position + 1;
                Log.i(TAG, "Нажато " + pos + " слово");
                Toast.makeText(getActivity(), "Нажато " + pos + " слово", Toast.LENGTH_SHORT).show();
            }
        });


    }
}