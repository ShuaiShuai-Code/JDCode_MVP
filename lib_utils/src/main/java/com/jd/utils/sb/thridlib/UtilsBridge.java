package com.jd.utils.sb.thridlib;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;

import androidx.annotation.RequiresPermission;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import static android.Manifest.permission.CALL_PHONE;

/**
 * <pre>
 *     author: blankj
 *     blog  : http://blankj.com
 *     time  : 2020/03/19
 *     desc  :
 * </pre>
 */
class UtilsBridge {

    static void init(Application app) {
        UtilsActivityLifecycleImpl.INSTANCE.init(app);
    }

    static void unInit(Application app) {
        UtilsActivityLifecycleImpl.INSTANCE.unInit(app);
    }

    static void preLoad() {
        preLoad(AdaptScreenUtils.getPreLoadRunnable());
    }

    ///////////////////////////////////////////////////////////////////////////
    // UtilsActivityLifecycleImpl
    ///////////////////////////////////////////////////////////////////////////
    static Activity getTopActivity() {
        return UtilsActivityLifecycleImpl.INSTANCE.getTopActivity();
    }

    static void addOnAppStatusChangedListener(final Utils.OnAppStatusChangedListener listener) {
        UtilsActivityLifecycleImpl.INSTANCE.addOnAppStatusChangedListener(listener);
    }

    static void removeOnAppStatusChangedListener(final Utils.OnAppStatusChangedListener listener) {
        UtilsActivityLifecycleImpl.INSTANCE.removeOnAppStatusChangedListener(listener);
    }

    static void addActivityLifecycleCallbacks(final Activity activity,
                                              final Utils.ActivityLifecycleCallbacks callbacks) {
        UtilsActivityLifecycleImpl.INSTANCE.addActivityLifecycleCallbacks(activity, callbacks);
    }

    static void removeActivityLifecycleCallbacks(final Activity activity) {
        UtilsActivityLifecycleImpl.INSTANCE.removeActivityLifecycleCallbacks(activity);
    }

    static void removeActivityLifecycleCallbacks(final Activity activity,
                                                 final Utils.ActivityLifecycleCallbacks callbacks) {
        UtilsActivityLifecycleImpl.INSTANCE.removeActivityLifecycleCallbacks(activity, callbacks);
    }

    static List<Activity> getActivityList() {
        return UtilsActivityLifecycleImpl.INSTANCE.getActivityList();
    }

    static Application getApplicationByReflect() {
        return UtilsActivityLifecycleImpl.INSTANCE.getApplicationByReflect();
    }

    ///////////////////////////////////////////////////////////////////////////
    // ActivityUtils
    ///////////////////////////////////////////////////////////////////////////
    static boolean isActivityAlive(final Activity activity) {
        return ActivityUtils.isActivityAlive(activity);
    }

    static String getLauncherActivity() {
        return ActivityUtils.getLauncherActivity();
    }

    static String getLauncherActivity(final String pkg) {
        return ActivityUtils.getLauncherActivity(pkg);
    }

    static Activity getActivityByContext(Context context) {
        return ActivityUtils.getActivityByContext(context);
    }

    static void finishAllActivities() {
        ActivityUtils.finishAllActivities();
    }

    /*///////////////////////////////////////////////////////////////////////////
    // AppUtils
    ///////////////////////////////////////////////////////////////////////////
    static Context getTopActivityOrApp() {
        if (AppUtils.isAppForeground()) {
            Activity topActivity = getTopActivity();
            return topActivity == null ? Utils.getApp() : topActivity;
        } else {
            return Utils.getApp();
        }
    }

    static boolean isAppRunning(@NonNull final String pkgName) {
        return AppUtils.isAppRunning(pkgName);
    }

    static boolean isAppInstalled(final String pkgName) {
        return AppUtils.isAppInstalled(pkgName);
    }

    static String getAppVersionName() {
        return AppUtils.getAppVersionName();
    }

    static int getAppVersionCode() {
        return AppUtils.getAppVersionCode();
    }

    static boolean isAppDebug() {
        return AppUtils.isAppDebug();
    }*/

    ///////////////////////////////////////////////////////////////////////////
    // BarUtils
    ///////////////////////////////////////////////////////////////////////////
    static int getStatusBarHeight() {
        return BarUtils.getStatusBarHeight();
    }

    static int getNavBarHeight() {
        return BarUtils.getNavBarHeight();
    }


    ///////////////////////////////////////////////////////////////////////////
    // EncodeUtils
    ///////////////////////////////////////////////////////////////////////////
    static byte[] base64Encode(final byte[] input) {
        return EncodeUtils.base64Encode(input);
    }

    static byte[] base64Decode(final byte[] input) {
        return EncodeUtils.base64Decode(input);
    }

    ///////////////////////////////////////////////////////////////////////////
    // FileIOUtils
    ///////////////////////////////////////////////////////////////////////////
    static boolean writeFileFromBytes(final File file,
                                      final byte[] bytes) {
        return FileIOUtils.writeFileFromBytesByChannel(file, bytes, true);
    }

    static byte[] readFile2Bytes(final File file) {
        return FileIOUtils.readFile2BytesByChannel(file);
    }

    static boolean writeFileFromString(final String filePath, final String content, final boolean append) {
        return FileIOUtils.writeFileFromString(filePath, content, append);
    }

    static boolean writeFileFromIS(final String filePath, final InputStream is) {
        return FileIOUtils.writeFileFromIS(filePath, is);
    }

    ///////////////////////////////////////////////////////////////////////////
    // FileUtils
    ///////////////////////////////////////////////////////////////////////////
    static boolean isFileExists(final File file) {
        return FileUtils.isFileExists(file);
    }

    static File getFileByPath(final String filePath) {
        return FileUtils.getFileByPath(filePath);
    }

    static boolean deleteAllInDir(final File dir) {
        return FileUtils.deleteAllInDir(dir);
    }

    static boolean createOrExistsFile(final File file) {
        return FileUtils.createOrExistsFile(file);
    }

    static boolean createOrExistsDir(final File file) {
        return FileUtils.createOrExistsDir(file);
    }

    static boolean createFileByDeleteOldFile(final File file) {
        return FileUtils.createFileByDeleteOldFile(file);
    }

    static long getFsTotalSize(String path) {
        return FileUtils.getFsTotalSize(path);
    }

    static long getFsAvailableSize(String path) {
        return FileUtils.getFsAvailableSize(path);
    }

    ///////////////////////////////////////////////////////////////////////////
    // ImageUtils
    ///////////////////////////////////////////////////////////////////////////
    static byte[] bitmap2Bytes(final Bitmap bitmap) {
        return ImageUtils.bitmap2Bytes(bitmap);
    }

    static byte[] bitmap2Bytes(final Bitmap bitmap, final Bitmap.CompressFormat format, int quality) {
        return ImageUtils.bitmap2Bytes(bitmap, format, quality);
    }

    static Bitmap bytes2Bitmap(final byte[] bytes) {
        return ImageUtils.bytes2Bitmap(bytes);
    }

    static byte[] drawable2Bytes(final Drawable drawable) {
        return ImageUtils.drawable2Bytes(drawable);
    }

    static byte[] drawable2Bytes(final Drawable drawable, final Bitmap.CompressFormat format, int quality) {
        return ImageUtils.drawable2Bytes(drawable, format, quality);
    }

    static Drawable bytes2Drawable(final byte[] bytes) {
        return ImageUtils.bytes2Drawable(bytes);
    }

    static Bitmap view2Bitmap(final View view) {
        return ImageUtils.view2Bitmap(view);
    }

    static Bitmap drawable2Bitmap(final Drawable drawable) {
        return ImageUtils.drawable2Bitmap(drawable);
    }

    static Drawable bitmap2Drawable(final Bitmap bitmap) {
        return ImageUtils.bitmap2Drawable(bitmap);
    }

    ///////////////////////////////////////////////////////////////////////////
    // IntentUtils
    ///////////////////////////////////////////////////////////////////////////
    static boolean isIntentAvailable(final Intent intent) {
        return IntentUtils.isIntentAvailable(intent);
    }

    static Intent getLaunchAppIntent(final String pkgName) {
        return IntentUtils.getLaunchAppIntent(pkgName);
    }

    static Intent getInstallAppIntent(final File file) {
        return IntentUtils.getInstallAppIntent(file);
    }

    static Intent getUninstallAppIntent(final String pkgName) {
        return IntentUtils.getUninstallAppIntent(pkgName);
    }

    static Intent getDialIntent(final String phoneNumber) {
        return IntentUtils.getDialIntent(phoneNumber);
    }

    @RequiresPermission(CALL_PHONE)
    static Intent getCallIntent(final String phoneNumber) {
        return IntentUtils.getCallIntent(phoneNumber);
    }

    static Intent getSendSmsIntent(final String phoneNumber, final String content) {
        return IntentUtils.getSendSmsIntent(phoneNumber, content);
    }


    ///////////////////////////////////////////////////////////////////////////
    // KeyboardUtils
    ///////////////////////////////////////////////////////////////////////////
    static void fixSoftInputLeaks(final Activity activity) {
        KeyboardUtils.fixSoftInputLeaks(activity);
    }

    ///////////////////////////////////////////////////////////////////////////
    // ProcessUtils
    ///////////////////////////////////////////////////////////////////////////
    static boolean isMainProcess() {
        return ProcessUtils.isMainProcess();
    }

    static String getForegroundProcessName() {
        return ProcessUtils.getForegroundProcessName();
    }

    static String getCurrentProcessName() {
        return ProcessUtils.getCurrentProcessName();
    }

    ///////////////////////////////////////////////////////////////////////////
    // SDCardUtils
    ///////////////////////////////////////////////////////////////////////////
    static String getSDCardPathByEnvironment() {
        return SDCardUtils.getSDCardPathByEnvironment();
    }

    static boolean isSDCardEnableByEnvironment() {
        return SDCardUtils.isSDCardEnableByEnvironment();
    }

    ///////////////////////////////////////////////////////////////////////////
    // ServiceUtils
    ///////////////////////////////////////////////////////////////////////////
    static boolean isServiceRunning(final String className) {
        return ServiceUtils.isServiceRunning(className);
    }

    ///////////////////////////////////////////////////////////////////////////
    // ShellUtils
    ///////////////////////////////////////////////////////////////////////////
    static ShellUtils.CommandResult execCmd(final String command, final boolean isRooted) {
        return ShellUtils.execCmd(command, isRooted);
    }

    ///////////////////////////////////////////////////////////////////////////
    // SizeUtils
    ///////////////////////////////////////////////////////////////////////////
    static int dp2px(final float dpValue) {
        return SizeUtils.dp2px(dpValue);
    }

    static int px2dp(final float pxValue) {
        return SizeUtils.px2dp(pxValue);
    }

    static int sp2px(final float spValue) {
        return SizeUtils.sp2px(spValue);
    }

    static int px2sp(final float pxValue) {
        return SizeUtils.px2sp(pxValue);
    }


    ///////////////////////////////////////////////////////////////////////////
    // StringUtils
    ///////////////////////////////////////////////////////////////////////////
    static boolean isSpace(final String s) {
        return StringUtils.isSpace(s);
    }

    static boolean equals(final CharSequence s1, final CharSequence s2) {
        return StringUtils.equals(s1, s2);
    }


    ///////////////////////////////////////////////////////////////////////////
    // ThreadUtils
    ///////////////////////////////////////////////////////////////////////////
    static <T> Utils.Task<T> doAsync(final Utils.Task<T> task) {
        ThreadUtils.getCachedPool().execute(task);
        return task;
    }

    static void runOnUiThread(final Runnable runnable) {
        ThreadUtils.runOnUiThread(runnable);
    }

    static void runOnUiThreadDelayed(final Runnable runnable, long delayMillis) {
        ThreadUtils.runOnUiThreadDelayed(runnable, delayMillis);
    }

    ///////////////////////////////////////////////////////////////////////////
    // ThrowableUtils
    ///////////////////////////////////////////////////////////////////////////
    static String getFullStackTrace(Throwable throwable) {
        return ThrowableUtils.getFullStackTrace(throwable);
    }



    private static void preLoad(final Runnable... runs) {
        for (final Runnable r : runs) {
            ThreadUtils.getCachedPool().execute(r);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // UriUtils
    ///////////////////////////////////////////////////////////////////////////
    static Uri file2Uri(final File file) {
        return UriUtils.file2Uri(file);
    }
}
