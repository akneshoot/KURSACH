package com.example.happyenglish;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.PagerAdapter;

public class CustomSlider extends PagerAdapter {

    Context context;

    String [] headings;
    String [] descriptions;
    int [] images = {R.drawable.tense, R.drawable.conditional, R.drawable.gerund, R.drawable.relative, R.drawable.reported, R.drawable.idiom, R.drawable.quiz};


    public CustomSlider (Context context, String [] headings, String [] descriptions){
        this.context=context;
        this.headings=headings;
        this.descriptions=descriptions;

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

       slider_image.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sliderPromptDialog(position);

           }
       });

        container.addView(view);
        return view;
    }

    private void sliderPromptDialog(int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Make a choice");
        final int slider_title_arrays[] = {
                R.array.slider_tenses_titles,
                R.array.slider_conditional_titles,
                R.array.slider_title_gerunds,
                R.array.slider_title_relatives,
                R.array.slider_title_directspeech,
                R.array.slider_idiom_titles,
                R.array.slider_test_titles};
        builder.setItems(slider_title_arrays[position], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(position==0){

                    Intent intent = new Intent(CustomSlider.this.context, PDFViewer.class);
                    intent.putExtra("group_id",1);
                    intent.putExtra("child_id",i);
                    context.startActivity(intent);


                } else if (position==1) {
                    Intent intent = new Intent(CustomSlider.this.context, PDFViewer.class);
                    intent.putExtra("group_id",4);
                    intent.putExtra("child_id",i);
                    context.startActivity(intent);

                } else if (position==2) {
                    Intent intent = new Intent(CustomSlider.this.context, PDFViewer.class);
                    intent.putExtra("group_id",5);
                    intent.putExtra("child_id",i);
                    context.startActivity(intent);

                }else if (position==3) {
                    Intent intent = new Intent(CustomSlider.this.context, PDFViewer.class);
                    intent.putExtra("group_id",6);
                    intent.putExtra("child_id",i);
                    context.startActivity(intent);

                }else if (position==4) {
                    Intent intent = new Intent(CustomSlider.this.context, PDFViewer.class);
                    intent.putExtra("group_id",7);
                    intent.putExtra("child_id",i);
                    context.startActivity(intent);

                }else if (position==5) {
                    Intent intent = new Intent(CustomSlider.this.context, PDFViewer.class);
                    intent.putExtra("group_id",9);
                    intent.putExtra("child_id",i);
                    context.startActivity(intent);

                }else if (position==6) {
                    Intent intent = new Intent(CustomSlider.this.context, GrammarTestActivity.class);
                    intent.putExtra("group_id",10);
                    intent.putExtra("child_id",i);
                    context.startActivity(intent);

                }


                Toast.makeText(context, "You have selected the item " + (i+1), Toast.LENGTH_SHORT).show();


            }
        });

        builder.show();

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
