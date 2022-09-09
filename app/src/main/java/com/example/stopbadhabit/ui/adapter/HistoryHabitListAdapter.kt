package com.example.stopbadhabit.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stopbadhabit.R
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.data.model.HabitAndModel.HabitAndDiary
import com.example.stopbadhabit.data.model.PresentHabit.PresentHabit
import com.example.stopbadhabit.databinding.HabitHistoryItemviewBinding

class HistoryHabitListAdapter :
    RecyclerView.Adapter<HistoryHabitListAdapter.HistoryHabitViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryHabitViewHolder = HistoryHabitViewHolder(
        HabitHistoryItemviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: HistoryHabitListAdapter.HistoryHabitViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    private var list: List<HabitAndDiary> = ArrayList()

    inner class HistoryHabitViewHolder(private val binding: HabitHistoryItemviewBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(habitAndDiary: HabitAndDiary ){
                when(habitAndDiary.habit.state){
                    1->{
                        Glide.with(binding.root).load(R.drawable.ic_heart).into(binding.ivHhChar)
                    }
                    2->{
                        Glide.with(binding.root).load(R.drawable.ic_emptyheart).into(binding.ivHhChar)

                    }
                }
                binding.tvHhName.text = habitAndDiary.habit.name
                binding.tvHhDate.text = String.format(binding.root.resources.getString(R.string.hr_date),habitAndDiary.habit.start_date,habitAndDiary.habit.end_date)
            }
        }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<HabitAndDiary>) {
        this.list = newItems
        notifyDataSetChanged()
    }
}