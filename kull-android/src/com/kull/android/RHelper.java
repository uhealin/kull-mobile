package com.kull.android;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.kull.ObjectHelper;

public class RHelper {

	
	public enum RType{
		id,attr,style,layout,menu,string
	}
	private static Map<String, Integer> CACHE_IDS=new HashMap<String, Integer>();
	//private static Map<RType,Map<String, Integer>> IDS=new HashMap<RType,Map<String, Integer>>();
	
	public static void init(Class clsR) throws Exception{
		if(!"R".equals(clsR.getSimpleName())){
			throw new Exception("this class must be R");
		}
		
		Class[] clss=  clsR.getDeclaredClasses();
		for(RType rType :RType.values()){
			//IDS.put(rType, new HashMap<String, Integer>());
			for(Class cls :clss){
				if(cls.getSimpleName().equals(rType.name())){
					Field[] fields=cls.getDeclaredFields();
					for(Field field :fields){
						String key=getKey(rType, field.getName());
						CACHE_IDS.put(key, field.getInt(cls));
					}
				}
			}
		}
	}
	
	private static String getKey(RType rtype,String name){
		return rtype.name()+":"+name;
	}
	
	public static int getResourceIdByName(RType rtype,String name) throws NullPointerException{
		String key=getKey(rtype, name);
		if(CACHE_IDS.containsKey(key)){
			return CACHE_IDS.get(key);
		}else throw new NullPointerException(rtype.name()+":"+name+" is not exist");
		
		
	}
	
	public static int getResourceIdByName(String name)throws NullPointerException{
		
		for(RType rtype :RType.values()){
			String key=getKey(rtype, name);
			if(CACHE_IDS.containsKey(key)){
				return CACHE_IDS.get(key);
			}
		}
		throw new NullPointerException(name+" is not exist");
		
	}
	
	
	public static int getResourceByKey(String key) throws NullPointerException{
		
		if(CACHE_IDS.containsKey(key)){
			return CACHE_IDS.get(key);
		}else throw new NullPointerException(key+" is not exist");
		
		
	}
	
	
	public static String getString(Context context, String name){
		String key=getKey(RType.string, name);
		if(CACHE_IDS.containsKey(key)){
			return context.getString(CACHE_IDS.get(key));
		}else throw new NullPointerException(name+" is not exist");
	}
}
