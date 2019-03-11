package com.spectrum.demo.specapp

import android.app.Application
import android.arch.persistence.room.Room
import com.spectrum.demo.specapp.common.data.database.AppDatabase
import com.spectrum.demo.specapp.common.network.WebApiService

class SpecApplication : Application() {

    companion object {

        lateinit var _instance: SpecApplication

        fun getInstance(): SpecApplication {
            return _instance
        }
    }

    private val DATABASE_NAME = "SpecDb"
    private var database: AppDatabase? = null
    private var webservice : WebApiService? = null


    override fun onCreate() {
        super.onCreate()
        _instance = this

        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, DATABASE_NAME)
                .build()

        webservice = WebApiService.create()
    }

    fun getDatabase(): AppDatabase? {
        return database
    }

    fun getWebService(): WebApiService?{
        return webservice
    }
}