package com.hippie.trackitcoach.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hippie.trackitcoach.R;
import com.hippie.trackitcoach.models.DB_Athlete;

/**
 * Created by Hippie on 8/22/2015.
 */
public class AthleteNameAdapter extends BaseAdapter {

    private Context mContext;
    private DB_Athlete[] mDBAthletes;

    public AthleteNameAdapter(Context context, DB_Athlete[] DBAthletes) {
        mContext = context;
        mDBAthletes = DBAthletes;
    }

    @Override
    public int getCount() {
        return mDBAthletes.length;
    }

    @Override
    public Object getItem(int position) {
        return mDBAthletes[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.athlete_list_item, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.athlete_name_view);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        DB_Athlete DBAthlete = mDBAthletes[position];

        holder.nameView.setText(DBAthlete.getFirstName() + " " + DBAthlete.getLastName());

        return convertView;
    }

    private static class ViewHolder {
        TextView nameView;

    }
}
