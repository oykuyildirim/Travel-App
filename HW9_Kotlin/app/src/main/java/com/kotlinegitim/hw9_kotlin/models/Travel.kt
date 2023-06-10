package com.kotlinegitim.hw9_kotlin

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Travel( val city: String? = null,val note: String? = null)

data class TravelDetail (val title: String? = null , val travel: Travel?)

data class Travels( val uid:String? = null, val traveldetail : TravelDetail?)