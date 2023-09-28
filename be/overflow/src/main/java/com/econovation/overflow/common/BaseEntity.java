package com.econovation.overflow.common;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class, SoftDeleteListener.class})
@SuperBuilder(toBuilder = true)
public class BaseEntity {

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDate;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updatedDate;

	@Builder.Default
	@Column(nullable = false)
	private Boolean deleted = false;

	public void delete() {
		this.deleted = true;
	}
}
