package com.kull.android;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Types;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kull.LinqHelper;
import com.kull.ObjectHelper;
import com.kull.StringHelper;
import com.kull.util.IQueryable;


import android.R.color;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
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
		SQLiteDatabase database=this.getWritableDatabase();
		database.execSQL(sql, params);
		//database.close();
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
	
	
	public <T> T load(Class<T> cls,String pk) throws Exception{
		 T t=cls.newInstance();
		 return load(t, pk);
	}
	
	public <T> T load(T t,String pk) throws Exception {
		if(t==null)throw new NullPointerException();
		OrmTable table=null;
		String sql="";
		Field pkField=null;
		table=t.getClass().getAnnotation(OrmTable.class);
		pkField=t.getClass().getDeclaredField(table.pk());
	    return load(t, table.pk(),pk);
	}
	
	public <T> T load(Class<T> cls,String column,String pk) throws Exception{
		 T t=cls.newInstance();
		 return load(t,column, pk);
	}
	
	public <T> T load(T t,String column,String pk) throws Exception {
		if(t==null)throw new NullPointerException();
		OrmTable table=null;
		String sql="";
		table=t.getClass().getAnnotation(OrmTable.class);
		sql=MessageFormat.format(" {1}= ?", table.name(),column);
	    Cursor cursor=this.getReadableDatabase().query(table.name(), new String[]{"*"}, sql, new String[]{pk},"","","");
	    t=evalObject(t, cursor);
		return t;
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
						if( ObjectHelper.isIn(field.getName(),table.ingoreColumnNames())||
								   (!table.insertPk()&& field.getName().equalsIgnoreCase(table.pk()) )
						)continue;	
						cols+=MessageFormat.format(" `{0}`,",field.getName() );
						vals+=" ?,";
						
					}
					cols=StringHelper.trim(cols, ",");
					vals=StringHelper.trim(vals, ",");
		    	    sql=MessageFormat.format(sqlPattern, table.name(),cols,vals);
		    	    SQL_CACHE.put(sqlCacheKey, sql);
		    	    
		    	}
		    	
		        int j=0;
		        List<Object> ivals=new ArrayList<Object>();
				for(Field field:fields){
					if( ObjectHelper.isIn(field.getName(),table.ingoreColumnNames())||
					   (!table.insertPk()&& field.getName().equalsIgnoreCase(table.pk()) )
					)continue;	
					//String getterName="get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
					Method m=ObjectHelper.getGetter(obj.getClass(), field);
					Object value=m.invoke(obj);
					ivals.add(value);
					j++;
				}
				
				executeUpdate(sql, ivals.toArray());
				success++;
		   
		       
		    
		}
		return success;
	}


public int update(Object...objs) throws Exception{
	
	int success=0;
	
	for(Object obj : objs){
		if(obj==null)continue;
		OrmTable table=null;
		String sqlPattern="update {0} set {1} where {2}=? ",key="",sql=""
				,sqlCacheKey=obj.getClass().getName()+":update";
		Field[] fields=null;
		Field pkField=null;
		
			table=obj.getClass().getAnnotation(OrmTable.class);
			fields=obj.getClass().getDeclaredFields();
			pkField=obj.getClass().getDeclaredField(table.pk());
			if(SQL_CACHE.containsKey(sqlCacheKey)){
					sql=SQL_CACHE.get(sqlCacheKey);  
		}else{

		int i=0;
		for(Field field:fields){
			if(LinqHelper.isIn(field.getName(),table.ingoreColumnNames())||field.getName().equalsIgnoreCase(table.pk())){pkField=field;continue;}
			key+=MessageFormat.format(" `{0}` =? ,",field.getName() );
			i++;
		}
		key=StringHelper.trim(key, ",");
	    sql=MessageFormat.format(sqlPattern, table.name(),key,table.pk());
	    SQL_CACHE.put(sqlCacheKey,sql);
	    }
	    //.fields.preparedStatement=conn.prepareStatement(sql);
		List<Object> params=new ArrayList<Object>();
	    int j=0;
		for(Field field:fields){
			if(LinqHelper.isIn(field.getName(),table.ingoreColumnNames())||field.getName().equalsIgnoreCase(table.pk()))continue;	
			//String getterName="get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
			Method m=ObjectHelper.getGetter(obj.getClass(), pkField);
			Object value=m.invoke(obj);
			params.add(value);
			j++;
		}
		//String getterName="get"+pkField.getName().substring(0,1).toUpperCase()+pkField.getName().substring(1);
		Method m=ObjectHelper.getGetter(obj.getClass(), pkField);
		Object value=m.invoke(obj);
		//preparedStatement.setObject(j+1, value);
		params.add(value);
		
		executeUpdate(sql,params.toArray());
		//success+=preparedStatement.executeUpdate();
		success++;
		
	
	}
	return success;
}

public int delete(Object...objs)throws Exception{
	
	int success=0;
	
	for(Object obj:objs){
		if(obj==null)continue;
		OrmTable table=null;
		String  sqlPattern="delete from {0} where {1}=?",sql="",
				sqlCacheKey=obj.getClass().getName()+":delete";
		
		  table=obj.getClass().getAnnotation(OrmTable.class);
		  if(SQL_CACHE.containsKey(sqlCacheKey)){
			sql=SQL_CACHE.get(sqlCacheKey);  
		  }else{
		    sql=MessageFormat.format(sqlPattern, table.name(),table.pk());
		    SQL_CACHE.put(sqlCacheKey,sql); 
		  }
		  //System.out.println(sql);
		  String getterName="get"+table.pk().substring(0,1).toUpperCase()+table.pk().substring(1);;
		  Method method=obj.getClass().getDeclaredMethod(getterName);
		  Object value=method.invoke(obj);
		  this.executeUpdate(sql,new Object[]{value});
		  success++;
		
	}
	
	return success;
}

public <T> List<T> select(Class<T> cls) throws Exception{
    return select(cls, new String[]{"*"}, "", new String[]{});
}

public <T> List<T> select(Class<T> cls,String[] columns,String selection,String[] selectionArgs) throws Exception{
      return select(cls, columns, selection, selectionArgs,"","","");
}

  public <T> List<T> select(Class<T> cls,String[] columns,String selection,String[] selectionArgs,String  groupBy,String  having,String orderBy) throws Exception{
	   OrmTable table=cls.getAnnotation(OrmTable.class);
	   List<T> list=select(cls, table.name(), columns, selection, selectionArgs, groupBy, having, orderBy);
	   return list;
  }
  
  public <T> List<T> select(Class<T> cls,String table,String[] columns,String selection,String[] selectionArgs,String  groupBy,String  having,String orderBy) throws Exception{
	   SQLiteDatabase database= this.getReadableDatabase();
	   Cursor cursor=database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
	   List<T> list=evalList(cls, cursor);
	   cursor.close();
	   return list;
 }
       
   public  <T> List<T> evalList (Class<T> cls,Cursor cursor) throws InstantiationException, IllegalAccessException{
	   List<T> list=new ArrayList<T>();
	   while(!cursor.isAfterLast()){
		  T t=evalObject(cls, cursor);
		  list.add(t);
		  cursor.moveToNext();
	   }
	   return list;
   }
   
   private <T> T evalObject (Class<T> cls,Cursor cursor) throws InstantiationException, IllegalAccessException {
	   T t=cls.newInstance();
	   return evalObject(t, cursor);
   }
   
   private <T> T evalObject (T t,Cursor cursor) {
	   Class cls=t.getClass();
	   for(Field field :ObjectHelper.getAllDeclaredFields(cls)){
		   int i=cursor.getColumnIndex(field.getName());
		   if(i<0)continue;
		   Type ft=field.getType();
		   try{
		   Method setter=ObjectHelper.getSetter(cls, field); 
		   if(String.class.equals(ft)){
			   setter.invoke(t, cursor.getString(i));
		   }else if(Integer.class.equals(ft)){
			   setter.invoke(t, cursor.getInt(i));
		   }else if(Double.class.equals(ft)){
			   setter.invoke(t, cursor.getDouble(i));
		   }else if(Float.class.equals(ft)){
			   setter.invoke(t, cursor.getFloat(i));
		   }else if(Long.class.equals(ft)){
			   setter.invoke(t, cursor.getLong(i));
		   }else if(Short.class.equals(ft)){
			   setter.invoke(t, cursor.getShort(i));
		   }else if(Byte[].class.equals(ft)){
			   setter.invoke(t, cursor.getBlob(i));
		   }
		   }catch(Exception ex){continue;}
		   
	   }
	   return t;
   }

  
}
