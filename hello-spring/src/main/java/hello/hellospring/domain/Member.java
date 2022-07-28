package hello.hellospring.domain;

public class Member {

    private Long id; // 구분을 위한 시스템에 저장하는
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
