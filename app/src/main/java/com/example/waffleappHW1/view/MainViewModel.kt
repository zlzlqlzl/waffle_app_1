package com.example.waffleappHW1.view

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import com.example.waffleappHW1.data.User
import com.example.waffleappHW1.data.service.UserService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userService: UserService) : BaseViewModel() {
    private val compositeDisposable = CompositeDisposable()
    lateinit var userlist: List<User>
    fun getUsersInfo() = userService.getUsers()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            userlist = it
        }, {})
        .also { compositeDisposable.add(it) }

    fun checkUserInfo(username: String, email: String): Boolean {
        userlist.forEach {
            if (it.username == username && it.email == email) {
                return true
            }
        }
        return false
    }
}


