package com.sample.downlaoder;

public interface OnFileDownloaderListener {
    public abstract void onStart();

    public abstract void onProgressUpdate(int progress);

    public abstract void onError(String error);

    public abstract void onDownloaded(DownloadState downloadState);
}
