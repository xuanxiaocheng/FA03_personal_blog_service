package app.example.blogBE.entity;

import lombok.Data;

@Data
public class Question {
    private String articleTitle;
    private String markdown;
    private String articleId;
}
