package ie.setu.jetpack_android_shortvideo.screen.usercenter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import ie.setu.jetpack_android_shortvideo.viewmodel.DiscoverViewModel
import ie.setu.jetpack_android_shortvideo.viewmodel.SharedUiViewModel

@Composable
fun UserCenterScreen(
    discoverViewModel: DiscoverViewModel,
    sharedUiViewModel: SharedUiViewModel
) {
    var isLoginDialogOpen by remember { mutableStateOf(false) }
    val isLoggedIn by sharedUiViewModel.isLoggedIn

    if (!isLoggedIn) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("You are not logged in", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { isLoginDialogOpen = true }) {
                Text("Login")
            }
        }
    }

    if (isLoginDialogOpen) {
        LoginDialog(
            onDismiss = { isLoginDialogOpen = false },
            onLoginSuccess = {
                sharedUiViewModel.login()
                isLoginDialogOpen = false
            }
        )
    }

    if (!isLoggedIn) return

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 卡片包裝頭像與統計資料
        Card(
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 頭像與名稱
                Image(
                    painter = rememberAsyncImagePainter("https://placekitten.com/150/150"),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Text("DemoUser", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(Modifier.height(16.dp))

                // 統計資料
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Videos\n10", textAlign = TextAlign.Center)
                    Text("Likes\n999", textAlign = TextAlign.Center)
                    Text("Comments\n33", textAlign = TextAlign.Center)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // 發佈影片清單（模擬 demo 用）
        Text("My Videos", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxHeight(),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(discoverViewModel.videoList.take(6)) { video ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = rememberAsyncImagePainter(video.thumbnailUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Text(video.title, textAlign = TextAlign.Center, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun LoginDialog(onDismiss: () -> Unit, onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Login") },
        text = {
            Column {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                if (email == "demo@example.com" && password == "123456") {
                    onLoginSuccess()
                }
            }) {
                Text("Login")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


