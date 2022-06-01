package javaTester;

public class Topic_05_Casting {
	
	public static void main(String[] args) {
		// Ngầm định = ko mất dữ liệu , tăng dần lên từ variable nhỏ lên lớn
//		byte bNumber  = 126;
//		System.out.println(bNumber);
//		
//		short sNumber = bNumber;
//		System.out.println(sNumber);
//		
//		int iNumber = sNumber;
//		System.out.println(iNumber);
//		
//		long lNumber = iNumber;
//		System.out.println(lNumber);
//		
//		float fNumber = lNumber;
//		
//		
//		double dNumber = fNumber;
//		System.out.println(dNumber);
		
		// Tường minh - Mất dữ liệu
		double dNumber = 654321789;
		System.out.println(dNumber);
		
		float fNumber = (float) dNumber;
		System.out.println(fNumber);
		
		long lNumber = (long) fNumber;
		System.out.println(lNumber);
		
		int iNumber = (int) lNumber;
		System.out.println(iNumber);
		
		short sNumber = (short) iNumber;
		System.out.println(sNumber);
	}
}
