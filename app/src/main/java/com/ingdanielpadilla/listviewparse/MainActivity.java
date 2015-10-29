package com.ingdanielpadilla.listviewparse;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    private List<ParseObject> ob;
    ArrayList values= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mLv=(ListView)findViewById(R.id.listView);



        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
                Log.d("Desarrollo", "Texto " + (String) view.getTag());
            }
        });
    }

    public void SendIt(View view) {
        new SendData().execute();
    }
    public void GetIt(View view) {
        new GetData().execute();
    }

    private class SendData extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            ParseObject testObject = new ParseObject("DataEntry");
            testObject.put("first","Daniel");
            testObject.put("last","Padilla");
            testObject.put("gender", "M");
            testObject.saveInBackground();
            return null;
        }
    }
    private  class GetData extends AsyncTask<Void,Void,Void> {

        protected void onPreExecuted(){
            super.onPreExecute();
            ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setTitle("Cargando base de datos");
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {


            try{
                ParseQuery<ParseObject> query= new ParseQuery<ParseObject>("Data Entry");
                ob= query.find();
                for(ParseObject dato : ob){
                    values.add(dato.get("first")+" "+dato.get("last"));
                }
            }catch(ParseException e){
                Log.e("Error", e.getMessage());
                e.printStackTrace();

            }
            return null;
        }

        protected void onPostExecute(Void result){
            CustomAdapter adapter=new CustomAdapter(MainActivity.this,values);
            mLv.setAdapter(adapter);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
