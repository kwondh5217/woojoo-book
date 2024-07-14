package com.ssafy11.woojoobook;

import com.ssafy11.woojoobook.infra.config.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class IndexController {

    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @GetMapping("/jwt")
    public ResponseEntity<String> createToken(){
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("ssafy", "ssafy");
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String token = jwtProvider.createToken(authenticate);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        return ResponseEntity.ok().headers(headers).body(token);
    }

    @GetMapping("/auth")
    public ResponseEntity<String> getAuth(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(auth.getName());
    }
}
