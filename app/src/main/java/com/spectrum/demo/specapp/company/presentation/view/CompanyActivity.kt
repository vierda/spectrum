package com.spectrum.demo.specapp.company.presentation.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.appcompat.R.id.search_close_btn
import android.support.v7.appcompat.R.id.search_src_text
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import com.spectrum.demo.specapp.R
import com.spectrum.demo.specapp.common.data.model.Company
import com.spectrum.demo.specapp.company.presentation.view.adapter.CompanyAdapter
import com.spectrum.demo.specapp.company.presentation.viewmodel.CompanyViewModel
import com.spectrum.demo.specapp.member.presentation.view.MemberActivity
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*

class CompanyActivity : AppCompatActivity(), CompanyDataView, AdapterView.OnItemSelectedListener,
        android.support.v7.widget.SearchView.OnQueryTextListener {

    private val TAG = CompanyActivity::class.java.simpleName

    private var companies = ArrayList<Company>()
    private var selectCompanies = ArrayList<Company>()
    private lateinit var companyAdapter: CompanyAdapter

    private lateinit var mainViewModel: CompanyViewModel
    private lateinit var loadCountLiveData: LiveData<Int>
    private lateinit var loadCompaniesLiveData: LiveData<Boolean>
    private lateinit var selectAllLiveData: LiveData<List<Company>>
    private lateinit var updateFavoriteLiveData: LiveData<Boolean>
    private lateinit var updateFollowLiveData: LiveData<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        companyAdapter = CompanyAdapter(this, this)
        main_recycler_view.adapter = companyAdapter
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        main_recycler_view.addItemDecoration(DividerItemDecoration(this, 0))

        mainViewModel = ViewModelProviders.of(this).get(CompanyViewModel::class.java)
        loadCountLiveData = mainViewModel.getCountLiveData()
        loadCompaniesLiveData = mainViewModel.getLoadFromNetworkLiveData()
        selectAllLiveData = mainViewModel.getSelectAllLiveData()
        updateFavoriteLiveData = mainViewModel.getUpdateFavoriteLiveData()
        updateFollowLiveData = mainViewModel.getUpdateFollowLiveData()

        load_progress.visibility = View.VISIBLE
        mainViewModel.getResourceCount()
        observeCount()

        val sortOptions = resources.getStringArray(R.array.sort_arrays)
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sortOptions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sort_by.setAdapter(adapter);
        sort_by.setOnItemSelectedListener(this)

        search.setOnQueryTextListener(this)
        val serchEdit = search.findViewById(search_src_text) as EditText
        val clearButton = search.findViewById(search_close_btn) as ImageView
        clearButton.setOnClickListener(View.OnClickListener { _ ->
            serchEdit.text.clear()
            selectCompanies.clear()
            companyAdapter.setData(companies)
            adapter.notifyDataSetChanged()

        })

    }

    private fun observeCount() {
        loadCountLiveData.observe(this, Observer<Int> { t ->

            if (t == 0) {
                mainViewModel.loadDataFromNetwork()
                observeLoadData()
            } else {
                mainViewModel.selecAllCompany()
                observeAllCompanies()
            }

        })
    }

    private fun observeLoadData() {
        loadCompaniesLiveData.observe(this, Observer<Boolean> { _ ->
            mainViewModel.selecAllCompany()
            observeAllCompanies()
        })
    }

    private fun observeAllCompanies() {
        selectAllLiveData.observe(this, Observer<List<Company>> { t ->

            if (t!!.isNotEmpty()) {
                if (companies.isNotEmpty()) companies.clear()
                companies.addAll(t)
                companyAdapter.setData(companies)
                companyAdapter.notifyDataSetChanged()
            }

            hideProgressLoading()
        })
    }

    private fun hideProgressLoading() {
        if (load_progress.visibility == View.VISIBLE)
            load_progress.visibility = View.GONE
    }

    override fun updateFollow(isFollow: Boolean, companyId: String) {
        mainViewModel.updateFollow(isFollow, companyId)
        updateFollowLiveData.observe(this, Observer<Boolean> { t ->
            Log.d(TAG, "Followed = $t")
        })
    }

    override fun updateFavorite(isFavorite: Boolean, companyId: String) {
        mainViewModel.updateFavorite(isFavorite, companyId)
        updateFavoriteLiveData.observe(this, Observer<Boolean> { t ->
            Log.d(TAG, "Favorite = $t")
        })
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        //no-opt
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        when (position) {
            0 -> {
                //no-opt
            }

            1 -> companyAdapter.setData(companies.sortedBy { company -> company.companyName })
            2 -> companyAdapter.setData(companies.sortedByDescending { company -> company.companyName })

        }

        companyAdapter.notifyDataSetChanged()
    }

    override fun onQueryTextSubmit(query: String): Boolean {

        selectCompanies.clear()

        val charText = query.toLowerCase(Locale.getDefault())
        if (charText.isEmpty()) {
            companyAdapter.setData(companies)
        } else {
            for (company in companies) {
                if (company.companyName.toLowerCase(Locale.getDefault()).contains(charText)) {
                    selectCompanies.add(company)
                }
            }

            companyAdapter.setData(selectCompanies)
        }
        companyAdapter.notifyDataSetChanged()
        return false
    }

    override fun onQueryTextChange(text: String): Boolean {
        return false
    }


    override fun seeMembers(companyId: String) {
        val intent = Intent(this, MemberActivity::class.java)
        intent.putExtra(MemberActivity.COMPANY_ID,companyId)
        startActivity(intent)
    }
}