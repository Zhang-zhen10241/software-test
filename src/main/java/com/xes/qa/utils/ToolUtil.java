/**  
 * @Title:ToolUtil.java   
 * @Package:util   
 * @Description:TODO 
 * @author:ZhangXiaolin     
 * @date:2019年4月7日 下午7:45:49   
 * @version:V1.0 
 * @Copyright:www.xueersi.com
 */

package com.xes.qa.utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

/**   
 * @ClassName:ToolUtil   
 * @Description:公共方法   
 * @author:ZhangXiaolin
 * @date:2019年4月7日 下午7:45:49  
 * @Copyright:www.xueersi.com 
 */

public class ToolUtil {
	/**
	 * 
	 * @Title:getStringNum   
	 * @Description:获取字符串中的数字 
	 * @param:@param str
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static String getStringNum(String str) {
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	
	/**
	 * 
	 * @Title:ascii2Char   
	 * @Description:将ASCII码转换成char类型   
	 * @param:@param str
	 * @param:@return      
	 * @return:char      
	 * @throws
	 */
	public  static char ascii2Char(String str) {  
        if (str.length() != 6) {  
            throw new IllegalArgumentException(  
                    "Ascii string of a native character must be 6 character.");  
        }  
        if (!PREFIX.equals(str.substring(0, 2))) {  
            throw new IllegalArgumentException(  
                    "Ascii string of a native character must start with \"\\u\".");  
        }  
        String tmp = str.substring(2, 4);  
        int code = Integer.parseInt(tmp, 16) << 8;  
        tmp = str.substring(4, 6);  
        code += Integer.parseInt(tmp, 16);  
        return (char) code;  
    } 
	
	static String PREFIX = "\\u";
	
	/**
	 * 
	 * @Title:ascii2Native   
	 * @Description:将ASCII转换成native   
	 * @param:@param str
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static String ascii2Native(String str) {  
        StringBuilder sb = new StringBuilder();  
        int begin = 0;  
        int index = str.indexOf(PREFIX);  
        while (index != -1) {  
            sb.append(str.substring(begin, index));  
            sb.append(ascii2Char(str.substring(index, index + 6)));  
            begin = index + 6;  
            index = str.indexOf(PREFIX, begin);  
        }  
        sb.append(str.substring(begin));  
        return sb.toString();  
    }
	
	/**
	 * 
	 * @Title:getDate   
	 * @Description:获取当前日期的明天 ，格式为yyyy-MM-dd
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static String getDate(){
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
//		String detailedTime = sdf.format(date);
//		return detailedTime;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	/**
	 * 
	 * @Title:getDateToday   
	 * @Description:获取今天的日期   
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static String getDateToday(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String detailedTime = sdf.format(date);
		return detailedTime;
	}
	
	/**
	 * 
	 * @Title:getDateFuture   
	 * @Description:获取相对于明天的未来某天的日期   ，格式为yyyy-MM-dd
	 * @param:@param num
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static String getDateFuture(int num){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, num+1);
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	/**
	 * 
	 * @Title:analysisOutlineIdsByJson  
	 * @Description:解析获取到Json数据并传入list中，http://courseapi.xesv5.com/Outline/Outline/getOutlineCatalogInfo?outlineIds=12476  
	 *  具体的json数据具体分析，这个适用于获取的大纲IDS
	 * @param:@param responseStr      
	 * @return:void      
	 * @throws
	 */
	public static List<String> analysisOutlineIdsByJson(String responseStr){
		JSONObject json = JSONObject.fromObject(responseStr);	
		JSONObject data = json.getJSONObject("data");	
		Set<String> keys = data.keySet();	
		List<String> idsList = new ArrayList<String>();
		for(String key : keys){
			JSONObject item = data.getJSONObject(key);
			Set<String> keys2 = item.keySet();			
			for(String key2 : keys2){
				JSONObject detail = item.getJSONObject(key2);
				String id = detail.getString("id");
				idsList.add(id);
			}
		}
		return idsList;
	}
	
	/**
	 * 
	 * @Title:analysisOutlineIdsByRegular   
	 * @Description:正则匹配获取到Json数据并传入list中，http://courseapi.xesv5.com/Outline/Outline/getOutlineCatalogInfo?outlineIds=12476   
	 * @param:@param responseStr
	 * @param:@return      
	 * @return:List<String>      
	 * @throws
	 */
	public static List<String> analysisOutlineIdsByRegular(String responseStr){
		responseStr = responseStr.replaceAll("\\r\\n", "");//获取到了接口字符串信息
	    String regex =  "\"id\": *\"(\\d+)\",";;	    
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(responseStr);	    
	    List<String> idsList = new ArrayList<String>();
	    while(matcher.find()) {
	      String id = matcher.group(1);
	      idsList.add(id);
	    }
	    return idsList;
	}
	
	/**
	 * 
	 * @Title:analysisOutlineIdsByRegularFromHTML  
	 * @Description:正则匹配获取到html数据并传入list中
	 * @param:@param responseStr
	 * @param:@return      
	 * @return:List<String>      
	 * @throws
	 */
	public static List<String> analysisOutlineIdsByRegularFromHTML(String responseStr){
		responseStr = responseStr.replace("\r\n", "");	    
	   //String regex = "<input type=\"hidden\" id=\"catalogIds\" name=\"catalog_ids\" value=\"(\\d+)\">";
		String regex = "id=\"catalogIds\" name=\"catalog_ids\" value=\"(\\d+)";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(responseStr);
	    List<String> idsList = new ArrayList<String>();   
	    while(matcher.find()) {
	        String id = matcher.group(1);
	        idsList.add(id);
	    }
	    return idsList;
	}
	
	public static void sleep(long s) {
		try {
			Thread.sleep(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public  static void main(String[] args) {
		System.out.println(getDate());
		System.out.println(getDateFuture(-1));
		
	}
}
