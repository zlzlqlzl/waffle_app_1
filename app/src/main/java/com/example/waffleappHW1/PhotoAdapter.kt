package com.example.waffleappHW1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoAdapter(private val onItemClick: (Photo) -> Unit) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewholder>() {
    var list: List<Photo> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    //뷰홀더 객체 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewholder {
        return PhotoViewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)).also { vh ->
            vh.adapterPosition.takeIf { it != RecyclerView.NO_POSITION }?.let { position ->

            }

        }
    }

    //전체 데이터 개수 리턴
    override fun getItemCount(): Int = list.size

    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    override fun onBindViewHolder(holder: PhotoViewholder, position: Int) {
        list[position].let { photo ->
            holder.itemView.findViewById<TextView>(R.id.title_text)?.text = photo.title
            Glide.with(holder.itemView.context).load(photo.thumbnailUrl).into(holder.itemView.pic)
            holder.itemView.pic.setOnClickListener {
                onItemClick(photo)
            }
        }
    }

    class PhotoViewholder(view: View) : RecyclerView.ViewHolder(view)


}