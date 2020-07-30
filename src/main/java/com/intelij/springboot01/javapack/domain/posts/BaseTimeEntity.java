package com.intelij.springboot01.javapack.domain.posts;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 해당 클래스를 상속할 경우 필드들도 컬럼으로 인식할 수 있게 해줌
@EntityListeners(AuditingEntityListener.class)  //해당 클래스에 Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능
public class BaseTimeEntity {

    @CreatedDate //Entity가 생성되어 저장될 때 시간이 자동으로 저장
    private LocalDateTime createdDate;

    @LastModifiedDate //조회한 Entity값이 변경될 때 시간이 자동으로 저장
    private LocalDateTime modifiedDate;

}
