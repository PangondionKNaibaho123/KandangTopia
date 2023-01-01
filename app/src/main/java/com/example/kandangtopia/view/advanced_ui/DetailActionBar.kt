package com.example.kandangtopia.view.advanced_ui

import android.app.ActionBar
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kandangtopia.R
import com.example.kandangtopia.databinding.DetailActionbarBinding
import com.example.kandangtopia.model.Kandang

class DetailActionBar: ConstraintLayout {
    private lateinit var mContext: Context
    private lateinit var binding: DetailActionbarBinding

    private var actionBarListener: ActionBarListener?= null

    constructor(context: Context):super(context){
        init(context, null)
    }

    constructor(context: Context, attributeSet: AttributeSet):super(context, attributeSet){
        init(context, attributeSet)
    }

    private fun init(context: Context, attributeSet: AttributeSet?){
        mContext = context

        binding = DetailActionbarBinding.bind(
            LayoutInflater.from(context)
                .inflate(R.layout.detail_actionbar, this, true)
        )

        binding.icBack.setOnClickListener {
            actionBarListener?.onBackClick()
        }
    }

    fun setListener(listener: ActionBarListener){
        actionBarListener = listener
    }

    fun setDataKandang(inputKandang: Kandang){
        binding.tvNamaKandang.text = inputKandang.nama_kandang
        binding.tvNamaKota.text = inputKandang.alamat_kandang
        binding.tvTanggaldoc.text = inputKandang.tanggal_doc

        val periodeNUmur = "${inputKandang.periode} | Umur ${inputKandang.umur}"
        binding.tvPeriodenUmur.text= periodeNUmur

        binding.tvNamaTypeKandang.text = inputKandang.jenis_kandang
    }

    interface ActionBarListener{
        fun onBackClick()
    }
}