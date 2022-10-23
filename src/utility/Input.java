package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Input {

	public static boolean readBoolean() {
		boolean result;
		try {
			result = Boolean.parseBoolean(readString());
		} catch(NumberFormatException e) {
			result = false;
		}

		return result;
	}

	public static double readDouble() {
		double result;
		try {
			result = Double.parseDouble(readString());
		} catch(NumberFormatException e) {
			result = 0d;
		}

		return result;
	}

	public static float readFloat() {
		float result;
		try {
			result = Float.parseFloat(readString());
		}
		catch(NumberFormatException e) {
			result = 0f;
		}

		return result;
	}

	public static int readInt() {
		int result;
		String v;
		try {
			v = readString();
			result = Integer.decode(v);
		} catch(NumberFormatException e) {
			return 0;
		}

		return result;
	}

	public static String readString() {
		String result;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			result = in.readLine();
		} catch(IOException e) {
			result = "";
		}
		return result;
	}

	// Only for test purposes
	public static void main(String[] s) {
		int input = 0;
		while(input != -1) {
			System.out.print("Enter text: ");
			System.out.println("Utility.Input was:" + Input.readString());
			System.out.print("Enter float: ");
			System.out.println("Utility.Input was:" + Input.readFloat());
			System.out.print("Enter double: ");
			System.out.println("Utility.Input was:" + Input.readDouble());
			System.out.print("Enter boolean: ");
			System.out.println("Utility.Input was:" + Input.readBoolean());
			System.out.print("Enter integer number (Cancel with -1): ");
			input = Input.readInt();
			System.out.println("Utility.Input was: " + input);
		}
	}
}
