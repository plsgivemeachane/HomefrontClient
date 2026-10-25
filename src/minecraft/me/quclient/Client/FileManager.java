package me.quclient.Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class FileManager {
	
	public static Gson gson = new Gson();
	
	public static File ROOT_DIR = new File("HomeFrontClient");
	public static File MODS_DIR = new File(ROOT_DIR,"Mods");
	
	public static void init() {
		if(!ROOT_DIR.exists()) ROOT_DIR.mkdirs();
		if(!MODS_DIR.exists()) MODS_DIR.mkdirs();
	}
	
	public static Gson getGson() {
		return gson;
	}
	
	public static File getModsDirectory() {
		return MODS_DIR;
	}
	
	public static void writeJsonToFile(File file,Object json){
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileOutputStream ostream = new FileOutputStream(file);
			ostream.write(gson.toJson(json).getBytes());
			ostream.flush();
			ostream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static <T extends Object> T readFromFile(File file, Class<T> c) {
		
		try {
			FileInputStream istream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(istream);
			BufferedReader buffer = new BufferedReader(inputStreamReader);
			
			StringBuilder builder = new StringBuilder();
			
			String line;
			
			while((line = buffer.readLine()) != null) {
				builder.append(line);
			}
			
			buffer.close();
			inputStreamReader.close();
			istream.close();
			
			return gson.fromJson(builder.toString(), c);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return null;
		
	}
}
