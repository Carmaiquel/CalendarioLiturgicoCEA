package santiagocaride.calendarioliturgicocea;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Firebase mrootRef;
    TextView fecha_numero;
    TextView fecha_semana;
    TextView primeraLectura1;
    //    TextView primeraLectura2;
//    TextView salmo1;
//    TextView salmo2;
    TextView segundaLectura1;
//    TextView segundaLectura2;
//    TextView evangelio1;
//    TextView evangelio2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        mrootRef = new Firebase("https://calendario-liturgico-3fa98.firebaseio.com/");

        fecha_numero = (TextView) findViewById(R.id.fecha_numero);
        fecha_semana = (TextView) findViewById(R.id.fecha_semana);
        primeraLectura1 = (TextView) findViewById(R.id.primeralectura1);
//        primeraLectura2 = (TextView) findViewById(R.id.primeralectura2);
//        salmo1 = (TextView) findViewById(R.id.salmo1);
//        salmo2 = (TextView) findViewById(R.id.salmo2);
        segundaLectura1 = (TextView) findViewById(R.id.segundalectura1);
//        segundaLectura2 = (TextView) findViewById(R.id.segundalectura2);
//        evangelio1 = (TextView) findViewById(R.id.evangelio1);
//        evangelio2 = (TextView) findViewById(R.id.evangelio2);

    }

    @Override
    protected void onStart() {
        super.onStart();


        Firebase messagesRef = mrootRef.child("version");
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

        // Poner la fecha de hoy

        Context context = this;
        TextView fecha_numero = (TextView) findViewById(R.id.fecha_numero);
        Date myDate = new Date();
        SimpleDateFormat sm = new SimpleDateFormat("dd" + " " + "EEEE", new Locale("es", "ES"));
        String strDate = sm.format(myDate);
        fecha_numero.setText(strDate);

        // Obtener la diferencia entre Pascua y la fecha de hoy

        // Obtener la fecha de Pascua

        TextView segundaLectura1 = (TextView) findViewById(R.id.segundalectura1);
        SimpleDateFormat anio = new SimpleDateFormat("yyyy");
        String aniopascua = anio.format(myDate);
        int year = Integer.parseInt(aniopascua);
        getEasterSundayDate(year);
        segundaLectura1.setText(year);

    }
    public static String getEasterSundayDate(int year) {
        int a = year % 19,
                b = year / 100,
                c = year % 100,
                d = b / 4,
                e = b % 4,
                g = (8 * b + 13) / 25,
                h = (19 * a + b - d - g + 15) % 30,
                j = c / 4,
                k = c % 4,
                m = (a + 11 * h) / 319,
                r = (2 * e + 2 * j - k - h + m + 32) % 7,
                n = (h - m + r + 90) / 25,
                p = (h - m + r + n + 19) % 32;

        String result;
        switch (n) {
            case 1:
                result = "January ";
                break;
            case 2:
                result = "February ";
                break;
            case 3:
                result = "March ";
                break;
            case 4:
                result = "April ";
                break;
            case 5:
                result = "May ";
                break;
            case 6:
                result = "June ";
                break;
            case 7:
                result = "July ";
                break;
            case 8:
                result = "August ";
                break;
            case 9:
                result = "September ";
                break;
            case 10:
                result = "October ";
                break;
            case 11:
                result = "November ";
                break;
            case 12:
                result = "December ";
                break;
            default:
                result = "error";
        }

        return result + p;
    }

}


