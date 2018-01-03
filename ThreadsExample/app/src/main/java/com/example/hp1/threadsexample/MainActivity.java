package com.example.hp1.threadsexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar1, progressBar2;
    TextView status1, status2, percent1, percent2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        status1 = (TextView) findViewById(R.id.status1);
        status2 = (TextView) findViewById(R.id.status2);
        percent1 = (TextView) findViewById(R.id.percent1);
        percent2 = (TextView) findViewById(R.id.percent2);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            AsyncTask task1=null, task2=null;
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                String btnText = btn.getText().toString();
                if (btnText.equalsIgnoreCase("Start All Downloads")) {
                    Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
                    task1 = new DownloadTask(progressBar1, status1, percent1)
                            .executeOnExecutor(executor, null);
                    task2 = new DownloadTask(progressBar2, status2, percent2)
                            .executeOnExecutor(executor, null);
                    button.setText("Cancel All Downloads");
                }
                if (btnText.equalsIgnoreCase("Cancel All Downloads")) {
                    task1.cancel(true);
                    task2.cancel(true);
                    button.setText("Cancel All Downloads");
                }
            }
        });
    }

    public class DownloadTask extends AsyncTask<Void, Integer, Void>{

        int max;
        ProgressBar progressBar;
        TextView statusView;
        TextView percentView;

        public DownloadTask(ProgressBar bar, TextView text, TextView percent){
            progressBar = bar;
            statusView = text;
            percentView = percent;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            max = progressBar.getMax();
            statusView.setText("Downloading...");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=1; i<=max; i++){
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(isCancelled()) break;
                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            percentView.setText((values[0]*100/max)+"%");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            statusView.setText("Download completed.");
            button.setText("Start All Downloads");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            statusView.setText("Cancelled...");
            button.setText("Start All Downloads");
        }
    }
}