package com.mahdizareeii.downloader;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadAsyncTask extends AsyncTask<String, Integer, String> {

    private String fileStorageUrl;
    private OnFileDownloaderListener onFileDownloaderListener;

    public DownloadAsyncTask(String fileStorageUrl, OnFileDownloaderListener fileDownloader) {
        this.fileStorageUrl = fileStorageUrl;
        this.onFileDownloaderListener = fileDownloader;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onFileDownloaderListener.onStart();
    }

    @Override
    protected String doInBackground(String... urls) {
        return download(urls[0], fileStorageUrl);
    }

    private String download(String fileUrl, String storageUrl) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(fileUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                onError(httpURLConnection.getResponseMessage() + " | " + httpURLConnection.getResponseCode());
                return String.valueOf(httpURLConnection.getResponseCode());
            }
            int fileLength = httpURLConnection.getContentLength();
            inputStream = httpURLConnection.getInputStream();
            outputStream = new FileOutputStream(storageUrl);
            byte[] data = new byte[4096];
            long total = 0;
            int count;
            while ((count = inputStream.read(data)) != -1) {
                if (isCancelled()) {
                    inputStream.close();
                    return null;
                }
                total += count;

                if (fileLength > 0)
                    publishProgress((int) (total * 100 / fileLength));

                outputStream.write(data, 0, count);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            onError(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            onError(e.getMessage());
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();

                if (outputStream != null)
                    outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
                onError(e.getMessage());
            }

            if (httpURLConnection != null)
                httpURLConnection.disconnect();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        onFileDownloaderListener.onProgressUpdate(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            onFileDownloaderListener.onDownloaded(new DownloadState(false, s));
        } else {
            onFileDownloaderListener.onDownloaded(new DownloadState(true, "Download Successful"));
        }
    }

    private void onError(final String message) {
        new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                onFileDownloaderListener.onError(message);
            }
        };
    }
}
