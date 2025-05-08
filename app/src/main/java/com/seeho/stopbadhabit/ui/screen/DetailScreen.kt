package com.seeho.stopbadhabit.ui.screen

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.seeho.stopbadhabit.R
import com.seeho.stopbadhabit.data.model.Diary.Diary
import com.seeho.stopbadhabit.data.model.Habit.Habit
import com.seeho.stopbadhabit.data.model.PresentHabit.DayStatus
import com.seeho.stopbadhabit.data.model.PresentHabit.PresentBattle
import com.seeho.stopbadhabit.ui.viewmodel.HabitDetailViewModel
import com.seeho.stopbadhabit.ui.viewmodel.MainViewModel
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    habitDetailViewModel: HabitDetailViewModel = viewModel(),
    mainViewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity),
) {
    val habitId = mainViewModel.detailHabitId.value
    val habit by habitDetailViewModel.habitById(habitId!!).collectAsState()
    val diaryList by mainViewModel.flowDiaryList(habitId!!).collectAsState()
    val presentBattleList by habitDetailViewModel.presentBattlesFlow(habitId!!).collectAsState()


    Scaffold(
        containerColor = colorResource(R.color.new_beige_100),
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        /**Column(modifier = Modifier.padding(innerPadding)) {
            habit?.let {
                Text(
                    text = "습관 이름: ${it.name}",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(text = "시작 날짜: ${it.start_date}")
            } ?: Text("습관 정보를 불러오는 중...")
        }*/

        val habit1 = Habit(1, "예쁜 손 만들기", 30, "2025.04.03", null, 4, 5, 1, 1, null)
        val samples = List(30) { index ->
            PresentBattle(
                index,
                1,
                "2025.04.${index + 2}",
                when (index) {
                    0, 1, 2, 4, 5, 6, 7 -> DayStatus.SUCCESS
                    3 -> DayStatus.FAIL
                    else -> DayStatus.NONE
                }
            )
        }
        val diarys = List(14) { index ->
            Diary(
                index,
                1,
                "2025.04.${index + 2}",
                "",
                "",
                "",
                "",
                (index % 4 + 1)
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal =  16.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if(habit != null) {
                item { MonsterInfo(habit!!) }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item { Summary(presentBattleList, habit!!.goal_date) }
                item { Spacer(modifier = Modifier.height(12.dp)) }
                item { MyInfoAndTodayBattle() }
                item { DiaryList(diaryList) }
            } else {

            } ?: item { Text("습관 정보를 불러오는 중...") }
        }
    }
}

@Composable
fun CharacterImage(
    painterResourceId : Int
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        Image(
            painter = painterResource(painterResourceId),
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
        CharacterImage(
            when(habit.mode){
                0 -> R.drawable.bg_mob_easy
                1 -> R.drawable.bg_mob_normal
                2 -> R.drawable.bg_mob_hard
                else -> R.drawable.bg_mob_easy
            }

        )
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
fun DiaryList(diaryList: List<Diary>?) {
    Column {
        Title("반성일기")
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            modifier = Modifier.heightIn(max = 32767.dp),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(4.dp), // 열 간격 명시
            verticalArrangement = Arrangement.spacedBy(4.dp) // 행 간격도 필요시
        ) {
            if(diaryList != null) {
                items(diaryList) { diary ->
                    DiaryItem(diary)
                }
            }
        }
    }

}

@Composable
fun DiaryItem(diary: Diary) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f) // 정사각형
            .fillMaxSize(), // 셀 크기에 맞게
        verticalArrangement = Arrangement.Center, // 세로 중앙 정렬
        horizontalAlignment = Alignment.CenterHorizontally // 가로 중앙 정렬
    ) {
        Text(
            text = "No. 1",
            fontSize = 12.sp,
            textAlign = TextAlign.Start,
            fontFamily = FontFamily(Font(R.font.font)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
        Box(
            modifier = Modifier
                .width(72.dp)
                .height(72.dp)
        ) {
            Image(
                painter =
                    when (diary.img_res) {
                        1 -> painterResource(id = R.drawable.bg_item_1)
                        2 -> painterResource(id = R.drawable.bg_item_2)
                        3 -> painterResource(id = R.drawable.bg_item_3)
                        4 -> painterResource(id = R.drawable.bg_item_4)
                        else -> painterResource(id = R.drawable.bg_item_1)
                    },
                contentDescription = "Hard",
            )
        }
    }
}


@Composable
@Preview
fun MyInfoAndTodayBattle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Title("나의 상태 & 오늘 전투")
        Row(
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CharacterImage(R.drawable.bg_main_character)
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
            Spacer(modifier = Modifier.width(30.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
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
                    Spacer(modifier = Modifier.width(12.dp))
                    BattleBalloon()
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp) // 텍스트와 아이콘 간격
                ) {
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
                BattleChip("👊 8일째 전투중", borderColor = Color.Black)
                BattleChip("🔥 5일 연속 공격 성공", borderColor = Color.Red, textColor = Color.Red)
            }
        }
    }
}

@Composable
fun BattleChip(
    text: String,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Gray,
    textColor: Color = Color.Black,
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 4.dp, vertical = 3.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.font)),
            color = textColor,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun BattleBalloon() {
    val builder = rememberBalloonBuilder {
        setWidthRatio(0.75f)
        //setHeight(BalloonSizeSpec.WRAP)
        setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        setArrowOrientation(ArrowOrientation.BOTTOM)
        setArrowSize(10)
        setArrowPosition(0.5f)
        setPadding(12)
        setMargin(5)
        setCornerRadius(8f)
        setArrowColorMatchBalloon(true)
        setBackgroundColorResource(R.color.white)
        setBalloonAnimation(BalloonAnimation.ELASTIC)
    }

    Balloon(
        //modifier = Modifier.align(Alignment.Center),
        builder = builder,
        balloonContent = {
            Column {
                Text(
                    text = stringResource(id=R.string.hd_tooltip1),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.font)),
                )
                Text(
                    text = stringResource(id=R.string.hd_tooltip2),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.font)),
                )
            }

//            Text(
//                text = stringResource(id=R.string.hd_tooltip2),
//                modifier = Modifier.padding(horizontal = 8.dp),
//                fontSize = 14.sp,
//                textAlign = TextAlign.Center,
//                fontFamily = FontFamily(Font(R.font.font)),
//            )
        }
    ) { balloonWindow ->
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(colorResource(R.color.new_beige_200), shape = CircleShape)
                .clickable { balloonWindow.showAlignTop() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_tooltip),
                contentDescription = "",
                modifier = Modifier.size(16.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}


@Composable
fun Summary(battleList: List<PresentBattle>, goalDate: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Title("전투 요약")
        Spacer(modifier = Modifier.height(8.dp))
        BattleBoxes(battleList,goalDate)
    }
}

@Composable
fun BattleBoxes(
    battleList: List<PresentBattle>,
    goalDate: Int // 15, 30, 50 중 하나
) {
    val rows = when (goalDate) {
        50 -> 5
        else -> 3
    }

    val colsPerRow = (battleList.size + rows - 1) / rows

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(5.dp)
                .fillMaxHeight()
                .background(Color(0xFF87CEFA)) // 연파랑
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            for (rowIndex in 0 until rows) {
                Row {
                    for (colIndex in 0 until colsPerRow) {
                        val flatIndex = rowIndex * colsPerRow + colIndex
                        val daySummary = battleList.getOrNull(flatIndex)

                        Box(
                            modifier = Modifier
                                .size(26.dp)
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
                "2025.04.${index + 2}",
                when (index) {
                    0, 1, 2, 4, 5, 6, 7 -> DayStatus.SUCCESS
                    3 -> DayStatus.FAIL
                    else -> DayStatus.NONE
                }
            )
        }
        val diarys = List(4) { index ->
            Diary(
                index,
                1,
                "2025.04.${index + 2}",
                "",
                "",
                "",
                "",
                (index % 4 + 1)
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
                Summary(samples,15)
                Spacer(modifier = Modifier.height(12.dp))
                MyInfoAndTodayBattle()
                DiaryList(diarys)
            } ?: Text("습관 정보를 불러오는 중...")
        }
    }
}
