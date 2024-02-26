package com.jd.lie.mine.network.api

import com.jd.lie.mine.network.entity.AddRelateProblemEntity
import com.jd.lie.mine.network.entity.UserEntity
import com.jd.lie.mine.network.entity.VersionEntity
import com.mp5a5.www.library.use.RetrofitFactory
import io.reactivex.Observable
import okhttp3.MultipartBody

/**
 * @describe ：
 * @author ：王文彬 on 2020/5/20 15：00
 * @email ：wangwenbin29@jd.com
 */
object CqtService {

    private val mCqtApi: CqtApi by lazy {
        RetrofitFactory.getInstance()
            .create(CqtApi::class.java)
    }


    fun getAddProblemList(
        dimensionId: String?,
        crimeId: String?,
        questionType: String?,
        index: Int
    ): Observable<AddRelateProblemEntity> {
        val map = mutableMapOf<String, Any?>()
        map["dimensionId"] = dimensionId
        map["crimeId"] = crimeId
        map["questionType"] = questionType
        map["index"] = index
        return mCqtApi.getAddRelateProblem(map)
    }

    fun login(
        userName: String?,
        password: String?
    ): Observable<UserEntity> {
        val map = mutableMapOf<String, Any?>()
        map["userName"] = userName
        map["password"] = password
        return mCqtApi.login(map)
    }

    fun uploadPic(
        picList: List<MultipartBody.Part>?
    ): Observable<UserEntity?>? {
        return mCqtApi.postUpload(picList)
    }

    fun update(
        photoUrl: String?,
        phoneNumber: String?
    ): Observable<UserEntity> {
        val map = mutableMapOf<String, Any?>()
        map["photoUrl"] = photoUrl
        map["phoneNumber"] = phoneNumber
        return mCqtApi.update(map)
    }

    fun updateVersion(
        versionNumber: String?
    ): Observable<VersionEntity> {
        val map = mutableMapOf<String, Any?>()
        map["versionNumber"] = versionNumber
        return mCqtApi.updataVersion(map)
    }

    fun feedback(
        versionNumber: String?,
        feedbackType: String?,
        feedbackContent: String?,
        urls:List<String>
    ): Observable<UserEntity> {
        val map = mutableMapOf<String, Any?>()
        map["feedbackType"] = feedbackType
        map["versionNumber"] = versionNumber
        map["feedbackContent"] = feedbackContent
        map["urls"] = urls
        return mCqtApi.feedback(map)
    }
}