package com.example.practica4.view;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.practica4.R;
import com.example.practica4.viewmodel.Imagechange;
import com.example.practica4.viewmodel.Mymodel;



public class FirstFragment extends Fragment {

    private static final String TAG = "MyApp";

    private ImageView imageView;
    private Imagechange imageChange;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel Description");
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Button notificationButton = view.findViewById(R.id.not);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });

        Button button1 = getView().findViewById(R.id.Button1);
        Button button2 = getView().findViewById(R.id.Button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myArg = "Hello from Second Fragment";
                Bundle bundle = new Bundle();
                bundle.putString("myArg", myArg);
                Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_secondFragment, bundle);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myArg = "Hello from Third Fragment";
                Bundle bundle = new Bundle();
                bundle.putString("myArg", myArg);
                Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_thirdFragment, bundle);
            }
        });

        EditText editText = getView().findViewById(R.id.editTextTextPersonName);

        Mymodel viewModel = new ViewModelProvider(this).get(Mymodel.class);
        viewModel.getData().observe(getViewLifecycleOwner(), text_changer -> {
            String changed_text = text_changer.getEdited_text();
            System.out.println(changed_text);
        });

        Button button3 = getView().findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                Log.i(TAG, "Ваше имя успешно сохранено");
                viewModel.Change_text(editText.getText().toString());
            }

        });

        imageView = getView().findViewById(R.id.imageView9);
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.vocabulary);
        imageView.setImageBitmap(image);

        imageChange = new Imagechange(R.drawable.vocabulary);

        Button buttonChangeImage = getView().findViewById(R.id.buttonChangeImage);
        buttonChangeImage.setOnClickListener(new View.OnClickListener() {
            boolean isInitialImage = true;
            @Override
            public void onClick(View v) {
                if (isInitialImage) {
                    int newImage = R.drawable.useit_foreground;
                    imageView.setImageResource(newImage);
                    imageChange.changeImage(newImage);
                    isInitialImage = false;
                } else {
                    int initialImage = R.drawable.vocabulary;
                    imageView.setImageResource(initialImage);
                    imageChange.changeImage(initialImage);
                    isInitialImage = true;
                }
            }
        });

    }


    private void showNotification() {
        Notification notification = new NotificationCompat.Builder(getContext(), "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Уведомление")
                .setContentText("Вы выучили 12 слов!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
