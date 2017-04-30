package learning.ifeel3.swim_lab_3_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Michał on 27.04.2017.
 */

public class CustomAdapter extends BaseAdapter {

    ArrayList<DataModel> list = new ArrayList<>();


    public CustomAdapter() {

    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

