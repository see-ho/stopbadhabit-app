package com.example.stopbadhabit.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stopbadhabit.R
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.data.model.PresentHabit.PresentHabit
import com.example.stopbadhabit.databinding.HabitItemviewBinding
import com.example.stopbadhabit.databinding.HabitListItemviewBinding
import com.example.stopbadhabit.ui.viewmodel.HomeViewModel
import com.example.stopbadhabit.ui.viewmodel.MainViewModel
import com.example.stopbadhabit.util.toDate
import java.util.*
import kotlin.collections.ArrayList

class HomeHabitListAdapter(
    val onClickDone:(Int) -> Unit,
    val onClickDoing: (Int) -> Unit,
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
            HabitListItemviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HomeHabitViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    private var list: List<PresentHabit> = ArrayList()

    inner class HomeHabitViewHolder(private val binding: HabitListItemviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(presentHabit: PresentHabit) {
            when(presentHabit.status){
                1-> {
                    binding.habitDone.root.visibility = View.VISIBLE
                    binding.habitDoing.root.visibility = View.GONE
                    binding.habitDone.root.setOnClickListener {
                    presentHabit.habit.habit_id?.let(onClickDone)
                    }
                }
                0->{
                    binding.habitDone.root.visibility = View.GONE
                    binding.habitDoing.root.visibility = View.VISIBLE

                    val dateFromStart = ((today - presentHabit.habit.start_date.toDate()) / (24 * 60 * 60 * 1000) + 1).toInt()
                    when(presentHabit.habit.mode){
                        0-> Glide.with(binding.root).load(R.drawable.bg_mob_easy).into(binding.habitDoing.ivMob)
                        1-> Glide.with(binding.root).load(R.drawable.bg_mob_normal).into(binding.habitDoing.ivMob)
                        2-> Glide.with(binding.root).load(R.drawable.bg_mob_hard).into(binding.habitDoing.ivMob)
                    }
                    binding.habitDoing.tvHabitName.text = presentHabit.habit.name
                    binding.habitDoing.tvHabitCurrentDay.text = String.format(
                        binding.root.resources.getString(R.string.hd_current),
                        dateFromStart
                    )
                    binding.habitDoing.tvHabitDate.text = presentHabit.habit.start_date
                    binding.habitDoing.tvLife.text = String.format(binding.root.resources.getString(R.string.life),presentHabit.habit.current_life,presentHabit.habit.setting_life)
                    //"${presentHabit.habit.current_life} / ${presentHabit.habit.setting_life}"
                    binding.habitDoing.root.setOnClickListener {
                        presentHabit.habit.habit_id?.let {
                            onClickDoing(it)
                        }
                    }

                    binding.habitDoing.ivShield.setOnClickListener {
                        presentHabit.habit.habit_id?.let {
                            onDiaryAddClick(it)
                        }
                    }

                }
            }


            //binding.tvLifeMessage.text = String.format(binding.root.resources.getString(R.string.bt_sheet_life),3)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<PresentHabit>) {
        this.list = newItems
        notifyDataSetChanged()
    }

}