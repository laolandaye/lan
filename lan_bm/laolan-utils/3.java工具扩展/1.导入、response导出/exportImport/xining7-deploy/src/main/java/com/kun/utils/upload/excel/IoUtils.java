package com.kun.utils.upload.excel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class IoUtils {



	/**
	 *
	 * @param 绝对路径
	 * @return 是否是文件
	 */
	public static boolean isFile(String path) {
		return path != null ? new File(path).isFile() : false;
	}

	/**
	 *
	 * @param 绝对路径
	 * @return 是否是一个文件夹
	 */
	public static boolean isDirectory(String path) {
		return path != null ? new File(path).isDirectory() : false;
	}

	/**
	 * 获取文件名
	 *
	 * @param path
	 * @return
	 */
	public static String getFileName(String path) {
		if (isFile(path))
			return new File(path).getName();
		else
			return null;
	}

	/**
	 * 获取文件名
	 *
	 * @param file
	 * @return
	 */
	public static String getFileName(File file) {
		if (file != null && file.isFile())
			return file.getName();
		else
			return null;
	}


	/**
	 * 获取前缀
	 *
	 * @param path
	 * @return
	 */
	public static String getSuffix(String path) {
		if (isFile(path))
			return getSuffix(new File(path));
		else
			return null;
	}

	/**
	 * 创建文件夹
	 *
	 * @param path
	 * @return
	 */
	public static boolean createDirectories(String path) {
		if (isDirectory(path)){
			return true;
		}
		else{
			//如果是文件  创建其文件夹
			if(isFilePath(path)){
				int num = path.lastIndexOf("\\");
				if(num<0){
					num = path.lastIndexOf("/");
				}
				path = path.substring(0,num);
			}

			return new File(path).mkdirs();
		}
	}

	/**
	 * 是否是一个 文件路径
	 * @param filePath
	 * @return
	 */
	public static boolean isFilePath(String filePath){
		if(StringUtils.isNullOrEmpty(filePath)){
			return false;
		}
		return filePath.indexOf(".")>=0;
	}


	/**
	 * 创建文件
	 * @param bytes
	 * @param filePath 文件全路径
	 */
	public static void writeAllBytes(byte[] bytes,String filePath){
		if(bytes == null || bytes.length<=0 || filePath == null || "".equals(filePath.trim())){
			return;
		}
		OutputStream outputStream = null;
		String[] split = filePath.split("/");
		String fileName = split[split.length-1];
		String directoryPath =filePath.substring(0, filePath.lastIndexOf("/")) ;
		if(!isDirectory(directoryPath)){
			createDirectories(directoryPath);
		}
		filePath = combine(directoryPath,fileName);
		try {
			outputStream = new FileOutputStream(filePath);
			outputStream.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(outputStream != null){
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 *
	 * @param file 读取文件的全部字节
	 * @return
	 */
	public static  byte[] readAllBytes(File file){
		byte[] result = null;
		if(file.isFile()){
			try {
				result = readAllBytes(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 *
	 * @param inStream 读取文件的全部字节
	 * @return
	 */
	public static  byte[] readAllBytes(InputStream inStream)   { 
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream(); 
		byte[] buff = new byte[100]; 
		byte[] result = null;
		int rc = 0; 
		try {
			while ((rc = inStream.read(buff, 0, 100)) > 0) { 
				swapStream.write(buff, 0, rc); 
			}
			result = swapStream.toByteArray(); 
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				swapStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		return result; 
	} 

	/**
	 *
	 * @param file
	 * @return
	 */
	public static String getSuffix(File file) {
		if (file != null && file.isFile())
			return file.getName().substring(file.getName().lastIndexOf(".") + 1);
		else
			return null;
	}


	/**
	 * 读取一个文本文件的全部字符
	 *
	 * @param path
	 * @return
	 */
	public static String readAllTxt(String path) {
		StringBuilder stringBuilder = new StringBuilder();
		File file = new File(path);
		if (file.isFile() && file.exists()) {
			InputStreamReader read = null;
			try {
				read = new InputStreamReader(new FileInputStream(file), "utf8");
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					stringBuilder.append(lineTxt + "\r\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (read != null)
					try {
						read.close();
					} catch (IOException e) {
						read = null;
					}
			}

		}
		return stringBuilder.toString();
	}

	public static List<String> readTxtByLine(String path) throws Exception {
		List<String> list = new ArrayList<String>();
		File file = new File(path);
		if (file.isFile() && file.exists()) {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				list.add(lineTxt);
			}
			read.close();
		}

		return list;
	}

	public static void writeTxt(List<String> info, String fileName) {
		writeTxt(info.toArray(new String[info.size()]), fileName);
	}

	/**
	 *
	 * @param info
	 * @param fileName
	 */
	public static void writeTxt(String[] info, String fileName) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(fileName, true);
			for (String str : info) {
				writer.write(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				writer = null;
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 * @param info
	 *            把字符串写入文件
	 * @param fileName
	 *           文件全路径名
	 */
	public static void writeAllTxt(String info, String fileName) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(fileName);
			writer.write(info);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				writer = null;
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 * @param info
	 *            把字符串写入文件
	 * @param fileName
	 *           文件全路径名
	 */
	public static boolean writeAllTxt2(String info, String fileName) {
		FileWriter writer = null;
		boolean isTrue = true;
		try {
			writer = new FileWriter(fileName);
			writer.write(info);

		} catch (Exception e) {
			e.printStackTrace();
			isTrue = false;
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				writer = null;
				e.printStackTrace();
			}
		}
		return isTrue;
	}

	/**
	 * 复制文件
	 * @param oldFilePath
	 *            文件路径
	 * @param newFilePath
	 *            新文件的path
	 * @return
	 */
	public static boolean copyer(String oldFilePath, String newFilePath) {
		return copyer(oldFilePath, newFilePath, 10);
	}


	/**
	 * 复制文件
	 * @param oldFilePath
	 *            文件路径
	 * @param newFilePath
	 *            新文件的path
	 *  @param bufferSize 缓冲大小默认10MB
	 * @return
	 */
	public static boolean copyer(String oldFilePath, String newFilePath, int bufferSize) {
		if (oldFilePath == null || "".equals(oldFilePath.trim()))
			return false;
		boolean flag = true;
		try {
			InputStream inputStream = new FileInputStream(oldFilePath);
			flag = copyer(inputStream, newFilePath, bufferSize);
		} catch (FileNotFoundException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 复制文件
	 *
	 * @param inputStream
	 *            输入流
	 * @param newFilePath
	 *            新文件的path
	 * @return
	 */
	public static boolean copyer(InputStream inputStream, String newFilePath) {
		return copyer(inputStream, newFilePath, 10);
	}

	/**
	 * 复制文件
	 *
	 * @param inputStream
	 *            输入流
	 * @param newFilePath
	 *            新文件的path
	 * @return
	 */
	public static boolean copyer(InputStream inputStream, String newFilePath, int bufferSize) {
		if (inputStream == null || newFilePath == null || "".equals(newFilePath.trim()) || bufferSize <= 0)
			return false;
		boolean flag = true;
		OutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(newFilePath);
			byte[] buffer = new byte[1024 * 1024 * bufferSize];
			int byteread = 0;
			while ((byteread = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, byteread);
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			try {
				outputStream.flush();
				if (inputStream != null)
					inputStream.close();
				if (outputStream != null)
					outputStream.close();
			} catch (IOException e) {
				inputStream = null;
				outputStream = null;
				e.printStackTrace();
			}
		}

		return flag;
	}
	/**
	 * 保存图片的方法
	 */
	public static boolean savaImage(BufferedImage image,String suffxi,String  path){
		if(image==null&&"".equals(suffxi)&&suffxi==null&&path==null&&"".equals(path)){
			return false;
		}
		String dirPath=path.substring(0, path.lastIndexOf("/"));
		boolean flag = true;
		File f = new File(dirPath);
		if(!f.exists()){
			f.mkdirs();
		}
		try {
			flag = ImageIO.write(image, suffxi, new File(path));
		} catch (IOException e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 保存excle的方法
	 */
	public static boolean savaExcel(MultipartFile item,String path){
		InputStream is=null;
		FileOutputStream fos=null;
		if(item==null&&"".equals(path)&&path==null){
			return false;
		}
		String dirPath=path.substring(0, path.lastIndexOf("/"));
		boolean flag = true;
		File f = new File(dirPath);
		if(!f.exists()){
			f.mkdirs();
		}
		try {
			is=item.getInputStream();
			fos = new FileOutputStream(path);
			byte[] b = new byte[1024];
			while((is.read(b)) != -1){
				fos.write(b);
			}
		} catch (IOException e) {
			flag=false;
			e.printStackTrace();
		}finally {
			try {
				if(is!=null){
					is.close();
				}
				if(fos!=null){
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 *
	 * @param folderPath
	 *           文件夹目录（输入目录）
	 * @param newFolderPath
	 *            文件夹目录（输出目录）
	 * @param deep          
	 *            是否查找子文件夹
	 * @param fileNames
	 *            文件名
	 * @return 成功复制的文件的文件名集合
	 */
	public static List<String> copyerFromFolder(String folderPath, String newFolderPath, boolean deep,
			String... fileNames) {
		if (folderPath == null || fileNames == null || "".equals(folderPath.trim()) || fileNames.length <= 0)
			return null;
		List<String> successFileName = new ArrayList<String>();
		File folder = new File(folderPath);
		if (folder.isDirectory()) {
			File[] listFiles = folder.listFiles();
			for (File file : listFiles) {
				if (file.isFile()) {
					for (String fileName : fileNames) {
						if (fileName.toLowerCase().equals(file.getName().toLowerCase())) {
							if (copyer(combine(folderPath, fileName), combine(newFolderPath, fileName)))
								successFileName.add(fileName);
						}
					}

				} else if (file.isDirectory() && deep) {
					copyerFromFolder(file.getPath(), newFolderPath, deep, fileNames);
				}
			}
		}

		return successFileName;
	}

	/**
	 *
	 * @param folderPath
	 *           文件夹目录（输入目录）
	 * @param newFolderPath
	 *            文件夹目录（输出目录）
	 * @param deep          
	 *            是否查找子文件夹
	 * @param fileNames
	 *            文件名
	 * @return 成功复制的文件的文件名集合
	 */
	public static List<String> copyerFromFolder(String folderPath, String newFolderPath, boolean deep,
			List<String> fileNames) {
		return copyerFromFolder(folderPath, newFolderPath, deep, fileNames.toArray(new String[fileNames.size()]));
	}

	/**
	 *
	 * @param folderPath
	 *           文件夹目录（输入目录）
	 * @param newFolderPath
	 *            文件夹目录（输出目录）
	 * @param fileNames
	 *            文件名
	 * @return 成功复制的文件的文件名集合
	 */
	public static List<String> copyerFromFolder(String folderPath, String newFolderPath, List<String> fileNames) {
		return copyerFromFolder(folderPath, newFolderPath, false, fileNames.toArray(new String[fileNames.size()]));
	}

	/**
	 * 拼接一个文件路径
	 *
	 * @param
	 * @return 文件全路径
	 */
	public static String combine(String... strings) {
		if (strings == null || strings.length <= 0)
			return null;
		for (int i = 0; i < strings.length; i++) {
			strings[i] = repalceLine(strings[i]);
		}
		return null;
	}

	/**
	 * 去掉字符串前后"\" 或者 "/"
	 * @param str
	 *           
	 * @return
	 */
	public static String repalceLine(String str) {
		String suffix = str.substring(str.length() - 1, str.length());
		String prefix = str.substring(0, 1);
		if (suffix.equals("\\") || suffix.equals("/")) {
			str = str.substring(0, str.length() - 1);
		}
		if (prefix.equals("\\") || prefix.equals("/")) {
			str = str.substring(1, str.length());
		}
		return str;
	}

	/**
	 * 读取properties文件
	 *
	 * @param path
	 *            properties路径
	 * @return map
	 */
	public static Map<Object, Object> readProperties(String path) {

		if (!new File(path).isFile())
			return null;
		Map<Object, Object> properties = null;
		try {
			InputStream inputStream = new FileInputStream(path);
			properties = readProperties(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * 读取properties文件
	 *
	 * @param inputStream
	 * @return Map
	 */
	public static Map<Object, Object> readProperties(InputStream inputStream) {
		if (inputStream == null)
			return null;
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				inputStream = null;
				e.printStackTrace();
			}
		}
		return properties;
	}
	public static BufferedImage formatImage(InputStream inputStream,Integer preWidth,Integer preHeight){
		Image[] array = new Image[10];
		BufferedImage bi=null;
		BufferedImage newBI=null;
		try {
			bi=ImageIO.read(inputStream);
			double width=bi.getWidth();
			double height=bi.getHeight();
			double proportion=(width/preWidth>=height/preHeight)?(width/preWidth):(height/preHeight);
			int newWidth=(int) (width/proportion);
			int newHeight=(int) (height/proportion);
			newBI = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
			array[0]=newBI;
			Graphics graphics = newBI.createGraphics(); 
			graphics.drawImage(bi, 0, 0, newWidth, newHeight, null);
			return newBI;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newBI;
	}
	public static BufferedImage formatImage(BufferedImage bi,Integer preWidth,Integer preHeight){
		Image[] array = new Image[10];
		BufferedImage newBI=null;
		double width=bi.getWidth();
		double height=bi.getHeight();
		double proportion=(width/preWidth>=height/preHeight)?(width/preWidth):(height/preHeight);
		int newWidth=(int) (width/proportion);
		int newHeight=(int) (height/proportion);
		newBI = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
		array[0]=newBI;
		Graphics graphics = newBI.createGraphics(); 
		graphics.drawImage(bi, 0, 0, newWidth, newHeight, null);
		return newBI;

	}
	public static String getTimeStamp(){
		long time=new  Date().getTime();
		String str=time+"_";
		return str;
	}
	public static String getZoomTimeStamp(){
		long time=new  Date().getTime();
		String str="small_"+time+"_";
		return str;
	}
	/*
	 * Java文件操作 获取文件扩展名
	 *
	 */ 
	public static String getExtensionName(String filename) {  
		if ((filename != null) && (filename.length() > 0)) {  
			int dot = filename.lastIndexOf('.');  
			if ((dot >-1) && (dot < (filename.length() - 1))) {  
				return filename.substring(dot + 1);  
			}  
		}  
		return filename;  
	}
	/**
	 * 删除文件
	 * @param filePath
	 * @return
	 */
	public static boolean delFile(String filePath){

		File ff = new File(filePath);
		if(ff.exists()){
			ff.delete();
		}
		return true;

	}  
	/**
	 * 文件是否存在
	 * @param filePath
	 * @return
	 */
	public static boolean hisExist(String filePath){
		File ff = new File(filePath);

		return ff.exists();
	}
	//删除指定文件夹下所有文件
	//param path 文件夹完整绝对路径
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); //删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); //删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//  public static void main(String[] args) {
	//      String path="E:/gpjtestdata/uploads/temp";
	//      delAllFile(path);
	//  }
	//删除指定文件夹下所有文件
	//param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);//再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}


	public static void main(String[] args) {

		createDirectories("e:\\1\\1.txt");

	}

}