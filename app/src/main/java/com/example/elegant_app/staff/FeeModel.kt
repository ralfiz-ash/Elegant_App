package com.example.elegant_app.staff

data class FeeModel(
    var id:Int,
    var s_name:String,
    var std:String,
    var amount:Float,
    var date:String?=null,
    var month:String?=null,
)
