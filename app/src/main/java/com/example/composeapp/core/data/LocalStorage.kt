package com.example.composeapp.core.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


class LocalStorage(private val context: Context)  {
    private val gson = Gson()
    private val pref: SharedPreferences =
        context.getSharedPreferences("LocaleStorage", Context.MODE_PRIVATE)

    fun clearUserData() {
        pref.edit().clear().apply()
    }

//    var loginResponse: LoginResponse?
//        get() {
//            val json = pref.getString(AppKeys.LOGIN_RESPONSE, null)
//            return gson.fromJson(json, LoginResponse::class.java)
//        }
//        set(value) {
//            val json = gson.toJson(value)
//            pref.edit().putString(AppKeys.LOGIN_RESPONSE, json).apply()
//        }
//
//    var currentProfile: Profile?
//        get() {
//            val json = pref.getString(AppKeys.CURRENT_PROFILE, null)
//            return gson.fromJson(json, Profile::class.java)
//        }
//        set(value) {
//            val json = gson.toJson(value)
//            pref.edit().putString(AppKeys.CURRENT_PROFILE, json).apply()
//        }
//
//    var dontAskAgainChooseProfile: Boolean
//        get() {
//            return pref.getBoolean(AppKeys.DO_NOT_ASK_AGAIN, false)
//        }
//        set(value) {
//            pref.edit().putBoolean(AppKeys.DO_NOT_ASK_AGAIN, value).apply()
//        }
//
//    var megogoAccessToken: String
//        get() {
//            return pref.getString(AppKeys.MEGOGO_ACCESS_TOKEN, "") ?: ""
//        }
//        set(value) {
//            pref.edit().putString(AppKeys.MEGOGO_ACCESS_TOKEN, value).apply()
//        }
//    var favouritesGenres: FavouritedGenres?
//        get() {
//            val json = pref.getString(AppKeys.FAVOURITE_GENRES, null)
//            return gson.fromJson(json, FavouritedGenres::class.java)
//        }
//        set(value) {
//            val json = gson.toJson(value)
//            pref.edit().putString(AppKeys.FAVOURITE_GENRES, json).apply()
//        }
//
//    var lastSearchedMovies: LastSearchedMovies?
//        get() {
//            val json = pref.getString(AppKeys.LAST_SEARCHED_MOVIES, null)
//            return gson.fromJson(json, LastSearchedMovies::class.java)
//        }
//        set(value) {
//            val json = gson.toJson(value)
//            pref.edit().putString(AppKeys.LAST_SEARCHED_MOVIES, json).apply()
//        }
//
//    var customerProfile: CustomerProfile?
//        get() {
//            val json = pref.getString(AppKeys.CUSTOMER_PROFILE, null)
//            return gson.fromJson(json, CustomerProfile::class.java)
//        }
//        set(value) {
//            val json = gson.toJson(value)
//            pref.edit().putString(AppKeys.CUSTOMER_PROFILE, json).apply()
//        }

}


object AppKeys {
    const val LOGIN_RESPONSE = "login_response"
    const val CURRENT_PROFILE = "current_profile"
    const val DO_NOT_ASK_AGAIN = "do_not_ask_again_profile"
    const val FAVOURITE_GENRES = "favourite_genres"
    const val LAST_SEARCHED_MOVIES = "last_searched_movies"
    const val CUSTOMER_PROFILE = "customer_profile"
    const val MEGOGO_ACCESS_TOKEN = "megogo_access_token"
}



