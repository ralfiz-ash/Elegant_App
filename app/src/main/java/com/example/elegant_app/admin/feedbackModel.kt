package com.example.elegant_app.admin
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class feedbackModel(
    var s_id:String,
    var content:String,
):Parcelable

