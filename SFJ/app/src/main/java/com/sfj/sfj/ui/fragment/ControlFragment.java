package com.sfj.sfj.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sfj.sfj.R;
import com.sfj.sfj.base.AppInfoManager;
import com.sfj.sfj.base.BaseActivity;
import com.sfj.sfj.base.BaseDetailFragment;
import com.sfj.sfj.bean.ApiBean;
import com.sfj.sfj.bean.ControlBean;
import com.sfj.sfj.bean.Sfj_Bean;
import com.sfj.sfj.net.CloudSDKHttpHandler;
import com.sfj.sfj.net.ICloudSDKHttpHandler;
import com.sfj.sfj.net.TGBApi;
import com.sfj.sfj.utils.ToastUtils;
import com.sfj.sfj.widget.AppToolbar;
import com.sfj.sfj.widget.SwitchGameDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlFragment extends BaseDetailFragment<ControlBean> {

    SwitchGameDialog gameDialog;
    private String fertilizerId = "";
    private RecyclerView recyclerView;
    private TextView tvTitle;
    private TextView sfjName,sfjOnline;
    ScrollView mScrollView;
    CxGridAdapter ggGridAdapter,cxGridAdapter;

    public static ControlFragment newInstance() {

        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_FRAGMENT_TITLE, "控制");
        ControlFragment fragment = new ControlFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean initTitle(AppToolbar appBar) {
        appBar.showBottomLine(true);
        tvTitle = appBar.creatCenterView(TextView.class);
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle != null) {
                tvTitle.setText(bundle.getString(BUNDLE_KEY_FRAGMENT_TITLE, ""));
            }
        }

        Drawable drawable = getResources().getDrawable(R.mipmap.left_arrow_back);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        TextView leftTextView = appBar.creatLeftView(TextView.class);
        leftTextView.setCompoundDrawables(drawable, null, null, null);
        leftTextView.setCompoundDrawablePadding(10);
        leftTextView.setText("返回");
        leftTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        appBar.build();
        return true;
    }

    @Override
    protected int getDetailLayoutId() {
        return R.layout.fragment_control;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        gameDialog = new SwitchGameDialog(getContext());
        gameDialog.setOnSwitchGameListener(new SwitchGameDialog.OnSwitchGameListener() {
            @Override
            public void OnSwitchGame(String gameId, String gameName,int isOnline) {
                if (isOnline==1){
                    tvTitle.setText(gameName);
                    fertilizerId = gameId;
                    mSwipeRefreshLayout.setRefreshing(true);
                    String username = AppInfoManager.getInstance().getUserInfo().getUsername();
                    String password = AppInfoManager.getInstance().getUserInfo().getPassword();
                    TGBApi.doFertilizerInfo(username, password, gameId, mHandler);
                }else{
                    ToastUtils.showShortToast("该施肥机已离线");
                }
            }
        });
        mScrollView = (ScrollView) view.findViewById(R.id.scrollView);
        if (mScrollView != null) {
            mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    if (mSwipeRefreshLayout != null) {
                        mSwipeRefreshLayout.setEnabled(mScrollView.getScrollY() == 0);
                    }
                }
            });
        }
        initCx(view);

        sfjName = (TextView) view.findViewById(R.id.tv_name);
        sfjOnline = (TextView) view.findViewById(R.id.tv_state);
        view.findViewById(R.id.card_qiehuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameDialog == null) {
                    gameDialog = new SwitchGameDialog(getContext());
                }
                gameDialog.show();
            }
        });
    }

    public void initCx(View view) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_cx);
        recyclerView.setLayoutManager(gridLayoutManager);
        cxGridAdapter = new CxGridAdapter(R.layout.item_text_view,0);
        cxGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                send(position+1,(int)adapter.getItem(position)==0?1:0,"appProgramControl.do");
            }
        });
        recyclerView.setAdapter(cxGridAdapter);
        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.recycler_gg);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 4);
        recyclerView1.setLayoutManager(gridLayoutManager1);
        ggGridAdapter = new CxGridAdapter(R.layout.item_text_view,1);
        ggGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                send(position+1,(int)adapter.getItem(position)==0?1:0,"appManualControl.do");
            }
        });
        recyclerView1.setAdapter(ggGridAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView1.setNestedScrollingEnabled(false);
    }

    class CxGridAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

        private int mType;

        public CxGridAdapter(int layoutResId,int type) {
            super(layoutResId);
            mType = type;
        }

        @Override
        protected void convert(BaseViewHolder helper, Integer item) {
            if (item==1) {
                helper.getView(R.id.tv_1).setBackgroundResource(R.drawable.select_bg);
            } else {
                helper.getView(R.id.tv_1).setBackgroundResource(R.drawable.select_bg_gray);
            }
            if (mType==0){
                helper.setText(R.id.tv_1, "程序"+(helper.getLayoutPosition()+1));
            }else{
                helper.setText(R.id.tv_1, helper.getLayoutPosition()+1+"#");
            }
        }
    }


    @Override
    protected void sendRequestData() {
        super.sendRequestData();
        String username = AppInfoManager.getInstance().getUserInfo().getUsername();
        String password = AppInfoManager.getInstance().getUserInfo().getPassword();
        TGBApi.doFertilizerToControl(username, password, fertilizerId, mHandler);
    }

    @Override
    protected ControlBean parseItem(String mjson) {
        ControlBean data = JSON.parseObject(mjson, ControlBean.class);
        return data;
    }

    @Override
    protected void executeOnLoadDataSuccess(ControlBean item) {
        refreshSfj(item);
        ggGridAdapter.replaceData(item.getIrrigValves());
        cxGridAdapter.replaceData(item.getPrograms());
    }


    //刷新施肥机
    public void refreshSfj(ControlBean item){
        tvTitle.setText(item.getFertilizerName());
        fertilizerId = String.valueOf(item.getFertilizerId());
        List<Sfj_Bean.FertilizersBean> beans = new ArrayList<>();
        for (int i = 0; i < item.getFertilizers().size(); i++) {
            Sfj_Bean.FertilizersBean b = new Sfj_Bean.FertilizersBean();
            b.setFertilizerName(item.getFertilizers().get(i).getFertilizerName());
            b.setFertilizerId(item.getFertilizers().get(i).getFertilizerId());
            b.setIsOnline(item.getFertilizers().get(i).getIsOnline());
            beans.add(b);
        }
        gameDialog.setmData(beans);
        sfjName.setText(item.getFertilizerName());
        sfjOnline.setText(item.getIsOnline() == 1 ? "在线" : "离线");
        downTimer.start();
    }


    public void send(int valveNum,int value,String action){
        ((BaseActivity)getActivity()).showWaitDialog("正在发送数据...");
        Map<String,String> values = new HashMap<>();
        values.put("valveNum",String.valueOf(valveNum));
        values.put("value",String.valueOf(value));
        String username = AppInfoManager.getInstance().getUserInfo().getUsername();
        String password = AppInfoManager.getInstance().getUserInfo().getPassword();
        TGBApi.doFertilizerControl(username,password,fertilizerId,values,action,new CloudSDKHttpHandler(new ICloudSDKHttpHandler() {
            @Override
            public void onSuccess(int statusCode, String mjson) {
                ((BaseActivity)getActivity()).hideWaitDialog();
                ApiBean bean = JSON.parseObject(mjson,ApiBean.class);
                if ("200".equals(bean.getCode())){
                    ToastUtils.showLongToast("发送成功,更新界面中请稍后...");
                    sendRequestData();
                }else{
                    ToastUtils.showShortToast(bean.getMsg());
                }
            }

            @Override
            public void onFailure(int statusCode, String responseBody, Throwable error) {
                ((BaseActivity)getActivity()).hideWaitDialog();
                ToastUtils.showShortToast(R.string.error_view_network);
            }
        }));
    }

    private CountDownTimer downTimer = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
//            sendRequestData();
        }
    };
}
