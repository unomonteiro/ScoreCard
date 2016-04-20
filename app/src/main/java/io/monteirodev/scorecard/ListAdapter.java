package io.monteirodev.scorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    //Fields
    private final Context mContext;
    private final Hole[] mHoles;

    // we need context and holes
    public ListAdapter (Context context, Hole[] holes){
        mContext = context;
        mHoles = holes;
    }

    @Override
    public int getCount() {
        return mHoles.length;
    }

    @Override
    public Object getItem(int position) {
        return mHoles[position];
    }

    @Override
    public long getItemId(int position) {
        return 0; // Not Implemented
    }

    /** needs a viewHolder class */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null){
            // if null set it to a view
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);

            // setting the viewHolder properties
            holder = new ViewHolder();
            holder.holeLabel = (TextView) convertView.findViewById(R.id.holeLabel);
            holder.strokeCount = (TextView) convertView.findViewById(R.id.strokeCount);
            holder.removeStrokeButton = (Button) convertView.findViewById(R.id.removeStrokeButton);
            holder.addStrokeButton = (Button) convertView.findViewById(R.id.addStrokeButton);

            // add view to viewHolder as a TAG
            convertView.setTag(holder);
        } else {
            // if convert view != null
            // get the holder from convertView
            holder = (ViewHolder) convertView.getTag();
        }

        // now we surely have a holder, lets set properties
        holder.holeLabel.setText(mHoles[position].getLabel());
        holder.strokeCount.setText(mHoles[position].getStrokeCount() + "");
        holder.removeStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int updatedStrokeCount = mHoles[position].getStrokeCount() -1;
                if (updatedStrokeCount < 0) updatedStrokeCount = 0;
                mHoles[position].setStrokeCount(updatedStrokeCount);
                holder.strokeCount.setText(updatedStrokeCount+"");
            }
        });
        holder.addStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int updatedStrokeCount = mHoles[position].getStrokeCount() + 1;
                mHoles[position].setStrokeCount(updatedStrokeCount);
                holder.strokeCount.setText(updatedStrokeCount + "");
            }
        });

        //return the view that has our viewHolder
        return convertView;
    }

    /** Just holds the views: 2 textViews 2 buttons */
    private static class ViewHolder {
        TextView holeLabel;
        TextView strokeCount;
        Button removeStrokeButton;
        Button addStrokeButton;
    }
}
