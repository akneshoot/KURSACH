package com.example.practica4.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.example.practica4.R;
import com.example.practica4.viewmodel.List;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<List> {
    private Context context;
    private int resource;
    private ArrayList<List> arrayList;

    public Adapter(Context context, int resource, ArrayList<List> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(resource, parent, false);
        }

        ImageView imageView = view.findViewById(R.id.image);
        TextView textView = view.findViewById(R.id.text_list_res);
        Button button = view.findViewById(R.id.button0);

        final List list = arrayList.get(position);

        imageView.setImageResource(list.getImage());
        textView.setText(list.getTitle());
        button.setText("Запомни");

        View finalView = view;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myArg = "Я запомнил это слово";
                Bundle bundle = new Bundle();
                bundle.putString("myArg", myArg);
                Navigation.findNavController(finalView).navigate(R.id.action_secondFragment_to_thirdFragment, bundle);

            }
        });

        return view;
    }
}
