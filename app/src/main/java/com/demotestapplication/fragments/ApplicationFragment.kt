package com.demotestapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demotestapplication.R
import com.demotestapplication.adapter.ApplicationAdapter
import com.demotestapplication.databinding.FragmentApplicationBinding
import com.demotestapplication.model.App
import com.demotestapplication.model.AppListResponse
import com.demotestapplication.utils.ProgressDialog
import com.demotestapplication.utils.hide
import com.demotestapplication.utils.hideKeyboard
import com.demotestapplication.utils.show
import com.demotestapplication.viewmodels.ApplicationViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder


class ApplicationFragment : Fragment() {
    lateinit var binding : FragmentApplicationBinding
    private lateinit var applicationViewModel: ApplicationViewModel
    private lateinit var recyclerViewAdapter: ApplicationAdapter
    lateinit var progressDialg: ProgressDialog
    lateinit var  arrayList : ArrayList<App>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_application, container, false)

        initial()
        eventListener()

        return binding.root
    }

    private fun eventListener() {
        binding.ivSearch.setOnClickListener {
            if(binding.etSearch.text.trim().toString().isNotEmpty()){
                val searchList = arrayList.filter { it.app_name.contains(binding.etSearch.text.trim().toString(),ignoreCase = true)}
                println("COUNT : "+searchList.size)
                recyclerViewAdapter.updateData(searchList as ArrayList<App>)
                hideKeyboard()
                binding.ivSearch.hide()
                binding.ivClose.show()
                if(searchList.isEmpty()){
                    Toast.makeText(requireContext(), "No application found", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "Please type application name", Toast.LENGTH_SHORT).show()
            }

        }

        binding.ivClose.setOnClickListener {
            binding.etSearch.text.clear()
            fetchServerData()
            binding.ivSearch.show()
            binding.ivClose.hide()
        }

        applicationViewModel.getRecyclerObserver().observe(viewLifecycleOwner, Observer<AppListResponse> { response ->
            progressDialg.dismissDialog()
            if(response != null){
                binding.ivSearch.show()
                binding.ivClose.hide()
                binding.etSearch.text.clear()
                arrayList = ArrayList()
                arrayList.addAll(response.data.app_list)
                recyclerViewAdapter.updateData(response.data.app_list)
            }else{
                println("RESPONSE : " + "Failed")
            }
        })

    }

    private fun fetchServerData() {
        //TODO("Not yet implemented")
        progressDialg.showDialog()
        applicationViewModel.makeApiCall("378")
    }

    private fun initial() {
        progressDialg = ProgressDialog(requireActivity(), R.layout.progress_dialog)
        applicationViewModel = ViewModelProvider(requireActivity()).get(ApplicationViewModel::class.java)
        recyclerViewAdapter = ApplicationAdapter()
        binding.rcvApp.adapter = recyclerViewAdapter
    }

    override fun onResume() {
        super.onResume()
        fetchServerData()
    }


}