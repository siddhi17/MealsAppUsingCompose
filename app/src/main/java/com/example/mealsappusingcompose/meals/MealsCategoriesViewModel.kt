package com.example.mealsappusingcompose.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mealsappusingcompose.model.MealsRepository
import com.example.mealsappusingcompose.model.response.MealResponse
import com.example.mealsappusingcompose.model.response.MealsCategoriesResponse

class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {

    // val mealsState: MutableState<List<MealResponse>> = mutableStateOf(emptyList<MealResponse>())

    suspend fun getMeals() : List<MealResponse> {
       return repository.getMeals().categories
   }
}