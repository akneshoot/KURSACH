package com.example.happyenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GrammarTestActivity extends AppCompatActivity {

    private ArrayList<QuestionModel> questionBundle;
    private TextView question_text_tv;
    private TextView question_counter_tv;
    private RadioGroup option_group;
    private RadioButton option_a;
    private RadioButton option_b;
    private RadioButton option_c;
    private Button submit_btn;

    private int current_question_index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_test);

        typeCastObject();
        setLevelForQuestion();
        setQuestionElementsOnViews(current_question_index);

        submit_btn.setOnClickListener(v -> {

            if (option_group.getCheckedRadioButtonId()==-1){
                Toast.makeText(GrammarTestActivity.this, "Ничего не выбрано!", Toast.LENGTH_SHORT).show();
            } else
            {

                  if (isCorrectAnswerSelect()) {
                      Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();
                      gotNext();
                }else{
                      Toast.makeText(this, "Неправильно!", Toast.LENGTH_SHORT).show();
                      gotNext();
                  }
            }
        });
    }

    private void gotNext() {

        current_question_index++;
        if (current_question_index<questionBundle.size()){
            setQuestionElementsOnViews(current_question_index);
        }else{
            Toast.makeText(this, "Тестирование окончено!", Toast.LENGTH_SHORT).show();
            submit_btn.setEnabled(false);
        }

    }

    private boolean isCorrectAnswerSelect() {

        String answer="";
        RadioButton selected_option=findViewById(option_group.getCheckedRadioButtonId());

        if (selected_option==option_a){
            answer="a";
        }else if (selected_option==option_b){
            answer="b";
        }else if (selected_option==option_c){
            answer="c";
        }

        return questionBundle.get(current_question_index).is_answer_correct(answer);
    }


    private void setQuestionElementsOnViews(int current_question_index) {

        question_text_tv.setText(questionBundle.get(current_question_index).getQuestion_text());
        question_counter_tv.setText(Integer.toString(current_question_index+1) + ": ");
        option_a.setText(questionBundle.get(current_question_index).getOption_a());
        option_b.setText(questionBundle.get(current_question_index).getOption_b());
        option_c.setText(questionBundle.get(current_question_index).getOption_c());
        option_group.clearCheck();
    }

    private void setLevelForQuestion() {

        level_a1();
    }

    private void level_a1() {

        questionBundle.add(new QuestionModel("How old _______ she?",
                "are",
                "is",
                "does",
                "b"));
        questionBundle.add(new QuestionModel("Who is she?",
                "it is my manager.",
                "he is my manager.",
                "she is my manager.",
                "c"));
        questionBundle.add(new QuestionModel("This city is hot _______ summer.",
                "on",
                "at",
                "in",
                "c"));
        questionBundle.add(new QuestionModel("Friday is the _______ day of the week.",
                "second",
                "third",
                "fifth",
                "c"));
        questionBundle.add(new QuestionModel("The children _______ toys.",
                "has",
                "have",
                "having",
                "b"));
        questionBundle.add(new QuestionModel("_______ a lamp near the coach yesterday.",
                "There are",
                "There is",
                "There was",
                "c"));
        questionBundle.add(new QuestionModel("There are some _______ on the road.",
                "women",
                "womens",
                "womans",
                "a"));
        questionBundle.add(new QuestionModel("She _______ up at 7. She gets up at 9.",
                "don't get",
                "doesn't gets",
                "doesn't get",
                "c"));
        questionBundle.add(new QuestionModel("Does Anton teaches english? ",
                "Yes, he do.",
                "No, He does.",
                "No, He doesn't. ",
                "c"));
        questionBundle.add(new QuestionModel("She is _______ on the road right now.",
                "walk",
                "walking",
                "walked",
                "b"));
        questionBundle.add(new QuestionModel("You can't talk in the library.",
                "permission",
                "ability",
                "request",
                "a"));
        questionBundle.add(new QuestionModel("Please choose, you can have orange juice ___ apple juice.",
                "but",
                "or",
                "and",
                "b"));
        questionBundle.add(new QuestionModel("You ___ be polite to your opponents.",
                "mustn't",
                "can",
                "must",
                "c"));
        questionBundle.add(new QuestionModel("I don't like this program. It's ___.",
                "bored",
                "boring",
                "bore",
                "b"));
        questionBundle.add(new QuestionModel("Would you like to have a drink with me?",
                "Yes, I'd love to.",
                "No, I would love to.",
                "Yes, thank you.",
                "a"));
        questionBundle.add(new QuestionModel("Which one is the ___ shopping mall in town?",
                "big",
                "biggest",
                "bigger",
                "b"));
        questionBundle.add(new QuestionModel("Going to work on foot is ___ that going by bus.",
                "good",
                "better",
                "the best",
                "b"));
        questionBundle.add(new QuestionModel("Don't worry we have ___ oli for tonight.",
                "little",
                "a few",
                "a little",
                "c"));
        questionBundle.add(new QuestionModel("Please choose, you can have orange juice ___ apple juice.",
                "but",
                "or",
                "and",
                "b"));
        questionBundle.add(new QuestionModel("You ___ be polite to your opponents.",
                "mustn't",
                "can",
                "must",
                "c"));
        questionBundle.add(new QuestionModel("I don't like this program. It's ___.",
                "bored",
                "boring",
                "bore",
                "b"));
        questionBundle.add(new QuestionModel("Does Anton teaches english? ",
                "Yes, he do.",
                "No, He does.",
                "No, He doesn't. ",
                "c"));
        questionBundle.add(new QuestionModel("She is _______ on the road right now.",
                "walk",
                "walking",
                "walked",
                "b"));
        questionBundle.add(new QuestionModel("You can't talk in the library.",
                "permission",
                "ability",
                "request",
                "a"));
        questionBundle.add(new QuestionModel("Please choose, you can have orange juice ___ apple juice.",
                "but",
                "or",
                "and",
                "b"));


    }
    private void level_a2() {
    }
    private void level_b1() {
    }
    private void level_b2() {
    }
    private void level_c1() {
    }
    private void level_c2() {
    }
    private void typeCastObject() {
        questionBundle=new ArrayList<QuestionModel>();
        question_text_tv=findViewById(R.id.question_text);
        question_counter_tv=findViewById(R.id.question_counter);
        option_group=findViewById(R.id.option_group);
        option_a=findViewById(R.id.option_a);
        option_b=findViewById(R.id.option_b);
        option_c=findViewById(R.id.option_c);
        submit_btn=findViewById(R.id.btn_submit);
    }
}