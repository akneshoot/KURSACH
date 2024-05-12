package com.example.happyenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PDFViewer extends AppCompatActivity {
    PDFView mPdfView;

    private int child_id, group_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        mPdfView=findViewById(R.id.pdfView);

       // mPdfView.fromAsset("present_simple.pdf").load();

        child_id = getIntent().getIntExtra("child_id",0);
        group_id=getIntent().getIntExtra("group_id",0);

        if (group_id==0){
            if (child_id==0){
                mPdfView.fromAsset("1000_words.pdf").load();
            }

        }else if(group_id==1){
            if(child_id==0){
                mPdfView.fromAsset("present_simple.pdf").load();
            }else if (child_id==1){
                mPdfView.fromAsset("present_continuous.pdf").load();
            }else if (child_id==2){
                mPdfView.fromAsset("present_perfect.pdf").load();
            }else if (child_id==3){
                mPdfView.fromAsset("present_perfect_continuous.pdf").load();
            }else if (child_id==4){
                mPdfView.fromAsset("past_simple.pdf").load();
            }else if (child_id==5){
                mPdfView.fromAsset("past_continuous.pdf").load();
            }else if (child_id==6){
                mPdfView.fromAsset("past_perfect.pdf").load();
            }else if (child_id==7){
                mPdfView.fromAsset("past_perfect_continuous.pdf").load();
            }else if (child_id==8){
                mPdfView.fromAsset("future_simple.pdf").load();
            }else if (child_id==9){
                mPdfView.fromAsset("future_continuous.pdf").load();
            }else if (child_id==10){
                mPdfView.fromAsset("future_perfect.pdf").load();
            }else if (child_id==11){
                mPdfView.fromAsset("future_perfect_continuous.pdf").load();
            }
        }else if (group_id==2){
            if (child_id==0){
                mPdfView.fromAsset("define_indefinite_article.pdf").load();
            }
        }else if (group_id==3) {
            if (child_id == 0) {
                mPdfView.fromAsset("POSSESSIVES.pdf").load();
            }
        }else if (group_id==4){
            if(child_id==0){
                mPdfView.fromAsset("zero_conditional.pdf").load();
            }else if (child_id==1){
                mPdfView.fromAsset("1_con.pdf").load();
            }else if (child_id==2){
                mPdfView.fromAsset("2_con.pdf").load();
            }else if (child_id==3){
                mPdfView.fromAsset("3_con.pdf").load();
            }else if (child_id==4){
                mPdfView.fromAsset("mix_con.pdf").load();
    }
}else if (group_id==5){
            if(child_id==0){
                mPdfView.fromAsset("gerunds.pdf").load();
            }else if (child_id==1){
                mPdfView.fromAsset("infinitives.pdf").load();
            }else if (child_id==2){
                mPdfView.fromAsset("infinitives_or_gerund.pdf").load();
            }
    }else if (group_id==6){
            if(child_id==0){
                mPdfView.fromAsset("defining.pdf").load();
            }else if (child_id==1){
                mPdfView.fromAsset("non_defing.pdf").load();
            }
}else if (group_id==7){
            if(child_id==0){
                mPdfView.fromAsset("direct.pdf").load();
            }else if (child_id==1){
                mPdfView.fromAsset("reporting_ques.pdf").load();
            }else if (child_id==2){
                mPdfView.fromAsset("repoting_commands.pdf").load();
            }
    }else if (group_id==8){
            if(child_id==0){
                mPdfView.fromAsset("full_verb.pdf").load();
            }else if (child_id==1){
                mPdfView.fromAsset("aux_v.pdf").load();
            }else if (child_id==2){
                mPdfView.fromAsset("modal_aux.pdf").load();
    }
        }else if (group_id==9) {
            if (child_id == 0) {
                mPdfView.fromAsset("idioms.pdf").load();
            } else if (child_id == 1) {
                mPdfView.fromAsset("metaphors.pdf").load();
            } else if (child_id == 2) {
                mPdfView.fromAsset("similes.pdf").load();
            }
        }
}}