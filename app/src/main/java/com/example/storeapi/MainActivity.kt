package com.example.storeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.storeapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        setSupportActionBar(binding.toolBar)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}