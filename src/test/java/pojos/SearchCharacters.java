package pojos;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SearchCharacters {
    public String name;
    public String nameStartWith;

    public String modifiedSince;
    public String id;
    public String description;
    public String modified;
    public String resourceURI;
        public String thumbnail;
        public String comics;
        public String stories;
        public String events;
        public String urls;
        public int offset;
        public int limit;

}
