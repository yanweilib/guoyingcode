package com.example.androidcode.Units;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtil {
	private static Toast t;
	private static int duration;
	private static Toast midToast = null;

	private static void makeText(Context context, String msg, int duration) {
		if (ToastUtil.duration != duration) {
			if (t != null) {
				t.cancel();
			}
			t = Toast.makeText(context, msg, duration);
		} else {
			if (t == null) {
				t = Toast.makeText(context, msg, duration);
			} else {
				t.setText(msg);
			}
		}
		ToastUtil.duration = duration;
		t.show();
	}

	public static void makeText(Context context, int resId, int duration) {
		makeText(context, context.getResources().getString(resId), duration);
	}

	public static void makeShortText(Context context, String msg) {
		if (!TextUtils.isEmpty(msg) && context != null) {
			makeText(context, msg, Toast.LENGTH_SHORT);
		}
	}

	public static void makeShortText(Context context, int resId) {
		makeText(context, resId, Toast.LENGTH_SHORT);
	}

	public static void makeLongText(Context context, String msg) {
		makeText(context, msg, Toast.LENGTH_LONG);
	}

	public static void makeLongText(Context context, int resId) {
		makeText(context, resId, Toast.LENGTH_LONG);
	}

	public static void makeMiddleToast(Context context, int icon,
									   CharSequence msg) {
//		if (midToast == null) {
//			midToast = new Toast(context);
//			midToast.setDuration(Toast.LENGTH_SHORT);
//			midToast.setGravity(Gravity.CENTER, 0, 0);
//			LinearLayout toastView = (LinearLayout) LayoutInflater
//					.from(context).inflate(R.layout.toast_middle_view, null);
//			midToast.setView(toastView);
//		}
//		ImageView img = (ImageView) midToast.getView().findViewById(
//				R.id.iv_toast_icon);
//		TextView text = (TextView) midToast.getView().findViewById(
//				R.id.tv_toast_content);
//		img.setImageResource(icon);
//		text.setText(msg);
//		midToast.show();
	}
}
