package com.xes.qa.model;

public class OneCourse {
	private String cookie;
	private String reviewCookie;
	private String namePrefix;
	private String grade;
	private String subject;
	private String term;
	private String category;
	private String catalog_num;
	private String materialId;
	private String jiangyiId;
	private String teacherId;
	private String counselorTeacherId;
	private String cycleStr;
	private String pattern;
	private String classLimit;
	private String courseLimit;
	private String existedOutlineId;
	
	public OneCourse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OneCourse(String cookie, String reviewCookie, String namePrefix,  String grade, String subject, String term, String category, String catalog_num, String materialId, String jiangyiId,String teacherId,
			String counselorTeacherId,String cycleStr,String pattern,String classLimit, String courseLimit,String existOutLineId) {
		super();
		this.cookie = cookie;
		this.reviewCookie = reviewCookie;
		this.namePrefix = namePrefix;
		this.grade = grade;
		this.subject = subject;
		this.term = term;
		this.category = category;
		this.catalog_num = catalog_num;
		this.materialId = materialId;
		this.jiangyiId = jiangyiId;
		this.teacherId = teacherId;
		this.counselorTeacherId = counselorTeacherId;
		this.cycleStr = cycleStr;
		this.pattern = pattern;
		this.classLimit = classLimit;
		this.courseLimit = courseLimit;
		this.existedOutlineId = existedOutlineId;
	}
	
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getReviewCookie() {
		return reviewCookie;
	}
	public void setReviewCookie(String reviewCookie) {
		this.reviewCookie = reviewCookie;
	}
	public String getNamePrefix() {
		return namePrefix;
	}
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCatalog_num() {
		return catalog_num;
	}
	public void setCatalog_num(String catalog_num) {
		this.catalog_num = catalog_num;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public void setJiangyiId(String jiangyiId) {
		this.jiangyiId = jiangyiId;
	}
	public String getJiangyiId() {
		return jiangyiId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getCounselorTeacherId() {
		return counselorTeacherId;
	}
	public void setCounselorTeacherId(String counselorTeacherId) {
		this.counselorTeacherId = counselorTeacherId;
	}
	public String getCycleStr() {
		return cycleStr;
	}
	public void setCycleStr(String cycleStr) {
		this.cycleStr = cycleStr;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getClassLimit() {
		return classLimit;
	}
	public void setClassLimit(String classLimit) {
		this.classLimit = classLimit;
	}
	public String getCourseLimit() {
		return courseLimit;
	}
	public void setCourseLimit(String courseLimit) {
		this.courseLimit = courseLimit;
	}
	public String getExistedOutlineId() {
		return existedOutlineId;
	}
	public void setExistedOutlineId(String existedOutlineId) {
		this.existedOutlineId = existedOutlineId;
	}
	
	
	
}

