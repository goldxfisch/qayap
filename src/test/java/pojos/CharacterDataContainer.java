package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CharacterDataContainer {


    @JsonProperty("offset")
     public int offset ;

    @JsonProperty("limit")
    public int limit ;

    @JsonProperty("total")
    public int total ;

    @JsonProperty("count")
   public  int count ;

    @JsonProperty("results")
    public Character[] results ;
}
