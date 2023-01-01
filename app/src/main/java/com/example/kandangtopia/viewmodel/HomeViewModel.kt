package com.example.kandangtopia.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kandangtopia.model.Kandang
import com.example.kandangtopia.usecase.GetKandangUseCase
import com.example.kandangtopia.usecase.UseCaseResponse
import com.example.kandangtopia.webservices.ApiError
import kotlinx.coroutines.cancel

class HomeViewModel constructor(private val getKandangUseCase: GetKandangUseCase): ViewModel(){
    private val TAG = HomeViewModel::class.java.simpleName

    private var _listKandangAktif = MutableLiveData<List<Kandang>>()
    val listKandangAktif : LiveData<List<Kandang>> = _listKandangAktif

    private var _listKandangRehat = MutableLiveData<List<Kandang>>()
    val listKandangRehat: LiveData<List<Kandang>> = _listKandangRehat

    private var _messageData = MutableLiveData<String>()
    val messageData : LiveData<String> = _messageData

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isFail = MutableLiveData<Boolean>()
    val isFail: LiveData<Boolean> = _isFail

    fun getListKandangAktif(){
        _isLoading.value = true
        getKandangUseCase.invokeKAktif(viewModelScope, null, object:UseCaseResponse<List<Kandang>>{
            override fun onSuccess(result: List<Kandang>) {
                _isLoading.value = false
                _isFail.value = false
                Log.d(TAG, "result: $result")
                _listKandangAktif.value = result
            }

            override fun onError(apiError: ApiError?) {
                _isLoading.value = false
                _isFail.value = true
                _messageData.value = apiError?.getErrorMessage()
            }

        })
    }

    fun getListKandangRehat(){
        _isLoading.value = true
        getKandangUseCase.invokeKRehat(viewModelScope, null, object:UseCaseResponse<List<Kandang>>{
            override fun onSuccess(result: List<Kandang>) {
                _isLoading.value = false
                _isFail.value = false
                Log.d(TAG, "result: $result")
                _listKandangRehat.value = result
            }

            override fun onError(apiError: ApiError?) {
                _isLoading.value = false
                _isFail.value = true
                _messageData.value = apiError?.getErrorMessage()
            }

        })
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}