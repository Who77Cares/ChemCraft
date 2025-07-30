package com.bignerdranch.chemcraft.data.old

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefManager(context: Context) {

    companion object {
        private const val PREF_NAME = "favorites_pref"
        private const val KEY_FAVORITES = "favorites_list"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val gson = Gson()

    // Сохраняем список
    fun saveFavorites(favorites: List<String>) {
        val jsonString = gson.toJson(favorites)
        sharedPreferences.edit()
            .putString(KEY_FAVORITES, jsonString)
            .apply()
    }

    // Получаем текущий список
    fun getFavorite(): List<String> {
        val jsonString = sharedPreferences.getString(KEY_FAVORITES, null) ?: return emptyList()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(jsonString, type)
    }

    fun addFavorite(item: String) {
        val favorites = getFavorite().toMutableList()
        if(!favorites.contains(item)) {
            favorites.add(item)
            saveFavorites(favorites)
        }
    }

    fun removeFavorite(item: String) {
        val favorites = getFavorite().toMutableList()
        if(favorites.contains(item)) {
            favorites.remove(item)
            saveFavorites(favorites)
        }
    }

    fun isFavorite(item: String): Boolean {
        return getFavorite().contains(item)
    }

}