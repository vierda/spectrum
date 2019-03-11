package com.spectrum.demo.specapp.common.data.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.spectrum.demo.specapp.common.data.model.Name
import com.spectrum.demo.specapp.common.util.Util

class NameConverter {

    internal var gson = Util.initGson()

    @TypeConverter
    fun getName(data: String): Name {
        val type = object : TypeToken<Name>() {

        }.type
        return gson.fromJson<Name>(data, type)
    }

    @TypeConverter
    fun getStringFromName(data: Name): String {
        return gson.toJson(data)
    }
}