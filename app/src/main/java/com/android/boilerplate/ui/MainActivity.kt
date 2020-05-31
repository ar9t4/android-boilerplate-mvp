package com.android.boilerplate.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.android.boilerplate.R
import com.android.boilerplate.base.BaseActivity
import com.android.boilerplate.databinding.ActivityMainBinding
import com.android.boilerplate.interfaces.OnClickListener

class MainActivity : BaseActivity(), OnClickListener<Any> {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    companion object {
        fun getStartIntent(packageContext: Context): Intent {
            return Intent(packageContext, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.listener = this
        binding.layoutNoInternet.listener = this

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navController = findNavController(R.id.nav_host_fragment_main)
        appBarConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.home_dest,
                    R.id.deep_link_dest
                ), drawerLayout
            )

        setupActionBar()
        setupSideNavigationView()
        setupBottomNavigationView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_overflow_main, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun noConnectivity() {
        hasConnectivity(false)
    }

    override fun hasConnectivity(connectivity: Boolean) {
        binding.layoutNoInternet.root.apply {
            visibility = if (connectivity) View.GONE else View.VISIBLE
        }
    }

    override fun onClicked(v: Any) {
        hasConnectivity(true)
    }

    private fun setupActionBar() {
        NavigationUI.setupActionBarWithNavController(
            this, navController, appBarConfiguration
        )
    }

    private fun setupSideNavigationView() {
        NavigationUI.setupWithNavController(
            findViewById<NavigationView>(R.id.side_nav),
            navController
        )
    }

    private fun setupBottomNavigationView() {
        NavigationUI.setupWithNavController(
            findViewById<BottomNavigationView>(R.id.bottom_nav),
            navController
        )
    }
}