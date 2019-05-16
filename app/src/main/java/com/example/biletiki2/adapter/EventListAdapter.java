package com.example.biletiki2.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.biletiki2.R;
import com.example.biletiki2.data.StoreProvider;
import com.example.biletiki2.data.dto.Event;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventListAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    public boolean showFooter = true;
    private List<Event> list;
    private List<Event> mFilteredList;
    private AdapterCallBack callBack;
    private Event lastRemoved;
    private int lastRemovedPosition = -1;
    private static final int FOOTER_VIEW = 1;
    private AddEvents callBackFooter;

    public EventListAdapter(List<Event> list, AdapterCallBack callBack, AddEvents callBackFooter) {
        this.mFilteredList = list;
        this.list = list;
        this.callBack = callBack;
        this.callBackFooter = callBackFooter;
    }

    public void updateList(List<Event> e){
        list = new ArrayList<>();
        list.addAll(e);
        notifyDataSetChanged();
    }

    public List<Event> getList(){
        return list;
    }

    public List<Event> getmFilteredList(){
        return mFilteredList;
    }

    public void setmFilteredList(List<Event> l){
        this.mFilteredList = l;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int type) {
        View v;

        if (type == FOOTER_VIEW) {
            v = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.list_item_footer, recyclerView, false);
            FooterViewHolder vh = new FooterViewHolder(v);
            return vh;
        }

        v = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.my_row, recyclerView, false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        try {
            if (viewHolder instanceof MyViewHolder) {
                MyViewHolder vh = (MyViewHolder) viewHolder;

                Event event = list.get(position);
                vh.artistTxt.setText(event.getArtist());
                long time = event.getEventStart();
                String dateRes = StoreProvider.getInstance().getDate(time);
                vh.dateTxt.setText(dateRes);
                vh.eventName.setText(event.getEventName());
                String[] arr = event.getImages();
                Picasso.get().load(arr[0]).into(vh.image);
            } else if (viewHolder instanceof FooterViewHolder) {
                if (callBackFooter!=null && showFooter){
                    callBackFooter.addMore();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (list == null || list.size() == 0) {
            return 0;
        }

        if (showFooter){
            return list.size() + 1;
        }

        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return FOOTER_VIEW;
        }

        return super.getItemViewType(position);
    }

    public Event get(int position) {
        return list.get(position);
    }

    public void clearList(){
        list = new ArrayList<>();
    }

    public Event remove(int position) {
        lastRemoved = list.remove(position);
        lastRemovedPosition = position;
        notifyItemRemoved(position);
        return lastRemoved;
    }

    public void addAll(List<Event> l){
        mFilteredList.addAll(l);
        notifyDataSetChanged();
    }

    public void cancelLastRemove() {
        if (lastRemovedPosition >= 0 && lastRemoved != null) {
            list.add(lastRemovedPosition, lastRemoved);
            notifyItemInserted(lastRemovedPosition);
            lastRemovedPosition = -1;
            lastRemoved = null;
        }
    }

    public void hideFooter() {
        showFooter = false;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()){
                    list = mFilteredList;
                }else {
                    List<Event> filteredList = new ArrayList<>();
                    for (Event e: mFilteredList){
                        if (e.getArtist().toLowerCase().contains(charString)||e.getEventName().toLowerCase().contains(charString)){
                            filteredList.add(e);
                        }
                    }
                    list = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<Event>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    //============================================================================================
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView dateTxt, artistTxt, eventName;
        private ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTxt = itemView.findViewById(R.id.date_txt);
            artistTxt = itemView.findViewById(R.id.artist_txt);
            eventName = itemView.findViewById(R.id.event_name_txt);
            image = itemView.findViewById(R.id.image_picture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (callBack != null) {
                callBack.onRowClick(getAdapterPosition(), list.get(getAdapterPosition()));
            }
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    //============================================================================================
    public interface AdapterCallBack {
        void onRowClick(int position, Event event);
    }
//============================================================================================
    public interface AddEvents{
        void addMore();
}
}
