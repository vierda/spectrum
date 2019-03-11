package com.spectrum.demo.specapp.common.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.spectrum.demo.specapp.common.data.model.Name

@Dao
interface NameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(name: Name)
}