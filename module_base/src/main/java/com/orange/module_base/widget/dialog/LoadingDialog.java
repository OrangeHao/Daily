package com.orange.module_base.widget.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.orange.module_base.R;


/**
 * @author OrangeHao
 * @date 2018/12/12
 * @Github https://github.com/OrangeHao
 * @describe
 */
public class LoadingDialog extends ProgressDialog {
    public LoadingDialog(Context context) {
        super(context, R.style.dialog_loading);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    private void init(){
//        setCancelable(false);
//        setCanceledOnTouchOutside(false);

        setContentView(R.layout.dialog_loading);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }
}
