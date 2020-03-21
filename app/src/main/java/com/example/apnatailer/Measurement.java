package com.example.apnatailer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Measurement extends AppCompatActivity {
    int[] Images={R.drawable.login,R.drawable.login,R.drawable.login};

    String[] Name={"Neck","Head","Neck1"};
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);

        listView=(ListView)findViewById(R.id.listView);
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);

    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return Images.length;
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
           convertView=getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView mImage=convertView.findViewById(R.id.mImage);
            TextView mName= convertView.findViewById(R.id.mName);
            mImage.setImageResource(Images[position]);
            mName.setText(Name[position]);

            return convertView;
        }
    }

}
