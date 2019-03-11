package com.spectrum.demo.specapp.common.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.spectrum.demo.specapp.common.data.dao.CompanyDao
import com.spectrum.demo.specapp.common.data.dao.MemberDao
import com.spectrum.demo.specapp.common.data.dao.NameDao
import com.spectrum.demo.specapp.common.data.model.Company
import com.spectrum.demo.specapp.common.data.model.Member
import com.spectrum.demo.specapp.common.data.model.Name

@Database(entities = [Company::class, Member::class, Name::class], version=1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun CompanyDao(): CompanyDao
    abstract fun MemberDao(): MemberDao
    abstract fun NameDao(): NameDao
}