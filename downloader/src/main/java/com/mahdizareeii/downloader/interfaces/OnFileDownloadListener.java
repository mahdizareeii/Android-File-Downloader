package com.mahdizareeii.downloader.interfaces;

public interface OnFileDownloadListener {
    public abstract void onStart();

    public abstract void onProgressUpdate(int progress);

    public abstract void onError(String error);

    public abstract void onDownloaded();
}
