package com.example.mealsappusingcompose.model

import com.example.mealsappusingcompose.model.api.MealsWebService
import com.example.mealsappusingcompose.model.response.MealResponse
import com.example.mealsappusingcompose.model.response.MealsCategoriesResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    private var cachedMeals = listOf<MealResponse>()

    suspend fun getMeals() : MealsCategoriesResponse {
        val response = webService.getMeals()
        cachedMeals = response.categories
        return webService.getMeals()
    }

    fun getMeal(id: String): MealResponse? {
        return cachedMeals.firstOrNull {
            it.id == id
        }
    }
    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() = instance?: synchronized(this) {
            instance ?: MealsRepository().also { instance = it }
        }
    }
}