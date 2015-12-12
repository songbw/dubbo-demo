package com.flzc.base.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
	
	public static void zip(String file, String type, StringReader sr) {
		try {
			String temp = null;
			String objectFile = null;

			if (file.indexOf("\\") != -1) {
				temp = file.substring(file.lastIndexOf("\\") + 1);
				objectFile = file.substring(0, file.lastIndexOf("\\") + 1) + temp.replaceAll(type, "zip");
			}

			if (file.indexOf("/") != -1) {
				temp = file.substring(file.lastIndexOf("/") + 1);
				objectFile = file.substring(0, file.lastIndexOf("/") + 1) + temp.replaceAll(type, "zip");
			}

			if (!"html".equalsIgnoreCase(type)) {
				objectFile = objectFile.substring(0, objectFile.lastIndexOf(".zip")) + "_" + type + ".zip";
			}

			BufferedReader in = new BufferedReader(sr);

			FileOutputStream f = new FileOutputStream(objectFile);
			CheckedOutputStream ch = new CheckedOutputStream(f, new CRC32());
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(ch));

			out.putNextEntry(new ZipEntry(temp));
			int c;
			while ((c = in.read()) != -1)
				out.write(c);
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void zip(String file, String type) {
		try {
			String temp = null;
			String objectFile = null;

			if (file.indexOf("\\") != -1) {
				temp = file.substring(file.lastIndexOf("\\") + 1);
				objectFile = file.substring(0, file.lastIndexOf("\\") + 1) + temp.replaceAll(type, "zip");
			}

			if (file.indexOf("/") != -1) {
				temp = file.substring(file.lastIndexOf("/") + 1);
				objectFile = file.substring(0, file.lastIndexOf("/") + 1) + temp.replaceAll(type, "zip");
			}

			if (!"html".equalsIgnoreCase(type)) {
				objectFile = objectFile.substring(0, objectFile.lastIndexOf(".zip")) + "_" + type + ".zip";
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO8859_1"));

			FileOutputStream f = new FileOutputStream(objectFile);
			CheckedOutputStream ch = new CheckedOutputStream(f, new CRC32());
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(ch));

			out.putNextEntry(new ZipEntry(temp));
			int c;
			while ((c = in.read()) != -1)
				out.write(c);
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getTxtFileName(String htmlFile) {
		
		String temp = null;
		String txtpath = null;

		if (htmlFile.indexOf("\\") != -1) {
			temp = htmlFile.substring(htmlFile.lastIndexOf("\\") + 1);
			txtpath = htmlFile.substring(0, htmlFile.lastIndexOf("\\") + 1) + temp.replaceAll("html", "txt");
		}

		if (htmlFile.indexOf("/") != -1) {
			temp = htmlFile.substring(htmlFile.lastIndexOf("/") + 1);
			txtpath = htmlFile.substring(0, htmlFile.lastIndexOf("/") + 1) + temp.replaceAll("html", "txt");
		}

		return txtpath;
	}

	public static void genFile(String pathName, String fileContent, String encoding) {
		File file = new File(pathName);
		OutputStream bos = null;
		ByteArrayInputStream is = null;
		try {
			File parent = file.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}

			if (!file.exists()) {
				file.createNewFile();
			}

			is = new ByteArrayInputStream(fileContent.getBytes(encoding));
			bos = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];

			while ((bytesRead = is.read(buffer, 0, 8192)) != -1)
				bos.write(buffer, 0, bytesRead);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			if (is != null)
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	public static String readFromFile(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		StringBuffer content = new StringBuffer();
		String line = br.readLine();
		while (line != null) {
			content.append(line + "\n");
			line = br.readLine();
		}
		br.close();
		return content.toString();
	}

	public static String readFromFile(String filename, String charset) throws IOException {
		FileInputStream stream = new FileInputStream(filename);
		InputStreamReader reader = new InputStreamReader(stream, charset);
		BufferedReader br = new BufferedReader(reader);
		StringBuffer content = new StringBuffer();
		String line = br.readLine();
		while (line != null) {
			content.append(line);
			content.append("\r");
			content.append("\n");
			line = br.readLine();
		}
		reader.close();
		return content.toString();
	}

	public static void saveFile(String filename, String content) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		try {
			bw.write(content);
			bw.flush();
		} finally {
			bw.close();
		}
	}

	public static void removeFile(String filename) throws IOException {
		File f = new File(filename);
		if (f.exists())
			f.delete();
	}

	public static String getPath(String filename) {
		if (filename == null)
			return null;
		int pos = filename.lastIndexOf("/");
		if (pos <= 0)
			return null;
		return filename.substring(0, pos);
	}

	public static void appendFile(String filename, String content) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
		try {
			bw.write(content);
		} finally {
			bw.close();
		}
	}

	public static void saveFile(String filename, String content, String charset) throws IOException {
		OutputStream stream = new FileOutputStream(filename);
		OutputStreamWriter writer = new OutputStreamWriter(stream, charset);
		try {
			writer.write(content + "\n\r");
			writer.flush();
		} finally {
			writer.close();
		}
	}
}