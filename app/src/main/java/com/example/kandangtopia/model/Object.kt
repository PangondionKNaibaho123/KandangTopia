package com.example.kandangtopia.model

object Object {
    interface NETWORKING{
        companion object{
            const val BASE_URL = "https://my-json-server.typicode.com/"
            const val TIME_OUT = 30L
        }
    }

    fun getDummyListKandangAktif(): List<Kandang> = listOf(
        Kandang(
            nama_kandang = "Kandang Bangkit",
            alamat_kandang = "Kota Jakarta Utara",
            status_aktif = true,
            client_name = null,
            jenis_peternak = "MANDIRI",
            periode = "Periode 5",
            umur = "21 Hari",
            populasi = "23.000 Ekor",
            body_weight = "840 gr",
            jenis_kandang = "Open House",
            tanggal_doc = "21 Des 2021"
        ),
        Kandang(
            nama_kandang = "Kandang Bangkit",
            alamat_kandang = "Kota Jakarta Utara",
            status_aktif = true,
            client_name = null,
            jenis_peternak = "MANDIRI",
            periode = "Periode 5",
            umur = "21 Hari",
            populasi = "23.000 Ekor",
            body_weight = "840 gr",
            jenis_kandang = "Open House",
            tanggal_doc = "21 Des 2021"
        ),
        Kandang(
            nama_kandang = "Kandang Bangkit",
            alamat_kandang = "Kota Semarang",
            status_aktif = true,
            client_name = "Agrinis",
            jenis_peternak = "PLASMA",
            periode = "Periode 5",
            umur = "21 Hari",
            populasi = "23.000 Ekor",
            body_weight = "840 gr",
            jenis_kandang = "Close House",
            tanggal_doc = "21 Des 2021"
        ),
        Kandang(
            nama_kandang = "Kandang Bangkit",
            alamat_kandang = "Kota Medan",
            status_aktif = true,
            client_name = "Maringan",
            jenis_peternak = "MANDIRI",
            periode = "Periode 5",
            umur = "21 Hari",
            populasi = "23.000 Ekor",
            body_weight = "840 gr",
            jenis_kandang = "Open House",
            tanggal_doc = "21 Des 2021"
        )
    )
}