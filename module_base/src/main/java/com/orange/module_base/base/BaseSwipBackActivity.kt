package com.orange.module_base.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.orange.module_base.R
import com.orange.module_base.widget.dialog.LoadingDialog
import com.orange.module_base.widget.swipbacklayout.SwipeBackLayout
import com.orange.module_base.widget.swipbacklayout.app.SwipeBackActivity
import kotlinx.android.synthetic.main.base_titlebar_layout.*
import org.greenrobot.eventbus.EventBus

/**
 * created by czh on 2018/12/25
 */
abstract class BaseSwipBackActivity: SwipeBackActivity(){
    open lateinit var mContext: Context
    var progressDlg: Dialog? = null
    var mEmptyView: View?=null
    var mErrorView: View?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(getContentLayoutId())
        if (isRegistEventBus()){
            EventBus.getDefault().register(this)
        }
        initView()
        initData()
        initOnClick()
    }



    /*** 初始化数据（在完成布局初始化之后） */
    open fun initData() {

    }

    /*** 获取当前Activity的布局ID */
    abstract fun getContentLayoutId(): Int

    /*** 初始化布局 */
    open fun initView() {
    }

    /*** 点击事件 */
    open fun initOnClick() {

    }

    //网络错误等点击调用
    open fun refresh(){

    }

    fun getEmptyView(recyclerView: RecyclerView): View {
        if (mEmptyView==null){
            mEmptyView=layoutInflater.inflate(R.layout.base_empty_view, recyclerView.parent as ViewGroup, false)
            mEmptyView!!.setOnClickListener{
                refresh()
            }
        }
        return mEmptyView!!
    }

    fun getErrorView(recyclerView: RecyclerView): View {
        if (mErrorView==null){
            mErrorView=layoutInflater.inflate(R.layout.base_error_view, recyclerView.parent as ViewGroup, false)
            mErrorView!!.setOnClickListener{
                refresh()
            }
        }
        return mErrorView!!
    }

    private fun initBackBtn(){
        toolbar_layout.setNavigationIcon(R.drawable.ic_back)
        toolbar_layout.setNavigationOnClickListener({ finish()})
    }

    fun initNormalTitleBar(stringId:Int){
        appbar_title.setText(stringId)
        initBackBtn()
    }

    fun initNormalTitleBar(title:String){
        appbar_title.text = title
        initBackBtn()
    }

    fun setRightText(stringId:Int){
        appbar_right_text.visibility= View.VISIBLE
        appbar_right_text.setText(stringId)
    }

    fun setRightText(title:String){
        appbar_right_text.visibility= View.VISIBLE
        appbar_right_text.setText(title)
    }

    fun setRightImg(drawableId:Int){
        appbar_right_btn.visibility= View.VISIBLE
        appbar_right_btn.setImageResource(drawableId)
    }


    open fun showProgress() {
        progressDlg = LoadingDialog(mContext)
        progressDlg!!.show()
    }

    open fun dismissProgress() {
        progressDlg?.dismiss()
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