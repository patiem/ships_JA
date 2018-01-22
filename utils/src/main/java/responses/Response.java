package responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.ShipModel;
import model.Shot;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = HitResponse.class, name = "HitResponse"),
    @JsonSubTypes.Type(value = LossResponse.class, name = "LossResponse"),
    @JsonSubTypes.Type(value = MissedResponse.class, name = "MissedResponse"),
    @JsonSubTypes.Type(value = OpponentHitResponse.class, name = "OpponentHitResponse"),
    @JsonSubTypes.Type(value = OpponentMissedResponse.class, name = "OpponentMissedResponse"),
    @JsonSubTypes.Type(value = PlayResponse.class, name = "PlayResponse"),
    @JsonSubTypes.Type(value = WinResponse.class, name = "WinResponse"),
    @JsonSubTypes.Type(value = SunkResponse.class, name = "SunkResponse"),
    @JsonSubTypes.Type(value = HitAgainResponse.class, name = "HitAgainResponse")}
    )
public interface Response {
  ResponseHeader getHeader();

  @JsonProperty("shot")
  default Optional<Shot> getShot() {
    return Optional.empty();
  }

  @JsonProperty("sunkShip")
  default Optional<ShipModel> getSunkShip() {
    return Optional.empty();
  }
}
