package com.example.demo.until;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	static DateFormat dfymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static DateFormat dfymd = new SimpleDateFormat("yyyy-MM-dd");
	static DateFormat dfhms = new SimpleDateFormat("HH:mm:ss");
	static DateFormat dfhm = new SimpleDateFormat("HH:mm");
	static DateFormat dfymdhm_zh = new SimpleDateFormat("yyyy��MM��dd��HH��mm��");
	static DateFormat dfymdhm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	static DateFormat dfmd_zh = new SimpleDateFormat("MM��dd��");
	static DateFormat dfmd_d = new SimpleDateFormat("MM.dd");
	
	public static void main(String[] args) {
		System.out.println(getMonthDayDis("1.23","5.2"));
	}
	
	/**
	 * �ж���.�յĴ�С.��ʽ:��.��
	 * @param md1
	 * @param md2
	 * @return 0 ͬһ��,1 md1Ϊδ��ʱ�� ,-1 md1Ϊ��ȥʱ��
	 */
	public static int getMonthDayDis(String md1,String md2) {
		Date date1;
		Date date2;
		try {
			date1 = dfmd_d.parse(md1);
			date2 = dfmd_d.parse(md2); 
			long d1 = date1.getTime();
			long d2 = date2.getTime();
			long x = d1 - d2;
			if (x == 0) {
				return 0;
			}else if (x > 0) {
				return 1;
			}else if (x < 0) {
				return -1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -2;
	}


	/**
	 * ��yyyy-MM-dd HH:mmתΪyyyy��MM��dd��HHʱmm��
	 * @param dateString
	 * @return
	 */
	public static String fomatStrDate(String dateString) {
		Date date;
		try {
			date = dfymdhm.parse(dateString);
			return dfymdhm_zh.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateString;
	}
	public static String dataToStringYMD(Date d) {
		return dfymd.format(d);
	}


	public static String dataToStringYMDHMS(Date d) {
		return dfymdhms.format(d);
	}


	/**
	 * �õ���ǰ����
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getCurrentDateString() {
		return dfymd.format(new Date());
	}


	/**
	 * �õ���ǰ���ں�ʱ��
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDateTime() {
		return dfymdhms.format(new Date());
	}


	/**
	 * �õ���ǰʱ��
	 * 
	 * @return HH:mm:ss
	 */
	public static String getCurrentTime() {
		return dfhms.format(new Date());
	}


	/**
	 * �õ���ǰСʱ��
	 * 
	 * @return HH
	 */
	@SuppressWarnings("unused")
	public static int getCurrentHour() {
		int y, m, d, h, mi, s;
		Calendar cal = Calendar.getInstance();
		y = cal.get(Calendar.YEAR);
		m = cal.get(Calendar.MONTH);
		d = cal.get(Calendar.DATE);
		h = cal.get(Calendar.HOUR_OF_DAY);
		mi = cal.get(Calendar.MINUTE);
		s = cal.get(Calendar.SECOND);
		return h;
	}


	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		return y;
	}
 


	/**
	 * ����ʱ����������ٷ�
	 * 
	 * @param baseTime
	 *            ʱ����� 1 ��ʽ��yyyy-MM-dd HH:mm:ss
	 * @param nowTime
	 *            ʱ����� 2 ��ʽ��yyyy-MM-dd HH:mm:ss
	 * @return   �� 
	 * 
	 */
	@SuppressWarnings("unused")
	public static long getDistanceTimes(String baseTime, String nowTime) {


		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			one = dfymdhms.parse(baseTime);
			two = dfymdhms.parse(nowTime);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			diff = time2 - time1;
			return (diff / (1000 * 60));
			/*
			 * if(time1<time2) { diff = time2 - time1; } else { diff = time1 - time2; }
			 */
			/*
			 * day = diff / (24 * 60 * 60 * 1000); hour = (diff / (60 * 60 * 1000) - day *
			 * 24); min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			 */
			// sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// long[] times = {day, hour, min, sec};
		// return day+" "+" "+hour +" "+min+" "+sec;
		return 0;
	}
	
	 
	public static Date toDate(String time,String formatStr){
		 DateFormat dfymdhms = new SimpleDateFormat(formatStr);
		 try {
			return dfymdhms.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *��ȡ������ǰ���ʱ��
	 * @param time
	 * @param  day ��Ϊ��ȥ��ʱ�䣬 +Ϊδ����ʱ��
	 * @return
	 */
	public static Date getFureTimeByDay(Date time,int day) {
		String timeStr = getFureTimeByDay(dataToStringYMDHMS(time),day);
		return toDate(timeStr,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 *��ȡ������ǰ���ʱ��
	 * @param time
	 * @param  day ��Ϊ��ȥ��ʱ�䣬 +Ϊδ����ʱ��
	 * @return
	 */
	public static String getFureTimeByDay(String time,int day) {
		try {
		   Calendar calendar=Calendar.getInstance();   
		   calendar.setTime(dfymdhms.parse(time)); 
		   calendar.add(Calendar.DATE, day);
		   Date date1 = calendar.getTime();
		   return dfymdhms.format(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
	  


	/**
	 * ����ʱ�������������
	 * 
	 * @param baseTime
	 *            ʱ����� 1 ��ʽ��yyyy-MM-dd HH:mm:ss
	 * @param nowTime
	 *            ʱ����� 2 ��ʽ��yyyy-MM-dd HH:mm:ss
	 * @return ������ʾ�ٵ����ٷ���,-����ʾ�絽���ٷ�Ҳ����û�ٵ� long[] ����ֵΪ��{��, ʱ, ��, ��}
	 * 
	 */
	public static long getDistanceDay(String baseTime, String nowTime) {
		try {
			Date one = dfymdhms.parse(baseTime);
			Date two = dfymdhms.parse(nowTime);
			return getDistanceDay(one, two);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public static long getDistanceDay(Date baseTime, Date nowTime) {
		long time1 = baseTime.getTime();
		long time2 = nowTime.getTime();
		long diff;
		diff = time2 - time1;
		System.out.println("diff = "+diff);
		return (diff / (1000 * 60 * 60 *24));
	}
	/**
	 * ����ʱ�������������
	 * 
	 * @param baseTime
	 *            ʱ����� 1 ��ʽ��yyyy-MM-dd HH:mm:ss
	 * @param nowTime
	 *            ʱ����� 2 ��ʽ��yyyy-MM-dd HH:mm:ss
	 * @return ������ʾ�ٵ����ٷ���,-����ʾ�絽���ٷ�Ҳ����û�ٵ� long[] ����ֵΪ��{��, ʱ, ��, ��}
	 * 
	 */
	public static long getDistanceYear(String baseTime, String nowTime) {
		try {
			Date one = dfymdhms.parse(baseTime);
			Date two = dfymdhms.parse(nowTime);
			return getDistanceYear(one, two);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static long getDistanceYear(Date baseTime, Date nowTime) {
			long time1 = baseTime.getTime();
			long time2 = nowTime.getTime();
			long diff;
			diff = time2 - time1;
			return (diff / (1000 * 60 * 60 * 24) / 365);
			 
	}
	
	public static Integer getTimeIsFure(String baseTime, String nowTime) {
		Date one;
		Date two;
		try {
			one = dfymdhms.parse(baseTime);
			two = dfymdhms.parse(nowTime);
			return getTimeIsFureByDate(one,two);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �ж��������ڴ�С
	 * @param baseTime ��һ��ʱ��
	 * @param nowTime �ڶ���ʱ��
	 * @return 1 ��һ��ʱ�����δ��ʱ�䣩��0��ͬһʱ�䣬��1��һ��ʱ��С���ǹ�ȥʱ�䣩
	 */
	public static Integer getTimeIsFureByDate(Date one, Date two) {
		try {
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			diff = time2 - time1;
			if (diff > 0) {
				return -1;
			}else if (diff == 0) {
				return 0;
			}else if (diff < 0) {
				return 1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �ж�����ʱ�ִ�С
	 * @param baseTime ��һ��ʱ��
	 * @param nowTime �ڶ���ʱ��
	 * @return 1 ��һ��ʱ�����δ��ʱ�䣩��0��ͬһʱ�䣬��1��һ��ʱ��С���ǹ�ȥʱ�䣩
	 */
	public static Integer getOlnyTimeIsFure(String baseTime, String nowTime) {
		Date one;
		Date two;
		try {
			one = dfhm.parse(baseTime);
			two = dfhm.parse(nowTime);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			diff = time2 - time1;
			if (diff > 0) {
				return -1;
			}else if (diff == 0) {
				return 0;
			}else if (diff < 0) {
				return 1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
}
