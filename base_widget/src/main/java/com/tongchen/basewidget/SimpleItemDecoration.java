package com.tongchen.basewidget;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by TongChen at 23:46 on 2018/12/12.
 * <p>
 * Description:分割线
 */
public class SimpleItemDecoration extends RecyclerView.ItemDecoration {

    private int mDividerSize;


    public SimpleItemDecoration(Context context) {
        mDividerSize = context.getResources().getDimensionPixelSize(R.dimen.base_widget_divider_height);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = mDividerSize;
        outRect.bottom = mDividerSize;
        outRect.left = mDividerSize;
        outRect.right = mDividerSize;

    }
}
