package com.seeho.stopbadhabit.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.seeho.stopbadhabit.ui.viewmodel.HabitDetailViewModel
import com.seeho.stopbadhabit.ui.viewmodel.MainViewModel

@Composable
fun DetailScreen(
    habitDetailViewModel: HabitDetailViewModel = viewModel(),
    mainViewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    val habitId = mainViewModel.detailHabitId.value
    val habit by habitDetailViewModel.habitById(habitId!!).collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            habit?.let {
                Text(text = "습관 이름: ${it.name}", style = MaterialTheme.typography.headlineMedium)
                Text(text = "시작 날짜: ${it.start_date}")
            } ?: Text("습관 정보를 불러오는 중...")
        }
    }
}