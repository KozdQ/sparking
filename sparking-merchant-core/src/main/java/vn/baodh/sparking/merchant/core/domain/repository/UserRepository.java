package vn.baodh.sparking.merchant.core.domain.repository;

import java.util.List;
import vn.baodh.sparking.merchant.core.domain.model.UserModel;

public interface UserRepository {

  boolean create(UserModel entity) throws Exception;

  boolean update(UserModel entity);

  List<UserModel> getUserByPhone(String phone) throws Exception;

  String getUserIdByPhone(String phone) throws Exception;
}
