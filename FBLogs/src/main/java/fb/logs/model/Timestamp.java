package fb.logs.model;

public class Timestamp {

	private int year;
	private int month;
	private int day;
	private int hour;
	private int min;
	private int sec;
	private int ms;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getSec() {
		return sec;
	}
	public void setSec(int sec) {
		this.sec = sec;
	}
	public int getMs() {
		return ms;
	}
	public void setMs(int ms) {
		this.ms = ms;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(year).append("-");
		stringBuilder.append(String.format("%02d", month)).append("-");
		stringBuilder.append(String.format("%02d", day)).append(" ");
		stringBuilder.append(String.format("%02d", hour)).append(":");
		stringBuilder.append(String.format("%02d", min)).append(":");
		stringBuilder.append(String.format("%02d", sec)).append(".");
		stringBuilder.append(String.format("%03d", ms));
		return stringBuilder.toString();
	}
	
}
