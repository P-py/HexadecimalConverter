package hexadecimalconverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.List;

public class HexadecimalConverter {
	public static void menuStart() {
		System.out.println("-HEXADECIMAL CONVERTER-");
		System.out.println("Developed by: Pedro Salviano, v0.1"); 
	}
	public static void menu() {
		System.out.println("\n-MENU-");
		System.out.println("1 - Hexadecimal to decimal");
		System.out.println("2 - Decimal to hexadecimal");
		System.out.println("3 - Exit");
		System.out.printf("->");
	}
	public static void exit() {
		System.out.println("Exiting...");
		System.exit(0);
	}
	public static List<Integer> hexadecimalToDecimal(String value, String[] hexadecimalRange, int[] decimalRange) {
		List<Integer> resultList = new ArrayList<>();
		int decimalValue=0;
		for (int i=0; i<value.length(); i++) {
			for (int j=0; j<hexadecimalRange.length; j++) {
				if ((value.toUpperCase().charAt(i))==hexadecimalRange[j].charAt(0)) {
					decimalValue = decimalRange[j];
					break;
				}
			}
			double decimalResult = decimalValue*Math.pow(16, (value.length()-1)-i);
			resultList.add((int)decimalResult);
		}
		
		return resultList;
	}
	public static List<String> decimalToHexadecimal(int value, String[] hexadecimalRange) {
		List<String> resultList = new ArrayList<>();
		int oldValue = value;
		do {
			int newValue = oldValue/16;
			int remanescent = oldValue%16;
			oldValue = newValue;
			String remanescentString = hexadecimalRange[remanescent];
			//String remanescentString = Integer.toString(remanescent);
			resultList.add(remanescentString);
		}while(oldValue!=0);
		Collections.reverse(resultList);
		return resultList;
	}
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		String[] hexadecimalRange = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
		int[] decimalRange = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		
		
		menuStart();
		
		int option;
		do {
			menu();
			option = sc.nextInt();
			switch(option) {
				case 1:
					System.out.printf("Type in the hexadecimal value: ");
					//clear the input breakline cache
					sc.nextLine();
					String hexadecimalValue = sc.nextLine();
					List<Integer> resultToDecimal = hexadecimalToDecimal(hexadecimalValue, hexadecimalRange, decimalRange);
					System.out.println(resultToDecimal.stream().collect(Collectors.summingInt(Integer::intValue)));
					break;
				case 2:
					System.out.printf("Type in the decimal value: ");
					int decimalValue = sc.nextInt();
					List<String> resultToHexadecimal = decimalToHexadecimal(decimalValue, hexadecimalRange);
					System.out.println(resultToHexadecimal.toString().substring(1, 3*resultToHexadecimal.size()-1).replaceAll(", ", ""));
					break;
				case 3:
					exit();
					break;
			}
		}while(option!=3);
		
		sc.close();
	}
}