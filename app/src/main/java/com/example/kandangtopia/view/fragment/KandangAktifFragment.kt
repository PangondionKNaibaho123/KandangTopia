package com.example.kandangtopia.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kandangtopia.R
import com.example.kandangtopia.databinding.FragmentKandangBinding
import com.example.kandangtopia.model.Kandang
import com.example.kandangtopia.view.activity.DetailActivity
import com.example.kandangtopia.view.adapter.ItemKandangAdapter

class KandangAktifFragment:Fragment() {
    private val TAG = KandangAktifFragment::class.java.simpleName

    private lateinit var binding: FragmentKandangBinding
    private lateinit var _deliveredListKandang: ArrayList<Kandang>
    private var deliveredListKandang: List<Kandang> = listOf()

    companion object{
        private const val DELIVERED_LIST_KANDANG = "DELIVERED_LIST_KANDANG"
        fun newInstance(deliveredListKandang: ArrayList<Kandang>): KandangAktifFragment{
            val fragment = KandangAktifFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(DELIVERED_LIST_KANDANG, deliveredListKandang)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKandangBinding.bind(
            inflater.inflate(
                R.layout.fragment_kandang,
                container,
                false
            )
        )

        _deliveredListKandang = arguments?.getParcelableArrayList(DELIVERED_LIST_KANDANG)!!

        Log.d(TAG, "_deliveredListKandang : $_deliveredListKandang")

        _deliveredListKandang.forEach {
            deliveredListKandang += it
        }

        Log.d(TAG, "deliveredListKandang: $deliveredListKandang")

        setUpRV(deliveredListKandang)

        return binding.root
    }

    private fun setUpRV(listKandang: List<Kandang>){
        val textAmountList = String.format(getString(R.string.amount_list_aktif), listKandang.size)
        binding.tvAmountList.text = textAmountList

        binding.rvListKandang.layoutManager = LinearLayoutManager(this@KandangAktifFragment.requireContext())
        binding.rvListKandang.setHasFixedSize(true)

        val adapter = ItemKandangAdapter(listKandang, object: ItemKandangAdapter.onItemClickCallback{
            override fun onSelectedItem(item: Kandang) {
                startActivity(
                    DetailActivity.newIntent(this@KandangAktifFragment.requireActivity(), item)
                )
            }

        })

        binding.rvListKandang.adapter = adapter
    }
}