package prediction.football.goal.cup.world.com.itrans;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

import static prediction.football.goal.cup.world.com.itrans.Constanst.BOOKURL;

public class Booking extends AppCompatActivity {
    Button datebtn,cancel, confirm;
    EditText  numbers;

    String allocationid,date;
    int nseats,fare;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
            context=this;
            Bundle extras=getIntent().getExtras();
            allocationid=extras.getString("allocationid");
            fare=extras.getInt("fare");

         numbers=(EditText)findViewById(R.id.seats);
         confirm=(Button)findViewById(R.id.confirm);
         cancel=(Button)findViewById(R.id.cancel);
         datebtn=(Button)findViewById(R.id.datebtn);

        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date=datebtn.getText().toString();

            nseats= Integer.parseInt(numbers.getText().toString());
                try {
                    book(context,allocationid,fare,nseats,date);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    //booking function
    public  void book(Context ctx, String allocationid, int amount, int nseats, String date) throws ExecutionException, InterruptedException {

        amount=amount*nseats;
        String lastseat=String.valueOf(nseats);
        String lastamount= String.valueOf(amount);
        String userid;
        //get the user id
        User user=new User();
        SharedPreferences sharedPreferences= ctx.getSharedPreferences("login",MODE_PRIVATE);
        String userdata=sharedPreferences.getString("user",null);
        UserHelper helper=new UserHelper();
        user=helper.execute(userdata).get();
         userid=userdata;
        Toast.makeText(ctx, userid, Toast.LENGTH_SHORT).show();
        //make booking connection;
        String url=BOOKURL+"/"+allocationid+"/"+userid+"/"+lastseat+"/"+lastamount+"/"+date;
        numbers.setText(url);
        Connection connection=new Connection();
        connection.execute(url);


    }

    //method to create the dialog
    public void createDialog(){

        final Dialog datedialog=new Dialog(this);
        datedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        datedialog.setContentView(R.layout.datedialog);
        final DatePicker datePicker=(DatePicker)datedialog.findViewById(R.id.datepicker);
        Button ok=(Button)datedialog.findViewById(R.id.okbtn);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //process the date to match the database format
                String s = datePicker.getYear() + "-" + getDigit(datePicker.getMonth()+1) + "-" + getDigit(datePicker.getDayOfMonth());

                datebtn.setText(s);
                datedialog.dismiss();

            }
        });
        datedialog.show();;
    }


    public static void createDate(Context ctx){
        Dialog dialog=new Dialog(ctx);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialoglayout);
        dialog.setCancelable(true);

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

    //method to format date to have 0 at the begining if less than ten

    public String getDigit(int digit){
        if(digit<10){
            return "0"+digit;
        }
        else {
            return digit+"";
        }
    }
}
