package com.sfj.sfj.base;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sfj.sfj.R;
import com.sfj.sfj.bean.ApiBean;
import com.sfj.sfj.bean.ListEntity;
import com.sfj.sfj.net.CloudSDKHttpHandler;
import com.sfj.sfj.net.ICloudSDKHttpHandler;
import com.sfj.sfj.utils.ToastUtils;
import com.sfj.sfj.widget.EmptyLayout;

import java.util.List;

/**
 * Created by wangyu on 2018/1/9.
 */

public abstract class BaseListFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected BaseListAdapter<T> mListAdapter;
    protected static int CURR_PAGE = 0;
    protected static int PAGE_SIZE = 10;

    protected ICloudSDKHttpHandler mHttpHandler = new ICloudSDKHttpHandler() {
        @Override
        public void onSuccess(int statusCode, String mjson) {
            executeParserTask(mjson);
        }

        @Override
        public void onFailure(int statusCode, String responseBody, Throwable error) {
            executeOnLoadDataFail(responseBody);
        }
    };
    private ParserTask mParserTask;
    public EmptyLayout mEmptyLayout;


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

    private class ParserTask extends AsyncTask<String, Void, ListEntity<T>> {

        private String errorMessage;


        @Override
        protected ListEntity<T> doInBackground(String... params) {
            ListEntity<T> data = null;
            try {

                ApiBean mApiBean = JSON.parseObject(params[0], ApiBean.class);
                String sateCode = mApiBean.getCode();
                if (ApiBean.isCheckOk(sateCode)) {
                    data = parseList(mApiBean.getData());
                } else {
                    errorMessage = mApiBean.getMsg();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(ListEntity<T> data) {
            super.onPostExecute(data);
            if (errorMessage != null||data==null) {
                executeOnLoadDataFail(errorMessage);
            } else {
                executeOnLoadDataSuccess(data.getList());
                onExecuteOnLoadDataSuccess(data);
            }
        }
    }

    protected void onExecuteOnLoadDataSuccess(ListEntity<T> data) {

    }

    protected abstract ListEntity<T> parseList(String data);


    protected void executeOnLoadDataFail(String errorMessage) {
        if (isRefresh) {
            mListAdapter.setEnableLoadMore(true);
            mSwipeRefreshLayout.setRefreshing(false);
            mEmptyLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
        } else {
            mListAdapter.loadMoreFail();
            mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            ToastUtils.showShortToast(errorMessage);
        }

    }

    private boolean isRefresh = true;

    public void executeOnLoadDataSuccess(List<T> list) {
        if (isRefresh) {
            setData(true, list);
            mListAdapter.setEnableLoadMore(true);
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            setData(false, list);
        }
    }

    protected void setData(boolean isRefresh, List data) {
        CURR_PAGE++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            if (size == 0) {
                mEmptyLayout.setErrorType(EmptyLayout.NODATA);
            }
            mListAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mListAdapter.addData(data);
            }
        }
        mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mListAdapter.loadMoreEnd(isRefresh);
        } else {
            mListAdapter.loadMoreComplete();
        }
    }

    protected CloudSDKHttpHandler mHandler = new CloudSDKHttpHandler(mHttpHandler);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_list;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mEmptyLayout = (EmptyLayout) view.findViewById(R.id.empty_layout);
        mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        if (mListAdapter != null) {
            mRecyclerView.setAdapter(mListAdapter);
        } else {
            mListAdapter = getListAdapter();
            mListAdapter.setOnLoadMoreListener(this, mRecyclerView);
            mListAdapter.disableLoadMoreIfNotFullPage();
            mRecyclerView.setAdapter(mListAdapter);
        }
        mEmptyLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestData();
                mSwipeRefreshLayout.setRefreshing(true);
                mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
            }
        });

    }

    protected abstract BaseListAdapter<T> getListAdapter();

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
        sendRequestData();
    }

    protected void sendRequestData() {

    }


    @Override
    public void onRefresh() {
        isRefresh = true;
        CURR_PAGE = 0;
        mListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        sendRequestData();
    }

    @Override
    public void onLoadMoreRequested() {
        isRefresh = false;
        sendRequestData();
    }


    public class BaseListAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

        public BaseListAdapter(@LayoutRes int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, T item) {

        }
    }
}

