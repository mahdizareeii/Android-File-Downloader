package com.mahdizareeii.downloader.aSyncTasks;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.mahdizareeii.downloader.DownloadState;
import com.mahdizareeii.downloader.interfaces.OnFileDownloadListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FullDownloadAsyncTask extends AsyncTask<String, Integer, String> {

    private String fileStorageUrl;
    private OnFileDownloadListener onFileDownloadListener;

    public FullDownloadAsyncTask(String fileStorageUrl, OnFileDownloadListener onFileDownloadListener) {
        this.fileStorageUrl = fileStorageUrl;
        this.onFileDownloadListener = onFileDownloadListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onFileDownloadListener.onStart();
    }

    @Override
    protected String doInBackground(String... urls) {
        return download(urls[0], fileStorageUrl);
    }

    private String download(String fileUrl, String storageUrl) {
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        URLConnection urlConnection = null;

        try {
            URL url = new URL(fileUrl);
            urlConnection = url.openConnection();
            urlConnection.connect();

            int fileLength = urlConnection.getContentLength();
            byte[] data = new byte[fileLength];

            dataInputStream = new DataInputStream(url.openStream());
            dataInputStream.readFully(data);
            dataInputStream.close();
            if (!isCancelled()) {
                dataOutputStream = new DataOutputStream(new FileOutputStream(storageUrl));
                dataOutputStream.write(data);
                dataOutputStream.flush();
                dataOutputStream.close();
                onFileDownloadListener.onDownloaded(new DownloadState(true, "Download Successful"));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            onError(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            onError(e.getMessage());
        }
        return null;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        onFileDownloadListener.onProgressUpdate(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    private void onError(final String message) {
        new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                onFileDownloadListener.onError(message);
            }
        };
    }
}
