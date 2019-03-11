package com.spectrum.demo.specapp.company.data

import com.spectrum.demo.specapp.common.data.model.Company
import com.spectrum.demo.specapp.common.util.DefaultSubscriber
import com.spectrum.demo.specapp.common.util.UseCase

class CompanyUseCase : UseCase() {

    private var repository: CompanyRepository = CompanyRepository()

    fun getDataCount(subscriber: DefaultSubscriber<Int>) {
        execute(repository.getDataCount(), subscriber)
    }

    fun loadCompanyFromNetwork(subscriber: DefaultSubscriber<Boolean>) {
        execute(repository.loadCompanyFromNetwork(), subscriber)
    }

    fun getAllCompanies(subscriber: DefaultSubscriber<List<Company>>) {
        execute(repository.selectAllCompanyFromDb(), subscriber)
    }

    fun updateFavorite(isFavorite: Boolean, companyId: String, subscriber: DefaultSubscriber<Boolean>) {
        execute(repository.updateFavorite(isFavorite, companyId), subscriber)
    }

    fun updateFollow(isFollow: Boolean, companyId: String, subscriber: DefaultSubscriber<Boolean>) {
        execute(repository.updateFollow(isFollow, companyId), subscriber)
    }
}