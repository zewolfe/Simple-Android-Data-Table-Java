package com.wolfpack.simpleandroiddatatablejava.datatable.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.wolfpack.simpleandroiddatatablejava.datatable.rows.DessertRow;
import com.wolfpack.simpleandroiddatatablejava.datatable.rows.HeaderRow;
import com.wolfpack.simpleandroiddatatablejava.datatable.rows.Row;

import java.util.ArrayList;
import java.util.List;

public abstract class RowsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // The different types of rows that make up our data table
    private final static List<Class<? extends Row>> types = new ArrayList<>();

    private List<Row> rows;

    static {
        types.add(HeaderRow.class);
        types.add(DessertRow.class);
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return rows.get(position).hashCode();
    }

    @Override
    public int getItemViewType(int position) {
        return types.indexOf(rows.get(position).getClass());
    }

    @Override
    public int getItemCount() {
        return rows == null ? 0 : rows.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return onCreateViewHolder(viewGroup, types.get(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        onBindViewHolder(viewHolder, rows.get(position));
    }

    public abstract RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, Class<? extends Row> rowType);

    public abstract void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, Row row);
}
