package com.example.cloudbreastcancer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.material.tabs.TabLayout

import androidx.viewpager.widget.ViewPager
import com.example.breastcancerlocal.model.HeartDiseaseVO
import com.example.cloudbreastcancer.adapter.SectionsPagerAdapter
import com.example.cloudbreastcancer.fragments.ListFragment
import com.example.cloudbreastcancer.viewModel.HeartDiseaseCRUDViewModel


class MainActivity : AppCompatActivity(), ListFragment.OnListFragmentInteractionListener {

    private lateinit var model: HeartDiseaseCRUDViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewpager: ViewPager = findViewById(R.id.view_pager)
        viewpager.adapter = myPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewpager)
        model = HeartDiseaseCRUDViewModel.getInstance(this)
    }

    override fun onListFragmentInteraction(item: HeartDiseaseVO) {
        model.setSelectedHeartDisease(item)
    }
}