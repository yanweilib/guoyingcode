package com.yw.gril.Units;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by 伟 on 2016/12/13.
 * 获取图片 裁剪，上传文件
 */

public class CreateBmpFactory2 extends Activity implements View.OnClickListener{
    private static final int TAKE_PHOTO_CODE = 1;//拍照选择图片请求CODE
    private static final int PICK_PHOTO_CODE = 2;//图库选择图片请求CODE
    private static final int CROP_PHOTO_CODE = 3;//剪裁图片请求CODE
    private static final String TAG = "CreateBmpFactory2";

    //图片URI
    private Uri headuri;
    //缓存的图片文件
    private File file;
    //选择拍照和本地文件弹框
    private AlertDialog.Builder builder;
    private String[] item_sel = {"相机", "本地相册"};


    private void initView()
    {
        file = new File(FileUtil.getUserDir("name").getPath() + "/usetimg.jpg");
        headuri = Uri.fromFile(file);

        builder = new AlertDialog.Builder(this);
        builder.setItems(item_sel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    //调用相机
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, headuri);
                    startActivityForResult(intent, TAKE_PHOTO_CODE);
                } else {
                    // 调用图库
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_PHOTO_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //拍照指定了输出到uri所以data为空
        if (requestCode == TAKE_PHOTO_CODE && resultCode == Activity.RESULT_OK) {
            cropPhoto(headuri);
        }
        //图库选择不能指定到uri
        else if (requestCode == PICK_PHOTO_CODE && resultCode == Activity.RESULT_OK && null != data) {
            Uri uri = data.getData();
            cropPhoto(uri);
        } else if (requestCode == CROP_PHOTO_CODE && resultCode == Activity.RESULT_OK && null != data) {
            Bitmap bitmap = decodeUriAsBitmap(headuri);
            uploadImg();
        }
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * 剪裁图片
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);

        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 200);

        intent.putExtra("outputY", 200);

        intent.putExtra("scale", true);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, headuri);

        intent.putExtra("return-data", false);

        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        intent.putExtra("noFaceDetection", true); //人脸识别

        startActivityForResult(intent, CROP_PHOTO_CODE);

    }

    private void uploadImg() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("file", file, "image/jpeg");
        String account = SharePreferenceUtil.getStringData(this, AppManger.USERNAME_KEY);

        params.addBodyParameter("useraccount", account);
        params.addHeader("Authorization", "Basic YWRtaW46MTAw");

        HttpUtils http = new HttpUtils();
//        http.send(HttpRequest.HttpMethod.POST,"url", params, new RequestCallBack<String>() {
//
//            @Override
//            public void onFailure(HttpException arg0, String arg1) {
//            }
//
//            @Override
//            public void onSuccess(ResponseInfo<String> arg0) {
//                LogUtil.e(TAG, "头像上传成功：" + arg0.result);
//            }
//        });
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        builder.show();
    }
}
