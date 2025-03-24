package com.personal.BlogApp.articles;

import com.personal.BlogApp.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;


@Entity(name = "articles")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @Column(unique = true)
    private String slug;

    @Nullable
    private String subTitle;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="authorId", nullable = false)
   private UserEntity author;
    //TODO: add tags

}
