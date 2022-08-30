package com.example.elegant_app.staff

import android.net.Uri
import android.os.Parcelable
import java.io.Serializable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudentModel(
    var reg_no:String?=null,
    var name:String,
    var school:String?=null,
    var parent: String?=null,
    var mobile: String?=null,
    var email: String?=null,
    var blood: String?=null,
    var dob: String?=null,
    var adhar: String?=null,
    var gender: String?=null,
    var address: String?=null,
    var standard: String?=null,
    var division: String?=null,
    var medium: String?=null,
    var photo: String?=null,
    var doc_id: String?=null
    ):Parcelable
