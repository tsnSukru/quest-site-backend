package com.project.questsite.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "comment")
@Data
public class Comment {
	@Id
	Long id;
	Long postId;
	Long userId;
	@Lob // buyuk objeleri kabul etmesi icin
	@Column(columnDefinition = "text")
	String text;
}
