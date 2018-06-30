package com.example.reeve.myfirstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseEditorCustomEListAdapter extends BaseExpandableListAdapter{
    private ArrayList<StructGradeType> hwList = new ArrayList<>();
    private Context ctx;

    CourseEditorCustomEListAdapter(Context ctx, ArrayList<StructGradeType> hwList){
        this.ctx = ctx;
        this.hwList = hwList;
    }


    @Override
    public int getGroupCount() {
        return hwList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return hwList.get(i).getAssignlist().size();
    }

    @Override
    public Object getGroup(int i) {
        return hwList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return hwList.get(i).getAssignlist().get(i1);
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
    public View getGroupView(int i, boolean b, View convertView, ViewGroup parent) {
        String title = hwList.get(i).getName();
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.hw_types, null);
        }

        TextView textView = convertView.findViewById(R.id.hwType);
        textView.setText(title);

        return convertView;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View convertView, ViewGroup viewGroup) {

        String title = (String) hwList.get(i).getAssignlist().get(i1).getName();
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.assign_types, null);

            Button button = (Button) convertView.findViewById(R.id.assignRemoveBut);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hwList.get(i).removeAssign(i1);
                    notifyDataSetChanged();
                }
            });
        }
        TextView textView = (TextView) convertView.findViewById(R.id.assignName);
        textView.setText(title);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
