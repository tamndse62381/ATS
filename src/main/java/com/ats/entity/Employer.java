package com.ats.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the employer database table.
 * 
 */
@Entity
@NamedQuery(name="Employer.findAll", query="SELECT e FROM Employer e")
public class Employer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	@Lob
	private String description;

	private String email;

	private String fullName;

	private String gender;

	private String status;

	private String telephoneNumber;

	private String vacancyName;

	//bi-directional many-to-one association to Company
	@OneToMany(mappedBy="employer")
	private List<Company> companies;

	//bi-directional many-to-one association to Joblevel
	@ManyToOne
	@JoinColumn(name="JobLevelID")
	private Joblevel joblevel;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="AccountID")
	private Account account;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="CityID")
	private City city;

	//bi-directional many-to-one association to Employerlikecv
	@OneToMany(mappedBy="employer")
	private List<Employerlikecv> employerlikecvs;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="employer")
	private List<Job> jobs;

	//bi-directional many-to-one association to Logemployer
	@OneToMany(mappedBy="employer")
	private List<Logemployer> logemployers;

	//bi-directional many-to-one association to Receipt
	@OneToMany(mappedBy="employer")
	private List<Receipt> receipts;

	public Employer() {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getVacancyName() {
		return this.vacancyName;
	}

	public void setVacancyName(String vacancyName) {
		this.vacancyName = vacancyName;
	}

//	public List<Company> getCompanies() {
//		return this.companies;
//	}
//
//	public void setCompanies(List<Company> companies) {
//		this.companies = companies;
//	}
//
//	public Company addCompany(Company company) {
//		getCompanies().add(company);
//		company.setEmployer(this);
//
//		return company;
//	}
//
//	public Company removeCompany(Company company) {
//		getCompanies().remove(company);
//		company.setEmployer(null);
//
//		return company;
//	}
//
//	public Joblevel getJoblevel() {
//		return this.joblevel;
//	}
//
//	public void setJoblevel(Joblevel joblevel) {
//		this.joblevel = joblevel;
//	}
//
//	public Account getAccount() {
//		return this.account;
//	}
//
//	public void setAccount(Account account) {
//		this.account = account;
//	}
//
//	public City getCity() {
//		return this.city;
//	}
//
//	public void setCity(City city) {
//		this.city = city;
//	}
//
//	public List<Employerlikecv> getEmployerlikecvs() {
//		return this.employerlikecvs;
//	}
//
//	public void setEmployerlikecvs(List<Employerlikecv> employerlikecvs) {
//		this.employerlikecvs = employerlikecvs;
//	}
//
//	public Employerlikecv addEmployerlikecv(Employerlikecv employerlikecv) {
//		getEmployerlikecvs().add(employerlikecv);
//		employerlikecv.setEmployer(this);
//
//		return employerlikecv;
//	}
//
//	public Employerlikecv removeEmployerlikecv(Employerlikecv employerlikecv) {
//		getEmployerlikecvs().remove(employerlikecv);
//		employerlikecv.setEmployer(null);
//
//		return employerlikecv;
//	}
//
//	public List<Job> getJobs() {
//		return this.jobs;
//	}
//
//	public void setJobs(List<Job> jobs) {
//		this.jobs = jobs;
//	}
//
//	public Job addJob(Job job) {
//		getJobs().add(job);
//		job.setEmployer(this);
//
//		return job;
//	}
//
//	public Job removeJob(Job job) {
//		getJobs().remove(job);
//		job.setEmployer(null);
//
//		return job;
//	}
//
//	public List<Logemployer> getLogemployers() {
//		return this.logemployers;
//	}
//
//	public void setLogemployers(List<Logemployer> logemployers) {
//		this.logemployers = logemployers;
//	}
//
//	public Logemployer addLogemployer(Logemployer logemployer) {
//		getLogemployers().add(logemployer);
//		logemployer.setEmployer(this);
//
//		return logemployer;
//	}
//
//	public Logemployer removeLogemployer(Logemployer logemployer) {
//		getLogemployers().remove(logemployer);
//		logemployer.setEmployer(null);
//
//		return logemployer;
//	}
//
//	public List<Receipt> getReceipts() {
//		return this.receipts;
//	}
//
//	public void setReceipts(List<Receipt> receipts) {
//		this.receipts = receipts;
//	}
//
//	public Receipt addReceipt(Receipt receipt) {
//		getReceipts().add(receipt);
//		receipt.setEmployer(this);
//
//		return receipt;
//	}
//
//	public Receipt removeReceipt(Receipt receipt) {
//		getReceipts().remove(receipt);
//		receipt.setEmployer(null);
//
//		return receipt;
//	}

}