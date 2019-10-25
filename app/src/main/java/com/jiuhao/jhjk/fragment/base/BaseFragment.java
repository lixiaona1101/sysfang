package com.jiuhao.jhjk.fragment.base;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.core.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.utils.StatusBarUtils;


public abstract class BaseFragment extends Fragment implements IBaseFragment {

    private FrameLayout frameLayout;
    //根布局视图
    protected View mContentView;

    //视图是否已经初始化完毕
    protected boolean isViewReady = false;
    //是否第一次
    protected boolean isFirstLoad = true;
    // fragment是否处于可见状态
    public boolean isFragmentVisible;
    //是否已经加载过
    protected boolean isLoaded = false;
    private Button button;

    protected boolean isResume = false;

    protected int mWidth;
    protected int mHeight;

    protected FragmentManager fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_base, container, false);
            View view = inflater.inflate(getLayoutId(), null);
            frameLayout = (FrameLayout) mContentView.findViewById(R.id.f_base_content);
            frameLayout.addView(view);

        }

        fm = getFragmentManager();
        WindowManager manager = getActivity().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        mWidth = outMetrics.widthPixels;
        mHeight = outMetrics.heightPixels;
        return mContentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        //视图准备完毕
        isViewReady = true;

        onFragmentVisiable();
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return mContentView.findViewById(id);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isFragmentVisible = !hidden;
    }

    //懒加载
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isFragmentVisible = isVisibleToUser;
    }

    //设置并返回布局ID
    protected abstract int getLayoutId();

    //做视图相关的初始化
    protected abstract void initView();

    //来做数据的初始化
    protected abstract void initData();

    //做事件监听的初始化
    protected abstract void setListener();

    private void onFragmentVisiable() {
        if (!isLoaded) {
            isLoaded = true;
            initView();
            initData();
            setListener();
        }
    }


    /**
     * 这个函数用于移除根视图
     * 因为已经有过父布局的View是不能再次添加到另一个新的父布局上面的
     */
    @Override
    public void onDestroyView() {
        if (mContentView != null) {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        isViewReady = false;

        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        isResume = true;
    }

    public void onPause() {
        super.onPause();
        isResume = false;
    }


    @Override
    public int getResourceColor(@ColorRes int colorId) {
        return isAdded() ? ResourcesCompat.getColor(getResources(), colorId, null) : 0;
    }

    @Override
    public String getResourceString(@StringRes int stringId) {
        return isAdded() ? getResources().getString(stringId) : null;
    }

    @Override
    public Drawable getResourceDrawable(@DrawableRes int id) {
        return isAdded() ? ResourcesCompat.getDrawable(getResources(), id, null) : null;
    }

    /**
     * 进度对话框
     */
    private ProgressDialog progressDialog;

    protected void showProgress(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
        }
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(message);
        progressDialog.setOnKeyListener((dialog, keyCode, event) -> {
            if (progressDialog != null && progressDialog.isShowing()
                    && keyCode == KeyEvent.KEYCODE_BACK) {
                return true;
            }
            return false;
        });

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog.show();
    }

    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    /**
     * 将一个布局延长状态栏的高度
     */

    protected void setStatusBar(@IdRes int id) {
        if (mContentView != null) {
            final View linear_bar = mContentView.findViewById(id);
            final int statusHeight = StatusBarUtils.getStatusHeight(getContext());
            linear_bar.post(() -> {
                ViewGroup.LayoutParams params = linear_bar.getLayoutParams();
                params.height = statusHeight;
                linear_bar.setLayoutParams(params);
            });
        }
    }
}
