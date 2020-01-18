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
import androidx.core.content.ContextCompat
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
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel.getUsersInfo()

        submit_button.setOnClickListener {
            val EmailText = findViewById<TextView>(R.id.EmailText)
            val NameText = findViewById<TextView>(R.id.NameText)
            if (viewModel.checkUserInfo(NameText.text.toString(), EmailText.text.toString())) {
                startActivity(Intent(this, ListActivity::class.java))
            }
            else {
                Toast.makeText(this, "no user data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}