package com.example.metcastapp.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.metcastapp.models.Main;

import java.util.ArrayList;
import java.util.List;

public class MetAdapter extends RecyclerView.Adapter<MetAdapter.MetViewHolder> {
    private List<Main> tweetList = new ArrayList<>();
    

