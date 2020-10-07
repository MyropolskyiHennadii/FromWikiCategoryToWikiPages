package places.wiki;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import places.configuration.ConstantsParsingWiki;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ManageWikiCategoriesAndPages {

    //private static final Logger log = Logger.getLogger(WebMain.class);//without spring
    private static final Logger log = LogManager.getLogger(ManageWikiCategoriesAndPages.class);

    /**
     * get a languge of page (doc)
     *
     * @param doc html doc
     * @return
     */
    public static String getPageLanguage(Document doc) {
        String lang = null;
        Elements htmls = doc.getElementsByTag("html");
        for (Element e : htmls) {
            lang = e.attr("lang");
            if (lang != null && !lang.isEmpty()) {
                ConstantsParsingWiki.getLanguages().add(e.attr("lang"));
            }
            break;
        }
        return lang;
    }

    /**
     * get title of page (doc)
     *
     * @param doc html doc
     * @return
     */
    public static String getPageTitle(Document doc) {
        String title = null;
        Elements htmls = doc.getElementsByTag("title");
        for (Element e : htmls) {
            title = e.text();
            if (title != null && !title.isEmpty()) {
                ConstantsParsingWiki.getLanguages().add(e.attr("lang"));
            }
            break;
        }
        return title;
    }

    /**
     * because of 'no-normal'order of languages have to order languages in such a case
     *
     * @param mapCategory
     * @param link
     */
    public static void addLinkCategoryToLanguageOrderedMap(Map<Integer, Set<Category>> mapCategory, Element link) {
        String lang = link.attr("lang").trim();
        switch (lang) {
            case "en": {
                mapCategory.get(1).add(new Category(lang, link.text(), link.attr("abs:href")));
                break;
            }
            case "fr": {
                mapCategory.get(2).add(new Category(lang, link.text(), link.attr("abs:href")));
                break;
            }
            case "de": {
                mapCategory.get(3).add(new Category(lang, link.text(), link.attr("abs:href")));
                break;
            }
            case "it": {
                mapCategory.get(4).add(new Category(lang, link.text(), link.attr("abs:href")));
                break;
            }
            case "es": {
                mapCategory.get(5).add(new Category(lang, link.text(), link.attr("abs:href")));
                break;
            }
            case "ro": {
                mapCategory.get(6).add(new Category(lang, link.text(), link.attr("abs:href")));
                break;
            }
            case "ru": {
                mapCategory.get(7).add(new Category(lang, link.text(), link.attr("abs:href")));
                break;
            }
            case "uk": {
                mapCategory.get(8).add(new Category(lang, link.text(), link.attr("abs:href")));
                break;
            }
            default:
                mapCategory.get(99).add(new Category(lang, link.text(), link.attr("abs:href")));
        }
    }

    /**
     * because of 'no-normal'order of languages have to order languages in such a case
     *
     * @param mapCategory
     * @param doc
     * @param href
     */
    public static void addLinkCategoryToLanguageOrderedMap(Map<Integer, Set<Category>> mapCategory, Document doc, String href) {
        String lang = getPageLanguage(doc);
        String title = getPageTitle(doc);
        switch (lang) {
            case "en": {
                mapCategory.get(1).add(new Category(lang, title, href));
                break;
            }
            case "fr": {
                mapCategory.get(2).add(new Category(lang, title, href));
                break;
            }
            case "de": {
                mapCategory.get(3).add(new Category(lang, title, href));
                break;
            }
            case "it": {
                mapCategory.get(4).add(new Category(lang, title, href));
                break;
            }
            case "es": {
                mapCategory.get(5).add(new Category(lang, title, href));
                break;
            }
            case "ro": {
                mapCategory.get(6).add(new Category(lang, title, href));
                break;
            }
            case "ru": {
                mapCategory.get(7).add(new Category(lang, title, href));
                break;
            }
            case "uk": {
                mapCategory.get(8).add(new Category(lang, title, href));
                break;
            }
            default:
                mapCategory.get(99).add(new Category(lang, title, href));
        }
    }

    /**
     * because of 'no-normal'order of languages have to order languages in such a case
     *
     * @param mapCategory
     * @param category
     */
    public void addLinkCategoryToLanguageOrderedMap(Map<Integer, Set<Category>> mapCategory, Category category) {
        String lang = category.getLang();
        switch (lang) {
            case "en": {
                mapCategory.get(1).add(category);
                break;
            }
            case "fr": {
                mapCategory.get(2).add(category);
                break;
            }
            case "de": {
                mapCategory.get(3).add(category);
                break;
            }
            case "it": {
                mapCategory.get(4).add(category);
                break;
            }
            case "es": {
                mapCategory.get(5).add(category);
                break;
            }
            case "ro": {
                mapCategory.get(6).add(category);
                break;
            }
            case "ru": {
                mapCategory.get(7).add(category);
                break;
            }
            case "uk": {
                mapCategory.get(8).add(category);
                break;
            }
            default:
                mapCategory.get(99).add(category);
        }
    }

    /**
     * fill list of categories for the doc (page)
     *
     * @param doc
     */
    public static List<Category> getFullListSynonymInAllLanguagesFromPage(Document doc) {

        Map<Integer, Set<Category>> mapCategory = ConstantsParsingWiki.createMapLanguagesWithOrderedCategory();
        //at first: adding start page:
        addLinkCategoryToLanguageOrderedMap(mapCategory, doc, ConstantsParsingWiki.getStartPage());

        Element p_lang = doc.getElementById("p-lang");
        if (p_lang != null) {
            Elements links = p_lang.select("a[href]");
            for (Element link : links) {
                addLinkCategoryToLanguageOrderedMap(mapCategory, link);
            }
        } else {
            log.info("There is no element 'p-lang' in " + doc.baseUri());
        }
        return (getOrderedListOfCategories(mapCategory));
    }

    /**
     * /have to sort with no-normal order, that en, de, fr, it, es, ro, ru, uk stand the first
     *
     * @param mapCategory
     * @return
     */
    public static <T extends Category> List<T> getOrderedListOfCategories(Map<Integer, Set<T>> mapCategory) {
        List<T> orderedList = new ArrayList<>();
        orderedList.addAll(mapCategory.get(1));
        orderedList.addAll(mapCategory.get(2));
        orderedList.addAll(mapCategory.get(3));
        orderedList.addAll(mapCategory.get(4));
        orderedList.addAll(mapCategory.get(5));
        orderedList.addAll(mapCategory.get(6));
        orderedList.addAll(mapCategory.get(7));
        orderedList.addAll(mapCategory.get(8));
        orderedList.addAll(mapCategory.get(99));

        return orderedList;
    }


    /**
     * whether erticle exist already in list
     *
     * @param categories
     * @param articles
     * @return
     */
    public static boolean referenceExistsInList(List<Category> categories, Set<Article> articles) {
        for (Category article : categories
        ) {
            if (articles.contains(article)) {
                //already exists
                return true;
            }
        }
        return false;
    }

    /**
     * check whether the page and its synonyms already exist in articles set and add it, if there is no such article
     *
     * @param href  reference
     * @param title header
     * @throws IOException
     */
    public static void checkAndAddSimpleArticleToSet(String href, String title) throws IOException {
        boolean articleExists = false;
        Set<Article> articles = ConstantsParsingWiki.getSetArticles();
        Set<Article> unusedArticles = ConstantsParsingWiki.getSetUnusedArticles();

        if (articles.contains(new Article("", title, href, ""))) {
            //already exists
            return;
        }
        Document doc = Jsoup.connect(href).get();

        List<Category> synonymArticlesInOtherLanguages = getFullListSynonymInAllLanguagesFromPage(doc);
        if (referenceExistsInList(synonymArticlesInOtherLanguages, articles)) {
            //already exists
            return;
        }

        if (!articleExists) {
            //check geodata. If there is => ok. if there isn't => ommit
            Element scriptElement = doc.getElementsByTag("script").first();
            String wholeText = scriptElement.dataNodes().get(0).getWholeData();
            int iPos = wholeText.indexOf("\"wgCoordinates\"");
            String coordinate = "";
            if (iPos > -1) {
                wholeText = wholeText.substring(iPos + 16);
                iPos = wholeText.indexOf("}");
                if (iPos >= 1) {
                    coordinate = wholeText.substring(0, iPos + 1);
                }
            }
            if (coordinate.isEmpty()) {//without geodata
                if (!unusedArticles.contains(new Article("", title, href, ""))) {
                    //doesn't exist directly
                    if (!referenceExistsInList(synonymArticlesInOtherLanguages, unusedArticles)) {
                        //doesn't exist in synonyms
                        unusedArticles.add(new Article(getPageLanguage(doc), getPageTitle(doc), href, ""));
                    }
                }
            } else {
                //create object and add it to Set articles with geodata
                articles.add(new Article(getPageLanguage(doc), getPageTitle(doc), href, coordinate));
            }
        }

    }

    /**
     * find sub-articles (simple pages) and subcategory in Category-page
     *
     * @param category
     * @throws IOException
     */
    public void findArticlesAndSubCategoryAtCategory(Category category) throws IOException {

        String startPage = category.getReference().trim();
        Document doc = Jsoup.connect(startPage).get();

        //at first: look for articles/pages
        if (doc != null) {
            Element bodyContent = doc.getElementById("bodyContent");
            if (bodyContent != null) {
                Element mw_content_text = bodyContent.getElementById("mw-content-text");
                if (mw_content_text != null) {
                    //simple articles
                    Element mw_pages = mw_content_text.getElementById("mw-pages");
                    if (mw_pages != null) {
                        Elements mw_category_group = mw_pages.getElementsByClass("mw-category-group");
                        for (Element group : mw_category_group) {
                            Elements links = group.select("a[href]");
                            for (Element link : links) {
                                checkAndAddSimpleArticleToSet(link.attr("abs:href"), link.attr("title"));
                            }
                        }
                    }
                    //then: subcategories with recursion
                    Element mw_subcategories = mw_content_text.getElementById("mw-subcategories");
                    if (mw_subcategories != null) {
                        Elements mw_category_group = mw_subcategories.getElementsByClass("mw-category-group");
                        if (mw_category_group != null) {
                            for (Element group : mw_category_group) {
                                Elements links = group.select("a[href]");
                                for (Element link : links) {
                                    findArticlesAndSubCategoryAtCategory(new Category(getPageLanguage(doc), link.attr("title"), link.attr("abs:href")));
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    /**
     * claring all variable before start
     */
    public static void clearAll() {
        ConstantsParsingWiki.getListCategories().clear();
        ConstantsParsingWiki.getLanguages().clear();
        ConstantsParsingWiki.getSetArticles().clear();
        ConstantsParsingWiki.getSetUnusedArticles().clear();
        ConstantsParsingWiki.setStartPage("");
        ConstantsParsingWiki.setStartPageWasChanged(true);
        ConstantsParsingWiki.setErrorMessage("");
    }

    /**
     * form list of Category at the start page
     */
    public void formListCategoryAtStartPage() throws IOException {
        String startPage = ConstantsParsingWiki.getStartPage();
        Document doc = Jsoup.connect(startPage).get();
        //list of the associated pages in other languages
        ConstantsParsingWiki.getListCategories().addAll(getFullListSynonymInAllLanguagesFromPage(doc));
    }

    /**
     * @return List of results
     * @throws IOException
     */
    public List<Category> doQueryToWikiCategory() throws IOException {

        //getListCategories: list of Categories is ready at this point
        for (Category category : ConstantsParsingWiki.getListCategories()) {
            findArticlesAndSubCategoryAtCategory(category);
        }

        //ordering Set of articles, recieve List
        Map<Integer, Set<Category>> orderedByLanguages = ConstantsParsingWiki.createMapLanguagesWithOrderedCategory();
        for (Article article : ConstantsParsingWiki.getSetArticles()) {
            addLinkCategoryToLanguageOrderedMap(orderedByLanguages, article);
        }
        return getOrderedListOfCategories(orderedByLanguages);
    }
}

