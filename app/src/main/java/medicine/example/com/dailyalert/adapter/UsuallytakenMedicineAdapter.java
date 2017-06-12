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
 * Created by Start4me on 6/10/2017.
 */

public class UsuallytakenMedicineAdapter extends RecyclerView.Adapter<UsuallytakenMedicineAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<HashMap<String, String>> al_list;
    HashMap<String, String> hashMap;
    public static ArrayList<HashMap<String,String>> al_listtime;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_medicinename,tv_dosage,tv_time;
        LinearLayout ll_medicine;


        int position;


        public MyViewHolder(View rowView) {
            super(rowView);

            tv_medicinename = (TextView) rowView.findViewById(R.id.tv_medicinename);
            tv_dosage = (TextView) rowView.findViewById(R.id.tv_dosage);
            tv_time = (TextView) rowView.findViewById(R.id.tv_time);
            ll_medicine=(LinearLayout)rowView.findViewById(R.id.ll_medicine);


        }
    }
    public UsuallytakenMedicineAdapter(Context context, ArrayList<HashMap<String, String>> moviesList) {

        this.mContext=context;
        this.al_list = moviesList;

    }
    @Override
    public UsuallytakenMedicineAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_usuallymedicine, parent, false);

        return new UsuallytakenMedicineAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UsuallytakenMedicineAdapter.MyViewHolder holder, final int position) {
        //  Toast.makeText(mContext,""+position,Toast.LENGTH_SHORT).show();
        // hashMap = al_list.get(position);
        HashMap<String,String> hashMap=al_list.get(position);
        holder.tv_medicinename.setText("Medicine name -"+hashMap.get("mname"));
        // holder.tv_date.setText(hashmap.get("time"));
        holder.tv_dosage.setText("Dosage of medicine -"+hashMap.get("dose"));
        holder.tv_time.setText("Time of medicine -"+hashMap.get("reminder1")+" "+hashMap.get("reminder2"));

holder.ll_medicine.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        HashMap<String,String>map=al_list.get(position);
        new AddNewMedicine(map.get("mname"),map.get("dose"),map.get("quantity"),map.get("noofdoseperday")
                ,map.get("reminder1"),map.get("reminder2"),map.get("noofmedicine"),map.get("mid"));
        Intent a=new Intent(mContext,AddNewMedicine.class);
        mContext.startActivity(a);
    }
});

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
