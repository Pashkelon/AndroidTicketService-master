package com.example.biletiki2.halls.grosse_saal.areas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.biletiki2.R;
import com.example.biletiki2.UIclasses.MyView;
import com.example.biletiki2.UIclasses.ShoppingCart;
import com.example.biletiki2.data.OkHttpProvider;
import com.example.biletiki2.data.StoreProvider;
import com.example.biletiki2.data.dto.ListLockedSeats;
import com.example.biletiki2.data.dto.LockedSeats;
import com.example.biletiki2.data.dto.PriceRanges;
import com.example.biletiki2.halls.kleiner_saal.KleinerSaal;

import java.util.List;

import interfaces.LockSeat;

public class SecondArea extends AppCompatActivity implements View.OnClickListener, LockSeat {

    private MyView r1_4L, r1_5L, r1_6L, r1_7L, r1_8L, r1_9L, r1_10L, r1_11L, r1_10R, r1_9R, r1_8R, r1_7R, r1_6R, r1_5R, r1_4R;
    private MyView r2_5L, r2_6L, r2_7L, r2_8L, r2_9L, r2_10L, r2_11L, r2_12L, r2_12R, r2_11R, r2_10R, r2_9R, r2_8R, r2_7R, r2_6R, r2_5R;
    private MyView r3_6L, r3_7L, r3_8L, r3_9L, r3_10L, r3_11L, r3_12L, r3_13L, r3_14L, r3_13R, r3_12R, r3_11R, r3_10R, r3_9R, r3_8R, r3_7R, r3_6R;
    private MyView r4_7L, r4_8L, r4_9L, r4_10L, r4_11L, r4_12L, r4_13L, r4_14L, r4_15L, r4_15R, r4_14R, r4_13R, r4_12R, r4_11R, r4_10R, r4_9R, r4_8R, r4_7R;
    private MyView r5_8L, r5_9L, r5_10L, r5_11L, r5_12L, r5_13L, r5_14L, r5_15L, r5_16L, r5_17L, r5_16R, r5_15R, r5_14R, r5_13R, r5_12R, r5_11R, r5_10R, r5_9R, r5_8R;
    private MyView r6_9L, r6_10L, r6_11L, r6_12L, r6_13L, r6_14L, r6_15L, r6_16L, r6_17L, r6_18L, r6_18R, r6_17R, r6_16R, r6_15R, r6_14R, r6_13R, r6_12R, r6_11R, r6_10R, r6_9R;
    private MyView r7_10L, r7_11L, r7_12L, r7_13L, r7_14L, r7_15L, r7_16L, r7_17L, r7_18L, r7_19L, r7_20L, r7_19R, r7_18R, r7_17R, r7_16R, r7_15R, r7_14R, r7_13R, r7_12R, r7_11R, r7_10R;
    private MyView r8_11L, r8_12L, r8_13L, r8_14L, r8_15L, r8_16L, r8_17L, r8_18L, r8_19L, r8_20L, r8_21L, r8_21R, r8_20R, r8_19R, r8_18R, r8_17R, r8_16R, r8_15R, r8_14R, r8_13R, r8_12R, r8_11R;
    private MyView r9_11L, r9_12L, r9_13L, r9_14L, r9_15L, r9_16L, r9_17L, r9_18L, r9_19L, r9_20L, r9_21L, r9_22L, r9_21R, r9_20R, r9_19R, r9_18R, r9_17R, r9_16R, r9_15R, r9_14R, r9_13R, r9_12R, r9_11R;
    private MyView r10_10L, r10_11L, r10_12L, r10_13L, r10_14L, r10_15L, r10_16L, r10_17L, r10_18L, r10_19L, r10_20L, r10_20R, r10_19R, r10_18R, r10_17R, r10_16R, r10_15R, r10_14R, r10_13R, r10_12R, r10_11R, r10_10R;
    private MyView r11_9L, r11_10L, r11_11L, r11_12L, r11_13L, r11_14L, r11_15L, r11_16L, r11_17L, r11_18L, r11_19L, r11_18R, r11_17R, r11_16R, r11_15R, r11_14R, r11_13R, r11_12R, r11_11R, r11_10R, r11_9R;
    private MyView r12_8L, r12_9L, r12_10L, r12_11L, r12_12L, r12_13L, r12_14L, r12_15L, r12_16L, r12_17L, r12_17R, r12_16R, r12_15R, r12_14R, r12_13R, r12_12R, r12_11R, r12_10R, r12_9R, r12_8R;
    private MyView r13_7L, r13_8L, r13_9L, r13_10L, r13_11L, r13_12L, r13_13L, r13_14L, r13_15L, r13_16L, r13_17L, r13_16R, r13_15R, r13_14R, r13_13R, r13_12R, r13_11R, r13_10R, r13_9R, r13_8R, r13_7R;
    private MyView r14_6L, r14_7L, r14_8L, r14_9L, r14_10L, r14_11L, r14_12L, r14_13L, r14_14L, r14_15L, r14_16L, r14_16R, r14_15R, r14_14R, r14_13R, r14_12R, r14_11R, r14_10R, r14_9R, r14_8R, r14_7R, r14_6R;
    private MyView r15_1L, r15_2L, r15_3L, r15_4L, r15_5L, r15_6L, r15_7L, r15_8L, r15_9L, r15_10L, r15_11L, r15_12L, r15_11R, r15_10R, r15_9R, r15_8R, r15_7R, r15_6R, r15_5R, r15_4R, r15_3R, r15_2R, r15_1R;

    private ImageView doneImg;
    private int id;
    private List<LockedSeats> lockedSeatsList;
    private List<PriceRanges> priceRangesList;
    private MyView[] arr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_area);

        //row 1
        r1_4L = findViewById(R.id.R1_4L);
        r1_5L = findViewById(R.id.R1_5L);
        r1_6L = findViewById(R.id.R1_6L);
        r1_7L = findViewById(R.id.R1_7L);
        r1_8L = findViewById(R.id.R1_8L);
        r1_9L = findViewById(R.id.R1_9L);
        r1_10L = findViewById(R.id.R1_10L);
        r1_11L = findViewById(R.id.R1_11L);
        r1_10R = findViewById(R.id.R1_10R);
        r1_9R = findViewById(R.id.R1_9R);
        r1_8R = findViewById(R.id.R1_8R);
        r1_7R = findViewById(R.id.R1_7R);
        r1_6R = findViewById(R.id.R1_6R);
        r1_5R = findViewById(R.id.R1_5R);
        r1_4R = findViewById(R.id.R1_4R);

        //row 2
        r2_5L = findViewById(R.id.R2_5L);
        r2_6L = findViewById(R.id.R2_6L);
        r2_7L = findViewById(R.id.R2_7L);
        r2_8L = findViewById(R.id.R2_8L);
        r2_9L = findViewById(R.id.R2_9L);
        r2_10L = findViewById(R.id.R2_10L);
        r2_11L = findViewById(R.id.R2_11L);
        r2_12L = findViewById(R.id.R2_12L);
        r2_12R = findViewById(R.id.R2_12R);
        r2_11R = findViewById(R.id.R2_11R);
        r2_10R = findViewById(R.id.R2_10R);
        r2_9R = findViewById(R.id.R2_9R);
        r2_8R = findViewById(R.id.R2_8R);
        r2_7R = findViewById(R.id.R2_7R);
        r2_6R = findViewById(R.id.R2_6R);
        r2_5R = findViewById(R.id.R2_5R);

        //row 3
        r3_6L = findViewById(R.id.R3_6L);
        r3_7L = findViewById(R.id.R3_7L);
        r3_8L = findViewById(R.id.R3_8L);
        r3_9L = findViewById(R.id.R3_9L);
        r3_10L = findViewById(R.id.R3_10L);
        r3_11L = findViewById(R.id.R3_11L);
        r3_12L = findViewById(R.id.R3_12L);
        r3_13L = findViewById(R.id.R3_13L);
        r3_14L = findViewById(R.id.R3_14L);
        r3_13R = findViewById(R.id.R3_13R);
        r3_12R = findViewById(R.id.R3_12R);
        r3_11R = findViewById(R.id.R3_11R);
        r3_10R = findViewById(R.id.R3_10R);
        r3_9R = findViewById(R.id.R3_9R);
        r3_8R = findViewById(R.id.R3_8R);
        r3_7R = findViewById(R.id.R3_7R);
        r3_6R = findViewById(R.id.R3_6R);

        //row 4
        r4_7L = findViewById(R.id.R4_7L);
        r4_8L = findViewById(R.id.R4_8L);
        r4_9L = findViewById(R.id.R4_9L);
        r4_10L = findViewById(R.id.R4_10L);
        r4_11L = findViewById(R.id.R4_11L);
        r4_12L = findViewById(R.id.R4_12L);
        r4_13L = findViewById(R.id.R4_13L);
        r4_14L = findViewById(R.id.R4_14L);
        r4_15L = findViewById(R.id.R4_15L);
        r4_15R = findViewById(R.id.R4_15R);
        r4_14R = findViewById(R.id.R4_14R);
        r4_13R = findViewById(R.id.R4_13R);
        r4_12R = findViewById(R.id.R4_12R);
        r4_11R = findViewById(R.id.R4_11R);
        r4_10R = findViewById(R.id.R4_10R);
        r4_9R = findViewById(R.id.R4_9R);
        r4_8R = findViewById(R.id.R4_8R);
        r4_7R = findViewById(R.id.R4_7R);

        //row 5
        r5_8L = findViewById(R.id.R5_8L);
        r5_9L = findViewById(R.id.R5_9L);
        r5_10L = findViewById(R.id.R5_10L);
        r5_11L = findViewById(R.id.R5_11L);
        r5_12L = findViewById(R.id.R5_12L);
        r5_13L = findViewById(R.id.R5_13L);
        r5_14L = findViewById(R.id.R5_14L);
        r5_15L = findViewById(R.id.R5_15L);
        r5_16L = findViewById(R.id.R5_16L);
        r5_17L = findViewById(R.id.R5_17L);
        r5_16R = findViewById(R.id.R5_16R);
        r5_15R = findViewById(R.id.R5_15R);
        r5_14R = findViewById(R.id.R5_14R);
        r5_13R = findViewById(R.id.R5_13R);
        r5_12R = findViewById(R.id.R5_12R);
        r5_11R = findViewById(R.id.R5_11R);
        r5_10R = findViewById(R.id.R5_10R);
        r5_9R = findViewById(R.id.R5_9R);
        r5_8R = findViewById(R.id.R5_8R);

        //row 6
        r6_9L = findViewById(R.id.R6_9L);
        r6_10L = findViewById(R.id.R6_10L);
        r6_11L = findViewById(R.id.R6_11L);
        r6_12L = findViewById(R.id.R6_12L);
        r6_13L = findViewById(R.id.R6_13L);
        r6_14L = findViewById(R.id.R6_14L);
        r6_15L = findViewById(R.id.R6_15L);
        r6_16L = findViewById(R.id.R6_16L);
        r6_17L = findViewById(R.id.R6_17L);
        r6_18L = findViewById(R.id.R6_18L);
        r6_18R = findViewById(R.id.R6_18R);
        r6_17R = findViewById(R.id.R6_17R);
        r6_16R = findViewById(R.id.R6_16R);
        r6_15R = findViewById(R.id.R6_15R);
        r6_14R = findViewById(R.id.R6_14R);
        r6_13R = findViewById(R.id.R6_13R);
        r6_12R = findViewById(R.id.R6_12R);
        r6_11R = findViewById(R.id.R6_11R);
        r6_10R = findViewById(R.id.R6_10R);
        r6_9R = findViewById(R.id.R6_9R);

        //row 7
        r7_10L = findViewById(R.id.R7_10L);
        r7_11L = findViewById(R.id.R7_11L);
        r7_12L = findViewById(R.id.R7_12L);
        r7_13L = findViewById(R.id.R7_13L);
        r7_14L = findViewById(R.id.R7_14L);
        r7_15L = findViewById(R.id.R7_15L);
        r7_16L = findViewById(R.id.R7_16L);
        r7_17L = findViewById(R.id.R7_17L);
        r7_18L = findViewById(R.id.R7_18L);
        r7_19L = findViewById(R.id.R7_19L);
        r7_20L = findViewById(R.id.R7_20L);
        r7_19R = findViewById(R.id.R7_19R);
        r7_18R = findViewById(R.id.R7_18R);
        r7_17R = findViewById(R.id.R7_17R);
        r7_16R = findViewById(R.id.R7_16R);
        r7_15R = findViewById(R.id.R7_15R);
        r7_14R = findViewById(R.id.R7_14R);
        r7_13R = findViewById(R.id.R7_13R);
        r7_12R = findViewById(R.id.R7_12R);
        r7_11R = findViewById(R.id.R7_11R);
        r7_10R = findViewById(R.id.R7_10R);

        //row 8
        r8_11L = findViewById(R.id.R8_11L);
        r8_12L = findViewById(R.id.R8_12L);
        r8_13L = findViewById(R.id.R8_13L);
        r8_14L = findViewById(R.id.R8_14L);
        r8_15L = findViewById(R.id.R8_15L);
        r8_16L = findViewById(R.id.R8_16L);
        r8_17L = findViewById(R.id.R8_17L);
        r8_18L = findViewById(R.id.R8_18L);
        r8_19L = findViewById(R.id.R8_19L);
        r8_20L = findViewById(R.id.R8_20L);
        r8_21L = findViewById(R.id.R8_21L);
        r8_21R = findViewById(R.id.R8_21R);
        r8_20R = findViewById(R.id.R8_20R);
        r8_19R = findViewById(R.id.R8_19R);
        r8_18R = findViewById(R.id.R8_18R);
        r8_17R = findViewById(R.id.R8_17R);
        r8_16R = findViewById(R.id.R8_16R);
        r8_15R = findViewById(R.id.R8_15R);
        r8_14R = findViewById(R.id.R8_14R);
        r8_13R = findViewById(R.id.R8_13R);
        r8_12R = findViewById(R.id.R8_12R);
        r8_11R = findViewById(R.id.R8_11R);

        //row 9
        r9_11L = findViewById(R.id.R9_11L);
        r9_12L = findViewById(R.id.R9_12L);
        r9_13L = findViewById(R.id.R9_13L);
        r9_14L = findViewById(R.id.R9_14L);
        r9_15L = findViewById(R.id.R9_15L);
        r9_16L = findViewById(R.id.R9_16L);
        r9_17L = findViewById(R.id.R9_17L);
        r9_18L = findViewById(R.id.R9_18L);
        r9_19L = findViewById(R.id.R9_19L);
        r9_20L = findViewById(R.id.R9_20L);
        r9_21L = findViewById(R.id.R9_21L);
        r9_22L = findViewById(R.id.R9_22L);
        r9_21R = findViewById(R.id.R9_21R);
        r9_20R = findViewById(R.id.R9_20R);
        r9_19R = findViewById(R.id.R9_19R);
        r9_18R = findViewById(R.id.R9_18R);
        r9_17R = findViewById(R.id.R9_17R);
        r9_16R = findViewById(R.id.R9_16R);
        r9_15R = findViewById(R.id.R9_15R);
        r9_14R = findViewById(R.id.R9_14R);
        r9_13R = findViewById(R.id.R9_13R);
        r9_12R = findViewById(R.id.R9_12R);
        r9_11R = findViewById(R.id.R9_11R);

        //row 10
        r10_10L = findViewById(R.id.R10_10L);
        r10_11L = findViewById(R.id.R10_11L);
        r10_12L = findViewById(R.id.R10_12L);
        r10_13L = findViewById(R.id.R10_13L);
        r10_14L = findViewById(R.id.R10_14L);
        r10_15L = findViewById(R.id.R10_15L);
        r10_16L = findViewById(R.id.R10_16L);
        r10_17L = findViewById(R.id.R10_17L);
        r10_18L = findViewById(R.id.R10_18L);
        r10_19L = findViewById(R.id.R10_19L);
        r10_20L = findViewById(R.id.R10_20L);
        r10_20R = findViewById(R.id.R10_20R);
        r10_19R = findViewById(R.id.R10_19R);
        r10_18R = findViewById(R.id.R10_18R);
        r10_17R = findViewById(R.id.R10_17R);
        r10_16R = findViewById(R.id.R10_16R);
        r10_15R = findViewById(R.id.R10_15R);
        r10_14R = findViewById(R.id.R10_14R);
        r10_13R = findViewById(R.id.R10_13R);
        r10_12R = findViewById(R.id.R10_12R);
        r10_11R = findViewById(R.id.R10_11R);
        r10_10R = findViewById(R.id.R10_10R);

        //row 11
        r11_9L = findViewById(R.id.R11_9L);
        r11_10L = findViewById(R.id.R11_10L);
        r11_11L = findViewById(R.id.R11_11L);
        r11_12L = findViewById(R.id.R11_12L);
        r11_13L = findViewById(R.id.R11_13L);
        r11_14L = findViewById(R.id.R11_14L);
        r11_15L = findViewById(R.id.R11_15L);
        r11_16L = findViewById(R.id.R11_16L);
        r11_17L = findViewById(R.id.R11_17L);
        r11_18L = findViewById(R.id.R11_18L);
        r11_19L = findViewById(R.id.R11_19L);
        r11_18R = findViewById(R.id.R11_18R);
        r11_17R = findViewById(R.id.R11_17R);
        r11_16R = findViewById(R.id.R11_16R);
        r11_15R = findViewById(R.id.R11_15R);
        r11_14R = findViewById(R.id.R11_14R);
        r11_13R = findViewById(R.id.R11_13R);
        r11_12R = findViewById(R.id.R11_12R);
        r11_11R = findViewById(R.id.R11_11R);
        r11_10R = findViewById(R.id.R11_10R);
        r11_9R = findViewById(R.id.R11_9R);

        //row 12
        r12_8L = findViewById(R.id.R12_8L);
        r12_9L = findViewById(R.id.R12_9L);
        r12_10L = findViewById(R.id.R12_10L);
        r12_11L = findViewById(R.id.R12_11L);
        r12_12L = findViewById(R.id.R12_12L);
        r12_13L = findViewById(R.id.R12_13L);
        r12_14L = findViewById(R.id.R12_14L);
        r12_15L = findViewById(R.id.R12_15L);
        r12_16L = findViewById(R.id.R12_16L);
        r12_17L = findViewById(R.id.R12_17L);
        r12_17R = findViewById(R.id.R12_17R);
        r12_16R = findViewById(R.id.R12_16R);
        r12_15R = findViewById(R.id.R12_15R);
        r12_14R = findViewById(R.id.R12_14R);
        r12_13R = findViewById(R.id.R12_13R);
        r12_12R = findViewById(R.id.R12_12R);
        r12_11R = findViewById(R.id.R12_11R);
        r12_10R = findViewById(R.id.R12_10R);
        r12_9R = findViewById(R.id.R12_9R);
        r12_8R = findViewById(R.id.R12_8R);

        //row 13
        r13_7L = findViewById(R.id.R13_7L);
        r13_8L = findViewById(R.id.R13_8L);
        r13_9L = findViewById(R.id.R13_9L);
        r13_10L = findViewById(R.id.R13_10L);
        r13_11L = findViewById(R.id.R13_11L);
        r13_12L = findViewById(R.id.R13_12L);
        r13_13L = findViewById(R.id.R13_13L);
        r13_14L = findViewById(R.id.R13_14L);
        r13_15L = findViewById(R.id.R13_15L);
        r13_16L = findViewById(R.id.R13_16L);
        r13_17L = findViewById(R.id.R13_17L);
        r13_16R = findViewById(R.id.R13_16R);
        r13_15R = findViewById(R.id.R13_15R);
        r13_14R = findViewById(R.id.R13_14R);
        r13_13R = findViewById(R.id.R13_13R);
        r13_12R = findViewById(R.id.R13_12R);
        r13_11R = findViewById(R.id.R13_11R);
        r13_10R = findViewById(R.id.R13_10R);
        r13_9R = findViewById(R.id.R13_9R);
        r13_8R = findViewById(R.id.R13_8R);
        r13_7R = findViewById(R.id.R13_7R);

        //row 14
        r14_6L = findViewById(R.id.R14_6L);
        r14_7L = findViewById(R.id.R14_7L);
        r14_8L = findViewById(R.id.R14_8L);
        r14_9L = findViewById(R.id.R14_9L);
        r14_10L = findViewById(R.id.R14_10L);
        r14_11L = findViewById(R.id.R14_11L);
        r14_12L = findViewById(R.id.R14_12L);
        r14_13L = findViewById(R.id.R14_13L);
        r14_14L = findViewById(R.id.R14_14L);
        r14_15L = findViewById(R.id.R14_15L);
        r14_16L = findViewById(R.id.R14_16L);
        r14_16R = findViewById(R.id.R14_16R);
        r14_15R = findViewById(R.id.R14_15R);
        r14_14R = findViewById(R.id.R14_14R);
        r14_13R = findViewById(R.id.R14_13R);
        r14_12R = findViewById(R.id.R14_12R);
        r14_11R = findViewById(R.id.R14_11R);
        r14_10R = findViewById(R.id.R14_10R);
        r14_9R = findViewById(R.id.R14_9R);
        r14_8R = findViewById(R.id.R14_8R);
        r14_7R = findViewById(R.id.R14_7R);
        r14_6R = findViewById(R.id.R14_6R);

        //row 15
        r15_1L = findViewById(R.id.R15_1L);
        r15_2L = findViewById(R.id.R15_2L);
        r15_3L = findViewById(R.id.R15_3L);
        r15_4L = findViewById(R.id.R15_4L);
        r15_5L = findViewById(R.id.R15_5L);
        r15_6L = findViewById(R.id.R15_6L);
        r15_7L = findViewById(R.id.R15_7L);
        r15_8L = findViewById(R.id.R15_8L);
        r15_9L = findViewById(R.id.R15_9L);
        r15_10L = findViewById(R.id.R15_10L);
        r15_11L = findViewById(R.id.R15_11L);
        r15_12L = findViewById(R.id.R15_12L);
        r15_11R = findViewById(R.id.R15_11R);
        r15_10R = findViewById(R.id.R15_10R);
        r15_9R = findViewById(R.id.R15_9R);
        r15_8R = findViewById(R.id.R15_8R);
        r15_7R = findViewById(R.id.R15_7R);
        r15_6R = findViewById(R.id.R15_6R);
        r15_5R = findViewById(R.id.R15_5R);
        r15_4R = findViewById(R.id.R15_4R);
        r15_3R = findViewById(R.id.R15_3R);
        r15_2R = findViewById(R.id.R15_2R);
        r15_1R = findViewById(R.id.R15_1R);

        arr = new MyView[]{r1_4L, r1_5L, r1_6L, r1_7L, r1_8L, r1_9L, r1_10L, r1_11L, r1_10R, r1_9R, r1_8R, r1_7R, r1_6R, r1_5R, r1_4R,
                r2_5L, r2_6L, r2_7L, r2_8L, r2_9L, r2_10L, r2_11L, r2_12L, r2_12R, r2_11R, r2_10R, r2_9R, r2_8R, r2_7R, r2_6R, r2_5R,
                r3_6L, r3_7L, r3_8L, r3_9L, r3_10L, r3_11L, r3_12L, r3_13L, r3_14L, r3_13R, r3_12R, r3_11R, r3_10R, r3_9R, r3_8R, r3_7R, r3_6R,
                r4_7L, r4_8L, r4_9L, r4_10L, r4_11L, r4_12L, r4_13L, r4_14L, r4_15L, r4_15R, r4_14R, r4_13R, r4_12R, r4_11R, r4_10R, r4_9R, r4_8R, r4_7R,
                r5_8L, r5_9L, r5_10L, r5_11L, r5_12L, r5_13L, r5_14L, r5_15L, r5_16L, r5_17L, r5_16R, r5_15R, r5_14R, r5_13R, r5_12R, r5_11R, r5_10R, r5_9R, r5_8R,
                r6_9L, r6_10L, r6_11L, r6_12L, r6_13L, r6_14L, r6_15L, r6_16L, r6_17L, r6_18L, r6_18R, r6_17R, r6_16R, r6_15R, r6_14R, r6_13R, r6_12R, r6_11R, r6_10R, r6_9R,
                r7_10L, r7_11L, r7_12L, r7_13L, r7_14L, r7_15L, r7_16L, r7_17L, r7_18L, r7_19L, r7_20L, r7_19R, r7_18R, r7_17R, r7_16R, r7_15R, r7_14R, r7_13R, r7_12R, r7_11R, r7_10R,
                r8_11L, r8_12L, r8_13L, r8_14L, r8_15L, r8_16L, r8_17L, r8_18L, r8_19L, r8_20L, r8_21L, r8_21R, r8_20R, r8_19R, r8_18R, r8_17R, r8_16R, r8_15R, r8_14R, r8_13R, r8_12R, r8_11R,
                r9_11L, r9_12L, r9_13L, r9_14L, r9_15L, r9_16L, r9_17L, r9_18L, r9_19L, r9_20L, r9_21L, r9_22L, r9_21R, r9_20R, r9_19R, r9_18R, r9_17R, r9_16R, r9_15R, r9_14R, r9_13R, r9_12R, r9_11R,
                r10_10L, r10_11L, r10_12L, r10_13L, r10_14L, r10_15L, r10_16L, r10_17L, r10_18L, r10_19L, r10_20L, r10_20R, r10_19R, r10_18R, r10_17R, r10_16R, r10_15R, r10_14R, r10_13R, r10_12R, r10_11R, r10_10R,
                r11_9L, r11_10L, r11_11L, r11_12L, r11_13L, r11_14L, r11_15L, r11_16L, r11_17L, r11_18L, r11_19L, r11_18R, r11_17R, r11_16R, r11_15R, r11_14R, r11_13R, r11_12R, r11_11R, r11_10R, r11_9R,
                r12_8L, r12_9L, r12_10L, r12_11L, r12_12L, r12_13L, r12_14L, r12_15L, r12_16L, r12_17L, r12_17R, r12_16R, r12_15R, r12_14R, r12_13R, r12_12R, r12_11R, r12_10R, r12_9R, r12_8R,
                r13_7L, r13_8L, r13_9L, r13_10L, r13_11L, r13_12L, r13_13L, r13_14L, r13_15L, r13_16L, r13_17L, r13_16R, r13_15R, r13_14R, r13_13R, r13_12R, r13_11R, r13_10R, r13_9R, r13_8R, r13_7R,
                r14_6L, r14_7L, r14_8L, r14_9L, r14_10L, r14_11L, r14_12L, r14_13L, r14_14L, r14_15L, r14_16L, r14_16R, r14_15R, r14_14R, r14_13R, r14_12R, r14_11R, r14_10R, r14_9R, r14_8R, r14_7R, r14_6R,
                r15_1L, r15_2L, r15_3L, r15_4L, r15_5L, r15_6L, r15_7L, r15_8L, r15_9L, r15_10L, r15_11L, r15_12L, r15_11R, r15_10R, r15_9R, r15_8R, r15_7R, r15_6R, r15_5R, r15_4R, r15_3R, r15_2R, r15_1R};

        for (int i = 0; i < arr.length; i++) {
            arr[i].setOnClickListener(this);
        }

        doneImg = findViewById(R.id.done_img);
        doneImg.setOnClickListener(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("EVENT", -1);
        if (id > 0) {
            new SecondArea.Hall().execute();
//            MyView.clearList();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.done_img) {
            finish();
        } else {
            onSeatClick((MyView) v);
        }
    }

    @Override
    public void onBackPressed() {
//            MyView.clearList();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onSeatClick(MyView view) {
        int id = view.getId();
        String[] arr = getResources().getResourceEntryName(id).split("_");
        char[] chars = arr[0].toCharArray();
        if (chars.length == 2) {
            String row = String.valueOf(chars[1]);
            Toast.makeText(this, "row " + row + " " + "place " + arr[1] + "\nPrice: " + view.getPrice(), Toast.LENGTH_SHORT).show();
            LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), arr[1]);
            view.changeStatus(lockedSeats, view.getPrice());
        } else if (chars.length == 3) {
            String row = String.valueOf(chars[1]) + String.valueOf(chars[2]);
            Toast.makeText(this, "row " + row + " " + "place " + arr[1] + "\nPrice: " + view.getPrice(), Toast.LENGTH_SHORT).show();
            LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), arr[1]);
            view.changeStatus(lockedSeats, view.getPrice());
        }
    }

    class Hall extends AsyncTask<Void, Void, ListLockedSeats> {

        private AlertDialog dialog = new AlertDialog.Builder(SecondArea.this)
                .setView(R.layout.frame_progress_dialog)
                .create();
        private ListLockedSeats l;

        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected ListLockedSeats doInBackground(Void... voids) {
            try {
                l = OkHttpProvider.getInstance().listLockedSeats(id);
                return l;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ListLockedSeats listLockedSeats) {
            if (listLockedSeats != null) {
                dialog.dismiss();
                lockedSeatsList = listLockedSeats.getList();
                priceRangesList = listLockedSeats.getPriceList();

                for (int i = 0; i < lockedSeatsList.size(); i++) {
                    for (int j = 0; j < arr.length; j++) {
                        int id = arr[j].getId();
                        String[] array = getResources().getResourceEntryName(id).split("_");
                        char[] chars = array[0].toCharArray();
                        if (chars.length == 2) {
                            String row = String.valueOf(chars[1]);
                            LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), array[1]);
                            if (lockedSeats.equals(lockedSeatsList.get(i))) {
                                arr[j].changeColor(lockedSeats);
                            }
                        } else if (chars.length == 3) {
                            String row = String.valueOf(chars[1]) + String.valueOf(chars[2]);
                            LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), array[1]);
                            if (lockedSeats.equals(lockedSeatsList.get(i))) {
                                arr[j].changeColor(lockedSeats);
                            }
                        }
                    }
                }

                for (int i = 0; i < priceRangesList.size(); i++) {
                    PriceRanges priceRanges = priceRangesList.get(i);
                    int[] rows = priceRanges.getRows();
                    double price = priceRanges.getPrice();
                    String col = priceRanges.getColor();

                    for (int j = 0; j < arr.length; j++) {
                        int id = arr[j].getId();
                        String[] array = getResources().getResourceEntryName(id).split("_");
                        char[] chars = array[0].toCharArray();
                        if (chars.length == 2) {
                            String row = String.valueOf(chars[1]);
                            LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), array[1]);
                            for (int k = 0; k < rows.length; k++) {
                                if (lockedSeats.getRow() == rows[k]) {
                                    arr[j].setColor(col);
                                    arr[j].changePriceRangeColor(col);
                                    arr[j].setPrice(price);
                                }
                            }
                        } else if (chars.length == 3) {
                            String row = String.valueOf(chars[1]) + String.valueOf(chars[2]);
                            LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), array[1]);
                            for (int k = 0; k < rows.length; k++) {
                                if (lockedSeats.getRow() == rows[k]) {
                                    arr[j].setColor(col);
                                    arr[j].changePriceRangeColor(col);
                                    arr[j].setPrice(price);
                                }
                            }
                        }
                    }
                }

                for (int a = 0; a < MyView.getList().size(); a++) {
                    for (int j = 0; j < arr.length; j++) {
                        int id = arr[j].getId();
                        String[] array = getResources().getResourceEntryName(id).split("_");
                        char[] chars = array[0].toCharArray();
                        if (chars.length == 2) {
                            String row = String.valueOf(chars[1]);
                            checkIfChoosed(a, j, new LockedSeats(Integer.parseInt(row), array[1]));
                        } else if (chars.length == 3) {
                            String row = String.valueOf(chars[1]) + String.valueOf(chars[2]);
                            checkIfChoosed(a, j, new LockedSeats(Integer.parseInt(row), array[1]));
                        }
                    }
                }

            } else {
                dialog.dismiss();
                new AlertDialog.Builder(SecondArea.this)
                        .setTitle("ERROR")
                        .setMessage("Something wrong")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .create()
                        .show();
            }

        }

        private void checkIfChoosed(int a, int j, LockedSeats lockedSeats1) {
            LockedSeats lockedSeats = lockedSeats1;
            if (lockedSeats.getRow() == MyView.getList().get(a).getRow() && lockedSeats.getSeat().equals(MyView.getList().get(a).getSeat())) {
                arr[j].changeColorBooked(lockedSeats);
            }
        }
    }
}

