package com.github.v43d3rm4k4r.besthotels.presentation

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.github.v43d3rm4k4r.besthotels.R
import com.github.v43d3rm4k4r.besthotels.databinding.ActivityMainBinding

/**
 TODO: This application using:
- Clean Architecture + MVI
- Coroutines + Flow
- Navigation Component + SafeArgs
- Dagger
- Retrofit
- Unit & IntegrationTesting
- Network Connectivity Observing
 */

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHost
        navController = navHost.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }
}