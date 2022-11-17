package app.example.blogBE.entity;

import lombok.Data;

@Data
public class Article {
    private String articleTitle;
    private String markdown;
    private String articleId;
    private String coverImgSrc;
    private String sign;
}
