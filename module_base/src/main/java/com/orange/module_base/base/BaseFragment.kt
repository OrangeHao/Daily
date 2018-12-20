package com.orange.module_base.base

import android.app.Dialog
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orange.module_base.R
import com.orange.module_base.widget.dialog.LoadingDialog
import org.greenrobot.eventbus.EventBus

/**
 * @author OrangeHao
 * @date 2018/12/12
 * @Github https://github.com/OrangeHao
 * @describe
 */
abstract class BaseFragment : Fragment(){
    var progressDlg: Dialog? = null
    var mEmptyView:View?=null
    var mErrorView:View?=null

    abstract fun getLayoutId(): Int


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isRegistEventBus()){
            EventBus.getDefault().register(this)
        }
        initView()
        initData()
        initOnClick()
    }

    /**初始化参数*/
    open fun initData() {
    }

    open fun initOnClick() {
    }

    /** 初始化布局*/
    open fun initView() {
    }

    //网络错误等点击调用
    open fun refresh(){

    }

    fun getEmptyView(recyclerView: RecyclerView):View{
        if (mEmptyView==null){
            mEmptyView=layoutInflater.inflate(R.layout.base_empty_view, recyclerView.parent as ViewGroup, false)
            mEmptyView!!.setOnClickListener{
                refresh()
            }
        }
        return mEmptyView!!
    }

    fun getErrorView(recyclerView: RecyclerView):View{
        if (mErrorView==null){
            mErrorView=layoutInflater.inflate(R.layout.base_error_view, recyclerView.parent as ViewGroup, false)
            mErrorView!!.setOnClickListener{
                refresh()
            }
        }
        return mErrorView!!
    }

    open fun showProgress() {
        if (progressDlg==null){
            progressDlg = LoadingDialog(context!!)
        }
        progressDlg!!.show()
    }

    open fun dismissProgress() {
        progressDlg?.dismiss()
    }


    fun replaceChildFragment(@IdRes id: Int, fragment: Fragment) {
        childFragmentManager.beginTransaction().replace(id, fragment).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRegistEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }

    protected fun isRegistEventBus(): Boolean {
        return false
    }
}