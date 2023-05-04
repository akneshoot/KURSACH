package com.example.practica4;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class FirstFragment extends Fragment {

    private static final String TAG = "MyApp";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button1 = getView().findViewById(R.id.Button1);
        Button button2 = getView().findViewById(R.id.Button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_secondFragment);
                Log.i(TAG, "Запущен 2 фрагмент");
                Toast.makeText(getActivity(), "Запущен 2 фрагмент", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdFragment nextFrag= new ThirdFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_frame_layout, nextFrag)
                        .addToBackStack(null)
                        .commit();
                Log.i(TAG, "Запущен 3 фрагмент");
                Toast.makeText(getActivity(), "Запущен 3 фрагмент", Toast.LENGTH_SHORT).show();
            }
        });


    }
}

