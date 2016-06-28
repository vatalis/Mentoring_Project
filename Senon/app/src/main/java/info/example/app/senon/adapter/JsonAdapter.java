// fungsi pada class ini adalah sebagai controller penghubuung antara item_activity dengan view_activity serta json model
// dimana sebuah data yang akan ditarik dari sebuah database "android_api" dalam bentuk json yang akan ditampilkan dalam
// bentuk list view
package info.example.app.senon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import info.example.app.senon.JsonModel;
import info.example.app.senon.R;

/**
 * Created by AAfif on 6/6/2016.
 */
public class JsonAdapter extends BaseAdapter{
    ArrayList<JsonModel> jsonModels;
    Context context;

    public JsonAdapter(Context context, ArrayList<JsonModel> jsonModels){
        this.jsonModels = jsonModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return jsonModels.size();
    }

    @Override
    public Object getItem(int position) {
        return jsonModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.view_item, null);

            TextView id = (TextView) convertView.findViewById(R.id.data_id);
            TextView name = (TextView) convertView.findViewById(R.id.data_name);
            TextView email = (TextView) convertView.findViewById(R.id.data_email);

            id.setText(jsonModels.get(position).getId());
            name.setText(jsonModels.get(position).getName());
            email.setText(jsonModels.get(position).getEmail());
        }
        return convertView;
    }
}
