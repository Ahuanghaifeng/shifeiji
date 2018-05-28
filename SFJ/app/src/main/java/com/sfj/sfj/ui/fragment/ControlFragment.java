package com.sfj.sfj.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseDetailFragment;
import com.sfj.sfj.bean.CxBean;
import com.sfj.sfj.widget.EmptyLayout;

import java.util.ArrayList;
import java.util.List;

public class ControlFragment extends BaseDetailFragment {

    private RecyclerView recyclerView;
    private TextView ggsg,jssq,js1,js2,xf1,xf2,xf3,xf4;

    public static ControlFragment newInstance() {

        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_FRAGMENT_TITLE,"控制");
        ControlFragment fragment = new ControlFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getDetailLayoutId() {
        return R.layout.fragment_control;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
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

        jssq.setTag(false);
        ggsg.setTag(false);
        js1.setTag(false);
        js2.setTag(false);
        xf1.setTag(false);
        xf2.setTag(false);
        xf3.setTag(false);
        xf4.setTag(false);

        ggsg.setOnClickListener(onClickListener);
        jssq.setOnClickListener(onClickListener);
        js1.setOnClickListener(onClickListener);
        js2.setOnClickListener(onClickListener);
        xf1.setOnClickListener(onClickListener);
        xf2.setOnClickListener(onClickListener);
        xf3.setOnClickListener(onClickListener);
        xf4.setOnClickListener(onClickListener);
    }

    public void initCx(View view){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_cx);
        recyclerView.setLayoutManager(gridLayoutManager);
        final CxGridAdapter cxGridAdapter = new CxGridAdapter(R.layout.item_text_view);
        final List<CxBean> datas = new ArrayList<>();
        for (int i=0;i<3;i++){
            CxBean data = new CxBean();
            data.setName("程序"+i);
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

    class CxGridAdapter extends BaseQuickAdapter<CxBean,BaseViewHolder> {

        public CxGridAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, CxBean item) {
            if (item.isCheck()){
                helper.getView(R.id.tv_1).setBackgroundResource(R.drawable.icon_control_checked);
            }else{
                helper.getView(R.id.tv_1).setBackgroundResource(R.drawable.icon_control_check);
            }
            helper.setText(R.id.tv_1,item.getName());
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_jssg:
                    jssq.setTag(!(boolean)jssq.getTag());
                    changeSelectBg(jssq);
                    break;
                case R.id.tv_ggsg:
                    ggsg.setTag(!(boolean)ggsg.getTag());
                    changeSelectBg(ggsg);
                    break;
                case R.id.tv_js1:
                    js1.setTag(!(boolean)js1.getTag());
                    changeSelectBg(js1);
                    break;
                case R.id.tv_js2:
                    js2.setTag(!(boolean)js2.getTag());
                    changeSelectBg(js2);
                    break;
                case R.id.tv_xf_1:
                    xf1.setTag(!(boolean)xf1.getTag());
                    changeSelectBg(xf1);
                    break;
                case R.id.tv_xf_2:
                    xf2.setTag(!(boolean)xf2.getTag());
                    changeSelectBg(xf2);
                    break;
                case R.id.tv_xf_3:
                    xf3.setTag(!(boolean)xf3.getTag());
                    changeSelectBg(xf3);
                    break;
                case R.id.tv_xf_4:
                    xf4.setTag(!(boolean)xf4.getTag());
                    changeSelectBg(xf4);
                    break;
            }
        }
    };

    public void changeSelectBg(TextView view){
        if ((boolean)view.getTag()){
            view.setBackgroundResource(R.drawable.icon_control_checked);
        }else{
            view.setBackgroundResource(R.drawable.icon_control_check);
        }
    }
}
