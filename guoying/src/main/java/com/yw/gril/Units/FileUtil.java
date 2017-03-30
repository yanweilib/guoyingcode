package com.example.androidcode.Units;

import android.os.Environment;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

    public static String separator = File.separator;

    public static File getUserDir(String username){
        String dir = getRootDir().getAbsolutePath()+separator+"renrensai"+separator+username;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 获取SDCard根目录
     */
    public static File getRootDir() {
        if (SDCardExist()) {
            return Environment.getExternalStorageDirectory();
        } else {
            return null;
        }
    }

    /**
     * 判断sdcard是否存在
     */
    public static boolean SDCardExist() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 创建文件夹
     */
    public static boolean createFile(String name) {
        if (SDCardExist()) {
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
     * 保存图片到图片文件夹
     */
    public static boolean saveFile(String username, String filename, byte[] data) throws Exception {
        if (SDCardExist() && filename != null && data != null && data.length > 0) {
            ByteArrayInputStream inStream = new ByteArrayInputStream(data);
            String path = getUserDir(username).getPath()+separator+"picture";
            if (createFile(path)) {
                File file = new File(path, filename + ".jpg");
                FileOutputStream outStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inStream.read(buffer)) > -1) {
                    outStream.write(buffer, 0, len);
                }
                inStream.close();
                outStream.close();
            } else {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}