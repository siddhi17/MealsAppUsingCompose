package com.example.mealsappusingcompose.meals

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsappusingcompose.model.MealsRepository
import com.example.mealsappusingcompose.model.response.MealResponse
import kotlinx.coroutines.launch

class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository.getInstance()): ViewModel() {

    // Own scope declared

/*    private val mealsJob = Job()

    init {

        val scope = CoroutineScope(mealsJob + Dispatchers.IO)
        scope.launch()
        {
            val meals = getMeals()
            mealsState.value = meals
        }
    }*/


    init {

        //  val scope = CoroutineScope(mealsJob + Dispatchers.IO)

        // no need to clear the job by using viewmodelscope
        Log.d("TAG", "We are about to launch a coroutine") // 1

        viewModelScope.launch()
        {
            Log.d("TAG", "We have launched a coroutine") // 2
            val meals = getMeals()
            Log.d("TAG", "We have received the asynchronous data") // 4
            mealsState.value = meals
        }
        Log.d("TAG", "Other work") // 3
    }

    val mealsState: MutableState<List<MealResponse>> = mutableStateOf(emptyList<MealResponse>())


   /* override fun onCleared() {
        super.onCleared()
        mealsJob.cancel()
    }*/

    private suspend fun getMeals() : List<MealResponse> {
       return repository.getMeals().categories
   }
}