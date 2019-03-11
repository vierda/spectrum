package com.spectrum.demo.specapp.member.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.spectrum.demo.specapp.R
import com.spectrum.demo.specapp.common.data.model.Member
import com.spectrum.demo.specapp.member.presentation.view.MemberDataView
import com.spectrum.demo.specapp.member.presentation.view.viewholder.MemberViewHolder

class MemberAdapter (val context: Context, val dataView: MemberDataView)
    : RecyclerView.Adapter<MemberViewHolder>()  {

    private var data = ArrayList<Member>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        return MemberViewHolder(context, LayoutInflater.from(parent.context).inflate(R.layout.item_member,
                null), dataView)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
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

    fun setData(list: List<Member>) {
        if (data.isNotEmpty()) data.clear()
        data.addAll(list)
    }
}