package com.example.waffleappHW1.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val title: String,
    val thumbnailUrl: String,
    val url: String
) : Parcelable
