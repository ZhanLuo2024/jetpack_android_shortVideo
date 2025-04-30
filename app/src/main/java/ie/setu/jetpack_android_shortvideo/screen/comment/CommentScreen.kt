package ie.setu.jetpack_android_shortvideo.screen.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import ie.setu.jetpack_android_shortvideo.model.Comment
import ie.setu.jetpack_android_shortvideo.viewmodel.CommentUiState
import ie.setu.jetpack_android_shortvideo.viewmodel.CommentViewModel
import ie.setu.jetpack_android_shortvideo.viewmodel.SharedUiViewModel
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun CommentScreen(
    sharedUiViewModel: SharedUiViewModel,
    commentViewModel: CommentViewModel = viewModel()
) {
    // 隱藏 TabBar
    LaunchedEffect(Unit) { sharedUiViewModel.hideTab() }
    DisposableEffect(Unit) { onDispose { sharedUiViewModel.showTab() } }

    val uiState by commentViewModel.uiState.collectAsState()
    var newComment by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Comments",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        when (uiState) {
            is CommentUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is CommentUiState.Error -> {
                val message = (uiState as CommentUiState.Error).message
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = message, color = MaterialTheme.colorScheme.error)
                }
            }

            is CommentUiState.Success -> {
                val comments = (uiState as CommentUiState.Success).comments

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                ) {
                    items(comments) { comment ->
                        CommentItem(comment = comment)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = newComment,
                        onValueChange = { newComment = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Write a comment...") }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            if (newComment.isNotBlank()) {
                                commentViewModel.addComment(newComment)
                                newComment = ""
                            }
                        }
                    ) {
                        Text("Send")
                    }
                }
            }
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = comment.user,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF1C1C1E)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = comment.content,
                fontSize = 15.sp,
                color = Color(0xFF3C3C43)
            )

            comment.imageUrl?.let { url ->
                Spacer(modifier = Modifier.height(12.dp))
                Image(
                    painter = rememberAsyncImagePainter(url),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
