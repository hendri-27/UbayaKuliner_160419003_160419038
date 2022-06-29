package com.ubaya.ubayakuliner_160419003_160419038.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.ubaya.ubayakuliner_160419003_160419038.R
import com.ubaya.ubayakuliner_160419003_160419038.util.DatabaseInit
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sharedName = packageName
        var shared = getSharedPreferences(sharedName, Context.MODE_PRIVATE)
        var initDatabase = shared.getString("INIT_DATABASE",null)

        if (initDatabase == null){
            var viewModel = ViewModelProvider(this).get(DatabaseInit::class.java)
            viewModel.fetch()
            var editor = shared.edit()
            editor.putString("INIT_DATABASE", "DONE")
            editor.apply()
        }

        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)
        bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isMainPage = bottomNav.selectedItemId == destination.id
            bottomNav.isVisible = isMainPage

            if (isMainPage) hostFragment.setPadding(0,0,0, if (bottomNav.height == 0) 147 else bottomNav.height) else hostFragment.setPadding(0,0,0,0)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout) || super.onSupportNavigateUp()
    }
}