package com.jd.common.ui.customview;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.jd.utils.LogUtils;

/**
 * @author ：王文彬 on 2020/3/25 16：15
 * @describe ： 圆角裁剪
 * @email ：wangwenbin29@jd.com
 */
public class TextureVideoViewOutlineProvider extends ViewOutlineProvider {

    private int margin = 300;

    public TextureVideoViewOutlineProvider() {
    }

    public TextureVideoViewOutlineProvider(int margin) {
        this.margin = margin;
    }

    @Override
    public void getOutline(View view, Outline outline) {
        /*int left = 0;
        int top = (view.getHeight() - view.getWidth()) / 2;
        int right = view.getWidth();
        int bottom = (view.getHeight() - view.getWidth()) / 2 + view.getWidth();
        //outline.setOval(left, top, right, bottom);

        outline.setRoundRect(view.getWidth()/2, 200, view.getWidth(), view.getHeight(), 1000);*/

        int left = (view.getWidth() - view.getHeight()) / 2;
        int top = 0;
        int right = left + view.getHeight();
        int bottom = view.getHeight();
        LogUtils.e("TextureVideoViewOutlineProvider", left + "--" + top + "--" + right + "--" + bottom);
        outline.setOval(left + margin, top + margin, right - margin, bottom - margin);
        //outline.setRoundRect(left, top, right, bottom, 2800);
    }

}
