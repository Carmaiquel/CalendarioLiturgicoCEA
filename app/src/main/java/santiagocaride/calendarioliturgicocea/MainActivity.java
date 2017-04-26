package santiagocaride.calendarioliturgicocea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    Firebase mrootRef;
    TextView primeraLectura1;
    TextView primeraLectura2;
    TextView salmo1;
    TextView salmo2;
    TextView segundaLectura1;
    TextView segundaLectura2;
    TextView evangelio1;
    TextView evangelio2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        mrootRef = new Firebase("https://calendario-liturgico-3fa98.firebaseio.com/");

        primeraLectura1 = (TextView) findViewById(R.id.primeralectura1);
        primeraLectura2 = (TextView) findViewById(R.id.primeralectura2);
        salmo1 = (TextView) findViewById(R.id.salmo1);
        salmo2 = (TextView) findViewById(R.id.salmo2);
        segundaLectura1 = (TextView) findViewById(R.id.segundalectura1);
        segundaLectura2 = (TextView) findViewById(R.id.segundalectura2);
        evangelio1 = (TextView) findViewById(R.id.evangelio1);
        evangelio2 = (TextView) findViewById(R.id.evangelio2);

    }

    @Override
    protected void onStart() {
        super.onStart();


        Firebase messagesRef = mrootRef.child("Movil");
        messagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message = dataSnapshot.getValue(String.class);
                Log.v("E_VALUE", message);
                primeraLectura1.setText(message);
            }

            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
}
