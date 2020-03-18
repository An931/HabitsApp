import android.graphics.Color

class Habit(
    var name: String,
    var descriptor: String,
    var priority: Int,
    var type: HabitType,
    var periodicity: String,
    var color: Int
)
//var h = Habit("","",0,"","","")
//var t = h.
//название, описание, приоритет, тип, периодичность, цвет

enum class HabitType(type:String) {
    Good("good"),
    Bad("bad"),
    Neutral("neutral")
}

//enum class Color(val rgb: String) {
//    Red("f00"),
//    Green("000"),
//    Blue("000")
//}

enum class Periodicity {
    Hour, Day, Week, Month, Year
} //change to times-per-smth