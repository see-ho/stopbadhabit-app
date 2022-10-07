package com.seeho.stopbadhabit.ui

import android.app.Dialog
import android.content.Context
import android.os.*
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.seeho.stopbadhabit.R
import com.seeho.stopbadhabit.data.model.Diary.Diary
import com.seeho.stopbadhabit.databinding.FragmentDiaryWriteBinding
import com.seeho.stopbadhabit.ui.viewmodel.DiaryWriteViewModel
import com.seeho.stopbadhabit.ui.viewmodel.HabitDetailViewModel
import com.seeho.stopbadhabit.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate


@AndroidEntryPoint
class DiaryWriteFragment(
    val dismissListener: () -> Unit
) : BottomSheetDialogFragment() {
    private val binding by lazy { FragmentDiaryWriteBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by activityViewModels()
    private val habitDetailViewModel: HabitDetailViewModel by activityViewModels()
    private val diaryWriteViewModel: DiaryWriteViewModel by viewModels()
    private var detailId = -1

//    companion object {
//        fun newInstance() = DiaryWriteFragment()
//    }

    private lateinit var viewModel: DiaryWriteViewModel

    private var diary_date: String = ""
    private var situation: String = ""
    private var reason: String = ""
    private var emotion: String = ""
    private var promise: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel.detailHabitId.observe(viewLifecycleOwner){
            if(it != -1)
                diaryWriteViewModel.getHabitDetail(it)
            else{
                dismiss()
                Snackbar.make(requireActivity().window.decorView,String.format(requireActivity().resources.getString(R.string.habit_id_error)),
                    Snackbar.LENGTH_SHORT).show()}
        }

        diaryWriteViewModel.habit.observe(viewLifecycleOwner){
            binding.tvHwName.text = it.name
//            binding.tvHwName.text = diaryWriteViewModel.habit.value?.name
            binding.tvTodayDate.text=LocalDate.now().toString()
        }

        binding.btnDwfAdd.setOnClickListener {
            setDiary()
        }

        with(binding){
            etReason.addTextChangedListener(object : TextWatcher{
                var maxReasonText = ""
                override fun afterTextChanged(p0: Editable?) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    maxReasonText = etReason.text.toString()
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (etReason.lineCount > 2) {
                        etReason.setText(maxReasonText)
                        etReason.setSelection(etReason.length())
                    } else if (etReason.length()>30){
                        etReason.setText(maxReasonText)
                        etReason.setSelection(etReason.length())
                    }
                }
            })

            etSituation.addTextChangedListener(object : TextWatcher{
                var maxSituationText = ""
                override fun afterTextChanged(p0: Editable?) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    maxSituationText = etSituation.text.toString()
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (etSituation.lineCount > 2) {
                        etSituation.setText(maxSituationText)
                        etSituation.setSelection(etSituation.length())
                    } else if (etSituation.length()>30){
                        etSituation.setText(maxSituationText)
                        etSituation.setSelection(etSituation.length())
                    }
                }
            })

            etEmotion.addTextChangedListener(object : TextWatcher{
                var maxEmotionText = ""
                override fun afterTextChanged(p0: Editable?) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    maxEmotionText = etEmotion.text.toString()
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (etEmotion.lineCount > 5) {
                        etEmotion.setText(maxEmotionText)
                        etEmotion.setSelection(etEmotion.length())
                    } else if (etEmotion.length()>50){
                        etEmotion.setText(maxEmotionText)
                        etEmotion.setSelection(etEmotion.length())
                    }
                }
            })

            etPromise.addTextChangedListener(object : TextWatcher{
                var maxPromiseText = ""
                override fun afterTextChanged(p0: Editable?) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    maxPromiseText = etPromise.text.toString()
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (etPromise.lineCount > 5) {
                        etPromise.setText(maxPromiseText)
                        etPromise.setSelection(etPromise.length())
                    } else if (etPromise.length()>50){
                        etPromise.setText(maxPromiseText)
                        etPromise.setSelection(etPromise.length())
                    }
                }
            })
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.detailHabitId.observe(viewLifecycleOwner) {
            detailId = it
            diaryWriteViewModel.getHabitDetail(it)
        }


    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = (super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet = findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)
            }
        })as BottomSheetDialog
        //dialog.window?.setBackgroundDrawableResource(android.R.color.transparent) as BottomSheetDialog
        return dialog
    }


    private fun setDiary() {
        if (binding.etPromise.text.toString() == "") {
            Snackbar.make(
                binding.root,
                String.format(requireActivity().resources.getString(R.string.dw_setPromise)),
                Snackbar.LENGTH_SHORT
            ).show()
            return
        }
        else {
            promise = binding.etPromise.text.toString()
            situation = binding.etSituation.text.toString()
            reason = binding.etReason.text.toString()
            emotion = binding.etEmotion.text.toString()
            diaryWriteViewModel.updateLife()
            mainViewModel.insertDiary(
                Diary(
                    situation = situation,
                    reason = reason,
                    emotion = emotion,
                    promise = promise,
                    habit_id = detailId,
                    diary_date = LocalDate.now().toString(),
                    img_res = (1..4).random()
                ), detailId, diaryWriteViewModel.habit.value
            )
            val vib = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager =
                    requireActivity().getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator

            } else {
                @Suppress("DEPRECATION")
                requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }
            vib.vibrate(VibrationEffect.createOneShot(300,50))

            habitDetailViewModel.getHabitDetail(detailId)
            mainViewModel.playHeartLottie()
            mainViewModel.setDetailId(detailId)
            dismiss()
        }
    }

    override fun dismiss() {
        super.dismiss()
        dismissListener()
    }

}