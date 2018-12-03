package Model;

import java.util.HashMap;

public class Document {
    private String id;
    private int indexId;
    public static int doccumentsAmount = 1;
    private int max_tf;
    private HashMap<Term, Integer> textTerms;
    private String date;
    private String title;
    private String content;
    private String city;
    private HashMap<Term, Integer> titleTerms;
    private HashMap<Term, Integer> dateTerms;

    public Document() {
        titleTerms = new HashMap<>();
        dateTerms = new HashMap<>();
        textTerms = new HashMap<>();
        max_tf = 0;
        indexId = doccumentsAmount++;
        content = "";
    }

    public int getMax_tf() {
        return max_tf;
    }

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getCity() {
        return city;
    }

    void setDate(String date) {
        this.date = date;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getContent() {
        return content;
    }

    void setContent(String content) {
        this.content = content;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public HashMap<Term, Integer> getTextTerms() {
        return textTerms;
    }

    void addTermToText(Term term) {
        if (!textTerms.containsKey(term))
            textTerms.put(term, 1);
        else {
            textTerms.put(term, textTerms.get(term) + 1);
        }
        if (textTerms.get(term) > max_tf)
            max_tf = textTerms.get(term);
    }


    public void print() {
        System.out.println("id :" + id);
        System.out.println("title :" + title);
        System.out.println("date :" + date);
        System.out.println("city :" + city);
        System.out.println("Text :" + content);
        System.out.println("-------------------------------------------------");
    }

    public int getIndexId() {
        return indexId;
    }
}