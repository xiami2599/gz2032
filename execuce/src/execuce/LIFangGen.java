package execuce;

import java.util.Scanner;

public class LIFangGen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入一个数");
		double input = sc.nextDouble();
		System.out.println(input+" 的立方根："+getCubeRoot(input));

	}

	public static double getCubeRoot(double input) {
		
		
		double result = input/3;
		
		return result;
		
	}
}
