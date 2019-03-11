package com.spectrum.demo.specapp.company.data

import com.spectrum.demo.specapp.SpecApplication
import com.spectrum.demo.specapp.common.data.model.Company
import io.reactivex.Observable


class CompanyRepository {

    private var databaseRepository = CompanyDatabaseRepository(SpecApplication.getInstance().getDatabase()!!)
    private var networkRepository = CompanyNetworkRepository(SpecApplication.getInstance().getWebService()!!)


    fun getDataCount(): Observable<Int> {
        return databaseRepository.getDataCount()
    }

    fun loadCompanyFromNetwork() : Observable<Boolean> {
        return networkRepository.loadAllCompanies().concatMap { t: List<Company> ->
            databaseRepository.saveAllCompanies(t)
        }
    }

    fun selectAllCompanyFromDb () : Observable<List<Company>> {
        return databaseRepository.selectAllCompanies()
    }


    fun updateFavorite (isFavorite:Boolean,companyId:String) : Observable<Boolean>{
        return databaseRepository.updateFavorite(isFavorite,companyId)
    }

    fun updateFollow (isFollow:Boolean,companyId:String) : Observable<Boolean>{
        return databaseRepository.updateFollow(isFollow,companyId)
    }

}