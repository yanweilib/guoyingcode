package com.example.androidcode.Widgets.TitleBar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.androidcode.R;

/**
 * 自定义控件--标题栏
 * 
 * @author lv.ly
 * 
 * */
@SuppressLint("NewApi")
public class TitleBar extends RelativeLayout {
	private RelativeLayout layout;
	private TextView left_tv, right_tv, title_tv;
	private ImageView left_iv, right_iv;
	private View title_view;
	private String title_text, left_text, right_text;
	private int title_text_color, left_text_color, right_text_color;
	private Drawable left_background, right_background;

	private TitleBarClickListener titleBarClickListener;
	private Drawable title_img;
	private ImageView title_iv;

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.title_bar);
		title_text = ta.getString(R.styleable.title_bar_title_text);
		title_img = ta.getDrawable(R.styleable.title_bar_title_img);
		left_text = ta.getString(R.styleable.title_bar_left_text);
		right_text = ta.getString(R.styleable.title_bar_right_text);
		title_text_color = ta.getColor(R.styleable.title_bar_title_text_color,
				Color.WHITE);
		left_text_color = ta.getColor(R.styleable.title_bar_left_text_color, Color.WHITE);
		right_text_color = ta.getColor(R.styleable.title_bar_right_text_color,
				Color.WHITE);

		left_background = ta.getDrawable(R.styleable.title_bar_left_background);
		right_background = ta.getDrawable(R.styleable.title_bar_right_background);
		ta.recycle();

		title_view = inflate(context, R.layout.title_layout, null);
		layout = (RelativeLayout) title_view.findViewById(R.id.layout);
		left_tv = (TextView) title_view.findViewById(R.id.left_tv);
		right_tv = (TextView) title_view.findViewById(R.id.right_tv);
		title_tv = (TextView) title_view.findViewById(R.id.title_tv);
		left_iv = (ImageView) title_view.findViewById(R.id.left_iv);
		right_iv = (ImageView) title_view.findViewById(R.id.right_iv);
		title_iv = (ImageView) title_view.findViewById(R.id.title_iv);
		left_tv.setText(left_text);
		left_tv.setTextColor(left_text_color);
		left_iv.setImageDrawable(left_background);

		right_tv.setText(right_text);
		right_tv.setTextColor(right_text_color);
		right_iv.setImageDrawable(right_background);
		title_iv.setImageDrawable(title_img);
		title_tv.setText(title_text);
		title_tv.setTextColor(title_text_color);

		addView(title_view);

		left_tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (titleBarClickListener != null) {
					titleBarClickListener.leftClick();
				}
			}
		});

		right_tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (titleBarClickListener != null) {
					titleBarClickListener.rightClick();
				}
			}
		});
		left_iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (titleBarClickListener != null) {
					titleBarClickListener.leftClick();
				}
			}
		});
		right_iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (titleBarClickListener != null) {
					titleBarClickListener.rightClick();
				}
			}
		});
	}

	public RelativeLayout getLayout() {
		return layout;
	}

	public void setLayout(RelativeLayout layout) {
		this.layout = layout;
	}

	public void setTopBarClickListener(
			TitleBarClickListener titleBarClickListener) {
		this.titleBarClickListener = titleBarClickListener;
	}

	public String getTitle_text() {
		return title_text;
	}

	public void setTitle_text(String title_text) {
		this.title_text = title_text;
		title_tv.setText(title_text);
	}

	public String getLeft_text() {
		return left_text;
	}

	public void setLeft_text(String left_text) {
		this.left_text = left_text;
		left_tv.setText(left_text);
	}

	public String getRight_text() {
		return right_text;
	}

	public void setRight_text(String right_text) {
		this.right_text = right_text;
		right_tv.setText(right_text);

	}

	public int getTitle_text_color() {
		return title_text_color;
	}

	public void setTitle_text_color(int title_text_color) {
		this.title_text_color = title_text_color;
		title_tv.setTextColor(title_text_color);
	}

	public int getLeft_text_color() {
		return left_text_color;
	}

	public void setLeft_text_color(int left_text_color) {
		this.left_text_color = left_text_color;
		left_tv.setTextColor(left_text_color);
	}

	public int getRight_text_color() {
		return right_text_color;
	}

	public void setRight_text_color(int right_text_color) {
		this.right_text_color = right_text_color;
		right_tv.setTextColor(right_text_color);
	}

	public Drawable getLeft_background() {
		return left_background;
	}

	public void setLeft_background(Drawable left_background) {
		this.left_background = left_background;
		left_iv.setImageDrawable(left_background);
	}

	public Drawable getRight_background() {
		return right_background;
	}

	public void setRight_background(Drawable right_background) {
		this.right_background = right_background;
		right_iv.setImageDrawable(right_background);
	}

	public interface TitleBarClickListener {

		void leftClick();

		void rightClick();
	}

	public TextView getLeft_tv() {
		return left_tv;
	}

	public void setLeft_tv(TextView left_tv) {
		this.left_tv = left_tv;
	}

	public TextView getRight_tv() {
		return right_tv;
	}

	public void setRight_tv(TextView right_tv) {
		this.right_tv = right_tv;
	}

	public TextView getTitle_tv() {
		return title_tv;
	}

	public void setTitle_tv(TextView title_tv) {
		this.title_tv = title_tv;
	}

	public ImageView getLeft_iv() {
		return left_iv;
	}

	public void setLeft_iv(ImageView left_iv) {
		this.left_iv = left_iv;
	}

	public ImageView getRight_iv() {
		return right_iv;
	}

	public void setRight_iv(ImageView right_iv) {
		this.right_iv = right_iv;
	}

	public View getTitle_view() {
		return title_view;
	}

	public void setTitle_view(View title_view) {
		this.title_view = title_view;
	}

	public ImageView getTitle_iv() {
		return title_iv;
	}

	public void setTitle_iv(ImageView title_iv) {
		this.title_iv = title_iv;
	}

	public void setRightVisibility(int visibility) {
		right_iv.setVisibility(visibility);
		right_tv.setVisibility(visibility);
	}

}
