package com.example.habitsapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    @ColumnInfo var name: String,
    @ColumnInfo var descriptor: String,
    @ColumnInfo var priority: Int,
    @ColumnInfo var type: String,
    @ColumnInfo var periodicity: String,
    @ColumnInfo var color: String
) {
    //    var id :Long = -1
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


enum class HabitType {
    Good,
    Bad,
    Neutral,
}

enum class Periodicity {
    Hour, Day, Week, Month, Year
} //change to times-per-smth

//fun getView(context: Context?): LinearLayout {
//    val layout = LinearLayout(context)
//    layout.setOrientation(LinearLayout.VERTICAL)
//    val texts = listOf(
//        name,
//        descriptor,
//        priority.toString(),
//        type.toString(),
//        periodicity
//    )
//
//    texts.forEach {
//        val tv = TextView(context)
//        val color =
//            try {
//                Color.parseColor(color)}
//            catch(e:Exception){
//                Color.parseColor("red")}
//        tv.setTextColor(color)
//        tv.text = (it + " ")
//        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30.toFloat())
//        layout.addView(tv)
//    }
////        layout.setOnClickListener {
////            val intent = Intent(this, HabitActivity::class.java)
////            intent.putExtra("habitId", habit.id)
////            intent.putExtra("name", habit.name)
////            intent.putExtra("description", habit.descriptor)
////            intent.putExtra("priority", habit.priority)
////            intent.putExtra("type", habit.type.toString())
////            intent.putExtra("periodicity", habit.periodicity)
////            intent.putExtra("color", habit.color)
////            startActivity(intent);
////        }
//    return layout
//}
