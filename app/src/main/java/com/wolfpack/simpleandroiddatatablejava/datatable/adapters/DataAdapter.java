package com.wolfpack.simpleandroiddatatablejava.datatable.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.wolfpack.simpleandroiddatatablejava.datatable.rows.DessertRow;
import com.wolfpack.simpleandroiddatatablejava.datatable.rows.HeaderRow;
import com.wolfpack.simpleandroiddatatablejava.datatable.rows.Row;

public class DataAdapter extends RowsAdapter implements View.OnLayoutChangeListener {
    private final static int DEFAULT_VISIBLE_WINDOW_WIDTH = 200;
    private int visibleArrayWindowWidth = DEFAULT_VISIBLE_WINDOW_WIDTH;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, Class<? extends Row> rowType) {
        if (rowType.equals(HeaderRow.class)) {
            return HeaderRow.createViewHolder(viewGroup);
        } else {
            return DessertRow.createViewHolder(viewGroup);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, Row row) {
        row.onBindViewHolder(viewHolder);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (left == 0 && top == 0 && right == 0 && bottom == 0) {
            visibleArrayWindowWidth = DEFAULT_VISIBLE_WINDOW_WIDTH;
            return;
        }

        int newWidth = right - left;
        if (newWidth == visibleArrayWindowWidth) return;

        visibleArrayWindowWidth = newWidth;
        notifyDataSetChanged();
    }
}
