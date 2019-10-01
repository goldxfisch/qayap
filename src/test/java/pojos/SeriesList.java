package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SeriesList {

    @JsonProperty("available")
    int available;

    @JsonProperty("returned")
    int returned ; //upto 20

    @JsonProperty("collectionURI")
    public String collectionURI ;

   @JsonProperty("items")
    SeriesSummary[] items ;
}
