package com.spectrum.demo.specapp.common.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.spectrum.demo.specapp.common.data.converter.NameConverter

@Entity(tableName = "member")
class Member {

    @PrimaryKey
    @SerializedName("_id")
    var id: String = ""

    @TypeConverters(NameConverter::class)
    lateinit var name : Name

    var age : Int =0
    var email : String = ""
    var phone : String = ""
    var favorite : Boolean = false

    var companyId : String = ""
}