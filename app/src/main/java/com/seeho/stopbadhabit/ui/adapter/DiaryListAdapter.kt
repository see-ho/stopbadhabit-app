package com.seeho.stopbadhabit.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seeho.stopbadhabit.R
import com.seeho.stopbadhabit.data.model.Diary.Diary
import com.seeho.stopbadhabit.databinding.DiaryItemviewBinding


class DiaryListAdapter(
    val onClick: (Int) -> Unit
) : RecyclerView.Adapter<DiaryListAdapter.DiaryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiaryListAdapter.DiaryViewHolder =
        DiaryViewHolder(
            DiaryItemviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: DiaryListAdapter.DiaryViewHolder, position: Int) {
        holder.bind(list[position])
        holder.number.text = "No. ${position+1}"

    }

    override fun getItemCount(): Int = list.size

    var list: List<Diary> = arrayListOf<Diary>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class DiaryViewHolder(private val binding: DiaryItemviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val number = binding.tvNum
        fun bind(diary: Diary) {

            when(diary.img_res){
                1-> Glide.with(binding.root).load(R.drawable.bg_item_1).into(binding.ivDiary)
                2-> Glide.with(binding.root).load(R.drawable.bg_item_2).into(binding.ivDiary)
                3-> Glide.with(binding.root).load(R.drawable.bg_item_3).into(binding.ivDiary)
                4-> Glide.with(binding.root).load(R.drawable.bg_item_4).into(binding.ivDiary)
            }
            binding.tvDate.text = diary.diary_date
            binding.root.setOnClickListener {
                diary.diary_id?.let{
                    onClick(it)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<Diary>) {

        this.list = newItems
        notifyDataSetChanged()
    }
}
