package com.sfj.sfj.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sfj.sfj.R;
import com.sfj.sfj.bean.ControlBean;
import com.sfj.sfj.bean.Sfj_Bean;
import com.sfj.sfj.utils.PxUtil;
import com.sfj.sfj.utils.ScreenUtils;


import java.util.List;

/**
 * Created by haifeng on 2018/4/2.
 */

public class SwitchGameDialog extends Dialog{

    private RecyclerView recyclerView;
    private List<Sfj_Bean.FertilizersBean> mData;
    GameAdapter gameAdapter;

    public SwitchGameDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
        gameAdapter = new GameAdapter(R.layout.item_switch_game);
    }

    public SwitchGameDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_switch_game);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_game);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(gameAdapter);
        gameAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (onSwitchGameListener!=null){
                    onSwitchGameListener.OnSwitchGame(String.valueOf(mData.get(position).getFertilizerId()),mData.get(position).getFertilizerName(),mData.get(position).getIsOnline());
                    dismiss();
                }
            }
        });
    }

    public void setmData(List<Sfj_Bean.FertilizersBean> bean){
        mData = bean;
        gameAdapter.replaceData(mData);
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.TOP;
        layoutParams.width = ScreenUtils.getScreenWidth(getContext());
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.y = PxUtil.convertDIP2PX(getContext(), 50);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }

    private class GameAdapter extends BaseQuickAdapter<Sfj_Bean.FertilizersBean,BaseViewHolder>{

        public GameAdapter(@LayoutRes int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Sfj_Bean.FertilizersBean item) {
            helper.setText(R.id.tv_game_name,item.getFertilizerName());
        }
    }

    public interface OnSwitchGameListener{
        void OnSwitchGame(String gameId, String gameName,int isOnline);
    }

    OnSwitchGameListener onSwitchGameListener;

    public void setOnSwitchGameListener(OnSwitchGameListener onSwitchGameListener) {
        this.onSwitchGameListener = onSwitchGameListener;
    }
}
