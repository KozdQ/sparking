package vn.baodh.sparking.parking.core.domain.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QrModel implements Serializable {

  private String socketKey;
  private QrType type;
  private long timestamp;
  private String userPhone;
  private String vehicleId = "";
  private String voucherId = "";
}
