package com.spectrum.demo.specapp.common.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.spectrum.demo.specapp.common.data.model.Company

@Dao
interface CompanyDao {

    @Query("SELECT count(*) FROM company")
    abstract fun getDataCount():Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(companies: List<Company>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(company: Company)

    @Query("SELECT * FROM company")
    abstract fun selectAllCompanies(): List<Company>

    @Query("UPDATE company SET favorite = :isFavorite WHERE id = :companyId")
    abstract fun updateFavorite(isFavorite: Boolean, companyId: String)

    @Query("UPDATE company SET follow = :isFollow WHERE id = :companyId")
    abstract fun updateFollow(isFollow: Boolean, companyId: String)

}