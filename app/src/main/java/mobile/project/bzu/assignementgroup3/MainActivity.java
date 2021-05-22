package mobile.project.bzu.assignementgroup3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText name1;
    private EditText id1a;
    private EditText Date;
    private EditText Address;
    private EditText Phone;
    private EditText gsg;
    private Spinner sph;
    private Spinner sm;
    private Spinner spinnere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sph=findViewById(R.id.spinnerg);
        sm=findViewById(R.id.ahm);
        spinnere=findViewById(R.id.spinner2br);
        spineermothedgwnder();
        spineermothedgwndefr();
        spineer2mothed();
        setUpViews();
    }

    private void spineermothedgwnder() {
        ArrayList<String> aray =new ArrayList<>();
        aray.add("Male");
        aray.add("Female");
        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,aray);
        sph.setAdapter(arrayadapter);
    }


    private void spineermothedgwndefr() {
        ArrayList<String> aray =new ArrayList<>();
        aray.add("10");
        aray.add("11");
        aray.add("12");
        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,aray);
        sm.setAdapter(arrayadapter);
    }
    private void spineer2mothed() {
        ArrayList<String> aray =new ArrayList<>();
        aray.add("scientific");
        aray.add("literary");

        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,aray);
        spinnere.setAdapter(arrayadapter);
    }
    private void setUpViews() {

        name1=findViewById(R.id.nameT);
        id1a=findViewById(R.id.idT);

        Date=findViewById(R.id.dateg);

        Address=findViewById(R.id.objectivetxt);
        Phone=findViewById(R.id.phone);
        gsg=findViewById(R.id.gg);

    }
    private String processRequest(String restUrl) throws UnsupportedEncodingException {

        String namrA = name1.getText().toString();
        String  IDe = id1a.getText().toString();
        String s5 =sph.getSelectedItem().toString().trim();
        String Dates = Date.getText().toString();
        String Phonn = Phone.getText().toString();
        String Addresss = Address.getText().toString();
        String Gpa =gsg.getText().toString();
        String ss =sm.getSelectedItem().toString().trim();
        String s7 =spinnere.getSelectedItem().toString().trim();

        //  String namrA = String.valueOf(id1a.getText());
        //  s.setStudentName(namrA);

        String data = URLEncoder.encode("StudentName", "UTF-8")
                + "=" + URLEncoder.encode(namrA, "UTF-8");

         data += "&" + URLEncoder.encode("StudentID", "UTF-8") + "="
              + URLEncoder.encode(IDe, "UTF-8");

        data += "&" + URLEncoder.encode("Gender", "UTF-8")
                + "=" + URLEncoder.encode(s5, "UTF-8");


        data += "&" + URLEncoder.encode("DOB", "UTF-8")
                + "=" + URLEncoder.encode(Dates, "UTF-8");

        data += "&" + URLEncoder.encode("Phone", "UTF-8")
                + "=" + URLEncoder.encode(Phonn, "UTF-8");
        data += "&" + URLEncoder.encode("address", "UTF-8")
                + "=" + URLEncoder.encode(Addresss, "UTF-8");
        data += "&" + URLEncoder.encode("Gpa", "UTF-8")
                + "=" + URLEncoder.encode(Gpa, "UTF-8");
          data += "&" + URLEncoder.encode("Class", "UTF-8")
                + "=" + URLEncoder.encode(ss, "UTF-8");
        data += "&" + URLEncoder.encode("Branch", "UTF-8")
                + "=" + URLEncoder.encode(s7, "UTF-8");
                /*
          */

        String text = "";
        BufferedReader reader=null;
        //  s.setStudentID(ID);

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL(restUrl);

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        // Show response on activity
        return text;



    }



    private class SendPostRequest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return processRequest(urls[0]);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return "";
        }
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }

    public void add(View view) {
        String restUrl = "http://192.168.1.17/android/InsertData.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            MainActivity.SendPostRequest runner = new MainActivity.SendPostRequest();
            runner.execute(restUrl);
        }
    }

    public void next(View view) {
        Intent intent;
       intent = new Intent(this, Main2.class);
        //  intent.putExtra("StudentID", ddd);
        // String s1 = m.getName();
        // intent.putExtra("Name",s1);
        startActivity(intent);
    }
}