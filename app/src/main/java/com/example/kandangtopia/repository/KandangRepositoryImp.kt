package com.example.kandangtopia.repository

import com.example.kandangtopia.model.Kandang
import com.example.kandangtopia.webservices.ApiServices

class KandangRepositoryImp(private val apiServices: ApiServices): KandangRepository {
    override suspend fun getKandangAktif(): List<Kandang> {
        return apiServices.getlistkandangaktif()
    }

    override suspend fun getKandangRehat(): List<Kandang> {
        return apiServices.getlistkandangrehat()
    }
}