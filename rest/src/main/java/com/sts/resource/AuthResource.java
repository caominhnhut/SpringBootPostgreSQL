package com.sts.resource;

import static com.sts.util.ErrorCode.OTP_INVALID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sts.dto.authentication.request.AuthenticationRequest;
import com.sts.dto.otp.request.OtpRequest;
import com.sts.dto.register.request.SignupRequest;
import com.sts.exception.Error;
import com.sts.mapper.UserResourceMapper;
import com.sts.model.ResponseData;
import com.sts.service.otp.OtpService;
import com.sts.service.user.UserDetailsImpl;
import com.sts.service.user.UserService;
import com.sts.util.JwtUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthResource {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final OtpService otpService;

    private final JwtUtil jwtUtils;

    private final UserResourceMapper userRequestMapper;

    @PutMapping("/otp")
    public ResponseEntity<ResponseData<Boolean>> generateOtp(@Valid @RequestBody OtpRequest otpRequest) {

        otpService.generateOtp(otpRequest.getPhoneNumber());

        var responseData = new ResponseData<Boolean>();
        responseData.setData(Boolean.TRUE);

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseData<Long>> register(@Valid @RequestBody SignupRequest signUpRequest) {

        log.info("User registration: {}", signUpRequest);
        var responseData = new ResponseData<Long>();
        if (!otpService.validateOtp(signUpRequest.getPhoneNumber(), signUpRequest.getOtp())) {
            responseData.setError(Error.builder()
                    .code(OTP_INVALID.getCode())
                    .message(OTP_INVALID.getMessage())
                    .build());
            return ResponseEntity.badRequest().body(responseData);
        }

        var user = userRequestMapper.toUser(signUpRequest);

        var userId = userService.register(user);
        responseData.setData(userId);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseData<String>> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getPhoneNumber(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var userDetails = (UserDetailsImpl) authentication.getPrincipal();

        var responseData = new ResponseData<String>();
        responseData.setData(jwtUtils.generateToken(userDetails));

        return ResponseEntity.ok().body(responseData);
    }

        @PostMapping("/sign-out")
        public ResponseEntity<ResponseData<String>> logout() {
            var responseData = new ResponseData<String>();
            responseData.setData("You've been signed out!");
            return ResponseEntity.ok(responseData);
        }
}
