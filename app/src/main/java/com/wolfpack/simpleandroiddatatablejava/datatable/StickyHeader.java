package com.wolfpack.simpleandroiddatatablejava.datatable;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.wolfpack.simpleandroiddatatablejava.datatable.rows.HeaderRow;
import com.wolfpack.simpleandroiddatatablejava.datatable.rows.Row;

import java.util.ArrayList;
import java.util.List;

public class StickyHeader extends RecyclerView.OnScrollListener {
    private List<Row> rows;
    private ViewGroup headerTitleView;
    private ViewGroup headerColumnsView;
    private HeaderRow currentHeader;
    private int currentPosition = 0;

    StickyHeader(ViewGroup headerTitleView, ViewGroup headerColumnsView) {
        this.headerTitleView = headerTitleView;
        this.headerColumnsView = headerColumnsView;
        this.rows = new ArrayList<>();
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
        updateHeader();
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        if (shouldUpdateHeader(recyclerView)) {
            updateHeader();
        }
    }

    private boolean shouldUpdateHeader(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (linearLayoutManager == null) return false;

        int pos = linearLayoutManager.findFirstVisibleItemPosition();
        if (pos == currentPosition) return false;

        currentPosition = pos;
        return true;
    }

    private void updateHeader() {
        HeaderRow newHeader = findCurrentHeader(currentPosition);
        if (newHeader == currentHeader) return;
        currentHeader = newHeader;

        headerTitleView.removeAllViews();
        headerColumnsView.removeAllViews();
        if (currentHeader == null) return;

        RecyclerView.ViewHolder vh = HeaderRow.createTitleViewHolder(headerTitleView);
        currentHeader.onBindTitleViewHolder(vh);
        headerTitleView.addView(vh.itemView);

        vh = HeaderRow.createViewHolder(headerColumnsView);

        currentHeader.onBindViewHolder(vh);
        headerColumnsView.addView(vh.itemView);
    }

    private HeaderRow findCurrentHeader(int pos) {
        if (pos >= rows.size()) return null;

        for (int i = pos; i >= 0; i--) {
            Row row = rows.get(i);
            if (row instanceof HeaderRow) {
                return (HeaderRow) row;
            }
        }

        return null;
    }

}
