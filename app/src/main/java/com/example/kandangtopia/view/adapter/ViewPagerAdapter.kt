package com.example.kandangtopia.view.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kandangtopia.model.Kandang
import com.example.kandangtopia.view.fragment.KandangAktifFragment
import com.example.kandangtopia.view.fragment.KandangRehatFragment

class ViewPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){
    private val listFragments = mutableListOf<Fragment>()

    override fun getCount(): Int = listFragments.size

    override fun getItem(position: Int): Fragment = listFragments[position]

    fun addFragment(fragment: Fragment){
        listFragments.add(fragment)
    }

}