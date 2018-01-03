package com.example.hp1.json_to_list_example;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


public class DataDownloader extends AsyncTask<String, Void, Void> {

    private String TAG = "TESTLOG";
    Context context;
    ListView listView;
    ProgressBar progressBar;
    ArrayList<PersonData> usersList;

    public DataDownloader(Context ctx,
                          ListView lv, ProgressBar pBar) {
        context = ctx;
        progressBar = pBar;
        listView = lv;
        usersList = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context,
                "Requesting web content...",
                Toast.LENGTH_SHORT).show();
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            Toast.makeText(context,
                    "Connection error...",
                    Toast.LENGTH_SHORT).show();
            cancel(true);
        }
    }

    @Override
    protected Void doInBackground(String... url) {
        String jsonData = null;
        try {
            jsonData = downloadJSONData(url[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
//            Log.i(TAG, "Response from url: " + jsonStr);
        if (jsonData != null) {
            parseJSON(jsonData);
        } else {
            Log.e(TAG, "Couldn't get JSON");
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context,
                            "Couldn't get JSON!",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        MyListAdapter adapter = new MyListAdapter(context,
                R.layout.list_item, usersList);
        progressBar.setVisibility(View.GONE);
        listView.setAdapter(adapter);
    }

    private void parseJSON(String jsonData) {
        try {
            JSONObject jsonObj = new JSONObject(jsonData);
            JSONArray users = jsonObj.getJSONArray("results");
//                    Log.i(TAG, users.length()+"");
            // looping through All Contacts
            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);

                JSONObject name = user.getJSONObject("name");
                String fname = name.getString("first");
                String lname = name.getString("last");
                fname = fname.substring(0, 1).toUpperCase() + fname.substring(1);
                lname = lname.substring(0, 1).toUpperCase() + lname.substring(1);
                String fullName = fname + " " + lname;

                JSONObject login = user.getJSONObject("login");
                String username = login.getString("username");

                JSONObject picture = user.getJSONObject("picture");
                String pictureUrl = picture.getString("thumbnail");
//                        Log.i(TAG, pictureUrl);
                Bitmap bmp = downloadThumbnails(pictureUrl);
                usersList.add(new PersonData(
                        fullName, username, bmp
                ));
            }
        } catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context,
                            "Json parsing error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private String downloadJSONData(String urlString) throws IOException {
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            is = conn.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            String string = new String(total);
            return string;
        }
        finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private Bitmap downloadThumbnails(String url){
        URL imageUrl = null;
        InputStream is = null;
//            Log.i(TAG, "downloading image with URL: "+url);
        try {
            imageUrl = new URL(url);
            is = imageUrl.openConnection().getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(is);
    }

}
