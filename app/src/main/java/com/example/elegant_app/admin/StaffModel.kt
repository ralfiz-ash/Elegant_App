package com.example.elegant_app.admin

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StaffModel(

    var name:String,
    var mobile: String?=null,
    var email: String?=null,
    var blood: String?=null,
    var address: String?=null,
    var dob: String?=null,
    var gender:String?=null,
    var adhar: String?=null,
    var photo: String?=null,
):Parcelable
