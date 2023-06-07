package com.demotestapplication.viewmodels

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demotestapplication.model.AppListResponse
import com.demotestapplication.network.ApiService
import com.demotestapplication.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApplicationViewModel : ViewModel() {

    var recyclerListLiveData: MutableLiveData<AppListResponse> = MutableLiveData()

    fun getRecyclerObserver(): MutableLiveData<AppListResponse> {
        return recyclerListLiveData
    }

    fun makeApiCall(kid_id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retroInstance =
                    RetrofitInstance.getRetroInstance().create(ApiService::class.java)
                val response = retroInstance.getApplicationData(kid_id)
                recyclerListLiveData.postValue(response)
            } catch (e: Exception) {

            }

        }
    }

}