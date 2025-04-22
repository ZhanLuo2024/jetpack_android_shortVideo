package ie.setu.jetpack_android_shortvideo.screen.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import ie.setu.jetpack_android_shortvideo.viewmodel.DiscoverViewModel
import ie.setu.jetpack_android_shortvideo.model.Video
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.layout.ContentScale

@Composable
fun DiscoverScreen(navController: NavController) {
    val viewModel: DiscoverViewModel = viewModel()
    val videos = viewModel.videoList

    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        Text(
            text = "Discover",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(6.dp)) {
            items(videos) { video ->
                DiscoverGridItem(video = video) {
                    navController.navigate("preview/${video.id}")
                }
            }
        }
    }
}

@Composable
fun DiscoverGridItem(video: Video, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(6.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = rememberAsyncImagePainter(video.thumbnailUrl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = video.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF222222),
            maxLines = 1
        )
    }
}
