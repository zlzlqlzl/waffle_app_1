package com.example.waffleappHW1.view


import android.content.Intent
import androidx.activity.viewModels
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.waffleappHW1.R
import com.example.waffleappHW1.data.User
import com.example.waffleappHW1.data.service.UserService
import com.example.waffleappHW1.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {
    lateinit var userlist: List<User>
    @Inject
    lateinit var userService: UserService
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val compositeDisposable = CompositeDisposable()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this

//        userService.getUsers().enqueue(
//            object : Callback<List<User>> {
//                override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//                    if (response.isSuccessful) {
//                        response.body()?.let {
//                            userlist = it
//                        }
//                    }
//                }
//            })
        userService.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userlist = it
            },{})
            .also { compositeDisposable.add(it) }

        submit_button.setOnClickListener {
            val EmailText = findViewById<TextView>(R.id.EmailText)
            val NameText = findViewById<TextView>(R.id.NameText)
            userlist.forEach {
                if (it.username == NameText.text.toString() && it.email == EmailText.text.toString()) {
                    startActivity(Intent(this, ListActivity::class.java))
                    return@setOnClickListener
                }
            }
            val toast =
                Toast.makeText(this, "no user data", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}