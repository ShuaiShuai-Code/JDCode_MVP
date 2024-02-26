package com.jd.lie.mine.ui.fragment

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jd.common.constant.Constant
import com.jd.common.ui.customview.NetworkDialogUtils
import com.jd.common.ui.fragment.TrialCitAddFoilProblemMvpFragment
import com.jd.lie.mine.R
import com.jd.lie.mine.network.entity.AddRelateProblemEntity
import com.jd.lie.mine.presenter.CqtContract
import com.jd.lie.mine.presenter.CqtPresenter
import com.jd.lie.mine.ui.adapter.CqtAddRelateProblemAdapter
import com.jd.utils.DisplayUtil
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.fragment_cqt_add_relate_problem.*
import org.simple.eventbus.EventBus

/**
 * @author ：王文彬 on 2020/3/16 20：06
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
class CqtAddRelateFragment : TrialCitAddFoilProblemMvpFragment<CqtPresenter>(),
    CqtContract.View {

    private lateinit var mAdapter: CqtAddRelateProblemAdapter

    var topOffset = 0

    var behavior: BottomSheetBehavior<FrameLayout>? = null
        private set

    private lateinit var mContext: AppCompatActivity

    private var mBottomSheetDialog: BottomSheetDialog? = null

    private var dimensionId: String? = null
    private var crimeId: String? = null
    private var questionType: String? = null
    private var currentIndex = 1
    private var mSelectPosition = -1
    private var mDataList: MutableList<AddRelateProblemEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity as AppCompatActivity
        arguments?.apply {
            dimensionId = getString("dimensionId")
            crimeId = getString("crimeId")
            questionType = getString("questionType")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cqt_add_relate_problem, container, false)
    }

    override fun onStart() {
        super.onStart()
        mContext.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        mBottomSheetDialog = dialog as BottomSheetDialog?
        val bottomSheet =
            mBottomSheetDialog?.delegate?.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        if (bottomSheet != null) {
            val layoutParams =
                bottomSheet.layoutParams as CoordinatorLayout.LayoutParams
            layoutParams.height = height
            behavior = BottomSheetBehavior.from(bottomSheet)
            behavior?.isHideable = false
            behavior?.isDraggable = false
            behavior?.state = BottomSheetBehavior.STATE_EXPANDED
            behavior?.addBottomSheetCallback(mBottomSheetBehaviorCallback)
            behavior?.removeBottomSheetCallback(mBottomSheetBehaviorCallback)
        }
    }

    private val mBottomSheetBehaviorCallback: BottomSheetBehavior.BottomSheetCallback =
        object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(
                bottomSheet: View,
                newState: Int
            ) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    if (behavior != null) {
                        behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }
            }

            override fun onSlide(
                bottomSheet: View,
                slideOffset: Float
            ) {
            }
        }

    override fun onResume() {
        super.onResume()
        mContext = activity as AppCompatActivity
    }

    private val height: Int
        get() {
            val displayMetric = DisplayUtil.getDisplayMetrics(mContext)
            var height = displayMetric.heightPixels
            val wm = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            wm.defaultDisplay.getSize(point)
            height = point.y - topOffset
            return height
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initAdapter()
        initNet()
        initListener()
    }

    private fun initView() {
        rv_recycler_view.setHasFixedSize(true)
        rv_recycler_view.layoutManager = LinearLayoutManager(mContext)
    }

    private fun initAdapter() {
        mAdapter = CqtAddRelateProblemAdapter()
        rv_recycler_view.adapter = mAdapter
    }

    private fun initNet() {
        presenter.getAddRelateProblem(
            mContext as RxAppCompatActivity,
            dimensionId,
            crimeId,
            questionType,
            currentIndex++
        )
    }

    private fun initListener() {
        mAdapter.setOnItemClickListener(mOnItemClickListener)
        btn_change.setOnClickListener { initNet() }
        iv_close.setOnClickListener { closeDialog() }
    }

    override fun getBasePresenter(): CqtPresenter {
        return CqtPresenter()
    }

    override fun <E> showData(data: E) {
        mDataList = data as MutableList<AddRelateProblemEntity>
        mAdapter.setNewInstance(mDataList)
    }

    override fun showEmptyView() {
        /*val view = View.inflate(mContext, R.layout.view_empty, null)
        view.findViewById<View>(R.id.tv_content)
            .setOnClickListener { v: View ->
                currentIndex = 1
                initNet()
            }
        mAdapter.setEmptyView(view)*/
    }

    override fun showLoading() {
        super.showLoading()
        NetworkDialogUtils.show(mContext)
    }

    override fun hideLoading() {
        NetworkDialogUtils.dismiss()
    }

    private val mOnItemClickListener: OnItemClickListener =
        OnItemClickListener { adapter, view, position ->
            if (mSelectPosition == position) {
                mDataList?.get(mSelectPosition)?.isSelect = false
                mAdapter.notifyItemChanged(position)
                mSelectPosition = -1
            } else if (mSelectPosition != position && mSelectPosition != -1) {
                mDataList?.get(mSelectPosition)?.isSelect = false
                mAdapter.notifyItemChanged(mSelectPosition)

                mSelectPosition = position
                mDataList?.get(position)?.isSelect = true
                mAdapter.notifyItemChanged(position)
            } else if (mSelectPosition == -1) {
                mSelectPosition = position
                mDataList?.get(position)?.isSelect = true
                mAdapter.notifyItemChanged(position)
            }
            val entity: AddRelateProblemEntity = adapter.data[position] as AddRelateProblemEntity
            entity.questionType = questionType
            closeDialog()
        }

    private fun closeDialog() {
        if (behavior != null) {
            behavior!!.state = BottomSheetBehavior.STATE_HIDDEN
        }
        if (mBottomSheetDialog!!.isShowing) {
            mBottomSheetDialog!!.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        closeDialog()
    }
}