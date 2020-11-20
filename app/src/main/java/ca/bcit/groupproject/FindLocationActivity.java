package ca.bcit.groupproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FindLocationActivity extends AppCompatActivity {

    private EditText postalCode;
    private Button findWithAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_location);

        postalCode = findViewById(R.id.address);
        findWithAddress = findViewById(R.id.findWithAddress);

        findWithAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findWithAddress();
            }
        });

    }

    private void findWithAddress() {
        String userLocation = postalCode.getText().toString().trim();

        if (TextUtils.isEmpty(userLocation)) {
            Toast.makeText(this, "This field can't be empty", Toast.LENGTH_LONG).show();
            return;
        }

    }

}