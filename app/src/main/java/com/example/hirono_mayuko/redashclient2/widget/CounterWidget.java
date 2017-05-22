package com.example.hirono_mayuko.redashclient2.widget;

import com.example.hirono_mayuko.redashclient2.model.Dashboard;
import com.example.hirono_mayuko.redashclient2.activity.MainActivity;
import com.example.hirono_mayuko.redashclient2.item.CounterWidgetItem;
import com.example.hirono_mayuko.redashclient2.model.Widget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by hirono-mayuko on 2017/04/24.
 */

public class CounterWidget extends Widget {
    private HashMap<String, String> mVisualData;
    private MainActivity mainActivity;
    private CounterWidgetItem mItem;

    public String mValue;
    public String mVisualName;
    public String mQueryName;

    public CounterWidget(HashMap<String, String> visualData, MainActivity activity, CounterWidgetItem item){
        mVisualData = visualData;
        mainActivity = activity;
        mItem = item;
        mainActivity.queryData(visualData.get(Dashboard.QUERY_ID), this);
    }

    public void setData(JSONArray dataArray){
        String counterColName = mVisualData.get(Dashboard.COUNTER_COL_NAME);
        mVisualName = mVisualData.get(Dashboard.VISUAL_NAME);
        mQueryName = mVisualData.get(Dashboard.QUERY_NAME);

        try {
            JSONObject obj = dataArray.getJSONObject(0);
            int val = Integer.parseInt(obj.getString(counterColName));
            mValue = String.format(Locale.US, "%1$,3d", val);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mItem.notifyWidgetChanged();
    }

    public void callback(JSONArray dataArray){
        this.setData(dataArray);
    }
}
