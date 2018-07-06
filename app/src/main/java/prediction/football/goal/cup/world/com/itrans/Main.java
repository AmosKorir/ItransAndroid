package prediction.football.goal.cup.world.com.itrans;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static prediction.football.goal.cup.world.com.itrans.Constanst.BALANCE;
import static prediction.football.goal.cup.world.com.itrans.Constanst.BOOKURL;
import static prediction.football.goal.cup.world.com.itrans.Constanst.BUSDEFAULT;
import static prediction.football.goal.cup.world.com.itrans.Constanst.BUSES;
import static prediction.football.goal.cup.world.com.itrans.Constanst.STATIONA;
import static prediction.football.goal.cup.world.com.itrans.Constanst.STATIONB;
import static prediction.football.goal.cup.world.com.itrans.Constanst.TIMES;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView listView;
    ArrayList<Model> arrayList;
    String jsonarray="[]",stationA,stationB,Times;
    Connection connection,connectionA,connectionB,connectionT;

    ArrayAdapter adapter,adapter2,adapter3;
    ArrayList stationas,stationbs,times;

    Spinner spinnerFrom,spinnerTo,spinnerTime;
    CustomAdapter adaptern;

    //string for selected search
    String selectFrom,selectTo,selectTime;

    //booking data
    String date;
    String userid;
    String balance;
    static Button datebtn;
    TextView BalanceText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       listView=(ListView)findViewById(R.id.listview);
       spinnerFrom=(Spinner)findViewById(R.id.spinnerfrom);
       spinnerTo=(Spinner)findViewById(R.id.spinnerto);
       spinnerTime=(Spinner)findViewById(R.id.spinnertime);
       BalanceText=(TextView)findViewById(R.id.balance);


        SharedPreferences sharedPreferences= getSharedPreferences("login",MODE_PRIVATE);
        userid=sharedPreferences.getString("user",null);


        /*run the method to get the balance*/

        getBalance(userid);



        connection=new Connection();
        connectionA=new Connection();
        connectionB=new Connection();
        connectionT=new Connection();

        try {

            jsonarray=connection.execute(BUSDEFAULT).get();
            stationA=connectionA.execute(STATIONA).get();
            stationB=connectionB.execute(STATIONB).get();
            Times=connectionT.execute(TIMES).get();


        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
        }
        try {
            arrayList=new ArrayList<Model>(new ModelHelper().execute(jsonarray).get());
        } catch (InterruptedException e) {
            Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (ExecutionException e) {
            Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


        try {
            stationas=new ArrayList(new ArrayHelper().execute(stationA,"A").get());
            stationbs=new ArrayList(new ArrayHelper().execute(stationB,"B").get());
            times=new ArrayList(new ArrayHelper().execute(Times,"T").get());

        } catch (InterruptedException e) {
            Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
        }


         adaptern=new CustomAdapter(Main.this,R.layout.item,arrayList);
         listView.setAdapter(adaptern);

        adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, stationas);
        adapter2=new ArrayAdapter(this, android.R.layout.simple_list_item_1, stationbs);
        adapter3=new ArrayAdapter(this, android.R.layout.simple_list_item_1, times);

        //set the spinner

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter2);
        spinnerTime.setAdapter(adapter3);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectFrom=spinnerFrom.getSelectedItem().toString();
                fetch();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectTo=spinnerTo.getSelectedItem().toString();
                fetch();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectTime=spinnerTime.getSelectedItem().toString();
                fetch();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


































        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(Main.this,Login.class));
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(Main.this,HistoryBooking.class));
        } else if (id == R.id.nav_gallery) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //method to refesh an fetch the list

    public void fetch(){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {


        Connection connection=new Connection();
        String current;
        try {
            current=connection.execute(BUSES+"/"+selectFrom+"/"+selectTo+"/"+selectTime).get();
            ModelHelper modelHelper=new ModelHelper();

            arrayList=new ArrayList(modelHelper.execute(current).get());

            adaptern=new CustomAdapter(Main.this,R.layout.item,arrayList);
            listView.setAdapter(adaptern);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

            }
        };
        Handler handler=new Handler();
        handler.post(runnable);

    }

    //method to create dialog for booking t
//    the following method is no longer in use.

    public static void createBook(final Context ctx, String allocationid, int fare){
        final Dialog dialog=new Dialog(ctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialoglayout);
        dialog.setCancelable(false);


        EditText editText=(EditText)dialog.findViewById(R.id.seats);
        Button confirm=(Button)dialog.findViewById(R.id.confirm);
        Button cancel=(Button)dialog.findViewById(R.id.cancel);
        datebtn=(Button)dialog.findViewById(R.id.datebtn);

        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              createDate(ctx);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        dialog.show();
    }

    //method to create the date dialog

    public static void createDate(Context ctx){
        Dialog dialog=new Dialog(ctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialoglayout);
        dialog.setCancelable(false);

        final DatePicker datePicker=(DatePicker)dialog.findViewById(R.id.datepicker);
        Button ok=(Button)dialog.findViewById(R.id.okbtn);
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String s = datePicker.getYear() + "-" + datePicker.getMonth() + "-" + datePicker.getDayOfMonth();
//                datebtn.setText(s);
//
//            }
//        });
        dialog.show();
    }

    //method to the balance of the customer
    private void getBalance(final String userid){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Connection balanceConnection=new Connection();
                try {
                    balance= balanceConnection.execute(BALANCE+userid).get();
                    BalanceText.setText("Balance"+ ":"+balance);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        Handler handler=new Handler();
        handler.post(runnable);
    }


    /*method to logout*/
    public void logout(){
        SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("user","");
        editor.commit();
        startActivity(new Intent(Main.this,Login.class));
        finish();
    }













}
