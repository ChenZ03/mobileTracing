package com.example.contacttracingproject.repository

import androidx.annotation.WorkerThread
import com.example.contacttracingproject.dao.UserDao
import com.example.contacttracingproject.models.User

class UserRepository(private val userDao: UserDao) {

    fun getUser(icNumber : String) = userDao.getUser(icNumber)

    fun registerUser(user : User){
        return userDao.insert(user)
    }

    fun update(user: User) = userDao.update(user)

    fun delete(user: User) = userDao.delete(user)

    suspend fun loginUser(icNumber : String, password : String) : User {
        return userDao.loginUser(icNumber, password)
    }



}