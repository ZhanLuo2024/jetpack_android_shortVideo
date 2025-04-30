package ie.setu.jetpack_android_shortvideo.network

import ie.setu.jetpack_android_shortvideo.model.Comment
import ie.setu.jetpack_android_shortvideo.model.Video
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface VideoApiService {
    @GET("videos")
    suspend fun getVideos(): List<Video>

    @GET("comments")
    suspend fun getComments(
        @Query("video_id") videoId: String = "video_01"
    ): List<Comment>

    @POST("likes")
    suspend fun likeVideo(@Body payload: Map<String, String>): retrofit2.Response<Unit>

}
