package prediction.football.goal.cup.world.com.itrans;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static prediction.football.goal.cup.world.com.itrans.Constanst.CANCELURL;
import static prediction.football.goal.cup.world.com.itrans.Constanst.HISTORYBOOKING;
import static prediction.football.goal.cup.world.com.itrans.Splash.getUserId;

public class HistoryBooking extends AppCompatActivity {
    String jsonString;
    Connection connection;
    ArrayList<Ticket>arrayList=null;
    ListView listView;
    String userid;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.bookinglist);

        //get the userid from shared preference
        context=HistoryBooking.this;
        userid=getUserId(context);

            fetchData();


        //

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Data refreshed", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                fetchData();
            }
        });
    }

    //create cancel dialog

    public  static void createDialog(final Context c, final String ticketcode, final String allocationid, final String userid, final String amount, final String seats, final String date){
        final Dialog dialog=new Dialog(c);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.confirmdelete);
        dialog.setCancelable(false);

        Button confirm ,cancel;
        confirm=(Button)dialog.findViewById(R.id.confirm);
        cancel=(Button)dialog.findViewById(R.id.cancel);
        dialog.show();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Connection connection=new Connection();
               connection.execute(CANCELURL+ticketcode+"/"+allocationid+"/"+userid+"/"+amount+"/"+seats+"/"+date);
                Toast.makeText(c, CANCELURL+ticketcode+"/"+allocationid+"/"+userid+"/"+amount+"/"+seats+"/"+date, Toast.LENGTH_SHORT).show();
                dialog.dismiss();


               

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public  void fetchData(){
        connection=new Connection();
        try {
            jsonString=connection.execute(HISTORYBOOKING+"/"+userid).get();

            arrayList=new ArrayList<Ticket>(new BookingHelper().execute(jsonString).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        BookingAdapter adapter=new BookingAdapter(this,R.layout.tickecitem,arrayList);
        listView.setAdapter(adapter);


    }

}
