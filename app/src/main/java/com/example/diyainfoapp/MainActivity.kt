package com.example.diyainfoapp

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.diyainfoapp.databinding.ActivityMainBinding
import com.example.diyainfoapp.extension.setupWithNavController
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var currentNavController: LiveData<NavController>? = null

    override fun getLayoutId() = R.layout.activity_main

    override fun bindData(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
        } ?: run { setupBottomNavigation() }
        setSupportActionBar(activityViewBinder.toolBarLayout.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun setupBottomNavigation() {
        lifecycleScope.launch {
            val navGraphIds = listOf(
                R.navigation.nav_home,
                R.navigation.nav_ticket,
                R.navigation.nav_search
            )

            val controller = activityViewBinder.bottomNavigation.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = supportFragmentManager,
                containerId = R.id.mainNavigationFragment,
                intent = intent
            )
            controller.observe(this@MainActivity) {
                setupActionBarWithNavController(it)
            }
            currentNavController = controller
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigation()
    }

    fun showHideBottomNavigation(visibility: Int) {
        activityViewBinder.bottomNavigation.visibility = visibility
    }

    fun setupToolbar(pair: Pair<Boolean, String?>) {
        if (pair.first) {
            activityViewBinder.toolBarLayout.root.visibility = View.VISIBLE
        } else {
            activityViewBinder.toolBarLayout.root.visibility = View.GONE
        }
        activityViewBinder.toolBarLayout.tvTitle.text = pair.second
    }


    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}
