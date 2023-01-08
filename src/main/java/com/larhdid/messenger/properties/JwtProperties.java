package com.larhdid.messenger.properties;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.jwt")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class JwtProperties {
    private String secretKey;
    private String tokenPrefix;
    private Integer accessTokenPeriod;
    private Integer refreshTokenPeriod;

    public Algorithm getAlgorithm (){
        return Algorithm.HMAC256(this.secretKey.getBytes());
    }

}