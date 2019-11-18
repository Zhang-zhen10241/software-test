/**  
 * @Title:CreateCourseNew.java   
 * @Package:service   
 * @Description:TODO 
 * @author:ZhangXiaolin     
 * @date:2019年9月28日 下午2:23:09   
 * @version:V1.0 
 * @Copyright:www.xueersi.com
 */

package com.xes.qa.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.SocksProxySocketFactory;
import com.xes.qa.model.OneCourse;
import com.xes.qa.utils.HttpUtil;
import com.xes.qa.utils.ToolUtil;

/**   
 * @ClassName:CreateCourseNew   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author:ZhangXiaolin
 * @date:2019年9月28日 下午2:23:09  
 * @Copyright:www.xueersi.com 
 */

public class CreateCourseNew {

	long start = System.currentTimeMillis();
	static Date date = new Date();
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMdd-HHmm");// 设置日期格式
	static String detailedTime = sdf.format(date);// new Date()为获取当前系统时间，也可使用当前时间戳
	//建课前就获取到的参数
	static int catalog_num = 0;//场次数
	static String namePrefix = "";//课程名称前缀，增加辨识度
	static String cycleStr = "";//循环周期，如7
	static String subject = "";//学科的key，如“数学”“语文”
	static String category = "";//课程品类的key，如“直播小班”
	static String materialId = "";//讲义ID
	static String courseFileId = "";//课件ID
	static String teacherId="";//主讲老师ID
	static int courseLimit = 10;
	static int classLimit = 2;
	static String counselorTeacherId = "";
	static String cookie = "";
	static String reviewCookie = "";
	static String existedOutlineId = "";
	static String grade = "";
	static String term = "";
	//建课中生成的参数
	static String outlineId = "";//大纲ID
	static String timeTemplateId = "";//时间模板ID
	static String packageId = "";//场次包ID
	static String courseId = "";//课程ID
	static List<String> outlineCatalogIdList = new ArrayList<>();//课程大纲里各ID
	static String sessionPackageId="";//场次包ID
	static String counselorCourseId = "";//辅导班ID
	
	static int courseDuration = 0;
	public static boolean createCourseResult = true;
	public static boolean loginResultPro = true;
	public static String resMessage="";//返回信息
	public static String errMessage = "";
	
	public static void getParameter(OneCourse course){
		long start = System.currentTimeMillis();
		date = new Date();
		sdf = new SimpleDateFormat("yyyy-MMdd-HHmm");// 设置日期格式
		detailedTime = sdf.format(date);// new Date()为获取当前系统时间，也可使用当前时间戳
		
		catalog_num=Integer.parseInt(course.getCatalog_num());
		namePrefix = course.getNamePrefix();
		cycleStr = course.getCycleStr();
		subject= course.getSubject();
		category = course.getCategory();
		teacherId = course.getTeacherId();
		materialId = course.getMaterialId();
		existedOutlineId = course.getExistedOutlineId();
		courseLimit = Integer.parseInt(course.getCourseLimit());
		classLimit = Integer.parseInt(course.getClassLimit());
		counselorTeacherId = course.getCounselorTeacherId();
		cookie = course.getCookie().trim();
		reviewCookie = course.getReviewCookie().trim();
		grade = course.getGrade().trim();
		term=course.getTerm().trim();		
		courseDuration = Integer.parseInt(cycleStr)*catalog_num;		
	}
//		System.out.println("catalog_num:"+catalog_num);
//		System.out.println("subject:"+subject);
//		System.out.println("namePrefix:"+namePrefix);
//		System.out.println("cycleStr:"+cycleStr);
//		System.out.println("category:"+category);
//		System.out.println("teacherId:"+teacherId);
//		System.out.println("materialId:"+materialId);
//		System.out.println("existedOutlineId:"+existedOutlineId);
//		System.out.println("courseLimit:"+courseLimit);
//		System.out.println("classLimit:"+classLimit);
//		System.out.println("counselorTeacherId:"+counselorTeacherId);
//		System.out.println("grade:"+grade);
//		System.out.println("reviewCookie:"+reviewCookie);
//		System.out.println("cookie:"+cookie);
//		System.out.println("term:"+term);

	/**
	 * 
	 * 
	 * @Title:createNewCourse   
	 * @Description:完成一次完整的建课流程 
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static String createNewCourse(OneCourse course){		
		getParameter(course);
		String result= "";
		if(existedOutlineId.equals("")){
			//添加大纲操作
			if(!addOutline(course)){				
				result ="添加大纲失败"+ errMessage;
				return result;
			}				
			//添加大纲目录操作
			if(!addCatalogue(course)){				
				result="添加大纲目录失败" + errMessage;
				return result;
			}	
			//发布大纲操作
			if(!publishOutline(course)){				
				result= "发布大纲失败" + errMessage;
				return result;
			}	
			//获得大纲目录ID
			getOutlineCatalogIdsByHtml();
			//添加讲义
			if(!materialId.equals("")){//如果未填写讲义，则不进行添加讲义步骤
				if(!addMaterial()){				
					result=  "添加讲义失败" + errMessage;
					return result;
				}	
			}			
			//创建时间模板
			if(!addTimeTemplate(course)){				
				result= "创建时间模板失败" + errMessage;
				return result;
			}	
			//创建场次包
			if(!addSessionPackage()){				
				result= "创建场次包失败" + errMessage;
				return result;
			}	
			//创建课程
			if(!addCourse()){				
				result= "创建课程失败" + errMessage;
				return result;
			}	
			//提交上架申请
			if(!submitRequestApplication()){				
				result=  "提交上架申请失败" + errMessage;
				return result;
			}	
			//审核课程，注意要用有权限的账号审核
			if(!reviewCourse()){				
				result=  "审核课程失败" + errMessage;
				return result;
			}	
			//添加销售期
			if(!addSalesPeriod()){				
				result=  "添加销售期失败" + errMessage;
				return result;
			}	
			//添加限额设置	
			if(!addLimit()){				
				result=  "添加限额设置失败" + errMessage;
				return result;
			}	
			//将课程展示信息设置为不展示
			if(!editListNotShow()){				
				result=  "将课程展示信息设置为不展示失败**" + errMessage;
				return result;
			}	
			//创建辅导班
			if(!addCounselorCourse()){				
				result = "创建辅导班失败" +  errMessage;
				return result;
			}	
			result=result();
		}else{
			//获得大纲目录ID
			getOutlineCatalogIdsByHtml();
		/*	if(!createCourseResult){				
				result= "获取大纲目录ID失败" + errMessage;
				return result;
			}	*/
			//创建时间模板
			if(!addTimeTemplate(course)){				
				result="创建时间模板失败" +  errMessage;
				return result;
			}	
			//创建场次包
			if(!addSessionPackage()){				
				result=  "创建场次包失败" + errMessage;
				return result;
			}	
			//创建课程
			if(!addCourse()){				
				result= "创建课程失败" + errMessage;
				return result;
			}	
			//提交上架申请
			if(!submitRequestApplication()){				
				result= "提交上架申请失败" + errMessage;
				return result;
			}	
			//审核课程，注意要用有权限的账号审核
			if(!reviewCourse()){				
				result= "审核课程失败" + errMessage;
				return result;
			}	
			//添加销售期
			if(!addSalesPeriod()){				
				result="添加销售期失败" +  errMessage;
				return result;
			}	
			//添加限额设置
			if(!addLimit()){				
				result= "添加限额设置失败" + errMessage;
				return result;
			}	
			//将课程展示信息设置为不展示
			if(!editListNotShow()){				
				result= "将课程展示信息设置为不展示失败" + errMessage;
				return result;
			}	
			//创建辅导班
			if(!addCounselorCourse()){				
				result= "创建辅导班失败" + errMessage;
				return result;
			}	
			result=result();			
		}		
		return result;
	}
	/**
	 * 
	 * @Title:addOutline
	 *  @Description:添加一个新的大纲
	 *  @param:@param
	 *  @param:@return 
	 *  @return:String 
	 *  @throws
	 */
	public static boolean addOutline(OneCourse course) {
		System.out.println("******开始新建大纲******");
		String responseStr = null;
		String url = "http://admin.xesv5.com/Outline/saveOutline";
		//String referer = "http://admin.xesv5.com/Outline/addOutline";
		String contentType = "application/x-www-form-urlencoded; charset=UTF-8";
		String gradeIDS = "";
		String sendStr = "";
		/*for (String grade : course.getGrade()) {
			gradeIDS += "&grade_ids[]=" + grade;
		}*/
		gradeIDS += "&grade_ids[]=" + grade;
		String termIDS = "";
		/*for (String term : course.getTerm()) {
			termIDS += "&term_ids[]=" + term;
		}	*/	
		termIDS += "&term_ids[]=" + term;
		String outlineName = namePrefix + "-测试-大纲";		
		if(subject.equals("26")){//适配编程课，编程课的value为26
			sendStr = "name=" + outlineName + "&category=" + category  + "&subject_ids=" + subject +gradeIDS+"&learningstage_ids=90"+ termIDS
					+ "&language_type=1&year_id=22&textbook_id=&version_id=&difficulty_id=&area_id=1&price=1&catalog_num="
					+ course.getCatalog_num() + "&outline_type=1";
		}else{
			sendStr = "name=" + outlineName + "&category=" + category  + "&subject_ids=" + subject +gradeIDS+ termIDS
					+ "&language_type=1&year_id=22&textbook_id=&version_id=&difficulty_id=&area_id=1&price=1&catalog_num="
					+ course.getCatalog_num() + "&outline_type=1";
		}
		//name=test001&category=8&subject_ids=26&grade_ids%5B%5D=2&learningstage_ids=90&term_ids%5B%5D=1&language_type=1&year_id=22&
		//textbook_id=&version_id=&difficulty_id=&price=1&catalog_num=&outline_type=1
		System.out.println("上送数据信息为：" + sendStr);
		//responseStr = HttpUtil.sendPostRequestWithCookies(url, referer, sendStr, contentType, cookie);
		responseStr = HttpUtil.sendPostRequest2(url, sendStr, contentType, cookie);
		String createOutlineResult = "创建大纲失败，请检查各参数";
		if (responseStr.contains("\"stat\":1")) {
			createOutlineResult = "创建大纲成功";
			outlineId = ToolUtil.getStringNum(ToolUtil.ascii2Native(responseStr)).substring(1);
			resMessage="返回数据信息为：" + ToolUtil.ascii2Native(responseStr) + "\n" +"******"+ createOutlineResult+"，大纲ID为："+outlineId+"******";
			System.out.println(resMessage);
			return true;
		}else{
			createCourseResult=false;
			errMessage=ToolUtil.ascii2Native(responseStr);
			System.out.println(errMessage);
			return false;
		}
	
		
	}
	
	/**
	 * 
	 * @Title:addCatalogue   
	 * @Description:添加大纲目录   
	 * @param:@param course
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static boolean addCatalogue(OneCourse course) {
		System.out.println("******开始添加大纲目录******");
		String responseStr = null;
		String url = "http://admin.xesv5.com/Outline/addCatalog";
		String contentType = "application/x-www-form-urlencoded";
		String referer = "http://admin.xesv5.com/Outline/outlineCatalog?id="+outlineId;
		String id = "\"id\":\""+outlineId+"\",";
		String catalog_ids = "\"catalog_ids\":[";
		String catalog_name = "\"catalog_name\":[";
		String teacher_class_durations = "\"teacher_class_durations\":[";
		String counselor_class_durations ="\"counselor_class_durations\":[";
		String study_report_id="\"study_report_id\":[";
		String pattern = "\"pattern\":[";
		for(int i=0;i<catalog_num;i++){
			if(i==catalog_num-1){
				catalog_ids+="\"0\"],";
				catalog_name+="\"测试场次"+(i+1)+"\"],";
				teacher_class_durations+="\"120\"],";
				counselor_class_durations+="\"20\"],";
				study_report_id+="\"1\"],";
				if(i==0){
					pattern+="\"1\"]";
				}else{
					pattern+="\"1\"],";
				}			
			}else{
				catalog_ids+="\"0\",";
				catalog_name+="\"测试场次"+(i+1)+"\",";
				teacher_class_durations+="\"120\",";
				counselor_class_durations+="\"20\",";
				study_report_id+="\"1\",";
				pattern+="\"1\",";
			}			
		}
		String mode_ids = "";
		for(int i=1;i<catalog_num;i++){
			if(i==catalog_num-1){
				mode_ids+="\"mode_ids["+i+"]\":\"1\"";
			}else{
				mode_ids+="\"mode_ids["+i+"]\":\"1\",";
			}			
		}
		String sendStr ="data={"+ id+"\"tag_id\":[\"0\"],\"tag_name\":[\"0\"],"+catalog_ids+catalog_name+"\"mode_ids[0]\":\"1\","+teacher_class_durations+counselor_class_durations+study_report_id+pattern+mode_ids+"}";
		System.out.println("上送数据信息为：" + sendStr);
		responseStr = HttpUtil.sendPostRequestWithCookies(url, referer, sendStr, contentType, cookie);
		String addCatalogueResult="添加大纲目录失败，请检查参数或查看该大纲是否已发布";
		if (responseStr.contains("\"stat\":1")) {
			addCatalogueResult = "添加大纲目录成功";
			resMessage="返回数据信息为：" + ToolUtil.ascii2Native(responseStr) + "\n" +"******"+ addCatalogueResult+"******";
			System.out.println(resMessage);
			return true;
		}else{
			createCourseResult=false;
			errMessage=ToolUtil.ascii2Native(responseStr);
			System.out.println(errMessage);
			return false;
		}
	}

	/**
	 * 
	 * @Title:publishOutline   
	 * @Description:发布大纲  
	 * @param:@param course
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static boolean publishOutline(OneCourse course) {
		System.out.println("******开始发布大纲******");
		String responseStr = null;
		String url = "http://admin.xesv5.com/Outline/publishOutlines";
		String contentType = "application/x-www-form-urlencoded";
		String referer = "http://admin.xesv5.com/Outline/getOutlineList";
		String sendStr = "ids="+outlineId;
		System.out.println("上送数据信息为：" + sendStr);
		responseStr = HttpUtil.sendPostRequestWithCookies(url,referer,sendStr,contentType,cookie);
		String publishOutlineResult="发布大纲失败，请检查该大纲是否有大纲目录或该大纲是否已发布";
		if (responseStr.contains("\"stat\":1")) {
			publishOutlineResult = "发布大纲成功";
			resMessage = "返回数据信息为：" + ToolUtil.ascii2Native(responseStr) + "\n" +"******"+ publishOutlineResult+"******";
			System.out.println(resMessage);
			return true;
		}else{
			createCourseResult=false;
			errMessage=ToolUtil.ascii2Native(responseStr);
			System.out.println(errMessage);
			return false;
		}		
	}
	
	/**
	 * 
	 * @Title:getOutlineCatalogIds   弃用
	 * @Description:通过接口抓取大纲的所有IDS，并传入到list中，注：集成环境和线上环境不支持
	 * @param:@return      
	 * @return:List<String>      
	 * @throws
	 */
	/*public static List<String> getOutlineCatalogIdsByAPI(){
		System.out.println("******获得大纲目录ID******");
		String responseStr = null;
		String url = "http://courseapi.xesv5.com/Outline/Outline/getOutlineCatalogInfo?outlineIds="+outlineId;
		String contentType = "application/x-www-form-urlencoded";
		String sendStr = "";
		responseStr = HttpUtil.sendPostRequest(url, sendStr, contentType);
		System.out.println(responseStr);
		outlineCatalogIdList = ToolUtil.analysisOutlineIdsByRegular(responseStr);
		System.out.println("大纲目录ID的list为："+outlineCatalogIdList);
		return ToolUtil.analysisOutlineIdsByRegular(responseStr);
	}*/
	
	/**
	 * 
	 * @Title:getOutlineCatalogIdsByHtml   ***
	 * @Description:通过html匹配出大纲的所有IDS，并传入到list中********************************
	 * @param:@return      
	 * @return:List<String>      
	 * @throws
	 */	
	public static List<String> getOutlineCatalogIdsByHtml(){
		System.out.println("******获得大纲目录ID******");
		String responseStr = null;
		String id = "";
		if(existedOutlineId.equals("")){
			id = outlineId;
		}else{
			id = existedOutlineId;
		}
		String url = "http://admin.xesv5.com/Outline/outlineCatalog?id="+id;
		String contentType = "application/x-www-form-urlencoded";
		String sendStr = "";
		responseStr = HttpUtil.sendPostRequest2(url, sendStr, contentType,cookie);
		outlineCatalogIdList = ToolUtil.analysisOutlineIdsByRegularFromHTML(responseStr);
		System.out.println("大纲目录ID的list为："+outlineCatalogIdList);
		return ToolUtil.analysisOutlineIdsByRegular(responseStr);
	}
	
	/**
	 * 
	 * @Title:AddMaterial   
	 * @Description:添加大纲素材   
	 * @param:@param course
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static boolean addMaterial() {
		System.out.println("******开始添加大纲素材******");
		String responseStr = null;
		String url = "http://admin.xesv5.com/Outline/saveMaterial";
		String contentType = "application/x-www-form-urlencoded";		
		String referer = "Referer: http://admin.xesv5.com/Outline/outlineMaterial?id="+outlineId;	
		String sendStr = "";
		if((!materialId.equals(""))&&courseFileId.equals("")&&outlineCatalogIdList.size()!=0){
			String pre ="data={";
			String id = "\"outline_id\":\""+outlineId+"\",";
			String append_material_ids= "\"append_material_ids\":\"\",";
			String catalog_ids = "\"catalog_ids\":[\"";
			for(int i=1;i<=catalog_num;i++){
				if(i==catalog_num){
					catalog_ids+=outlineCatalogIdList.get(i-1)+"\"],";
				}else{
					catalog_ids+=outlineCatalogIdList.get(i-1)+"\",\"";
				}
			}
			String type_1 = "\"type_1_"+outlineCatalogIdList.get(0)+"\":\""+materialId+"\",";
			String preview_url = "\"preview_url\":[\"";
			for(int i=1;i<=catalog_num;i++){
				if(i==catalog_num){
					preview_url+="http://jiaoyan.xesv5.com/?noauth=true#/preview/\",\"http://jiaoyan.xesv5.com/?noauth=true#/taskShow/\",\"\"],";
				}else{
					preview_url+="http://jiaoyan.xesv5.com/?noauth=true#/preview/\",\"http://jiaoyan.xesv5.com/?noauth=true#/taskShow/\",\"\",\"";
				}
			}
			String type_2 = "\"type_2_"+outlineCatalogIdList.get(0)+"\":\"\",";
			String type_53 = "\"type_53_"+outlineCatalogIdList.get(0)+"\":\"\"";
			String end =type_2+type_53;
			if(catalog_num==1){
				end+="}";
			}else{
				for(int i=2;i<=catalog_num;i++){
					if(i==2){
						end+=",";
					}
					if(i==catalog_num){
						end+="\"type_1_"+outlineCatalogIdList.get(i-1)+"\":\""+materialId+"\","+"\"type_2_"+outlineCatalogIdList.get(i-1)+"\":\"\","+"\"type_53_"+outlineCatalogIdList.get(i-1)+"\":\"\""+"}";
					}else{
						end+="\"type_1_"+outlineCatalogIdList.get(i-1)+"\":\""+materialId+"\","+"\"type_2_"+outlineCatalogIdList.get(i-1)+"\":\"\","+"\"type_53_"+outlineCatalogIdList.get(i-1)+"\":\"\""+",";
					}
				}
			}
			sendStr=pre+id+append_material_ids+catalog_ids+type_1+preview_url+end;
		}	
		System.out.println("上送数据信息为：" + sendStr);
		responseStr = HttpUtil.sendPostRequestWithCookies(url,referer,sendStr,contentType,cookie);
		String AddMaterialResult="添加大纲素材失败";
		if (responseStr.contains("\"stat\":1")) {
			AddMaterialResult = "添加大纲素材成功";
			resMessage = "返回数据信息为：" + ToolUtil.ascii2Native(responseStr) + "\n" +"******"+ AddMaterialResult+"******";
			System.out.println(resMessage);
			return true;
		}else{
			createCourseResult=false;
			errMessage=ToolUtil.ascii2Native(responseStr);
			System.out.println(errMessage);
			return false;
		}
		
	}

	/**
	 * 
	 * @Title:addTimeTemplate   
	 * @Description:创建时间模板   
	 * @param:@param course
	 * @param:@param course2
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static boolean addTimeTemplate(OneCourse course){
		System.out.println("******开始创建时间模板******");
		String responseStr = null;
		String url = "http://admin.xesv5.com/TimeTemplate/saveAddTimeTemplates";
		String contentType = "application/x-www-form-urlencoded";
		String name = "name="+namePrefix+"-测试-时间模板";
		String subjects = "&subject_ids[]=" + subject;
		String termIDS = "";		
		termIDS += "&term_ids[]=" + term;
		String time_slot = "&time_slot[]=1&time_slot[]=2&time_slot[]=3&";
		String school_schedule="school_schedule=1&";
		String plan_num = "plan_num="+catalog_num+"&";
		String cycle = "cycle="+cycleStr;
		String time = "&start_hour=19&start_minute=00&end_hour=21&end_minute=00&";
		String end = "planInfos=[";
		for(int i=1;i<=catalog_num;i++){
			int j=Integer.parseInt(cycleStr);
			j=j*(i-1);
			if(i==catalog_num){
				end+="{\"day\":\""+ToolUtil.getDateFuture(j)+"\",\"hourStart\":\"19\",\"muniteStart\":\"00\",\"hourEnd\":\"21\",\"mumiteEnd\":\"00\"}]";
			}else{
				end+="{\"day\":\""+ToolUtil.getDateFuture(j)+"\",\"hourStart\":\"19\",\"muniteStart\":\"00\",\"hourEnd\":\"21\",\"mumiteEnd\":\"00\"},";
			}
		}	
		String sendStr = name+subjects+termIDS+time_slot+school_schedule+plan_num+cycle+time+end;
		System.out.println("上送数据信息为：" + sendStr);
		responseStr = HttpUtil.sendPostRequest2(url,sendStr,contentType,cookie);
		String addTimeTemplateResult="添加时间模板失败";
		if (responseStr.contains("\"stat\":1")) {
			addTimeTemplateResult = "添加时间模板成功";
			timeTemplateId = ToolUtil.getStringNum(ToolUtil.ascii2Native(responseStr)).substring(1);
			resMessage = "返回数据信息为：" + ToolUtil.ascii2Native(responseStr) + "\n" +"******"+ addTimeTemplateResult+"，时间模板ID为："+timeTemplateId+"******";
			System.out.println(resMessage);
			return true;
		}else{
			createCourseResult=false;
			errMessage=ToolUtil.ascii2Native(responseStr);
			System.out.println(errMessage);
			return false;
		}
	}
	
	/**
	 * 
	 * @Title:addSessionPackage   
	 * @Description:创建场次包 ，注意 创建场次包之前必须要登录admin后台
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static boolean addSessionPackage(){		
		System.out.println("******开始创建场次包******");
		String responseStr = null;
		String url = "http://admin.xesv5.com/LivePlan/saveAddPackagePlans";
		String contentType = "application/x-www-form-urlencoded";
		String name = "name="+namePrefix+"-测试-场次包";
		String id = "";
		if(existedOutlineId.equals("")){
			id = "&outline_id=" + outlineId;
		}else{
		    outlineId=existedOutlineId;
			id = "&outline_id=" + outlineId;
		}		
		String select_teacher_id = "&select_teacher_id="+teacherId;
		String select_teacher_name="";
		String select_time_template_id = "&select_time_template_id="+timeTemplateId;
		String planInfos = "";
		for(int i=1;i<=catalog_num;i++){
			int j=Integer.parseInt(cycleStr);
			j=j*(i-1);
			if(i==catalog_num){
				planInfos+="{\"catalogId\":\""+outlineCatalogIdList.get(i-1)+"\",\"mode\":\"1\",\"pattern\":\"1\",\"planName\":\"测试场次"+i+"\",\"teacherId\":\""+teacherId+"\",\"day\":\""+ToolUtil.getDateFuture(j)+"\",\"hourStart\":\"19\",\"muniteStart\":\"00\",\"hourEnd\":\"21\",\"mumiteEnd\":\"00\",\"videoResources\":\"\"}]";
			}else{
				planInfos+="{\"catalogId\":\""+outlineCatalogIdList.get(i-1)+"\",\"mode\":\"1\",\"pattern\":\"1\",\"planName\":\"测试场次"+i+"\",\"teacherId\":\""+teacherId+"\",\"day\":\""+ToolUtil.getDateFuture(j)+"\",\"hourStart\":\"19\",\"muniteStart\":\"00\",\"hourEnd\":\"21\",\"mumiteEnd\":\"00\",\"videoResources\":\"\"},";
			}
		}
		String sendStr = name+id+select_teacher_id+select_teacher_name+select_time_template_id+"&teacher_salary=0&term_id=0&planInfos=["+planInfos;
		System.out.println("上送数据信息为："+sendStr);
		responseStr = HttpUtil.sendPostRequest2(url,sendStr,contentType,cookie);
		String addSessionPackageResult="添加场次包失败";
		if (responseStr.contains("\"stat\":1")) {
			addSessionPackageResult = "添加场次包成功";
			sessionPackageId = ToolUtil.getStringNum(ToolUtil.ascii2Native(responseStr)).substring(1);
			resMessage = "返回数据信息为：" + ToolUtil.ascii2Native(responseStr) + "\n" +"******"+ addSessionPackageResult+"，场次包ID为："+sessionPackageId+"******";
			return true;
		}else{
			createCourseResult=false;
			errMessage=ToolUtil.ascii2Native(responseStr);
			return false;
		}
	}
	
	/**
	 * 
	 * @Title:addCourse   
	 * @Description:创建课程，注意创建课程前登陆   
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static boolean addCourse(){
		System.out.println("******开始创建课程******");
		String responseStr = null;
		String url = "http://admin.xesv5.com/Course/addCourse";
		String contentType = "application/x-www-form-urlencoded";
		String sendStr = "name="+namePrefix+"-测试-课程-"+detailedTime+"&category="+category+"&plan_package_ids="+sessionPackageId;
		System.out.println("上送数据信息为："+sendStr);
		responseStr = HttpUtil.sendPostRequest2(url,sendStr,contentType,cookie);
		String addCourseResult="添加课程失败";
		if (responseStr.contains("\"stat\":1")) {
			addCourseResult = "添加课程成功";
			courseId = ToolUtil.getStringNum(ToolUtil.ascii2Native(responseStr)).substring(1);
			resMessage = "返回数据信息为：" + ToolUtil.ascii2Native(responseStr) + "\n" +"******"+ addCourseResult+"，课程ID为："+courseId+"******";
			System.out.println(resMessage);
			return true;
		}else{
			createCourseResult=false;
			errMessage=ToolUtil.ascii2Native(responseStr);
			System.out.println(errMessage);
			return false;
		}
	}
	
	/**
	 * 
	 * @Title:submitRequestApplication   
	 * @Description:提交上架审核，注意登陆   
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static boolean submitRequestApplication(){
		System.out.println("******提交课程上架申请******");
		String responseStr = null;
		String url = "http://admin.xesv5.com/Course/verify?course_ids="+courseId;
		String contentType = "application/x-www-form-urlencoded";
		String sendStr = "";
		responseStr = HttpUtil.sendPostRequest2(url,sendStr,contentType,cookie);
		String submitRequestApplicationResult="提交上架申请失败";
		if (responseStr.contains("\"stat\":1")) {
			submitRequestApplicationResult = "提交上架申请成功";
			resMessage = "返回数据信息为：" +ToolUtil.ascii2Native(responseStr)+ "\n" +"******"+ submitRequestApplicationResult+"******";
			System.out.println(resMessage);
			return true;
		}else{
			createCourseResult=false;
			errMessage=ToolUtil.ascii2Native(responseStr);
			System.out.println(errMessage);
			return false;
		}
	}
	
	/**
	 * 
	 * @Title:reviewCourse   
	 * @Description:审核课程，注意要有审核权限的账号进行审核
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static boolean reviewCourse(){
		System.out.println("******审核课程******");
		String responseStr = null;
		String url = "http://admin.xesv5.com/Course/verifyBatchShelf";
		String contentType = "application/x-www-form-urlencoded";
		String sendStr = "verify=1&note=pass&course_ids="+courseId;
		System.out.println("上送数据信息为："+sendStr);
		responseStr = HttpUtil.sendPostRequest2(url,sendStr,contentType,reviewCookie);
		String reviewCourseResult="审核课程失败";
		if (responseStr.contains("\"stat\":1")) {
			reviewCourseResult = "审核课程成功";
			resMessage = "返回数据信息为：" +ToolUtil.ascii2Native(responseStr)+ "\n" +"******"+ reviewCourseResult+"******";
			System.out.println(resMessage);
			return true;
		}else{
			createCourseResult=false;
			errMessage=ToolUtil.ascii2Native(responseStr);
			System.out.println(errMessage);
			return false;
		}
	}
	
	/**
	 * 
	 * @Title:addSalesPeriod   
	 * @Description:添加课程销售期   
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static boolean addSalesPeriod(){
		System.out.println("******添加课程销售期******");	  
		String responseStr = null;
		String url = "http://admin.xesv5.com/Course/addSale";
		String contentType = "application/x-www-form-urlencoded";
		//String sendStr = "course_name=【测试】测试创建课程-测试-课程-语文-直播小班&second_title=&sale_type=1&public_date=2019-04-13&sale_time=2019-04-13&sale_time_hours=00&sale_time_minute=00&sale_time_second=00&halt_sale_time=2019-04-14&halt_sale_time_hours=23&halt_sale_time_minute=59&halt_sale_time_second=59&resale=1&price_type=1&charge_mode=2&validday=0&start_date=2019-04-13&end_date=2020-04-13&course_id=51022&price=1&is_nosale=0'";
		String sendStr = "course_name=【测试】"+namePrefix+"-课程-"+detailedTime+"&second_title=&sale_type=1&public_date="+ToolUtil.getDateToday()+"&sale_time="+ToolUtil.getDateToday()+"&sale_time_hours=00&sale_time_minute=00&sale_time_second=00&halt_sale_time="+ToolUtil.getDate()+"&halt_sale_time_hours=23&halt_sale_time_minute=59&halt_sale_time_second=59&resale=1&price_type=1&charge_mode=2&validday=0&start_date="+ToolUtil.getDateToday()+"&end_date="+ToolUtil.getDateFuture(365)+"&course_id="+courseId+"&price=1&is_nosale=0";
		System.out.println("上送数据信息为："+sendStr);
		responseStr = HttpUtil.sendPostRequest2(url,sendStr,contentType,cookie);
		String addSalesPeriodResult="添加课程销售期失败";
		if (responseStr.contains("\"stat\":1")) {
			addSalesPeriodResult = "添加课程销售期成功";
			resMessage = "返回数据信息为：" +ToolUtil.ascii2Native(responseStr)+ "\n" +"******"+ addSalesPeriodResult+"******";
			System.out.println(resMessage);
			return true;
		}else{
			createCourseResult=false;
			errMessage=ToolUtil.ascii2Native(responseStr);
			System.out.println(errMessage);
			return false;
		}
	}
	
	/**
	 * 
	 * @Title:addLimit   
	 * @Description:添加限额设置 
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static boolean addLimit(){
		System.out.println("******添加限额******");	
		String responseStr = null;
		String url = "http://admin.xesv5.com/Course/saveCourseExtraInfo";
		String contentType = "application/x-www-form-urlencoded";
		//courseQuota=100&courseClassLimit=2&classType=1&id=51023
		String sendStr = "courseQuota="+courseLimit+"&courseClassLimit="+classLimit+"&classType=1&id="+courseId;
		System.out.println("上送数据信息为："+sendStr);
		responseStr = HttpUtil.sendPostRequest2(url,sendStr,contentType,cookie);
		String addLimitResult="添加限额失败";
		if (responseStr.contains("\"stat\":1")) {
			addLimitResult = "添加限额成功";
			resMessage = "返回数据信息为：" +ToolUtil.ascii2Native(responseStr)+ "\n" +"******"+ addLimitResult+"******";
			System.out.println(resMessage);
			return true;
		}else{
			createCourseResult=false;
			errMessage=ToolUtil.ascii2Native(responseStr);
			System.out.println(errMessage);
			return false;
		}
	}
	
	/**
	 * 
	 * @Title:editListNotShow   
	 * @Description:将课程展示信息设置为不展示
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static boolean editListNotShow(){
		System.out.println("******设置为列表不展示******");	
		String responseStr = null;
		String url = "http://admin.xesv5.com/Course/editCourseShow";
		String contentType = "application/x-www-form-urlencoded";
		String sendStr = "school_schedule[1][]=1&schooltime_name[1][]=上课时间&is_recommend=2&is_nosale=0&is_refund=0&keywords=&img_ids=&audio_visual=&video_ids=&live_playback_type=1&live_playback=0&course_id="+courseId+"&category="+category;
		System.out.println("上送数据信息为："+sendStr);
		responseStr = HttpUtil.sendPostRequest2(url,sendStr,contentType,cookie);
		String addListNotShowResult="设置为列表不展示失败";
		if (responseStr.contains("\"stat\":1")) {
			addListNotShowResult = "设置为列表不展示成功";
			resMessage = "返回数据信息为：" +ToolUtil.ascii2Native(responseStr)+ "\n" +"******"+ addListNotShowResult+"******";
			System.out.println(resMessage);
			return true;
		}else{
			createCourseResult=false;
			errMessage=ToolUtil.ascii2Native(responseStr);
			System.out.println(errMessage);
			return false;
		}
		
	}
	
	/**
	 * 
	 * @Title:addCounselorCourse   
	 * @Description:创建辅导班   
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static boolean addCounselorCourse(){
		System.out.println("******开始创建辅导班******");
		String responseStr = null;
		String url="http://admin.xesv5.com/LiveClass/save";
		String contentType = "application/x-www-form-urlencoded";
		String sendStr="className="+namePrefix+"-测试-辅导班-"+"&counselorId="+counselorTeacherId+"&classQuota="+classLimit+"&recommend=0&autoBuildroom=1&classStuType=1&id="+courseId;
		System.out.println("上送数据信息为："+sendStr);
		responseStr = HttpUtil.sendPostRequest2(url, sendStr, contentType,cookie);
		String addCounselorCourseResult = "创建辅导班失败";	
		if(responseStr.contains("\"stat\":1")){
			addCounselorCourseResult="创建辅导班成功";
			counselorCourseId = ToolUtil.getStringNum(ToolUtil.ascii2Native(responseStr)).substring(1);
			resMessage = "返回数据信息为：" +ToolUtil.ascii2Native(responseStr)+ "\n" +"******"+ addCounselorCourseResult+"，辅导班ID为："+counselorCourseId+"******";
			System.out.println(resMessage);
			return true;
		}else{
			createCourseResult=false;
			errMessage=ToolUtil.ascii2Native(responseStr);
			System.out.println(errMessage);
			return false;
		}
	}
	
	/**
	 * 
	 * @Title:result   
	 * @Description:建课流程的结果
	 * @param:@return      
	 * @return:String      
	 * @throws
	 */
	public static String result(){
			return "建课成功，大纲ID："+outlineId+"，场次包ID："+sessionPackageId+"，课程ID："+courseId+"，辅导班ID："+counselorCourseId+"，买课链接为https://www.xueersi.com/course-detail/"+courseId+"/"+counselorCourseId;		
	}
	
	
	public static void main(String[] args) {
		
	}
}
