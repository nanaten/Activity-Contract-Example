package com.nanaten.activity_contract_example

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (val name: String, val attr: Int): Parcelable {
    enum class Attribute(val id: Int, val res: Int, val value: String) {
        ADULT(0, R.id.adult, "大人"),
        CHILD(1, R.id.child, "子ども")
    }
}