package org.pccpa.api;

public class StringHelper {

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

}
