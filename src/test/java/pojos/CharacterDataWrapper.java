package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CharacterDataWrapper {

    @JsonProperty("code")
    public int code ;

    @JsonProperty("status")
    public String status ;

    @JsonProperty("copyright")
    public String copyright ;

    @JsonProperty("attributionText")
    public String attributionText ;

    @JsonProperty("attributionHTML")
    public String attributionHTML ;

    @JsonProperty("data")
    public CharacterDataContainer data;

    @JsonProperty("etag")
     public String etag ;
}
