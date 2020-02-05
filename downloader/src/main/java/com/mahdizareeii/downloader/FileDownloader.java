package com.mahdizareeii.downloader;

import com.mahdizareeii.downloader.interfaces.OnFileDownloadCancelListener;
import com.mahdizareeii.downloader.interfaces.OnFileDownloadListener;

public class FileDownloader {

    private DownloadAsyncTask downloadAsyncTask;
    private String fileDownloadUrl, fileStorageUrl;

    public FileDownloader(String fileDownloadUrl, String fileStorageUrl) {
        this.fileDownloadUrl = fileDownloadUrl;
        this.fileStorageUrl = fileStorageUrl;
    }

    public void downloadFile(OnFileDownloadListener onFileDownloadListener) {
        downloadAsyncTask = new DownloadAsyncTask(fileStorageUrl, onFileDownloadListener);
        downloadAsyncTask.execute(fileDownloadUrl);
    }

    public void cancelDownload(OnFileDownloadCancelListener onFileDownloadListener) {
        onFileDownloadListener.onCancel();
        if (downloadAsyncTask != null)
            downloadAsyncTask.cancel(true);
    }
}
