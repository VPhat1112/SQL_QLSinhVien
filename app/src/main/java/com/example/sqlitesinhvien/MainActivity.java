package com.example.sqlitesinhvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitesinhvien.Adapter.SinhVienAdapter;
import com.example.sqlitesinhvien.Adapter.Sinhvien;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Database database;
    RecyclerView recyclerViewSV;
    List<Sinhvien> sinhviens;
    Sinhvien sinhvien;
    Button btnThem;
    private SinhVienAdapter sinhVienAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnThem=(Button)findViewById(R.id.btnThem245);
        //Tạo Database
        database = new Database(this,"Sinhvien.db",null,1);
        //Tạo table Sinhvien
        database.QueryData("CREATE TABLE IF NOT EXISTS SinhVien(" +
                "msv varchar(20) not null primary key," +
                "Tensv nvarchar(100) not null," +
                "Nam int not null)");
        //Insert sinhvien
        //database.QueryData("insert into SinhVien values('2050531200214','Lưu Nguyễn Trọng Nghĩa',3),
        // ('2050531200214','Lưu Nguyễn Trọng Nghĩa',3)");
        /*Cursor data=database.GetData("Select * from SinhVien");
        while(data.moveToNext()){
            String msg=data.getString(1);
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }*/
        recyclerViewSV=findViewById(R.id.rcv_sv);
        sinhVienAdapter= new SinhVienAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewSV.setLayoutManager(linearLayoutManager);
        sinhVienAdapter.setData(getSinhviens());
        recyclerViewSV.setAdapter(sinhVienAdapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogThem();
            }
        });

    }
    @NonNull
    private List<Sinhvien> getSinhviens() {
        List<Sinhvien> sinhvienList= new ArrayList<>();
        Cursor data=database.GetData("Select * from SinhVien");
        while(data.moveToNext()) {
            String Masinhvien = data.getString(0);
            String TenSinhvien = data.getString(1);
            int Nam = data.getInt(2);
            sinhvienList.add(new Sinhvien(R.drawable.pngegg,Masinhvien,TenSinhvien,Nam));
        }
        sinhVienAdapter.notifyDataSetChanged();


        return sinhvienList;
    }
    public void DialogThem(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogcreatesv);

        final EditText edtMaSV = (EditText) dialog.findViewById(R.id.edtaddMasv245);
        final EditText edtTenSV = (EditText) dialog.findViewById(R.id.edtaddTensv245);
        final EditText edtNamHoc = (EditText) dialog.findViewById(R.id.edtaddNamhoc245);
        Button btnThem = (Button) dialog.findViewById(R.id.LưuSV245);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuy245);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MSV = edtMaSV.getText().toString();
                String TenSV = edtTenSV.getText().toString();
                String NamHoc = edtNamHoc.getText().toString();
                database.QueryData("INSERT INTO SinhVien" +
                        " VALUES('"+ MSV +"','"+ TenSV +"',"+ NamHoc +")");
                Toast.makeText(MainActivity.this, "Đã Thêm", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                sinhVienAdapter.setData(getSinhviens());
                recyclerViewSV.setAdapter(sinhVienAdapter);
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void DialogSua(String msv,String Tensv,String Nam){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);

        EditText edtenSV = (EditText) dialog.findViewById(R.id.edtTensv245);
        EditText edNam = (EditText) dialog.findViewById(R.id.edtNamhoc245);
        Button btnXacnhan = (Button) dialog.findViewById(R.id.btn_XacNhan_update245);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_Huy_update245);

        edtenSV.setText(Tensv);
        edNam.setText(Nam);

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Tenmoi = edtenSV.getText().toString().trim();
                String Namhoc = edNam.getText().toString().trim();
                database.QueryData("UPDATE SinhVien SET Tensv = '"+ Tenmoi +"',Nam = "+ Namhoc +" WHERE msv = '"+ msv +"'");
                Toast.makeText(MainActivity.this, "Đã Sửa", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                sinhVienAdapter.setData(getSinhviens());
                recyclerViewSV.setAdapter(sinhVienAdapter);
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void DialogXoa(String MSV){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa Sinh Viên này hay không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.QueryData("DELETE FROM SinhVien WHERE msv = '"+MSV+"'");
                Toast.makeText(MainActivity.this, "Đã Xóa", Toast.LENGTH_SHORT).show();
                sinhVienAdapter.setData(getSinhviens());
                recyclerViewSV.setAdapter(sinhVienAdapter);
            }
        });

        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogXoa.show();
    }
}