package com.spectrum.demo.specapp.company.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.spectrum.demo.specapp.R
import com.spectrum.demo.specapp.common.data.model.Company
import com.spectrum.demo.specapp.company.presentation.view.CompanyDataView
import com.spectrum.demo.specapp.company.presentation.view.viewholder.CompanyViewHolder

class CompanyAdapter (val context: Context, val dataView: CompanyDataView)
    : RecyclerView.Adapter<CompanyViewHolder>()  {

    private var data = ArrayList<Company>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        return CompanyViewHolder(context, LayoutInflater.from(parent.context).inflate(R.layout.item_company, null), dataView)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        if (data.isNotEmpty()) {
            holder.bindData(data[position])
        }

    }

    override fun getItemCount(): Int {
        if (data.isNotEmpty())
            return data.size
        else
            return 0
    }

    fun setData(list: List<Company>) {
        if (data.isNotEmpty()) data.clear()
        data.addAll(list)
    }
}