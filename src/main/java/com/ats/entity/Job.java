package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the job database table.
 * 
 */
@Entity
@NamedQuery(name="Job.findAll", query="SELECT j FROM Job j")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String additionalRequest;

	private String address;

	private String candidateBenefits;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDateForApply;

	private String jobDescription;

	private double salaryFrom;

	private double salaryTo;

	private String status;

	private String title;

	private String vacancyName;

	private int yearExperience;

	//bi-directional many-to-one association to Apply
	@OneToMany(mappedBy="job")
	private List<Apply> applies;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="CityID")
	private City city;

	//bi-directional many-to-one association to Employer
	@ManyToOne
	@JoinColumn(name="EmployerID")
	private Employer employer;

	//bi-directional many-to-one association to Industry
	@ManyToOne
	@JoinColumn(name="IndustryID")
	private Industry industry;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="CompanyID")
	private Company company;

	//bi-directional many-to-one association to Joblevel
	@ManyToOne
	@JoinColumn(name="JobLevelID")
	private Joblevel joblevel;

	//bi-directional many-to-one association to Jobseekerlikejob
	@OneToMany(mappedBy="job")
	private List<Jobseekerlikejob> jobseekerlikejobs;

	//bi-directional many-to-one association to Skillneedforjob
	@OneToMany(mappedBy="job")
	private List<Skillneedforjob> skillneedforjobs;

	public Job() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdditionalRequest() {
		return this.additionalRequest;
	}

	public void setAdditionalRequest(String additionalRequest) {
		this.additionalRequest = additionalRequest;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCandidateBenefits() {
		return this.candidateBenefits;
	}

	public void setCandidateBenefits(String candidateBenefits) {
		this.candidateBenefits = candidateBenefits;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getEndDateForApply() {
		return this.endDateForApply;
	}

	public void setEndDateForApply(Date endDateForApply) {
		this.endDateForApply = endDateForApply;
	}

	public String getJobDescription() {
		return this.jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
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

	public String getVacancyName() {
		return this.vacancyName;
	}

	public void setVacancyName(String vacancyName) {
		this.vacancyName = vacancyName;
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
		apply.setJob(this);

		return apply;
	}

	public Apply removeApply(Apply apply) {
		getApplies().remove(apply);
		apply.setJob(null);

		return apply;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Employer getEmployer() {
		return this.employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public Industry getIndustry() {
		return this.industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Joblevel getJoblevel() {
		return this.joblevel;
	}

	public void setJoblevel(Joblevel joblevel) {
		this.joblevel = joblevel;
	}

//	public List<Jobseekerlikejob> getJobseekerlikejobs() {
//		return this.jobseekerlikejobs;
//	}
//
//	public void setJobseekerlikejobs(List<Jobseekerlikejob> jobseekerlikejobs) {
//		this.jobseekerlikejobs = jobseekerlikejobs;
//	}
//
//	public Jobseekerlikejob addJobseekerlikejob(Jobseekerlikejob jobseekerlikejob) {
//		getJobseekerlikejobs().add(jobseekerlikejob);
//		jobseekerlikejob.setJob(this);
//
//		return jobseekerlikejob;
//	}
//
//	public Jobseekerlikejob removeJobseekerlikejob(Jobseekerlikejob jobseekerlikejob) {
//		getJobseekerlikejobs().remove(jobseekerlikejob);
//		jobseekerlikejob.setJob(null);
//
//		return jobseekerlikejob;
//	}
//
//	public List<Skillneedforjob> getSkillneedforjobs() {
//		return this.skillneedforjobs;
//	}
//
//	public void setSkillneedforjobs(List<Skillneedforjob> skillneedforjobs) {
//		this.skillneedforjobs = skillneedforjobs;
//	}
//
//	public Skillneedforjob addSkillneedforjob(Skillneedforjob skillneedforjob) {
//		getSkillneedforjobs().add(skillneedforjob);
//		skillneedforjob.setJob(this);
//
//		return skillneedforjob;
//	}
//
//	public Skillneedforjob removeSkillneedforjob(Skillneedforjob skillneedforjob) {
//		getSkillneedforjobs().remove(skillneedforjob);
//		skillneedforjob.setJob(null);
//
//		return skillneedforjob;
//	}

}