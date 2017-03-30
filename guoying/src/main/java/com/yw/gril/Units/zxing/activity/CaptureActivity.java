package com.example.androidcode.Units.zxing.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidcode.R;
import com.example.androidcode.Units.LogUtil;
import com.example.androidcode.Units.zxing.camera.CameraManager;
import com.example.androidcode.Units.zxing.decoding.CaptureActivityHandler;
import com.example.androidcode.Units.zxing.decoding.InactivityTimer;
import com.example.androidcode.Units.zxing.view.ViewfinderView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Vector;

/**
 * Initial the camera
 *
 * @author Ryan.Tang
 */
public class CaptureActivity extends Activity implements Runnable, Callback, OnClickListener {
    private static final float BEEP_VOLUME = 0.10f;
    private static final String TAG = "CaptureActivity";

    private static final String CAPTURE_TYPE_REGISTER = "06";//签到
    public static final int CAPTURE_RESULT_TYPE_REGISTER = 66;//签到

    private boolean hasSurface;
    private boolean playBeep = true;
    private boolean vibrate = true;
    public static boolean isCameraOpen = false;
    private  boolean isFinished=false;

    private CaptureActivity context;
    private CaptureActivityHandler handler;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;

    private ViewfinderView viewfinderView;
    private ImageView btn_back;
    private RelativeLayout rlay_openflashlight;
    private TextView tv_openflashlight;
    private ImageView init_refresh;
    private ImageView bg_init;

    Handler openCameraHandler = new Handler();
    /**
     * 签到扫码
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        hasSurface = false;
        playBeep = true;

        setContentView(R.layout.public_saoyisao);

        initView();

        CameraManager.init(getApplication());
        inactivityTimer = new InactivityTimer(this);

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();

    }

    private void initView() {
        init_refresh = (ImageView) findViewById(R.id.refresh_init);
        bg_init = (ImageView) findViewById(R.id.bg_init);

        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);

        btn_back = (ImageView) findViewById(R.id.btn_back);
        rlay_openflashlight = (RelativeLayout) findViewById(R.id.rlay_openflashlight);
        tv_openflashlight = (TextView) findViewById(R.id.tv_openflashlight);
        rlay_openflashlight.setOnClickListener(this);
        btn_back.setOnClickListener(this);


        Animation roateAnim = AnimationUtils.loadAnimation(this, R.anim.roate);
        LinearInterpolator lin = new LinearInterpolator();
        roateAnim.setInterpolator(lin);
        init_refresh.startAnimation(roateAnim);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
        isCameraOpen = false;
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    /**
     * Handler scan result
     *
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();

        String resultString = result.getText();
        LogUtil.e("CaptureActivity—retultString：", resultString);
        if (resultString.equals("")) {
//            Toast.makeText(context, context.getResources().getString(R.string.scan_current_code), Toast.LENGTH_SHORT).show();
            continuePreview();
            return;
        }
        if (!(resultString.contains("code") && (resultString.contains("vtaiqiu")))) {
//            Toast.makeText(context, context.getResources().getString(R.string.scan_current_code), Toast.LENGTH_SHORT).show();
            continuePreview();
            return;
        }
//        MsgTipManager.show(this, Tooltip.DOING, getResources().getString(R.string.tooltip_processing));

        String QRCode = "";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(resultString);
            QRCode = jsonObject.getString("QRCode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        switch (QRCode)//头像审核；签到；开台；确认比分
        {
            case CAPTURE_TYPE_REGISTER://签到
                RegisterCaptureManagement.getInstance().start(this, resultString);
                break;
            default:
                //当不知道二维码类别的时候就用空的页面打开
//                Intent intent = new Intent(this, DisplayActivity.class);
//                intent.putExtra("content", resultString);
//                startActivity(intent);
                break;
        }
    }

    /**
     * 退出界面
     */
    public void finished()
    {
        btn_back.performClick();
    }

    public void continuePreview() {
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        initCamera(surfaceHolder);
        if (handler != null) {
            handler.restartPreviewAndDecode();
        }
    }

    private void initCamera(final SurfaceHolder surfaceHolder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CameraManager.get().openDriver(surfaceHolder);
                    openCameraHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (handler == null) {
                                handler = new CaptureActivityHandler(context, decodeFormats, characterSet);
                            }
                            bg_init.setVisibility(View.GONE);
                            init_refresh.clearAnimation();
                            init_refresh.setVisibility(View.GONE);
                            isCameraOpen = true;
                        }
                    },0);
                } catch (IOException ioe) {
                    return;
                } catch (RuntimeException e) {
                    return;
                }
            }
        }).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;

    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    TranslateAnimation ta1;

    /**
     * 初始化扫描动画
     */
    private void initTla() {
        ta1 = new TranslateAnimation(0, 0, 0, 600);
        ta1.setDuration(2000);
        ta1.setStartTime(0);
        ta1.setRepeatCount(-1);
        ta1.setRepeatMode(TranslateAnimation.RESTART);
    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                isFinished=true;
                finish();
                break;
            case R.id.rlay_openflashlight:
                String strClose = "关闭";
                String strOpen = "打开";
                Camera m_Camera = CameraManager.get().camera;
                Camera.Parameters parameters = m_Camera.getParameters();

                if (strOpen.equals(tv_openflashlight.getText())) {
                    PackageManager pm = this.getPackageManager();
                    FeatureInfo[] features = pm.getSystemAvailableFeatures();
                    for (FeatureInfo f : features) {
                        if (PackageManager.FEATURE_CAMERA_FLASH.equals(f.name))   //判断设备是否支持闪光灯
                        {
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                            m_Camera.setParameters(parameters);
//                            m_Camera.startPreview();
                            //打开闪光灯 处理
                            tv_openflashlight.setText(strClose);
                        }
                    }
                } else {
                    if (m_Camera != null) {
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        m_Camera.setParameters(parameters);
                    }
                    //关闭闪光灯 处理
                    tv_openflashlight.setText(strOpen);
                }
                break;
        }
    }

    @Override
    public void run() {

    }
}