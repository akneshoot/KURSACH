package com.example.practica4.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.practica4.R;
import com.example.practica4.view.ResAdapter;


public class ThirdFragment extends Fragment{

    private static final String TAG = "MyApp";

    private String[] set_of_titles = new String[] {
            "abatable — аннулируемый",
            "abask — греясь на солнышке",
            "bibliopegy — переплетное дело",
            "bibulosity — пьянство",
            "csardas — чардаш",
            "dead-smooth — бархатный",
            "deadneck — кретин",
            "deadness — отсутствие признаков жизни",
            "deady — джин",
            "ebrious — опьяневший",
            "ebulliate — кипеть",
    };

    private int[] set_of_images = new int[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j,
            R.drawable.k,
    };

    RecyclerView recyclerView;
    RecyclerView.Adapter progAdapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String myArg = getArguments().getString("myArg");
        Toast.makeText(getContext(), myArg, Toast.LENGTH_SHORT).show();

        recyclerView = getView().findViewById(R.id.recycle_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        progAdapter = new ResAdapter(getActivity(), set_of_images, set_of_titles);
        recyclerView.setAdapter(progAdapter);
    }

}