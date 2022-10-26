package com.example.elegant_app.staff

import android.os.Parcelable

data class teacher(var name:String?=null)
data class student(var name:String?=null)
data class staff(var name:String?=null)


data class TimetableModel(
    var date:String?=null,
    var standard:String?=null,
    var subject:String?=null,
    var time:String?=null,
    var endtime:String?=null,
    var tutor:String?=null,
)

data class StudyModel(
    var subject:String?=null,
    var standard:String?=null,
    var title:String?=null,
    var link:String?=null,
    var pdf:String?=null
)

data class ExamModel(
    var examName:String?=null
)

data class AdModel(
    var imageUrl:String?=null,
    var id:String?=null
)



data class ScoreModel(
    var reg_no: String? = null,
    var exam: String? = null,
    var subject: String? = null,
    var medium: String? = null,
    var standard: String? = null,
    var mark: String? = null,
    val studentname: String?
)

data class AlumniTeacherModel(
    var name:String,
    var qualification:String?=null,
    var experience: String?=null,
    var mobile: String?=null,
    var email: String?=null,
    var blood: String?=null,
    var address: String?=null,
    var dob: String?=null,
    var gender:String?=null,
    var adhar: String?=null,
    var standard: String?=null,
    var medium: String?=null,
    var division: String?=null,
    var photo: String?=null,
    var doc_id: String?=null,
)
data class AlumniStudentModel(
    var reg_no:String?=null,
    var name:String,
    var school:String?=null,
    var parent: String?=null,
    var mobile: String?=null,
    var email: String?=null,
    var blood: String?=null,
    var dob: String? = null,
    var adhar: String? = null,
    var gender: String? = null,
    var address: String? = null,
    var standard: String? = null,
    var division: String? = null,
    var medium: String? = null,
    var photo: String? = null,
    var doc_id: String? = null
)

data class AlumniStaffModel(
    var name: String,
    var mobile: String? = null,
    var email: String? = null,
    var blood: String? = null,
    var address: String? = null,
    var dob: String? = null,
    var gender: String? = null,
    var adhar: String? = null,
    var photo: String? = null,
    var doc_id: String? = null,
)
