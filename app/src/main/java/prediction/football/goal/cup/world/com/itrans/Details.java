package prediction.football.goal.cup.world.com.itrans;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static prediction.football.goal.cup.world.com.itrans.Constanst.CUSTOMERDETAILS;

public class Details extends AppCompatActivity {
    String fname,lname,scity,sgender,snationalid;
    EditText firstname,lastname,city,gender,nationalid;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        firstname=(EditText)findViewById(R.id.firstname);
        lastname=(EditText)findViewById(R.id.lastname);
        city=(EditText)findViewById(R.id.city);

        gender=(EditText)findViewById(R.id.gender);
        nationalid=(EditText)findViewById(R.id.nationalid);

        save=(Button)findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

    }
    //method to get data
    public void getData(){
        fname=firstname.getText().toString().trim();
        lname=lastname.getText().toString().trim();

        scity=city.getText().toString().trim();
        sgender=gender.getText().toString().trim();
        snationalid=nationalid.getText().toString().trim();

        if(!fname.isEmpty()&&!lname.isEmpty()&&!scity.isEmpty()&&!sgender.isEmpty()&&!snationalid.isEmpty()){
            uploadData();
        }else {
            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }

    }
    //method to upload data
    public void uploadData(){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait..");
        progressDialog.show();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Connection connection=new Connection();
                try {
                    String phone=getUserPhone();
                    Toast.makeText(Details.this, phone, Toast.LENGTH_SHORT).show();
                    String parser=CUSTOMERDETAILS+phone+"/"+fname+"/"+lname+"/"+snationalid+"/"+sgender+"/"+scity;
                    nationalid.setText(parser);
                    String result =connection.execute(parser).get();
                    Toast.makeText(Details.this, result, Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(Details.this, e+"", Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();

               startActivity(new Intent(Details.this,Login.class));
            }
        };
        Handler handler=new Handler();
        handler.post(runnable);
    }

    //get the user phone from the registration

    public String  getUserPhone(){
        Bundle extras=getIntent().getExtras();
        String phone=extras.getString("phone");

                return phone;

    }

}
