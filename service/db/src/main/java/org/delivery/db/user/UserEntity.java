package org.delivery.db.user;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.user.enums.UserStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50, nullable = false)
    private UserStatus status;

    @Column(name = "address", length = 150, nullable = false)
    private String address;

    @Column(name = "registeredAt")
    private LocalDateTime registeredAt;

    @Column(name = "unregisteredAt")
    private LocalDateTime unregisteredAt;

    @Column(name = "lastLoginAt")
    private LocalDateTime lastLoginAt;

}
