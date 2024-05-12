package com.example.happyenglish

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class IntroViewPagerAdapter(var context: Context, var introList: List<Intro>) : PagerAdapter() {
    override fun getCount(): Int {
        return introList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.intro_screen_layout, null)

        val image = view.findViewById<ImageView>(R.id.imageView)
        val title = view.findViewById<TextView>(R.id.title)
        val desc = view.findViewById<TextView>(R.id.desc)


        image.setImageResource(introList[position].imageUrl)
        title.text = introList[position].title
        desc.text = introList[position].desc
        container.addView(view)
        return view
    }
}
