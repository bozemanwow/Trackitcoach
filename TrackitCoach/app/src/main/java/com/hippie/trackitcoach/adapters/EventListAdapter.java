package com.hippie.trackitcoach.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hippie.trackitcoach.R;

/**
 * Created by Hippie on 8/23/2015.
 */
public class EventListAdapter extends BaseAdapter {


    private Context mContext;
    private String[] mEvents;

    public EventListAdapter(Context context, String[] events) {
        mContext = context;
        mEvents = events;
    }

    @Override
    public int getCount() {
        return mEvents.length;
    }

    @Override
    public Object getItem(int position) {
        return mEvents[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.event_list_item, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.event_list_view);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        String event = mEvents[position];

        holder.nameView.setText(event);

        return convertView;
    }

    private static class ViewHolder {
        TextView nameView;
    }
}
