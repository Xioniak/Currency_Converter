package com.example.currency_converter.ui.theme

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

class ThemeManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)

    fun applyTheme(isDarkMode: Boolean) {
        prefs.edit().putBoolean("dark_mode", isDarkMode).apply()
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun loadTheme() {
        val isDarkMode = prefs.getBoolean("dark_mode", false)
        applyTheme(isDarkMode)
    }

    fun isDark(): Boolean = prefs.getBoolean("dark_mode", false)
}