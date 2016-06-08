package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.annotations.DataProvider;

public class ReadTxtFile {

	private String fileName;
	public static Object[][] data;

	public ReadTxtFile(String fileName, int numberOfDrafts) {
		this.fileName = fileName;
		data = new Object[numberOfDrafts][3];
		readFile(numberOfDrafts);
	}

	@DataProvider(name = "letterInfo")
	public static Object[][] getLetterInfo() {
		return data;
	}

	private void readFile(int numberDrafts) {
		BufferedReader reader = null;
		String temporary;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			for (int i = 0; i < numberDrafts; i++) {
				temporary = reader.readLine();
				if ("#address".equals(temporary)) {
					data[i][0] = reader.readLine();
				}
				temporary = reader.readLine();
				if ("#theme".equals(temporary)) {
					data[i][1] = reader.readLine();
				}
				temporary = reader.readLine();
				if ("#text".equals(temporary)) {
					data[i][2] = reader.readLine();
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println(fileName + " does not exist. First, create the data.");
			System.out.println(e);
		} catch (IOException e) {
			System.out.println("I/O exception.");
			System.out.println(e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				System.out.println("Error closing file.");
			}
		}
	}
}
