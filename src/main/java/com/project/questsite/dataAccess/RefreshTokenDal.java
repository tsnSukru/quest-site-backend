package com.project.questsite.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questsite.entities.RefreshToken;

public interface RefreshTokenDal extends JpaRepository<RefreshToken, Long> {

	RefreshToken findByUserId(Long id);

}
