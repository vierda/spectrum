package com.spectrum.demo.specapp.common.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.spectrum.demo.specapp.common.data.converter.MemberConverter

@Entity(tableName = "company")
class Company {

    @PrimaryKey
    @SerializedName("_id")
    var id: String = ""

    @SerializedName("company")
    var companyName: String = ""

    var website: String = ""
    var logo: String = ""
    var about: String = ""

    @TypeConverters(MemberConverter::class)
    lateinit var members: List<Member>

    var favorite: Boolean = false
    var follow: Boolean = false
}