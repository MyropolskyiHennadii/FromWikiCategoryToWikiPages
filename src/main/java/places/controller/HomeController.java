package places.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import places.configuration.ConstantsParsingWiki;
import places.util.Mappings;
import places.util.ViewNames;
import places.wiki.ManageWikiCategoriesAndPages;

@Controller
public class HomeController {

    // == handler methods ==
    // http://localhost:8080/...(Mappings.HOME)
    @GetMapping(Mappings.HOME)
    public String home() {
        return ViewNames.HOME;
    }


    @PostMapping(Mappings.HOME)
    public String goToResults(String startPage) {
        ManageWikiCategoriesAndPages.clearAll();
        if (startPage.trim().isEmpty()) {
            ConstantsParsingWiki.setErrorMessage("Reference to wiki-page can't be empty!");
            return "redirect:/" + Mappings.ERROR;
        }

        ConstantsParsingWiki.setStartPage(startPage.trim());
        ConstantsParsingWiki.setStartPageWasChanged(true);
        return "redirect:/" + Mappings.RESULTS;
    }

}
