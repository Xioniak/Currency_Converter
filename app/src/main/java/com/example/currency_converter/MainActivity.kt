package com.example.currency_converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.fragment.app.commit
import com.example.currency_converter.uii.Calcu
import com.example.currency_converter.uii.RatesFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout)
        val nav = findViewById<NavigationView>(R.id.navigationView)

        // Ekran startowy
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, Calcu())
        }

        nav.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_calculator -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragmentContainer, Calcu())
                    }
                }
                R.id.nav_rates -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragmentContainer, RatesFragment())
                    }
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }
    }
}
