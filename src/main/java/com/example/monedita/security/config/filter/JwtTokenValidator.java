package com.example.monedita.security.config.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.monedita.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class JwtTokenValidator extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    //constructor de mi clase que revise el jwtUtils
    public JwtTokenValidator(JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }

    //estos parametros que trae por default no deben de ser nulos
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        //traigo los datos del request de la autorizacion de sesion/token
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(jwtToken != null){

            //quitamos la palabra bearer que viene antes del token
            //bearer MiTokenAqui2134190ASDMKASDL;AS
            jwtToken = jwtToken.substring(7);

            //validar que el token sea correcto
            DecodedJWT decodedJWT = jwtUtils.verifyToken(jwtToken);
            
            //si el token es valido le permitimos el acceso
            //traemos el usuario
            String username = jwtUtils.getUsernameFromToken(decodedJWT);
            //me devuele los authorities, claim del token, y lo pasamos a string
            String authorities = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();


            //Lista de authoridades
            //toma una cadena de caracteres separadas por comas y pasalo a una authorityList
            Collection<? extends GrantedAuthority> authotiesList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

            //traemos nuestro security context holder actual, y lo guardo en la variable
            SecurityContext context = SecurityContextHolder.getContext();

            //seteamos datos
            //creamos la instancia de authentication y le pasamos el usuario, y la lista de permisos
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authotiesList);
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

        }

        //Si no viene el token, va al siguiente filtro
        //Si no viene el token esto arroja error
        //Importar el throw de doFilter
        filterChain.doFilter(request, response);
    }
}
