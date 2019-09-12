package com.abn.abnspringassignment.dao;

import org.springframework.stereotype.Repository;

import com.abn.abnspringassignment.entity.RecipesDataBean;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository("recipesRepository")
public interface RecipesRepository extends JpaRepository<RecipesDataBean , Long>{ 

}
