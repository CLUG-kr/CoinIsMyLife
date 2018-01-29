import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class CreateFile {
	private ArrayList<Friend> list;
	
	
	public CreateFile(Character character, String fileName) throws IOException {
		fileName +=".txt";
		
		BufferedWriter fw = new BufferedWriter(new FileWriter(fileName)); // ���� �����
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
		fw.write("ģ�� : �̸�/����/��·���/ģ�е�/�����ص�");
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
			fw.write(Integer.toString(f.getLevel()));
			fw.newLine();
			fw.write(Integer.toString(f.getCloseness()));
			fw.newLine();
			fw.write(Integer.toString(f.getLoan()));
		} 
		
		fw.close();
		
	}

}
