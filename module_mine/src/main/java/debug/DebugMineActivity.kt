package debug

import com.jd.common.ui.activity.BaseActivity
import com.jd.lie.mine.R
import com.jd.lie.mine.ui.fragment.MineFragment

/**
 * @describe ：
 * @author ：王文彬 on 2020/5/31 11：52
 * @email ：wangwenbin29@jd.com
 */
class DebugMineActivity : BaseActivity() {
    override fun initLayout(): Int = R.layout.activity_debug_mine

    override fun initView() {
        super.initView()
        supportFragmentManager.beginTransaction().add(R.id.clContent, MineFragment.newInstance())
            .commit()
    }
}