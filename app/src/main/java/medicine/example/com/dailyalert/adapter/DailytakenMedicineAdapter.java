package medicine.example.com.dailyalert.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import medicine.example.com.dailyalert.R;
import medicine.example.com.dailyalert.addnewmedicine.AddNewMedicine;

/**
 * Created by Start4me on 6/11/2017.
 */

public class DailytakenMedicineAdapter extends RecyclerView.Adapter<DailytakenMedicineAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<HashMap<String, String>> al_list;
    HashMap<String, String> hashMap;
    public static ArrayList<HashMap<String,String>> al_listtime;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_medicinename,tv_dosage,tv_time;



        int position;


        public MyViewHolder(View rowView) {
            super(rowView);

            tv_medicinename = (TextView) rowView.findViewById(R.id.tv_medicinename);
            tv_dosage = (TextView) rowView.findViewById(R.id.tv_dosage);
            tv_time = (TextView) rowView.findViewById(R.id.tv_time);


        }
    }
    public DailytakenMedicineAdapter(Context context, ArrayList<HashMap<String, String>> moviesList) {

        this.mContext=context;
        this.al_list = moviesList;

    }
    @Override
    public DailytakenMedicineAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_usuallymedicine, parent, false);

        return new DailytakenMedicineAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DailytakenMedicineAdapter.MyViewHolder holder, final int position) {
        //  Toast.makeText(mContext,""+position,Toast.LENGTH_SHORT).show();
        // hashMap = al_list.get(position);
        HashMap<String,String> hashMap=al_list.get(position);
        holder.tv_medicinename.setText("Medicine name -"+hashMap.get("mname"));
        // holder.tv_date.setText(hashmap.get("time"));
        holder.tv_dosage.setText("Dosage of medicine -"+hashMap.get("dose"));
        holder.tv_time.setText("Time of medicine -"+hashMap.get("reminder1")+" "+hashMap.get("reminder2"));


    }

    @Override
    public int getItemCount() {

        return al_list.size();//al_list.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
