package com.wolfpack.simpleandroiddatatablejava.datatable.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.wolfpack.simpleandroiddatatablejava.datatable.rows.DessertRow;
import com.wolfpack.simpleandroiddatatablejava.datatable.rows.HeaderRow;
import com.wolfpack.simpleandroiddatatablejava.datatable.rows.Row;

public class TitlesAdapter extends RowsAdapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, Class<? extends Row> rowType) {
        if (rowType.equals(HeaderRow.class)) {
            return HeaderRow.createTitleViewHolder(viewGroup);
        } else {
            return DessertRow.createTitleViewHolder(viewGroup);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, Row row) {
        row.onBindTitleViewHolder(viewHolder);
    }
}
