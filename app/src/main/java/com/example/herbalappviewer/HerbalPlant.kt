package com.example.herbalappviewer

import android.os.Parcel
import android.os.Parcelable

data class HerbalPlant(
    val name: String,
    val description: String,
    val backgroundImageResId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(backgroundImageResId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HerbalPlant> {
        override fun createFromParcel(parcel: Parcel): HerbalPlant {
            return HerbalPlant(parcel)
        }

        override fun newArray(size: Int): Array<HerbalPlant?> {
            return arrayOfNulls(size)
        }
    }
}

