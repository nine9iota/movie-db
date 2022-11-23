package ch.bbw.moviemongodb;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ViewController {
    String connectionString = "mongodb://root:1234@localhost:27017";
    String mongoDB = "morroldb";
    com.mongodb.client.MongoDatabase mongoDBCon;

    MongoClient mongoClient;
    ViewController() {
        mongoDBCon = MongoClients.create(connectionString).getDatabase(mongoDB);
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("peoples", mongoDBCon.getCollection("people").find());
        model.addAttribute("studios", mongoDBCon.getCollection("studio").find());
        model.addAttribute("movies", mongoDBCon.getCollection("movies").find());
        return "Home";
    }

    @PostMapping("/people")
    public String addPeople(Model model, @RequestBody MultiValueMap<String, String> formData) {
        Document data = new Document() {{
            append("first_name", formData.get("first_name").get(0));
            append("last_name", formData.get("last_name").get(0));
            append("birth_date", formData.get("birth_date").get(0));
        }};
        mongoDBCon.getCollection("people").insertOne(data);
        return "redirect:/";
    }

    @GetMapping("/people")
    public String delPeople(@RequestParam MultiValueMap<String, String> formData) {
        mongoDBCon.getCollection("people").deleteOne(new Document("_id", new ObjectId(formData.get("id").get(0))));
        return "redirect:/";
    }

    @PostMapping("/studio")
    public String addStudio(@RequestBody MultiValueMap<String, String> formData) {
        Document data = new Document() {{
            append("name", formData.get("name").get(0));
            append("year_founded", formData.get("year_founded").get(0));
            append("movies", new ArrayList<String>());
            append("headquarters", new Document(){{
                append("address", formData.get("address").get(0));
                append("city", formData.get("city").get(0));
                append("state", formData.get("state").get(0));
                append("country", formData.get("country").get(0));
            }});
        }};
        mongoDBCon.getCollection("studio").insertOne(data);
        return "redirect:/";
    }

    @GetMapping("/studio")
    public String delStudio(@RequestParam MultiValueMap<String, String> formData) {
        mongoDBCon.getCollection("studio").deleteOne(new Document("_id", new ObjectId(formData.get("id").get(0))));
        return "redirect:/";
    }

    @PostMapping("/movie")
    public String addMovie(@RequestBody MultiValueMap<String, String> formData) {
        Document data = new Document() {{
            append("title", formData.get("title").get(0));
            append("plot", formData.get("plot").get(0));
            append("release_year", formData.get("release_year").get(0));
            append("actors", new ArrayList<String>());
            append("imdb", new Document(){{
                append("rating", formData.get("imdb_rating").get(0));
                append("yotes", formData.get("imdb_yotes").get(0));
                append("url", formData.get("imdb_url").get(0));
            }});
        }};
        mongoDBCon.getCollection("movies").insertOne(data);
        return "redirect:/";
    }

    @GetMapping("/movie")
    public String delMovie(@RequestParam MultiValueMap<String, String> formData) {
        mongoDBCon.getCollection("movies").deleteOne(new Document("_id", new ObjectId(formData.get("id").get(0))));
        return "redirect:/";
    }
}
