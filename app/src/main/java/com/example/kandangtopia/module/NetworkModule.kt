package com.example.kandangtopia.module

import com.example.kandangtopia.model.Object.NETWORKING.Companion.BASE_URL
import com.example.kandangtopia.model.Object.NETWORKING.Companion.TIME_OUT
import com.example.kandangtopia.repository.KandangRepository
import com.example.kandangtopia.repository.KandangRepositoryImp
import com.example.kandangtopia.usecase.GetKandangUseCase
import com.example.kandangtopia.webservices.ApiServices
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val NetworkModule = module {
    single { createService(get()) }

    single { createRetrofit(get(), BASE_URL) }

    single { createOkHttpClient() }

    single { MoshiConverterFactory.create() }

    single { Moshi.Builder().build() }
}

fun createService(retrofit: Retrofit): ApiServices{
    return retrofit.create(ApiServices::class.java)
}

fun createRetrofit(okHttpClient: OkHttpClient, url:String): Retrofit{
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun createOkHttpClient(): OkHttpClient{
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createPostRepository(apiService: ApiServices): KandangRepository{
    return KandangRepositoryImp(apiService)
}

fun createGetKandangUseCase(kandangRepository: KandangRepository):GetKandangUseCase{
    return GetKandangUseCase(kandangRepository)
}