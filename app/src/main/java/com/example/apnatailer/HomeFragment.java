package com.example.apnatailer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {


    String[] Name={"Bhavin","Keval","Abhay"};
    String[] Mobile={"78457845784","7845123690"};
    ListView listviewHome;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        listviewHome=(ListView)findViewById(R.id.listviewHome);
        CustomAdapter customAdapter=new CustomAdapter();
        listviewHome.setAdapter(customAdapter);
    }

    private void setContentView(int fragment_home) {
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return Name.length;
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
            convertView=getLayoutInflater().inflate(R.layout.homecustomelayout,null);
            TextView tvCName=convertView.findViewById(R.id.tvCName);
            TextView tvCMobile= convertView.findViewById(R.id.tvCMobile);

            tvCName.setText(Name[position]);
            tvCMobile.setText(Mobile[position]);

            return convertView;
        }
    }
}
