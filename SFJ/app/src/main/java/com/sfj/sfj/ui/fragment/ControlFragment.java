package com.sfj.sfj.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sfj.sfj.R;
import com.sfj.sfj.base.AppInfoManager;
import com.sfj.sfj.base.BaseDetailFragment;
import com.sfj.sfj.bean.ApiBean;
import com.sfj.sfj.bean.ControlBean;
import com.sfj.sfj.bean.CxBean;
import com.sfj.sfj.bean.Sfj_Bean;
import com.sfj.sfj.net.CloudSDKHttpHandler;
import com.sfj.sfj.net.ICloudSDKHttpHandler;
import com.sfj.sfj.net.TGBApi;
import com.sfj.sfj.utils.ToastUtils;
import com.sfj.sfj.widget.AppToolbar;
import com.sfj.sfj.widget.EmptyLayout;
import com.sfj.sfj.widget.SwitchGameDialog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ControlFragment extends BaseDetailFragment<ControlBean> {

    SwitchGameDialog gameDialog;
    private String fertilizerId = "";
    private RecyclerView recyclerView;
    private TextView ggsg, jssq, js1, js2, xf1, xf2, xf3, xf4;
    private TextView gg1, gg2, gg3, gg4, gg5, gg6, gg7, gg8, gg9, gg10, gg11, gg12, gg13, gg14, gg15, gg16;
    private TextView tvTitle;
    private Button send;
    private Map<String,TextView> sends;

    public static ControlFragment newInstance() {

        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_FRAGMENT_TITLE, "控制");
        ControlFragment fragment = new ControlFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean initTitle(AppToolbar appBar) {
        TextView rightText = appBar.creatRightView(TextView.class, new int[]{0, 0, 0, 0}, new int[]{0, 0, 10, 0});
        rightText.setTextColor(Color.parseColor("#000000"));
        rightText.setText("切换");
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameDialog == null) {
                    gameDialog = new SwitchGameDialog(getContext());
                }
                gameDialog.show();
            }
        });
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
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setEnabled(false);
        mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        initCx(view);
        jssq = (TextView) view.findViewById(R.id.tv_jssg);
        ggsg = (TextView) view.findViewById(R.id.tv_ggsg);
        js1 = (TextView) view.findViewById(R.id.tv_js1);
        js2 = (TextView) view.findViewById(R.id.tv_js2);
        xf1 = (TextView) view.findViewById(R.id.tv_xf_1);
        xf2 = (TextView) view.findViewById(R.id.tv_xf_2);
        xf3 = (TextView) view.findViewById(R.id.tv_xf_3);
        xf4 = (TextView) view.findViewById(R.id.tv_xf_4);

        gg1 = (TextView) view.findViewById(R.id.tv_gg_1);
        gg2 = (TextView) view.findViewById(R.id.tv_gg_2);
        gg3 = (TextView) view.findViewById(R.id.tv_gg_3);
        gg4 = (TextView) view.findViewById(R.id.tv_gg_4);
        gg5 = (TextView) view.findViewById(R.id.tv_gg_5);
        gg6 = (TextView) view.findViewById(R.id.tv_gg_6);
        gg7 = (TextView) view.findViewById(R.id.tv_gg_7);
        gg8 = (TextView) view.findViewById(R.id.tv_gg_8);
        gg9 = (TextView) view.findViewById(R.id.tv_gg_9);
        gg10 = (TextView) view.findViewById(R.id.tv_gg_10);
        gg11 = (TextView) view.findViewById(R.id.tv_gg_11);
        gg12 = (TextView) view.findViewById(R.id.tv_gg_12);
        gg13 = (TextView) view.findViewById(R.id.tv_gg_13);
        gg14 = (TextView) view.findViewById(R.id.tv_gg_14);
        gg15 = (TextView) view.findViewById(R.id.tv_gg_15);
        gg16 = (TextView) view.findViewById(R.id.tv_gg_16);

        send = (Button)view.findViewById(R.id.bt_send);

        initSend();
        initOnClick();
    }

    public void initOnClick(){
        ggsg.setOnClickListener(onClickListener);
        jssq.setOnClickListener(onClickListener);
        js1.setOnClickListener(onClickListener);
        js2.setOnClickListener(onClickListener);
        xf1.setOnClickListener(onClickListener);
        xf2.setOnClickListener(onClickListener);
        xf3.setOnClickListener(onClickListener);
        xf4.setOnClickListener(onClickListener);

        gg1.setOnClickListener(onClickListener);
        gg2.setOnClickListener(onClickListener);
        gg3.setOnClickListener(onClickListener);
        gg4.setOnClickListener(onClickListener);
        gg5.setOnClickListener(onClickListener);
        gg6.setOnClickListener(onClickListener);
        gg7.setOnClickListener(onClickListener);
        gg8.setOnClickListener(onClickListener);
        gg9.setOnClickListener(onClickListener);
        gg10.setOnClickListener(onClickListener);
        gg11.setOnClickListener(onClickListener);
        gg12.setOnClickListener(onClickListener);
        gg13.setOnClickListener(onClickListener);
        gg14.setOnClickListener(onClickListener);
        gg15.setOnClickListener(onClickListener);
        gg16.setOnClickListener(onClickListener);

        send.setOnClickListener(onClickListener);
    }

    public void initSend(){
        jssq.setTag(false);
        ggsg.setTag(false);
        js1.setTag(false);
        js2.setTag(false);
        xf1.setTag(false);
        xf2.setTag(false);
        xf3.setTag(false);
        xf4.setTag(false);
        gg1.setTag(false);
        gg2.setTag(false);
        gg3.setTag(false);
        gg4.setTag(false);
        gg5.setTag(false);
        gg6.setTag(false);
        gg7.setTag(false);
        gg8.setTag(false);
        gg9.setTag(false);
        gg10.setTag(false);
        gg11.setTag(false);
        gg12.setTag(false);
        gg13.setTag(false);
        gg14.setTag(false);
        gg15.setTag(false);
        gg16.setTag(false);

        sends = new HashMap<>();
        sends.put("inWaterPump",jssq);
        sends.put("irrigWaterPump",ggsg);
        sends.put("inWaterValve1",js1);
        sends.put("inWaterValve2",js2);
        sends.put("inFertValve1",xf1);
        sends.put("inFertValve2",xf2);
        sends.put("inFertValve3",xf3);
        sends.put("inFertValve4",xf4);
        sends.put("irrigValve1",gg1);
        sends.put("irrigValve2",gg2);
        sends.put("irrigValve3",gg3);
        sends.put("irrigValve4",gg4);
        sends.put("irrigValve5",gg5);
        sends.put("irrigValve6",gg6);
        sends.put("irrigValve7",gg7);
        sends.put("irrigValve8",gg8);
        sends.put("irrigValve9",gg9);
        sends.put("irrigValve10",gg10);
        sends.put("irrigValve11",gg11);
        sends.put("irrigValve12",gg12);
        sends.put("irrigValve13",gg13);
        sends.put("irrigValve14",gg14);
        sends.put("irrigValve15",gg15);
        sends.put("irrigValve16",gg16);
    }

    public void initCx(View view) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_cx);
        recyclerView.setLayoutManager(gridLayoutManager);
        final CxGridAdapter cxGridAdapter = new CxGridAdapter(R.layout.item_text_view);
        final List<CxBean> datas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CxBean data = new CxBean();
            data.setName("程序" + i);
            datas.add(data);
        }
        cxGridAdapter.addData(datas);
        cxGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                datas.get(position).setCheck(!datas.get(position).isCheck());
                cxGridAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(cxGridAdapter);
    }

    class CxGridAdapter extends BaseQuickAdapter<CxBean, BaseViewHolder> {

        public CxGridAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, CxBean item) {
            if (item.isCheck()) {
                helper.getView(R.id.tv_1).setBackgroundResource(R.drawable.icon_control_checked);
            } else {
                helper.getView(R.id.tv_1).setBackgroundResource(R.drawable.icon_control_check);
            }
            helper.setText(R.id.tv_1, item.getName());
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
        tvTitle.setText(item.getFertilizerName());
        fertilizerId = String.valueOf(item.getFertilizerId());
        ControlBean.ControlDataBean bean = item.getControlData();
        changeSelectBg(xf1, bean.getInFertValve1());
        changeSelectBg(xf2, bean.getInFertValve2());
        changeSelectBg(xf3, bean.getInFertValve3());
        changeSelectBg(xf4, bean.getInFertValve4());
        changeSelectBg(gg1, bean.getIrrigValve1());
        changeSelectBg(gg2, bean.getIrrigValve2());
        changeSelectBg(gg3, bean.getIrrigValve3());
        changeSelectBg(gg4, bean.getIrrigValve4());
        changeSelectBg(gg5, bean.getIrrigValve5());
        changeSelectBg(gg6, bean.getIrrigValve6());
        changeSelectBg(gg7, bean.getIrrigValve7());
        changeSelectBg(gg8, bean.getIrrigValve8());
        changeSelectBg(gg9, bean.getIrrigValve9());
        changeSelectBg(gg10, bean.getIrrigValve10());
        changeSelectBg(gg11, bean.getIrrigValve11());
        changeSelectBg(gg12, bean.getIrrigValve12());
        changeSelectBg(gg13, bean.getIrrigValve13());
        changeSelectBg(gg14, bean.getIrrigValve14());
        changeSelectBg(gg15, bean.getIrrigValve15());
        changeSelectBg(gg16, bean.getIrrigValve16());
        changeSelectBg(js1, bean.getInWaterValve1());
        changeSelectBg(js2, bean.getInWaterValve2());
        changeSelectBg(jssq, bean.getInWaterPump());
        changeSelectBg(ggsg, bean.getIrrigWaterPump());
        List<Sfj_Bean.FertilizersBean> beans = new ArrayList<>();
        for (int i = 0; i < item.getFertilizers().size(); i++) {
            Sfj_Bean.FertilizersBean b = new Sfj_Bean.FertilizersBean();
            b.setFertilizerName(item.getFertilizers().get(i).getFertilizerName());
            b.setFertilizerId(item.getFertilizers().get(i).getFertilizerId());
            b.setIsOnline(item.getFertilizers().get(i).getIsOnline());
            beans.add(b);
        }
        gameDialog.setmData(beans);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_jssg:
                    jssq.setTag(!(boolean) jssq.getTag());
                    changeSelectBg(jssq);
                    break;
                case R.id.tv_ggsg:
                    ggsg.setTag(!(boolean) ggsg.getTag());
                    changeSelectBg(ggsg);
                    break;
                case R.id.tv_js1:
                    js1.setTag(!(boolean) js1.getTag());
                    changeSelectBg(js1);
                    break;
                case R.id.tv_js2:
                    js2.setTag(!(boolean) js2.getTag());
                    changeSelectBg(js2);
                    break;
                case R.id.tv_xf_1:
                    xf1.setTag(!(boolean) xf1.getTag());
                    changeSelectBg(xf1);
                    break;
                case R.id.tv_xf_2:
                    xf2.setTag(!(boolean) xf2.getTag());
                    changeSelectBg(xf2);
                    break;
                case R.id.tv_xf_3:
                    xf3.setTag(!(boolean) xf3.getTag());
                    changeSelectBg(xf3);
                    break;
                case R.id.tv_xf_4:
                    xf4.setTag(!(boolean) xf4.getTag());
                    changeSelectBg(xf4);
                    break;
                case R.id.tv_gg_1:
                    gg1.setTag(!(boolean) gg1.getTag());
                    changeSelectBg(gg1);
                    break;
                case R.id.tv_gg_2:
                    gg2.setTag(!(boolean) gg2.getTag());
                    changeSelectBg(gg2);
                    break;
                case R.id.tv_gg_3:
                    gg3.setTag(!(boolean) gg3.getTag());
                    changeSelectBg(gg3);
                    break;
                case R.id.tv_gg_4:
                    gg4.setTag(!(boolean) gg4.getTag());
                    changeSelectBg(gg4);
                    break;
                case R.id.tv_gg_5:
                    gg5.setTag(!(boolean) gg5.getTag());
                    changeSelectBg(gg5);
                    break;
                case R.id.tv_gg_6:
                    gg6.setTag(!(boolean) gg6.getTag());
                    changeSelectBg(gg6);
                    break;
                case R.id.tv_gg_7:
                    gg7.setTag(!(boolean) gg7.getTag());
                    changeSelectBg(gg7);
                    break;
                case R.id.tv_gg_8:
                    gg8.setTag(!(boolean) gg8.getTag());
                    changeSelectBg(gg8);
                    break;
                case R.id.tv_gg_9:
                    gg9.setTag(!(boolean) gg9.getTag());
                    changeSelectBg(gg9);
                    break;
                case R.id.tv_gg_10:
                    gg10.setTag(!(boolean) gg10.getTag());
                    changeSelectBg(gg10);
                    break;
                case R.id.tv_gg_11:
                    gg11.setTag(!(boolean) gg11.getTag());
                    changeSelectBg(gg11);
                    break;
                case R.id.tv_gg_12:
                    gg12.setTag(!(boolean) gg12.getTag());
                    changeSelectBg(gg12);
                    break;
                case R.id.tv_gg_13:
                    gg13.setTag(!(boolean) gg13.getTag());
                    changeSelectBg(gg13);
                    break;
                case R.id.tv_gg_14:
                    gg14.setTag(!(boolean) gg14.getTag());
                    changeSelectBg(gg14);
                    break;
                case R.id.tv_gg_15:
                    gg15.setTag(!(boolean) gg15.getTag());
                    changeSelectBg(gg15);
                    break;
                case R.id.tv_gg_16:
                    gg16.setTag(!(boolean) gg16.getTag());
                    changeSelectBg(gg16);
                    break;
                case R.id.bt_send:
                    send();
                    break;
            }
        }
    };

    public void changeSelectBg(TextView view) {
        if ((boolean) view.getTag()) {
            view.setBackgroundResource(R.drawable.icon_control_checked);
        } else {
            view.setBackgroundResource(R.drawable.icon_control_check);
        }
    }

    public void changeSelectBg(TextView view, int controlState) {
        if (controlState == 0) {
            view.setBackgroundResource(R.drawable.icon_control_check);
            view.setTag(false);
        } else if (controlState == 1) {
            view.setTag(true);
            view.setBackgroundResource(R.drawable.icon_control_checked);
        }
    }

    public void send(){
        Map<String,String> values = new HashMap<>();
        Set<String> sets = sends.keySet();
        List<String> list = new ArrayList<>(sets);
        for (String str:list){
            boolean b = (boolean) sends.get(str).getTag();
            values.put(str,b?"1":"0");
        }
        String username = AppInfoManager.getInstance().getUserInfo().getUsername();
        String password = AppInfoManager.getInstance().getUserInfo().getPassword();
        TGBApi.doFertilizerControl(username,password,fertilizerId,values,new CloudSDKHttpHandler(new ICloudSDKHttpHandler() {
            @Override
            public void onSuccess(int statusCode, String mjson) {
                ApiBean bean = JSON.parseObject(mjson,ApiBean.class);
                if ("200".equals(bean.getCode())){
                    ToastUtils.showShortToast("发送成功");
                    sendRequestData();
                }
            }

            @Override
            public void onFailure(int statusCode, String responseBody, Throwable error) {

            }
        }));
    }
}
