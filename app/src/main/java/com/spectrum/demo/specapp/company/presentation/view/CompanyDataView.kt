package com.spectrum.demo.specapp.company.presentation.view

interface CompanyDataView {

    fun updateFollow (isFollow:Boolean,companyId:String)
    fun updateFavorite (isFavorite:Boolean, companyId: String)
    fun seeMembers(companyId:String)
}