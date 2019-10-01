package pojos;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class APIResults {
    public int code;
    public String status;
    public String eTag;
    
}
