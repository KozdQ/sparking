package vn.baodh.sparking.um.authorization.domain.model.payload;

import java.util.Map;
import lombok.Data;
import vn.baodh.sparking.um.authorization.domain.model.PayLoad;

@Data
public class SignUpPayload implements PayLoad {

  private String phone;
  private String pin;
  private String fullName;
  private String gender;
  private String birthday;
  private String email;
  private String imageUrl;

  private String deviceId;
  private String deviceModel;
  private String appVersion;

  public SignUpPayload getPayLoadInfo(Map<String, ?> params) {
    SignUpPayload payload = new SignUpPayload();
    payload.setPhone((String) params.get("phone"));
    payload.setPin((String) params.get("pin"));
    payload.setFullName((String) params.get("fullname"));
    payload.setGender((String) params.get("gender"));
    payload.setBirthday((String) params.get("birthday"));
    payload.setEmail((String) params.get("email"));
    payload.setImageUrl((String) params.get("image_url"));

    payload.setDeviceId((String) params.get("device_id"));
    payload.setDeviceModel((String) params.get("device_model"));
    payload.setAppVersion((String) params.get("app_version"));
    return payload;
  }

  // TODO: need update
  public boolean validatePayload() {
    return this.getPhone() != null && this.getPhone().matches("^\\d{1,20}$")
        && this.getPin() != null && this.getPin().matches("^\\d{6}$")
        && this.getFullName() != null && this.getFullName().matches(".+")
        && this.getGender() != null && this.getGender().matches("^(Male|Female|Indefinite)$");
  }
}
