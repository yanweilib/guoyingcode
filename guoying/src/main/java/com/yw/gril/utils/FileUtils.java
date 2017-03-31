package com.yw.gril.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * <code>FileUtils</code>
 * 文件工具类
 * @author YANWEI
 * @version 1.0.0
 * @see java.lang.Class
 * @since 2017/3/31 18:21
 */
public class FileUtils {

    public static String separator = File.separator;

    public static String appName="";//App名字。用于创建app缓存文件夹

    /**
     * 获取用户文件
     * @param username
     * @return
     */
    public static File getUserDir(String username) {
        String dir = getSDCardPath() + separator + appName + separator + username;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 获取用户文件路径
     * @param username
     * @return
     */
    public static String getUserDirPath(String username) {
        return getUserDir(username).getPath();
    }

    /**
     * 创建文件夹
     */
    public static boolean createFile(String name) {
        if (isSDCardEnable()) {
            File file = new File(name);
            if (!file.exists()) {
                return file.mkdirs();
            } else if (file.exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断SDCard是否可用
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }


    /**
     * 获取SDCard根目录
     */
    public static File getSDCardDir() {
        if (isSDCardEnable()) {
            return Environment.getExternalStorageDirectory();
        } else {
            return null;
        }
    }

    /**
     * 获取SD卡路径
     * @return
     */
    public static String getSDCardPath() {
        if (isSDCardEnable()) {
          return   Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator;
        }
        else
        {
            return "";
        }
    }

    /**
     * 获取SD卡的剩余容量 单位byte
     * @return
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 获取系统存储路径
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }
}
