package com.example.mealsappusingcompose.model

import com.example.mealsappusingcompose.model.api.MealsWebService
import com.example.mealsappusingcompose.model.response.MealsCategoriesResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    suspend fun getMeals() : MealsCategoriesResponse {
        return webService.getMeals()
    }
}