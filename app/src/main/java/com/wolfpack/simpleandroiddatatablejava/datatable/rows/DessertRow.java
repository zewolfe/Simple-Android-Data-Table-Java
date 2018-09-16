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
import com.wolfpack.simpleandroiddatatablejava.models.Dessert;

public class DessertRow extends Row {
    private Dessert dessert;

    public DessertRow(Dessert dessert) {
        this.dessert = dessert;
    }

    @Override
    public void onBindTitleViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
        DessertTitleViewHolder vh = (DessertTitleViewHolder) viewHolder;
        vh.titleView.setText(this.dessert.getTitle());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
        DessertViewHolder vh = (DessertViewHolder) viewHolder;
        Context context = vh.columnsLayout.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        vh.columnsLayout.removeAllViews();
        for (Float data : dessert.getData()) {
            TextView textView = (TextView) inflater.
                    inflate(R.layout.column_cell, vh.columnsLayout, false);
            textView.setText(String.valueOf(data));
            vh.columnsLayout.addView(textView);
        }
    }

    public static RecyclerView.ViewHolder createTitleViewHolder (@NonNull ViewGroup parent) {
        return new DessertTitleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dessert_title_table_row, parent, false));
    }

    public static RecyclerView.ViewHolder createViewHolder (@NonNull ViewGroup parent) {
        return new DessertViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dessert_table_row, parent, false));
    }


    static class DessertTitleViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;

        DessertTitleViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.textview_table_dessert_title);
        }
    }

    static class DessertViewHolder extends RecyclerView.ViewHolder {
        LinearLayout columnsLayout;

        DessertViewHolder(@NonNull View itemView) {
            super(itemView);

            columnsLayout = itemView.findViewById(R.id.dessert_columns_layout);
        }
    }
}
