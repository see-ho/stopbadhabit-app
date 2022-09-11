package com.example.stopbadhabit.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stopbadhabit.R
import com.example.stopbadhabit.data.model.Diary.Diary
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.data.model.PresentHabit.PresentHabit
import com.example.stopbadhabit.databinding.DiaryItemviewBinding
import com.example.stopbadhabit.databinding.FragmentDiaryWriteBinding
import com.example.stopbadhabit.databinding.HabitItemviewBinding

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

    override fun onBindViewHolder(holder: DiaryListAdapter.DiaryViewHolder, position: Int) {
        holder.bind(list[position])
        holder.number.text = "No. ${position+1}"
//        binding.textView19.text = String.format(binding.root.resources.getString(R.string.hd_diary_num),adapterPosition+1)

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
            //val position = (1..3).random()
            //TODO  이거 어칼거임
            when(diary.img_res){
                1-> Glide.with(binding.root).load(R.drawable.bg_item_1).into(binding.ivDiary)
                2-> Glide.with(binding.root).load(R.drawable.bg_item_2).into(binding.ivDiary)
                3-> Glide.with(binding.root).load(R.drawable.bg_item_3).into(binding.ivDiary)
                4-> Glide.with(binding.root).load(R.drawable.bg_item_4).into(binding.ivDiary)
            }
            //TODO 이것도 뭔가 문제
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
