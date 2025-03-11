package com.konkuk.strhat.feature.diary

import androidx.lifecycle.ViewModel
import com.konkuk.strhat.feature.diary.state.Diary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.LocalDate
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor() : ViewModel() {
    private val _selectedDate = MutableStateFlow<LocalDate?>(null)
    val selectedDate = _selectedDate.asStateFlow()

    private val _selectedDiary = MutableStateFlow<Diary?>(null)
    val selectedDiary = _selectedDiary.asStateFlow()

    private val dummyDiary: Map<LocalDate, Diary> = mapOf(
        LocalDate(2025, 3, 1) to Diary(
            date = LocalDate(2025, 3, 1),
            content = "3월 1일에 작성된 일기 요약"
        ),
        LocalDate(2025, 2, 26) to Diary(
            date = LocalDate(2025, 2, 26),
            content = "2월 26일에 작성된 일기 요약"
        )
    )

    fun onDateSelected(date: LocalDate) {
        _selectedDate.value = date
        _selectedDiary.value = dummyDiary[date]
    }
}