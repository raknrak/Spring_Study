package kr.co.chunjae.vo;

public class ArticleVO {
    private int articleNo;
    private String writer;
    private String title;
    private String content;

    // 기본 메서드
    public ArticleVO() {    }

    public ArticleVO(int articleNo, String writer, String title, String content) {
        this.articleNo = articleNo;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    //getter and setter
    public int getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    // toString
    @Override
    public String toString() {
        String info = "";
        info += "\n" + articleNo + "\n" + writer + "\n" + title + "\n" + content + "\n";
        return info;
    }
}
