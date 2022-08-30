package com.example.elegant_app.staff

import android.net.Uri
import android.os.Parcelable
import android.provider.ContactsContract
import java.io.Serializable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeacherModel(
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
):Parcelable
