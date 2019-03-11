package com.spectrum.demo.specapp.company.presentation.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.spectrum.demo.specapp.common.data.model.Company
import com.spectrum.demo.specapp.common.util.DefaultSubscriber
import com.spectrum.demo.specapp.company.data.CompanyUseCase

class CompanyViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = CompanyViewModel::class.java.simpleName
    private var useCase: CompanyUseCase = CompanyUseCase()

    private var countLiveData: MutableLiveData<Int> = MutableLiveData()
    private var loadFromNetworkData: MutableLiveData<Boolean> = MutableLiveData()
    private var selectAllLiveData: MutableLiveData<List<Company>> = MutableLiveData()
    private var updateFavoriteLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var updateFollowLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getCountLiveData(): MutableLiveData<Int> {
        return countLiveData
    }

    fun getLoadFromNetworkLiveData(): MutableLiveData<Boolean> {
        return loadFromNetworkData
    }

    fun getSelectAllLiveData(): MutableLiveData<List<Company>> {
        return selectAllLiveData
    }

    fun getUpdateFavoriteLiveData(): MutableLiveData<Boolean> {
        return updateFavoriteLiveData
    }

    fun getUpdateFollowLiveData(): MutableLiveData<Boolean> {
        return updateFollowLiveData
    }

    fun getResourceCount() {
        useCase.getDataCount(object : DefaultSubscriber<Int>() {
            override fun onError(t: Throwable?) {
                Log.e(TAG, t?.message)
            }

            override fun onNext(t: Int) {
                countLiveData.postValue(t)

            }
        })
    }

    fun loadDataFromNetwork() {
        useCase.loadCompanyFromNetwork(object : DefaultSubscriber<Boolean>() {
            override fun onError(t: Throwable?) {
                Log.e(TAG, t?.message)
            }

            override fun onNext(t: Boolean) {
                loadFromNetworkData.postValue(t)

            }
        })
    }

    fun updateFavorite(isFavorite: Boolean, companyId: String) {
        useCase.updateFavorite(isFavorite, companyId, object : DefaultSubscriber<Boolean>() {
            override fun onError(t: Throwable?) {
                Log.e(TAG, t?.message)
            }

            override fun onNext(t: Boolean) {
                updateFavoriteLiveData.postValue(t)

            }
        })
    }

    fun updateFollow(isFollow: Boolean, companyId: String) {
        useCase.updateFollow(isFollow, companyId, object : DefaultSubscriber<Boolean>() {
            override fun onError(t: Throwable?) {
                Log.e(TAG, t?.message)
            }

            override fun onNext(t: Boolean) {
                updateFollowLiveData.postValue(t)

            }
        })
    }

    fun selecAllCompany() {
        useCase.getAllCompanies(CompanySubscriber())
    }


    inner class CompanySubscriber : DefaultSubscriber<List<Company>>() {

        override fun onError(t: Throwable?) {
            Log.e(TAG, "" + t?.printStackTrace())

        }

        override fun onNext(t: List<Company>) {
            selectAllLiveData.postValue(t)
        }
    }
}