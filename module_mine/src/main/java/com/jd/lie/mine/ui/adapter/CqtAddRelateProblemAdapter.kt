package com.jd.lie.mine.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jd.lie.mine.R
import com.jd.lie.mine.network.entity.AddRelateProblemEntity
import com.jd.utils.StringUtils

/**
 * @author ：王文彬 on 2020/3/16 19：54
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
class CqtAddRelateProblemAdapter(private val layoutId: Int = R.layout.item_cqt_add_relate_problem) :
    BaseQuickAdapter<AddRelateProblemEntity, BaseViewHolder>(layoutId) {
    override fun convert(
        holder: BaseViewHolder,
        item: AddRelateProblemEntity
    ) {
        holder.setText(R.id.tv_text_first, item.questionContentPart1)
        holder.setText(R.id.tv_text_third, item.questionContentPart3)
        if (StringUtils.isNotNull(item.questionContentPart2) || StringUtils.isNotNull(item.questionContentPart3)) {
            holder.setText(R.id.tv_text_second, item.questionDefinition)
        }
        if (item.isSelect) {
            holder.setImageResource(R.id.iv_select, R.mipmap.ic_foil_problem_select)
        } else {
            holder.setImageResource(R.id.iv_select, R.mipmap.ic_foil_problem_no_select)
        }
    }

}