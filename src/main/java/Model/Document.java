package Model;

import java.util.HashMap;

/**
 * The document class which holds all of its required information
 */
public class Document extends ADocument {
    private int max_tf;
    private HashMap<String, String> cities;
    private double rank;
    private String date;
    private String title;
    private HashMap<Term, Integer> titleTerms;
    private HashMap<Term, Integer> dateTerms;

    /**
     * The default document constructor
     */
    public Document() {
        rank = 0;
        titleTerms = new HashMap<>();
        dateTerms = new HashMap<>();
        cities = new HashMap<>();
        max_tf = 0;
        indexId = documentsAmount++;
        content = "";
    }

    /**
     * Adds a term which was found on this document text
     *
     * @param term - The found term
     */
    void addTermToText(Term term) {
        if (!textTerms.containsKey(term))
            textTerms.put(term.getValue(), term);
        else {
            term.setAmount(term.getAmount() + 1);
            textTerms.put(term.getValue(), term);
        }
        if (textTerms.get(term).getAmount() > max_tf)
            max_tf = textTerms.get(term).getAmount();
    }

    /**
     * Initialize the documents amount to 1
     */
    void initialize() {
        documentsAmount = 1;
        indexId = documentsAmount;
    }

    /**
     * Returns the document's max term frequency
     *
     * @return - The max term frequency
     */
    int getMax_tf() {
        return max_tf;
    }

    /**
     *
     * @return the rank of the document
     */
    public double getRank () {return rank;}

    /**
     *
     * @param rank the rank of the document after calculation in ranker
     */
    public void setRank (double rank){this.rank = rank;}


    /**
     * Sets the document's date to the given date
     *
     * @param date - The given date
     */
    void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the document's title to the given title
     *
     * @param title - The given title
     */
    void setTitle(String title) {
        this.title = title;
    }

    public void setMax_tf(int max_tf) { this.max_tf = max_tf; }


}
