package com.example.taufiq.mitchnotes.utils;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created By Taufiq on 6/26/2019.
 * MitchNotes
 */
public class RecyclerItemDecorator  extends RecyclerView.ItemDecoration {

    private final int spacingHeight;
    private final int spacingWidth;

    public RecyclerItemDecorator(int spacingHeight, int spacingWidth) {
        this.spacingHeight = spacingHeight;
        this.spacingWidth = spacingWidth;
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = spacingHeight;
        outRect.top = spacingHeight;
        outRect.right = spacingWidth;
        outRect.left = spacingWidth;

    }
}
