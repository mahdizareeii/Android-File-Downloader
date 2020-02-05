package com.mahdizareeii.downloader;

import com.mahdizareeii.downloader.aSyncTasks.FullDownloadAsyncTask;
import com.mahdizareeii.downloader.aSyncTasks.StreamDownloadAsyncTask;
import com.mahdizareeii.downloader.interfaces.OnFileDownloadCancelListener;
import com.mahdizareeii.downloader.interfaces.OnFileDownloadListener;

public class FileDownloader {

    private StreamDownloadAsyncTask streamDownloadAsyncTask;
    private FullDownloadAsyncTask fullDownloadAsyncTask;
    private String fileDownloadUrl, fileStorageUrl;

    public FileDownloader(String fileDownloadUrl, String fileStorageUrl) {
        this.fileDownloadUrl = fileDownloadUrl;
        this.fileStorageUrl = fileStorageUrl;
    }

    public void fullDownloadFile(OnFileDownloadListener onFileDownloadListener) {
        fullDownloadAsyncTask = new FullDownloadAsyncTask(fileStorageUrl, onFileDownloadListener);
        fullDownloadAsyncTask.execute(fileDownloadUrl);
    }

    public void streamDownloadFile(OnFileDownloadListener onFileDownloadListener) {
        streamDownloadAsyncTask = new StreamDownloadAsyncTask(fileStorageUrl, onFileDownloadListener);
        streamDownloadAsyncTask.execute(fileDownloadUrl);
    }

    public void cancelStreamDownload(OnFileDownloadCancelListener onFileDownloadListener) {
        if (streamDownloadAsyncTask != null) {
            onFileDownloadListener.onCancel();
            streamDownloadAsyncTask.cancel(true);
        }
    }

    public void cancelFullDownload(OnFileDownloadCancelListener onFileDownloadListener) {
        if (fullDownloadAsyncTask != null) {
            onFileDownloadListener.onCancel();
            fullDownloadAsyncTask.cancel(true);
        }
    }
}
