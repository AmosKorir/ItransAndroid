package prediction.football.goal.cup.world.com.itrans;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static prediction.football.goal.cup.world.com.itrans.Constanst.REGISTERURL;

public class Register extends AppCompatActivity {
    EditText username,password,phone,email,cornfirm;
    String userstr,passwordstr,phonestr,emailstr,confirmstr;
    Button register;
    Boolean mailValid=false;
    Boolean phoneValid=false;

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
        isEmailValid(emailstr);
        isPhoneValid(phonestr);

        if(!userstr.isEmpty() && !passwordstr.isEmpty() && !phonestr.isEmpty() && !emailstr.isEmpty() && !confirmstr.isEmpty()){



            if(passwordstr.equals(confirmstr)) {

                if(mailValid){
                    if(phoneValid) {

                        Connection direconection = new Connection();
                        try {

                            String output = direconection.execute(REGISTERURL + "/" + phonestr + "/" + userstr + "/" + passwordstr + "/" + emailstr).get();


                            if (output.equals("successfull")) {
                                finish();
                                startActivity(new Intent(Register.this, Details.class)
                                        .putExtra("phone", phonestr));
                                finish();
                            } else if (output.equals("unsuccessfull")) {
                                Toast.makeText(this, "User with the same number exist", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Sorry an error was encountered", Toast.LENGTH_SHORT).show();
                            }

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
                        Toast.makeText(this, "Enter correct Phone number", Toast.LENGTH_SHORT).show();
                    }
            }
            else {Toast.makeText(this, "Enter Valid email", Toast.LENGTH_SHORT).show();}
            }else{
                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }

    }

    public void isEmailValid(String emailstr){
        if (email == null) {

        } else {
            if( android.util.Patterns.EMAIL_ADDRESS.matcher(emailstr).matches()){
                mailValid=true;
            };
        }
    }
    public void   isPhoneValid(String phone){
        Pattern pattern = Pattern.compile("^(?:254|\\+254|0)?(7(?:(?:[129][0-9])|(?:0[0-8])|(4[0-1]))[0-9]{6})$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            phone = "254" + matcher.group(1);
            phoneValid=true;
        }else {
             pattern = Pattern.compile("^(?:254|\\+254|0)?(7(?:(?:[3][0-9])|(?:5[0-6])|(8[5-9]))[0-9]{6})$");
             matcher = pattern.matcher(phone);
            if (matcher.matches()) {
                phone = "254" + matcher.group(1);
                phoneValid=true;
            }else {
                pattern = Pattern.compile("^(?:254|\\+254|0)?(77[0-6][0-9]{6})$");
                matcher = pattern.matcher(phone);
                matcher = pattern.matcher(phone);
                if (matcher.matches()) {
                    phone = "254" + matcher.group(1);
                    phoneValid=true;
                }
            }
        }

        phonestr=phone;
    }
}
