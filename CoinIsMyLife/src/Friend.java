
public class Friend implements Runnable {
	private Thread thread;
	
	private String name; // �̸�
	private int age; // ����
	private int moneyLevel; // ��� ����
	private double closeness; // ���ΰ����� ģ�е�
	private int loan; // ���� �� ��
	
	private int rice; // ����ֱ� ��Ÿ��
	private int alcohol; // �����ֱ� ��Ÿ��
	private int prediction; // ���� ���� ��Ÿ��
	private int loan_time; // ���ƾ� �� 
	
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
			if(rice==0) { // �ð� �ʰ���
				rice=600; // �ʱ�ȭ
				rice_check = false;
				break; // ������ ��
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
				if(alcohol==0) { // �ð� �ʰ���
					alcohol=1200; // �ʱ�ȭ
					alcohol_check = false;
					break; // ������ ��
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
				if(prediction==0) { // �ð� �ʰ���
					prediction=3000; // �ʱ�ȭ
					prediction_check = false;
					break; // ������ ��
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
				if(loan_time==0) { // �ð� �ʰ���
					loanTimeCheck(); // �ʱ�ȭ
					loan_time_check = false;
					break; // ������ ��
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
	
	public String relationship() {//ģ�� ���
		if(closeness > 0 && closeness < 20) {return "����";}
		else if(closeness >= 20 && closeness < 60) {return "ģ��";}
		else if(closeness >= 60 && closeness < 90) {return "����";}
		else {return "�ҿ����Ʈ";}
		
	}
	
	public void closenessControl_rice(String closenessLevel) {
		if(closenessLevel.equals("����")) {
			this.closeness +=5;
		}
		else if(closenessLevel.equals("ģ��")) {
			this.closeness +=3;
		}
		else if(closenessLevel.equals("����")) {
			this.closeness +=1;
		}
		else if(closenessLevel.equals("�ҿ����Ʈ")) {
			this.closeness +=0.1;
		}
	}
	
	public void closenessControl_alcohol(String closenessLevel) {
		if(closenessLevel.equals("����")) {
			this.closeness +=10;
		}
		else if(closenessLevel.equals("ģ��")) {
			this.closeness +=5;
		}
		else if(closenessLevel.equals("����")) {
			this.closeness +=2;
		}
		else if(closenessLevel.equals("�ҿ����Ʈ")) {
			this.closeness +=0.3;
		}
	}
	
	public void closenessControl_loan(String closenessLevel) {
		if(closenessLevel.equals("����")) {
			this.closeness +=5;
		}
		else if(closenessLevel.equals("ģ��")) {
			this.closeness +=3;
		}
		else if(closenessLevel.equals("����")) {
			this.closeness +=1;
		}
		else if(closenessLevel.equals("�ҿ����Ʈ")) {
			this.closeness +=0.1;
		}
	}
	
	
}
