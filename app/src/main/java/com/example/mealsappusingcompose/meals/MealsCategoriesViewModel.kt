package com.example.mealsappusingcompose.meals

import androidx.lifecycle.ViewModel
import com.example.mealsappusingcompose.model.MealsRepository
import com.example.mealsappusingcompose.model.response.MealsCategoriesResponse

class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {
    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        repository.getMeals { response ->
            successCallback(response)
        }
    }
}