package com.example.elagant.pojo


data class TeacherModel(
    var name: String,
    var std: String,
    var mobile: String,
    var photo: String,
    var medium: String,
    var sub: String
) {
}

data class Doubtmodel(var question: String, var student: String, var answer: String) {
}

data class complaintModel(var complaint: String, var sid: String) {
}

data class NotifyModel(var announcement: String, var dates: String) {
}

data class DutyModel(var subject: String, var topic: String, var date1: String, var std: String) {
}

data class TimetableModel(
    var date: String,
    var std: String,
    var subject: String,
    var time: String,
    var sname: String
) {
}

data class StudyModel(var title: String, var subject: String, var link: String) {
}

data class ExamModel(var subject: String, var score: String) {
}