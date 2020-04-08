package com.example.habitsapp

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue.*
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

import java.text.FieldPosition

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val TAG = "MainActivity"

//    val dbOpenHelper = DbOpenHelper(this)


//    val habitsModel = HabitsModel(db)


    private lateinit var navigationView: NavigationView
    private lateinit var habitsModel:HabitsModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        fillDataBase()
//        dbOpenHelper.cleanDb()

        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "habit"
        ).allowMainThreadQueries().build()
        habitsModel = HabitsModel(db.habitDao())


        if (savedInstanceState == null) {
            val vp = findViewById<ViewPager>(R.id.viewpager)
            vp.adapter = HabitsFragmentPagerAdapter(
                supportFragmentManager,
                habitsModel
            )

            val st = findViewById<TabLayout>(R.id.sliding_tabs)
            st.setupWithViewPager(vp)
        }


        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.create_your_habit,
            R.string.create_your_habit
        )
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        this.navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)


        fab.setOnClickListener {
            Log.d(TAG, "toCreateButton")
            showHabitCreationFragment()
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.creationFragment, creationFragment, "crFragment")
//                .commit()
        }
        Log.d(TAG, "onCreate")
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.nav_item_about ->AboutAppFragment.newInstance().show(supportFragmentManager, "")
            R.id.nav_item_home -> ""
        }
        return true
    }


    fun showHabitCreationFragment() {
        val creationFragment = HabitCreationFragment.newInstance(habitsModel)
        creationFragment.show(supportFragmentManager, "t")
    }


    private fun fillDataBase() {
//        db.habitDao().insertAll(
//            Habit("k", "j", 3, HabitType.Good, "j", "white")
//        )
//        dbOpenHelper.addToDB(Habit("n1", "des", 3, HabitType.Good, "e", "black"))
//        dbOpenHelper.addToDB(Habit("n2", "des", 6, HabitType.Bad, "e", "black"))

    }

}


