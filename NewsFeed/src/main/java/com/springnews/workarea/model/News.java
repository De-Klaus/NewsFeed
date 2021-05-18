package com.springnews.workarea.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.springnews.workarea.config.ApplicationConstants;


@Entity //анотация для работы в БД
@Table(name="newsсontent") //анотация для работы с конкретной таблицей из указаннов в файлике БД
public class News {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotEmpty(message = "Heading should not be empty")
	@Size(min = 2, max = 80, message = "Heading should be between 2 and 80 characters")
	@Column(name="heading")
	private String heading;
	
	@NotEmpty(message = "News should not be empty")
	@Column(name="news")
	private String news;
	
	@Column(name="path")
	private String path;
	
	@Column(name="date_add")
	private Date date_add;
	
	public News() {
	}

	public News(String heading, String news, String path,  Date date_add) {
		this.heading = heading;
		this.news = news;
		this.path = path;
		this.date_add = date_add;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public String getPath() {
		return ApplicationConstants.getDirectory+path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getDate_add() {
		return date_add;
	}

	public void setDate_add(Date date_add) {
		this.date_add = date_add;
	}

	@Override
	public String toString() {
		return "DateNews [id=" + id + ", heading=" + heading + ", news=" + news + ", path=" + path + ", date_add="
				+ date_add + "]";
	}
	
}
