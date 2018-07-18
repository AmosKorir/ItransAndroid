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

public class CustomAdapter extends ArrayAdapter<Model>{
    Context ctx;
    int resource;
    ArrayList<Model> arrayList;
    LayoutInflater layoutInflater;
    public CustomAdapter( Context context, int resource,  ArrayList<Model> objects) {

        super(context, resource, objects);
        ViewHolder viewHolder;

        this.ctx = context;
        this.resource = resource;
        arrayList = objects;
        layoutInflater = ((Activity) ctx).getLayoutInflater();


    }

    @Override
    public View getView(final int position, View itemview, ViewGroup parent) {
        ViewHolder viewHolder;
        Button book;
        Model model= arrayList.get(position);

        final String allocationid;
        final int fare;

        if (itemview == null) {
            viewHolder = new ViewHolder();


            itemview = layoutInflater.inflate(resource, parent, false);
            viewHolder.plate=(TextView)itemview.findViewById(R.id.plateno);
            viewHolder.stationA=(TextView)itemview.findViewById(R.id.stationa);
            viewHolder.StationB=(TextView)itemview.findViewById(R.id.stationb);
            viewHolder.time=(TextView)itemview.findViewById(R.id.time);
            viewHolder.fare=(TextView)itemview.findViewById(R.id.fare);


            viewHolder.plate.setText(model.getPlateno());
            viewHolder.stationA.setText(model.getStartionA());
            viewHolder.StationB.setText(model.getStartionB());
            viewHolder.time.setText(model.getTime());
            fare=model.getFare();
            viewHolder.fare.setText(fare+"");

            allocationid=model.getAllocationid();


            book=(Button)itemview.findViewById(R.id.book);
            book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   getContext().startActivity(new Intent(getContext(),Booking.class).putExtra("allocationid",allocationid).putExtra("fare",fare));
                }
            });

            itemview.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)itemview.getTag();
            viewHolder.plate.setText(model.getPlateno());
            viewHolder.stationA.setText(model.getStartionA());
            viewHolder.StationB.setText(model.getStartionB());
            viewHolder.time.setText(model.getTime());

            fare=model.getFare();
            viewHolder.fare.setText(fare+"");
            allocationid=model.getAllocationid();

            book=(Button)itemview.findViewById(R.id.book);
            book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getContext().startActivity(new Intent(getContext(),Booking.class).putExtra("allocationid",allocationid).putExtra("fare",fare));
                }
            });
        }


        return  itemview;
    }}