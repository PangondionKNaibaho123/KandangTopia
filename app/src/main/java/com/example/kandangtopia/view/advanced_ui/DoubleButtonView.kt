package com.example.kandangtopia.view.advanced_ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.kandangtopia.R
import com.example.kandangtopia.databinding.DoubleButtonLayoutBinding

class DoubleButtonView: ConstraintLayout {
    private lateinit var mContext: Context
    private lateinit var binding: DoubleButtonLayoutBinding

    private var buttonListener: ButtonListener?= null

    constructor(context: Context):super(context){
        init(context, null)
    }

    enum class TYPE {AKTIF, REHAT}

    constructor(context: Context, attrs: AttributeSet):super(context, attrs){
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?){
        mContext = context

        binding = DoubleButtonLayoutBinding.bind(
            LayoutInflater.from(context)
                .inflate(R.layout.double_button_layout, this, true)
        )

        binding.tvOptionAktif.setOnClickListener {
            buttonListener?.onAktifClicked()
        }

        binding.tvOptionRehat.setOnClickListener {
            buttonListener?.onRehatClicked()
        }
    }

    fun setListener(listener: ButtonListener){
        buttonListener = listener
    }

    fun updateType(type: TYPE){
        when(type){
            TYPE.AKTIF ->{
                binding.tvOptionAktif.setBackgroundResource(R.drawable.bg_green)
                binding.tvOptionAktif.setTextColor(ContextCompat.getColor(context, R.color.white))
                binding.tvOptionRehat.setBackgroundResource(0)
                binding.tvOptionRehat.setTextColor(ContextCompat.getColor(context, R.color.grey2))
            }
            TYPE.REHAT ->{
                binding.tvOptionAktif.setBackgroundResource(0)
                binding.tvOptionAktif.setTextColor(ContextCompat.getColor(context, R.color.grey2))
                binding.tvOptionRehat.setBackgroundResource(R.drawable.bg_green)
                binding.tvOptionRehat.setTextColor(ContextCompat.getColor(context, R.color.white))
            }
        }
    }

    interface ButtonListener{
        fun onAktifClicked()
        fun onRehatClicked()
    }
}
