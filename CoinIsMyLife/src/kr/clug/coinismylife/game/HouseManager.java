
public class HouseManager extends House{
	public House level1;
	public House level2;
	public House level3;
	public House level4;
	public House level5;
	public House level6;
	
	public HouseManager() {
		level1 = new House("������ ��ĭ��", 1, 200, 30); //�̸� ���� ���� ����
		level2 = new House("â���� �޸� ��ĭ��", 2, 500, 35);
		level3 = new House("�װź��� ���� ��", 3 ,1000, 40);
		level4 = new House("�װź��� �� ���� ��", 4, 2000, 45);
		level5 = new House("���ǽ���", 5, 4000, 50);
		level6 = new House("����", 6, 10000, 200);
	}
	
	
	public House getLevel1() {
		return level1;
	}

	public House getLevel2() {
		return level2;
	}

	public House getLevel3() {
		return level3;
	}

	public House getLevel4() {
		return level4;
	}

	public House getLevel5() {
		return level5;
	}

	public House getLevel6() {
		return level6;
	}

}
