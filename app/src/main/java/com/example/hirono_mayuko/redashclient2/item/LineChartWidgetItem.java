package com.example.hirono_mayuko.redashclient2.item;


import android.view.View;

import com.example.hirono_mayuko.redashclient2.ChartHelper;
import com.example.hirono_mayuko.redashclient2.widget.LineChartWidget;
import com.example.hirono_mayuko.redashclient2.activity.MainActivity;
import com.example.hirono_mayuko.redashclient2.R;
import com.example.hirono_mayuko.redashclient2.databinding.ItemLineChartBinding;
import com.xwray.groupie.Item;

import java.util.HashMap;

/**
 * Created by hirono-mayuko on 2017/04/24.
 */

public class LineChartWidgetItem extends Item<ItemLineChartBinding> {
    public String mWidgetId;
    public LineChartWidget mWidget;
    public MainActivity mainActivity;

    public LineChartWidgetItem(String widgetId, HashMap<String, String> visualData,  MainActivity activity){
        super();
        mWidgetId = widgetId;
        mWidget = new LineChartWidget(visualData, activity, this);
        mainActivity = activity;
    }

    @Override
    public void bind(ItemLineChartBinding binding, int position){
        binding.setLineChartWidget(mWidget);

        if(mWidget.mLineData == null) {
            return;
        }

        ChartHelper.lineChartAxisOptions(binding.chart, mWidget.maxTime, mWidget.minTime);
        binding.chart.setData(mWidget.mLineData);
        binding.chart.invalidate();
        binding.progressBar.setVisibility(View.GONE);
        binding.chart.setVisibility(View.VISIBLE);
    }

    @Override public int getLayout() {
        return R.layout.item_line_chart;
    }

    public void notifyWidgetChanged(){
        mainActivity.notifyItemChanged(this);
    }
}
