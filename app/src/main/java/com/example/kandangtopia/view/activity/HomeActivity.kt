package com.example.kandangtopia.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.kandangtopia.R
import com.example.kandangtopia.databinding.ActivityHomeBinding
import com.example.kandangtopia.model.Kandang
import com.example.kandangtopia.model.Object.getDummyListKandangAktif
import com.example.kandangtopia.view.adapter.ItemKandangAdapter
import com.example.kandangtopia.view.adapter.ViewPagerAdapter
import com.example.kandangtopia.view.advanced_ui.DoubleButtonView
import com.example.kandangtopia.view.advanced_ui.SearchBarView
import com.example.kandangtopia.view.fragment.KandangAktifFragment
import com.example.kandangtopia.view.fragment.KandangRehatFragment
import com.example.kandangtopia.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val TAG = HomeActivity::class.java.simpleName
    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var fragmentKandangAktif: KandangAktifFragment
    private lateinit var fragmentKandangRehat: KandangRehatFragment

    private var _listKandangAktif: ArrayList<Kandang> = ArrayList()
    private var _listKandangRehat: ArrayList<Kandang> = ArrayList()

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    companion object{
        fun newIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callFromVM()
        setUpSearchBar()
        setUpDoubleButton()
    }

    private fun setUpSearchBar(){
        binding.sbvHome.setTextHelper(getString(R.string.searchbar_helper))
        binding.sbvHome.setListener(object : SearchBarView.InputSearchListener{
            override fun onClickSearch() {
                binding.sbvHome.hideKeyboard()
                Toast.makeText(this@HomeActivity, "Hasil Pencarian : ${binding.sbvHome.getText()}", Toast.LENGTH_SHORT).show()
            }

            override fun onClearSearch() {
                binding.sbvHome.clearText()
            }

        })
    }

    private fun setUpDoubleButton(){
        binding.dbvHome.setListener(object : DoubleButtonView.ButtonListener{
            override fun onAktifClicked() {
                binding.dbvHome.updateType(DoubleButtonView.TYPE.AKTIF)
                binding.vpFragmentKandang.currentItem = 0
            }

            override fun onRehatClicked() {
                binding.dbvHome.updateType(DoubleButtonView.TYPE.REHAT)
                binding.vpFragmentKandang.currentItem = 1
            }

        })
    }

    private fun setUpViewPager(listKandangAktif: List<Kandang>, listKandangRehat: List<Kandang>){

        listKandangAktif.forEach {
            _listKandangAktif.add(it)
        }

        listKandangRehat.forEach {
            _listKandangRehat.add(it)
        }

        fragmentKandangAktif = KandangAktifFragment.newInstance(_listKandangAktif)
        fragmentKandangRehat = KandangRehatFragment.newInstance(_listKandangRehat)

        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(fragmentKandangAktif)
        viewPagerAdapter.addFragment(fragmentKandangRehat)

        binding.vpFragmentKandang.adapter = viewPagerAdapter
    }

    private fun callFromVM(){
        homeViewModel.getListKandangAktif()
        homeViewModel.getListKandangRehat()
        homeViewModel.listKandangAktif.observe(this, {listKandangAktif ->
            homeViewModel.listKandangRehat.observe(this, {listKandangRehat ->
                setUpViewPager(listKandangAktif, listKandangRehat)
            })
        })



    }

}