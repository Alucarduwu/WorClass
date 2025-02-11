package com.example.worclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.worclass.ui.theme.WorClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorClassTheme {
                Column() {
                    Column() {
                        TextComposable(name= "xd")
                        TextComposable()
                        TextComposable()
                        TextComposable()



                    }
                    Row(){
                        TextComposable()
                        TextComposable()
                        TextComposable()
                        TextComposable()

                    }
                    Column() {
                        ModifierExample2()
                    }
                }

            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun TextComposable(name:String = "Empty"){
        Text(text = "Hello word")
        Text(name)
    }
    @Preview
    @Composable
    fun ModifierExample1(){
        Column(
            modifier = Modifier
                .padding(24.dp, 10.dp, 18.dp,30.dp)
        ) {
            Text(text = "Hello word")
        }
    }
    @Preview
    @Composable
    fun ModifierExample2(){
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .clickable(onClick = {clickAction()})
        ) {
            Text(text = "Hello word")
        }
    }

    fun clickAction(){
        println(":) onClick")
    }
    @Preview
    @Composable
    fun ModifierExample3(){
        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .background(Color.Gray)
            .border(width = 2.dp, color = Color.Black)
            .width(2.dp), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly){
            TextComposable(name = "A")
            TextComposable(name = "B")
            TextComposable(name = "C")
            TextComposable(name = "D")

        }

    }





}
    