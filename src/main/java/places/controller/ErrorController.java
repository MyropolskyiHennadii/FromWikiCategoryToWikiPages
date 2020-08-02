package places.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import places.configuration.ConstantsParsingWiki;
import places.util.Mappings;
import places.util.ViewNames;

@Controller
public class ErrorController {

    @GetMapping(Mappings.ERROR)
    public String showError(Model model) {
        model.addAttribute("errorMessage", ConstantsParsingWiki.getErrorMessage());
        return ViewNames.ERROR;
    }

}
