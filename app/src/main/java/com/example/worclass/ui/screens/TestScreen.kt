package com.example.worclass.ui.screens

import android.os.Bundle
import androidx.compose.foundation.layout.Row
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.worclass.R
import com.example.worclass.ui.theme.WorClassTheme

@Composable
fun TestScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxSize()
    ) {
        Text(text = "Test Screen")

                Column {
                    TextComposable(name = "xd")
                    TextComposable()
                    TextComposable()
                    TextComposable()
                }
                Row {
                    TextComposable()
                    TextComposable()
                    TextComposable()
                    TextComposable()
                }
                Column {
                    ModifierExample2()
                    ModifierExample4()
                    CustomText()
                    picture()
                }
            }
        }


@Preview(showBackground = true)
@Composable
fun TextComposable(name: String = "Empty") {
    Column {
        Text(text = "Hello world")
        Text(name)
    }
}

@Preview
@Composable
fun ModifierExample1() {
    Column(
        modifier = Modifier
            .padding(start = 24.dp, top = 10.dp, end = 18.dp, bottom = 30.dp) // Corrección en padding
    ) {
        Text(text = "Hello world")
    }
}

@Preview
@Composable
fun ModifierExample2() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .clickable(onClick = { /* Acción en onClick */ }) // Se evitó el llamado a clickAction()
    ) {
        Text(text = "Hello world")
    }
}

fun clickAction() {
    println(":) onClick")
}

@Preview
@Composable
fun ModifierExample3() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .background(Color.Blue)
            .border(width = 2.dp, color = Color.Black)
            .width(200.dp), // Se corrigió el ancho de 2.dp a 200.dp
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TextComposable(name = "A")
        TextComposable(name = "B")
        TextComposable(name = "E")
        TextComposable(name = "D")
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierExample4() {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .height(300.dp)
            .width(300.dp)
            .background(Color.Magenta)
    ) {
        Text(text = "1", Modifier.align(Alignment.TopStart))
        Text(text = "1.1", Modifier.align(Alignment.TopCenter))
        Text(text = "1.2", Modifier.align(Alignment.TopEnd))
        Text(text = "2", Modifier.align(Alignment.CenterStart))
        Text(text = "2.1", Modifier.align(Alignment.Center))
        Text(text = "2.2", Modifier.align(Alignment.CenterEnd))
        Text(text = "3", Modifier.align(Alignment.BottomStart))
        Text(text = "3.1", Modifier.align(Alignment.BottomCenter))
        Text(text = "3.2", Modifier.align(Alignment.BottomEnd))
    }
}

@Preview(showBackground = true)
@Composable
fun CustomText() {
    Column {
        Text(
            text = stringResource(R.string.app_name),
            color = colorResource(R.color.purple_500),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold
        )
        val gradientColors =
            listOf(Color.Gray, Color.Magenta, colorResource(R.color.purple_200))
        Text(
            text = stringResource(R.string.app_name),
            style = TextStyle(brush = Brush.linearGradient(colors = gradientColors))
        )
    }
}

@Composable
fun picture() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .height(300.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Gatitos Bonitos",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
    }
}

