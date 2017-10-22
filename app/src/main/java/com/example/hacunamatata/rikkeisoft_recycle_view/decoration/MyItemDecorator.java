package com.example.hacunamatata.rikkeisoft_recycle_view.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by brianhoang on 10/16/17.
 */

public class MyItemDecorator extends RecyclerView.ItemDecoration {
    int itemSpace;

    public MyItemDecorator(int itemSpace) {
        this.itemSpace = itemSpace;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom = itemSpace;
        outRect.top = itemSpace;
        outRect.left = itemSpace;
        outRect.right = itemSpace;
    }
}
