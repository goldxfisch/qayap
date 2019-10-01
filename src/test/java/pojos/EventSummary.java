package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class EventSummary {

    @JsonProperty("resourceURI")
    public String resourceURI;

    @JsonProperty("name")
    public String name;

}
