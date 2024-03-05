package com.project.questsite.entities;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "post")
@Data
public class Post {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne(fetch = FetchType.EAGER) // postu db'den cektigimizde user'ida cekmesin
	@JoinColumn(name = "user_id", nullable = false) // iliskili kolon
	@OnDelete(action = OnDeleteAction.CASCADE) // user silindiginde postlarida silinsin
	User user;

	@Column(name = "title")
	String title;

	@Lob
	@Column(name = "text", columnDefinition = "text") // hibernate sql de string olarak alması icin. Yazmazsak
														// varchar(255) alır
	String text;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	Date createDate;

	public Post() {

	}

	public static class Builder {
		private User user;
		private String title;
		private String text;
		private Date createDate;

		public Builder setUser(User user) {
			this.user = user;
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setText(String text) {
			this.text = text;
			return this;
		}

		public Builder setCreateDate(Date createDate) {
			this.createDate = createDate;
			return this;
		}

		public Post build() {
			Post post = new Post();
			post.user = this.user;
			post.title = this.title;
			post.text = this.text;
			post.createDate = this.createDate;
			return post;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
