package vn.baodh.sparking.merchant.core.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QrTokenModel {
  private String qrToken;
}
