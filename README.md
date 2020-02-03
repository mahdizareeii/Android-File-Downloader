# File-Downloader
[![](https://jitpack.io/v/mahdizareeii/File-Downloader.svg)](https://jitpack.io/#mahdizareeii/File-Downloader)

step 1:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
step 2:

	dependencies {
	        implementation 'com.github.mahdizareeii:File-Downloader:1.0'
	}

step 3:
	add this permissions in your manifest

	<uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    
step 4:

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
