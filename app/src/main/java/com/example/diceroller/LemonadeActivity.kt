package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme

class LemonadeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DiceRollerTheme {
        LemonadeApp()
    }
}

@Composable
fun LemonadeApp() {

    // Current step the app is displaying (remember allows the state to be retained
    // across recompositions).
    var currentStep by remember { mutableStateOf(1) }

    var tapCount by remember {
        mutableStateOf(0)
    }

    when (currentStep) {

        1 -> {
            LemonTextAndImage(
                text = stringResource(id = R.string.tap_the_lemon_tree), image = painterResource(
                    id = R.drawable.lemon_tree
                ), stringResource(id = R.string.lemon_tree)
            ) {
                currentStep = 2
            }
        }
        2 -> {
            val randomNumber = (2..4).random()
            println("Random Number = $randomNumber")
            LemonTextAndImage(
                text = stringResource(id = R.string.keep_tapping), image = painterResource(
                    id = R.drawable.lemon_squeeze
                ), stringResource(id = R.string.lemon)
            ) {
                tapCount++

                if (tapCount == randomNumber) {
                    currentStep = 3
                    tapCount = 0
                }

            }

        }
        3 -> {
            LemonTextAndImage(
                text = stringResource(id = R.string.tap_the_lemonade), image = painterResource(
                    id = R.drawable.lemon_drink
                ), stringResource(id = R.string.glass_of_lemonade)
            ) {
                currentStep = 4
            }
        }
        else -> {
            LemonTextAndImage(
                text = stringResource(id = R.string.tap_the_empty_glass), image = painterResource(
                    id = R.drawable.lemon_restart
                ), stringResource(id = R.string.empty_glass)
            ) {
                currentStep = 1
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    text: String,
    image: Painter,
    contentDescription: String,
    onImageClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = text, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(32.dp))
        /* Image(
             painter = image,
             contentDescription = contentDescription,
             modifier = Modifier
                 .wrapContentSize()
                 .clickable {
                     onImageClicked()
                 }
         )*/
        Button(
            onClick = onImageClicked,
            shape = RoundedCornerShape(40.dp),
        ) {
            Image(
                painter = image,
                contentDescription = contentDescription,
                modifier = Modifier
                    .width(dimensionResource(R.dimen.button_image_width))
                    .height(dimensionResource(R.dimen.button_image_height))
                    .padding(dimensionResource(R.dimen.button_interior_padding))
            )
        }
    }
}
