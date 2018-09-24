package com.wolfpack.simpleandroiddatatablejava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wolfpack.simpleandroiddatatablejava.datatable.DataTable;
import com.wolfpack.simpleandroiddatatablejava.datatable.rows.DessertRow;
import com.wolfpack.simpleandroiddatatablejava.datatable.rows.HeaderRow;
import com.wolfpack.simpleandroiddatatablejava.datatable.rows.Row;
import com.wolfpack.simpleandroiddatatablejava.models.Data;
import com.wolfpack.simpleandroiddatatablejava.models.Dessert;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Row> rows = generateRows();
        DataTable dataTable = findViewById(R.id.dataTable);
        dataTable.setRows(rows);
    }

    private List<Row> generateRows() {
        List<Row> rows = new ArrayList<>();
        String jsonData = loadJSONFromAsset("data.json");
        Type type = new TypeToken<List<Data>>(){}.getType();
        List<Data> data = new Gson().fromJson(jsonData, type);

        for (Data datum : data) {
            rows.add(new HeaderRow(datum.getHeader()));

            for (Dessert dessert: datum.getData()) {
                rows.add(new DessertRow(dessert));
            }
        }

        return rows;
    }

    public String loadJSONFromAsset(String filename) {
        String json;
        try {
            InputStream is = this.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
