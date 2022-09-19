package com.example.stopbadhabit.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stopbadhabit.R
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.data.model.HabitAndModel.HabitAndDiary
import com.example.stopbadhabit.data.model.PresentHabit.PresentHabit
import com.example.stopbadhabit.databinding.HabitHistoryItemviewBinding
import com.example.stopbadhabit.ui.HabitDetailFragmentDirections
import com.example.stopbadhabit.ui.HistoryFragmentDirections
import androidx.navigation.fragment.findNavController

class HistoryHabitListAdapter(
    val onDiaryClick:(Int) -> Unit,
) :
    RecyclerView.Adapter<HistoryHabitListAdapter.HistoryHabitViewHolder>() {

    private lateinit var diaryListAdapter: DiaryListAdapter


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

    override fun getItemViewType(position: Int): Int {
        return position
    }

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
                binding.root.setOnClickListener{
                    if(binding.layoutDiary.visibility== View.GONE){
                        binding.layoutDiary.visibility = View.VISIBLE

                    }else{
                        binding.layoutDiary.visibility = View.GONE
                    }
                }
                when(habitAndDiary.habit.state){
                    1->{
                        when(habitAndDiary.habit.mode){
                            0 -> Glide.with(binding.root).load(R.drawable.bg_easy_fail)
                                .into(binding.ivHhChar)
                            1 -> Glide.with(binding.root).load(R.drawable.bg_normal_fail)
                                .into(binding.ivHhChar)
                            2 -> Glide.with(binding.root).load(R.drawable.bg_hard_fail)
                                .into(binding.ivHhChar)
                        }
                        Glide.with(binding.root).load(R.drawable.ic_heart).into(binding.ivLifeState)
                    }
                    2->{
                        //TODO 실패 이미지 적용
                        Glide.with(binding.root).load(R.drawable.ic_close).into(binding.ivHhChar)
                        Glide.with(binding.root).load(R.drawable.ic_emptyheart).into(binding.ivLifeState)
                    }
                }
                binding.tvHhName.text = habitAndDiary.habit.name
                binding.tvHhDate.text = String.format(binding.root.resources.getString(R.string.hr_date),habitAndDiary.habit.start_date,habitAndDiary.habit.end_date)
                binding.tvHhLife.text = String.format(binding.root.resources.getString(R.string.life),habitAndDiary.habit.current_life,habitAndDiary.habit.setting_life)

                diaryListAdapter = DiaryListAdapter {
                    onDiaryClick(it)
                }.apply {
                    setHasStableIds(true)
                }
                habitAndDiary.diaries?.let { diaryListAdapter.setData(it) }

                if(habitAndDiary.diaries?.isEmpty() == true){
                    binding.tvDiaryState.visibility = View.VISIBLE
                }

                binding.rvHhDiary.adapter = diaryListAdapter
                binding.rvHhDiary.layoutManager =
                    GridLayoutManager(binding.root.context,2, LinearLayoutManager.VERTICAL, false)
            }
        }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<HabitAndDiary>) {
        this.list = newItems
        notifyDataSetChanged()
    }
}