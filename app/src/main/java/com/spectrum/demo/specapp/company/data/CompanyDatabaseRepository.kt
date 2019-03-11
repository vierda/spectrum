package com.spectrum.demo.specapp.company.data

import com.spectrum.demo.specapp.common.data.database.AppDatabase
import com.spectrum.demo.specapp.common.data.model.Company
import com.spectrum.demo.specapp.common.data.model.Member
import io.reactivex.Observable
import java.util.concurrent.Executors

class CompanyDatabaseRepository(val database: AppDatabase) {

    fun getDataCount(): Observable<Int> {
        return Observable.create {
            Executors.newSingleThreadExecutor().execute(Runnable {
                val count = database.CompanyDao().getDataCount()
                it.onNext(count)
            })
        }
    }


    fun saveAllCompanies(companies: List<Company>): Observable<Boolean> {
        return Observable.create<Boolean> {
            Executors.newSingleThreadExecutor().execute(Runnable {

                val editMembers:ArrayList<Member> = ArrayList<Member>()

                companies.forEach { company: Company ->
                    company.members.forEach {

                        it.companyId = company.id
                        editMembers.add(it)
                    }

                   company.members = editMembers
                   database.MemberDao().insertAll(editMembers)

                }

                database.CompanyDao().insertAll(companies)
                it.onNext(true)
            })
        }
    }

    fun selectAllCompanies(): Observable<List<Company>> {
        return Observable.create<List<Company>> {
            Executors.newSingleThreadExecutor().execute(Runnable {
                val companies = database.CompanyDao().selectAllCompanies()
                it.onNext(companies)
            })
        }
    }


    fun updateFavorite(isFavorite: Boolean, companyId: String): Observable<Boolean> {
        return Observable.create<Boolean> {
            Executors.newSingleThreadExecutor().execute(Runnable {
                database.CompanyDao().updateFavorite(isFavorite, companyId)
                it.onNext(isFavorite)
            })
        }
    }

    fun updateFollow(isFollow: Boolean, companyId: String): Observable<Boolean> {
        return Observable.create<Boolean> {
            Executors.newSingleThreadExecutor().execute(Runnable {
                database.CompanyDao().updateFollow(isFollow, companyId)
                it.onNext(isFollow)
            })
        }
    }
}