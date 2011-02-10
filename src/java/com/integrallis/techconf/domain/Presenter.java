package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Presenter extends User implements Serializable {

	private static final long serialVersionUID = -3991673522717104886L;
	
	public static String PROP_BIO = "Bio";
	public static String PROP_COMPANY = "Company";
	public static String PROP_COMPANYURL = "CompanyURL";
	public static String PROP_BLOGLINK = "BlogLink";

	// fields
	private String bio;
	private String company;	
	private String companyURL;
	private BlogLink blogLink;

	// collections
	private Set abstracts;
	private Set<Book> books;

	/**
	 * @return Returns the books.
	 */
	public Set<Book> getBooks() {
		return books;
	}

	/**
	 * @param books The books to set.
	 */
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	public void addToBooks(Book book) {
		if (null == this.books)
			this.books = new HashSet<Book>();
		this.books.add(book);
	}	

	// constructors
	public Presenter() {
	}
	
	public Presenter(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
	}	

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Set getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(Set abstracts) {
		this.abstracts = abstracts;
	}

	@SuppressWarnings("unchecked")
	public void addToAbstracts(Object obj) {
		if (null == this.abstracts)
			this.abstracts = new HashSet();
		this.abstracts.add(obj);
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof Presenter))
			return false;
		else {
			Presenter mObj = (Presenter) obj;
			if (null == this.getId() || null == mObj.getId())
				return false;
			else
				return (this.getId().equals(mObj.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return hashCode;
	}

	/**
	 * @return Returns the companyURL.
	 */
	public String getCompanyURL() {
		return companyURL;
	}

	/**
	 * @param companyURL The companyURL to set.
	 */
	public void setCompanyURL(String companyURL) {
		this.companyURL = companyURL;
	}

	/**
	 * @return Returns the blogLink.
	 */
	public BlogLink getBlogLink() {
		return blogLink;
	}

	/**
	 * @param blogLink The blogLink to set.
	 */
	public void setBlogLink(BlogLink blogLink) {
		this.blogLink = blogLink;
	}

}