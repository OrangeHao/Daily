package com.orange.module_base.utils

import android.Manifest
import android.annotation.SuppressLint
import com.orange.module_base.base.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions


@SuppressLint("CheckResult")
private fun RxPermissions.checkPermission(listener: (granted: Boolean) -> Unit, vararg permissions: String) {
   request(*permissions)
      .subscribe {
         listener.invoke(it)
      }
}

/**
 * 获取拨打电话权限
 */
fun BaseActivity.checkPermissionCallPhone(listener: (granted: Boolean) -> Unit) {
   RxPermissions(this).checkPermission(listener, Manifest.permission.CALL_PHONE)
}


/**
 * 获取拍照权限
 */
fun BaseActivity.checkPermissionCamera(listener: (granted: Boolean) -> Unit) {
   RxPermissions(this).checkPermission(listener, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
}

/**
 * 获取IMEI
 */
fun BaseActivity.checkPermissionPhoneState(listener: (granted: Boolean) -> Unit) {
   RxPermissions(this).checkPermission(listener, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE)
}


/**
 * 获取IMEI
 */
fun BaseActivity.checkPermissionWriteExteralStorage(listener: (granted: Boolean) -> Unit) {
   RxPermissions(this).checkPermission(listener, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
}