package com.wutl.pintu;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wutl.pintu.niftydialog.Effectstype;
import com.wutl.pintu.niftydialog.NiftyDialogBuilder;
import com.wutl.pintu.view.GamePintuLayout;

public class MainActivity extends Activity {

	GamePintuLayout mGameView;
	private TextView mCurrentLevelView, mTotalStayView;
	private int mCurrentLevel = 1, mTotalStayTimeMillis = 150;
	private boolean isShowDialog = false;

	private ScheduledExecutorService mSheduleExecutor = null;
	private NiftyDialogBuilder mDialogBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		mDialogBuilder = NiftyDialogBuilder.getInstance(this);
		if (mSheduleExecutor == null)
			mSheduleExecutor = Executors.newScheduledThreadPool(1);
		mSheduleExecutor.scheduleWithFixedDelay(new TotalTimeThread(), 1000,
				1000, TimeUnit.MILLISECONDS);
	}

	private void initViews() {
		mCurrentLevelView = (TextView) findViewById(R.id.current_level);
		mTotalStayView = (TextView) findViewById(R.id.total_stay_timemills);
		mGameView = (GamePintuLayout) findViewById(R.id.id_gameview);
	}

	class TotalTimeThread implements Runnable {

		@Override
		public void run() {
			mTotalStayTimeMillis--;
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (mTotalStayTimeMillis >= 0) {
						mTotalStayView.setText(String
								.valueOf(mTotalStayTimeMillis));
					} else {
						showDialog();
					}
				}
			});
		}
	}

	private void showDialog() {

		mDialogBuilder
				.withTitle("单次时间已经到了！")
				.withTitleColor("#FFFFFF")
				.withDividerColor("#11000000")
				// .withMessage()
				.withMessageColor("#FFFFFF")
				.withIcon(getResources().getDrawable(R.drawable.icon))
				.isCancelableOnTouchOutside(true).withDuration(700)
				.withEffect(Effectstype.RotateLeft).withButton1Text("再试一次")
				.withButton2Text("退出")
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				}).setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				}).show();

	}
}
