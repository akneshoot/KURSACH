package com.example.happyenglish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView

class PDFViewer : AppCompatActivity() {
    var mPdfView: PDFView? = null

    private var child_id = 0
    private var group_id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)

        mPdfView = findViewById(R.id.pdfView)

        child_id = intent.getIntExtra("child_id", 0)
        group_id = intent.getIntExtra("group_id", 0)

        val fileName = getFileName(group_id, child_id)

        if (fileName.isNotEmpty()) {
            mPdfView?.fromAsset(fileName)?.load()
        } else {
            // Обработка случаев, когда файл не найден
        }
    }

    private fun getFileName(groupId: Int, childId: Int): String {
        return when (groupId) {
            0 -> "1000_words.pdf"
            1 -> when (childId) {
                0 -> "present_simple.pdf"
                1 -> "present_continuous.pdf"
                2 -> "present_perfect.pdf"
                3 -> "present_perfect_continuous.pdf"
                4 -> "past_simple.pdf"
                5 -> "past_continuous.pdf"
                6 -> "past_perfect.pdf"
                7 -> "past_perfect_continuous.pdf"
                8 -> "future_simple.pdf"
                9 -> "future_continuous.pdf"
                10 -> "future_perfect.pdf"
                11 -> "future_perfect_continuous.pdf"
                else -> ""
            }
            2 -> when (childId) {
                0 -> "define_indefinite_article.pdf"
                else -> ""
            }
            3 -> when (childId) {
                0 -> "POSSESSIVES.pdf"
                else -> ""
            }
            4 -> when (childId) {
                0 -> "zero_conditional.pdf"
                1 -> "1_con.pdf"
                2 -> "2_con.pdf"
                3 -> "3_con.pdf"
                4 -> "mix_con.pdf"
                else -> ""
            }
            5 -> when (childId) {
                0 -> "gerunds.pdf"
                1 -> "infinitives.pdf"
                2 -> "infinitives_or_gerund.pdf"
                else -> ""
            }
            6 -> when (childId) {
                0 -> "defining.pdf"
                1 -> "non_defing.pdf"
                else -> ""
            }
            7 -> when (childId) {
                0 -> "direct.pdf"
                1 -> "reporting_ques.pdf"
                2 -> "repoting_commands.pdf"
                else -> ""
            }
            8 -> when (childId) {
                0 -> "full_verb.pdf"
                1 -> "aux_v.pdf"
                2 -> "modal_aux.pdf"
                else -> ""
            }
            9 -> when (childId) {
                0 -> "idioms.pdf"
                1 -> "metaphors.pdf"
                2 -> "similes.pdf"
                else -> ""
            }
            else -> ""
        }
    }
}
