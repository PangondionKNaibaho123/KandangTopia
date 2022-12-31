package com.example.kandangtopia.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kandang(
    @Json(name = "nama_kandang")
    var nama_kandang: String = "",

    @Json(name = "alamat_kandang")
    var alamat_kandang: String = "",

    @Json(name = "status_aktif")
    var status_aktif: Boolean = false,

    @Json(name = "client_name")
    var client_name: String?= null,

    @Json(name = "jenis_peternak")
    var jenis_peternak: String = "",

    @Json(name = "periode")
    var periode: String = "",

    @Json(name = "umur")
    var umur: String = "",

    @Json(name = "populasi")
    var populasi: String = "",

    @Json(name = "body_weight")
    var body_weight: String = "",

    @Json(name = "jenis_kandang")
    var jenis_kandang: String = "",

    @Json(name = "tanggal_doc")
    var tanggal_doc: String = ""
): Parcelable