package com.example.waffleappHW1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val username: String,
    val email: String
) : Parcelable