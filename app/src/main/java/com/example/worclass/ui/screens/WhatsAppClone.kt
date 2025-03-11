import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun WhatsAppCloneApp(navHostController: NavHostController) {
    val listState = rememberLazyListState()
    var isScrollingUp by remember { mutableStateOf(true) }
    var lastScrollOffset by remember { mutableStateOf(0) }

    // Detecta el scroll correctamente
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { scrollOffset ->
                isScrollingUp = scrollOffset < lastScrollOffset
                lastScrollOffset = scrollOffset
            }
    }

    Scaffold(
        topBar = { WhatsAppTopBar() },
        floatingActionButton = { FloatingActionButtons() },
        bottomBar = { WhatsAppBottomNavBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xFF0A1014))
        ) {
            // SCROLL PERO PARA OCULTAR LA BARRA EHHHHH
            AnimatedVisibility(visible = isScrollingUp) {
                Column {
                    SearchBarOnly()
                    TabsRow()
                }
            }
            ChatList(listState)
        }
    }
}


@Composable
fun FloatingActionButtons() {
    Column(
        modifier = Modifier.fillMaxSize().padding(end = 8.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            onClick = { /* TODO */ },
            modifier = Modifier.padding(4.dp),
            containerColor = Color(0xFF25D366),
            contentColor = Color.White
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Chat")
        }
        Spacer(modifier = Modifier.height(16.dp))
        FloatingActionButton(
            onClick = { /* TODO */ },
            modifier = Modifier.padding(4.dp),
            containerColor = Color(0xFF128C7E),
            contentColor = Color.White
        ) {
            //Por si quiero agg alguna acción al botonsito
            Icon(Icons.Default.Add, contentDescription = "Camera")
        }
    }

}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatsAppTopBar() {
    var showMenu by remember { mutableStateOf(false) } // Estado para controlar la visibilidad del menú

    TopAppBar(
        title = {
            Text("WhatsApp", fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Color.White)
        },
        actions = {
            IconButton(onClick = {}) {
                //Icon(painterResource(id = drawable.cam), contentDescription = "Camera", tint = Color.White)
            }


            IconButton(onClick = { showMenu = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Menu", tint = Color.White)
            }

            // MENUSITO DE 3 PUNTOS
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Nuevo grupo") },
                    onClick = { showMenu = false }
                )
                DropdownMenuItem(
                    text = { Text("Nueva difusión") },
                    onClick = { showMenu = false }
                )
                DropdownMenuItem(
                    text = { Text("Dispositivos vinculados") },
                    onClick = { showMenu = false }
                )
                DropdownMenuItem(
                    text = { Text("Mensajes destacados") },
                    onClick = { showMenu = false }
                )
                DropdownMenuItem(
                    text = { Text("Ajustes") },
                    onClick = { showMenu = false }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
    )
}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarOnly() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(8.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Preguntar a Meta AI o buscar", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(Color(0xFF1E1E1E)),
            colors = TextFieldDefaults.run {
                textFieldColors(
                        //containerColor = Color(0xFF1E1E1E),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent

                    )
            }
        )
    }
}
@Preview
@Composable
fun TabsRow() {
    Row(modifier = Modifier.fillMaxWidth().background(Color.Black).padding(8.dp), horizontalArrangement = Arrangement.SpaceAround) {
        listOf("Todos", "No leídoss", "Favoritos", "Grupos").forEach { tab ->
            Text(
                text = tab,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier
                    .clip(androidx.compose.foundation.shape.CircleShape)
                    .background(Color(0xFF1E1E1E))
                    .padding(horizontal = 16.dp, vertical = 8.dp)

            )
        }
    }
}




@Composable
fun ChatList(listState: LazyListState) {
    val initialChats = listOf(
        ChatData("Tim @i", "Little: Sticker", "8:16 a. m.", true, 2),
        ChatData("Miguel", "Hoy ya no", "7:58 a. m.", true, 1),
        ChatData("Luy", "Buenos días", "7:34 a. m.", false, 0),
        ChatData("Abeto bb", "Sticker", "7:05 a. m.", false, 0),
        ChatData("Raíz Negativa", "Increíble", "7:02 a. m.", true, 3),
        ChatData("Meli", "Sticker", "7:00 a. m.", false, 0),
        ChatData("Cristina", "uyyy, ansiosa? de terminar ya alv", "6:50 a. m.", true, 5)
    )

    val randomChats = List(20) { i ->
        ChatData(
            name = "Contacto ${i + 8}",
            message = listOf("Hola", "¿Qué tal?", "Nos vemos", "Jajaja", "😂").random(),
            time = "${(1..12).random()}:${(10..59).random()} a. m.",
            isMuted = (0..1).random() == 1,
            unreadCount = if ((0..1).random() == 1) (1..5).random() else 0
        )
    }

    val chats = remember { initialChats + randomChats }

    LazyColumn(
        state = listState, // RE-LLAMAMOS EL SCROLL
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        items(chats) { chat ->
            ChatItem(chat)
        }
    }
}


@Composable
fun ChatItem(chat: ChatData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 8.dp)
            .background(Color(0xFF000000), RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Gray, CircleShape), // FONDITO IMAGENES
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.AccountCircle,
                contentDescription = "Profile",
                tint = Color.White,
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = chat.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
            Text(text = chat.message, color = Color(0xFFB0B0B0), fontSize = 14.sp)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = chat.time, color = Color(0xFF888888), fontSize = 12.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (chat.isMuted) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "Muted",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                if (chat.unreadCount > 0) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color(0xFF25D366), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = chat.unreadCount.toString(), color = Color.White, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun WhatsAppBottomNavBar() {
    NavigationBar(containerColor = Color.Black, contentColor = Color.White) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Menu, contentDescription = "Chats") },
            label = { Text("Chats") },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Send, contentDescription = "Novedades") },
            label = { Text("Novedades") },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Comunidades") },
            label = { Text("Comunidades") },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Call, contentDescription = "Llamadas") },
            label = { Text("Llamadas") },
            selected = false,
            onClick = { }
        )
    }
}
data class ChatData(
    val name: String,
    val message: String,
    val time: String,
    val isMuted: Boolean,
    val unreadCount: Int
)



@Preview(showBackground = true)
@Composable
fun PreviewWhatsAppCloneApp() {
    WhatsAppCloneApp(navHostController = rememberNavController()) // Fake controller solo para preview
}

