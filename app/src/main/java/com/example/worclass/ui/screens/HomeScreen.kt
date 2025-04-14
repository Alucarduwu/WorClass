package com.example.worclass.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.worclass.R
@Composable
fun HomeScreen(navController: NavHostController){
    topbar()
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxSize()
        .padding(60.dp)
        .verticalScroll(rememberScrollState())
    )
    {
        Content1()
        Content2()
        Content1()
        Content2()
        Content1()
        Content2()
        Content1()
        Content2()

        //Botón que sirve para navegar a MainMenuScreen
        Button(onClick = {navController.navigate("MainMenu_Screen")}
        ) {
            Text("RETURN TO MAIN MENU")
        }
    }
}
@Preview
@Composable
fun Content1(){
    Card(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxWidth()
            .padding(5.dp)
    ){

        Text(
            text = "This is a title",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp)
        )
        Image(
            modifier = Modifier
                .width(80.dp)
                .height(100.dp),
            painter = painterResource(R.drawable.img),
            contentDescription = "Amongus",
            contentScale = ContentScale.Crop
        )
        Text(
            stringResource(R.string.app_name),
            textAlign = TextAlign.Justify,
            lineHeight = 18.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}
@Preview
@Composable
fun Content2(){
    Card(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxWidth()
            .padding(5.dp)
    ){
        Row (){
            Image(
                modifier = Modifier
                    .width(80.dp)
                    .height(100.dp),
                painter = painterResource(R.drawable.img),
                contentDescription = "Amongus",
                contentScale = ContentScale.Crop
            )
            Column() {
                Text(
                    text = "This is a title",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp)
                )
                Text(
                    stringResource(R.string.app_name),
                    textAlign = TextAlign.Justify,
                    lineHeight = 14.sp,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
@Preview
@Composable
fun topbar(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.Black)
            .padding(10.dp)
    ){
        Icon(
            Icons.Filled.Menu,
            contentDescription = "Menu_Icon",
            tint = Color.White,
            modifier = Modifier
                .size(50.dp)
        )
        Text(
            stringResource(R.string.app_name),
            color = Color.Black,
            fontSize = 30.sp
        )
    }
}