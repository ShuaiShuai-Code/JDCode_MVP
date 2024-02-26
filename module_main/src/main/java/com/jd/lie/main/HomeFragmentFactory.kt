package com.jd.lie.main

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.jd.common.utils.RouterPathUtils
import com.jd.utils.BaseFragmentFactory

const val CONTROL_POSITION = 0
const val MINE_POSITION = 1
private const val MAX_SIZE = 2

class HomeFragmentFactory() : BaseFragmentFactory() {
  
  override fun createFragment(position: Int): Fragment {
    var fragment = fragSparseArray.get(position)
    if (fragment != null) {
      return fragment
    } else {
      when (position) {
        CONTROL_POSITION -> fragment =
            ARouter.getInstance().build(RouterPathUtils.CONTROL).navigation() as Fragment
        MINE_POSITION -> fragment =
            ARouter.getInstance().build(RouterPathUtils.MINE).navigation() as Fragment
      }
      fragSparseArray.put(position, fragment)
    }
    return fragment
  }
  
  override fun getFragmentSize(): Int = MAX_SIZE
}