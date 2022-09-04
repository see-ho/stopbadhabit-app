package com.example.stopbadhabit.ui


import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.stopbadhabit.R
import com.example.stopbadhabit.data.model.Diary.Diary
import com.example.stopbadhabit.data.model.Habit.Habit
import com.example.stopbadhabit.databinding.FragmentBottomSheetBinding
import com.example.stopbadhabit.ui.viewmodel.BottomSheetViewModel
import com.example.stopbadhabit.ui.viewmodel.MainViewModel
import com.example.stopbadhabit.util.fragment.LifeType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@AndroidEntryPoint
class BottomSheetFragment : BottomSheetDialogFragment() {

    private val binding by lazy { FragmentBottomSheetBinding.inflate(layoutInflater) }
    private val bottomSheetViewModel : BottomSheetViewModel by viewModels()
    private val mainViewModel : MainViewModel by activityViewModels()
    private var settingLife : LifeType = LifeType.Error
    private var mMode : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val items = requireActivity().resources.getStringArray(R.array.goaldate_string_array)

        val mAdapter = ArrayAdapter(binding.root.context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items)

        binding.spinner.adapter = mAdapter

        var mGoalDate = 15

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        mGoalDate=15
                        if(mMode != -1) setLife(mMode,mGoalDate)
                    }
                    1 -> {
                        mGoalDate=30
                        if(mMode != -1) setLife(mMode,mGoalDate)
                    }
                    2 ->{
                        mGoalDate=50
                        if(mMode != -1) setLife(mMode,mGoalDate)
                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                mGoalDate=15
            }
        }

        binding.btnEasymode.setOnClickListener {
            mMode = 0
            setLife(mMode,mGoalDate)
            binding.btnEasymode.background = ContextCompat.getDrawable(requireContext(),R.drawable.ic_easymode_choose_2)

            binding.btnHardmode.background = ContextCompat.getDrawable(requireContext(),R.drawable.ic_hardmode)
            binding.btnNormalmode.background = ContextCompat.getDrawable(requireContext(),R.drawable.ic_normalmode)
        }

        binding.btnNormalmode.setOnClickListener {
            mMode = 1
            setLife(mMode,mGoalDate)
            binding.btnNormalmode.background = ContextCompat.getDrawable(requireContext(),R.drawable.ic_normalmode_choose)

            binding.btnEasymode.background = ContextCompat.getDrawable(requireContext(),R.drawable.ic_easymode_1)
            binding.btnHardmode.background = ContextCompat.getDrawable(requireContext(),R.drawable.ic_hardmode)
        }

        binding.btnHardmode.setOnClickListener {
            mMode = 2
            setLife(mMode,mGoalDate)
            binding.btnHardmode.background = ContextCompat.getDrawable(requireContext(),R.drawable.ic_hardmode_choose)

            binding.btnEasymode.background = ContextCompat.getDrawable(requireContext(),R.drawable.ic_easymode_1)
            binding.btnNormalmode.background = ContextCompat.getDrawable(requireContext(),R.drawable.ic_normalmode)
        }

        //TODO 에외처리에 대한 무언가.. 토스트 메세지나 확인 버튼 눌렀을 때 타입이 에러면 토스트 메세지 뜨면서 안되게

        binding.btnAdd.setOnClickListener {

            var mName = binding.etName.text.toString()
            //val mDiary = Diary(11,1,"","","","","")
            //var mList : MutableList<Diary> = mutableListOf(mDiary)
            val mDate = LocalDate.now()
            mainViewModel.insert(Habit(
                name=mName,
                goal_date = mGoalDate,
                start_date = mDate.toString(),
                setting_life = settingLife.life,
                current_life = settingLife.life,
                state = 0))
            dismiss()
            //TODO 여기 수정
        }
        return binding.root
    }

    private fun setLife(mMode:Int, mGoalDate:Int) : LifeType {
        settingLife = when {
            mMode==0 && mGoalDate==15 -> LifeType.Easy15
            mMode==0 && mGoalDate==30 -> LifeType.Easy30
            mMode==0 && mGoalDate==50 -> LifeType.Easy50
            mMode==1 && mGoalDate==15 -> LifeType.Normal15
            mMode==1 && mGoalDate==30 -> LifeType.Normal30
            mMode==1 && mGoalDate==50 -> LifeType.Normal50
            mMode==2 && mGoalDate==15 -> LifeType.Hard15
            mMode==2 && mGoalDate==30 -> LifeType.Hard30
            mMode==2 && mGoalDate==50 -> LifeType.Hard50
            else -> LifeType.Error
        }

        binding.tvLifeMessage.text = String.format(requireActivity().resources.getString(R.string.bt_sheet_life),settingLife.life)
        binding.tvLifeMessage.visibility = View.VISIBLE

        Log.e(javaClass.simpleName,"${settingLife.life}로 설정")

        return settingLife
    }

    companion object {
        const val TAG = "HabitAddBottomSheet"
    }

    fun getInstance(): BottomSheetFragment {
        return BottomSheetFragment()
    }
    //  난 잘났다. 내 그림이 최고다
}