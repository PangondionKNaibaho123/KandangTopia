package com.example.kandangtopia.repository

import com.example.kandangtopia.model.Kandang

interface KandangRepository {
    suspend fun getKandangAktif(): List<Kandang>
    suspend fun getKandangRehat(): List<Kandang>
}