package com.orange.module_collector.file

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import java.io.*


/**
 * created by czh on 2019/7/8
 */
class FileHelper{

    companion object{

        fun getFilePathByName(file: File,name:String):String{
            var result=""
            if (file.exists()){
                var files=file.listFiles()
                if (files.size==0){
                    return result
                }else{
                    for (temp in files){
                        Log.d("czh","file name:"+temp.name)
                        if (temp.isDirectory) {
                            result= getFilePathByName(temp, name)
                            if (!TextUtils.isEmpty(result)){
                                return result
                            }
                        } else{
                            if (temp.name.equals(name)){
                                result=temp.absolutePath
                                return result
                            }
                        }
                    }
                }
            }
            return result
        }

        fun copyFile(oldPath: String, newPath:String): Boolean {
            val oldFile = File(oldPath)
            if (!oldFile.exists()) {
                Log.e("czh", "copyFile:  oldFile not exist.")
                return false
            } else if (!oldFile.isFile) {
                Log.e("czh", "copyFile:  oldFile not file.")
                return false
            } else if (!oldFile.canRead()) {
                Log.e("czh", "copyFile:  oldFile cannot read.")
                return false
            }else if (!FileHelper.checkSpaceForFile(oldFile.length())){
                Log.e("czh", "copyFile:  do not have enough space.")
                return false
            }

            var newFile=File(newPath)
            if (newFile.exists()){
                newFile.delete()
            }

            val fileInputStream = FileInputStream(oldFile)
            val fileOutputStream = FileOutputStream(newFile)

//            fileInputStream.buffered(1024).use { input->
//                fileOutputStream.use { fileOut->
//                    input.copyTo(fileOut)
//                }
//            }
//            return true
            val totalSize=oldFile.length()
            try {
                val buffer = ByteArray(1024)
                var byteRead=0
                var totalByteRead=0
                while ({byteRead=fileInputStream.read(buffer);byteRead}()>0) {
                    fileOutputStream.write(buffer, 0, byteRead)
                    totalByteRead+=byteRead
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }finally {
                fileInputStream.close()
                fileOutputStream.flush()
                fileOutputStream.close()
                return true
            }
        }

        fun checkSpaceForFile(fileSize:Long):Boolean{
            val totalSpace=Environment.getExternalStorageDirectory().usableSpace
            return totalSpace>fileSize
        }


        fun delFolder(folderPath: String) {
            try {
                delAllFileUnderFolder(folderPath) //删除完里面所有内容
                var filePath = folderPath
                filePath = filePath
                val myFilePath =File(filePath)
                myFilePath.delete() //删除空文件夹
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        //删除指定文件夹下所有文件
        //param path 文件夹完整绝对路径
        fun delAllFileUnderFolder(path: String): Boolean {
            var flag = false
            val file = File(path)
            if (!file.exists()) {
                return flag
            }
            if (!file.isDirectory) {
                return flag
            }
            val tempList = file.list()
            if (tempList==null){
                return flag
            }
            var temp: File? = null
            for (i in tempList.indices) {
                if (path.endsWith(File.separator)) {
                    temp = File(path + tempList[i])
                } else {
                    temp = File(path + File.separator + tempList[i])
                }
                if (temp.isFile) {
                    temp.delete()
                }
                if (temp.isDirectory) {
                    delAllFileUnderFolder(path + "/" + tempList[i])//先删除文件夹里面的文件
//                    delFolder(path + "/" + tempList[i])//再删除空文件夹
                    temp.delete()
                    flag = true
                }
            }
            return flag
        }




        fun copyFileFromUri(context: Context,uri:Uri, newPath:String): Boolean {
            var newFile=File(newPath)
            if (newFile.exists()){
                newFile.delete()
            }

            val fileInputStream = context.contentResolver.openInputStream(uri)
            val fileOutputStream = FileOutputStream(newFile)

            try {
                val buffer = ByteArray(1024)
                var byteRead=0
                var totalByteRead=0
                while ({byteRead=fileInputStream.read(buffer);byteRead}()>0) {
                    fileOutputStream.write(buffer, 0, byteRead)
                    totalByteRead+=byteRead
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("czh",e.toString())
                return false
            }finally {
                fileInputStream.close()
                fileOutputStream.flush()
                fileOutputStream.close()
                return true
            }
        }





    }


}