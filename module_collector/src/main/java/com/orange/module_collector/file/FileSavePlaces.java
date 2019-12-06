package com.orange.module_collector.file;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;

import com.orange.module_collector.beans.FolderBean;
import com.orange.module_collector.beans.MediaBean;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * created by czh on 2019/11/29
 */
public class FileSavePlaces {


    private static final String Root_Name = "Collections";


    private static String getExternalPath(Context context) {
        String[] paths = new String[0];
        StorageManager sm = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        try {
            paths = (String[]) sm.getClass().getMethod("getVolumePaths", null).invoke(sm, null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        String innerPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.d("czh", "innerPath:" + innerPath);
        for (int i = 0; i < paths.length; i++) {
            if (!innerPath.equals(paths[i])) {
//                return paths[i];
            }
        }
        return innerPath;
    }


    /**
     * mkdir of path
     *
     * @param path file path
     * @return dir path
     */
    private static String getFilePathAndMkdir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            boolean result = file.mkdirs();
            Log.d("czh", "create folder:" + path + " " + result);
        }
        return file.getAbsolutePath();
    }

    /**
     * get root path where the app data would  place
     *
     * @param context context
     * @return root path
     */
    public static String getRootPath(Context context) {
        File file = new File(getExternalPath(context), Root_Name);
        if (!file.exists()) {
            boolean result = file.mkdir();
            Log.d("czh", "create folder:" + file.getAbsolutePath() + " " + result);
        }
        return file.getAbsolutePath();
    }


    /**
     * create new folder
     *
     * @param context
     * @param name
     * @return
     */
    public static String createNewCollection(Context context, String name) {
        return getFilePathAndMkdir(getRootPath(context) + File.separator + name);
    }


    /**
     * get collections in root folder
     *
     * @param context
     * @return
     */
    public static List<FolderBean> getFolderList(Context context) {
        File rootFolder = new File(getRootPath(context));
        List<FolderBean> result = new ArrayList<>();
        File[] list = rootFolder.listFiles();
        if (list != null) {
            for (File item : list) {
                if (item.isDirectory()) {
                    Log.d("czh", "get path:" + item.getAbsolutePath());
                    result.add(new FolderBean(item.getName(), item.getAbsolutePath(), item.lastModified()));
                }
            }
        }
        return result;
    }


    public static List<MediaBean> getAllMediaData(Context context) {
        return getSortedPicturesInFolder(getRootPath(context));
    }


    /**
     * get pictures in folder and sort them
     * @param path
     * @return
     */
    public static List<MediaBean> getSortedPicturesInFolder(String path) {
        List<MediaBean> result = getPicturesInFolder(path);
        Collections.sort(result, new Comparator<MediaBean>() {
            @Override
            public int compare(MediaBean o1, MediaBean o2) {
                return (int)(o2.getCreateTime()-o1.getCreateTime());
            }
        });
        for (MediaBean bean : result) {
            Log.d("czh", "get sorted pic:" + bean.getName());
        }
        return result;
    }


    public static List<MediaBean> getPicturesInFolder(String path) {
        List<MediaBean> result = new ArrayList<>();
        File rootFolder = new File(path);
        if (!rootFolder.exists()) {
            return result;
        }
        File[] list = rootFolder.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.isDirectory()) {
                    result.addAll(getPicturesInFolder(file.getAbsolutePath()));
                } else {
                    result.add(new MediaBean(file.getName(), file.getAbsolutePath(), Long.valueOf(file.getName().split("\\.")[0])));
                }
            }
        }
        return result;
    }


}
