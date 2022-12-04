package com.example.foodnavigator

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList

class DatabaseHelper(context: Context) {
    private val recipesDatabase: SharedPreferences
    private val gson: Gson

    init {
        recipesDatabase = context!!.getSharedPreferences("recipesDatabase", Context.MODE_PRIVATE)
        gson = Gson()
    }

    fun saveRecipes(recipes: ArrayList<Versions>) {
        val editor = recipesDatabase.edit()
        editor?.putString("recipes", gson?.toJson(recipes))
        editor?.apply()
    }

    val recipes : ArrayList<Versions>
        get() {
            val recipesString = recipesDatabase?.getString("recipes", null)
            val recipesListType = object : TypeToken<ArrayList<Versions?>?>() {}?.type
            val recipes = gson?.fromJson<ArrayList<Versions>>(recipesString, recipesListType)
            return recipes ?: ArrayList()
        }
}