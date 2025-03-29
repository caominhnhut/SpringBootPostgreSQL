package com.sts.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

import com.sts.util.enums.UserStatus;

@Entity
@Table(name = "users")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity extends AuditMetaData implements Serializable {

    @Serial
    private static final long serialVersionUID = 45345343L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password", nullable = false)
    @ToString.Exclude
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;

}
