package com.example.happyenglish

import CustomExpandableListViewAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.text.Html
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.example.happyenglish.WORD
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var mViewpager: ViewPager? = null

    var btnListWord: Button? = null
    var btnStart: Button? = null
    var btnTranslation: Button?=null
    var btnSettings: Button?=null

    var btnExit: Button? = null

    var btnMenu: FloatingActionButton? = null
    var mDrawer: DrawerLayout? = null

    //
    var mExpandableListView: ExpandableListView? = null
    var mExpandableListAdapter: ExpandableListAdapter? = null

    var list_group: List<String>? = null
    var list_children: HashMap<String, List<String>>? = null

    //
    private var mActionBarDrawerToggle: ActionBarDrawerToggle? = null

    private var lastExpandedPosition = -1

    //dotLayout
    private var dotsLayout: LinearLayout? = null
    private lateinit var dotsView: Array<TextView?>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addButtonListener()

        typcast_my_object()

        val mHeadings = resources.getStringArray(R.array.headings)
        val mDescriptions = resources.getStringArray(R.array.descriptions)
        val mCustomerslider = CustomSlider(this, mHeadings, mDescriptions)

        mViewpager!!.adapter = mCustomerslider

        btnMenu!!.setOnClickListener {
            if (mDrawer!!.isDrawerOpen(Gravity.LEFT)) {
                mDrawer!!.closeDrawer(Gravity.LEFT)
            } else {
                mDrawer!!.openDrawer(Gravity.LEFT)
            }
        }







        mExpandableListAdapter = CustomExpandableListViewAdapter(this, list_group!!, list_children!!)
        mExpandableListView!!.setAdapter(mExpandableListAdapter)


        mExpandableListView!!.setOnChildClickListener { expandableListView, view, groupPosition, childPosition, l ->
            if (groupPosition == 10) {
                val intent = Intent(this@MainActivity, GrammarTestActivity::class.java)
                intent.putExtra("child_id", childPosition)
                startActivity(intent)
            } else if (groupPosition != 10) {
                val intent = Intent(this@MainActivity, PDFViewer::class.java)
                intent.putExtra("child_id", childPosition)
                intent.putExtra("group_id", groupPosition)
                startActivity(intent)
            }
            true
        }

        mExpandableListView!!.setOnGroupExpandListener { groupPosition ->
            if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                mExpandableListView!!.collapseGroup(lastExpandedPosition)
            }
            lastExpandedPosition = groupPosition
        }


        mActionBarDrawerToggle = object :
            ActionBarDrawerToggle(this, mDrawer, R.string.drawer_open, R.string.drawer_closer) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                Toast.makeText(this@MainActivity, R.string.drawer_open, Toast.LENGTH_SHORT).show()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                Toast.makeText(this@MainActivity, R.string.drawer_closer, Toast.LENGTH_SHORT).show()
            }
        }
        mDrawer!!.setDrawerListener(mActionBarDrawerToggle)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        sliderDotCreator(0)
        mViewpager!!.addOnPageChangeListener(onPageChangeListener)
    }


    @SuppressLint("WrongViewCast")
    private fun addButtonListener() {
        btnListWord = findViewById<View>(R.id.btnWord) as Button
        btnListWord!!.setOnClickListener {
            val e = Intent(this@MainActivity, WORD::class.java)
            startActivity(e)
        }
        btnStart = findViewById<View>(R.id.Start) as Button
        btnStart!!.setOnClickListener {
            val e = Intent(this@MainActivity, SPEECH::class.java)
            startActivity(e)
        }
        btnTranslation =  findViewById<View>(R.id.btnTranslation) as Button
        btnTranslation!!.setOnClickListener{
            val e = Intent(this@MainActivity, Translation::class.java)
            startActivity(e)
        }
        btnSettings =  findViewById<View>(R.id.btnSettings) as Button
        btnSettings!!.setOnClickListener{
            val e = Intent(this@MainActivity, com.example.happyenglish.Settings::class.java)
            startActivity(e)
        }
        btnExit = findViewById<View>(R.id.Exit) as Button
        btnExit!!.setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                "You have logged out of the profile.",
                Toast.LENGTH_SHORT
            ).show()
            Toast.makeText(this@MainActivity, "Please log in to the app.", Toast.LENGTH_SHORT)
                .show()
            val e = Intent(this@MainActivity, Vhod::class.java)
            startActivity(e)
        }
    }


    private fun typcast_my_object() {
        mViewpager = findViewById(R.id.viewPager)
        btnMenu = findViewById(R.id.btn_menu)
        mDrawer = findViewById(R.id.mDrawer)

        mExpandableListView = findViewById(R.id.expandable_listview)
        list_children = ExpandableListData.getData()
        list_group = ArrayList(list_children!!.keys)


        dotsLayout = findViewById(R.id.dotsLayout)
    }

    override fun onPostCreate(saveInstanceState: Bundle?) {
        super.onPostCreate(saveInstanceState)
        mActionBarDrawerToggle!!.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (mActionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sliderDotCreator(position: Int) {
        dotsView = arrayOfNulls(7)
        dotsLayout!!.removeAllViews()

        for (i in dotsView.indices) {
            dotsView[i] = TextView(this)
            dotsView[i]!!.text = Html.fromHtml("&#8226;")
            dotsView[i]!!.textSize = 35f
            dotsView[i]!!.setTextColor(resources.getColor(R.color.teal_700))
            dotsLayout!!.addView(dotsView[i])
        }
        if (dotsView.size > 0) {
            dotsView[position]!!.setTextColor(Color.LTGRAY)
            dotsView[position]!!.textSize = 40f
        }
    }


    var onPageChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                sliderDotCreator(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        }
}