package com.wechat.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static List<String> getFile(File dir) {
		List<String> fileList = new ArrayList<String>();
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if (file.isDirectory()) {
					fileList.addAll(getFile(file));
				} else if (file.isFile()) {
					String _path = file.getPath();
					if (_path.contains("mapper") && _path.contains(".xml")) {
						fileList.add(file.getPath());
					}
				}
			}
		}
		return fileList;
	}

	public static List<String> getFileContext(String fileName) {
		List<String> listData = new ArrayList<String>();
		File file1 = new File(fileName);
		if (file1.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file1);
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String line;
				while ((line = br.readLine()) != null) {
					listData.add(line);
				}
				br.close();
				isr.close();
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return listData;
	}

	public static void setFileContext(String fileName, List<String> data) {
		File file = new File(fileName);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			for (int i = 0; i < data.size(); i++) {
				bw.write(data.get(i));
				bw.newLine();
			}
			bw.close();
			osw.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
