package com.example.waffleappHW1.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.waffleappHW1.data.Photo
import com.example.waffleappHW1.R
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoAdapter(private val items: List<Photo>, private val onItemClick: (Photo) -> Unit) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewholder>() {
    //뷰홀더 객체 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewholder =
        PhotoViewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_photo,
                parent,
                false
            )
        ).also {
            it.adapterPosition.takeIf { it != RecyclerView.NO_POSITION }?.let { position ->
            }
        }

    //전체 데이터 개수 리턴
    override fun getItemCount(): Int = items.size

    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    override fun onBindViewHolder(holder: PhotoViewholder, position: Int) {
        holder.bind(items[position],onItemClick)

//        items[position].let { photo ->
//            holder.itemView.findViewById<TextView>(R.id.title_text)?.text = photo.title
//            Glide.with(holder.itemView.context).load(photo.thumbnailUrl).into(holder.itemView.pic)
//            holder.itemView.pic.setOnClickListener {
//                onItemClick(photo)
//            }
//        }
    }

    class PhotoViewholder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(photo: Photo, onItemClick: (Photo) -> Unit) {
            photo.let{
                this.itemView.findViewById<TextView>(R.id.title_text)?.text = photo.title
                Glide.with(this.itemView.context).load(photo.thumbnailUrl).into(this.itemView.pic)
                this.itemView.pic.setOnClickListener {
                    onItemClick(photo)
                }
            }
        }
    }


}