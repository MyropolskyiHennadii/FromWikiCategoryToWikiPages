package places.storage;

import org.json.simple.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import places.configuration.ConstantsParsingWiki;
import places.wiki.Article;
import places.wiki.Category;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService {

    private final Path outputLocation = Paths.get("saveResults-dir");

    public Path getOutputLocation() {
        return outputLocation;
    }

    public Stream<Path> loadAllOutput() throws IOException {
        try {
            return Files.walk(getOutputLocation(), 1)
                    .filter(path -> !path.equals(getOutputLocation()))
                    .map(getOutputLocation()::relativize);
        } catch (IOException e) {
            ConstantsParsingWiki.setErrorMessage(e.getMessage());
            throw new IOException("Failed to read stored files", e);
        }
    }

    public Path loadOutput(String filename) {
        return outputLocation.resolve(filename);
    }

    public Resource loadAsResourceOutput(String filename) {
        try {
            Path file = loadOutput(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    /**
     * create file json
     *
     * @param listCategory
     * @param patToOutputFile
     * @throws IOException
     */
    public void saveListArticleAsJson(List<Category> listCategory, String patToOutputFile) throws IOException {

        Files.createDirectories(getOutputLocation());

        try (FileWriter file = new FileWriter(patToOutputFile)) {
            for (int i = 0; i < listCategory.size(); i++) {
                JSONObject categoryJsonObj = new JSONObject();
                categoryJsonObj.put("id", (i + 1));
                categoryJsonObj.put("lang", listCategory.get(i).getLang());
                categoryJsonObj.put("title", listCategory.get(i).getName());
                categoryJsonObj.put("reference", listCategory.get(i).getReference());
                categoryJsonObj.put("geodata", ((Article) listCategory.get(i)).getGeoData());
                file.write(categoryJsonObj.toJSONString());
            }
        }
    }

    public void saveListArticleAsCSV(List<Category> listCategory, String patToOutputFile) throws IOException {
        Files.createDirectories(getOutputLocation());

        try (FileWriter file = new FileWriter(patToOutputFile)) {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < listCategory.size(); i++) {
                String coordinates = ((Article) listCategory.get(i)).getGeoData().replaceAll("" + (char) (10), "");
                output.append((i + 1))
                        .append(";")
                        .append(listCategory.get(i).getLang())
                        .append(";")
                        .append(listCategory.get(i).getName())
                        .append(";")
                        .append(listCategory.get(i).getReference())
                        .append(";")
                        .append(coordinates)
                        //.append(",")
                        .append("\n");
            }
            file.write(output.toString());
        }
    }

    public void deleteAllFiles(){
        FileSystemUtils.deleteRecursively(getOutputLocation().toFile());
    }
}
