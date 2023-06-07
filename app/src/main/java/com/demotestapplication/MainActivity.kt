package com.demotestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.demotestapplication.adapter.FragmentAdapter
import com.demotestapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private var adapter: FragmentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        initial()
    }

    private fun initial() {
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText("Application")
        )
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText("Settings")
        )

        val fragmentManager: FragmentManager = this.supportFragmentManager
        adapter = FragmentAdapter(
            fragmentManager,
            lifecycle
        )
        binding.viewPager2.adapter = adapter



        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager2.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })



        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }
}