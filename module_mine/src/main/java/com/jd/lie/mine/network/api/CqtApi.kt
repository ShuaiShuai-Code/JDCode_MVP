package com.jd.lie.mine.network.api

import com.jd.common.entity.BaseDataEntity
import com.jd.lie.mine.network.entity.AddRelateProblemEntity
import com.jd.lie.mine.network.entity.UserEntity
import com.jd.lie.mine.network.entity.VersionEntity
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

/**
 * @describe ：
 * @author ：王文彬 on 2020/5/20 15：00
 * @email ：wangwenbin29@jd.com
 */
interface CqtApi {

    @POST("trial/cqt/question/get")
    fun getAddRelateProblem(@Body map: Map<String, @JvmSuppressWildcards Any?>): Observable<AddRelateProblemEntity>


    @POST("trial/user/login")
    fun login(@Body map: Map<String, @JvmSuppressWildcards Any?>): Observable<UserEntity>

    @Multipart
    @POST("trial/photo/upload")
    fun postUpload(@Part partList: List<MultipartBody.Part>?): Observable<UserEntity?>?

    @POST("/trial/user/update")
    fun update(@Body map: Map<String, @JvmSuppressWildcards Any?>): Observable<UserEntity>

    @POST("trial/app/version/latest")
    fun updataVersion(@Body map: Map<String, @JvmSuppressWildcards Any?>): Observable<VersionEntity>


    @POST("trial/app/feedback/add")
    fun feedback(@Body map: Map<String, @JvmSuppressWildcards Any?>): Observable<UserEntity>


    @Multipart
    @POST("trial/photo/upload")
    fun postPortrait(
        @Part part: MultipartBody.Part,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): Observable<BaseDataEntity<String>>


}