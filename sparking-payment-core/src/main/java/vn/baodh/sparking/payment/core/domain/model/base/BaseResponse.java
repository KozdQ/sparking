package vn.baodh.sparking.payment.core.domain.model.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.baodh.sparking.payment.core.domain.enumeration.StatusEnum;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseResponse<T> extends BaseEntity {

  public int returnCode = 0;
  public String returnMessage;
  public T[] data;

  public void updateResponse(int statusCode) {
    this.returnCode = statusCode;
    this.returnMessage = StatusEnum.getStatusEnum(statusCode).getStatusMessage();
  }
}
