package net.proselyte.webfluxsecurity.rest;

import lombok.RequiredArgsConstructor;
import net.proselyte.webfluxsecurity.dto.AuthRequestDto;
import net.proselyte.webfluxsecurity.dto.AuthResponseDto;
import net.proselyte.webfluxsecurity.dto.UserDto;
import net.proselyte.webfluxsecurity.entity.UserEntity;
import net.proselyte.webfluxsecurity.mapper.UserMapper;
import net.proselyte.webfluxsecurity.security.CustomPrincipal;
import net.proselyte.webfluxsecurity.security.SecurityService;
import net.proselyte.webfluxsecurity.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthRestControllerV1 {
    private final SecurityService securityService;
    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping("/register")
    public Mono<UserDto> register(@RequestBody UserDto dto) {
        UserEntity entity = userMapper.map(dto);
        return userService.registerUser(entity)
                .map(userMapper::map);
    }

    @PostMapping("/login")
    public Mono<AuthResponseDto> login(@RequestBody AuthRequestDto dto) {
        return securityService.authenticate(dto.getUsername(), dto.getPassword())
                .flatMap(tokenDetails -> Mono.just(
                        AuthResponseDto.builder()
                                .userId(tokenDetails.getUserId())
                                .token(tokenDetails.getToken())
                                .issuedAt(tokenDetails.getIssuedAt())
                                .expiresAt(tokenDetails.getExpiresAt())
                                .build()
                ));
    }

    @GetMapping("/info")
    public Mono<UserDto> getUserInfo(Authentication authentication) {
        CustomPrincipal customPrincipal = (CustomPrincipal) authentication.getPrincipal();

        return userService.getUserById(customPrincipal.getId())
                .map(userMapper::map);
    }
}
