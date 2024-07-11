package com.example.mealsappusingcompose.model

import com.example.mealsappusingcompose.model.api.MealsWebService
import com.example.mealsappusingcompose.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    suspend fun getMeals() : MealsCategoriesResponse {
        return webService.getMeals()
    }
}