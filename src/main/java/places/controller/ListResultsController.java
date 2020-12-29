package places.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import places.configuration.ConstantsParsingWiki;
import places.storage.FileSystemStorageService;
import places.util.Mappings;
import places.util.ViewNames;
import places.wiki.Category;
import places.wiki.ManageWikiCategoriesAndPages;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static places.configuration.ConstantsParsingWiki.isStartPageWasChanged;

@Controller
public class ListResultsController {

    public static List<Category> listArticles = new ArrayList<>();
    private final FileSystemStorageService storageService;

    @Autowired
    public ManageWikiCategoriesAndPages managerWiki;

    @Autowired
    public ListResultsController(FileSystemStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(Mappings.RESULTS)
    public String showResults(Model model) {

        try {
            //log.info("Start page: " + ConstantsParsingWiki.getStartPage());
            managerWiki.formListCategoryAtStartPage();
            model.addAttribute("nameOfResults", "Pages for " + ConstantsParsingWiki.getListCategories().get(0).getName());
            if (isStartPageWasChanged()) {
                listArticles = managerWiki.doQueryToWikiCategory();
            }
            model.addAttribute("listArticles", listArticles);
            return ViewNames.RESULTS;
        } catch (IOException e) {
            ConstantsParsingWiki.setErrorMessage(e.getMessage());
            return "redirect:/" + Mappings.ERROR;
        } catch (InterruptedException e) {
            ConstantsParsingWiki.setErrorMessage(e.getMessage());
            return "redirect:/" + Mappings.ERROR;
        } catch (IllegalArgumentException e) {
            ConstantsParsingWiki.setErrorMessage(e.getMessage());
            return "redirect:/" + Mappings.ERROR;
        }
    }

    @PostMapping(Mappings.RESULTS)
    public String saveResults(HttpServletRequest request) {

        try {
            if (request.getParameter("saveAsJson").equals("true")) {
                String fileName = storageService.getOutputLocation() + File.separator + ConstantsParsingWiki.getListCategories().get(0).getName().replaceAll(":", "_") + ".json";
                storageService.saveListArticleAsJson(listArticles, fileName);
            } else {
                String fileName = storageService.getOutputLocation() + File.separator + ConstantsParsingWiki.getListCategories().get(0).getName().replaceAll(":", "_") + ".csv";
                storageService.saveListArticleAsCSV(listArticles, fileName);
            }
        } catch (IOException e) {
            ConstantsParsingWiki.setErrorMessage(e.getMessage());
            return "redirect:/" + Mappings.ERROR;
        }
        return "redirect:/" + Mappings.SAVED_FILES;
    }
}
