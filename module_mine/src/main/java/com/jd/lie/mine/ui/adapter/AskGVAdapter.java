package com.jd.lie.mine.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import androidx.constraintlayout.widget.ConstraintLayout;

import com.jd.lie.mine.R;
import com.jd.lie.mine.utils.PhotoUtils;
import com.jd.utils.sb.thridlib.StringUtils;

import java.util.ArrayList;
import java.util.List;

/***
 * 图片适配器
 *
 * @author wsj
 * @UpdateDate 2014-09-04
 */
public class AskGVAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private int maxImg = 9;
    /***
     * 图片宽度
     */
    private int width;


    private List<Bitmap> bitmaps;
    private LayoutInflater inflater;

    public AskGVAdapter(Context context, int width) {
        this.context = context;
        this.list = new ArrayList<>();
        this.list.add("");
        this.width = width;
        this.inflater = LayoutInflater.from(context);
        this.bitmaps = new ArrayList<>();
    }

    public void setMaxImg(int maxImg) {
        this.maxImg = maxImg;
    }

    /***
     * 设置图片列表数据
     *
     * @param list
     *            图片列表数据
     */
    public void setListData(List<String> list) {
        for (int i = 0; i < bitmaps.size(); i++) {
            bitmaps.get(i).recycle();
        }
        bitmaps.clear();
        this.list.clear();
        this.list.addAll(list);
        if (list.size() < maxImg) {
            this.list.add("");
        }
        for (int i = 0; i < list.size(); i++) {
            if (!StringUtils.isEmpty(list.get(i)))
                bitmaps.add(PhotoUtils.getSmallBitmap(list.get(i), width, width));
        }
        notifyDataSetChanged();
    }

    /***
     * 清除图片列表数据
     */
    public void clearListData() {
        this.list.clear();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.photopick_img, parent, false);
        //ImageView iv = new ImageView(context);
        ImageView iv = view.findViewById(R.id.img);
        ImageView img_delete = view.findViewById(R.id.img_delete);
        //iv.setLayoutParams(new ConstraintLayout.LayoutParams(width, width));
        iv.setBackground(context.getResources().getDrawable(R.drawable.bg_round_color_quxiao));
        //convertView= LayoutInflater.from(context).inflate(R.layout.adapter_child_ask,null);

        if (list.size() <= maxImg) {
            if (position == list.size() - 1) {
                if (!StringUtils.isEmpty(list.get(position))) {
                    iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    iv.setImageBitmap(bitmaps.get(position));
                    img_delete.setVisibility(View.VISIBLE);
                } else {
                    iv.setScaleType(ImageView.ScaleType.CENTER);
                    //  iv.setLayoutParams(new ConstraintLayout.LayoutParams(width, width));
                    iv.setImageResource(R.mipmap.img_add);
                    img_delete.setVisibility(View.GONE);
                }
            } else {
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv.setImageBitmap(bitmaps.get(position));
                img_delete.setVisibility(View.VISIBLE);
            }
        } else {
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setImageBitmap(bitmaps.get(position));
            img_delete.setVisibility(View.VISIBLE);
        }
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });


        return view;
    }
}
