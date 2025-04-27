package com.Tech.Project.Service;

import com.Tech.Project.Dto.LoginRequest;
import com.Tech.Project.Dto.ResponseDto;
import com.Tech.Project.Dto.SignInResponseDTO;
import com.Tech.Project.Dto.SignUpRequest;
import com.Tech.Project.Entity.User;
import com.Tech.Project.Repository.UserRepository;
import com.Tech.Project.Utils.Constants;
import com.Tech.Project.Utils.JwtUtil;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    public ResponseDto createUser(User user) {
        return new ResponseDto(Constants.CREATED, this.userRepository.save(user), HttpStatus.CREATED.value());
    }

    public ResponseDto getUserById(String id) throws BadRequestException {
        Optional<User> user = Optional.ofNullable(this.userRepository.findById(id).orElseThrow(() -> new BadRequestException("User not found..")));
        return new ResponseDto(Constants.RETRIEVED, user, HttpStatus.OK.value());
    }

    public ResponseDto getUserByEmail(String email) throws BadRequestException {
        Optional<User> user = Optional.ofNullable(this.userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("User Not found..")));
        return new ResponseDto(Constants.RETRIEVED, user, HttpStatus.OK.value());
    }

    public ResponseDto signup(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUserName(signUpRequest.getUserName());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(signUpRequest.getRole());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return new ResponseDto(Constants.CREATED, this.userRepository.save(user), HttpStatus.CREATED.value());
    }

    public ResponseDto login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
        );
        User userDetails= this.userRepository.findByEmail(loginRequest.getUserName()).orElseThrow(()->new BadCredentialsException("User Not found..."));
        String userId = userDetails.getId();
        String token = jwtUtil.generateToken(loginRequest.getUserName());
        String refreshToken = this.jwtUtil.generateRefreshToken(userId);
        return ResponseDto.builder()
                .message(Constants.ACTIVATED)
                .statusCode(HttpStatus.CREATED.value())
                .data(SignInResponseDTO.builder()
                        .token(token)
                        .refreshToken(refreshToken)
                        .expiresIn(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                        .build()
                )
                .build();
    }

    public ResponseDto retriveUser() {
        return new ResponseDto(Constants.RETRIEVED,this.userRepository.findAll(),HttpStatus.OK.value());
    }
}
