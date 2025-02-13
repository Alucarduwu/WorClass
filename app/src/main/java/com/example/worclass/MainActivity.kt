package com.example.worclass

import android.os.Bundle
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
import androidx.compose.foundation.layout.Row  // ✅ Corrección aquí
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
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
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.worclass.ui.screens.MainMenuScreen
import com.example.worclass.ui.theme.WorClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorClassTheme {
                ComposeMultiScreenApp()
                /*Column() {
                    Column() {
                        TextComposable(name = "xd")
                        TextComposable()
                        TextComposable()
                        TextComposable()
                    }
                    Row() {
                        TextComposable()
                        TextComposable()
                        TextComposable()
                        TextComposable()
                    }
                    Column() {
                        ModifierExample2()
                        ModifierExample4()
                        CustomText()
                        picture()
                    }
                }*/
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextComposable(name: String = "Empty") {
    Text(text = "Hello word")
    Text(name)
}

@Preview
@Composable
fun ModifierExample1() {
    Column(
        modifier = Modifier
            .padding(24.dp, 10.dp, 18.dp, 30.dp)
    ) {
        Text(text = "Hello word")
    }
}

@Preview
@Composable
fun ModifierExample2() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .clickable(onClick = { clickAction() })
    ) {
        Text(text = "Hello word")
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
            .width(2.dp), horizontalAlignment = Alignment.CenterHorizontally,
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
    Column() {
        Text(
            stringResource(R.string.app_name),
            color = colorResource(R.color.purple_500),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold
        )
        val gradientColors =
            listOf(Color.Gray, Color.Magenta, colorResource(R.color.purple_200))
        Text(
            stringResource(R.string.app_name),
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
            painter = painterResource(R.drawable.img), // ✅ Corrección aquí
            contentDescription = "Gatitos Bonitos",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun ComposeMultiScreenApp() {
    val navController = rememberNavController()
    SetUpNavGraph(navController = navController)
}

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "MainMenuScreen" // ✅ Corrección aquí
    ) {
        composable("MainMenuScreen") { MainMenuScreen(navController) }
    }
}
