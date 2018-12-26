package org.wm.apilab.auth;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.wm.apilab.utils.JSONUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 432_000_000; // 5天
    static final String SECRET = "12321"; // JWT密码
    static final String TOKEN_PREFIX = "Bearer"; // Token前缀
    static final String HEADER_STRING = "Authorization";// 存放Token的Header Key

    public static void addAuthentication(HttpServletResponse response, String username,
            Collection<? extends GrantedAuthority> collectionList) {

        // TODO stream & Lambda
        collectionList.forEach(c -> System.out.println(c.getAuthority()));
        String collection =
                collectionList.stream().map(c -> c.getAuthority()).collect(Collectors.joining(","));

        String JWT = Jwts.builder()
                // 保存權限（角色）
                .claim("authorities", collection)
                // 用户名寫入標题
                .setSubject(username)
                // 有效期
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        // 將 JWT 寫入 body
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getOutputStream().println(JSONUtils.fillResultString(0, "", JWT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        // 從Header中拿到token
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 驗證sign
                    .setSigningKey(SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();

            // 拿用户名
            String user = claims.getSubject();
            request.setAttribute("userName", user);
            
            // 得到 權限（角色）
            List<GrantedAuthority> authorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList((String) claims.get("authorities"));

            // 回傳Token
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, authorities)
                    : null;
        }
        return null;
    }
}
