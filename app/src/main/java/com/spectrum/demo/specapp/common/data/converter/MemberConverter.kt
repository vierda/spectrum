package com.spectrum.demo.specapp.common.data.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.spectrum.demo.specapp.common.data.model.Member
import com.spectrum.demo.specapp.common.util.Util

class MemberConverter {

    internal var gson = Util.initGson()

    @TypeConverter
    fun getListMembers(data: String?): List<Member> {
        if (data == null) {
            return emptyList<Member>()
        }

        val listType = object : TypeToken<List<Member>>(){}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun getStringFromListMembers(data: List<Member>): String {
        return gson.toJson(data)
    }
}