package com.example.FurnitureShare.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.FurnitureShare.R;
import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.view.wheelview.OnWheelChangedListener;
import com.example.FurnitureShare.view.wheelview.OnWheelScrollListener;
import com.example.FurnitureShare.view.wheelview.WheelView;
import com.example.FurnitureShare.view.wheelview.adapter.AbstractWheelTextAdapter1;

import java.util.ArrayList;
import java.util.Calendar;

public class ChangeBirthdayPopwindow extends PopupWindow implements View.OnClickListener {

	private WheelView wvYear;
	private WheelView wvMonth;
	private WheelView wvDay;
	private View vChangeBirth;
	private View vChangeBirthChild;
	private TextView btnSure;
	private TextView btnCancel;

	private ArrayList<String> arry_years = new ArrayList<String>();
	private ArrayList<String> arry_months = new ArrayList<String>();
	private ArrayList<String> arry_days = new ArrayList<String>();

	private Context context;

	private AddressTextAdapter mYearAdapter;
	private AddressTextAdapter mMonthAdapter;
	private AddressTextAdapter mDaydapter;

	private int month;
	private int day;

	private int currentYear = getYear();
	private int currentMonth = 1;
	private int currentDay = 1;

	private int maxTextSize = 14;
	private int minTextSize = 14;

	private boolean issetdata = false;

	private String selectYear;
	private String selectMonth;
	private String selectDay;

	private OnBirthListener onBirthListener;

	private int maxsize = 14;
	private int minsize = 12;
	private String birthday;


	public ChangeBirthdayPopwindow(final Context context) {
		super(context);
		this.context = context;
		View view= View.inflate(context, R.layout.edit_changebirthday_pop_layout,null);

		//生日
		birthday = FSApplication.instance.sp.getString("birthday", "");

		wvYear = (WheelView) view.findViewById(R.id.wv_address_province);
		wvMonth = (WheelView) view.findViewById(R.id.wv_address_city);
		wvDay = (WheelView)view. findViewById(R.id.wv_address_area);
		vChangeBirth = view.findViewById(R.id.ly_myinfo_changeaddress);
		vChangeBirthChild = view.findViewById(R.id.ly_myinfo_changeaddress_child);
		btnSure = (TextView) view.findViewById(R.id.btn_myinfo_sure);
		btnCancel = (TextView)view. findViewById(R.id.btn_myinfo_cancel);


		//设置SelectPicPopupWindow的View
		this.setContentView(view);
		//设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
		//设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		//设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		//设置非PopupWindow区域是否可触摸
        this.setOutsideTouchable(true);
		//设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.mystyle);
		//实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0x00000000);
//		//设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);


		vChangeBirthChild.setOnClickListener(this);
		btnSure.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

		if (!issetdata) {
			initData();
		}
		initYears();
		mYearAdapter = new AddressTextAdapter(context, arry_years, setYear(currentYear), maxTextSize, minTextSize);
		wvYear.setVisibleItems(5);
		wvYear.setViewAdapter(mYearAdapter);
		wvYear.setCurrentItem(setYear(currentYear));

		initMonths(month);
		mMonthAdapter = new AddressTextAdapter(context, arry_months, setMonth(currentMonth), maxTextSize, minTextSize);
		wvMonth.setVisibleItems(5);
		wvMonth.setViewAdapter(mMonthAdapter);
		wvMonth.setCurrentItem(setMonth(currentMonth));

		initDays(day);
		mDaydapter = new AddressTextAdapter(context, arry_days, currentDay - 1, maxTextSize, minTextSize);
		wvDay.setVisibleItems(5);
		wvDay.setViewAdapter(mDaydapter);
		wvDay.setCurrentItem(currentDay - 1);

		wvYear.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
				selectYear = currentText;
				setTextviewSize(currentText, mYearAdapter);
				currentYear = Integer.parseInt(currentText);
				setYear(currentYear);
				initMonths(month);
				mMonthAdapter = new AddressTextAdapter(context, arry_months, 0, maxTextSize, minTextSize);
				wvMonth.setVisibleItems(5);
				wvMonth.setViewAdapter(mMonthAdapter);
				wvMonth.setCurrentItem(0);
			}
		});

		wvYear.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, mYearAdapter);
			}
		});

		wvMonth.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
				selectMonth = currentText;
				setTextviewSize(currentText, mMonthAdapter);
				setMonth(Integer.parseInt(currentText));
				initDays(day);
				mDaydapter = new AddressTextAdapter(context, arry_days, 0, maxTextSize, minTextSize);
				wvDay.setVisibleItems(5);
				wvDay.setViewAdapter(mDaydapter);
				wvDay.setCurrentItem(0);
			}
		});

		wvMonth.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, mMonthAdapter);
			}
		});

		wvDay.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, mDaydapter);
				selectDay = currentText;
			}
		});

		wvDay.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, mDaydapter);
			}
		});


	}


	public void initYears() {
		for (int i = getYear(); i > 1900; i--) {
			arry_years.add(i + "");
		}
	}

	public void initMonths(int months) {
		arry_months.clear();
		for (int i = 1; i <= months; i++) {
			arry_months.add(i + "");
		}
	}

	public void initDays(int days) {
		arry_days.clear();
		for (int i = 1; i <= days; i++) {
			arry_days.add(i + "");
		}
	}

	private class AddressTextAdapter extends AbstractWheelTextAdapter1 {
		ArrayList<String> list;

		protected AddressTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index) + "";
		}
	}

	public void setBirthdayListener(OnBirthListener onBirthListener) {
		this.onBirthListener = onBirthListener;
	}
	/**
	 * 设置字体大小
	 *
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(14);
			} else {
				textvew.setTextSize(12);
			}
		}
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnSure) {
			if (onBirthListener != null) {
				onBirthListener.onClick(selectYear, selectMonth, selectDay);
			}
		} else if (v == btnCancel) {

		} else if (v == vChangeBirthChild) {
			return;
		} else {
//			dismiss();
		}
		dismiss();
//        backgroundAlpha((Activity) context, 1f);
    }

	/**
	 * 回调接口
	 *
	 * @author Administrator
	 *
	 */
	public interface OnBirthListener {
		public void onClick(String year, String month, String day);
	}

	public int getYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	public int getMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH) + 1;
	}

	public int getDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DATE);
	}

	public void initData() {

		String year = "";
		String month = "";
		String day = "";
		if (birthday != null && !birthday.equals("")){

			year = birthday.substring(0,4);
			month = birthday.substring(5,7);
			if (Integer.parseInt(month) < 10){
				month = birthday.substring(6,7);
			}
			day = birthday.substring(8);
			if ( Integer.parseInt(day) < 10){
				day = birthday.substring(9);
			}
			setDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
			this.currentYear = Integer.parseInt(year);
			this.currentMonth = Integer.parseInt(month);
			this.currentDay = Integer.parseInt(day);
		}else {
			setDate(getYear(), getMonth(), getDay());
//			this.currentMonth = 1;
//			this.currentDay = 1;
		}


	}

	/**
	 * 设置年月日
	 *
	 * @param year
	 * @param month
	 * @param day
	 */
	public void setDate(int year, int month, int day) {
		selectYear = year + "" ;
		selectMonth = month + "";
		selectDay = day + "";
		issetdata = true;
		this.currentYear = year;
		this.currentMonth = month;
		this.currentDay = day;
		if (year == getYear()) {
			this.month = getMonth();
		} else {
			this.month = 12;
		}
		calDays(year, month);
	}

	/**
	 * 设置年份
	 *
	 * @param year
	 */
	public int setYear(int year) {
		int yearIndex = 0;
		if (year != getYear()) {
			this.month = 12;
		} else {
			this.month = getMonth();
		}
		for (int i = getYear(); i > 1900; i--) {
			if (i == year) {
				return yearIndex;
			}
			yearIndex++;
		}
		return yearIndex;
	}

	/**
	 * 设置月份
	 *
	 * @param
	 * @param month
	 * @return
	 */
	public int setMonth(int month) {
		int monthIndex = 0;
		calDays(currentYear, month);
		for (int i = 1; i < this.month; i++) {
			if (month == i) {
				return monthIndex;
			} else {
				monthIndex++;
			}
		}
		return monthIndex;
	}

	/**
	 * 计算每月多少天
	 *
	 * @param month
	 * @param
	 */
	public void calDays(int year, int month) {
		boolean leayyear = false;
		if (year % 4 == 0 && year % 100 != 0) {
			leayyear = true;
		} else {
			leayyear = false;
		}
		for (int i = 1; i <= 12; i++) {
			switch (month) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					this.day = 31;
					break;
				case 2:
					if (leayyear) {
						this.day = 29;
					} else {
						this.day = 28;
					}
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					this.day = 30;
					break;
			}
		}
		if (year == getYear() && month == getMonth()) {
			this.day = getDay();
		}
	}


}