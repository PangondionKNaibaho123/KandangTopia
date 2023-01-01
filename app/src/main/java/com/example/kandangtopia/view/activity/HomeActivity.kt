package com.example.kandangtopia.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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

    private var onFailMessage: String = ""

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
        setUpAddButton()
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

        binding.vpFragmentKandang.addOnPageChangeListener(onPageChangeListener)
    }

    private val onPageChangeListener = object: ViewPager.OnPageChangeListener{
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {}

        override fun onPageSelected(position: Int) {
            when(position){
                0 ->{
                    binding.dbvHome.updateType(DoubleButtonView.TYPE.AKTIF)
                }
                1 ->{
                    binding.dbvHome.updateType(DoubleButtonView.TYPE.REHAT)
                }
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}

    }

    private fun callFromVM(){
        homeViewModel.isLoading.observe(this, {
            showLoading(it)
        })
        homeViewModel.getListKandangAktif()
        homeViewModel.getListKandangRehat()
        homeViewModel.listKandangAktif.observe(this, {listKandangAktif ->
            homeViewModel.listKandangRehat.observe(this, {listKandangRehat ->
                setUpViewPager(listKandangAktif, listKandangRehat)
            })
        })
        homeViewModel.isFail.observe(this, {
            setUpWarning(it)
        })
        homeViewModel.messageData.observe(this, {
            setOnFailMessage(it)
        })
    }

    private fun setOnFailMessage(message: String){
        onFailMessage = message
    }

    private fun getOnFailMessage(): String{
        return onFailMessage
    }

    private fun showLoading(isLoading: Boolean){
        binding.pbHome.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    private fun setUpWarning(isFail: Boolean){
        if(isFail){
            Toast.makeText(this@HomeActivity, "${getOnFailMessage()}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpAddButton(){
        binding.ivAddItem.setOnClickListener {
            Toast.makeText(this@HomeActivity, "Add Item Implemented Soon", Toast.LENGTH_SHORT).show()
        }
    }

}