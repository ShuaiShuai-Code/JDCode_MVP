package com.jd.utils

import android.util.SparseArray
import androidx.fragment.app.Fragment

abstract class BaseFragmentFactory {

    abstract fun createFragment(position: Int): Fragment

    abstract fun getFragmentSize(): Int

    protected var fragSparseArray = SparseArray<Fragment>()

}