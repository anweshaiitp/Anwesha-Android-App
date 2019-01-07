package info.anwesha2k18.iitp.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import info.anwesha2k18.iitp.R;

public class
ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> mListDataHeader;
    private HashMap<String, List<String>> mListHashMap;

    public ExpandableListAdapter(Context context, List<String> listDataHandler, HashMap<String, List<String>> listHashMap) {
        this.mContext = context;
        this.mListDataHeader = listDataHandler;
        this.mListHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return mListDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mListHashMap.get(mListDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int i) {
        return mListDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mListHashMap.get(mListDataHeader.get(i)).get(i1);  //i=group item; i1=child item;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        final String childText = (String) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }
        TextView txtListChild = (TextView) view.findViewById(R.id.lblListitem);
        txtListChild.setText(childText);
        return view;

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group, null);
        }
        TextView lbListHeader = (TextView) view.findViewById(R.id.lblListHeader);
        lbListHeader.setTypeface(null, Typeface.BOLD);
        lbListHeader.setText(headerTitle);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}


