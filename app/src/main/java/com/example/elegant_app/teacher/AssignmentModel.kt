package com.example.elegant_app.teacher
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AssignmentModel(
    var subject:String?=null,
    var topic:String?=null,
    var date:String?=null,
    var standard:String?=null,
):Parcelable
