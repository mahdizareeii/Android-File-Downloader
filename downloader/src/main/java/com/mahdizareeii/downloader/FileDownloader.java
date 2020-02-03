package com.mahdizareeii.downloader;

public class FileDownloader {

    private DownloadAsyncTask downloadAsyncTask;
    private String fileDownloadUrl, fileStorageUrl;

    public FileDownloader(String fileDownloadUrl, String fileStorageUrl) {
        this.fileDownloadUrl = fileDownloadUrl;
        this.fileStorageUrl = fileStorageUrl;
    }

    public void downloadFile(OnFileDownloaderListener onFileDownloaderListener) {
        downloadAsyncTask = new DownloadAsyncTask(fileStorageUrl, onFileDownloaderListener);
        downloadAsyncTask.execute(fileDownloadUrl);
    }

    public void cancelDownload() {
        downloadAsyncTask.cancel(true);
    }
}
