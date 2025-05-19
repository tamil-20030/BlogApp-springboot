package com.personal.BlogApp.articles;

import com.personal.BlogApp.articles.dtos.CreateArticleRequest;
import com.personal.BlogApp.articles.dtos.UpdateArticleRequest;
import com.personal.BlogApp.users.UserRepository;
import com.personal.BlogApp.users.UsersService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@AllArgsConstructor
//@NoArgsConstructor
@Service
public class ArticlesService {

//    private final UsersService usersService;
    private ArticleRepository articleRepository;
    private UserRepository userRepository;


    public ArticlesService(ArticleRepository articleRepository, UserRepository userRepository, UsersService usersService) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
//        this.usersService = usersService;
    }



    public Iterable<ArticleEntity> getAllArticles()
    {
        return articleRepository.findAll();
    }

    public ArticleEntity findArticleBySlug(String slug)
    {
        var article=articleRepository.findArticleBySlug(slug);
        if(article==null)
            throw new ArticleNotFoundException(slug);
        return article;
    }

    public ArticleEntity createArticle(CreateArticleRequest a,Long authorId)
    {
        var author= userRepository.findById(authorId).orElseThrow(()->new UsersService.UserNotFoundException(authorId));
        var article=ArticleEntity.builder()
                .title(a.getTitle())
                .slug(a.getTitle()) //TODO: create a proper slugification function
                .body(a.getBody())
                .subTitle(a.getSubtitle())
                .author(author)
                .build();
        return articleRepository.save(article);
    }
    public ArticleEntity updateArticle(UpdateArticleRequest a, Long articleId){
        var article=articleRepository.findById(articleId).orElseThrow(()->new ArticleNotFoundException(articleId));
        if(a.getTitle()!=null) {
            article.setTitle(a.getTitle());
            article.setSlug(a.getTitle());
        }if(a.getBody()!=null)
            article.setBody(a.getBody());
        if(a.getSubtitle()!=null)
            article.setSubTitle(a.getSubtitle());

        return articleRepository.save(article);
    }
    static class ArticleNotFoundException extends IllegalArgumentException{
        public ArticleNotFoundException(String slug) {
            super("Article with Slug " + slug + " not found");
        }

        public ArticleNotFoundException(Long id) {
            super("Article with id " + id + " not found");
        }
    }
}