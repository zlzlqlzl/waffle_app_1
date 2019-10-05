package com.example.waffleappHW1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.photo_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity(){

    private fun setAdapter(photo_list: List<Photo>) {
        val mAdapter = PhotoAdapter({ final_photo ->
            val intent = Intent(this, PhotoDetailActivity::class.java)
            intent.putExtra("throw", final_photo)
            startActivity(intent)
        }, photo_list)
        recycler_view.adapter = mAdapter
        recycler_view.layoutManager = GridLayoutManager(this, 2)
        recycler_view.setHasFixedSize(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_list)

        recycler_view.layoutManager = GridLayoutManager(this, 2)

        WebAccess.getPhoto().enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>?, t: Throwable) {
                Log.d("error", t.message)
            }

            override fun onResponse(call: Call<List<Photo>>?, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        setAdapter(it)
                    }
                }
            }
        })
    }
}