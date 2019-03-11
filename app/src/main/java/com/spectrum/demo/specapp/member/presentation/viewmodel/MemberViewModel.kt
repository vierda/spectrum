package com.spectrum.demo.specapp.member.presentation.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.spectrum.demo.specapp.common.data.model.Member
import com.spectrum.demo.specapp.common.util.DefaultSubscriber
import com.spectrum.demo.specapp.member.data.MemberUseCase

class MemberViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = MemberViewModel::class.java.simpleName
    private var useCase: MemberUseCase = MemberUseCase()

    private var selectAllLiveData: MutableLiveData<List<Member>> = MutableLiveData()
    private var updateFavoriteLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getSelectAllLiveData(): MutableLiveData<List<Member>> {
        return selectAllLiveData
    }

    fun getUpdateFavoriteLiveData(): MutableLiveData<Boolean> {
        return updateFavoriteLiveData
    }

    fun updateMemberFavorite(isFavorite: Boolean, companyId: String) {
        useCase.updateFavorite(isFavorite, companyId, object : DefaultSubscriber<Boolean>() {
            override fun onError(t: Throwable?) {
                Log.e(TAG, t?.message)
            }

            override fun onNext(t: Boolean) {
                updateFavoriteLiveData.postValue(t)

            }
        })
    }

    fun getAllMembersByCompanyId(companyId: String) {
        useCase.getAllMembers(companyId,object: DefaultSubscriber<List<Member>>(){
            override fun onError(t: Throwable?) {
                Log.e(TAG, "" + t?.printStackTrace())

            }

            override fun onNext(t: List<Member>) {
                selectAllLiveData.postValue(t)
            }
        })
    }

}