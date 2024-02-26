package com.jd.lie.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomeViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
  
  override fun getItem(position: Int): Fragment = HomeFragmentFactory().createFragment(position)
  
  override fun getCount(): Int = HomeFragmentFactory().getFragmentSize()
}
