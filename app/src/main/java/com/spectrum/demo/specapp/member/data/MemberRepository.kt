package com.spectrum.demo.specapp.member.data

import com.spectrum.demo.specapp.SpecApplication
import com.spectrum.demo.specapp.common.data.model.Member
import io.reactivex.Observable

class MemberRepository {

    private var databaseRepository = MemberDatabaseRepository(SpecApplication.getInstance().getDatabase()!!)

    fun selectAllMemberFromDb(companyId:String): Observable<List<Member>> {
        return databaseRepository.selectAllMembers(companyId)
    }


    fun updateFavorite (isFavorite:Boolean,companyId:String) : Observable<Boolean> {
        return databaseRepository.updateFavorite(isFavorite,companyId)
    }
}