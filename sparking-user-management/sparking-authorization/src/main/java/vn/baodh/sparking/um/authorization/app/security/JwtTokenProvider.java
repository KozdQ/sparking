package vn.baodh.sparking.um.authorization.app.security;


import java.util.Date;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.baodh.sparking.um.authorization.domain.model.UserModel;

@Slf4j
@Component
public class JwtTokenProvider {
  // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
  private final String JWT_SECRET = "sparking-private-key";

  //Thời gian có hiệu lực của chuỗi jwt
  private final long JWT_EXPIRATION = 604800000L;

  // Tạo ra jwt từ thông tin user
  public String generateToken(UserModel userDetails) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
    // Tạo chuỗi json web token từ id của user.
    return Jwts.builder()
        .setSubject(userDetails.getUserId())
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
        .compact();
  }

  // Lấy thông tin user từ jwt
  public String getUserIdFromJWT(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(JWT_SECRET)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject();
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
      return true;
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty.");
    }
    return false;
  }

  public void any() {
    log.info(generateToken(new UserModel()));
    log.info(String.valueOf(validateToken("")));
    log.info(getUserIdFromJWT(""));
  }
}
