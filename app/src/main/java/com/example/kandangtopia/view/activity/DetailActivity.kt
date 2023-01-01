package com.example.kandangtopia.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kandangtopia.R
import com.example.kandangtopia.databinding.ActivityDetailBinding
import com.example.kandangtopia.model.Kandang
import com.example.kandangtopia.view.advanced_ui.DetailActionBar

class DetailActivity : AppCompatActivity() {
    private val TAG = DetailActivity::class.java.simpleName
    private lateinit var binding: ActivityDetailBinding
    private lateinit var deliveredKandang: Kandang

    companion object{
        private const val DELIVERED_KANDANG = "DELIVERED_KANDANG"

        fun newIntent(context: Context, deliveredKandang: Kandang): Intent = Intent(context, DetailActivity::class.java)
            .putExtra(DELIVERED_KANDANG, deliveredKandang)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        deliveredKandang = intent.getParcelableExtra(DELIVERED_KANDANG)!!
        Log.d(TAG, "deliveredKandang: $deliveredKandang")

        setUpActionBar()
    }

    private fun setUpActionBar(){
        binding.dabDetail.setDataKandang(deliveredKandang)
        binding.dabDetail.setListener(object : DetailActionBar.ActionBarListener{
            override fun onBackClick() {
                finish()
            }
        })
    }

}