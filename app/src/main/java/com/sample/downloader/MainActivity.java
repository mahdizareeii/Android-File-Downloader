package com.sample.downloader;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mahdizareeii.downloader.DownloadState;
import com.mahdizareeii.downloader.FileDownloader;
import com.mahdizareeii.downloader.interfaces.OnFileDownloadCancelListener;
import com.mahdizareeii.downloader.interfaces.OnFileDownloadListener;

public class MainActivity extends AppCompatActivity {

    private FileDownloader fileDownloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button streamDownload = findViewById(R.id.streamDownload);
        final Button fullDownload = findViewById(R.id.fullDownload);
        final Button cancelDownload = findViewById(R.id.cancelDownload);

        streamDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                streamDownload();
            }
        });
        fullDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullDownload();
            }
        });
        cancelDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDownload();
            }
        });
    }

    private void streamDownload() {
        //url of file
        String fileDownloadUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";

        //direction of downloadedFile
        String fileStorageDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hello_stream_download.png";

        //get instance of FileDownloader
        fileDownloader = new FileDownloader(fileDownloadUrl, fileStorageDir);

        //download file
        fileDownloader.streamDownloadFile(new OnFileDownloadListener() {
            @Override
            public void onStart() {
                Toast.makeText(MainActivity.this, "Download Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgressUpdate(int progress) {
                Log.i("onProgressUpdate:", "Downloading Stream: " + progress);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloaded(DownloadState downloadState) {
                if (downloadState.isSuccessfully()) {
                    Toast.makeText(MainActivity.this, downloadState.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, downloadState.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fullDownload() {
        //url of file
        String fileDownloadUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";

        //direction of downloadedFile
        String fileStorageDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hello_full_download.png";

        //get instance of FileDownloader
        fileDownloader = new FileDownloader(fileDownloadUrl, fileStorageDir);

        //download file
        fileDownloader.fullDownloadFile(new OnFileDownloadListener() {
            @Override
            public void onStart() {
                Toast.makeText(MainActivity.this, "Download Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgressUpdate(int progress) {
                Log.i("onProgressUpdate:", "Downloading full: " + progress);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloaded(DownloadState downloadState) {
                if (downloadState.isSuccessfully()) {
                    Toast.makeText(MainActivity.this, downloadState.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, downloadState.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cancelDownload() {

        //cancel download file
        if (fileDownloader != null) {
            fileDownloader.cancelStreamDownload(new OnFileDownloadCancelListener() {
                @Override
                public void onCancel() {
                    Toast.makeText(MainActivity.this, "Download Stream Canceled", Toast.LENGTH_SHORT).show();
                }
            });

            fileDownloader.cancelFullDownload(new OnFileDownloadCancelListener() {
                @Override
                public void onCancel() {
                    Toast.makeText(MainActivity.this, "Download full Canceled", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
