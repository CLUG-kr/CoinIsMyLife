import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeManager implements Runnable {
	private Thread thread;
	private Character c;
	private int cumulativeTime = 0;
	private int timeStamp_year;
	private int timeStamp_month;
	private int timeStamp_date;
	private int timeStamp_hour;
	private int timeStamp_minute;
	private int timeStamp_second;

	
	public TimeManager() {
		
	}
	
	public TimeManager(Character c) { // 파일에 저장되어 있는 시간을 불러와서 초기화
		this.c = c;
		
		this.cumulativeTime = c.getCumulativeTime();
		this.timeStamp_year = c.getYear();
		this.timeStamp_month = c.getMonth();
		this.timeStamp_date = c.getDate();
		this.timeStamp_hour = c.getHour();
		this.timeStamp_minute = c.getMinute();
		this.timeStamp_second = c.getSecond();
	}

	@Override
	public void run() {
		while (true) {
			cumulativeTime++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start_main() {
		thread = new Thread(this);
		thread.start();
	}
	
	public void setCumulativeTime(int cumulativeTime) {
		this.cumulativeTime = cumulativeTime; 
	}
	public int getCumulativeTime() {
		return cumulativeTime;
	}
	
	public Character getCharacter() {
		return c;
	}
	
	public void TimeSetUp() throws ParseException { // 게임 껏다가 나중에 다시켜도 현실시간 지난 것 만큼 흘러있음.
		// 저장된 시간
		String pastDateStr = Integer.toString(timeStamp_year) + "0" +Integer.toString(timeStamp_month) + Integer.toString(timeStamp_date) + 
				Integer.toString(timeStamp_hour) + Integer.toString(timeStamp_minute) + Integer.toString(timeStamp_second);
		
		// 현재 시간
		Date curDate = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		// 저장된 시간을 알맞게 변형
		Date pastDate = dateFormat.parse(pastDateStr);
		long pastDateTime = pastDate.getTime();
		
		// 현재 시간을 알맞게 변형
		curDate = dateFormat.parse(dateFormat.format(curDate));
		long curDateTime = curDate.getTime();
		
		//초로 표현
		long second = (curDateTime - pastDateTime)/1000;	
		
		TimeSetUp_monthlyRent((int)second, c.getTime());
		
		
		cumulativeTime = cumulativeTime + (int)second;
	}
	
	public void TimeSetUp_monthlyRent(int timeDiff, int rentTime) { // 재시작 시 월세 내야하는 시간 계산, 중복 허용
		
		boolean check = true;
		int cnt = 0;
		while(check) {
			if(timeDiff - rentTime>0) {
				cnt++;
				timeDiff = timeDiff - rentTime;
				}
			else {
				rentTime = rentTime - timeDiff;
				check = false;
			}
		}
		
		int money = Basic.hero.getMoney() - Basic.hero.house.getMonthlyRent()*cnt;
		Basic.hero.setTime(rentTime);
		Basic.hero.setMoney(money);
		
		
	}
	
	public void TimeSetUp_rice(int timeDiff, int riceTime) {
		// 친구들꺼 다 계산
	}
	

}
