package com.example.happyenglish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class CustomSlider extends PagerAdapter {

    Context context;

    String [] headings={"Russia", "America", "Australia"};
    String [] descriptions={"It is my country", "It is a good country", "I live in this country"};
    int [] images = {R.drawable.tense, R.drawable.reported, R.drawable.relative};


    public CustomSlider (Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_slider,container,false);

        ImageView slider_image = view.findViewById(R.id.slider_image_view);
        TextView slider_heading = view.findViewById(R.id.slider_heading);
        TextView slider_description = view.findViewById(R.id.slider_description);

        slider_image.setImageResource(images[position]);
        slider_heading.setText(headings[position]);
        slider_description.setText(descriptions[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
