package com.example.worclass.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.worclass.R


@Composable
fun FavoriteAccountCard(
    id: Int,
    name: String,
    username: String,
    password: String,
    description: String,
    imageURL: String,
    onDeleteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = imageURL,
                contentDescription = "Account Logo",
                contentScale = ContentScale.Fit,
                error = painterResource(R.drawable.img),
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                // .clip(RoundedCornerShape(12.dp))
            )
            // Nombre
            Text(
                text = name,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold
            )

            // btn delete
            IconButton(onClick = { onDeleteClick() }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete Account")
            }
        }

        // Fila que muestra el nombre de usuario
        Row(
            modifier = Modifier
                .fillMaxWidth()  // La fila ocupa todo el ancho disponible
                .padding(5.dp),  // Padding interno
            horizontalArrangement = Arrangement.SpaceBetween,  // Espaciado entre los elementos
            verticalAlignment = Alignment.CenterVertically  // Alineación vertical centrada
        ) {

            Text(
                text = "Username",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            // Nombre
            Text(
                text = username,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Password",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = password,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // descripción
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Description:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = description,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}