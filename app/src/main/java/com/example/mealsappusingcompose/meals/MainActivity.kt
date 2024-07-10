package com.example.mealsappusingcompose.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealsappusingcompose.model.response.MealResponse
import com.example.mealsappusingcompose.ui.theme.MealsAppUsingComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealsAppUsingComposeTheme {
                MealsCategoriesScreen()
            }
        }
    }
}

@Composable
fun MealsCategoriesScreen() {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val rememberedMeals: MutableState<List<MealResponse>> = remember { mutableStateOf(emptyList<MealResponse>()) }
    viewModel.getMeals { response ->
        val mealsFromTheApi = response?.categories
       rememberedMeals.value = mealsFromTheApi.orEmpty()
    }
    LazyColumn {
        items(rememberedMeals.value) { meal ->
            Text(text = meal.name)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealsAppUsingComposeTheme {
        MealsCategoriesScreen()
    }
}
