package prediction.football.goal.cup.world.com.itrans;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static prediction.football.goal.cup.world.com.itrans.Constanst.HISTORYBOOKING;

public class HistoryBooking extends AppCompatActivity {
    String jsonString;
    Connection connection;
    ArrayList<Ticket>arrayList=null;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.bookinglist);

        connection=new Connection();
        try {
            jsonString=connection.execute(HISTORYBOOKING+"/4").get();
            Toast.makeText(this, jsonString, Toast.LENGTH_SHORT).show();
            arrayList=new ArrayList<Ticket>(new BookingHelper().execute(jsonString).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        BookingAdapter adapter=new BookingAdapter(this,R.layout.tickecitem,arrayList);
        listView.setAdapter(adapter);




        //

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
