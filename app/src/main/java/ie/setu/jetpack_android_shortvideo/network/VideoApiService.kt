package ie.setu.jetpack_android_shortvideo.network

import ie.setu.jetpack_android_shortvideo.model.Video
import retrofit2.http.GET

interface VideoApiService {
    @GET("videos")
    suspend fun getVideos(): List<Video>
}
