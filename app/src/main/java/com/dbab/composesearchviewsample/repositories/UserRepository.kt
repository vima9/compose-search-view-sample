package com.dbab.composesearchviewsample.repositories

import android.content.Context
import com.dbab.composesearchviewsample.User
import com.dbab.composesearchviewsample.extensions.getJsonDataFromAsset
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import javax.inject.Inject

class UserRepository @Inject constructor(private val context: Context) {


    fun getUsers(): ArrayList<User>? {

        val jsonFileString = context.getJsonDataFromAsset("users.json") ?: return null

        val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        val userList = Types.newParameterizedType(List::class.java, User::class.java)
        val jsonAdapter: JsonAdapter<ArrayList<User>> = moshi.adapter(userList)

        return jsonAdapter.fromJson(jsonFileString)

    }


    fun getUser(id: Long?): User? {
        val users = getUsers()
        return users?.find { x -> x.id == id }
    }

}