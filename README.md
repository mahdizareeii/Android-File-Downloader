# File-Downloader
[![](https://jitpack.io/v/mahdizareeii/Android-File-Downloader.svg)](https://jitpack.io/#mahdizareeii/Android-File-Downloader)

step 1: add the following codes in 'build.gradle(Project: yourproject)'

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
step 2: add the following codes in 'build.gradle(Madule: app)'

	dependencies {
	        implementation 'com.github.mahdizareeii:File-Downloader:1.6'
	}

step 3: add the permissions in your manifest

	<uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    
step 4: how to use

        /* this is sample of streaming download */
	
        //url of file
        String fileDownloadUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";

        //direction of downloadedFile
        String fileStorageDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hello_stream_download.png";

        //get instance of FileDownloader
        fileDownloader = new FileDownloader(fileDownloadUrl, fileStorageDir);

        //download file
        fileDownloader.streamDownloadFile(new OnFileDownloadListener() {
            @Override
            public void onStart() {
                Toast.makeText(MainActivity.this, "Download Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgressUpdate(int progress) {
                Log.i("onProgressUpdate:", "Downloading Stream: " + progress);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloaded() {
                Toast.makeText(MainActivity.this, "downloaded", Toast.LENGTH_SHORT).show();
            }
        });
	
	
        /* this is sample of full download */
	
        //url of file
        String fileDownloadUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";

        //direction of downloadedFile
        String fileStorageDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hello_full_download.png";

        //get instance of FileDownloader
        fileDownloader = new FileDownloader(fileDownloadUrl, fileStorageDir);

        //download file
        fileDownloader.fullDownloadFile(new OnFileDownloadListener() {
            @Override
            public void onStart() {
                Toast.makeText(MainActivity.this, "Download Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgressUpdate(int progress) {
                Log.i("onProgressUpdate:", "Downloading full: " + progress);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloaded() {
                Toast.makeText(MainActivity.this, "downloaded", Toast.LENGTH_SHORT).show();
            }
        });
	
	
	 //cancel stream download file
	 fileDownloader.cancelStreamDownload(new OnFileDownloadCancelListener() {
                @Override
                public void onCancel() {
                    Toast.makeText(MainActivity.this, "Download Stream Canceled", Toast.LENGTH_SHORT).show();
                }
            });
            
 	 //cancel full download file
	fileDownloader.cancelFullDownload(new OnFileDownloadCancelListener() {
		@Override
		public void onCancel() {
		    Toast.makeText(MainActivity.this, "Download full Canceled", Toast.LENGTH_SHORT).show();
		}
	    });
        
	
