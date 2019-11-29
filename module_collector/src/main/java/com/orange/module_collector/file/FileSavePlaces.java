package com.orange.module_collector.file;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

/**
 * created by czh on 2019/11/29
 */
public class FileSavePlaces {


    private static final String Root_Name="collections";


    private static String getFileRootPath(Context context){
        String[] paths = new String[0];
        StorageManager sm = (StorageManager)context.getSystemService(Context.STORAGE_SERVICE);
        try {
            paths = (String[]) sm.getClass().getMethod("getVolumePaths", null).invoke(sm, null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        String innerPath= Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.d("czh","innerPath:"+innerPath);
        for (int i = 0; i < paths.length; i++) {
            Log.d("czh",paths[i]);
            if (!innerPath.equals(paths[i])){
                return paths[i];
            }
        }
        return innerPath;
    }


    /**
     * mkdir of path
     * @param path file path
     * @return dir path
     */
    private static String getFilePathAndMkdir(String path){
        File file=new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * get root path where the app data would  place
     * @param context context
     * @return root path
     */
    public static String getRootPath(Context context){
        return getFilePathAndMkdir(getFileRootPath(context)+File.separator+Root_Name);
    }


    /**
     * create new folder
     * @param context
     * @param name
     * @return
     */
    public static String createNewCollection(Context context,String name){
        return getFilePathAndMkdir(getRootPath(context)+File.separator+name);
    }








}
