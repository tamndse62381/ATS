package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cv database table.
 * 
 */
@Entity
@NamedQuery(name="Cv.findAll", query="SELECT c FROM Cv c")
public class Cv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Lob
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dob;

	private String email;

	private String firstName;

	private String gender;

	@Lob
	private String img;

	private int isActive;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModify;

	private String lastName;

	private double salaryFrom;

	private double salaryTo;

	private String status;

	private String title;

	private int yearExperience;

	//bi-directional many-to-one association to Apply
	@OneToMany(mappedBy="cv")
	private List<Apply> applies;

	//bi-directional many-to-one association to Certification
	@OneToMany(mappedBy="cv")
	private List<Certification> certifications;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="CityID")
	private City city;

	//bi-directional many-to-one association to Industry
	@ManyToOne
	@JoinColumn(name="IndustryID")
	private Industry industry;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="AccountID")
	private Account account;

	//bi-directional many-to-one association to Education
	@OneToMany(mappedBy="cv")
	private List<Education> educations;

	//bi-directional many-to-one association to Employerlikecv
	@OneToMany(mappedBy="cv")
	private List<Employerlikecv> employerlikecvs;

	//bi-directional many-to-one association to Logaccount
	@OneToMany(mappedBy="cv")
	private List<Logaccount> logaccounts;

	//bi-directional many-to-one association to Projectorproductworked
	@OneToMany(mappedBy="cv")
	private List<Projectorproductworked> projectorproductworkeds;

	//bi-directional many-to-one association to Skillincv
	@OneToMany(mappedBy="cv")
	private List<Skillincv> skillincvs;

	//bi-directional many-to-one association to Socialactivity
	@OneToMany(mappedBy="cv")
	private List<Socialactivity> socialactivities;

	//bi-directional many-to-one association to Workexperience
	@OneToMany(mappedBy="cv")
	private List<Workexperience> workexperiences;

	public Cv() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getIsActive() {
		return this.isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Date getLastModify() {
		return this.lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalaryFrom() {
		return this.salaryFrom;
	}

	public void setSalaryFrom(double salaryFrom) {
		this.salaryFrom = salaryFrom;
	}

	public double getSalaryTo() {
		return this.salaryTo;
	}

	public void setSalaryTo(double salaryTo) {
		this.salaryTo = salaryTo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYearExperience() {
		return this.yearExperience;
	}

	public void setYearExperience(int yearExperience) {
		this.yearExperience = yearExperience;
	}

	public List<Apply> getApplies() {
		return this.applies;
	}

	public void setApplies(List<Apply> applies) {
		this.applies = applies;
	}

	public Apply addApply(Apply apply) {
		getApplies().add(apply);
		apply.setCv(this);

		return apply;
	}

	public Apply removeApply(Apply apply) {
		getApplies().remove(apply);
		apply.setCv(null);

		return apply;
	}

	public List<Certification> getCertifications() {
		return this.certifications;
	}

	public void setCertifications(List<Certification> certifications) {
		this.certifications = certifications;
	}

	public Certification addCertification(Certification certification) {
		getCertifications().add(certification);
		certification.setCv(this);

		return certification;
	}

	public Certification removeCertification(Certification certification) {
		getCertifications().remove(certification);
		certification.setCv(null);

		return certification;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Industry getIndustry() {
		return this.industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Education> getEducations() {
		return this.educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public Education addEducation(Education education) {
		getEducations().add(education);
		education.setCv(this);

		return education;
	}

	public Education removeEducation(Education education) {
		getEducations().remove(education);
		education.setCv(null);

		return education;
	}

	public List<Employerlikecv> getEmployerlikecvs() {
		return this.employerlikecvs;
	}

	public void setEmployerlikecvs(List<Employerlikecv> employerlikecvs) {
		this.employerlikecvs = employerlikecvs;
	}

	public Employerlikecv addEmployerlikecv(Employerlikecv employerlikecv) {
		getEmployerlikecvs().add(employerlikecv);
		employerlikecv.setCv(this);

		return employerlikecv;
	}

	public Employerlikecv removeEmployerlikecv(Employerlikecv employerlikecv) {
		getEmployerlikecvs().remove(employerlikecv);
		employerlikecv.setCv(null);

		return employerlikecv;
	}

	public List<Logaccount> getLogaccounts() {
		return this.logaccounts;
	}

	public void setLogaccounts(List<Logaccount> logaccounts) {
		this.logaccounts = logaccounts;
	}

	public Logaccount addLogaccount(Logaccount logaccount) {
		getLogaccounts().add(logaccount);
		logaccount.setCv(this);

		return logaccount;
	}

	public Logaccount removeLogaccount(Logaccount logaccount) {
		getLogaccounts().remove(logaccount);
		logaccount.setCv(null);

		return logaccount;
	}

	public List<Projectorproductworked> getProjectorproductworkeds() {
		return this.projectorproductworkeds;
	}

	public void setProjectorproductworkeds(List<Projectorproductworked> projectorproductworkeds) {
		this.projectorproductworkeds = projectorproductworkeds;
	}

	public Projectorproductworked addProjectorproductworked(Projectorproductworked projectorproductworked) {
		getProjectorproductworkeds().add(projectorproductworked);
		projectorproductworked.setCv(this);

		return projectorproductworked;
	}

	public Projectorproductworked removeProjectorproductworked(Projectorproductworked projectorproductworked) {
		getProjectorproductworkeds().remove(projectorproductworked);
		projectorproductworked.setCv(null);

		return projectorproductworked;
	}

	public List<Skillincv> getSkillincvs() {
		return this.skillincvs;
	}

	public void setSkillincvs(List<Skillincv> skillincvs) {
		this.skillincvs = skillincvs;
	}

	public Skillincv addSkillincv(Skillincv skillincv) {
		getSkillincvs().add(skillincv);
		skillincv.setCv(this);

		return skillincv;
	}

	public Skillincv removeSkillincv(Skillincv skillincv) {
		getSkillincvs().remove(skillincv);
		skillincv.setCv(null);

		return skillincv;
	}

	public List<Socialactivity> getSocialactivities() {
		return this.socialactivities;
	}

	public void setSocialactivities(List<Socialactivity> socialactivities) {
		this.socialactivities = socialactivities;
	}

	public Socialactivity addSocialactivity(Socialactivity socialactivity) {
		getSocialactivities().add(socialactivity);
		socialactivity.setCv(this);

		return socialactivity;
	}

	public Socialactivity removeSocialactivity(Socialactivity socialactivity) {
		getSocialactivities().remove(socialactivity);
		socialactivity.setCv(null);

		return socialactivity;
	}

	public List<Workexperience> getWorkexperiences() {
		return this.workexperiences;
	}

	public void setWorkexperiences(List<Workexperience> workexperiences) {
		this.workexperiences = workexperiences;
	}

	public Workexperience addWorkexperience(Workexperience workexperience) {
		getWorkexperiences().add(workexperience);
		workexperience.setCv(this);

		return workexperience;
	}

	public Workexperience removeWorkexperience(Workexperience workexperience) {
		getWorkexperiences().remove(workexperience);
		workexperience.setCv(null);

		return workexperience;
	}

}