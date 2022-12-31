package com.example.kandangtopia.view.advanced_ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kandangtopia.R
import com.example.kandangtopia.databinding.TypeNPeriodLayoutBinding

class TypenPeriodLayout:ConstraintLayout {

    private lateinit var mContext: Context
    private lateinit var binding: TypeNPeriodLayoutBinding

    constructor(context: Context): super(context){
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr:Int):super(context, attrs, defStyleAttr){
        init(context, attrs)
    }

    private fun init(context: Context, attributeSet: AttributeSet?){
        mContext = context
        binding = TypeNPeriodLayoutBinding.bind(
            LayoutInflater.from(mContext).inflate(R.layout.type_n_period_layout, this, true)
        )
    }

    fun setStatusView(status: Boolean, type: String, period: String){
        when(status){
            true ->{
                binding.tvType.setBackgroundResource(R.drawable.bg_type_farmer)
                binding.tvType.text = type
                binding.tvPeriod.text = period
            }
            else ->{
                binding.tvType.setBackgroundResource(R.drawable.bg_type_farmer_unactive)
                binding.tvType.text = type
                binding.tvPeriod.text = period
            }
        }
    }
}