package _8thread.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CalendarUtil {

	private CalendarUtil(){}

	private static CalendarUtil calendarUtil=null;

	public static CalendarUtil getInstance(){
		if(calendarUtil == null){
			calendarUtil = new CalendarUtil();
		}
		return calendarUtil;
	}

	public String getCurrentDate(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}

	public String getLastTwoDayDate(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,-2);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}

	public Map<String, String> getTimeMap(){
//		CalendarUtil cu = CalendarUtil.getInstance();
		String date = this.getLastHourTime("yyyy-MM-dd");//今天的年月日
		String dateH = date;
		int hour = Integer.valueOf(this.getLastHourTime("HH"));

//		hour = 15;

		String timeSwap = "pm";//默认当前为下午，则查询今天上午的数据
		String timeSwapH = "am";//用作算下车比例，默认上午
//		if(hour>11) timeSwap = "pm";

		//如果是上午，则取昨天下午的数据
		if(hour<12){
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DATE,-1);
			Date time=cal.getTime();
			dateH = new SimpleDateFormat("yyyy-MM-dd").format(time);//时间改为昨天的年月日
			timeSwap = "am";
			timeSwapH = "pm";
		}

		Map<String, String> mapTime = new HashMap<String, String>();
		mapTime.put("date", date);
		mapTime.put("dateH", dateH);
		mapTime.put("timeSwap", timeSwap);
		mapTime.put("timeSwapH", timeSwapH);

		return mapTime;
	}

	public Map<String, String> getTimeMap(int hour){
//		CalendarUtil cu = CalendarUtil.getInstance();
		String date = this.getLastHourTime("yyyy-MM-dd");//今天的年月日
		String dateH = date;
//		int hour = Integer.valueOf(this.getLastHourTime("HH"));
//		hour = 15;

		String timeSwap = "pm";//默认当前为下午，则查询今天上午的数据
		String timeSwapH = "am";//用作算下车比例，默认上午
//		if(hour>11) timeSwap = "pm";

		//如果是上午，则取昨天下午的数据
		if(hour<12){
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DATE,-1);
			Date time=cal.getTime();
			dateH = new SimpleDateFormat("yyyy-MM-dd").format(time);//时间改为昨天的年月日
			timeSwap = "am";
			timeSwapH = "pm";
		}

		Map<String, String> mapTime = new HashMap<String, String>();
		mapTime.put("date", date);
		mapTime.put("dateH", dateH);
		mapTime.put("timeSwap", timeSwap);
		mapTime.put("timeSwapH", timeSwapH);

		return mapTime;
	}

	public String getCurrentDate1(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(calendar.getTime());
	}

	public String getCurrentDate2(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(calendar.getTime());
	}

	public String getCurrentDate2_1(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return format.format(calendar.getTime());
	}

	public String getCurrentDate3(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(calendar.getTime());
	}

	public String getCurrentMonth(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		return format.format(calendar.getTime());
	}

	public String getLastMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		return format.format(calendar.getTime());
	}

	public String getLastHourTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(calendar.getTime());
	}

	public String getLastFifteenMinTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 15);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(calendar.getTime());
	}

	public String getLastHourTime(String str){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat df = new SimpleDateFormat(str);
		return df.format(calendar.getTime());
	}

	public int getCurSecond(){
		Calendar calendar = Calendar.getInstance();
		int curSecond = calendar.get(calendar.HOUR_OF_DAY)*3600+calendar.get(calendar.MINUTE)*60+calendar.get(calendar.SECOND);
		return curSecond;
	}

	public int getIntervalSec(String time1,String time2){
		int result;
		long a,b;
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1=formatter.parse(time1);
			Date date2=formatter.parse(time2);
			a=date1.getTime();
			b=date2.getTime();
			result=(int)((a-b)/1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			result=0;
		}
		return result;
	}

	public String getlastTwoMinute(String time,int sec){
		String result;
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		try {
			Date date=formatter.parse(time);
			calendar.setTime(date);
			calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + sec);
			result=formatter.format(calendar.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			result=time;
		}
		return result;
	}

	public String parseTimeFromCurSecond(int curSecond){
		int hour=curSecond/3600;
		int min = (curSecond-hour*3600)/60;
		int sec = curSecond-hour*3600-min*60;
		String shour;
		String smin;
		String ssec;
		if(hour<10){
			shour="0"+hour;
		}else{
			shour=""+hour;
		}
		if(min<10){
			smin="0"+min;
		}else{
			smin=""+min;
		}
		if(sec<10){
			ssec="0"+sec;
		}else{
			ssec=""+sec;
		}
		return shour+":"+smin+":"+ssec;
	}
}
