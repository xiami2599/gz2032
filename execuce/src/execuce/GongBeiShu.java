package execuce;
import java.util.Scanner;

public class GongBeiShu{
   
	
	public static void main(String[] args) {
		
     Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			int a= sc.nextInt();
			int b= sc.nextInt();
			
			System.out.println("最小公倍数："+a*b/getResult(a, b));
		}
	}
	
	//求最小公倍数
	public static int getResult(int a, int b) {
		
		if(a<b) {
			
			int temp;
			temp =a;
			a=b;
			b=temp;
		}
		
		int k=0;
		while(b!=0) {
			
			k=a%b;
			a=b;
			b=k;
		}
		
		return a;
	}
}