package com.example.austin_androidadvance.views.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.austin_androidadvance.fragment.HomeFragment
import com.example.austin_androidadvance.fragment.PopularFragment

class SectionsEventPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = HomeFragment()
            1 -> fragment = PopularFragment()
        }

        return fragment as Fragment
    }
}