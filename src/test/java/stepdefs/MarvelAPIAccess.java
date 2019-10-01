package stepdefs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojos.Character;
import pojos.CharacterDataWrapper;
import utilities.ConfigProperties;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class MarvelAPIAccess {
    private final static Logger log = LoggerFactory.getLogger(MarvelAPIAccess.class);

    private static final String LBL_MARVEL_GATEWAY = ConfigProperties.getInstance().getProperty("marvel.gateway.url");
    private static final String LBL_MARVEL_API_PUBLIC_KEY = ConfigProperties.getInstance().getProperty("marvel.user.public.apikey");
    private static final String LBL_MARVEL_API_PRIVATE_KEY = ConfigProperties.getInstance().getProperty("marvel.user.private.apikey");
    private static final String LBL_MARVEL_API_CHARACTER_MANDATORY_PROPERTIES = ConfigProperties.getInstance().getProperty("marvel.api.characters.get.mandatory.attributes");
    private static final Path testJSONFilePath = Paths.get("src", "test", "resources");
    private static final String testJSONFileName = "allchar.json";


    public String getMD5Hash(String timeStamp) {
        String md5HashKey = DigestUtils.md5Hex(timeStamp + LBL_MARVEL_API_PRIVATE_KEY + LBL_MARVEL_API_PUBLIC_KEY);
        log.debug(" hashkey [{}] timeStamp [{}] APIPUBKEY [{}] APIPRIKEY [{}]", md5HashKey, timeStamp, LBL_MARVEL_API_PUBLIC_KEY, LBL_MARVEL_API_PRIVATE_KEY);
        return md5HashKey;
    }

    public List<String> getAttributesForVerification(String inputString) {
        return Collections.list(new StringTokenizer(inputString, ",")).stream()
                .map(token -> ((String) token).replace("\"", ""))
                .collect(Collectors.toList());
    }

    public MarvelAPIAccess(){}
    public List<String> getCharacterInformation(String characterName) {
        List<String> jsonAttributesActual = new ArrayList<String>();
        RestAssured.baseURI = LBL_MARVEL_GATEWAY;
        RestAssured.basePath = ConfigProperties.getInstance().getProperty("marvel.api");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY);

        log.debug(" RestAssured baseURI -> [{}]   basePath -> [{}] characterName [{}] ", RestAssured.baseURI, RestAssured.basePath,characterName);

        String timeStamp = Instant.now().toString();
        String hashKey = getMD5Hash(timeStamp);
        String userApiKey = LBL_MARVEL_API_PUBLIC_KEY;

        Response httpDownloadedResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .queryParam("name", characterName)
                .queryParam("ts", timeStamp)
                .queryParam("apikey", userApiKey)
                .queryParam("hash", hashKey)
                .get();

        int statusHttpDownloadedResponse = httpDownloadedResponse.statusCode();

        log.debug(" Response code [{}] ", statusHttpDownloadedResponse);

        ResponseBody responseBody = httpDownloadedResponse.getBody();

        try {
            CharacterDataWrapper cdc = objectMapper.readValue(responseBody.prettyPrint(), CharacterDataWrapper.class);

            Character[] arrayOfCharacters = cdc.getData().getResults();

            for (Character character : arrayOfCharacters) {
                Class aClass = character.getClass();
                Field[] jsonAttributesInCharacterClass = aClass.getDeclaredFields();
                for (Field fieldInCharacterClass : jsonAttributesInCharacterClass) {
                    jsonAttributesActual.add(fieldInCharacterClass.getName());
                }

            }
            return jsonAttributesActual;
        } catch (JsonProcessingException e) {
            log.error(" Error While Processing Response received from RESt API [{}] ", ExceptionUtils.getStackTrace(e));
        }
        return jsonAttributesActual;
    }
}




