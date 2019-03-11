package com.spectrum.demo.specapp.common.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.spectrum.demo.specapp.common.data.model.Company
import com.spectrum.demo.specapp.common.data.model.Member

@Dao
interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(members: List<Member>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(member: Member)

    @Query("SELECT * FROM member")
    abstract fun selectAllMembers(): List<Member>


    @Query("SELECT * FROM member WHERE companyId LIKE :companyId")
    abstract fun selectAllMembersByCompanyId(companyId: String): List<Member>

    @Query("UPDATE member SET favorite = :isFavorite WHERE id = :memberId")
    abstract fun updateFavorite(isFavorite: Boolean, memberId: String)
}