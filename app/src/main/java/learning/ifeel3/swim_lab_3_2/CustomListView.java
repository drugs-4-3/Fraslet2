package learning.ifeel3.swim_lab_3_2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListView extends AppCompatActivity {

    LayoutInflater inflater;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new CustomAdapter(new ArrayList<DataModel>()));
    }

    private class CustomAdapter extends BaseAdapter {

        ArrayList<DataModel> list;

        public CustomAdapter(ArrayList<DataModel> list) {
            this.list = list;
            list.add(new DataModel("Ewa Kowalska", "19", "Kobieta"));
            list.add(new DataModel("Przemyslaw Saleta", "45", "Mezczyzna"));
            list.add(new DataModel("Zbigniew Nowak", "32", "Mezyczyzna"));

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View mv;
            DataModel dm = list.get(position);
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.row_item, null);
            }
            mv = convertView;
            TextView name = (TextView) mv.findViewById(R.id.name);
            name.setText(dm.name);
            TextView sex = (TextView) mv.findViewById(R.id.sex);
            sex.setText(dm.sex);
            TextView age = (TextView) mv.findViewById(R.id.ageNumber);
            age.setText(dm.age);
            return mv;
        }
    }
}
