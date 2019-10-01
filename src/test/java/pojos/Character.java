package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)

public class Character {

    @JsonProperty("id")
   public int id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("description")
     public String description;

    @JsonProperty("modified")
    public String modified;

    @JsonProperty("resourceURI")
    public String resourceURI;

    @JsonProperty("urls")
    public Url[] urls;

    @JsonProperty("thumbnail")
    public Image thumbnail;

    @JsonProperty("comics")
    public ComicList comics;

    @JsonProperty("stories")
    public StoryList stories;

    @JsonProperty("events")
    public EventList events;

    @JsonProperty("series")
    public SeriesList series;
}
