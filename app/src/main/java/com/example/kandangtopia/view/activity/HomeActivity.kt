package com.example.kandangtopia.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kandangtopia.databinding.ActivityHomeBinding
import com.example.kandangtopia.model.Kandang
import com.example.kandangtopia.model.Object.getDummyListKandangAktif
import com.example.kandangtopia.view.adapter.ItemKandangAdapter
import com.example.kandangtopia.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val TAG = HomeActivity::class.java.simpleName
    private val homeViewModel: HomeViewModel by viewModel()

    companion object{
        fun newIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callFromVM()
    }

    private fun callFromVM(){
        binding.rvKandangAktif.setHasFixedSize(true)
        binding.rvKandangAktif.layoutManager = LinearLayoutManager(this)
//        binding.rvKandangAktif.adapter = ItemKandangAdapter(getDummyListKandangAktif(), object: ItemKandangAdapter.onItemClickCallback{
//            override fun onSelectedItem(item: Kandang) {
//                Toast.makeText(this@HomeActivity, "Implemented Soon", Toast.LENGTH_SHORT).show()
//            }
//
//        })
        homeViewModel.getListKandangAktif()
        homeViewModel.listKandangAktif.observe(this, {listKandangAktif ->
            setUpRV(listKandangAktif)
        })
    }

    private fun setUpRV(listKandangAktif: List<Kandang>){
        val adapter = ItemKandangAdapter(listKandangAktif, object : ItemKandangAdapter.onItemClickCallback{
            override fun onSelectedItem(item: Kandang) {
                Log.d(TAG, "item: $item")
                Toast.makeText(this@HomeActivity, "Implemented soon", Toast.LENGTH_SHORT).show()
            }

        })

        binding.rvKandangAktif.adapter = adapter
    }

}