package com.sfj.sfj.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sfj.sfj.R;
import com.sfj.sfj.utils.PxUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyu on 17/4/6.
 * app头
 */

public class AppToolbar extends RelativeLayout {


    private  View background;
    private LinearLayout toptitle_left;
    private LinearLayout toptitle_right;
    private LinearLayout toptitle_center;
    private View line;

    private List<View> leftViews = new ArrayList<>();
    private List<View> rightViews = new ArrayList<>();
    private List<View> centerViews = new ArrayList<>();
    private View status_bar;


    public AppToolbar(Context context) {
        super(context);

        init(context);
        setId(R.id.title_id);
    }


    public AppToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    private void init(Context context) {
        View titleView = View.inflate(context, R.layout.toptitle, this);
        toptitle_left = (LinearLayout) titleView
                .findViewById(R.id.toptitle_left);
        toptitle_right = (LinearLayout) titleView
                .findViewById(R.id.toptitle_right);
        toptitle_center = (LinearLayout) titleView
                .findViewById(R.id.toptitle_center);
        background=  titleView.findViewById(R.id.background);
        line= titleView.findViewById(R.id.line);
    }

    /**
     * 显示底部line
     */
    public void showBottomLine(boolean visible){
        if(visible){
            line.setVisibility(View.VISIBLE);
        }else{
            line.setVisibility(View.GONE);
        }

    }

    public AppToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidthLeft = toptitle_left.getMeasuredWidth();
        int measuredWidthRight = toptitle_right.getMeasuredWidth();
        int max = Math.max(measuredWidthLeft, measuredWidthRight);
        toptitle_left.getLayoutParams().width=max;
        toptitle_right.getLayoutParams().width=max;
    }

    public void attachFragment(View view) {
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, PxUtil.convertDIP2PX(getContext(), 50));
        ((ViewGroup) view).addView(this, lp);
    }


    public View getBackgroundView() {
        return background;
    }

    public View getLeftView(int i) {
        return toptitle_left.getChildAt(i);
    }

    public View getCenterView(int i) {
        return toptitle_center.getChildAt(i);
    }

    public View getRightView(int i) {
        return toptitle_right.getChildAt(i);
    }

    public LinearLayout getLeftViewGroup() {
        return toptitle_left;
    }

    public LinearLayout getCenterViewGroup() {
        return toptitle_center;
    }

    public LinearLayout getRightViewGroup() {
        return toptitle_right;
    }

    public void build() {
        build(true);
    }

    /**
     * 提交
     *
     * @param
     */
    public void build(boolean isVertical) {
        if (isVertical) {
            ViewGroup content = (ViewGroup) getParent();
            LayoutParams rlp = (LayoutParams) content.getChildAt(0).getLayoutParams();
            rlp.addRule(RelativeLayout.BELOW, R.id.title_id);
            content.getChildAt(0).setLayoutParams(rlp);
//            if(content.getChildAt(1)!=null&&content.getChildAt(1) instanceof EmptyLayout){
//                content.getChildAt(1).setLayoutParams(rlp);
//            }
            background.setBackgroundColor(getResources().getColor(R.color.white));
        } else {
            background.setBackgroundColor(getResources().getColor(R.color.white));
            background.setAlpha(0);
            ViewGroup content = (ViewGroup) getParent();
            LayoutParams rlp = (LayoutParams) content.getChildAt(0).getLayoutParams();
            rlp.removeRule(RelativeLayout.BELOW);
//            if(content.getChildAt(1)!=null&&content.getChildAt(1) instanceof EmptyLayout){
//                LayoutParams rlp2 = (LayoutParams) content.getChildAt(1).getLayoutParams();
//                rlp2.removeRule(RelativeLayout.BELOW);
//            }
        }

        //左边添加view
        for (View view : leftViews) {
            toptitle_left.addView(view);
        }
        //中间添加view
        for (View view : centerViews) {
            toptitle_center.addView(view);
        }
        //右边添加view
        for (View view : rightViews) {
            toptitle_right.addView(view);
        }

        rightViews.clear();
        centerViews.clear();
        leftViews.clear();
    }

    public void putRightView(View view) {

        rightViews.add(view);

    }

    public void putLeftView(View view) {
        leftViews.add(view);

    }


    /**
     * 通过此方法生成并返回左边textview imageview imagebutton button
     *
     * @param t
     * @return
     */
    public <T> T creatLeftView(Class<T> t) {
       return creatLeftView(t,null,null);
    }
    //单位为dp
    public <T> T creatLeftView(Class<T> t,int[] paddings,int[] margins) {
        if(paddings==null){
            paddings=new int[]{16,5,5,5};

        }
        if(margins==null){
            margins=new int[]{0,0,0,0};
        }

        LinearLayout.LayoutParams leftLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftLayoutParams.setMargins(PxUtil.convertDIP2PX(getContext(), margins[0]), PxUtil.convertDIP2PX(getContext(), margins[1]), PxUtil.convertDIP2PX(getContext(), margins[2]), PxUtil.convertDIP2PX(getContext(), margins[3]));
        if (t == TextView.class) {
            TextView textView = new TextView(getContext());
            textView.setTextSize(16);
            textView.setSingleLine();
            textView.setTextColor(getResources().getColor(R.color.color_333333));
            textView.setLayoutParams(leftLayoutParams);
            textView.setPadding(PxUtil.convertDIP2PX(getContext(), paddings[0]), PxUtil.convertDIP2PX(getContext(), paddings[1]), PxUtil.convertDIP2PX(getContext(), paddings[2]), PxUtil.convertDIP2PX(getContext(), paddings[3]));

            textView.setGravity(Gravity.CENTER);
            leftViews.add(textView);
            return (T) textView;
        } else if (t == ImageView.class) {
            ImageView imageView = new ImageView(getContext());
            leftLayoutParams.gravity = Gravity.CENTER_VERTICAL;
            imageView.setLayoutParams(leftLayoutParams);
            imageView.setPadding(PxUtil.convertDIP2PX(getContext(), paddings[0]), PxUtil.convertDIP2PX(getContext(), paddings[1]), PxUtil.convertDIP2PX(getContext(), paddings[2]), PxUtil.convertDIP2PX(getContext(), paddings[3]));

//            imageView.setImageResource(R.drawable.message_icon);
            leftViews.add(imageView);

            return (T) imageView;
        } else if (t == ImageButton.class) {
            ImageButton imageButton = new ImageButton(getContext());
            leftLayoutParams.gravity = Gravity.CENTER_VERTICAL;
            imageButton.setLayoutParams(leftLayoutParams);
            imageButton.setPadding(PxUtil.convertDIP2PX(getContext(), paddings[0]), PxUtil.convertDIP2PX(getContext(), paddings[1]), PxUtil.convertDIP2PX(getContext(), paddings[2]), PxUtil.convertDIP2PX(getContext(), paddings[3]));

//            imageButton.setImageResource(R.drawable.weixin);
            leftViews.add(imageButton);
            return (T) imageButton;
        } else if (t == Button.class) {
            Button button = new Button(getContext());
            button.setPadding(PxUtil.convertDIP2PX(getContext(), paddings[0]), PxUtil.convertDIP2PX(getContext(), paddings[1]), PxUtil.convertDIP2PX(getContext(), paddings[2]), PxUtil.convertDIP2PX(getContext(), paddings[3]));

            button.setLayoutParams(leftLayoutParams);
            button.setGravity(Gravity.CENTER);
            leftViews.add(button);
            return (T) button;
        } else {
            throw new IllegalArgumentException("不支持的头部控件类型");
        }

    }

    /**
     * 通过此方法生成并返回右边textview imageview imagebutton button
     *
     * @param t
     * @return
     */
    public <T> T creatRightView(Class<T> t) {
       return creatRightView(t,null,null);
    }
    //单位为dp
    public <T> T creatRightView(Class<T> t,int[] paddings,int[] margins) {
        if(paddings==null){
            paddings=new int[]{5,5,16,5};

        }
        if(margins==null){
            margins=new int[]{0,0,0,0};
        }
        LinearLayout.LayoutParams rightLayoutParams = new  LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightLayoutParams.setMargins(PxUtil.convertDIP2PX(getContext(), margins[0]), PxUtil.convertDIP2PX(getContext(), margins[1]), PxUtil.convertDIP2PX(getContext(), margins[2]), PxUtil.convertDIP2PX(getContext(), margins[3]));
        if (t == TextView.class) {
            TextView textView = new TextView(getContext());

            textView.setTextColor(getResources().getColorStateList(R.color.primary_material_light));
            textView.setTextSize(16);
            textView.setSingleLine();
            textView.setLayoutParams(rightLayoutParams);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(PxUtil.convertDIP2PX(getContext(), paddings[0]), PxUtil.convertDIP2PX(getContext(), paddings[1]), PxUtil.convertDIP2PX(getContext(), paddings[2]), PxUtil.convertDIP2PX(getContext(), paddings[3]));

            rightViews.add(textView);
            return (T) textView;
        } else if (t == ImageView.class) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(rightLayoutParams);
//            imageView.setImageResource(R.drawable.menu_more);
            imageView.setPadding(PxUtil.convertDIP2PX(getContext(), paddings[0]), PxUtil.convertDIP2PX(getContext(), paddings[1]), PxUtil.convertDIP2PX(getContext(), paddings[2]), PxUtil.convertDIP2PX(getContext(), paddings[3]));

            rightViews.add(imageView);
            return (T) imageView;
        } else if (t == ImageButton.class) {
            ImageButton imageButton = new ImageButton(getContext());
            imageButton.setLayoutParams(rightLayoutParams);
            imageButton.setPadding(PxUtil.convertDIP2PX(getContext(), paddings[0]), PxUtil.convertDIP2PX(getContext(), paddings[1]), PxUtil.convertDIP2PX(getContext(), paddings[2]), PxUtil.convertDIP2PX(getContext(), paddings[3]));

            rightViews.add(imageButton);
            return (T) imageButton;
        } else if (t == Button.class) {
            Button button = new Button(getContext());
            button.setPadding(PxUtil.convertDIP2PX(getContext(), paddings[0]), PxUtil.convertDIP2PX(getContext(), paddings[1]), PxUtil.convertDIP2PX(getContext(), paddings[2]), PxUtil.convertDIP2PX(getContext(), paddings[3]));

            button.setLayoutParams(rightLayoutParams);
            button.setGravity(Gravity.CENTER);
            rightViews.add(button);
            return (T) button;
        } else {
            throw new IllegalArgumentException("不支持的头部控件类型");
        }

    }

    /**
     * 通过此方法生成并返回中间textview imageview imagebutton button
     *
     * @param t
     * @return
     */
    public <T> T creatCenterView(Class<T> t) {
        return creatCenterView(t,null,null);
    }

 //单位为dp
    public <T> T creatCenterView(Class<T> t,int[] paddings,int[] margins) {
        if(paddings==null){
            paddings=new int[]{0,2,0,2};

        }
        if(margins==null){
            margins=new int[]{0,0,0,0};
        }
        LinearLayout.LayoutParams centerLayoutParams = new  LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        centerLayoutParams.setMargins(PxUtil.convertDIP2PX(getContext(), margins[0]), PxUtil.convertDIP2PX(getContext(), margins[1]), PxUtil.convertDIP2PX(getContext(), margins[2]), PxUtil.convertDIP2PX(getContext(), margins[3]));
        if (t == TextView.class) {
            TextView textView = new TextView(getContext());
            textView.setTextSize(18);
            textView.setSingleLine();
            textView.setTextColor(getResources().getColor(R.color.color_333333));
            textView.setPadding(PxUtil.convertDIP2PX(getContext(), paddings[0]), PxUtil.convertDIP2PX(getContext(), paddings[1]), PxUtil.convertDIP2PX(getContext(), paddings[2]), PxUtil.convertDIP2PX(getContext(), paddings[3]));
//            textView.getPaint().setFakeBoldText(true);
            textView.setLayoutParams(centerLayoutParams);
            textView.setGravity(Gravity.CENTER);
            centerViews.add(textView);
            return (T) textView;
        } else if (t == ImageView.class) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(centerLayoutParams);
            imageView.setPadding(PxUtil.convertDIP2PX(getContext(), paddings[0]), PxUtil.convertDIP2PX(getContext(), paddings[1]), PxUtil.convertDIP2PX(getContext(), paddings[2]), PxUtil.convertDIP2PX(getContext(), paddings[3]));

            centerViews.add(imageView);
            return (T) imageView;
        } else if (t == EditText.class) {
            EditText edit = new EditText(getContext());
            edit.setLayoutParams(centerLayoutParams);
            edit.setPadding(PxUtil.convertDIP2PX(getContext(), paddings[0]), PxUtil.convertDIP2PX(getContext(), paddings[1]), PxUtil.convertDIP2PX(getContext(), paddings[2]), PxUtil.convertDIP2PX(getContext(), paddings[3]));

            centerViews.add(edit);
            return (T) edit;
        } else {
            throw new IllegalArgumentException("不支持的头部控件类型");
        }

    }

}
