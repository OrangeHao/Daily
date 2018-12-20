package com.orange.module_base.widget.dialog

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager

import com.orange.module_base.R


/**
 * @author OrangeHao
 * @date 2018/12/12
 * @Github https://github.com/OrangeHao
 * @describe
 */
class LoadingDialog(context: Context) : ProgressDialog(context, R.style.module_base_dialog_loading) {

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        //        setCancelable(false);
        //        setCanceledOnTouchOutside(false);

        setContentView(R.layout.dialog_loading)
        val params = window!!.attributes
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = params
    }
}
