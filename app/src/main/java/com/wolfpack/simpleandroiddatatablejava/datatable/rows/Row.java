package com.wolfpack.simpleandroiddatatablejava.datatable.rows;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public abstract class Row {

    public abstract void onBindTitleViewHolder(@NonNull RecyclerView.ViewHolder viewHolder);

    public abstract void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder);
}
