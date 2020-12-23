package com.example.diyainfoapp

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var activityViewBinder: VB

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewBinder = DataBindingUtil.setContentView(
            this,
            getLayoutId()
        )
        bindData(savedInstanceState)
        supportActionBar?.hide()
    }

    open fun bindData(savedInstanceState: Bundle?) {
        // default does nothing, left to individual activities
    }

    @LayoutRes
    abstract fun getLayoutId(): Int
}