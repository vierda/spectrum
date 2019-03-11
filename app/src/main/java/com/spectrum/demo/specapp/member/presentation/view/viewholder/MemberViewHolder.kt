package com.spectrum.demo.specapp.member.presentation.view.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.spectrum.demo.specapp.R
import com.spectrum.demo.specapp.common.data.model.Member
import com.spectrum.demo.specapp.common.util.Util
import com.spectrum.demo.specapp.member.presentation.view.MemberDataView

import kotlinx.android.synthetic.main.item_member.view.*

class MemberViewHolder(val context: Context, val view: View, val dataView: MemberDataView)
    : RecyclerView.ViewHolder(view) {

    private var isFavorite = false

    fun bindData(member: Member) {

        view.name.text = "${member.name?.first} ${member.name?.last}"
        view.age.text = "${member.age} ${context.getString(R.string.years)}"
        view.email.text = member.email
        view.phone.text = member.phone

        Util.changeColor(member.favorite, view.favorite, R.color.red, context)

        view.favorite.setOnClickListener(View.OnClickListener {
            isFavorite = !isFavorite
            Util.changeColor(isFavorite, view.favorite, R.color.red, context)
            dataView.updateFavorite(isFavorite, member.id)
        })
    }
}