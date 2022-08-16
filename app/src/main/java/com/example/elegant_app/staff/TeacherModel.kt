package com.example.elegant_app.staff

import android.net.Uri
import android.provider.ContactsContract

data class TeacherModel(
    var id:Int,
    var name:String,
    var qualification:String?=null,
    var experience: Float?=null,
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
    var photo: Uri?=null,

)
