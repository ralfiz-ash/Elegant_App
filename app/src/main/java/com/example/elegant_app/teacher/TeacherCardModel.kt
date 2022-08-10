package com.example.elegant_app.teacher

import android.graphics.Color
import java.io.Serializable

data class TeacherCardModel(
    var id:Int,
    var title: String,
    var color: Int

): Serializable