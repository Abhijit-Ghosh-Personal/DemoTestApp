package com.demotestapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demotestapplication.fragments.ApplicationFragment
import com.demotestapplication.fragments.SettingsFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            SettingsFragment()
        } else ApplicationFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}