package com.spectrum.demo.specapp.member.presentation.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import com.spectrum.demo.specapp.R
import com.spectrum.demo.specapp.common.data.model.Member
import com.spectrum.demo.specapp.member.presentation.view.adapter.MemberAdapter
import com.spectrum.demo.specapp.member.presentation.viewmodel.MemberViewModel
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*

class MemberActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener,
        android.support.v7.widget.SearchView.OnQueryTextListener, MemberDataView {

    companion object {
        const val COMPANY_ID = "companyId"
    }

    private val TAG = MemberActivity::class.java.simpleName

    private var members = ArrayList<Member>()
    private var filterMembers = ArrayList<Member>()
    private lateinit var memberAdapter: MemberAdapter

    private lateinit var viewModel: MemberViewModel
    private lateinit var selectAllLiveData: LiveData<List<Member>>
    private lateinit var updateFavoriteLiveData: LiveData<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel = ViewModelProviders.of(this).get(MemberViewModel::class.java)
        selectAllLiveData = viewModel.getSelectAllLiveData()
        updateFavoriteLiveData = viewModel.getUpdateFavoriteLiveData()

        memberAdapter = MemberAdapter(this,this)
        main_recycler_view.adapter = memberAdapter
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        main_recycler_view.addItemDecoration(DividerItemDecoration(this, 0))


        val bundle = intent.extras
        if (bundle != null) {
            val companyId = bundle.getString(COMPANY_ID)
            load_progress.visibility = View.VISIBLE
            viewModel.getAllMembersByCompanyId(companyId)
            observeAllMembers()

        }


        val sortOptions = resources.getStringArray(R.array.sort_arrays_member)
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sortOptions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sort_by.setAdapter(adapter);
        sort_by.setOnItemSelectedListener(this)

        search.setOnQueryTextListener(this)
        val serchEdit = search.findViewById(android.support.v7.appcompat.R.id.search_src_text) as EditText
        val clearButton = search.findViewById(android.support.v7.appcompat.R.id.search_close_btn) as ImageView
        clearButton.setOnClickListener(View.OnClickListener { _ ->
            serchEdit.text.clear()
            filterMembers.clear()
            memberAdapter.setData(members)
            memberAdapter.notifyDataSetChanged()

        })


    }

    private fun observeAllMembers() {
        selectAllLiveData.observe(this, android.arch.lifecycle.Observer { t ->
            if (t!!.isNotEmpty()) {
                if (members.isNotEmpty()) members.clear()

                members.addAll(t)
                memberAdapter.setData(members)
                memberAdapter.notifyDataSetChanged()
            }

            if (load_progress.visibility == View.VISIBLE)
                load_progress.visibility = View.GONE
        })
    }

    override fun updateFavorite(isFavorite: Boolean, memberId: String) {
        viewModel.updateMemberFavorite(isFavorite, memberId)
        updateFavoriteLiveData.observe(this, Observer<Boolean> { t ->
            Log.d(TAG, "Member Favorite = $t")
        })
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //no-opt
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (position) {
            0 -> {
                //no-opt
            }
            1 -> memberAdapter.setData(members.sortedBy { member -> member.age })

            2 -> memberAdapter.setData(members.sortedByDescending { member -> member.age })

            3 -> memberAdapter.setData(members.sortedBy { member -> member.name.first })

            4 -> memberAdapter.setData(members.sortedByDescending { member -> member.name.last })

        }

        memberAdapter.notifyDataSetChanged()
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        filterMembers.clear()

        val charText = query.toLowerCase(Locale.getDefault())
        if (charText.isEmpty()) {
            memberAdapter.setData(members)
        } else {
            for (member in members) {
                if (member.name.first.toLowerCase(Locale.getDefault()).contains(charText)
                || member.name.last.toLowerCase(Locale.getDefault()).contains(charText)) {
                    filterMembers.add(member)
                }
            }

            memberAdapter.setData(filterMembers)
        }
        memberAdapter.notifyDataSetChanged()
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }
}