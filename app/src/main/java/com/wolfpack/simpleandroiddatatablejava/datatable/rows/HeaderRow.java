package com.wolfpack.simpleandroiddatatablejava.datatable.rows;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wolfpack.simpleandroiddatatablejava.R;
import com.wolfpack.simpleandroiddatatablejava.models.Header;

public class HeaderRow extends Row {
    private Header header;

    public HeaderRow(Header header) {
        this.header = header;
    }

    @Override
    public void onBindTitleViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
        HeaderTitleViewHolder vh = (HeaderTitleViewHolder) viewHolder;
        vh.title.setText(this.header.getTitle());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
        HeaderViewHolder vh = (HeaderViewHolder) viewHolder;
        Context context = vh.columnsLayout.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        vh.columnsLayout.removeAllViews();
        for (String column : this.header.getColumns()) {
            TextView textView = (TextView) inflater.
                    inflate(R.layout.column_cell, vh.columnsLayout, false);
            textView.setText(column);
            vh.columnsLayout.addView(textView);
        }
    }

    public static RecyclerView.ViewHolder createTitleViewHolder(@NonNull ViewGroup parent) {
        return new HeaderTitleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.header_title_table_cell, parent, false));
    }

    public static RecyclerView.ViewHolder createViewHolder(@NonNull ViewGroup parent) {
        return new HeaderViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.header_table_row, parent, false));
    }

    static class HeaderTitleViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        HeaderTitleViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textview_table_header_title);
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        LinearLayout columnsLayout;

        HeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            columnsLayout = itemView.findViewById(R.id.header_columns_layout);
        }
    }
}
