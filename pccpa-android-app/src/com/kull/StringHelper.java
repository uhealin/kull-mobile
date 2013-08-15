package com.kull;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;




public class StringHelper {

	
    public static enum Format{
    	upcaseFirstChar,lowcaseFirstChar
    }
    
    public static enum UrlCoding{
    	utf8("UTF-8"),gb2312,gbk;
    	
    	private String codeName;
    	
    	UrlCoding(){
    		this.codeName=this.name();
    	}
    	
    	UrlCoding(String codeName){
    		this.codeName=codeName;
    	}
    	
    	public String decode(String val) throws UnsupportedEncodingException {
    		return URLDecoder.decode(val, this.codeName);
    	}
    	
    	public String encode(String val) throws UnsupportedEncodingException{
    		return URLEncoder.encode(val,this.codeName);
    	}
    }
    
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> splitToList(String pStrVal,String pStrSplit) throws Exception
	{
		
		List<T> result = new ArrayList<T>();
		if(isBlank(pStrVal)){return result;}
		StringTokenizer tokenlizer = new StringTokenizer(pStrVal,pStrSplit);
		while(tokenlizer.hasMoreElements()) {
			Object s = tokenlizer.nextElement();
			result.add((T)s);
		}
		return result;
	}
	
	public static List<String> splits(String text,String... spars)throws Exception{
		String tempText=text;
		List<String> vals=new ArrayList<String>();
		for(int i=0;i<spars.length;i++){
			String spar=spars[i];
			if(!tempText.contains(spar)){
				throw new Exception(MessageFormat.format("\"{0}\" don''t contains \"{1}\"", tempText,spar));
			}
			if(i<spars.length-1){
			vals.add(tempText.substring(0,tempText.indexOf(spar)));
			tempText=tempText.substring(tempText.indexOf(spar)+1);
			}else{
				String[] tempTexts=tempText.split(spar);
				vals.add(tempTexts[0]);
				vals.add(tempTexts[1]);
			}
		}
		return vals;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> T[] splitToArray(String str,String seperators) throws Exception {

		List<T> list=splitToList(str, seperators);
		return (T[]) list.toArray();
	}
	
	public static <T> Set<T> splitToSet(String str,String seperators) throws Exception{
		List<T> list=splitToList(str, seperators);
		return new HashSet<T>(list);
	}
	
	public static boolean isBlank(String... strs)
	{
		for(String pStr :strs){
		if(pStr==null)return true;
		CharSequence cs= (CharSequence) pStr;
		int strLen = pStr.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		}
		return true;
	}
	
	public static boolean isNotBlank(String... pStr)
	{
		return !isBlank(pStr);
	}
	
	public static String quota(Object value)
	{
		if(value==null)return "\"\"";
		return "\""+value.toString()+"\"";
	}
	
	public static String formatId(String prefix,String spar,Object... subStr)
	{
		return formatId(true,prefix, spar,subStr);
		
	}
	
	public static String formatId(boolean isStr,String prefix,String spar,Object... subStr)
	{
		StringBuffer lSbrId=new StringBuffer("");
		String lSpar=ObjectHelper.parse(spar,",");
		lSbrId.append(ObjectHelper.parse(prefix,""));
		for(int i=0;i<subStr.length;i++)
		{
			lSbrId.append(subStr[i].toString());
			if(i!=subStr.length-1)
			{
			  lSbrId.append(lSpar);	
			}
		}
		if(isStr)
		{
			return quota(lSbrId.toString());
		}else
		{
			return lSbrId.toString();
		}
	}
	
	public static String format(String pStr,Format fe){
		if(isBlank(pStr))return "";
		String lStrFormat="";
		switch (fe) {
		case upcaseFirstChar:
			lStrFormat=pStr.substring(0, 1).toUpperCase()+pStr.substring(1);
			break;
		case lowcaseFirstChar:
			lStrFormat=pStr.substring(0, 1).toLowerCase()+pStr.substring(1);
			break;
		default:
			break;
		}
		return lStrFormat;
	}
	
	public static String ln(){
		return ln(1);
	}
	
	public static String ln(int ln){
		String lnReturn="";
		for(int i=1;i<=ln;i++){
			lnReturn+="\n";
		}
		return lnReturn;
	}
	
	public static String tab(){
		return tab(1);
	}
	
	public static String tab(int ln){
		String lnReturn="";
		for(int i=1;i<=ln;i++){
			lnReturn+="\t";
		}
		return lnReturn;
	}
	
	public static ArrayList<String> format(String pattern,ArrayList pListValue,Object...params){
		ArrayList<String> lListReturn=new ArrayList(); 
		for(Iterator it=pListValue.iterator();it.hasNext();){
		   String tempValue=it.next().toString();
           ArrayList<String> tempParam=new ArrayList<String>();
           tempParam.add(tempValue);
           for(int i=0;i<params.length;i++){
        	   tempParam.add(params[i].toString());
           } 
		   String value=MessageFormat.format(pattern,tempParam.toArray());
		   lListReturn.add(value);
		}
		return lListReturn;
	}
	
	
	

	
	
	public static String trimEnd(String value, String... suffixs){
		if(isBlank(value)){return value; }
		String v=value.trim();
		for(String suffix:suffixs){
        if(suffix==null){continue; }
        
        int index=0;
        while(v.endsWith(suffix)){
        	index=v.lastIndexOf(suffix);
        	v=v.substring(0, index);
        }
        
		}
		return v;
	}
	
	public static String trimStart(String value, String... prefixs){
		if(isBlank(value)){return value; }
		String v=value.trim();
		for(String prefix:prefixs){
		if(prefix==null){continue; }
        int index=0;
        int len=prefix.length();
        while(v.startsWith(prefix)){
        	index=v.indexOf(prefix)+len;
        	v=v.substring(index, v.length());
        }
		}
        return v;
	}
	
	public static String trim(String value,String... fixs){
		String v=trimStart(value, fixs);
		v=trimEnd(v, fixs);
		return v;
	}
	
	public static List<String> subString(String value,String start,String end){
		List<String> vals=new ArrayList<String>();
		String tempValue=value;
		while(tempValue.contains(start)&&tempValue.contains(end)){
			int startIndex=tempValue.indexOf(start);
			int endIndex=tempValue.indexOf(end);
			vals.add(tempValue.substring(startIndex+1, endIndex));
			tempValue=tempValue.substring(endIndex+1);
		}
		return vals;
	}
	
	public static String htmlWapper(String html){
		html=html.replace("<", "&lt;");
		html=html.replace(">", "&gt;");
		return html;
	}
	
	public static String concat(String ...vals){
		String re="";
		for(String val :vals){
			re+=val==null?"":val;
		}
		return re;
	}
	
}
