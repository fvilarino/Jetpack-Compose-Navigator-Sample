package com.francescsoftware.navigation.ui.three

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ThreeOptions(val value: Int) : Parcelable {
    One(0),
    Two(1),
    Three(2);

    fun serialize(): String = value.toString()

    companion object {
        fun deserialize(value: String) = value.toInt().let { intValue ->
            values().first { it.value == intValue }
        }
    }
}
