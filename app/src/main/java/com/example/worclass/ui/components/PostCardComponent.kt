package com.example.worclass.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
    fun PostCardComponent(id:Int, title:String, text:String,image: Int ){
        Card(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxSize()
                .padding(5.dp)
        ){
            Text (text= "This is a title", fontSize = 24.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier .padding(10.dp)
            )
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp),
                painter = painterResource(image),
                contentDescription = "Je",
                contentScale = ContentScale.Crop
            )
            Text(
                text = text,
                //stringResource( R.string.app_name),
                textAlign = TextAlign.Justify,
                lineHeight = 18.sp,
                modifier = Modifier.padding(10.dp)
            )




        }
}