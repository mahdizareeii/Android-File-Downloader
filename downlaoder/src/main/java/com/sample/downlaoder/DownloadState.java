package com.sample.downlaoder;

public class DownloadState {

    private boolean isSuccessfully;
    private String message;

    public DownloadState(boolean isSuccessfully, String message) {
        this.isSuccessfully = isSuccessfully;
        this.message = message;
    }

    public boolean isSuccessfully() {
        return isSuccessfully;
    }

    public void setSuccessfully(boolean successfully) {
        isSuccessfully = successfully;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
