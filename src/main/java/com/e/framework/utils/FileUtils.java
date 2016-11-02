package com.e.framework.utils;
/********************************************************************
 * 
 * 
 * opearetor file
 * 
 * 
 ********************************************************************/
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUtils {
	private static int BUFFER_SIZE = 8096;	
	public FileUtils(String string) {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 追加文本
	 * @param str
	 * @param path
	 * @param charset
	 */
	public static void writeAppend(String str,String path,String charset){
		BufferedWriter  out = null;
		try{  
			String dirPath = getDirPath(path);
			File f = new File(dirPath);
			if(!f.exists())
				f.mkdirs();
		    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,true),charset));    
	        out.write(str);       
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static String getDirPath(String path){
		String[] arr = path.split("/");
		path = path.replaceAll(arr[arr.length - 1], "");
		return path;
	}
	public static void main(String[] args) {
//		FileUtils.getDirPath("d:/analyze/coverage/33.txt");
		Map<String,String> mss = new HashMap<String,String>();
		mss = readDir("E:/��������/��Ƭ",mss,"jpg");
		System.out.println(FileUtils.isFileSize("C:/Users/jack/Desktop/desk/phone1.txt"));
	}
	public static Vector<String> ReadFile(String path,String charset){
		Vector<String> urllist =new  Vector<String>(0); 
		try{
			String s = "";	
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path),charset)) ;
			while(s != null){
				s = in.readLine();	
				if(s != null)
					urllist.addElement(s);
			}
			in.close();
		}catch(Exception e){
			
		}
		return urllist;
	}
	/**
	 * 创建写
	 * @param str
	 * @param path
	 * @param charset
	 */
	public static void WriteCreateFile(String str,String path,String charset){
		OutputStreamWriter out = null;
		try{
			   String dirPath = getDirPath(path);
			   File f = new File(dirPath);
			   if(!f.exists())
				   f.mkdirs();
			   out = new OutputStreamWriter(new FileOutputStream(path),charset); 
			   out.write(str); 
			   out.flush(); 
		   }catch(Exception e){e.printStackTrace();}
		   finally{
			   try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	   
		   }	   
	}
	/**
	 * �õ��ļ��Ĵ�С
	 * @param path
	 * @return
	 */
	public static long isFileSize(String path){
		File f = new File(path);
		return f.length();
	}
	//��ȡproperties�ļ�
	public static Properties loadProperties(String path,String charset){
		Properties properties = new Properties();
		FileInputStream in = null;
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(new FileInputStream(path), charset);
			in = new FileInputStream(path);
			properties.load(isr);
		} catch (Exception e) {
			properties = null;
						
		}
		if(in != null){
			try {
			    in.close();
			} catch (IOException e) {
				properties = null;
				
			}
		}
		return properties;
	 }
	//��ȡ�ļ�����ļ�,���ļ��ŵ�������map<String,String> key:�ļ���,value:�ļ�·��
	public static Map<String,String> readDir(String path,Map<String,String> lpath,String flag){
		if(lpath == null)lpath = new LinkedHashMap<String,String>();
		File f = new File(path);
		File[] farr = f.listFiles();
		for (File file : farr) {		
			if(file.isDirectory())readDir(file.getPath(),lpath,flag);
			else {				
				if(file.getName().endsWith(flag))
					lpath.put(file.getName(), file.getPath());
				
			}
		}		
		return lpath;
	}
	//ɾ���ļ�
	public static void delFile(String path){
	    File f = new File(path);
	    if(f.exists())
		f.delete();
	}
	public static String ReadFileAsString(String path,String charset){
		String s = "";
		String retrunStr = "";
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path),charset)) ;
			while(s != null){
				s = in.readLine();	
				if(s != null)
					retrunStr += s;
			}
			in.close();
		}catch(Exception e){
				
		}
		return retrunStr;
	}
	public static void DownFile(String URL,String filename) throws Exception{
		try{
			FileOutputStream fos = null;
			BufferedInputStream bis = null;
			HttpURLConnection httpUrl = null;
			URL url = null;
			byte[] buf = new byte[BUFFER_SIZE];
			int size = 0;
			url = new URL(URL);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream(filename); 
			while ( (size = bis.read(buf)) != -1) 
				fos.write(buf, 0, size);
			
			fos.close();
			bis.close();
			httpUrl.disconnect();
		}
		catch(Exception e){
			return;
		}
	}
	
	
	/**
	 * 
	 * @author wjf
	 * @brief ��ѹzip����ĳ��Ŀ¼��
	 * @version 
	 * @date 2012-10-31
	 * @param zipFileName
	 * @param outputDirectory
	 * @return
	 */
	public static boolean unZipFile(String zipFileName,String outputDirectory){
		boolean returnFlag = false;
		boolean readFlag = false; // ��ȡ���ļ���ʶ����ֹ���ļ���ѹ����ѹ�ɹ�
		try {
			ZipFile zipfile = new ZipFile(zipFileName);
			Enumeration e = zipfile.entries();
			ZipEntry zipEntry = null;
			while (e.hasMoreElements()) { // ���ѹ���ļ������ļ������ѹ��
				readFlag = true;
				zipEntry = (ZipEntry) e.nextElement();
				if (zipEntry.isDirectory()) {// ���ΪĿ¼���򴴽���
					String name = zipEntry.getName();
					name = name.substring(0, name.length() - 1);
					File f = new File(outputDirectory + File.separator + name);
					f.mkdir();
				} else {
					String fileName = zipEntry.getName();
					File file = new File(outputDirectory+"/"+fileName);
					
					file.deleteOnExit();
					file.createNewFile();
					
					InputStream in = zipfile.getInputStream(zipEntry);
					FileOutputStream out = new FileOutputStream(file);

					byte[] by = new byte[1024];
					int c;
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					out.close();
					in.close();
					
				}
			}
			returnFlag = readFlag;
		} catch (Exception ex) {
			ex.printStackTrace();
			returnFlag = false;
		}
		return returnFlag;
	}
	public static void deleteFile(String path){
		File fileDown = new File(path);
		boolean l = fileDown.delete();
	}

	public static boolean deleteDir(String dir){
		 boolean success = true ;
         File file = new File(dir) ;
         if(file.exists()){
                 File[] list = file.listFiles() ;
                 if(list != null){
                         int len = list.length ;
                         for(int i = 0 ; i < len ; ++i){
                                 if(list[i].isDirectory()){
                                         deleteDir(list[i].getPath()) ;
                                 } else {
                                         boolean ret = list[i].delete() ;
                                         if(!ret){
                                                 success = false ;
                                         }
                                 }
                         }
                 }
         } else {
                 success = false ;
         }
         if(success){
                 file.delete() ;
         }
         return success ;
	}
}
