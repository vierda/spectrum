package com.spectrum.demo.specapp.member.data

import com.spectrum.demo.specapp.common.data.database.AppDatabase
import com.spectrum.demo.specapp.common.data.model.Member
import io.reactivex.Observable
import java.util.concurrent.Executors

class MemberDatabaseRepository(val database: AppDatabase) {

    fun selectAllMembers(companyId:String): Observable<List<Member>> {
        return Observable.create<List<Member>> {
            Executors.newSingleThreadExecutor().execute(Runnable {
                val members = database.MemberDao().selectAllMembersByCompanyId(companyId)
                it.onNext(members)
            })
        }
    }


    fun updateFavorite(isFavorite: Boolean, companyId: String): Observable<Boolean> {
        return Observable.create<Boolean> {
            Executors.newSingleThreadExecutor().execute(Runnable {
                database.MemberDao().updateFavorite(isFavorite, companyId)
                it.onNext(isFavorite)
            })
        }
    }
}