import java.util.ArrayList;

public class FriendsManager extends Friend{
	Friend know1; //�̸� ���� ���� ģ�е� �����ص�
	Friend know2;
	Friend know3;
	Friend know4;
	Friend know5;
	Friend friend1;
	Friend friend2;
	Friend friend3;
	Friend friend4;
	Friend friend5;
	Friend bestFriend1;
	Friend bestFriend2;
	Friend bestFriend3;
	Friend bestFriend4;
	Friend bestFriend5;
	Friend soulMate1;
	Friend soulMate2;
	Friend soulMate3;
	Friend soulMate4;
	Friend soulMate5;
	
	public FriendsManager() {
		know1 = new Friend("�輺��", 25, 1, 10, 0); //�̸� ���� ���� ģ�е� �����ص�
		know2 = new Friend("���α�", 22, 1, 10, 0);
		know3 = new Friend("�����", 22, 1, 10, 0);
		know4 = new Friend("��ҿ�", 21, 1, 10, 0);
		know5 = new Friend("�����", 22, 1, 10, 0);
		friend1 = new Friend("�̼���", 24, 2, 30, 0);
		friend2 = new Friend("���ؿ�", 24, 2, 30, 0);
		friend3 = new Friend("�̽¿�", 26, 2, 30, 0);
		friend4 = new Friend("����ȯ", 28, 2, 30, 0);
		friend5 = new Friend("������", 28, 2, 30, 0);
		bestFriend1 = new Friend("�����", 22, 3, 70, 0);
		bestFriend2 = new Friend("�����", 27, 3, 70, 0);
		bestFriend3 = new Friend("�ٽþ�", 21, 3, 70, 0);
		bestFriend4 = new Friend("���䳪", 24, 3, 70, 0);
		bestFriend5 = new Friend("�̴Ϲ�", 26, 3, 70, 0);
		soulMate1 = new Friend("������", 25, 4, 100, 0);
		soulMate2 = new Friend("���̻�", 25, 4, 100, 0);
		soulMate3 = new Friend("ī����", 25, 4, 100, 0);
		soulMate4 = new Friend("�迡��", 25, 4, 100, 0);
		soulMate5 = new Friend("���Ӹ�", 25, 4, 100, 0);
	}

	
}
