package com.example.habitsapp.models
//
//class Habit : Model {
//
//    @Column(name = "title")
//    public var title: String? = null
//    @Column(name = "text")
//    public var text: String? = null
//    @Column(name = "create_date")
//    public var createDate: Date? = null
//    @Column(name = "change_date")
//    public var changeDate: Date? = null
//
//    constructor(title: String, createDate: Date, changeDate: Date) {
//        this.title = title
//        this.createDate = createDate
//        this.changeDate = changeDate
//    }
//
//    constructor()
//
//    fun getInfo(): String = "Название:\n$title\n" +
//            "Время создания:\n${formatDate(createDate)}\n" +
//            "Время изменения:\n${formatDate(changeDate)}";
//}