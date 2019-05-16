//package com.example.biletiki2.halls;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//
//import com.example.biletiki2.R;
//
//public class ChooseHall extends AppCompatActivity implements View.OnClickListener {
//
//    private FrameLayout grosseSaal, kleinerSaal;
//    private HallClick listener;
//
//    public void setListener(HallClick listener) {
//        this.listener = listener;
//    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.choose_hall);
//        grosseSaal = findViewById(R.id.grosse_saal_frame);
//        kleinerSaal = findViewById(R.id.kleiner_saal_frame);
//        grosseSaal.setOnClickListener(this);
//        kleinerSaal.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        if (id == R.id.grosse_saal_frame){
//            hallId(id);
//        }else if (id == R.id.kleiner_saal_frame){
//            hallId(id);
//        }
//    }
//
//    private void hallId(int id) {
//        if (listener != null) {
//            listener.onHallClick(id);
//        }
//    }
//
//    interface HallClick{
//        void onHallClick(int id);
//    }
//}
