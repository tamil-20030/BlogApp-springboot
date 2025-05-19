package com.personal.BlogApp.articles.dtos;

import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class UpdateArticleRequest {

    @Nullable
    String title;

    @Nullable
    String body;

    @Nullable
    String subtitle;
}
