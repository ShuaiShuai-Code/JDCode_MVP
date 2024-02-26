package com.jd.lie.mine.ui.activity


import com.jd.common.ui.activity.BaseMvpActivity
import com.jd.lie.mine.R
import com.jd.lie.mine.presenter.UpdataContract
import com.jd.lie.mine.presenter.UpdataPresenter
import com.jd.lie.mine.utils.PhotoUtilKt
import com.jd.utils.ToastUtils
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_infor_updata.*


/**
 * 设置
 */
class UpdataInforActivity : BaseMvpActivity<UpdataPresenter>(), UpdataContract.View {


    override fun initLayout(): Int = R.layout.activity_infor_updata
    private lateinit var photoUtilKt: PhotoUtilKt
    override fun needHeader(): Boolean {
        return true
    }

    override fun <E> showData(data: E) {
        super.showData(data)
        ToastUtils.show(data.toString())
    }
    override fun chooseHeader(): Header {
        return Header.SUB
    }

    override fun initListener() {
        super.initListener()
        tv_submit.setOnClickListener {
            presenter.updata(thisActivity as RxAppCompatActivity, "", edit_phone.text.toString())
        }
    }


    override fun initView() {
        super.initView()
        photoUtilKt = PhotoUtilKt()
        setTvSubLeftTitle(intent.getStringExtra("title"))


    }

    override fun getBasePresenter(): UpdataPresenter {
        return UpdataPresenter()
    }

}














