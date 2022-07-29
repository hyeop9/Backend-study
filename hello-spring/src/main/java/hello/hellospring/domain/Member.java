package hello.hellospring.domain;

import javax.persistence.*;

@Entity // JPA가 관리하는 엔티
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 구분을 위한 시스템에 저장하는

    // @Column(name = "username") username 하고 Mapping
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
