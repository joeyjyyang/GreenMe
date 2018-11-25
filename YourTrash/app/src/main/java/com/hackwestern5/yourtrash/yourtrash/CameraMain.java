package com.hackwestern5.yourtrash.yourtrash;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CameraMain extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private Camera.PictureCallback mPicture;
    private Button capture;
    private Context myContext;
    private LinearLayout cameraPreview;
    private TextView mCity_view;
    private String mLocation;
    public static Bitmap bitmap;

    //temp
    public static final String OBJECT = "com.hackwestern5.yourtrash.yourtrash.object";
    public static final String LOCATION = "com.hackwestern5.yourtrash.yourtrash.location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);

        mLocation = "London";   //init
        mCity_view = (TextView) findViewById(R.id.location);
        CharSequence tmp_buf = "City: " + mLocation;
        mCity_view.setText(tmp_buf);

        setContentView(R.layout.activity_camera);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        myContext = this;

        mCamera =  Camera.open();
        mCamera.setDisplayOrientation(90);
        cameraPreview = (LinearLayout) findViewById(R.id.cPreview);
        mPreview = new CameraPreview(myContext, mCamera);
        cameraPreview.addView(mPreview);

        capture = (Button) findViewById(R.id.btnCam);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, mPicture);
            }
        });

        mCamera.startPreview();

    }

    public void onResume() {

        super.onResume();
        if(mCamera == null) {
            mCamera = Camera.open();
            mCamera.setDisplayOrientation(90);
            mPicture = getPictureCallback();
            mPreview.refreshCamera(mCamera);
            Log.d("nu", "null");
        }else {
            Log.d("nu","no null");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        //when on Pause, release camera in order to be used from other applications
        releaseCamera();
    }

    private void releaseCamera() {
        // stop and release camera
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private Camera.PictureCallback getPictureCallback() {
        Camera.PictureCallback picture = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                Intent intent = new Intent(CameraMain.this,Results.class);
                startActivity(intent);
            }
        };
        return picture;
    }

    //Sends to Results page
    public void openResults (View view){
        EditText editText = (EditText) findViewById(R.id.Test_Location);
        String finalAddress = editText.getText().toString();
        if(finalAddress.equals("London")||finalAddress.equals("Toronto")){
            Intent intent = new Intent(this, Results.class);
            EditText currentLocation = (EditText) findViewById(R.id.Test_Location);
            String location = currentLocation.getText().toString();
            String object = "Object";
            intent.putExtra(OBJECT, object);
            intent.putExtra(LOCATION, location);
            startActivity(intent);
        }

    }
}
