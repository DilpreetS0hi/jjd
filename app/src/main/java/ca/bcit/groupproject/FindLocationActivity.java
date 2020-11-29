package ca.bcit.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindLocationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "postalCode";
    private EditText postalCodeEditText;

    public FindLocationActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_location);
        final TextView greeting = findViewById(R.id.greeting);
        postalCodeEditText = findViewById(R.id.address);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        assert user != null;
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.child(userId).addValueEventListener((new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String fullName = Objects.requireNonNull(snapshot.child("fullName").getValue()).toString();
                    String postalCode = Objects.requireNonNull(snapshot.child("postalCode").getValue()).toString();
                    greeting.setText("Hello, " + fullName + ".");
                    postalCodeEditText.setText(postalCode);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                }
            }));
        }
        greeting.setText("Hello, Guest.");

        Button findWithAddress = findViewById(R.id.findWithAddress);
        findWithAddress.setOnClickListener(this);
    }

    private void findWithAddress() {
        String userLocation = postalCodeEditText.getText().toString().toUpperCase();
//        String regex = "/^(?:[ABCEGHJ-NPRSTVXY]\\d[A-Z][ -]?\\d[A-Z]\\d)$/i";
//        String regex = "/^[A-Za-z]\\d[A-Za-z][ -]?\\d[A-Za-z]\\d$/";
        String regex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
        Pattern pattern = Pattern.compile(regex);

        if (TextUtils.isEmpty(userLocation)) {
            Toast.makeText(this, "This field can't be empty", Toast.LENGTH_LONG).show();
            return;
        }
        Log.i(TAG, "postalCode: " + userLocation);
        Matcher matcher = pattern.matcher(userLocation);
        if (!matcher.matches()) {
            Toast.makeText(this, "Invalid form of postal code", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("userLocation", userLocation);
        startActivity(intent);

//        startActivity(new Intent(this, MapsActivity.class));
    }

    @Override
    public void onClick(View v) {
        findWithAddress();
//        startActivity(new Intent(this, MapsActivity.class));

    }
}