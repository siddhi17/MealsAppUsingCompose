package com.example.mealsappusingcompose.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.mealsappusingcompose.model.response.MealResponse
import java.lang.Float.min


@Composable
fun MealDetailsScreen(meal: MealResponse?) {

    val profilePictureState by remember {
        mutableStateOf(MealProfilePictureState.Normal)
    }
    val transition = updateTransition(targetState = profilePictureState, label = "")

    //Collapsing toolbar on column
/*
    val scrollState = rememberScrollState()
    val offset = min(1f, 1 - (scrollState.value / 600f))
    val size by animateDpAsState(targetValue = max(100.dp, 200.dp * offset))
*/

    // collapsing toolbar on Lazy column
    val scrollState = rememberLazyListState()
    val offset = min(1f, 1 -
            (scrollState.firstVisibleItemScrollOffset / 600f
                    + scrollState.firstVisibleItemIndex))
    val size by animateDpAsState(targetValue = max(100.dp, 140.dp * offset))

    var isExpanded by remember { mutableStateOf(false) }
/*    val imageSizeDp: Dp by animateDpAsState(
        targetValue = if (isExpanded) 200.dp else 100.dp
    )*/

    val imageSizeDp: Dp by transition.animateDp(targetValueByState = { it.size }, label = "")

    val color by transition.animateColor (targetValueByState = { it.color}, label = "")

    val widthSize: Dp by transition.animateDp(targetValueByState = { it.borderWidth }, label = "")


    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            Surface(shadowElevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = widthSize,
                            color = color
                        )
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = meal?.imageUrl).apply(block = fun ImageRequest.Builder.() {
                                        transformations(CircleCropTransformation())
                                    }).build()
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(size)
                        )
                    }
                    Text(
                        text = meal?.name ?: "default name",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }

    /*        Row()
            {
                Button(modifier = Modifier
                    .padding(16.dp),
                    onClick = {
                        profilePictureState = if(profilePictureState == MealProfilePictureState.Normal)
                            MealProfilePictureState.Expanded
                        else
                            MealProfilePictureState.Normal
                    }) {
                    Text("Change state of meal profile picture")
                }
            }*/

          /*  Column(modifier = Modifier.verticalScroll(scrollState)) {
                Text("This is a text element", modifier = Modifier.padding(32.dp))
                Text("This is a text element", modifier = Modifier.padding(32.dp))
                Text("This is a text element", modifier = Modifier.padding(32.dp))
                Text("This is a text element", modifier = Modifier.padding(32.dp))
                Text("This is a text element", modifier = Modifier.padding(32.dp))
                Text("This is a text element", modifier = Modifier.padding(32.dp))
                Text("This is a text element", modifier = Modifier.padding(32.dp))
                Text("This is a text element", modifier = Modifier.padding(32.dp))
                Text("This is a text element", modifier = Modifier.padding(32.dp))
                Text("This is a text element", modifier = Modifier.padding(32.dp))

            }*/

            val dummyContentList = (0..100).map { it.toString() }
            LazyColumn(state = scrollState,modifier = Modifier.fillMaxWidth()) {
                items(dummyContentList) { dummyItem ->
                    Text(text = dummyItem, modifier = Modifier.padding(24.dp))
                }
            }
        }
    }

}

enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 4.dp),
    Expanded(Color.Green, 200.dp, 14.dp)
}
