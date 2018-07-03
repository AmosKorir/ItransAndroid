package prediction.football.goal.cup.world.com.itrans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import static prediction.football.goal.cup.world.com.itrans.Constanst.REGISTERURL;

public class Register extends AppCompatActivity {
    EditText username,password,phone,email,cornfirm;
    String userstr,passwordstr,phonestr,emailstr,confirmstr;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        resource binding

        username=(EditText)findViewById(R.id.username);
        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        cornfirm=(EditText)findViewById(R.id.confirmpwd);
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInput();
            }
        });
    }

    //method to the user input

    public void getInput(){
        userstr=username.getText().toString().trim();
        passwordstr=password.getText().toString().trim();
        phonestr=phone.getText().toString().trim();
        emailstr=email.getText().toString().trim();
        confirmstr=cornfirm.getText().toString().trim();

        if(!userstr.isEmpty() && !passwordstr.isEmpty() && !phonestr.isEmpty() && !emailstr.isEmpty() && !confirmstr.isEmpty()){



            if(passwordstr.equals(confirmstr)) {

             Connection direconection=new Connection();
                try {

                    String output=direconection.execute(REGISTERURL+"/"+phonestr+"/"+userstr+"/"+passwordstr +"/"+emailstr).get();

                    username.setText(output);
                    Toast.makeText(this, output, Toast.LENGTH_SHORT).show();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


                username.setText("");
                password.setText("");
                phone.setText("");
                email.setText("");
                cornfirm.setText("");
            }else{
                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }

    }
}
