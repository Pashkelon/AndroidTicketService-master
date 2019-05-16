package com.example.biletiki2.UIclasses;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biletiki2.R;
import com.example.biletiki2.adapter.EventListAdapter;
import com.example.biletiki2.data.OkHttpProvider;
import com.example.biletiki2.data.StoreProvider;
import com.example.biletiki2.data.dto.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,
        SearchView.OnCloseListener,
        EventListAdapter.AdapterCallBack, SearchView.OnQueryTextListener, CompoundButton.OnCheckedChangeListener, SwipeRefreshLayout.OnRefreshListener, EventListAdapter.AddEvents {

    private SearchView searchView;
    private CheckBox concertsCheckbox, exhibitionsCheckbox, showsCheckbox;
    private TextView toolbarTxt, filtersTxt;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private LinearLayout calendarRow, eventTypeRow;
    private ImageView eventArrowDown, calendarArrowDown, doneImg;
    private DatePicker datePicker;
    private LinearLayout popWindow;
    private FrameLayout underlineEvent, underlineCalendar;
    private RecyclerView eventList;
    private EventListAdapter adapter;
    private List<Event> list;
    private ProgressBar paginationProgress;
    private boolean from = false;
    private boolean to = false;
    private long tFrom = 1541384000000L;
    private long tTo = 1669073600000L;
    private String typeEvent = "1";
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//============================================================================================
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorHamburger));
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        searchView = findViewById(R.id.search_view);
        SearchView.SearchAutoComplete searchAutoComplete =
                searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.colorHamburger));
        searchAutoComplete.setTextColor(getResources().getColor(R.color.colorHamburger));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnSearchClickListener(this);
        searchView.setOnCloseListener(this);
        searchView.setOnQueryTextListener(this);

        toolbarTxt = findViewById(R.id.toolbar_txt);

        datePicker = findViewById(R.id.date_picker);
        doneImg = findViewById(R.id.done_img);
        filtersTxt = findViewById(R.id.filters_txt);
        calendarRow = findViewById(R.id.calendar_row);
        eventTypeRow = findViewById(R.id.event_type_row);
        eventArrowDown = findViewById(R.id.event_arrow_down);
        calendarArrowDown = findViewById(R.id.calendar_arrow_down);
        popWindow = findViewById(R.id.popup_window);
        underlineEvent = findViewById(R.id.underline_event);
        underlineCalendar = findViewById(R.id.underline_calendar);

        swipeRefreshLayout = findViewById(R.id.swipe_update);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(this);

        concertsCheckbox = findViewById(R.id.concerts_checkbox);
        exhibitionsCheckbox = findViewById(R.id.exhibitions_checkbox);
        showsCheckbox = findViewById(R.id.shows_checkbox);
        paginationProgress = findViewById(R.id.pagination_progress);
        paginationProgress.setVisibility(View.GONE);
        concertsCheckbox.setOnCheckedChangeListener(this);
        exhibitionsCheckbox.setOnCheckedChangeListener(this);
        showsCheckbox.setOnCheckedChangeListener(this);

        eventTypeRow.setOnClickListener(this);
        calendarRow.setOnClickListener(this);
        eventArrowDown.setOnClickListener(this);
        calendarArrowDown.setOnClickListener(this);
        filtersTxt.setOnClickListener(this);
        doneImg.setOnClickListener(this);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //============================================================================================
        //RecyclerView init.
        eventList = findViewById(R.id.event_list);
        new RecyclerTask().execute();
//============================================================================================

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (eventTypeRow.getVisibility() == View.VISIBLE){
            isFiltersVisible();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_login_out) {
            StoreProvider.getInstance().logout();
            Intent intent = new Intent(this, LogoutActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_events) {
            OkHttpProvider.getInstance().setPage(1);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_shopping_cart) {
            Intent intent = new Intent(this, ShoppingCart.class);
            startActivity(intent);
        } else if (id == R.id.nav_terms_and_conditions) {
            Intent intent = new Intent(this, TermsAndConditions.class);
            startActivity(intent);
        } else if (id == R.id.nav_about_us) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_view) {
            if (eventTypeRow.getVisibility() == View.VISIBLE) {
                isFiltersVisible();
            }
            toolbarTxt.setVisibility(View.GONE);
        } else if (v.getId() == R.id.filters_txt) {
            if (datePicker.getVisibility() == View.VISIBLE) {
                from = false;
                to = false;
            }
            if (eventTypeRow.getVisibility() == View.VISIBLE) {
                isFiltersVisible();
            } else {
                eventTypeRow.setVisibility(View.VISIBLE);
                underlineEvent.setVisibility(View.VISIBLE);
                underlineCalendar.setVisibility(View.VISIBLE);
                calendarRow.setVisibility(View.VISIBLE);
            }
        } else if (v.getId() == R.id.calendar_arrow_down || v.getId() == R.id.calendar_row) {
            if (datePicker.getVisibility() == View.VISIBLE) {
                showDate();
                calendarArrowDown.setImageResource(R.drawable.ic_arrow_dropdown);
                datePicker.setVisibility(View.GONE);
                doneImg.setVisibility(View.GONE);
            } else {
                calendarArrowDown.setImageResource(R.drawable.ic_arrow_dropdown_up);
                datePicker.setVisibility(View.VISIBLE);
                doneImg.setVisibility(View.VISIBLE);
            }

        } else if (v.getId() == R.id.event_arrow_down || v.getId() == R.id.event_type_row) {
            if (popWindow.getVisibility() == View.GONE) {
                popWindow.setVisibility(View.VISIBLE);
                eventArrowDown.setImageResource(R.drawable.ic_arrow_dropdown_up);
            } else {
                popWindow.setVisibility(View.GONE);
                eventArrowDown.setImageResource(R.drawable.ic_arrow_dropdown);
                from = false;
                to = false;
            }
        } else if (v.getId() == R.id.done_img) {
            if (!from && !to) {
                showDate();
            } else if (from && !to) {
                showDate();
            }

        }
    }

    private Calendar showDate() {
        int d = datePicker.getDayOfMonth();
        int m = datePicker.getMonth() + 1;
        int y = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(y, m, d);

        if (!from && !to) {
            tFrom = calendar.getTimeInMillis();
            from = true;
            new RecyclerTask().execute();
            Toast.makeText(this, "Date FROM choosed : " + d + "/" + m + "/" + y +
                    "\nChoose date to", Toast.LENGTH_SHORT).show();
        } else if (from && !to) {
            tTo = calendar.getTimeInMillis();
            from = false;
            new RecyclerTask().execute();
            Toast.makeText(this, "Date TO choosed : " + d + "/" + m + "/" + y +
                    "\nFiltering events in the set range", Toast.LENGTH_SHORT).show();
            isFiltersVisible();
        }
        return calendar;
    }


    private void isFiltersVisible() {
        eventTypeRow.setVisibility(View.GONE);
        calendarRow.setVisibility(View.GONE);
        datePicker.setVisibility(View.GONE);
        popWindow.setVisibility(View.GONE);
        underlineEvent.setVisibility(View.GONE);
        underlineCalendar.setVisibility(View.GONE);
        doneImg.setVisibility(View.GONE);
        calendarArrowDown.setImageResource(R.drawable.ic_arrow_dropdown);
        eventArrowDown.setImageResource(R.drawable.ic_arrow_dropdown);
        from = false;
        to = false;
    }


    @Override
    public boolean onClose() {
        toolbarTxt.setVisibility(View.VISIBLE);
        return false;
    }

    //==========================================================================================
    @Override
    public void onRowClick(int position, Event event) {
        Intent intent = new Intent(this, EventInfo.class);
        intent.putExtra("EVENT", event.getEventId());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_CANCELED) {
            new RecyclerTask().execute();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (adapter.getList() != null) {
            List<Event> e = new ArrayList<>();
            if (concertsCheckbox.isChecked()) {
                filteringConcertCheckbox(e, exhibitionsCheckbox, showsCheckbox, "1");
                filteringSearchViewQuery();
            }else if (exhibitionsCheckbox.isChecked()) {
                filteringConcertCheckbox(e, concertsCheckbox, showsCheckbox, "2");
                filteringSearchViewQuery();
            }else if (showsCheckbox.isChecked()) {
                filteringConcertCheckbox(e, concertsCheckbox, exhibitionsCheckbox, "3");
                filteringSearchViewQuery();
            } else {
                adapter.getFilter().filter(s);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();

        List<Event> e = new ArrayList<>();
        if (id == R.id.concerts_checkbox) {
            if (buttonView.isChecked()) {
                filteringConcertCheckbox(e, exhibitionsCheckbox, showsCheckbox, "1");
                filteringSearchViewQuery();
            }
        } else if (id == R.id.exhibitions_checkbox) {
            if (buttonView.isChecked()) {
                filteringConcertCheckbox(e, concertsCheckbox, showsCheckbox, "2");
                filteringSearchViewQuery();
            }
        } else if (id == R.id.shows_checkbox) {
            if (buttonView.isChecked()) {
                filteringConcertCheckbox(e, concertsCheckbox, exhibitionsCheckbox, "3");
                filteringSearchViewQuery();
            }
        }
    }

    @Override
    public void onRefresh() {
        adapter.clearList();
        concertsCheckbox.setChecked(false);
        showsCheckbox.setChecked(false);
        exhibitionsCheckbox.setChecked(false);
        isFiltersVisible();
        new RecyclerTask().execute();
    }

    @Override
    public void addMore() {
        OkHttpProvider.getInstance().plusPage();
        new GetMoreEvents().execute();
    }

    //========================================================================================
    //Recycler settings.
    class MyTouchCallBack extends ItemTouchHelper.Callback {

        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.END |
                    ItemTouchHelper.START);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            final int position = viewHolder.getAdapterPosition();
            final Event removed = adapter.remove(position);
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Delete?")
                    .setMessage("You want to hide this Event?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //todo delete task
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.cancelLastRemove();
                }
            }).create()
                    .show();
        }
    }

    //=======================================================================================
    class RecyclerTask extends AsyncTask<Void, Void, List<Event>> {
        private AlertDialog dialog;

        @Override
        protected void onPreExecute() {
            OkHttpProvider.getInstance().setPage(1);

            dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Loading events...")
                    .setView(R.layout.frame_progress_dialog)
                    .create();
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected List<Event> doInBackground(Void... voids) {
            list = new ArrayList<>();
            try {
                list = OkHttpProvider.getInstance()
                        .getEvents(tFrom, tTo, typeEvent);
                adapter = new EventListAdapter(list, MainActivity.this, MainActivity.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<Event> events) {
            if (events != null) {
                final LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                eventList.setLayoutManager(manager);

                ItemTouchHelper helper = new ItemTouchHelper(new MyTouchCallBack());
                helper.attachToRecyclerView(eventList);

                eventList.setAdapter(adapter);

                List<Event> e = new ArrayList<>();
                if (concertsCheckbox.isChecked()) {
                    filteringConcertCheckbox(e, exhibitionsCheckbox, showsCheckbox, "1");
                    filteringSearchViewQuery();
                }else if (exhibitionsCheckbox.isChecked()) {
                    filteringConcertCheckbox(e, concertsCheckbox, showsCheckbox, "2");
                    filteringSearchViewQuery();
                }else if (showsCheckbox.isChecked()) {
                    filteringConcertCheckbox(e, concertsCheckbox, exhibitionsCheckbox, "3");
                    filteringSearchViewQuery();
                }

                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                    CharSequence s = "";
                    searchView.setQuery(s, false);
                    searchView.setIconified(true);
                }
                dialog.dismiss();
            } else {
                dialog.dismiss();
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("ERROR")
                        .setMessage("Something wrong")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new RecyclerTask().execute();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            }
        }
    }

    private void filteringSearchViewQuery() {
        List<Event> eventList = new ArrayList<>();
        for (Event event : adapter.getList()) {
            if (event.getArtist().toLowerCase().contains(searchView.getQuery()) || event.getEventName().toLowerCase().contains(searchView.getQuery())) {
                eventList.add(event);
            }
        }
        adapter.updateList(eventList);
    }

    private void filteringConcertCheckbox(List<Event> e, CheckBox exhibitionsCheckbox, CheckBox showsCheckbox, String s2) {
        exhibitionsCheckbox.setChecked(false);
        showsCheckbox.setChecked(false);
        for (Event s1 : adapter.getmFilteredList()) {
            if (s1.getEventType().equals(s2)) {
                e.add(s1);
            }
        }
        adapter.updateList(e);
    }
//============================================================================================

    class GetMoreEvents extends AsyncTask<Void, Void, List<Event>> {

        private List<Event> eList;

        @Override
        protected List<Event> doInBackground(Void... voids) {
            eList = new ArrayList<>();
            try {
                eList = OkHttpProvider.getInstance()
                        .getEvents(tFrom, tTo, typeEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return eList;
        }

        @Override
        protected void onPostExecute(List<Event> events) {
            String sV = searchView.getQuery().toString().trim();
            if (events.size() == 5) {
                    adapter.addAll(events);
                    adapter.getFilter().filter(sV);
            } else {
                    adapter.addAll(events);
                    adapter.getFilter().filter(sV);
                adapter.hideFooter();
            }
        }
    }

}
