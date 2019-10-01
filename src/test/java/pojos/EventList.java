package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class EventList {
    @JsonProperty("available")
    public int available;
    @JsonProperty("returned")
    public int returned;
    @JsonProperty("collectionURI")
    public String collectionURI;
    @JsonProperty("items")
    public EventSummary[] items;
}
