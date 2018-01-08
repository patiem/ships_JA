package responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = HitResponse.class, name = "HitResponse"),
    @JsonSubTypes.Type(value = LossResponse.class, name = "LossResponse"),
    @JsonSubTypes.Type(value = MissedResponse.class, name = "MissedResponse"),
    @JsonSubTypes.Type(value = OpponentHitResponse.class, name = "OpponentHitResponse"),
    @JsonSubTypes.Type(value = OpponentMissedResponse.class, name = "OpponentMissedResponse"),
    @JsonSubTypes.Type(value = PlayResponse.class, name = "PlayResponse"),
    @JsonSubTypes.Type(value = WinResponse.class, name = "WinResponse")}
)
public abstract class Response {
  public abstract ResponseHeader getHeader();
}
