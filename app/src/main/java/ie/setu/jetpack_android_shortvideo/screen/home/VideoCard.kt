package ie.setu.jetpack_android_shortvideo.screen.home

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ie.setu.jetpack_android_shortvideo.network.RetrofitClient
import android.widget.Toast


@Composable
fun VideoCard(
    title: String,
    likeCount: Int,
    videoId: String,
    videoUrl: String,
    navController: NavController
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var currentLikes by remember { mutableStateOf(likeCount) }

    val exoPlayer = remember(videoUrl) {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.Builder()
                .setUri(Uri.parse(videoUrl))
                .setMimeType(MimeTypes.VIDEO_MP4)
                .build()
            setMediaItem(mediaItem)
            repeatMode = Player.REPEAT_MODE_ONE
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(9f / 16f),
            contentAlignment = Alignment.Center
        ) {
            AndroidView(
                factory = {
                    PlayerView(it).apply {
                        player = exoPlayer
                        useController = false
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            IconWithText(
                icon = Icons.Filled.Favorite,
                text = currentLikes.toString(),
                tint = Color.Red,
                onClick = {
                    currentLikes++
                    scope.launch {
                        try {
                            val response = RetrofitClient.videoApiService.likeVideo(
                                mapOf("video_id" to videoId)
                            )
                            if (!response.isSuccessful) {
                                currentLikes--
                                Toast.makeText(context, "Like failed", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            currentLikes--
                            Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
            IconWithText(
                icon = Icons.Filled.ChatBubbleOutline,
                text = "Comment",
                onClick = {
                    navController.navigate("comment")
                }
            )
        }
    }
}

@Composable
fun IconWithText(
    icon: ImageVector,
    text: String,
    tint: Color = Color.Gray,
    onClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = onClick?.let { Modifier.clickable { it() } } ?: Modifier
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = tint)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text)
    }
}
