package com.example.happyenglish;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class GrammarTestActivity extends AppCompatActivity {

    private ArrayList<QuestionModel> questionBundle;
    private TextView question_text_tv;
    private TextView question_counter_tv;
    private TextView level_tv;

    private TextView test_description_tv;
    private RadioGroup option_group;
    private RadioButton option_a;
    private RadioButton option_b;
    private RadioButton option_c;
    private Button submit_btn;

    private int current_question_index=0;

    //
    private int test_points_counter=0;
    private float test_current_average_counter=0f;
    private TextView test_points_tv;
    private TextView test_current_average_tv;

    //test result views
    private  TextView report_total_question_tv, report_answered_correct_tv, report_answered_wrong_tv, report_test_points_tv, report_current_average_tv, report_overall_average_tv;
   private int setLevelForQuestion_TAG;




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
                      //Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();
                      mCustomToast(R.drawable.correct);
                      test_points_counter++;
                      test_current_average_counter = (float) 100/questionBundle.size()*test_points_counter;
                      gotNext();
                }else{
                      //Toast.makeText(this, "Неправильно!", Toast.LENGTH_SHORT).show();
                      mCustomToast((R.drawable.wrong));
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

            test_end_show_result_dialog(setLevelForQuestion_TAG);
        }

    }

    private void test_end_show_result_dialog(int setLevelForQuestion_tag) {

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.grammar_test_result_layout, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Отчёт о тесте");
        builder.setView(view);

        report_total_question_tv=view.findViewById(R.id.tv_total_question);
        report_answered_correct_tv=view.findViewById(R.id.tv_answer_correct);
        report_answered_wrong_tv=view.findViewById(R.id.tv_answer_wrong);
        report_test_points_tv=view.findViewById(R.id.tv_test_points);
        report_current_average_tv=view.findViewById(R.id.tv_current_average);
        report_overall_average_tv=view.findViewById(R.id.tv_overall_average);

        report_total_question_tv.setText(Integer.toString(questionBundle.size()));
        report_answered_correct_tv.setText(Integer.toString(test_points_counter));
        report_answered_wrong_tv.setText(Integer.toString(questionBundle.size()- test_points_counter));
        report_test_points_tv.setText(Integer.toString(test_points_counter));
        report_current_average_tv.setText(Float.toString(test_current_average_counter));
        report_overall_average_tv.setText("Не найдено");


        Dialog dialog = builder.create();
        dialog.show();



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

        test_points_tv.setText("Тестовые очки:" + Integer.toString(test_points_counter));
        test_current_average_tv.setText("Среднее значение:" + Float.toString(test_current_average_counter));
    }

    private void setLevelForQuestion() {

         setLevelForQuestion_TAG = getIntent().getIntExtra("child_id", 0);

        switch (setLevelForQuestion_TAG){
            case 0:
                level_a1();
                level_tv.setText("Level A1");
                test_description_tv.setText("Этот тест содержит 25 вопросов, каждый из которых даёт баллы. Убедитесь, что выбрали верные варианты ответа перед отправкой");
                break;
            case 1:
                level_a2();
                level_tv.setText("Level A2");
                test_description_tv.setText("Этот тест содержит 25 вопросов, каждый из которых даёт баллы. Убедитесь, что выбрали верные варианты ответа перед отправкой");
                break;
            case 2:
                level_b1();
                level_tv.setText("Level B1");
                test_description_tv.setText("Этот тест содержит 15 вопросов, каждый из которых даёт баллы. Убедитесь, что выбрали верные варианты ответа перед отправкой");
                break;
            case 3:
                level_b2();
                level_tv.setText("Level B2");
                test_description_tv.setText("Этот тест содержит 15 вопросов, каждый из которых даёт баллы. Убедитесь, что выбрали верные варианты ответа перед отправкой");
                break;
            case 4:
                level_c1();
                level_tv.setText("Level C1");
                test_description_tv.setText("Этот тест содержит 10 вопросов, каждый из которых даёт баллы. Убедитесь, что выбрали верные варианты ответа перед отправкой");
                break;
            case 5:
                level_c2();
                level_tv.setText("Level C2");
                test_description_tv.setText("Этот тест содержит 10 вопросов, каждый из которых даёт баллы. Убедитесь, что выбрали верные варианты ответа перед отправкой");
                break;
        }

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
        questionBundle.add(new QuestionModel("Please could you show me _______ tie?",
                "that",
                "these",
                "those",
                "a"));

        questionBundle.add(new QuestionModel("I am going to the barbers _______.",
                "to sunbathe",
                "to get show some sleep",
                "to get my hair cut",
                "c"));

        questionBundle.add(new QuestionModel("I do veru _______ exercise. I am so unfit!",
                "a little",
                "little",
                "much",
                "a"));

        questionBundle.add(new QuestionModel("Children _______ go to school.",
                "must",
                "have to",
                "should",
                "a"));

        questionBundle.add(new QuestionModel("A person doing a job for someone is an _______.",
                "employer",
                "employee",
                "employed",
                "b"));

        questionBundle.add(new QuestionModel("I _______ to move to a house in the country.",
                "am wanting",
                "wanting",
                "want",
                "c"));

        questionBundle.add(new QuestionModel("Why _______ coming with you?",
                "I am",
                "am I",
                "do I",
                "b"));

        questionBundle.add(new QuestionModel("A: What will you do this evening? \n B: Maybe, _______",
                "I am going to see my friend.",
                "I wil go to cinema.",
                "I will be going to cinema.",
                "b"));

        questionBundle.add(new QuestionModel("Have you ever donated money to charity?",
                "No",
                "No, I haven`t never",
                "No, I have never",
                "c"));

        questionBundle.add(new QuestionModel("Kang thought the spicy tofu dish was _______ tasty, so he ordered it again.",
                "mainly",
                "fairly",
                "especially",
                "b"));

        questionBundle.add(new QuestionModel("If I _______ rich, I would have a lot of friends.",
                "were",
                "was",
                "both",
                "c"));
        questionBundle.add(new QuestionModel("I _______ be overweight, but I lost 10 kilos.",
                "used to",
                "use to",
                "didn`t used to",
                "a"));
        questionBundle.add(new QuestionModel("He ate the meal then left the restaurant _______.",
                "after",
                "a while later",
                "finally",
                "b"));

        questionBundle.add(new QuestionModel("I _______ in my office when my secretary _______.",
                "was working/call",
                "worked/calling",
                "was working/called",
                "c"));
        questionBundle.add(new QuestionModel("he building here are _______ beautiful. I love this place.",
                "really",
                "too",
                "realy",
                "a"));
        questionBundle.add(new QuestionModel("The trains are even _______ than the buses.",
                "dirtyer",
                "more dirty",
                "dirtier",
                "c"));
        questionBundle.add(new QuestionModel("She if a _______ singer that is why she sings _______.",
                "beautiful/beautiful", "beautiful/beautifully",
                "beautifully/beautiful", "b"));

        questionBundle.add(new QuestionModel("Hurry up! Have you not finished packing your bags _______?",
                "yet",
                "already",
                "just",
                "a"));

        questionBundle.add(new QuestionModel("We have lived in Washington _______ 10 years",
                "since", "for", "about", "b"));
        questionBundle.add(new QuestionModel("Have you got the novel which was _______ by Khan.", "write",
                "wrote", "written", "c"));
        questionBundle.add(new QuestionModel("Do we have _______ for the journey?",
                "enough money", "money enough", "money", "a"));

        questionBundle.add(new QuestionModel("I hate _______ to gym.", "go", "going", "to going", "b" ));
        questionBundle.add(new QuestionModel("The house _______ I was born is very cold",
                "which", "where", "there", "b"));

        questionBundle.add(new QuestionModel("She was really worried about her final exam, but in the end, she _______ it with no problems.",
                "got through",
                "got over",
                "got under",
                "a"));
        questionBundle.add(new QuestionModel("Your car is not _______. Let`s go in my car.",
                "enough big", "big enough", "enough", "b"));



    }
    private void level_b1() {
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "When are you going to go out?",
                "When going out are we?",
                "When do we go out?",
                "a"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "I work tomorrow",
                "I don't working tomorrow",
                "I'm working tomorrow",
                "c"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "Did you finish your project?",
                "Have you finished your project?",
                "Have you got finished your project?",
                "b"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "I am usually having some coffee and toast for my breakfast",
                "I am used to have some coffee and toast for my breakfast",
                " I usually have some coffee and toast for my breakfast",
                "c"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "I'm trying to eat a more healthy diet",
                "I try to eat a more healthy diet",
                "I'm trying to eat a healthier diet",
                "c"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "He's never been to New York",
                "He's never gone to New York",
                "He's gone often to New York",
                "a"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "At this rate, they will never be here on time",
                "At this rate, they are never here on time",
                " At this rate, they are never going here on time",
                "a"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "Are you studied Chinese before?",
                "Are you studying Chinese before?",
                "Have you studied Chinese before?",
                "c"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "I can do that for you",
                "I could do that",
                "I could to make that for you",
                "a"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "Are you going to University?",
                "Are you going to go to University?",
                "Do you like University?",
                "b"));
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "You haven't to do that, you know",
                "You didn't have to do that, you know",
                "You didn't must do that, you know",
                "b"));
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "How long is it from Hong Kong to Shanghai?",
                "How far is it from Hong Kong to Shanghai?",
                "How much is it from Hong Kong to Shanghai?",
                "b"));
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "When we finish the painting, we'll have a cup of tea",
                "When we've finished the painting, we'll have a cup of tea",
                "When the painting finishes, we'll have a cup of tea",
                "a"));
        questionBundle.add(new QuestionModel("Choose the correct option: He told her that...",
                "He would love her forever",
                "He loved her forever",
                "He is loving her forever",
                "a"));
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "She asked the shop assistant to have a refund",
                "She asked the shop assistant to give a refund",
                "She asked the shop assistant for a refund",
                "c"));
    }
    private void level_b2() {
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "He told he wasn't feeling well",
                "He said he doesn't feeling well",
                "He said he wasn't feeling well",
                "с"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "We won't know how to do after we get the results",
                "When we get the results we won't know what to do",
                "We won't know what to do until we get the results",
                "с"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "If you wouldn't tell me, I'll scream!",
                "If you don't tell me, I'll scream!",
                "If you didn't tell me, I'll scream!",
                "b"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "He's probably lost her number",
                "He's lost her number, probably",
                "Probably, he's lost her numbe",
                "a"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "I'll only tell you, if you can keep a secret",
                "If you can keep a secret, I would tell you",
                "you can't keep a secret, if I did tell you",
                "a"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "What do you think we'll be doing in five years' time?",
                "What do you think you're doing in five years' time?",
                "What do you think we do in five years' time?",
                "a"));

        questionBundle.add(new QuestionModel("Choose the correct option:",
                "I didn't do that if I were you",
                "I wouldn't do that if I were you",
                "I wouldn't do that if I was you",
                "b"));
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "I usually to live in Paris, but now I live in Madrid",
                "I used to live in Paris, but now I live in Madrid",
                "I am used to live in Paris, but now I live in Madrid",
                "b"));
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "This time tomorrow, I am in Tokyo",
                "This time tomorrow, I am being in Tokyo",
                "This time tomorrow, I will be in Tokyo",
                "c"));
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "You didn't use to smoke, did you?",
                "You usen't to smoke, did you?",
                "You aren't used to smoke, did you?",
                "a"));
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "He looked tired, because he had travelled all day",
                "He looked tired, because he had been travelling all day",
                "He looked tired, because he had travelling all day",
                "b"));
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "Sarah is working on something done!",
                "Sarah is having some work done!",
                "Sarah is doing work done!",
                "b"));
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "You must have drink something!",
                "You must have drinking!",
                "You must have been drinking!",
                "c"));
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "She's so nice a woman!",
                "She' s such a nice woman!",
                "She's a so nice woman!",
                "b"));
        questionBundle.add(new QuestionModel("Choose the correct option:",
                "I was told the party was next Friday",
                "I told the party was next Friday",
                "told them the party was next Friday",
                "a"));



    }
    private void level_c1() {
        questionBundle.add(new QuestionModel("It was really nice _______ you to invite me.",
                "for",
                "of",
                "to",
                "b"));
        questionBundle.add(new QuestionModel("The house has been _______ the market for a while.",
                "by",
                "to",
                "on",
                "c"));
        questionBundle.add(new QuestionModel("I'm not very good _______ maths.",
                "with",
                "on",
                "at",
                "c"));
        questionBundle.add(new QuestionModel("You are _______ no obligation to go.",
                "to",
                "with",
                "under",
                "c"));
        questionBundle.add(new QuestionModel("He is _______ charge of the whole department.",
                "at",
                "in",
                "on",
                "b"));
        questionBundle.add(new QuestionModel("Robert is an authority _______ English literature.",
                "in",
                "on",
                "over",
                "b"));
        questionBundle.add(new QuestionModel("You must be responsible _______ your decisions.",
                "for",
                "of",
                "to",
                "a"));
        questionBundle.add(new QuestionModel("I'm very concerned _______ his smoking.",
                "againts",
                "with",
                "about",
                "c"));
        questionBundle.add(new QuestionModel("Could you deal _______ this problem later, please.",
                "for",
                "with",
                "from",
                "b"));
        questionBundle.add(new QuestionModel("I want to protest _______ the state of the building.",
                "over",
                "on",
                "about",
                "c"));
    }
    private void level_c2() {
        questionBundle.add(new QuestionModel("The fact of the _________ is we have a government that will do what it wants to do for the next two years.",
                "matter",
                "problem",
                "situation",
                "a"));
        questionBundle.add(new QuestionModel("The thing _________ , once a film gets this kind of apocalyptically awful publicity, how do you not release it and give the public a crack at it?",
                "is",
                "what",
                "goes",
                "a"));
        questionBundle.add(new QuestionModel("_________, I still was able to get to the top of the mountain.",
                "Even though unfit",
                "Unfit as I am",
                "While ever out of condition",
                "b"));
        questionBundle.add(new QuestionModel("She managed to pull the horse up is spite of ________ a broken rein.",
                "having",
                "had",
                "have",
                "a"));
        questionBundle.add(new QuestionModel("The main argument in the report is _________ correct.",
                "up to a point",
                "fundamentally",
                "primarily",
                "b"));
        questionBundle.add(new QuestionModel("I think going for the weekend to the coast _________ be a good idea.",
                "bound to",
                "would",
                "should",
                "b"));
        questionBundle.add(new QuestionModel("There was a problem with the cellphone, but I _________ it now.",
                "have fixed",
                "fixed",
                "have been fixing",
                "a"));
        questionBundle.add(new QuestionModel("There are about twenty _________ so people waiting in the outer office.",
                "or",
                "but",
                "and",
                "a"));
        questionBundle.add(new QuestionModel("As a cab driver, I _________, though Iâ€™d love a bigger paycheck.",
                "take on",
                "get by",
                "get on",
                "b"));
        questionBundle.add(new QuestionModel("The main argument in the report is _________ correct.",
                "up to a point",
                "primarily",
                "fundamentally",
                "c"));
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
        level_tv=findViewById(R.id.level_textview);
        test_description_tv=findViewById(R.id.test_description_textview);

        test_points_tv=findViewById(R.id.test_points_textView);
        test_current_average_tv =findViewById(R.id.current_test_average_textView);


    }

    private void mCustomToast(int image){
        LayoutInflater inflater = (LayoutInflater)  this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_toast_layout, null, false);
        GifImageView imageView = view.findViewById(R.id.custom_toast_image_view);
        imageView.setImageResource(image);

        Toast mToast =new Toast(this);
        mToast.setView(view);
        mToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }
}