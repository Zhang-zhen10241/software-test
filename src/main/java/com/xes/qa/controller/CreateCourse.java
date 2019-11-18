package com.xes.qa.controller;

import java.io.PrintWriter;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.xes.qa.model.OneCourse;
import com.xes.qa.service.CreateCourseNew;

@Controller
@RequestMapping("")
public class CreateCourse {
	
	@RequestMapping(value = { "/putong" })
	public String createPutong() {
		System.out.println("==createPutong===================");
		return "putong";
	}
	
	@ResponseBody
	@RequestMapping(value = "/CreateCourseAPI", method = RequestMethod.POST)
	public String createCourse(@RequestBody OneCourse course) throws Exception {
		CreateCourseNew ccn = new CreateCourseNew();
		String result=ccn.createNewCourse(course);
		return JSONObject.toJSONString(result);
	}

	@RequestMapping(value = "/CreateExistOutlineCourseAPI", method = RequestMethod.POST)//暂不使用
	public void createExistOutlineCourse(@RequestBody OneCourse course, PrintWriter printWriter) {
		CreateCourse createCourse = new CreateCourse();
		Map<String,String> param = new HashMap<String,String>();
		/*param.put("xmlFileName", course.getXmlFileName());
		*/
		try {
			createCourse.createExistOutlineCourseByJenkins(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Map.Entry<String, String> entry : param.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		printWriter.write("{\"isSuccess\": \"success\"}");
	}

	public void createByJenkins(Map param) throws Exception {
		JenkinsServer jenkins = new JenkinsServer(new URI("http://qa.xesv5.com/jenkins"), "zhangxiaolin1", "123456");
		JobWithDetails jobs = jenkins.getJob("CreateOneCourseByJenkins");
		System.out.println(jobs);
		System.out.println("create");
		jobs.build(param, false);
	}	
	public void createExistOutlineCourseByJenkins(Map param) throws Exception {
		JenkinsServer jenkins = new JenkinsServer(new URI("http://qa.xesv5.com/jenkins"), "zhangxiaolin1", "123456");
		JobWithDetails jobs = jenkins.getJob("CreateExistedOutlineCourseByJenkins");
		System.out.println(jobs);
		System.out.println("create");
		jobs.build(param, false);
	}

}
