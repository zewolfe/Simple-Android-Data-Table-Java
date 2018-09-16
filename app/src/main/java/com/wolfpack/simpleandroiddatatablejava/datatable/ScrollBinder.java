package com.wolfpack.simpleandroiddatatablejava.datatable;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ScrollBinder {
    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;

    private boolean listenersEnabled;

    ScrollBinder(RecyclerView leftRecyclerView, RecyclerView rightRecyclerView) {
        this.leftRecyclerView = leftRecyclerView;
        this.rightRecyclerView = rightRecyclerView;
    }

    public void bind() {
        listenersEnabled = true;
        leftRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (!listenersEnabled || dy == 0) return;
                listenersEnabled = false;
                rightRecyclerView.scrollBy(dx, dy);
                listenersEnabled = true;
            }
        });

        rightRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (!listenersEnabled) return;
                listenersEnabled = false;
                if (dy != 0) {
                    leftRecyclerView.scrollBy(dx, dy);
                } else {
                    // view layout was recalculated and redrawn, not scrolled, example: keyboard opened
                    // just jump to the same item
                    scrollToSamePosition();
                }
                listenersEnabled = true;
            }
        });
    }

    private void scrollToSamePosition() {
        LinearLayoutManager arrayLM = (LinearLayoutManager) rightRecyclerView.getLayoutManager();
        LinearLayoutManager titlesLM = (LinearLayoutManager) leftRecyclerView.getLayoutManager();

        int firstItemPos = arrayLM.findFirstVisibleItemPosition();
        int offset = arrayLM.findViewByPosition(firstItemPos).getTop();
        if (titlesLM.findFirstVisibleItemPosition() != firstItemPos) {
            titlesLM.scrollToPositionWithOffset(firstItemPos, offset);
        }
    }
}
