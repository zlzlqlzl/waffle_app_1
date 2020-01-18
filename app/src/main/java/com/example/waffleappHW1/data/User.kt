package com.example.waffleappHW1.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val username: String,
    val email: String
) : Parcelable