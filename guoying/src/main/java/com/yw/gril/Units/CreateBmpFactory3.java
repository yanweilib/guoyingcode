package com.example.androidcode.Units;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by 伟 on 2016/12/13.
 * 获取图片 裁剪，上传文件
 */

public class CreateBmpFactory3 {
    private static final int TAKE_PHOTO_CODE = 1;//拍照选择图片请求CODE
    private static final int PICK_PHOTO_CODE = 2;//图库选择图片请求CODE
    private static final int CROP_PHOTO_CODE = 3;//剪裁图片请求CODE
    private static final String TAG = "CreateBmpFactory2";

    private Activity activity;
    //图片URI
    private Uri headuri;
    //缓存的图片文件
    private File file;
    //选择拍照和本地文件弹框
    private AlertDialog.Builder builder;
    private String[] item_sel = {"相机", "本地相册"};
    private CreateCall createCall;

    public interface CreateCall
    {
        void backimg(File file);
    }

    public CreateBmpFactory3(Activity activity, CreateCall createCall)
    {
        this.activity=activity;
        this.createCall=createCall;
        initView();
    }
    private void initView()
    {
        file = new File(FileUtil.getUserDir("name").getPath() + "/usetimg.jpg");
        headuri = Uri.fromFile(file);

        builder = new AlertDialog.Builder(activity);
        builder.setItems(item_sel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    //调用相机
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, headuri);
                    activity.startActivityForResult(intent, TAKE_PHOTO_CODE);
                } else {
                    // 调用图库
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    activity.startActivityForResult(intent, PICK_PHOTO_CODE);
                }
            }
        });
    }

    public void doingfromResult(int requestCode, int resultCode, Intent data)
    {
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
            bitmap = BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(uri));
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
    private void cropPhoto(Uri uri) {

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

        activity.startActivityForResult(intent, CROP_PHOTO_CODE);

    }

    private void uploadImg() {
        if(createCall!=null)
        {
            createCall.backimg(file);
        }
//        RequestParams params = new RequestParams();
//        params.addBodyParameter("file", file, "image/jpeg");
//        String account = SharePreferenceUtil.getStringData(activity, SharePreferenceKeys.USER_USERNAME);
//
//        params.addBodyParameter("useraccount", account);
//        params.addHeader("Authorization", "Basic YWRtaW46MTAw");
//
//        HttpUtils http = new HttpUtils();
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

    public void show()
    {
        builder.show();
    }
}
