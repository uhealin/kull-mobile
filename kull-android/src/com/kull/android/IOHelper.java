package com.kull.android;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;



public class IOHelper {
	public static void write(Context context, String fileName, String content) throws IOException {
		if (content == null)
			content = "";

			FileOutputStream fos = context.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			fos.write(content.getBytes());

			fos.close();
		
	}

	/**
	 * 读取文本文件
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	public static String read(Context context, String fileName) throws IOException {
		
			FileInputStream in = context.openFileInput(fileName);
			return readInStream(in);
		
	}

	private static String readInStream(FileInputStream inStream) throws IOException {
		
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, length);
			}

			outStream.close();
			inStream.close();
			return outStream.toString();
		
		
	}
}
