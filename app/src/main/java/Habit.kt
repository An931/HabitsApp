class Habit(
    var name: String,
    var descriptor: String,
    var priority: Int,
    var type: HabitType,
    var periodicity: String,
    var color: String
){
    var id :Long = -1
}

//название, описание, приоритет, тип, периодичность, цвет

enum class HabitType {
    Good,
    Bad,
    Neutral,
}

//enum class Color(val rgb: String) {
//    Red("f00"),
//    Green("000"),
//    Blue("000")
//}

enum class Periodicity {
    Hour, Day, Week, Month, Year
} //change to times-per-smth