package mobile.project.bzu.assignementgroup3;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Main2 extends AppCompatActivity {

    private Spinner rate;
    private Spinner course;
    private EditText name;
    private EditText id;
    private EditText cteacher;
    String postID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        rate=findViewById(R.id.rat);
        course=findViewById(R.id.cors);
        rate2mothed();
        spineer2mothed();
        setUpViews();

    }

    private void spineer2mothed() {
        ArrayList<String> aray =new ArrayList<>();
        aray.add("Sciences");
        aray.add("Math");
        aray.add("chemistry");
        aray.add("Physics");

        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,aray);
        course.setAdapter(arrayadapter);
    }
    private void rate2mothed() {
        ArrayList<String> aray =new ArrayList<>();
        aray.add("Good");
        aray.add("Very Good");
        aray.add("Excellent");


        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,aray);
        rate.setAdapter(arrayadapter);
    }

    private void setUpViews() {


        name=findViewById(R.id.teachernameT);
        id=findViewById(R.id.teacheridT);

    }

    private String processRequest(String restUrl) throws UnsupportedEncodingException {


        String s1= course.getSelectedItem().toString().trim();
        String s2 = rate.getSelectedItem().toString().trim();
        String s3 = name.getText().toString();
        String s4 = id.getText().toString();


        //school s =new school ();
        //  s.setStudentName(namrA);
        // s.getStudentID();
        // int sg=s.getStudentID();
        String data = URLEncoder.encode("TeacherName", "UTF-8")
                + "=" + URLEncoder.encode(s3, "UTF-8");
        data += "&" + URLEncoder.encode("TeacherID", "UTF-8")
                + "=" + URLEncoder.encode(s4, "UTF-8");
        data += "&" + URLEncoder.encode("Courses", "UTF-8")
                + "=" + URLEncoder.encode(s1, "UTF-8");
        data += "&" + URLEncoder.encode("Evaluation", "UTF-8")
                + "=" + URLEncoder.encode(s2, "UTF-8");





        String text = "";
        BufferedReader reader=null;

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






    public void next(View view) {
        Intent intent;
        intent = new Intent(this, ReadStudent.class);
        // String s1 = m.getName();
        // intent.putExtra("Name",s1);
        startActivity(intent);
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

            Toast.makeText(Main2.this, result, Toast.LENGTH_SHORT).show();
        }
    }

    public void save(View view) {

        String restUrl = "http://192.168.1.17/android/InsertData2.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            Main2.SendPostRequest runner = new Main2.SendPostRequest();
            runner.execute(restUrl);
        }
    }
}
