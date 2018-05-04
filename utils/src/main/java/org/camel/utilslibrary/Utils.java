package org.camel.utilslibrary;

import android.app.Application;
import android.content.Context;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/28
 *    desc   : 用于初始化 Utils-Everywhere
 * </pre>
 */
public class Utils {

    private Utils() {
    }

    /**
     * 初始化工具类
     *
     * @param context Context 对象
     */
    public static void init(Context context) {
        //初始化全局Context类，以后要获取全局Context直接在该工具类进行获取
        ContextUtils.init(context);
        //全局Handler工具类，如不使用可以不进行初始化，减少资源消耗
        HandlerUtils.init(context);
        HandlerUtils.postRunnable(new Runnable() {
            @Override
            public void run() {
                ThreadUtils.init();
            }
        });
        //初始化生命周期工具类
        if (context.getApplicationContext() instanceof Application) {
            ActivityLifecycleUtils.init((Application) context.getApplicationContext());
        }
    }

    /**
     * 初始化 LogUtils, 可手动设置是否打印日志
     * @param isDebug true 表示打印日志, false 不打印
     */
    public static void initLogUtils(boolean isDebug) {
        LogUtils.init(isDebug);
    }
}
