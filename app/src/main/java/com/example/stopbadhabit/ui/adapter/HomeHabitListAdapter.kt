package com.example.stopbadhabit.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stopbadhabit.R
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.databinding.HabitItemviewBinding
import com.example.stopbadhabit.util.toDate
import java.util.*
import kotlin.collections.ArrayList

class HomeHabitListAdapter(
    val onClick: (Int) -> Unit,
    val onDiaryAddClick: (Int) -> Unit,
) : RecyclerView.Adapter<HomeHabitListAdapter.HomeHabitViewHolder>() {

    private val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHabitViewHolder =
        HomeHabitViewHolder(
            HabitItemviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HomeHabitViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    private var list: List<Habit> = ArrayList()

    inner class HomeHabitViewHolder(private val binding: HabitItemviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(habit: Habit) {
            Glide.with(binding.root).load(R.drawable.bg_mob_hard1).into(binding.ivMob)
            binding.tvHabitName.text = habit.name
            binding.tvHabitCurrentDay.text = String.format(
                binding.root.resources.getString(R.string.hd_current),
                (today - habit.start_date.toDate()) / (24 * 60 * 60 * 1000) + 1
            )
            binding.tvHabitDate.text = habit.start_date
            binding.tvLife.text = "${habit.current_life} / ${habit.setting_life}"
            binding.root.setOnClickListener {
                habit.habit_id?.let {
                    onClick(it)
                }
            }

            binding.ivShield.setOnClickListener {
                habit.habit_id?.let {
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