package com.project.questsite.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "post")
@Data
public class Post {
	@Id
	Long id;
	Long userId;
	String title;
	@Lob
	@Column(columnDefinition = "text") // hibernate sql de string olarak alması icin. Yazmazsak varchar(255) alır
	String text;
}
