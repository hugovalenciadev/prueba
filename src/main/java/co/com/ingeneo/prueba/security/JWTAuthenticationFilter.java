package co.com.ingeneo.prueba.security;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import co.com.ingeneo.prueba.entities.User;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/login"); 
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
       
       User creds = null;
       try {
          creds = new ObjectMapper().readValue(req.getInputStream(), User.class);
       } catch (Exception e) {
           //ignore
       }
    
       if (Objects.isNull(creds)) {
         throw new BadCredentialsException("Badcredentials");
       }

        UsernamePasswordAuthenticationToken authRequest 
            = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword());
        //setDetails(request, authRequest);
        return this.authenticationManager.authenticate(authRequest);
        
    }

    public String generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
               .map(GrantedAuthority::getAuthority)
               .collect(Collectors.joining(","));

        return JWT.create()
                .withSubject(authentication.getName())
                .withClaim("AUTHORITIES", authorities)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 900_000))
                .sign(Algorithm.HMAC512("SECRET_KEY".getBytes()));
   }
   
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        res.getWriter().write(generateToken(auth));
        res.getWriter().flush();
    }
}
