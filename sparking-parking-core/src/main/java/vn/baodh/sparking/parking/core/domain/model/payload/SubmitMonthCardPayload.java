package vn.baodh.sparking.parking.core.domain.model.payload;

import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;
import vn.baodh.sparking.parking.core.domain.model.PayLoad;

@Data
@Accessors(chain = true)
public class SubmitMonthCardPayload implements PayLoad {

  private String phone;
  private String locationId;
  private String price;
  private String number;

  private String deviceId;
  private String deviceModel;
  private String appVersion;

  public SubmitMonthCardPayload getPayLoadInfo(Map<String, ?> params) {
    SubmitMonthCardPayload payload = new SubmitMonthCardPayload();
    payload.setPhone((String) params.get("phone"));
    payload.setLocationId((String) params.get("location_id"));
    payload.setPrice((String) params.get("price"));
    payload.setNumber((String) params.get("number"));

    payload.setDeviceId((String) params.get("device_id"));
    payload.setDeviceModel((String) params.get("device_model"));
    payload.setAppVersion((String) params.get("app_version"));
    return payload;
  }

  // TODO
  public boolean validatePayload() {
    return this.getPhone() != null && this.getPhone().matches("^\\d{1,20}$") &&
        this.getLocationId() != null && this.getLocationId().matches("^\\d{1,20}$") &&
        this.getPrice() != null && !this.getPrice().equals("") &&
        this.getNumber() != null && !this.getNumber().equals("");
  }
}
