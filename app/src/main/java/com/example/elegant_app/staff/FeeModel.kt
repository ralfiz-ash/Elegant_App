package com.example.elegant_app.staff
import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeeModel(
    var s_name:String,
    var std:String,
    var amount:String,
    var date:String?=null,
    var month:String?=null,
):Parcelable
