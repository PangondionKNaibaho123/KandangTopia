package com.example.kandangtopia.usecase

import com.example.kandangtopia.webservices.ApiError

interface UseCaseResponse<Type> {
    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}