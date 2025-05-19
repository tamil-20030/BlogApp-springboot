package com.personal.BlogApp.articles.dtos;

import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CreateArticleRequest {

    @NonNull
    String title;

    @NonNull
    String body;

    @Nullable
    String subtitle;
}
