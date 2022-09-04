package com.example.stopbadhabit.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stopbadhabit.R
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.databinding.HabitItemviewBinding

class HomeHabitListAdapter(
    val onClick: (Int)->Unit,
    val onDiaryAddClick: (Int) -> Unit,
) : RecyclerView.Adapter<HomeHabitListAdapter.HomeHabitViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHabitViewHolder =
        HomeHabitViewHolder(HabitItemviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: HomeHabitViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

   private var list: List<Habit> = ArrayList()
    inner class HomeHabitViewHolder(private val binding: HabitItemviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(habit: Habit){
            binding.tvHabitName.text = habit.name
            binding.tvHabitCurrentDay.text="현재 3일째 도전중"
            //TODO 어뎁터에서 res 접근 ?? 가능하지롱 바보녀석
            binding.tvHabitDate.text=habit.start_date
            binding.tvLife.text="${habit.current_life} / ${habit.setting_life}"
            binding.root.setOnClickListener {
                habit.habit_id?.let {
                    onClick(it)
                }
            }

            binding.ivShield.setOnClickListener {
                habit.habit_id?.let{
                    onDiaryAddClick(it)
                }
            }

            //binding.tvLifeMessage.text = String.format(binding.root.resources.getString(R.string.bt_sheet_life),3)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<Habit>) {
        this.list = newItems
        notifyDataSetChanged()
    }



}