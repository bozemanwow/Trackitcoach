package com.hippie.trackitcoach.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hippie.trackitcoach.R;
import com.hippie.trackitcoach.models.ReportListItem;

import java.util.Arrays;

/**
 * Created by Hippie on 8/23/2015.
 */
public class ReportListAdapter extends BaseAdapter {

    private Context mContext;
    private ReportListItem mReportListItems[];

    public ReportListAdapter(Context context, ReportListItem[] reportItems) {
        mContext = context;
        mReportListItems = Arrays.copyOf(reportItems, reportItems.length);
    }

    @Override
    public int getCount() {
        return mReportListItems.length;
    }

    @Override
    public Object getItem(int position) {
        return mReportListItems[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.report_list_item, null);
            holder = new ViewHolder();
            holder.eventName = (TextView) convertView.findViewById(R.id.all_eventName);
            holder.athleteName = (TextView) convertView.findViewById(R.id.all_athlete_name);
            holder.date = (TextView) convertView.findViewById(R.id.all_date);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        ReportListItem listItem = mReportListItems[position];

        holder.eventName.setText(listItem.getEventName());
        holder.athleteName.setText(listItem.getAthleteName());
        holder.date.setText(listItem.getDate());

        return convertView;
    }

    private static class ViewHolder {
        TextView eventName;
        TextView athleteName;
        TextView date;
    }
}

