package com.example.androidcode.Units.Drag;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.androidcode.R;

public class DragActivity extends AppCompatActivity implements View.OnTouchListener{
    private Button iv_dv_view;
    private int startx;
    private int starty;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
//		Drawable drawable = new ColorDrawable(color.transparent);
//		getWindow().setBackgroundDrawable(drawable);
        iv_dv_view = (Button) this.findViewById(R.id.btn);
        sp = this.getSharedPreferences("config", Context.MODE_PRIVATE);
        iv_dv_view.setOnTouchListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        int x = sp.getInt("lastx", 0);
        int y = sp.getInt("lasty", 0);
//		iv_dv_view.layout(iv_dv_view.getLeft() + x, iv_dv_view.getTop() + y,
//				iv_dv_view.getRight() + x, iv_dv_view.getBottom() + y);
//		iv_dv_view.invalidate();//界面重新渲染

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_dv_view.getLayoutParams();
        params.leftMargin = x;
        params.topMargin = y;
        iv_dv_view.setLayoutParams(params);
    }

    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v     The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     *              the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {

            // 如果手指放在imageView上拖动
            case R.id.btn:
                // event.getRawX(); //获取手指第一次接触屏幕在x方向的坐标
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:// 获取手指第一次接触屏幕
                        startx = (int) event.getRawX();
                        starty = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:// 手指在屏幕上移动对应的事件
                        int x = (int) event.getRawX();
                        int y = (int) event.getRawY();

                        if (y < 400) {
                            // 设置TextView在窗体的下面
//                            tv_drag_view.layout(tv_drag_view.getLeft(), 420,
//                                    tv_drag_view.getRight(), 440);
                        } else {
//                            tv_drag_view.layout(tv_drag_view.getLeft(), 60,
//                                    tv_drag_view.getRight(), 80);
                        }

                        // 获取手指移动的距离
                        int dx = x - startx;
                        int dy = y - starty;
                        // 得到imageView最开始的各顶点的坐标
                        int l = iv_dv_view.getLeft();
                        int r = iv_dv_view.getRight();
                        int t = iv_dv_view.getTop();
                        int b = iv_dv_view.getBottom();

                        // 更改imageView在窗体的位置
                        iv_dv_view.layout(l + dx, t + dy, r + dx, b + dy);

                        // 获取移动后的位置
                        startx = (int) event.getRawX();
                        starty = (int) event.getRawY();

                        break;
                    case MotionEvent.ACTION_UP:// 手指离开屏幕对应事件
                        // 记录最后图片在窗体的位置
                        int lasty = iv_dv_view.getTop();
                        int lastx = iv_dv_view.getLeft();
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("lasty", lasty);
                        editor.putInt("lastx", lastx);
                        editor.commit();
                        break;
                }
                break;

        }
        return true;// 不会中断触摸事件的返回
    }
}
