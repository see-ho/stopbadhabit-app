package com.seeho.stopbadhabit.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.seeho.stopbadhabit.R
import com.seeho.stopbadhabit.data.model.Habit.Habit
import com.seeho.stopbadhabit.data.model.PresentHabit.DayStatus
import com.seeho.stopbadhabit.data.model.PresentHabit.PresentBattle
import com.seeho.stopbadhabit.ui.viewmodel.HabitDetailViewModel
import com.seeho.stopbadhabit.ui.viewmodel.MainViewModel

@Composable
fun DetailScreen(
    habitDetailViewModel: HabitDetailViewModel = viewModel(),
    mainViewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity),
) {
    val habitId = mainViewModel.detailHabitId.value
    val habit by habitDetailViewModel.habitById(habitId!!).collectAsState()

    Scaffold(
        containerColor = colorResource(R.color.new_beige_100),
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            habit?.let {
                Text(
                    text = "습관 이름: ${it.name}",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(text = "시작 날짜: ${it.start_date}")
            } ?: Text("습관 정보를 불러오는 중...")
        }
    }
}

@Preview
@Composable
fun CharacterImage() {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_mob_easy),
            contentDescription = "Hard",
        )
    }
}

@Composable
fun MonsterInfo(habit: Habit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        CharacterImage()
        Text(
            text = habit.name,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.font)),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${habit.start_date} ~ ",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.font)),
        )
    }
}

@Composable
@Preview
fun MyInfoAndTodayBattle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Title("나의 상태 & 오늘 전투")
        Row {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CharacterImage()
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(4.dp) 
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = "Icon",
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "4 / 5",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.font)),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column (
               horizontalAlignment =  Alignment.CenterHorizontally,
                verticalArrangement =  Arrangement.spacedBy(8.dp)
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_clock),
                        contentDescription = "Icon",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "23:00",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        color = colorResource(R.color.new_beige_200),
                        fontFamily = FontFamily(Font(R.font.font)),
                    )
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp) // 텍스트와 아이콘 간격
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_sword),
                        contentDescription = "Icon",
                        modifier = Modifier.size(32.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_shield),
                        contentDescription = "Icon",
                        modifier = Modifier.size(32.dp)
                    )
                }
                BattleChip("👊8일째 전투중", borderColor = Color.Black)
                BattleChip("🔥 5일 연속 공격 성공", borderColor = Color.Black)
            }
        }
    }
}

@Composable
fun BattleChip(
    text: String,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Gray,
    textColor: Color = Color.Black
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun Summary(battleList: List<PresentBattle>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Title("전투 요약")
        Spacer(modifier = Modifier.height(8.dp))
        BattleBoxes(battleList)
    }
}

@Composable
fun BattleBoxes(battleList: List<PresentBattle>) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(4.dp) // 텍스트와 아이콘 간격
    ) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .fillMaxHeight()
                .background(Color(0xFF87CEFA)) // 연파랑
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val rows = 3
            val colsPerRow = (battleList.size + 2) / 3 // 3줄로 분배

            for (rowIndex in 0 until rows) {
                Row {
                    for (colIndex in 0 until colsPerRow) {
                        val flatIndex = rowIndex * colsPerRow + colIndex
                        val daySummary = battleList.getOrNull(flatIndex)

                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .padding(2.dp)
                                .background(
                                    when (daySummary?.status) {
                                        DayStatus.SUCCESS -> Color(0xFFA1DF7C) // 초록
                                        DayStatus.FAIL -> Color(0xFFC03830) // 빨강
                                        DayStatus.NONE, null -> Color(0xFFD3D3D3) // 연회색
                                    },
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Title(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            color = colorResource(R.color.new_beige_200),
            thickness = 1.dp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.font)),
        )
        HorizontalDivider(
            color = colorResource(R.color.new_beige_200),
            thickness = 1.dp,
            modifier = Modifier.weight(8f)
        )
    }
}


@Composable
@Preview
fun PreviewDetailScreen() {
    Scaffold(
        containerColor = colorResource(R.color.new_beige_100),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(Alignment.Center),
    ) { innerPadding ->
        val habit = Habit(1, "예쁜 손 만들기", 30, "2025.04.03", null, 4, 5, 1, 1, null)
        val samples = List(30) { index ->
            PresentBattle(
                index,
                1,
                "2025.04.${index+2}",
                when (index) {
                    0, 1, 2, 4, 5, 6, 7 -> DayStatus.SUCCESS
                    3 -> DayStatus.FAIL
                    else -> DayStatus.NONE
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            habit?.let {
                MonsterInfo(habit)
                Spacer(modifier = Modifier.height(8.dp))
                Summary(samples)
                Spacer(modifier = Modifier.height(12.dp))
                MyInfoAndTodayBattle()
            } ?: Text("습관 정보를 불러오는 중...")
        }
    }
}
