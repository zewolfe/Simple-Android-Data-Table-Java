package com.wolfpack.simpleandroiddatatablejava.datatable;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.HorizontalScrollView;

import com.wolfpack.simpleandroiddatatablejava.R;
import com.wolfpack.simpleandroiddatatablejava.datatable.adapters.DataAdapter;
import com.wolfpack.simpleandroiddatatablejava.datatable.adapters.TitlesAdapter;
import com.wolfpack.simpleandroiddatatablejava.datatable.rows.Row;

import java.util.List;

public class DataTable extends ConstraintLayout {
    private TitlesAdapter titlesAdapter;
    private DataAdapter dataAdapter;

    public DataTable(Context context) {
        super(context);
        init(context);
    }

    public DataTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DataTable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.data_table, this, true);
        }
        RecyclerView titlesRecyclerView = findViewById(R.id.titlesRecyclerView);
        titlesRecyclerView.setHasFixedSize(true);
        titlesRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        titlesAdapter = new TitlesAdapter();
        titlesRecyclerView.setAdapter(titlesAdapter);

        RecyclerView dataRecyclerView = findViewById(R.id.dataRecyclerView);
        dataRecyclerView.setHasFixedSize(true);
        dataRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        dataAdapter = new DataAdapter();
        HorizontalScrollView horizontalScrollView = findViewById(R.id.horizontalScrollView);
        horizontalScrollView.addOnLayoutChangeListener(dataAdapter);
        dataRecyclerView.setAdapter(dataAdapter);

        ScrollBinder scrollBinder = new ScrollBinder(titlesRecyclerView, dataRecyclerView);
        scrollBinder.bind();
    }

    public void setRows(List<Row> rows) {
        titlesAdapter.setRows(rows);
        dataAdapter.setRows(rows);
    }
}
