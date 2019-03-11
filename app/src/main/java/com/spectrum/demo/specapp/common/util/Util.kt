package com.spectrum.demo.specapp.common.util

import android.content.Context
import android.graphics.PorterDuff
import android.widget.ImageView
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class Util {

    companion object {
        var gson = initGson()

        fun initGson(): Gson {
            val builder = GsonBuilder()
            if (gson == null)
                gson = builder.create()
            return gson
        }

         fun changeColor(isChecked: Boolean, view: ImageView,
                                checkedColor: Int, context:Context) {
            if (isChecked)
                view.setColorFilter(context.getColor(checkedColor), PorterDuff.Mode.SRC_IN)
            else
                view.clearColorFilter()
        }
    }


}