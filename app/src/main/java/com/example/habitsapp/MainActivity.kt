package com.example.habitsapp

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import kotlinx.android.synthetic.main.activity_main.fab
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    val dbOpenHelper = DbOpenHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fillDataBase()
        //dbOpenHelper.cleanDb()

        if (savedInstanceState == null) {
            val vp = findViewById<ViewPager>(R.id.viewpager)
            vp.adapter = HabitsFragmentPagerAdapter(
                supportFragmentManager,
                this,
                dbOpenHelper.getAllHabits()
            )

            val st = findViewById<TabLayout>(R.id.sliding_tabs)
            st.setupWithViewPager(vp)
        }


//        val t = "android:switcher:" + R.id.viewpager + ":" + 1
//        val curF = supportFragmentManager.findFragmentByTag(t)
//        val l = curF?.view?.findViewById<LinearLayout>(R.id.habitsLayout)
//        val habits = dbOpenHelper.getAllHabits()
//        habits.forEach { l?.addView(it.getView(this)) }

//        val fs = supportFragmentManager.fragments
//
//        supportFragmentManager.fragments.forEach {
//            val l = it.view?.findViewById<LinearLayout>(R.id.habitsLayout)
//            val habits = dbOpenHelper.getAllHabits()
//            habits.forEach { l?.addView(it.getView(this)) }
//        }


//        if (intent.hasExtra("habitId")) {
//            val id = intent.getStringExtra("habitId")
//            val name = intent.getStringExtra("name")
//            val description = intent.getStringExtra("description")
//            val priority = intent.getStringExtra("priority")
//            val type = intent.getStringExtra("type")
//            val periodicity = intent.getStringExtra("periodicity")
//            val color = intent.getStringExtra("color")
//            val h = Habit(
//                name,
//                description,
//                priority.toInt(),
//                HabitType.valueOf(type),
//                periodicity,
//                color
//            )
//            h.id = id.toLong()
//            dbOpenHelper.addToDB(h)
//        }


//        val habits = dbOpenHelper.getAllHabits()
//        var layout = findViewById<LinearLayout>(R.id.habitsLayout)
//        habits.forEach { layout.addView(it.getView(this)) }


//        val fs = supportFragmentManager.fragments

        fab.setOnClickListener {
            Log.d(TAG, "toCreateButton")
//            val fs = supportFragmentManager.fragments

//            val habits = dbOpenHelper.getAllHabits()
//            setToFragment(0, habits.filter { it.type == HabitType.Good })
//            setToFragment(1, habits.filter { it.type == HabitType.Bad })

//            val intent = Intent(this, HabitActivity::class.java)
//            startActivity(intent);

//            val creationFragment = HabitCreationFragment.newInstance()
//            creationFragment.show(supportFragmentManager, "t")
            showHabitCreationFragment()
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.creationFragment, creationFragment, "crFragment")
//                .commit()

        }
        Log.d(TAG, "onCreate")
    }

    fun showHabitCreationFragment() {
        val callback = object : HabitCreationCallback {
            override fun onHabitSave(habit: Habit) {
                dbOpenHelper.addToDB(habit)
                Log.d("HabCreateFragment", "onHabSave")
                //updateOther
            }
        }
        val creationFragment = HabitCreationFragment.newInstance(callback)
        creationFragment.show(supportFragmentManager, "t")
    }

//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG, "onStart")
//    }
//
//    override fun onResume() {
//        val s = supportFragmentManager
//        val fs1 = supportFragmentManager.fragments
//
//        super.onResume()
//        val fs = supportFragmentManager.fragments
//
//        Log.d(TAG, "onResume")
//
//    }

//    private fun setToFragment(position: Int, habits: List<Habit>) {
//        val tag = "android:switcher:" + R.id.viewpager + ":" + position
//        val fragment = supportFragmentManager.findFragmentByTag(tag)
//        val layout = fragment?.view?.findViewById<LinearLayout>(R.id.habitsLayout)
//        habits.forEach { layout?.addView(it.getView(this)) }
//    }

    fun fillDataBase() {

//        dbWriter.delete(dbHelper.TABLE_NAME, "NAME=?", listOf<String>("n25").toTypedArray())
        //dbWriter.delete(dbHelper.TABLE_NAME, null, null)

//        val cv = ContentValues()
//        cv.put(dbHelper.NAME, "n25")
//        cv.put(dbHelper.DESCRIPTION, "des11")
//        cv.put(dbHelper.PRIORITY, "5")
//        cv.put(dbHelper.TYPE, "Bad")
//        cv.put(dbHelper.PERIODICITY, 2)
//        cv.put(dbHelper.COLOR, "cyan")
//        dbWriter.insert(dbHelper.TABLE_NAME, null, cv)
//        dbWriter.close()

        dbOpenHelper.addToDB(Habit("n1", "des", 3, HabitType.Good, "e", "black"))
        dbOpenHelper.addToDB(Habit("n2", "des", 6, HabitType.Bad, "e", "black"))

    }

}
