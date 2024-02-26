package com.jd.lie.mine.network.entity;

import com.jd.common.entity.BaseDataEntity;

import java.util.List;

/**
 * @author ：王文彬 on 2020/5/21 16：15
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class AddRelateProblemEntity extends BaseDataEntity<List<AddRelateProblemEntity>> {
    public String questionId;
    public String questionContentPart1;
    public String questionContentPart2;
    public String questionContentPart3;
    public String questionDefinition;
    public String dimensionId;

    public String questionType;
    public boolean isSelect = false;
}
