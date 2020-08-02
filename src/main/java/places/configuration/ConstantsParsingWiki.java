package places.configuration;

import places.wiki.Article;
import places.wiki.Category;

import java.util.*;

public class ConstantsParsingWiki {

    private static final Set<String> languages = new HashSet<>();// codes of languages
    private static final List<Category> listCategories = new ArrayList<>();
    private static final Set<Article> setArticles = new HashSet<>();
    private static final Set<Article> setUnusedArticles = new HashSet<>();
    private static String startPage = "https://en.wikipedia.org/wiki/Category:Romanesque_architecture";
    private static String errorMessage = "";
    //private static final Map<Integer, Set<Category>> mapLanguagesWithOrderedCategory = new HashMap<>();//for no-normal sorting

    public ConstantsParsingWiki() {
    }

    /**
     * map for no-normal sorting
     *
     * @return
     */
    public static Map<Integer, Set<Category>> createMapLanguagesWithOrderedCategory() {
        Map<Integer, Set<Category>> mapLanguagesWithOrderedCategory = new HashMap<>();
        mapLanguagesWithOrderedCategory.put(1, new HashSet<>());//en
        mapLanguagesWithOrderedCategory.put(2, new HashSet<>());//fr
        mapLanguagesWithOrderedCategory.put(3, new HashSet<>());//de
        mapLanguagesWithOrderedCategory.put(4, new HashSet<>());//it
        mapLanguagesWithOrderedCategory.put(5, new HashSet<>());//es
        mapLanguagesWithOrderedCategory.put(6, new HashSet<>());//ro
        mapLanguagesWithOrderedCategory.put(7, new HashSet<>());//ru
        mapLanguagesWithOrderedCategory.put(8, new HashSet<>());//uk
        mapLanguagesWithOrderedCategory.put(99, new HashSet<>());//other
        return mapLanguagesWithOrderedCategory;
    }

    public static String getStartPage() {
        return startPage;
    }

    public static void setStartPage(String startPage) {
        ConstantsParsingWiki.startPage = startPage;
    }

    public static Set<String> getLanguages() {
        return languages;
    }

    public static List<Category> getListCategories() {
        return listCategories;
    }

    public static Set<Article> getSetArticles() {
        return setArticles;
    }

    public static Set<Article> getSetUnusedArticles() {
        return setUnusedArticles;
    }

    public static String getErrorMessage() {
        return errorMessage;
    }

    public static void setErrorMessage(String errorMessage) {
        ConstantsParsingWiki.errorMessage = errorMessage;
    }
}
