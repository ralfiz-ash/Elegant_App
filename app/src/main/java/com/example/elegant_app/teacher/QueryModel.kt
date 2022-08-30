package com.example.elegant_app.teacher
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QueryModel(
    var id: String?=null,
    var student:String?=null,
    var question:String?=null,
    var answer:String?=null
):Parcelable
