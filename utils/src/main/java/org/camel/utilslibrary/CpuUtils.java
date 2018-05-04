package org.camel.utilslibrary;

import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/03/26
 *    desc   : CPU 相关工具类, 目前用于适配 cpu 结构
 * </pre>
 */
public class CpuUtils {

    public static String getCpuAbi() {
        String cpuAbi = Build.CPU_ABI;
        if (cpuAbi == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                String[] abis = Build.SUPPORTED_ABIS;
                if (abis != null) {
                    cpuAbi = abis[0];
                }
            }
        }
        return cpuAbi;
    }

    public static boolean matchAbi(@NonNull String... abis) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP && Build.SUPPORTED_ABIS != null) {
            for (String supportedAbi : Build.SUPPORTED_ABIS)
                for (String abi : abis)
                    if (supportedAbi.equals(abi)) return true;
        } else {
            for (String abi : abis)
                if (abi != null && (abi.equals(Build.CPU_ABI) || abi.equals(Build.CPU_ABI2))) return true;
        }
        return false;
    }

    public static String getCPUSerial() {
        String str = "", strCPU = "", cpuAddress = "0000000000000000";
        try {
            //读取CPU信息
            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            //查找CPU序列号
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    //查找到序列号所在行
                    if (str.indexOf("Serial") > -1) {
                        //提取序列号
                        strCPU = str.substring(str.indexOf(":") + 1,
                                str.length());
                        //去空格
                        cpuAddress = strCPU.trim();
                        break;
                    }
                } else {
                    //文件结尾
                    break;
                }
            }
        } catch (Exception ex) {
            //赋予默认值
            Log.e("CpuUtils","获取CPU序列号失败：  原因：" + ex.getMessage());
            ex.printStackTrace();
        }
        return cpuAddress;
    }
}
