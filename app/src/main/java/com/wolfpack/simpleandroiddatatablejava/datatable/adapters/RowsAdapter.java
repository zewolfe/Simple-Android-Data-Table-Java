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

    // Return the size of the data set (invoked by the layout manager)
    // If this isn't overridden to be the length of your data set your recyclerView will show up empty
    @Override
    public int getItemCount() {
        return rows == null ? 0 : rows.size();
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // returns our proxy onCreateViewHolder to make it easier to check the view type
        return onCreateViewHolder(viewGroup, types.get(viewType));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        onBindViewHolder(viewHolder, rows.get(position));
    }

    /*  Will be implemented by child adapters so they can create
        different RecyclerView.ViewHolder objects based on the item view type.
    */
    public abstract RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, Class<? extends Row> rowType);

    /**
     * Proxy method to be implemented so that children can update the view holder of the given row
     * @param viewHolder The type of RecyclerView.ViewHolder to populate
     * @param row The Row in our data set to update.
     */
    public abstract void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, Row row);
}
