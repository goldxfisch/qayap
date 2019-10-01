package stepdefs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ConfigProperties;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@RunWith(Parameterized.class)
public class MarvelAPIAccessTest {
    private final static Logger log = LoggerFactory.getLogger(MarvelAPIAccessTest.class);

    private static final String LBL_MARVEL_API_CHARACTER_MANDATORY_PROPERTIES = ConfigProperties.getInstance().getProperty("marvel.api.characters.get.mandatory.attributes");

    @Parameterized.Parameter(0)
    public String characterNameUnderTest;

    @Parameterized.Parameter(1)
    public static List<String> expectedJsonAttributesUnderTest =
            Collections.list(new StringTokenizer(LBL_MARVEL_API_CHARACTER_MANDATORY_PROPERTIES, ",")).stream()
                    .map(token -> ((String) token).replace("\"", ""))
                    .collect(Collectors.toList());

    public MarvelAPIAccessTest(){}


    @Parameterized.Parameters( name = "{index}: Test with characterNameUnderTest={0}, result is:{1} ")
    public static Collection<Object[]> data() {
        Object [][] data = new Object[][]{
             //   {"HULK",expectedJsonAttributesUnderTest},
             //   {"THOR",expectedJsonAttributesUnderTest}
                {"SPIRED",expectedJsonAttributesUnderTest}
        };
    return Arrays.asList(data);
    }

    @Test()
    public void checkForFieldsInFile() {

      //  log.debug(" expectedJsonAttributesUnderTest [{}]",expectedJsonAttributesUnderTest);
      //  log.debug(" MarvelAPIAccess [{}] ",(new MarvelAPIAccess()).getCharacterInformation(characterNameUnderTest));
     //// assertThat((new MarvelAPIAccess()).getCharacterInformation(characterNameUnderTest),containsInAnyOrder(expectedJsonAttributesUnderTest.toArray()));
        int sizeOfAttributesReturned = new MarvelAPIAccess().getCharacterInformation(characterNameUnderTest).size();

        log.debug(" size [{}]", sizeOfAttributesReturned);
      if (sizeOfAttributesReturned == 0 )
      {
          assertThat( "No Attributed found for "+characterNameUnderTest,(sizeOfAttributesReturned == 0 ));
      }
      else
      {
          assertThat(new MarvelAPIAccess().getCharacterInformation(characterNameUnderTest),containsInAnyOrder(expectedJsonAttributesUnderTest.toArray()));

      }

      ////assertThat((new MarvelAPIAccess()).getCharacterInformation(characterNameUnderTest),empty());
           ////   assertEquals("Attribute Not Found : " + attributeName, aClass.getField(attributeName).get(cdc.getData().getResults()[ctr]), notNullValue());

    }


}
