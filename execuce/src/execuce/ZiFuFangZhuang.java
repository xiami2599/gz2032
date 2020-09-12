package execuce;

import java.util.Scanner;

public class ZiFuFangZhuang {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		String str=sc.next();
		System.out.println(inputStr(str));
	}
		
	
	
	public static String inputStr(String str) {
		
		 StringBuilder sb=new StringBuilder();
		
		
		if(str==null||str.equals("")||str.length()>100) {
			return null;
		}
		
		for (int i = str.length()-1; i >=0; i--) {
			
			sb.append(str.charAt(i));
		}
		
		return sb.toString();
		
	}

}
