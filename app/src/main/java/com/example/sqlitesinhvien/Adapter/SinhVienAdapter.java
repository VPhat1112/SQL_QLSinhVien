package com.example.sqlitesinhvien.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import com.example.sqlitesinhvien.MainActivity;
import com.example.sqlitesinhvien.R;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.DataViewHolder> {
    //Dữ liệu hiển thị là danh sách sinh viên
    private List<Sinhvien> sinhviens;
    //Lưu context để dễ dàng truy cập
    private MainActivity context;

    public SinhVienAdapter(MainActivity context) {
        this.context = context;
    }

    public void setData(List<Sinhvien> list){
        this.sinhviens=list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (sinhviens!=null)
            return sinhviens.size();
        else return 0;
    }

    @NonNull
    @Override
    public SinhVienAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sinhvien, parent, false);

        return new DataViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Sinhvien sinhvien=sinhviens.get(position);
        if(sinhvien==null){
            return;
        }
        holder.Msv.setText(sinhvien.getMsv());
        holder.Tensv.setText(sinhvien.getTen());
        holder.Nam.setText(String.valueOf(sinhvien.getNam()));
        holder.imageView.setImageResource(sinhvien.getImageView());
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogSua(sinhvien.getMsv(),sinhvien.getTen(),String.valueOf(sinhvien.getNam()));
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoa(sinhvien.getMsv());
            }
        });
    }


    public static class DataViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView Msv;
        private TextView Tensv,Nam;
        private ImageView update,remove;

        public DataViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.images);
            Msv=(TextView) itemView.findViewById(R.id.MSV);
            Tensv= (TextView)itemView.findViewById(R.id.TENSV);
            Nam=(TextView)itemView.findViewById(R.id.Namhoc);
            update=(ImageView) itemView.findViewById(R.id.update);
            remove=(ImageView)itemView.findViewById(R.id.remove);
        }

    }






}
