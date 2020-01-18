package com.example.waffleappHW1.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.waffleappHW1.*
import com.example.waffleappHW1.data.Photo
import com.example.waffleappHW1.data.service.PhotoService
import dagger.android.DaggerActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.photo_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ListActivity : DaggerActivity() {

    @Inject
    lateinit var photoService: PhotoService
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_list)
        recycler_view.adapter = PhotoAdapter(listOf()) {
            val intent = Intent(
                this,
                PhotoDetailActivity::class.java
            )
            intent.putExtra("throw", it)
            startActivity(intent)
        }
        recycler_view.layoutManager = GridLayoutManager(this, 2)
        recycler_view.setHasFixedSize(false)

        recycler_view.layoutManager = GridLayoutManager(this, 2)

//        photoService.getPhoto().enqueue(object : Callback<List<Photo>> {
//            override fun onFailure(call: Call<List<Photo>>?, t: Throwable) {
//                Log.d("error", t.message)
//            }
//
//            override fun onResponse(call: Call<List<Photo>>?, response: Response<List<Photo>>) {
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        recycler_view.adapter =
//                            PhotoAdapter(it) { final_photo ->
//                                val intent = Intent(
//                                    this@ListActivity,
//                                    PhotoDetailActivity::class.java
//                                )
//                                intent.putExtra("throw", final_photo)
//                                startActivity(intent)
//                            }
//                    }
//                }
//            }
//        })
        photoService.getPhoto()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                recycler_view.adapter = PhotoAdapter(it) {
                    val intent = Intent(this, PhotoDetailActivity::class.java)
                    intent.putExtra("throw", it)
                    startActivity(intent)
                }
            }, {})
            .also { compositeDisposable.add(it) }
    }
}