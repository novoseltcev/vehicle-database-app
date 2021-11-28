package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class AboutController extends Controller {
    @FXML
    private ImageView avatarImg;

    @FXML
    private Label nameLabel;

    @FXML
    private Label loginLabel;


    @FXML
    private Label reposLabel;

    @FXML
    private Label gistsLabel;

    @FXML
    private Label followersLabel;

    @FXML
    private Label bioLabel;

    @FXML
    private Label companyLabel;

    @FXML
    private Label locationLabel;


    @Override
    protected void setLang() {}

    @FXML
    public void initialize() throws Exception {
        try {
            URL githubApi = new URL("https://api.github.com/users/st-a-novoseltcev");
            Scanner scanner = new Scanner(githubApi.openStream());
            String response = scanner.useDelimiter("\\Z").next();
            scanner.close();

            for (String i: response.split("\","))
                System.out.println(i);

            Map<String, String> json = parseJSON(response);
            System.out.println(json);

            URL avatarURL = new URL(json.get("avatar_url"));
            Image avatar = new Image(avatarURL.toString());
            avatarImg.setSmooth(true);
            avatarImg.setImage(avatar);

            loginLabel.setText(json.get("login"));
            nameLabel.setText(json.get("name"));

            gistsLabel.setText(json.get("public_gists"));
            reposLabel.setText(json.get("public_repos"));
            followersLabel.setText(json.get("followers"));

            bioLabel.setText(json.get("bio"));
            companyLabel.setText(json.get("company"));
            locationLabel.setText(json.get("location"));

        } catch (IOException e) {
            Thread.sleep(3 * 1000);
            initialize();
        }
    }

    private Map<String, String> parseJSON(String json) {
        HashMap<String, String> result = new HashMap<>();
        String normalizeJson = json.replaceAll("\\{\"", "").replaceAll("\"}", "");
        String[] newJson = normalizeJson.split(",\"");
        for (String str: newJson) {
            String[] split = str.split("\":");
            result.put(split[0], split[1].replaceAll("\"", ""));
        }
        return result;
    }
}
