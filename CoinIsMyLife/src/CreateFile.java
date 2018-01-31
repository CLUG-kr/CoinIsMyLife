import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class CreateFile {
	private ArrayList<Friend> list;
	
	
	public CreateFile(Character character, String fileName, int cumulativeTime) throws IOException {
		fileName +=".txt";
		
		
		BufferedWriter fw = new BufferedWriter(new FileWriter(fileName)); // ���� �����
		fw.write("�÷��̽ð�/��/��/��/��/��/��");
		fw.newLine();
		fw.write(Integer.toString(cumulativeTime));
		fw.newLine();
		fw.write(Integer.toString(character.getYear()));
		fw.newLine();
		fw.write(Integer.toString(character.getMonth()+1));
		fw.newLine();
		fw.write(Integer.toString(character.getDate()));
		fw.newLine();
		fw.write(Integer.toString(character.getHour()));
		fw.newLine();
		fw.write(Integer.toString(character.getMinute()));
		fw.newLine();
		fw.write(Integer.toString(character.getSecond()));
		fw.newLine();
		fw.write("����������� ���� �ð�");
		fw.newLine();
		fw.write(Integer.toString(character.getTime()));
		fw.newLine();
		fw.write("��/�ŷ�����");
		fw.newLine();
		fw.write(Integer.toString(character.getMoney()));
		fw.newLine();
		fw.write(Integer.toString(character.getLevelOfDeal()));
		fw.newLine();
		fw.write("�� : �̸�/����/����/����");
		fw.newLine();
		fw.write(character.house.getName());
		fw.newLine();
		fw.write(Integer.toString(character.house.getLevel()));
		fw.newLine();
		fw.write(Integer.toString(character.house.getPrice()));
		fw.newLine();
		fw.write(Integer.toString(character.house.getMonthlyRent()));
		
		
		//ģ���� ���� �Է�
		
		fw.newLine();
		fw.write("ģ�� : �̸�/����/��·���/ģ�е�/�����ص�/����/����/���ο�����/������");
		list = character.friendsList;
		Friend f ;
		Iterator<Friend> itr = list.iterator();
		while(itr.hasNext()) {
			f =itr.next();
			fw.newLine();
			fw.write(f.getName());
			fw.newLine();
			fw.write(Integer.toString(f.getAge()));
			fw.newLine();
			fw.write(Integer.toString(f.getMoneyLevel()));
			fw.newLine();
			fw.write(Double.toString(f.getCloseness()));
			fw.newLine();
			fw.write(Integer.toString(f.getLoan()));
			fw.newLine();
			fw.write(Integer.toString(f.getRice()));
			fw.newLine();
			fw.write(Integer.toString(f.getAlcohol()));
			fw.newLine();
			fw.write(Integer.toString(f.getPrediction()));
			fw.newLine();
			fw.write(Integer.toString(f.getLoan_time()));
			fw.newLine();
			fw.write("-----");
		} 
		
		fw.close();
		
	}

}
