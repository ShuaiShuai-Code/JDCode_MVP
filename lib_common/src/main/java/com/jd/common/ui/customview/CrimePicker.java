package com.jd.common.ui.customview;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.jd.common.entity.CrimeEntity;
import com.jd.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import cn.addapp.pickers.adapter.ArrayWheelAdapter;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnMoreWheelListener;
import cn.addapp.pickers.picker.LinkagePicker;
import cn.addapp.pickers.widget.WheelView;

/**
 * @author ：王文彬 on 2020/3/16 23：08
 * @describe ：犯罪选择器
 * @email ：wangwenbin29@jd.com
 */
public class CrimePicker extends LinkagePicker {

  private OnCrimeLinkageListener onLinkageListener;
  private OnMoreWheelListener onMoreWheelListener;
  private List<CrimeEntity.Crime> crimes;

  public CrimePicker(Activity activity, List<CrimeEntity.Crime> crimes) {
    super(activity, new CrimeProvider(crimes));
    this.crimes = crimes;
  }

  /**
   * 设置默认选中
   */
  public void CrimePicker(String crime, String level) {
    super.setSelectedItem(crime, level);
  }


  public CrimeEntity.Crime getSelectedCrime() {
    return crimes.get(selectedFirstIndex);
  }

  public CrimeEntity.Crime.Level getSelectedLevel() {
    return getSelectedCrime().level.get(selectedSecondIndex);
  }


  /**
   * 设置滑动监听器
   */
  @Override
  public void setOnMoreWheelListener(OnMoreWheelListener onMoreWheelListener) {
    this.onMoreWheelListener = onMoreWheelListener;
  }

  public void setOnLinkageListener(OnCrimeLinkageListener listener) {
    this.onLinkageListener = listener;
  }

  @NonNull
  @Override
  protected View makeCenterView() {
    if (null == provider) {
      throw new IllegalArgumentException("please set address provider before make view");
    }
    int[] widths = getColumnWidths(true);
    int provinceWidth = widths[0];
    int cityWidth = widths[1];
    LinearLayout layout = new LinearLayout(activity);
    layout.setOrientation(LinearLayout.HORIZONTAL);
    layout.setGravity(Gravity.CENTER);
    final WheelView crimeView = new WheelView(activity);
    crimeView.setCanLoop(canLoop);
    crimeView.setLayoutParams(new LinearLayout.LayoutParams(provinceWidth, WRAP_CONTENT));
    crimeView.setTextSize(textSize);
    crimeView.setSelectedTextColor(textColorFocus);
    crimeView.setUnSelectedTextColor(textColorNormal);
    crimeView.setLineConfig(lineConfig);
    crimeView.setAdapter(new ArrayWheelAdapter<>(provider.provideFirstData()));
    crimeView.setCurrentItem(selectedFirstIndex);
    layout.addView(crimeView);

    final WheelView levelView = new WheelView(activity);
    levelView.setCanLoop(canLoop);
    levelView.setTextSize(textSize);
    levelView.setSelectedTextColor(textColorFocus);
    levelView.setUnSelectedTextColor(textColorNormal);
    levelView.setLineConfig(lineConfig);
    levelView.setAdapter(new ArrayWheelAdapter<>(provider.provideSecondData(selectedFirstIndex)));
    levelView.setCurrentItem(selectedSecondIndex);
    levelView.setLayoutParams(new LinearLayout.LayoutParams(cityWidth, WRAP_CONTENT));
    layout.addView(levelView);

    crimeView.setOnItemPickListener(new OnItemPickListener<String>() {
      @Override
      public void onItemPicked(int index, String item) {
        selectedFirstItem = item;
        selectedFirstIndex = index;
        if (onMoreWheelListener != null) {
          onMoreWheelListener.onFirstWheeled(selectedFirstIndex, selectedFirstItem);
        }
        LogUtils.e("change cities after province wheeled");
        selectedSecondIndex = 0;//重置level索引
        List<String> cities = provider.provideSecondData(selectedFirstIndex);
        if (cities.size() > 0) {
          levelView.setAdapter(new ArrayWheelAdapter<>(cities));
          levelView.setCurrentItem(selectedSecondIndex);
        } else {
          levelView.setAdapter(new ArrayWheelAdapter<>(new ArrayList<String>()));
        }
      }
    });

    levelView.setOnItemPickListener((OnItemPickListener<String>) (index, item) -> {
      selectedSecondItem = item;
      selectedSecondIndex = index;
      if (onMoreWheelListener != null) {
        onMoreWheelListener.onSecondWheeled(selectedSecondIndex, selectedSecondItem);
      }
    });

    return layout;
  }

  @Override
  public void onSubmit() {
    if (onLinkageListener != null) {
      CrimeEntity.Crime crime = getSelectedCrime();
      CrimeEntity.Crime.Level level = getSelectedLevel();
      onLinkageListener.onCrimePicked(crime, level);
    }
  }


  /**
   * 地址提供者
   */
  public static class CrimeProvider implements DataProvider {
    private List<String> firstList = new ArrayList<>();
    private List<List<String>> secondList = new ArrayList<>();

    public CrimeProvider(List<CrimeEntity.Crime> crimes) {
      parseData(crimes);
    }

    @Override
    public boolean isOnlyTwo() {
      return true;
    }

    @Override
    public List<String> provideFirstData() {
      return firstList;
    }

    @Override
    public List<String> provideSecondData(int firstIndex) {
      return secondList.get(firstIndex);
    }

    @Override
    public List<String> provideThirdData(int firstIndex, int secondIndex) {
      return null;
    }

    private void parseData(List<CrimeEntity.Crime> data) {
      int crimeSize = data.size();
      //添加省
      for (int x = 0; x < crimeSize; x++) {
        CrimeEntity.Crime crime = data.get(x);
        firstList.add(crime.crimeLevelOne);
        List<CrimeEntity.Crime.Level> cities = crime.level;
        List<String> xLevels = new ArrayList<>();
        int citySize = cities.size();
        //添加地市
        for (int y = 0; y < citySize; y++) {
          CrimeEntity.Crime.Level cit = cities.get(y);
          xLevels.add(cit.crimeLevelTwo);
        }
        secondList.add(xLevels);
      }
    }
  }

  public interface OnCrimeLinkageListener {
    void onCrimePicked(CrimeEntity.Crime crime, CrimeEntity.Crime.Level level);
  }
}
