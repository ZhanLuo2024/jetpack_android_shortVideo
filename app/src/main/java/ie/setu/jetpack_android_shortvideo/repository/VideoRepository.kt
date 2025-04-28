package ie.setu.jetpack_android_shortvideo.repository

import ie.setu.jetpack_android_shortvideo.model.Video
import ie.setu.jetpack_android_shortvideo.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class VideoRepository {

    suspend fun getVideos(): List<Video> {
        return try {
            withContext(Dispatchers.IO) {
                RetrofitClient.videoApiService.getVideos()
            }
        } catch (e: IOException) {
            // 網路錯誤（比如：無網路、超時）
            emptyList()
        } catch (e: Exception) {
            // 其他錯誤
            emptyList()
        }
    }
}

