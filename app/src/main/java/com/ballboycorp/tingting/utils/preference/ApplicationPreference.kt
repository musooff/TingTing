package com.ballboycorp.tingting.utils.preference

import android.content.Context
import com.ballboycorp.tingting.profile.model.User

/**
 * Created by musooff on 07/04/2019.
 */

class ApplicationPreference(context: Context) {

    companion object {
        @Volatile
        private var INSTANCE: ApplicationPreference? = null

        fun getInstance(context: Context) =
                INSTANCE
                        ?: ApplicationPreference(context)
                                .also {
                                    INSTANCE = it
                                }

        private const val USER_NICKNAME = "nickname"
        private const val USER_GENDER = "gender"
        private const val USER_THUMB = "thumbnail"
    }

    private val sharedPreferences = context.getSharedPreferences("TinTing", 0)
    private val editor = sharedPreferences.edit()


    fun getUser(): User? {
        if (sharedPreferences.getString(USER_NICKNAME, null) == null) return null
        return User().apply {
            nickname = sharedPreferences.getString(USER_NICKNAME, null)
            gender = sharedPreferences.getInt(USER_GENDER, 0)
            thumbnail = sharedPreferences.getString(USER_THUMB, null)
        }
    }

    fun setUser(user: User?) {
        if (user == null) {
            editor
                    .remove(USER_NICKNAME)
                    .remove(USER_GENDER)
                    .remove(USER_THUMB)
                    .apply()
        } else
            editor
                    .putString(USER_NICKNAME, user.nickname)
                    .putInt(USER_GENDER, user.gender)
                    .putString(USER_THUMB, user.thumbnail)
                    .apply()
    }
}