package places.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import places.configuration.ConstantsParsingWiki;
import places.storage.FileSystemStorageService;
import places.util.Mappings;
import places.util.ViewNames;
import places.wiki.ManageWikiCategoriesAndPages;

import java.io.IOException;
import java.util.stream.Collectors;

import static places.configuration.ConstantsParsingWiki.setStartPageWasChanged;

@Controller
public class ListFilesController {

    private static final Logger log = LogManager.getLogger(ManageWikiCategoriesAndPages.class);
    private final FileSystemStorageService storageService;

    @Autowired
    public ListFilesController(FileSystemStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResourceOutput(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping(Mappings.SAVED_FILES)
    public String showSavedFiles(Model model) {
        setStartPageWasChanged(false);//for return to previous page without recalculation
        //file with results
        try {
            model.addAttribute("files", storageService.loadAllOutput().map(
                    path -> MvcUriComponentsBuilder.fromMethodName(ListFilesController.class,
                            "serveFile", path.getFileName().toString()).build().toUri().toString())
                    .collect(Collectors.toList()));

        } catch (IOException e) {
            ConstantsParsingWiki.setErrorMessage(e.getMessage());
            log.error(e.getMessage());
            return "redirect:/" + Mappings.ERROR;
        }

        return ViewNames.SAVED_FILES;
    }

}
