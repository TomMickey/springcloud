package com.grgbanking;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class GenetorFile {
	
	public static void main(String[] args) throws IOException {
		String[] names = {"Tom","Jack","Lucy"};
		Integer[] ages = {12,23,34};
		FileOutputStream fileOutputStream = new FileOutputStream("file.txt");
		for(int i = 0;i < 600000000;i++) {
			Random random = new Random();
			fileOutputStream.write(names[random.nextInt(names.length)].getBytes());
			fileOutputStream.write(" ".getBytes());
			fileOutputStream.write(ages[random.nextInt(ages.length)].toString().getBytes());
			fileOutputStream.write("\n".getBytes());
		}
		fileOutputStream.close();
	}

}
