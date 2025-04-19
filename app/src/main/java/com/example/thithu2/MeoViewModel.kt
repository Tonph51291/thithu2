package com.example.thithu2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MeoViewModel : ViewModel() {
    private val lstMeo = MutableLiveData<List<Meo>>()
    val meo : LiveData<List<Meo>> = lstMeo
    private  val selectMeo = MutableLiveData<Meo?>()
    val selectedMeo : MutableLiveData<Meo?> = selectMeo

    init {
        getMeo()
    }

    fun getMeo(){
        viewModelScope.launch {
            try {
                val respone = RetrofitService().meoService.getMeo()
                if (respone.isSuccessful){
                    lstMeo.value = respone.body()?.map { it.toMeo() }
                } else {
                    Log.e("MeoViewModel", "Lỗi khi lấy danh sách mèo: ${respone.message()}")
                }

            } catch (e: Exception) {
                Log.e("MeoViewModel", "Lỗi khi lấy danh sách mèo: ${e.message}")
            }
        }
    }
    fun getMeoById(id : String) {
        viewModelScope.launch {
            try {
                val respone = RetrofitService().meoService.getMeoById(id)
                if (respone.isSuccessful) {
                    selectMeo.value = respone.body()?.toMeo()
                 } else {
                    Log.e("MeoViewModel", "Lỗi khi lấy danh sách mèo: ${respone.message()}")
                }

            } catch (
                e : Exception
            ) {
                Log.e("MeoViewModel", "Lỗi khi lấy danh sách mèo: ${e.message}")
            }
        }
    }
}