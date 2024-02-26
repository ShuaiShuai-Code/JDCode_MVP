package com.jd.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;


public class BitmapUtil {

  /**
   * 添加logo到二维码图片上
   */
  public static Bitmap addLogo2QrCode(@NonNull Bitmap original, @NonNull Bitmap logo) {

    int srcWidth = original.getWidth();
    int srcHeight = original.getHeight();
    int logoWidth = logo.getWidth();
    int logoHeight = logo.getHeight();

    if (logoWidth > srcWidth) {
      logoWidth = srcWidth / 4;
      logoHeight = srcHeight / 4;
      logo = resizedBitmap(logo, logoWidth, logoHeight);
    }

    Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
    try {
      Canvas canvas = new Canvas(bitmap);
      canvas.drawBitmap(original, 0, 0, null);
      canvas.drawBitmap(logo, (srcWidth - logoWidth) >> 1, (srcHeight - logoHeight) >> 1, null);
      canvas.save();
      canvas.restore();
    } catch (Exception e) {
      e.printStackTrace();
      bitmap = original;
    }
    return bitmap;
  }

  private static Bitmap resizedBitmap(Bitmap original, int newWidth, int newHeight) {
    int width = original.getWidth();
    int height = original.getHeight();
    float scaleWidth = ((float) newWidth) / width;
    float scaleHeight = ((float) newHeight) / height;
    // create a matrix for the manipulation
    Matrix matrix = new Matrix();
    // resize the bitmap
    matrix.postScale(scaleWidth, scaleHeight);
    // "recreate" the new bitmap
    Bitmap resizedBitmap = Bitmap.createBitmap(original, 0, 0, width, height, matrix, false);
    original.recycle();
    return resizedBitmap;
  }

  public static Bitmap rotateBitmap(Bitmap original, float degrees) {
    int width = original.getWidth();
    int height = original.getHeight();

    Matrix matrix = new Matrix();
    matrix.postRotate(degrees);

    Bitmap rotatedBitmap = Bitmap.createBitmap(original, 0, 0, width, height, matrix, true);
    original.recycle();
    return rotatedBitmap;
  }

  public static Bitmap getBitmap(Context context, int vectorDrawableId) {
    Bitmap bitmap;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
      Drawable vectorDrawable = context.getDrawable(vectorDrawableId);
      if (vectorDrawable == null) {
        return null;
      }
      bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(),
          Bitmap.Config.ARGB_8888);
      Canvas canvas = new Canvas(bitmap);
      vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
      vectorDrawable.draw(canvas);
    } else {
      bitmap = BitmapFactory.decodeResource(context.getResources(), vectorDrawableId);
    }
    return bitmap;
  }

  /**
   * 将本地图片文件转换成可解码二维码的 Bitmap
   *
   * @param picturePath 本地图片文件路径
   */
  public static Bitmap decodeLocalFile2Bitmap(String picturePath) {
    try {
      BitmapFactory.Options options = new BitmapFactory.Options();
      options.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(picturePath, options);
      int sampleSize = Math.min(options.outHeight, options.outWidth) / 500;
      if (sampleSize <= 0) {
        sampleSize = 1;
      }
      options.inSampleSize = sampleSize;
      options.inJustDecodeBounds = false;

      return BitmapFactory.decodeFile(picturePath, options);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 放大bitmap
   */
  public static Bitmap zoomBitmap(Bitmap bitmap) {
    Matrix matrix = new Matrix();
    //长和宽放大缩小的比例
    matrix.postScale(2.5f, 2.5f);
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
  }

  /**
   * 将彩色图转换为灰度图
   *
   * @param img 位图
   * @return 返回转换好的位图
   */
  public static Bitmap convertGreyImg(Bitmap img) {
    //获取位图的宽
    int width = img.getWidth();
    //获取位图的高
    int height = img.getHeight();
    //通过位图的大小创建像素点数组
    int[] pixels = new int[width * height];

    img.getPixels(pixels, 0, width, 0, 0, width, height);
    int alpha = 0xFF << 24;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int grey = pixels[width * i + j];
        int red = ((grey & 0x00FF0000) >> 16);
        int green = ((grey & 0x0000FF00) >> 8);
        int blue = (grey & 0x000000FF);
        grey = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
        grey = alpha | (grey << 16) | (grey << 8) | grey;
        pixels[width * i + j] = grey;
      }
    }
    Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    result.setPixels(pixels, 0, width, 0, 0, width, height);
    return result;
  }


  /**
   * 对图片进行二值化处理
   *
   * @param bm 原始图片
   * @return 二值化处理后的图片
   */
  public static Bitmap binaryBitmap(Bitmap bm) {
    Bitmap bitmap = null;
    // 获取图片的宽和高
    int width = bm.getWidth();
    int height = bm.getHeight();
    // 创建二值化图像
    bitmap = bm.copy(Bitmap.Config.ARGB_8888, true);
    // 遍历原始图像像素,并进行二值化处理
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        // 得到当前的像素值
        int pixel = bitmap.getPixel(i, j);
        // 得到Alpha通道的值
        int alpha = pixel & 0xFF000000;
        // 得到Red的值
        int red = (pixel & 0x00FF0000) >> 16;
        // 得到Green的值
        int green = (pixel & 0x0000FF00) >> 8;
        // 得到Blue的值
        int blue = pixel & 0x000000FF;
        // 通过加权平均算法,计算出最佳像素值
        int gray = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
        // 对图像设置黑白图
        if (gray <= 95) {
          gray = 0;
        } else {
          gray = 255;
        }
        // 得到新的像素值
        int newPixel = alpha | (gray << 16) | (gray << 8) | gray;
        // 赋予新图像的像素
        bitmap.setPixel(i, j, newPixel);
      }
    }
    return bitmap;
  }


  /**
   * 给图片加水印
   * 水印位置根据服务端给的处理
   *
   * @param src       原图
   * @param watermark 水印
   * @return 加水印的原图
   */
  public Bitmap waterMask(Bitmap src, Bitmap watermark, Context context) {
    int w = src.getWidth();
    int h = src.getHeight();
    // 设置原图想要的大小
    float newWidth = DisplayUtil.getDisplayMetric(context).widthPixels;
    float newHeight = h * (newWidth / w);
    // 计算缩放比例
    float scaleWidth = (newWidth) / w;
    float scaleHeight = (newHeight) / h;
    Matrix matrix = new Matrix();
    matrix.postScale(scaleWidth, scaleHeight);
    src = Bitmap.createBitmap(src, 0, 0, w, h, matrix, true);
    //根据bitmap缩放水印图片
    float w1 = w / 5;
    float h1 = w1 / 5;
    //获取原始水印图片的宽、高
    int w2 = watermark.getWidth();
    int h2 = watermark.getHeight();

    //计算缩放的比例
    float scalewidth = w1 / w2;
    float scaleheight = h1 / h2;

    Matrix matrix1 = new Matrix();
    matrix1.postScale((float) 0.2, (float) 0.2);
    watermark = Bitmap.createBitmap(watermark, 0, 0, w2, h2, matrix1, true);
    //获取新的水印图片的宽、高
    w2 = watermark.getWidth();
    h2 = watermark.getHeight();
    // 创建一个新的和SRC长度宽度一样的位图
    Bitmap result = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas cv = new Canvas(result);
    //在canvas上绘制原图和新的水印图
    cv.drawBitmap(src, 0, 0, null);
    //水印图绘制在画布的右下角，距离右边和底部都为20
    //  cv.drawBitmap(watermark, src.getWidth() - w2-20, src.getHeight() - h2-20, null);
    cv.drawBitmap(watermark, newWidth - w2, 0, null);
    cv.save();
    cv.restore();
    return result;
  }

  /**
   * 添加文字水印
   */
  public static Bitmap addWaterMark(Bitmap srcBitmap, String txt) {
    //获取原始图片与水印图片的宽与高
    int mBitmapWidth = srcBitmap.getWidth();
    int mBitmapHeight = srcBitmap.getHeight();
    Bitmap mNewBitmap = Bitmap.createBitmap(mBitmapWidth, mBitmapHeight, Bitmap.Config.ARGB_8888);
    Canvas mCanvas = new Canvas(mNewBitmap);
    //向位图中开始画入MBitmap原始图片
    mCanvas.drawBitmap(srcBitmap, 0, 0, null);
    //添加文字
    Paint mPaint = new Paint();
    mPaint.setColor(Color.RED);
    mPaint.setTextSize(20);
    //水印的位置坐标
    mCanvas.drawText(txt, (float) (mBitmapWidth / 2), (float) ((mBitmapHeight) / 2), mPaint);
    mCanvas.save();
    mCanvas.restore();
    return mNewBitmap;
  }


}
