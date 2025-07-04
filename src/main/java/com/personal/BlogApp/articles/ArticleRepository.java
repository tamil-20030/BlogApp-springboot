package com.personal.BlogApp.articles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity,Long> {

    ArticleEntity findArticleBySlug(String slug);
}
