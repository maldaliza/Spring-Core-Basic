package hello.core.member;

public interface MemberRepository {

    // 회원 저장
    void save(Member member);

    // 회원아이디로 회원을 찾는 기능
    Member findById(Long memberId);
}
