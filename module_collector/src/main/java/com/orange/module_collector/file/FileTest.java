package com.orange.module_collector.file;

import android.content.Context;
import android.os.storage.StorageManager;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

/**
 * created by czh on 2019/11/29
 */
public class FileTest {

    public static  String[] getStoragePaths(Context context){

        String[] paths = new String[0];
        StorageManager sm = (StorageManager)context.getSystemService(Context.STORAGE_SERVICE);
        try {
            paths = (String[]) sm.getClass().getMethod("getVolumePaths", null).invoke(sm, null);
        } catch (IllegalAccessException e) {
            Log.d("czh",e.toString());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Log.d("czh",e.toString());
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            Log.d("czh",e.toString());
            e.printStackTrace();
        }
        for (int i = 0; i < paths.length; i++) {
            Log.d("czh",paths[i]);
            String path=paths[i];
            if (!path.contains("emulated")){
                File root=new File(path);
                File[] files=root.listFiles();
                for (File temp:files){
                    Log.d("czh",temp.getAbsolutePath());
                }
            }
        }
        return paths;
    }
}
