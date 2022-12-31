package com.example.kandangtopia.usecase

import com.example.kandangtopia.model.Kandang
import com.example.kandangtopia.repository.KandangRepository

class GetKandangUseCase constructor(
    private val kandangRepository: KandangRepository
): UseCase<List<Kandang>, Any?>(){
    override suspend fun runKAktif(params: Any?): List<Kandang> {
        return kandangRepository.getKandangAktif()
    }

    override suspend fun runKRehat(params: Any?): List<Kandang> {
        return kandangRepository.getKandangRehat()
    }
}