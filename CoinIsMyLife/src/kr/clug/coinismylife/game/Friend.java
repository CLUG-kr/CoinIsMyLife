
public class Friend {
	private String name; // �̸�
	private int age; // ����
	private int level; // ��� ����
	private int closeness; // ���ΰ����� ģ�е�
	private int loan; // ���� �� ��

	public Friend() {
	}
	public Friend(String name, int age, int level, int closeness, int loan) {
		this.name = name;
		this.age = age;
		this.level = level;
		this.closeness = closeness;
		this.loan = loan;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getCloseness() {
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
}
