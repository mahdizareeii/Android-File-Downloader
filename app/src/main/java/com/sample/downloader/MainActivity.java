package com.sample.downloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sample.downlaoder.DownloadState;
import com.sample.downlaoder.FileDownloader;
import com.sample.downlaoder.OnFileDownloaderListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.download);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download();
            }
        });
    }

    private void download() {
        //url of file
        String fileDownloadUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";

        //direction of downloadedFile
        String fileStorageDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hello.jpg";

        //get instance of FileDownloader
        FileDownloader fileDownloader = new FileDownloader(fileDownloadUrl, fileStorageDir);

        //download file
        fileDownloader.downloadFile(new OnFileDownloaderListener() {
            @Override
            public void onStart() {
                Toast.makeText(MainActivity.this, "Download Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgressUpdate(int progress) {
                Toast.makeText(MainActivity.this, "downloading : " + progress, Toast.LENGTH_SHORT).show();
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
}
