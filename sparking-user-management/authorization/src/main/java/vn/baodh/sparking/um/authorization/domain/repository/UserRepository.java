package vn.baodh.sparking.um.authorization.domain.repository;

import java.util.List;
import vn.baodh.sparking.um.authorization.domain.model.UserModel;

public interface UserRepository {

  UserModel create(UserModel entity) throws Exception;
  boolean update(UserModel entity) throws Exception;
  List<UserModel> getUserByPhone(String phone, boolean isSecure) throws Exception;
}
