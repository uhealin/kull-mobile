package com.kull.android;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Types;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kull.ObjectHelper;
import com.kull.StringHelper;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteOrmHelper extends SQLiteOpenHelper {

	public final static Map<Integer, Class> JDBCTYPE_REF_CLASS=new HashMap<Integer, Class>();
	public final static Map<Class,String> CLASS_REF_JDBCTYPE=new HashMap<Class, String>();
	
	private static Map<String, String> SQL_CACHE=new HashMap<String, String>();
	
	static{
		JDBCTYPE_REF_CLASS.put(Types.VARCHAR, String.class);
        JDBCTYPE_REF_CLASS.put(Types.INTEGER, Integer.class);
		
		CLASS_REF_JDBCTYPE.put(String.class, "text");
		CLASS_REF_JDBCTYPE.put(Integer.class, "int");
	}
	
	
	
	public SQLiteOrmHelper(Context context, String name) {
		super(context, name, null, 3);
		// TODO Auto-generated constructor stub
		
	}
	
	public SQLiteOrmHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stu
        
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public  void executeUpdate(String sql,Object ...params) throws SQLException{
		this.getWritableDatabase().execSQL(sql, params);
	}
	
	public int createTable(Class... clss) {
		String createSql="";
		int eff=0;
		for(Class cls :clss){
		OrmTable ormTable=(OrmTable)cls.getAnnotation(OrmTable.class);
		createSql+=MessageFormat.format("create table {0} ( ", ormTable.name());
		Field[] fields=cls.getDeclaredFields();
		for(Field field : fields){
			String fname=field.getName();
			Class fcls=field.getType();
			if(!CLASS_REF_JDBCTYPE.containsKey(fcls))continue;
			String type=CLASS_REF_JDBCTYPE.get(fcls);
			createSql+=MessageFormat.format("{0} {1} ,", fname,type);
		}
		createSql=createSql.substring(0, createSql.length()-1);
		createSql+=")";
		try{
		this.executeUpdate(createSql);
		}catch(Exception ex){continue;}
	    eff++;
		}
		return eff;
	}
	
	public int dropTable(Class... clss) {
		String createSql="";
		int eff=0;
		for(Class cls :clss){
		OrmTable ormTable=(OrmTable)cls.getAnnotation(OrmTable.class);
		createSql+=MessageFormat.format("drop table {0} ( ", ormTable.name());
		Field[] fields=cls.getDeclaredFields();
		for(Field field : fields){
			String fname=field.getName();
			Class fcls=field.getType();
			if(!CLASS_REF_JDBCTYPE.containsKey(fcls))continue;
			String type=CLASS_REF_JDBCTYPE.get(fcls);
			createSql+=MessageFormat.format("{0} {1} ,", fname,type);
		}
		createSql=createSql.substring(0, createSql.length()-1);
		createSql+=")";
		try{
		this.executeUpdate(createSql);
		}catch(Exception ex){continue;}
	    eff++;
		}
		return eff;
	}
	
	public int replaceTable(Class...clss){
		int eff=0;
		dropTable(clss);
		eff= createTable(clss);
		return eff;
	}
	
	public int truncateTable(Class... clss) throws SQLException{
		String createSql="";
		int eff=0;
		for(Class cls :clss){
		OrmTable ormTable=(OrmTable)cls.getAnnotation(OrmTable.class);
		createSql+=MessageFormat.format("truncate table {0} ( ", ormTable.name());
		Field[] fields=cls.getDeclaredFields();
		for(Field field : fields){
			String fname=field.getName();
			Class fcls=field.getType();
			if(!CLASS_REF_JDBCTYPE.containsKey(fcls))continue;
			String type=CLASS_REF_JDBCTYPE.get(fcls);
			createSql+=MessageFormat.format("{0} {1} ,", fname,type);
		}
		createSql=createSql.substring(0, createSql.length()-1);
		createSql+=")";
		try{
		this.executeUpdate(createSql);
		}catch(Exception ex){continue;}
	    eff++;
		}
		return eff;
	}
	
	
public int insert(Object...objs) throws Exception{
		
		int success=0;
		
		for(Object obj:objs){
			if(obj==null)continue;
			OrmTable table=null;
			String  sqlPattern="insert into {0} ({1}) values ({2})",sql="",
					sqlCacheKey=obj.getClass().getName()+":insert",cols="",vals="";
			Field[] fields=null;
		    
	    		table=obj.getClass().getAnnotation(OrmTable.class);
				fields=obj.getClass().getDeclaredFields();
		    	if(SQL_CACHE.containsKey(sqlCacheKey)){
		    		sql=SQL_CACHE.get(sqlCacheKey);
		    	}else{

					
					for(Field field:fields){
						if( ObjectHelper.isIn(field.getName(),table.excludeColumns())||
								   (!table.insertPk()&& field.getName().equalsIgnoreCase(table.pk()) )
						)continue;	
						cols+=MessageFormat.format(" `{0}`,",field.getName() );
						vals+=" ?,";
						
					}
					cols=StringHelper.trim(cols, ",");
					vals=StringHelper.trim(vals, ",");
		    	    sql=MessageFormat.format(sqlPattern, table.name(),cols,vals);
		    	    SQL_CACHE.put(sqlCacheKey, sql);
		    	    System.out.println(sql);
		    	}
		    	
		        int j=0;
		        List<Object> ivals=new ArrayList<Object>();
				for(Field field:fields){
					if( ObjectHelper.isIn(field.getName(),table.excludeColumns())||
					   (!table.insertPk()&& field.getName().equalsIgnoreCase(table.pk()) )
					)continue;	
					String getterName="get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
					Method m=obj.getClass().getDeclaredMethod(getterName);
					Object value=m.invoke(obj);
					ivals.add(value);
					j++;
				}
				
				executeUpdate(sql, ivals.toArray());
				
		   
		       
		    
		}
		return success;
	}

}
