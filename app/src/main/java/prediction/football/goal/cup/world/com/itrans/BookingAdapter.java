package prediction.football.goal.cup.world.com.itrans;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static prediction.football.goal.cup.world.com.itrans.Main.createBook;
import static prediction.football.goal.cup.world.com.itrans.Splash.getUserId;

public class BookingAdapter extends ArrayAdapter<Ticket>{
    Context ctx;
    int resource;
    ArrayList<Ticket> arrayList;
    LayoutInflater layoutInflater;
    public BookingAdapter( Context context, int resource,  ArrayList<Ticket> objects) {

        super(context, resource, objects);
        ViewHolder viewHolder;

        this.ctx = context;
        this.resource = resource;
        arrayList = objects;
        layoutInflater = ((Activity) ctx).getLayoutInflater();


    }

    @Override
    public View getView(final int position, View itemview, ViewGroup parent) {
        BookingHolder viewHolder;
        Button book;
       final Ticket model= arrayList.get(position);

        final String allocationid;
        final int fare;

        if (itemview == null) {
           viewHolder = new BookingHolder();


            itemview = layoutInflater.inflate(resource, parent, false);
            viewHolder.ticketcode=(TextView)itemview.findViewById(R.id.tickcode);
            viewHolder.stationA=(TextView)itemview.findViewById(R.id.stationa);
            viewHolder.StationB=(TextView)itemview.findViewById(R.id.stationb);
            viewHolder.time=(TextView)itemview.findViewById(R.id.time);
            viewHolder.fare=(TextView)itemview.findViewById(R.id.fare);
            viewHolder.cancel=(Button)itemview.findViewById(R.id.cancel);
            viewHolder.date=(TextView)itemview.findViewById(R.id.date);
            viewHolder.plate=(TextView)itemview.findViewById(R.id.plate);


            viewHolder.ticketcode.setText("Ticket: "+model.getTicketcode());
            viewHolder.stationA.setText(model.getStationA());
            viewHolder.StationB.setText(model.getStartionB());
            viewHolder.time.setText(model.getTime());
            viewHolder.fare.setText(model.getAmount());
            viewHolder.plate.setText(model.getPlate());
            viewHolder.date.setText(model.getDate());

            allocationid=model.getAllocationid();

            viewHolder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ticketcode,allocationid,userid,amount,nseat,date;
                    ticketcode=model.getTicketcode();
                    allocationid=model.getAllocationid();
                    userid=getUserId(getContext());
                    amount=model.getAmount();
                    nseat=model.getSeats();
                    date=model.getDate();
                    //createDialog(getContext(),ticketcode,allocationid,userid,amount,nseat,date);
                }
            });


            itemview.setTag(viewHolder);
        }else{

            viewHolder=(BookingHolder)itemview.getTag();

            viewHolder.ticketcode.setText("Ticket: "+model.getTicketcode());
            viewHolder.stationA.setText(model.getStationA());
            viewHolder.StationB.setText(model.getStartionB());
            viewHolder.time.setText(model.getTime());
            viewHolder.fare.setText(model.getAmount());
            allocationid=model.getAllocationid();
            viewHolder.plate.setText(model.getPlate());
            viewHolder.date.setText(model.getDate());

            viewHolder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ticketcode,allocationid,userid,amount,nseat,date;
                    ticketcode=model.getTicketcode();
                    allocationid=model.getAllocationid();
                    userid=getUserId(getContext());
                    amount=model.getAmount();
                    nseat=model.getSeats();
                    date=model.getDate();
                    //createDialog(getContext(),ticketcode,allocationid,userid,amount,nseat,date);
                }
            });



        }


        return  itemview;
    }}