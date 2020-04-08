package com.example.habitsapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit")
    fun getAll(): List<Habit>

//    @Query("SELECT * FROM habit WHERE type = :type")
//    fun getAllByType(type:HabitType): List<Habit>


//    @Query("SELECT * FROM habit WHERE id IN (:habitIds)")
//    fun getAllByIds(habitIds: IntArray): List<Habit>

//    @Query("SELECT * FROM habit WHERE title LIKE :title LIMIT 1")
//    fun findByTitle(title: String): Feed

    @Insert
    fun insert(habit: Habit)

    @Insert
    fun insertAll(vararg habit: Habit)

    @Delete
    fun delete(habit: Habit)
}

@Database(entities = arrayOf(Habit::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}
