package com.spectrum.demo.specapp.company.presentation.view.viewholder

import android.content.Context
import android.graphics.PorterDuff
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.spectrum.demo.specapp.R
import com.spectrum.demo.specapp.common.data.model.Company
import com.spectrum.demo.specapp.common.util.Util
import com.spectrum.demo.specapp.company.presentation.view.CompanyDataView
import kotlinx.android.synthetic.main.item_company.view.*

class CompanyViewHolder(val context: Context, val view: View, val dataView: CompanyDataView)
    : RecyclerView.ViewHolder(view) {

    private var isFollow = false
    private var isFavorite = false

    fun bindData(company: Company) {

        view.name.text = company.companyName
        view.website.text = company.website
        view.description.text = company.about

        val logo = view.company_logo
        Glide.with(context)
                .load(company.logo)
                .placeholder(R.drawable.empty)
                .fitCenter()
                .into(logo)

        Util.changeColor(company.follow,view.follow,R.color.yellow,context)
        Util.changeColor(company.favorite,view.favorite,R.color.red,context)

        isFollow = company.follow
        isFavorite = company.favorite

        view.follow.setOnClickListener(View.OnClickListener { _ ->
            isFollow = !isFollow
            Util.changeColor(isFollow,view.follow,R.color.yellow,context)
            dataView.updateFollow(isFollow, company.id)

        })

        view.favorite.setOnClickListener(View.OnClickListener { _ ->
            isFavorite = !isFavorite
            Util.changeColor(isFavorite,view.favorite,R.color.red,context)
            dataView.updateFavorite(isFavorite,company.id)
        })

        view.see_members.setOnClickListener(View.OnClickListener { _ ->
            dataView.seeMembers(company.id)
        })
    }


}