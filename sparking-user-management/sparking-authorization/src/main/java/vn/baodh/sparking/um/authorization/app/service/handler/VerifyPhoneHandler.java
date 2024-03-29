package vn.baodh.sparking.um.authorization.app.service.handler;

import java.util.Base64;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.baodh.sparking.um.authorization.app.service.FlowHandler;
import vn.baodh.sparking.um.authorization.domain.enumeration.StatusEnum;
import vn.baodh.sparking.um.authorization.domain.model.base.BaseRequestInfo;
import vn.baodh.sparking.um.authorization.domain.model.base.BaseResponse;
import vn.baodh.sparking.um.authorization.domain.model.TokenModel;
import vn.baodh.sparking.um.authorization.domain.model.UserModel;
import vn.baodh.sparking.um.authorization.domain.model.payload.VerifyPhonePayLoad;
import vn.baodh.sparking.um.authorization.infra.jdbc.master.JdbcUserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class VerifyPhoneHandler implements FlowHandler {

  private final JdbcUserRepository userRepository;
  private final String DEFAULT_OTP = "111111";

  @Override
  public BaseResponse<?> handle(BaseRequestInfo<?> baseRequestInfo) {
    BaseResponse<TokenModel> response = new BaseResponse<>();
    try {
      VerifyPhonePayLoad payload = new VerifyPhonePayLoad().getPayLoadInfo(
          baseRequestInfo.getParams());
      if (payload.validatePayload()) {
        String token = payload.getPhone();
        UserModel[] userModels = userRepository.getUserByPhone(payload.getPhone(), true)
            .toArray(new UserModel[0]);
        if (userModels.length == 0) {
          response.updateResponse(StatusEnum.USER_NOT_FOUND.getStatusCode());
        } else if (!Objects.equals(payload.getOtp(), DEFAULT_OTP)) {
          response.data = new TokenModel[]{
              new TokenModel().setNeedOtp(true).setAccessToken(null)
          };
          response.updateResponse(StatusEnum.WRONG_OTP.getStatusCode());
        } else {
          // TODO: check is new
          UserModel user = userModels[0];
          response.data = new TokenModel[]{
              new TokenModel().setNeedOtp(false).setAccessToken(
                  Base64.getEncoder().encodeToString(token.getBytes()))
          };
          response.updateResponse(StatusEnum.SUCCESS.getStatusCode());
        }
      } else {
        response.updateResponse(StatusEnum.INVALID_PARAMETER.getStatusCode());
      }
      log.info("[VerifyPhoneHandler] Finish handle request: {}, response: {}",
          baseRequestInfo, response);
      return response;
    } catch (Exception exception) {
      response.updateResponse(StatusEnum.EXCEPTION.getStatusCode());
      log.error(
          "[VerifyPhoneHandler] Handler handle >exception< with request: {}, response: {}, ",
          baseRequestInfo, response, exception);
      return response;
    }
  }
}
