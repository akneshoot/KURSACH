package com.example.happyenglish

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.PagerAdapter

class CustomSlider(
    var context: Context,
    var headings: Array<String>,
    var descriptions: Array<String>
) : PagerAdapter() {
    var images: IntArray = intArrayOf(
        R.drawable.tense,
        R.drawable.conditional,
        R.drawable.gerund,
        R.drawable.relative,
        R.drawable.reported,
        R.drawable.idiom,
        R.drawable.quiz
    )


    override fun getCount(): Int {
        return headings.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.main_slider, container, false)

        val slider_image = view.findViewById<ImageView>(R.id.slider_image_view)
        val slider_heading = view.findViewById<TextView>(R.id.slider_heading)
        val slider_description = view.findViewById<TextView>(R.id.slider_description)

        slider_image.setImageResource(images[position])
        slider_heading.text = headings[position]
        slider_description.text = descriptions[position]

        slider_image.setOnClickListener { sliderPromptDialog(position) }

        container.addView(view)
        return view
    }

    private fun sliderPromptDialog(position: Int) {
        val builder = AlertDialog.Builder(
            context
        )

        builder.setTitle("Make a choice")
        val slider_title_arrays = intArrayOf(
            R.array.slider_tenses_titles,
            R.array.slider_conditional_titles,
            R.array.slider_title_gerunds,
            R.array.slider_title_relatives,
            R.array.slider_title_directspeech,
            R.array.slider_idiom_titles,
            R.array.slider_test_titles
        )
        builder.setItems(slider_title_arrays[position]) { dialogInterface, i ->
            if (position == 0) {
                val intent = Intent(this@CustomSlider.context, PDFViewer::class.java)
                intent.putExtra("group_id", 1)
                intent.putExtra("child_id", i)
                context.startActivity(intent)
            } else if (position == 1) {
                val intent = Intent(this@CustomSlider.context, PDFViewer::class.java)
                intent.putExtra("group_id", 4)
                intent.putExtra("child_id", i)
                context.startActivity(intent)
            } else if (position == 2) {
                val intent = Intent(this@CustomSlider.context, PDFViewer::class.java)
                intent.putExtra("group_id", 5)
                intent.putExtra("child_id", i)
                context.startActivity(intent)
            } else if (position == 3) {
                val intent = Intent(this@CustomSlider.context, PDFViewer::class.java)
                intent.putExtra("group_id", 6)
                intent.putExtra("child_id", i)
                context.startActivity(intent)
            } else if (position == 4) {
                val intent = Intent(this@CustomSlider.context, PDFViewer::class.java)
                intent.putExtra("group_id", 7)
                intent.putExtra("child_id", i)
                context.startActivity(intent)
            } else if (position == 5) {
                val intent = Intent(this@CustomSlider.context, PDFViewer::class.java)
                intent.putExtra("group_id", 9)
                intent.putExtra("child_id", i)
                context.startActivity(intent)
            } else if (position == 6) {
                val intent = Intent(this@CustomSlider.context, GrammarTestActivity::class.java)
                intent.putExtra("group_id", 10)
                intent.putExtra("child_id", i)
                context.startActivity(intent)
            }
            Toast.makeText(context, "You have selected the item " + (i + 1), Toast.LENGTH_SHORT)
                .show()
        }

        builder.show()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}
