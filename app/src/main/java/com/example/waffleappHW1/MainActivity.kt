package com.example.waffleappHW1


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var userlist: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WebAccess.getUsers().enqueue(
            object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            userlist = it
                        }
                    }
                }
            })

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