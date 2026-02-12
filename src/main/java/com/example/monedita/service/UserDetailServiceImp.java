package com.example.monedita.service;

import com.example.monedita.dto.AuthLoginRequestDTO;
import com.example.monedita.dto.AuthResponseDTO;
import com.example.monedita.entity.UserSec;
import com.example.monedita.repository.IUserRepository;
import com.example.monedita.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSec userSec = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " Not found"));


        //create list for get authorities
        //SimpleGrantedAuthority es la clase de ss para manejar los permisos
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userSec.getRoleList().stream().flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));

        //retornar en formato del spring security
        return new User(
                userSec.getUsername(),
                userSec.getPassword(),
                userSec.isEnabled(),
                userSec.isAccountNonExpired(),
                userSec.isCredentialsNonExpired(),
                userSec.isAccountNonLocked(),
                authorityList);
    }

    private Authentication authentication(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails == null) {
            throw new BadCredentialsException("Bad credentials");
            //si no coincide la password en txt con la de la base de datos lanza un throw
        }else if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }


    //method login
    public AuthResponseDTO login(AuthLoginRequestDTO authLoginRequestDTO) {
        String username = authLoginRequestDTO.username();
        String password = authLoginRequestDTO.password();

        Authentication authentication = authentication(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //create token
        String accessToken = jwtUtils.generateToken(authentication);
        //response
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(username, "Login successful", accessToken, true);
        return authResponseDTO;

    }

}
