package mobile.project.bzu.assignementgroup3;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ReadStudent extends AppCompatActivity {
   ListView l1;
    List<String> ds;
    List<String> ds2;
    RecyclerView recyclerView,rs2;
    RradAdapter adapter;
    adapterw adapter2;
    public static Context context;
    EditText stud;
    EditText teac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read);
      //l1=findViewById(R.id.list1);
        context=this;
        recyclerView = findViewById(R.id.re);
        rs2= findViewById(R.id.ret2);
       ds=new ArrayList<String>();
        ds2=new ArrayList<String>();
        stud = findViewById(R.id.sidT);
        teac = findViewById(R.id.tidT);
    }

    public void readstudent(View view) {



        String url = " http://192.168.1.17/android/ReadData.php?cat=" + stud.getText();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            ReadStudent.DownloadTextTask runner = new ReadStudent.DownloadTextTask();
            runner.execute(url);
        }



    }
    public void TECHER(View view) {
        String url = " http://192.168.1.17/android/RadTacher.php?cat=" + teac.getText();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            ReadStudent.DownloadTextTas2 runner = new ReadStudent.DownloadTextTas2();
            runner.execute(url);
        }

    }
    private InputStream OpenHttpConnection(String urlString) throws IOException
    {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }
        catch (Exception ex)
        {
            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }
        return in;
    }
    private InputStream OpenHttpConnection2(String urlString) throws IOException
    {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }
        catch (Exception ex)
        {
            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }
        return in;
    }
    private String DownloadText(String URL)
    {
        int BUFFER_SIZE = 2000;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
        } catch (IOException e) {
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }

        InputStreamReader isr = new InputStreamReader(in);
        int charRead;
        String str = "";
        char[] inputBuffer = new char[BUFFER_SIZE];
        try {
            while ((charRead = isr.read(inputBuffer))>0) {
                //---convert the chars to a String---
                String readString =
                        String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            in.close();
        } catch (IOException e) {
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }
        return str;
    }
    private String DownloadText2(String URL)
    {
        int BUFFER_SIZE = 2000;
        InputStream in = null;
        try {
            in = OpenHttpConnection2(URL);
        } catch (IOException e) {
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }

        InputStreamReader isr = new InputStreamReader(in);
        int charRead;
        String str = "";
        char[] inputBuffer = new char[BUFFER_SIZE];
        try {
            while ((charRead = isr.read(inputBuffer))>0) {
                //---convert the chars to a String---
                String readString =
                        String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            in.close();
        } catch (IOException e) {
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }
        return str;
    }



    private class DownloadTextTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return DownloadText(urls[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
          //  String[] books = result.split(",");
          //  String str = "";
          //  String s;
          //  for(String s : books){
         //      str+= s + "\n";
         //    }
            //
             // result;
          //  School ss =new School();

         //   ss.getStudentName();
            Log.d("TAGGGGGG", "calculateNow: " + result);
         //   ds.add(ss);
         //   Log.d("TAGGGGGG", "calculate: " + ds);
            ds.add(result);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            adapter = new RradAdapter(getApplicationContext(),ds);
            recyclerView.setAdapter(adapter);
         //   edtData.setText(result);
        }
    }
    private class DownloadTextTas2 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return DownloadText2(urls[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
            //  String[] books = result.split(",");
            //  String str = "";
            //  String s;
            //  for(String s : books){
            //      str+= s + "\n";
            //    }
            //
            // result;
            //  School ss =new School();

            //   ss.getStudentName();
            Log.d("TAGGGGGG", "calculateNow: " + result);
            //   ds.add(ss);
            //   Log.d("TAGGGGGG", "calculate: " + ds);
            ds2.add(result);
            rs2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            adapter2 = new adapterw(getApplicationContext(),ds2);
            rs2.setAdapter(adapter2);
            //   edtData.setText(result);
        }
    }
}
