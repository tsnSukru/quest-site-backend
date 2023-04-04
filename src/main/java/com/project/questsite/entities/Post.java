package com.project.questsite.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import lombok.Data;

@Entity
@Table(name = "post")
@Data
public class Post {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne(fetch = FetchType.LAZY) // postu db'den cektigimizde user'ida cekmesin
	@JoinColumn(name = "user_id", nullable = false) // iliskili kolon
	@OnDelete(action = OnDeleteAction.CASCADE) // user silindiginde postlarida silinsin
	@JsonIgnore
	User user;

	@Column(name = "title")
	String title;

	@Lob
	@Column(columnDefinition = "text") // hibernate sql de string olarak alması icin. Yazmazsak varchar(255) alır
	String text;

	public Post(Long id, Long userId, String title, String text) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
	}

	public Post() {
		// TODO Auto-generated constructor stub
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
}
