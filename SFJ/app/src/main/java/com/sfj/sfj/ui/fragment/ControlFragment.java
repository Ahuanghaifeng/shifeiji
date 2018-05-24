package com.sfj.sfj.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);
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
                for (int j =0;j<datas.size();j++){
                    datas.get(j).setCheck(false);
                }
                datas.get(position).setCheck(true);
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
            helper.setText(R.id.tv_1,item.getName());
            if (item.isCheck()){
                ((TextView)helper.getView(R.id.tv_1)).setTextColor(Color.WHITE);
                helper.getView(R.id.tv_1).setBackgroundResource(R.drawable.select_bg);
            }else{
                ((TextView)helper.getView(R.id.tv_1)).setTextColor(Color.parseColor("#585858"));
                helper.getView(R.id.tv_1).setBackgroundResource(R.drawable.select_bg_gray);
            }
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_jssg:
                    List<TextView> list = new ArrayList<>();
                    list.add(jssq);
                    list.add(ggsg);
                    changeSelectBg(list);
                    break;
                case R.id.tv_ggsg:
                    List<TextView> list1 = new ArrayList<>();
                    list1.add(ggsg);
                    list1.add(jssq);
                    changeSelectBg(list1);
                    break;
                case R.id.tv_js1:
                    List<TextView> list2 = new ArrayList<>();
                    list2.add(js1);
                    list2.add(js2);
                    changeSelectBg(list2);
                    break;
                case R.id.tv_js2:
                    List<TextView> list3 = new ArrayList<>();
                    list3.add(js2);
                    list3.add(js1);
                    changeSelectBg(list3);
                    break;
                case R.id.tv_xf_1:
                    List<TextView> list4 = new ArrayList<>();
                    list4.add(xf1);
                    list4.add(xf2);
                    list4.add(xf3);
                    list4.add(xf4);
                    changeSelectBg(list4);
                    break;
                case R.id.tv_xf_2:
                    List<TextView> list5 = new ArrayList<>();
                    list5.add(xf2);
                    list5.add(xf1);
                    list5.add(xf3);
                    list5.add(xf4);
                    changeSelectBg(list5);
                    break;
                case R.id.tv_xf_3:
                    List<TextView> list6 = new ArrayList<>();
                    list6.add(xf3);
                    list6.add(xf1);
                    list6.add(xf2);
                    list6.add(xf4);
                    changeSelectBg(list6);
                    break;
                case R.id.tv_xf_4:
                    List<TextView> list7 = new ArrayList<>();
                    list7.add(xf4);
                    list7.add(xf1);
                    list7.add(xf2);
                    list7.add(xf3);
                    changeSelectBg(list7);
                    break;
            }
        }
    };

    public void changeSelectBg(List<TextView> views){
        views.get(0).setTextColor(Color.WHITE);
        views.get(0).setBackgroundResource(R.drawable.select_bg);
        for (int i = 1;i<views.size();i++){
            views.get(i).setTextColor(Color.parseColor("#585858"));
            views.get(i).setBackgroundResource(R.drawable.select_bg_gray);
        }
    }
}
