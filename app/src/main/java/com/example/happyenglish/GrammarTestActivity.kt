package com.example.happyenglish

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import pl.droidsonroids.gif.GifImageView

class GrammarTestActivity : AppCompatActivity() {
    private var questionBundle: ArrayList<QuestionModel>? = null
    private var question_text_tv: TextView? = null
    private var question_counter_tv: TextView? = null
    private var level_tv: TextView? = null

    private var test_description_tv: TextView? = null
    private var option_group: RadioGroup? = null
    private var option_a: RadioButton? = null
    private var option_b: RadioButton? = null
    private var option_c: RadioButton? = null
    private var submit_btn: Button? = null


    private var current_question_index = 0

    //
    private var test_points_counter = 0
    private var test_current_average_counter = 0f
    private var test_points_tv: TextView? = null
    private var test_current_average_tv: TextView? = null

    private var a1_average = 0f
    private var a2_average = 0f
    private var b1_average = 0f
    private var b2_average = 0f
    private var c1_average = 0f
    private var c2_average = 0f
    private var test_overall_average = 0f

    //test result views
    private var report_total_question_tv: TextView? = null
    private var report_answered_correct_tv: TextView? = null
    private var report_answered_wrong_tv: TextView? = null
    private var report_test_points_tv: TextView? = null
    private var report_current_average_tv: TextView? = null
    private var report_overall_average_tv: TextView? = null
    private var setLevelForQuestion_TAG = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_test)

        typeCastObject()
        setLevelForQuestion()
        setQuestionElementsOnViews(current_question_index)

        submit_btn!!.setOnClickListener { v: View? ->
            if (option_group!!.checkedRadioButtonId == -1) {
                Toast.makeText(this@GrammarTestActivity, "Nothing is selected!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (isCorrectAnswerSelect) {
                    //Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();
                    mCustomToast(R.drawable.correct)
                    test_points_counter++
                    test_current_average_counter =
                        100f / questionBundle!!.size * test_points_counter
                    gotNext()
                } else {
                    //Toast.makeText(this, "Неправильно!", Toast.LENGTH_SHORT).show();
                    mCustomToast((R.drawable.wrong))
                    gotNext()
                }
            }
        }
    }

    private fun gotNext() {
        current_question_index++
        if (current_question_index < questionBundle!!.size) {
            setQuestionElementsOnViews(current_question_index)
        } else {
            Toast.makeText(this, "Testing is over!", Toast.LENGTH_SHORT).show()
            submit_btn!!.isEnabled = false

            test_end_show_result_dialog(setLevelForQuestion_TAG)
        }
    }

    private fun test_end_show_result_dialog(setLevelForQuestion_tag: Int) {
        if (setLevelForQuestion_tag == 0) {
            setDataToSharedPreferences("a1_average", test_current_average_counter)
        } else if (setLevelForQuestion_tag == 1) {
            setDataToSharedPreferences("a2_average", test_current_average_counter)
        } else if (setLevelForQuestion_tag == 2) {
            setDataToSharedPreferences("b1_average", test_current_average_counter)
        } else if (setLevelForQuestion_tag == 3) {
            setDataToSharedPreferences("b2_average", test_current_average_counter)
        } else if (setLevelForQuestion_tag == 4) {
            setDataToSharedPreferences("c1_average", test_current_average_counter)
        } else if (setLevelForQuestion_tag == 5) {
            setDataToSharedPreferences("c2_average", test_current_average_counter)
        }

        val layoutInflater = this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.grammar_test_result_layout, null, false)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Отчёт о тесте")
        builder.setView(view)

        report_total_question_tv = view.findViewById(R.id.tv_total_question)
        report_answered_correct_tv = view.findViewById(R.id.tv_answer_correct)
        report_answered_wrong_tv = view.findViewById(R.id.tv_answer_wrong)
        report_test_points_tv = view.findViewById(R.id.tv_test_points)
        report_current_average_tv = view.findViewById(R.id.tv_current_average)
        report_overall_average_tv = view.findViewById(R.id.tv_overall_average)

        report_total_question_tv?.setText(questionBundle!!.size.toString())
        report_answered_correct_tv?.setText(test_points_counter.toString())
        report_answered_wrong_tv?.setText((questionBundle!!.size - test_points_counter).toString())
        report_test_points_tv?.setText(test_points_counter.toString())
        report_current_average_tv?.setText(test_current_average_counter.toString())
        report_overall_average_tv?.setText(dataFromSharedPreferences.toString())


        val dialog: Dialog = builder.create()
        dialog.show()
    }

    private val isCorrectAnswerSelect: Boolean
        get() {
            var answer = ""
            val selected_option = findViewById<RadioButton>(option_group!!.checkedRadioButtonId)

            if (selected_option === option_a) {
                answer = "a"
            } else if (selected_option === option_b) {
                answer = "b"
            } else if (selected_option === option_c) {
                answer = "c"
            }

            return questionBundle!![current_question_index].is_answer_correct(answer)
        }


    private fun setQuestionElementsOnViews(current_question_index: Int) {
        question_text_tv!!.text = questionBundle!![current_question_index].question_text
        question_counter_tv!!.text = (current_question_index + 1).toString() + ": "
        option_a!!.text = questionBundle!![current_question_index].option_a
        option_b!!.text = questionBundle!![current_question_index].option_b
        option_c!!.text = questionBundle!![current_question_index].option_c
        option_group!!.clearCheck()

        test_points_tv!!.text = "Тестовые очки:$test_points_counter"
        test_current_average_tv!!.text = "Среднее значение:$test_current_average_counter"
    }

    private fun setLevelForQuestion() {
        setLevelForQuestion_TAG = intent.getIntExtra("child_id", 0)

        when (setLevelForQuestion_TAG) {
            0 -> {
                level_a1()
                level_tv!!.text = "Level A1"
                test_description_tv!!.text =
                    "Этот тест содержит 25 вопросов, каждый из которых даёт баллы. Убедитесь, что выбрали верные варианты ответа перед отправкой"
            }

            1 -> {
                level_a2()
                level_tv!!.text = "Level A2"
                test_description_tv!!.text =
                    "Этот тест содержит 25 вопросов, каждый из которых даёт баллы. Убедитесь, что выбрали верные варианты ответа перед отправкой"
            }

            2 -> {
                level_b1()
                level_tv!!.text = "Level B1"
                test_description_tv!!.text =
                    "Этот тест содержит 15 вопросов, каждый из которых даёт баллы. Убедитесь, что выбрали верные варианты ответа перед отправкой"
            }

            3 -> {
                level_b2()
                level_tv!!.text = "Level B2"
                test_description_tv!!.text =
                    "Этот тест содержит 15 вопросов, каждый из которых даёт баллы. Убедитесь, что выбрали верные варианты ответа перед отправкой"
            }

            4 -> {
                level_c1()
                level_tv!!.text = "Level C1"
                test_description_tv!!.text =
                    "Этот тест содержит 10 вопросов, каждый из которых даёт баллы. Убедитесь, что выбрали верные варианты ответа перед отправкой"
            }

            5 -> {
                level_c2()
                level_tv!!.text = "Level C2"
                test_description_tv!!.text =
                    "Этот тест содержит 10 вопросов, каждый из которых даёт баллы. Убедитесь, что выбрали верные варианты ответа перед отправкой"
            }
        }
    }

    private fun level_a1() {
        questionBundle!!.add(
            QuestionModel(
                "How old _______ she?",
                "are",
                "is",
                "does",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Who is she?",
                "it is my manager.",
                "he is my manager.",
                "she is my manager.",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "This city is hot _______ summer.",
                "on",
                "at",
                "in",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Friday is the _______ day of the week.",
                "second",
                "third",
                "fifth",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "The children _______ toys.",
                "has",
                "have",
                "having",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "_______ a lamp near the coach yesterday.",
                "There are",
                "There is",
                "There was",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "There are some _______ on the road.",
                "women",
                "womens",
                "womans",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "She _______ up at 7. She gets up at 9.",
                "don't get",
                "doesn't gets",
                "doesn't get",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Does Anton teaches english? ",
                "Yes, he do.",
                "No, He does.",
                "No, He doesn't. ",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "She is _______ on the road right now.",
                "walk",
                "walking",
                "walked",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "You can't talk in the library.",
                "permission",
                "ability",
                "request",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Please choose, you can have orange juice ___ apple juice.",
                "but",
                "or",
                "and",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "You ___ be polite to your opponents.",
                "mustn't",
                "can",
                "must",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "I don't like this program. It's ___.",
                "bored",
                "boring",
                "bore",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Would you like to have a drink with me?",
                "Yes, I'd love to.",
                "No, I would love to.",
                "Yes, thank you.",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Which one is the ___ shopping mall in town?",
                "big",
                "biggest",
                "bigger",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Going to work on foot is ___ that going by bus.",
                "good",
                "better",
                "the best",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Don't worry we have ___ oli for tonight.",
                "little",
                "a few",
                "a little",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Please choose, you can have orange juice ___ apple juice.",
                "but",
                "or",
                "and",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "You ___ be polite to your opponents.",
                "mustn't",
                "can",
                "must",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "I don't like this program. It's ___.",
                "bored",
                "boring",
                "bore",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Does Anton teaches english? ",
                "Yes, he do.",
                "No, He does.",
                "No, He doesn't. ",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "She is _______ on the road right now.",
                "walk",
                "walking",
                "walked",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "You can't talk in the library.",
                "permission",
                "ability",
                "request",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Please choose, you can have orange juice ___ apple juice.",
                "but",
                "or",
                "and",
                "b"
            )
        )
    }

    private fun level_a2() {
        questionBundle!!.add(
            QuestionModel(
                "Please could you show me _______ tie?",
                "that",
                "these",
                "those",
                "a"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "I am going to the barbers _______.",
                "to sunbathe",
                "to get show some sleep",
                "to get my hair cut",
                "c"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "I do veru _______ exercise. I am so unfit!",
                "a little",
                "little",
                "much",
                "a"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Children _______ go to school.",
                "must",
                "have to",
                "should",
                "a"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "A person doing a job for someone is an _______.",
                "employer",
                "employee",
                "employed",
                "b"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "I _______ to move to a house in the country.",
                "am wanting",
                "wanting",
                "want",
                "c"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Why _______ coming with you?",
                "I am",
                "am I",
                "do I",
                "b"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "A: What will you do this evening? \n B: Maybe, _______",
                "I am going to see my friend.",
                "I wil go to cinema.",
                "I will be going to cinema.",
                "b"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Have you ever donated money to charity?",
                "No",
                "No, I haven`t never",
                "No, I have never",
                "c"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Kang thought the spicy tofu dish was _______ tasty, so he ordered it again.",
                "mainly",
                "fairly",
                "especially",
                "b"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "If I _______ rich, I would have a lot of friends.",
                "were",
                "was",
                "both",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "I _______ be overweight, but I lost 10 kilos.",
                "used to",
                "use to",
                "didn`t used to",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "He ate the meal then left the restaurant _______.",
                "after",
                "a while later",
                "finally",
                "b"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "I _______ in my office when my secretary _______.",
                "was working/call",
                "worked/calling",
                "was working/called",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "he building here are _______ beautiful. I love this place.",
                "really",
                "too",
                "realy",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "The trains are even _______ than the buses.",
                "dirtyer",
                "more dirty",
                "dirtier",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "She if a _______ singer that is why she sings _______.",
                "beautiful/beautiful", "beautiful/beautifully",
                "beautifully/beautiful", "b"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Hurry up! Have you not finished packing your bags _______?",
                "yet",
                "already",
                "just",
                "a"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "We have lived in Washington _______ 10 years",
                "since", "for", "about", "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Have you got the novel which was _______ by Khan.", "write",
                "wrote", "written", "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Do we have _______ for the journey?",
                "enough money", "money enough", "money", "a"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "I hate _______ to gym.",
                "go",
                "going",
                "to going",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "The house _______ I was born is very cold",
                "which", "where", "there", "b"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "She was really worried about her final exam, but in the end, she _______ it with no problems.",
                "got through",
                "got over",
                "got under",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Your car is not _______. Let`s go in my car.",
                "enough big", "big enough", "enough", "b"
            )
        )
    }

    private fun level_b1() {
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "When are you going to go out?",
                "When going out are we?",
                "When do we go out?",
                "a"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "I work tomorrow",
                "I don't working tomorrow",
                "I'm working tomorrow",
                "c"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "Did you finish your project?",
                "Have you finished your project?",
                "Have you got finished your project?",
                "b"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "I am usually having some coffee and toast for my breakfast",
                "I am used to have some coffee and toast for my breakfast",
                " I usually have some coffee and toast for my breakfast",
                "c"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "I'm trying to eat a more healthy diet",
                "I try to eat a more healthy diet",
                "I'm trying to eat a healthier diet",
                "c"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "He's never been to New York",
                "He's never gone to New York",
                "He's gone often to New York",
                "a"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "At this rate, they will never be here on time",
                "At this rate, they are never here on time",
                " At this rate, they are never going here on time",
                "a"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "Are you studied Chinese before?",
                "Are you studying Chinese before?",
                "Have you studied Chinese before?",
                "c"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "I can do that for you",
                "I could do that",
                "I could to make that for you",
                "a"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "Are you going to University?",
                "Are you going to go to University?",
                "Do you like University?",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "You haven't to do that, you know",
                "You didn't have to do that, you know",
                "You didn't must do that, you know",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "How long is it from Hong Kong to Shanghai?",
                "How far is it from Hong Kong to Shanghai?",
                "How much is it from Hong Kong to Shanghai?",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "When we finish the painting, we'll have a cup of tea",
                "When we've finished the painting, we'll have a cup of tea",
                "When the painting finishes, we'll have a cup of tea",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option: He told her that...",
                "He would love her forever",
                "He loved her forever",
                "He is loving her forever",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "She asked the shop assistant to have a refund",
                "She asked the shop assistant to give a refund",
                "She asked the shop assistant for a refund",
                "c"
            )
        )
    }

    private fun level_b2() {
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "He told he wasn't feeling well",
                "He said he doesn't feeling well",
                "He said he wasn't feeling well",
                "с"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "We won't know how to do after we get the results",
                "When we get the results we won't know what to do",
                "We won't know what to do until we get the results",
                "с"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "If you wouldn't tell me, I'll scream!",
                "If you don't tell me, I'll scream!",
                "If you didn't tell me, I'll scream!",
                "b"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "He's probably lost her number",
                "He's lost her number, probably",
                "Probably, he's lost her numbe",
                "a"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "I'll only tell you, if you can keep a secret",
                "If you can keep a secret, I would tell you",
                "you can't keep a secret, if I did tell you",
                "a"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "What do you think we'll be doing in five years' time?",
                "What do you think you're doing in five years' time?",
                "What do you think we do in five years' time?",
                "a"
            )
        )

        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "I didn't do that if I were you",
                "I wouldn't do that if I were you",
                "I wouldn't do that if I was you",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "I usually to live in Paris, but now I live in Madrid",
                "I used to live in Paris, but now I live in Madrid",
                "I am used to live in Paris, but now I live in Madrid",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "This time tomorrow, I am in Tokyo",
                "This time tomorrow, I am being in Tokyo",
                "This time tomorrow, I will be in Tokyo",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "You didn't use to smoke, did you?",
                "You usen't to smoke, did you?",
                "You aren't used to smoke, did you?",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "He looked tired, because he had travelled all day",
                "He looked tired, because he had been travelling all day",
                "He looked tired, because he had travelling all day",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "Sarah is working on something done!",
                "Sarah is having some work done!",
                "Sarah is doing work done!",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "You must have drink something!",
                "You must have drinking!",
                "You must have been drinking!",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "She's so nice a woman!",
                "She' s such a nice woman!",
                "She's a so nice woman!",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Choose the correct option:",
                "I was told the party was next Friday",
                "I told the party was next Friday",
                "told them the party was next Friday",
                "a"
            )
        )
    }

    private fun level_c1() {
        questionBundle!!.add(
            QuestionModel(
                "It was really nice _______ you to invite me.",
                "for",
                "of",
                "to",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "The house has been _______ the market for a while.",
                "by",
                "to",
                "on",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "I'm not very good _______ maths.",
                "with",
                "on",
                "at",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "You are _______ no obligation to go.",
                "to",
                "with",
                "under",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "He is _______ charge of the whole department.",
                "at",
                "in",
                "on",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Robert is an authority _______ English literature.",
                "in",
                "on",
                "over",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "You must be responsible _______ your decisions.",
                "for",
                "of",
                "to",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "I'm very concerned _______ his smoking.",
                "againts",
                "with",
                "about",
                "c"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "Could you deal _______ this problem later, please.",
                "for",
                "with",
                "from",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "I want to protest _______ the state of the building.",
                "over",
                "on",
                "about",
                "c"
            )
        )
    }

    private fun level_c2() {
        questionBundle!!.add(
            QuestionModel(
                "The fact of the _________ is we have a government that will do what it wants to do for the next two years.",
                "matter",
                "problem",
                "situation",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "The thing _________ , once a film gets this kind of apocalyptically awful publicity, how do you not release it and give the public a crack at it?",
                "is",
                "what",
                "goes",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "_________, I still was able to get to the top of the mountain.",
                "Even though unfit",
                "Unfit as I am",
                "While ever out of condition",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "She managed to pull the horse up is spite of ________ a broken rein.",
                "having",
                "had",
                "have",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "The main argument in the report is _________ correct.",
                "up to a point",
                "fundamentally",
                "primarily",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "I think going for the weekend to the coast _________ be a good idea.",
                "bound to",
                "would",
                "should",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "There was a problem with the cellphone, but I _________ it now.",
                "have fixed",
                "fixed",
                "have been fixing",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "There are about twenty _________ so people waiting in the outer office.",
                "or",
                "but",
                "and",
                "a"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "As a cab driver, I _________, though Iâ€™d love a bigger paycheck.",
                "take on",
                "get by",
                "get on",
                "b"
            )
        )
        questionBundle!!.add(
            QuestionModel(
                "The main argument in the report is _________ correct.",
                "up to a point",
                "primarily",
                "fundamentally",
                "c"
            )
        )
    }

    private fun typeCastObject() {
        questionBundle = ArrayList()
        question_text_tv = findViewById(R.id.question_text)
        question_counter_tv = findViewById(R.id.question_counter)
        option_group = findViewById(R.id.option_group)
        option_a = findViewById(R.id.option_a)
        option_b = findViewById(R.id.option_b)
        option_c = findViewById(R.id.option_c)
        submit_btn = findViewById(R.id.btn_submit)
        level_tv = findViewById(R.id.level_textview)
        test_description_tv = findViewById(R.id.test_description_textview)

        test_points_tv = findViewById(R.id.test_points_textView)
        test_current_average_tv = findViewById(R.id.current_test_average_textView)
    }

    private fun mCustomToast(image: Int) {
        val inflater = this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_toast_layout, null, false)
        val imageView = view.findViewById<GifImageView>(R.id.custom_toast_image_view)
        imageView.setImageResource(image)

        val mToast = Toast(this)
        mToast.view = view
        mToast.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL, 0, 0)
        mToast.duration = Toast.LENGTH_SHORT
        mToast.show()
    }


    private fun setDataToSharedPreferences(level_name: String, value: Float) {
        val setting = this.getSharedPreferences(level_name, MODE_PRIVATE)
        val editor = setting.edit()
        editor.putFloat(level_name, value)
        editor.commit()
    }

    val dataFromSharedPreferences: Float
        get() {
            val setting_a1 = this.getSharedPreferences("a1_average", MODE_PRIVATE)
            a1_average = setting_a1.getFloat("a1_average", 0f)

            val setting_a2 = this.getSharedPreferences("a2_average", MODE_PRIVATE)
            a2_average = setting_a2.getFloat("a2_average", 0f)

            val setting_b1 = this.getSharedPreferences("b1_average", MODE_PRIVATE)
            b1_average = setting_b1.getFloat("b1_average", 0f)

            val setting_b2 = this.getSharedPreferences("b2_average", MODE_PRIVATE)
            b2_average = setting_b2.getFloat("b2_average", 0f)

            val setting_c1 = this.getSharedPreferences("c1_average", MODE_PRIVATE)
            c1_average = setting_c1.getFloat("c1_average", 0f)

            val setting_c2 = this.getSharedPreferences("c2_average", MODE_PRIVATE)
            c2_average = setting_c2.getFloat("c2_average", 0f)


            test_overall_average =
                (a1_average + a2_average + b1_average + b2_average + c1_average + c2_average) / 600 * 100


            return test_overall_average
        }
}