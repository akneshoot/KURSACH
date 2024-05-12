package com.example.happyenglish

class QuestionModel(
    @JvmField var question_text: String,
    @JvmField var option_a: String,
    @JvmField var option_b: String,
    @JvmField var option_c: String,
    var correct_option: String
) {
    var isIs_score_already_given: Boolean = false
        private set

    fun setIs_score_already_given(is_score_already_given: Boolean) {
        this.isIs_score_already_given = is_score_already_given
    }


    fun is_answer_correct(selected_option: String): Boolean {
        return (correct_option == selected_option)
    }
}
