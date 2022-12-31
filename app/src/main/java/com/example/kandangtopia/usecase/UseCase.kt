package com.example.kandangtopia.usecase

import com.example.kandangtopia.webservices.traceErrorException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class UseCase<Type, in Params>() where Type: Any {
    abstract suspend fun runKAktif(params: Params?= null): Type
    abstract suspend fun runKRehat(params: Params?= null): Type

    fun invokeKAktif(scope:CoroutineScope, params: Params?, onResult:UseCaseResponse<Type>?){
        scope.launch {
            try {
                val result = runKAktif(params)
                onResult?.onSuccess(result)
            }catch (e: CancellationException){
                e.printStackTrace()
                onResult?.onError(traceErrorException(e))
            }catch (e: Exception){
                e.printStackTrace()
                onResult?.onError(traceErrorException(e))
            }
        }
    }
    fun invokeKRehat(scope:CoroutineScope, params: Params?, onResult:UseCaseResponse<Type>?){
        scope.launch {
            try {
                val result = runKRehat(params)
                onResult?.onSuccess(result)
            }catch (e: CancellationException){
                e.printStackTrace()
                onResult?.onError(traceErrorException(e))
            }catch (e: Exception){
                e.printStackTrace()
                onResult?.onError(traceErrorException(e))
            }
        }
    }
}