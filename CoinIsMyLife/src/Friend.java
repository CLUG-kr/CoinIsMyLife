
public class Friend implements Runnable {
	private Thread thread;
	
	private String name; // 이름
	private int age; // 나이
	private int moneyLevel; // 재력 레벨
	private double closeness; // 주인공과의 친밀도
	private int loan; // 빌려 준 돈
	
	private int rice; // 밥사주기 쿨타임
	private int alcohol; // 술사주기 쿨타임
	private int prediction; // 코인 예측 쿨타임
	private int loan_time; // 갚아야 할 
	
	public boolean rice_check = false;
	public boolean alcohol_check = false;
	public boolean prediction_check = false;
	public boolean loan_time_check = false;
	
	private int time; //

	public Friend() {
	}
	public Friend(String name, int age, int moneyLevel, int closeness, int loan) {
		this.name = name;
		this.age = age;
		this.moneyLevel = moneyLevel;
		this.closeness = closeness;
		this.loan = loan;

		time = 0;
		rice = 600;
		alcohol = 1200;
		prediction = 3000;
		
		loanTimeCheck();
	}
	public void loanTimeCheck() {
		if(closeness > 0 && closeness < 20) {loan_time=0;}
		else if(closeness >= 20 && closeness < 60) {loan_time=7200;}
		else if(closeness >= 60 && closeness < 90) {loan_time=36000;}
		else {loan_time=86400;}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getMoneyLevel() {
		return moneyLevel;
	}
	public void setMoneyLevel(int moneyLevel) {
		this.moneyLevel = moneyLevel;
	}
	public double getCloseness() {
		return closeness;
	}
	public void setCloseness(int closeness) {
		this.closeness = closeness;
	}
	public int getLoan() {
		return loan;
	}
	public void setLoan(int loan) {
		this.loan = loan;
	}
	public int getRice() {
		return rice;
	}
	public void setRice(int rice) {
		this.rice = rice;
	}
	public int getAlcohol() {
		return alcohol;
	}
	public void setAlcohol(int alcohol) {
		this.alcohol = alcohol;
	}
	public int getPrediction() {
		return prediction;
	}
	public void setPrediction(int prediction) {
		this.prediction = prediction;
	}
	public int getLoan_time() {
		return loan_time;
	}
	public void setLoan_time(int loan_time) {
		this.loan_time = loan_time;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public void run() {
		if(rice_check)
		while (!(rice==0)) {
			rice--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(rice==0) { // 시간 초과시
				rice=600; // 초기화
				rice_check = false;
				break; // 스레드 끝
			}
		}
		
		if(alcohol_check)
			while (!(alcohol==0)) {
				alcohol--;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(alcohol==0) { // 시간 초과시
					alcohol=1200; // 초기화
					alcohol_check = false;
					break; // 스레드 끝
				}
			}
		
		if(prediction_check)
			while (!(prediction==0)) {
				prediction--;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(prediction==0) { // 시간 초과시
					prediction=3000; // 초기화
					prediction_check = false;
					break; // 스레드 끝
				}
			}
		
		if(loan_time_check)
			while (!(loan_time==0)) {
				loan_time--;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(loan_time==0) { // 시간 초과시
					loanTimeCheck(); // 초기화
					loan_time_check = false;
					break; // 스레드 끝
				}
			}
	}

	
	public void start_rice() {
		time = rice;
		thread = new Thread(this);
		rice_check=true;
		thread.start();
	}
	
	public void start_alcohol() {
		time = alcohol;
		thread = new Thread(this);
		alcohol_check=true;
		thread.start();
	}
	
	public void start_prediction() {
		time = prediction;
		thread = new Thread(this);
		prediction_check=true;
		thread.start();
	}
	
	public void start_friendLoan() {
		time = loan_time;
		thread = new Thread(this);
		loan_time_check=true;
		thread.start();
	}
	
	public String relationship() {//친구 등급
		if(closeness > 0 && closeness < 20) {return "지인";}
		else if(closeness >= 20 && closeness < 60) {return "친구";}
		else if(closeness >= 60 && closeness < 90) {return "베프";}
		else {return "소울메이트";}
		
	}
	
	public void closenessControl_rice(String closenessLevel) {
		if(closenessLevel.equals("지인")) {
			this.closeness +=5;
		}
		else if(closenessLevel.equals("친구")) {
			this.closeness +=3;
		}
		else if(closenessLevel.equals("베프")) {
			this.closeness +=1;
		}
		else if(closenessLevel.equals("소울메이트")) {
			this.closeness +=0.1;
		}
	}
	
	public void closenessControl_alcohol(String closenessLevel) {
		if(closenessLevel.equals("지인")) {
			this.closeness +=10;
		}
		else if(closenessLevel.equals("친구")) {
			this.closeness +=5;
		}
		else if(closenessLevel.equals("베프")) {
			this.closeness +=2;
		}
		else if(closenessLevel.equals("소울메이트")) {
			this.closeness +=0.3;
		}
	}
	
	public void closenessControl_loan(String closenessLevel) {
		if(closenessLevel.equals("지인")) {
			this.closeness +=5;
		}
		else if(closenessLevel.equals("친구")) {
			this.closeness +=3;
		}
		else if(closenessLevel.equals("베프")) {
			this.closeness +=1;
		}
		else if(closenessLevel.equals("소울메이트")) {
			this.closeness +=0.1;
		}
	}
	
	
}
