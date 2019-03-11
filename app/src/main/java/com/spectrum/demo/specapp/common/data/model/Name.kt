package com.spectrum.demo.specapp.common.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "name")
class Name {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var first: String = ""
    var last: String = ""
}