package com.sfj.sfj.base;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSON;
import com.sfj.sfj.R;
import com.sfj.sfj.bean.ApiBean;
import com.sfj.sfj.net.CloudSDKHttpHandler;
import com.sfj.sfj.net.ICloudSDKHttpHandler;
import com.sfj.sfj.utils.ToastUtils;
import com.sfj.sfj.widget.EmptyLayout;


/**
 * Created by wangyu on 2018/1/9.
 */

public abstract class BaseDetailFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    protected SwipeRefreshLayout mSwipeRefreshLayout;
    public FrameLayout mRootView;


    protected ICloudSDKHttpHandler mHttpHandler = new ICloudSDKHttpHandler() {
        @Override
        public void onSuccess(int statusCode, String json) {
            executeParserTask(json);
        }

        @Override
        public void onFailure(int statusCode, String responseBody, Throwable error) {
            executeOnLoadDataFail(responseBody);
        }
    };
    private ParserTask mParserTask;
    protected EmptyLayout mEmptyLayout;

    private void executeParserTask(String data) {
        cancelParserTask();
        mParserTask = new ParserTask();
        mParserTask.execute(data);
    }

    private void cancelParserTask() {
        if (mParserTask != null) {
            mParserTask.cancel(true);
            mParserTask = null;
        }
    }

    private class ParserTask extends AsyncTask<String, Void, T> {

        private String errorMessage;


        @Override
        protected T doInBackground(String... params) {
            T item = null;
            try {

                ApiBean mApiBean = JSON.parseObject(params[0], ApiBean.class);
                String sateCode = mApiBean.getCode();
                if (ApiBean.isCheckOk(sateCode)) {
                    item = parseItem(mApiBean.getData());
                } else {
                    errorMessage = mApiBean.getMsg();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return item;
        }

        @Override
        protected void onPostExecute(T item) {
            super.onPostExecute(item);
            if (errorMessage != null||item==null) {
                executeOnLoadDataFail(errorMessage);
            } else {
                mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
                mSwipeRefreshLayout.setRefreshing(false);
                executeOnLoadDataSuccess(item);
            }
        }
    }

    private void executeOnLoadDataFail(String errorMessage) {
        mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        mSwipeRefreshLayout.setRefreshing(false);
        if (!TextUtils.isEmpty(errorMessage)){
            ToastUtils.showShortToast(errorMessage);
        }else{
            ToastUtils.showLongToast(R.string.error_view_network);
        }
    }

    protected void executeOnLoadDataSuccess(T item) {

    }

    protected T parseItem(String mjson) {
        return null;
    }

    protected CloudSDKHttpHandler mHandler = new CloudSDKHttpHandler(mHttpHandler);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_detail;
    }

    protected abstract int getDetailLayoutId();

    @Override
    protected void initView(View view) {
        super.initView(view);
        mRootView = (FrameLayout) view.findViewById(R.id.fl_container);

        mEmptyLayout = (EmptyLayout) view.findViewById(R.id.empty_layout);

        LayoutInflater.from(_mActivity).inflate(getDetailLayoutId(), mRootView);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        mEmptyLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestData();
                mSwipeRefreshLayout.setRefreshing(true);
                mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
            }
        });
    }

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
        sendRequestData();
    }

    protected void sendRequestData() {

    }

    @Override
    public void onRefresh() {
        sendRequestData();
    }
}
