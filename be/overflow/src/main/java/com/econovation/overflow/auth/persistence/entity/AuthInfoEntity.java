package com.econovation.overflow.auth.persistence.entity;

import static com.econovation.overflow.auth.persistence.entity.AuthInfoEntity.ENTITY_PREFIX;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@SuperBuilder(toBuilder = true)
@Entity(name = ENTITY_PREFIX + "_entity")
@Table(name = ENTITY_PREFIX + "tb")
public class AuthInfoEntity {

  public static final String ENTITY_PREFIX = "auth_info";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ENTITY_PREFIX + "_id", nullable = false)
  private Long id;

  @Builder.Default
  @Enumerated(EnumType.STRING)
  @Column(name = ENTITY_PREFIX + "_type", nullable = false)
  private LoginType type = LoginType.SERVICE;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = ENTITY_PREFIX + "_token", nullable = false)
  private String token;
}
