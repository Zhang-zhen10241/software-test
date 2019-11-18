/**  
* <p>Title: HttpUtil.java</p>  
* <p>Description: </p>   
* <p>Company: www.xueersi.com</p>  
* @author zhangxiaolin1
* @date 2019年3月15日
*/ 
package com.xes.qa.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Lookup;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/**
 * @author 
 *
 */
public class HttpUtil {

	/**
	 * @param args
	 */
	private static HttpClientContext context = null;//初始化context对象进行上下文管理
	private static Lookup<CookieSpecProvider> lookup = null;//初始化cookie注册规范
	private static CookieStore cookieStore = null;//初始化cookie存储对象
	/**
	 * 初始化context对象,进行cookie上下文管理
	 */
	static{
		context = HttpClientContext.create();
		context.setCookieSpecRegistry(lookup);//设置cookie注册规范
		context.setCookieStore(cookieStore);//设置cookie存储
	}
	
	
	//get请求
	public static String sendGetRequest(String url){
		String responseStr = null;
		CloseableHttpClient client=HttpClientBuilder.create().build();
		HttpGet get=new HttpGet(url);
		try {
			CloseableHttpResponse response=client.execute(get,context);
			HttpEntity entity=response.getEntity();
			String resString=EntityUtils.toString(entity);
			responseStr = resString;
			System.out.println(resString);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseStr;
	}
	
	//post请求
	public static String sendPostRequest(String url,String sendStr,String contentType){
		String resStr=null;
		CloseableHttpClient client=HttpClientBuilder.create().build();
		HttpPost post=new HttpPost(url);
		StringEntity sendEntity=new StringEntity(sendStr,"utf-8");
		sendEntity.setContentType(contentType);
		post.setEntity(sendEntity);
		CloseableHttpResponse response=null;
		try {
			response=client.execute(post,context);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity resEntity=response.getEntity();
		if(resEntity!=null){
			try {
				resStr=EntityUtils.toString(resEntity,"utf-8");
				//System.out.println(resStr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else{
			System.out.println("返回值为空！");
		}
		return resStr;
	}
	
	//post请求  ****
	public static String sendPostRequest2(String url,String sendStr,String contentType,String cookies){
			String resStr=null;
			CloseableHttpClient client=HttpClientBuilder.create().build();
			HttpPost post=new HttpPost(url);
			post.setHeader("Cookie",cookies);	
			StringEntity sendEntity=new StringEntity(sendStr,"utf-8");
			sendEntity.setContentType(contentType);
			post.setEntity(sendEntity);
			CloseableHttpResponse response=null;
			try {
				response=client.execute(post);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpEntity resEntity=response.getEntity();
			if(resEntity!=null){
				try {
					resStr=EntityUtils.toString(resEntity,"utf-8");
					//System.out.println(resStr);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}else{
				System.out.println("返回值为空！");
			}
			return resStr;
		}
	
	//get请求请求带上送数据与cookie
		public static String sendGetRequestWithCookies(String url,String referer,String sendStr,String cookies) {
			String responseStr = null;
			CloseableHttpClient client=HttpClientBuilder.create().build();
			HttpGet get=new HttpGet(url);
			 //设置头部信息
		     get.setHeader("Referer",referer);
		     get.setHeader("Cookie",cookies);		
			try {
				CloseableHttpResponse response=client.execute(get,context);
				HttpEntity entity=response.getEntity();
				String resString=EntityUtils.toString(entity);
				responseStr = resString;
				System.out.println(resString);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return responseStr;
		}
	
	//post请求带上送数据与cookie
	public static String sendPostRequestWithCookies(String url,String referer,String sendStr,String contentType,String cookies) {
		String resStr=null;
		CloseableHttpClient client=HttpClientBuilder.create().build();
		HttpPost post=new HttpPost(url);
	     //设置头部信息
	    post.setHeader("Referer",referer);
	    post.setHeader("Content-Type", contentType);
	    post.setHeader("X-Requested-With","XMLHttpRequest");
	    post.setHeader("Cookie",cookies);	
		StringEntity sendEntity=new StringEntity(sendStr,"utf-8");
		sendEntity.setContentType(contentType);
		post.setEntity(sendEntity);
		CloseableHttpResponse response=null;
		try {
			//response=client.execute(post,context);
			response=client.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity resEntity=response.getEntity();
		if(resEntity!=null){
			try {
				resStr=EntityUtils.toString(resEntity,"utf-8");
				//System.out.println(resStr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else{
			System.out.println("返回值为空！");
		}
		try {
			response.close();
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return resStr;
	}
	
	
	public static void main(String[] args) {
		
	}
}
