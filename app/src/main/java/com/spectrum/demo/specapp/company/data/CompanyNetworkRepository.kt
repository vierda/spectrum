package com.spectrum.demo.specapp.company.data

import com.spectrum.demo.specapp.common.data.model.Company
import com.spectrum.demo.specapp.common.network.WebApiService
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback

class CompanyNetworkRepository(val webApiService: WebApiService) {

    fun loadAllCompanies(): Observable<List<Company>> {
        return Observable.create {
            webApiService.loadAllReport().enqueue(object : Callback<List<Company>> {
                override fun onFailure(call: Call<List<Company>>?, t: Throwable?) {
                    it.onError(Throwable(t?.message))
                    it.onComplete()

                }

                override fun onResponse(call: Call<List<Company>>, response: retrofit2.Response<List<Company>>) {
                    it.onNext(response.body()!!)
                    it.onComplete()
                }
            })
        }
    }
}