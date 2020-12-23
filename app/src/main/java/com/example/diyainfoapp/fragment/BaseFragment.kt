package com.example.diyainfoapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.app.tersarkhang.extension.setYesButton
import com.example.diyainfoapp.BR
import com.example.diyainfoapp.MainActivity
import com.example.diyainfoapp.R

abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

    private var _viewBinder: VB? = null
    protected val viewBinder get() = _viewBinder!!
    private var _mContext: Context? = null
    protected val mContext get() = _mContext!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _mContext = context
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupMainToolbar()
        _viewBinder?.let {} ?: run {
            _viewBinder = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        }

        getViewModel()?.let {
            viewBinder.setVariable(BR.viewModel, it)
            viewBinder.lifecycleOwner = this
        }
        bindData(savedInstanceState)
        shouldShowBottomNavigation()
        showBottomNavigation()
        return viewBinder.root
    }

    private fun setupMainToolbar() {
        if (requireActivity() is MainActivity) {
            (requireActivity() as MainActivity).apply {
                setupToolbar(getMainToolbar())
            }
        }
    }

    open fun bindData(savedInstanceState: Bundle?) {
        // Default does nothing, its up to child  to override to tie ui behaviors if needed
    }

    /**
     * 1st param-> to show toolbar
     * 2nd param-> title
     * */
    open fun getMainToolbar() =
        Pair<Boolean, String?>(first = true, second = getString(R.string.app_name))

    open fun shouldShowBottomNavigation() = false
    abstract fun getViewModel(): ViewModel?

    @LayoutRes
    abstract fun getLayoutId(): Int

    private fun showBottomNavigation() {
        if (requireActivity() is MainActivity) {
            (requireActivity() as MainActivity).showHideBottomNavigation(
                if (shouldShowBottomNavigation()) View.VISIBLE else View.GONE
            )
        }
    }

    fun getDialog(@StyleRes themeResId: Int = R.style.Theme_AppCompat_Dialog): AlertDialog.Builder =
        AlertDialog.Builder(mContext, themeResId).setCancelable(false)

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        _viewBinder = null
    }

    @CallSuper
    override fun onDetach() {
        super.onDetach()
        _mContext = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let { onRestoreSavedInstance(it) }
    }

    open fun onRestoreSavedInstance(savedInstanceState: Bundle) {
        // Default does nothing, its up to child  to reload existing state
    }

    fun showNoInternetDialog() {
        val dialog = getDialog()
            .setTitle(R.string.alert)
            .setMessage(getString(R.string.no_internet_message))
            .setYesButton(R.string.ok)
            .show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isAllCaps = true
    }
}
