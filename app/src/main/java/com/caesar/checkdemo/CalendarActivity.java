package com.caesar.checkdemo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description:
 * Function:
 * Created by caesar on 2016/11/15.
 * 修订历史:
 */
public class CalendarActivity extends AppCompatActivity {
    private MaterialCalendarView widget;
    private TextView tv;
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        widget = (MaterialCalendarView) findViewById(R.id.calendarView);
        tv = (TextView) findViewById(R.id.tv);
        //设置当前日期的背景
        widget.addDecorators(oneDayDecorator);
        Calendar calendar = Calendar.getInstance();
        oneDayDecorator.setDate(calendar.getTime());
        //设置日期的边界(180天)
        Calendar instance1 = Calendar.getInstance();//180天前
        instance1.set(instance1.get(Calendar.DATE), Calendar.JANUARY, 1);
        instance1.setTime(new Date());
        instance1.add(Calendar.DATE,-180);//向前推半年
        Calendar instance2 = Calendar.getInstance();//当前日期
        widget.state().edit()
                .setMinimumDate(instance1.getTime())
                .setMaximumDate(instance2.getTime())
                .commit();
        //日历的选择监听事件
        widget.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                tv.setText(getSelectedDatesString());
                Log.i("test", new SimpleDateFormat("yyyyMMdd").format(date.getDate()));
                //startActivity(new Intent(CalendarActivity.this,SingleSelectionActivity.class));

            }
        });
    }

    /** 获取被选中的日期 */
    private String getSelectedDatesString() {
        CalendarDay date = widget.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }

    /**
     * 对选中日期增加指定修饰
     */
    public class OneDayDecorator implements DayViewDecorator {
        private CalendarDay date;
        public OneDayDecorator() {
            date = CalendarDay.today();
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return date != null && day.equals(date);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.addSpan(new RelativeSizeSpan(1.5f));
            view.addSpan(new ForegroundColorSpan(Color.WHITE));
            view.setSelectionDrawable(CalendarActivity.this.getResources().getDrawable(R.drawable.calendar_background));
        }

        /**
         * We're changing the internals, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
         */
        public void setDate(Date date) {
            this.date = CalendarDay.from(date);
        }
    }
}
