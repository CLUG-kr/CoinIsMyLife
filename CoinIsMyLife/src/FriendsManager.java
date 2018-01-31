import java.util.ArrayList;

public class FriendsManager extends Friend{
	Friend know1; //ÀÌ¸§ ³ªÀÌ ·¹º§ Ä£¹Ğµµ ºô·ÁÁØµ·
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
		know1 = new Friend("±è¼ºÀç", 25, 1, 10, 0); //ÀÌ¸§ ³ªÀÌ ·¹º§ Ä£¹Ğµµ ºô·ÁÁØµ·
		know2 = new Friend("Á¶¹Î±Ô", 22, 1, 10, 0);
		know3 = new Friend("±è°æÂù", 22, 1, 10, 0);
		know4 = new Friend("±è¼Ò¿¬", 21, 1, 10, 0);
		know5 = new Friend("±è¼öÁø", 22, 1, 10, 0);
		friend1 = new Friend("ÀÌ¼­¶ó", 24, 2, 30, 0);
		friend2 = new Friend("¿ÀÁØ¿À", 24, 2, 30, 0);
		friend3 = new Friend("ÀÌ½Â¿ì", 26, 2, 30, 0);
		friend4 = new Friend("ÀÌÀçÈ¯", 28, 2, 30, 0);
		friend5 = new Friend("³²»óÇõ", 28, 2, 30, 0);
		bestFriend1 = new Friend("±è´ö¹è", 22, 3, 70, 0);
		bestFriend2 = new Friend("»ïÅä¹Ù", 27, 3, 70, 0);
		bestFriend3 = new Friend("´Ù½Ã¾Æ", 21, 3, 70, 0);
		bestFriend4 = new Friend("·¹Åä³ª", 24, 3, 70, 0);
		bestFriend5 = new Friend("¹Ì´Ï¹ö", 26, 3, 70, 0);
		soulMate1 = new Friend("¿¥½ÊÀ°", 25, 4, 100, 0);
		soulMate2 = new Friend("¿¥ÀÌ»ç", 25, 4, 100, 0);
		soulMate3 = new Friend("Ä«±¸ÆÈ", 25, 4, 100, 0);
		soulMate4 = new Friend("±è¿¡¶¢", 25, 4, 100, 0);
		soulMate5 = new Friend("°³¸Ó¸®", 25, 4, 100, 0);
	}

	
}
