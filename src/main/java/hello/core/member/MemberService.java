package hello.core.member;


public interface MemberService {

    Member findMember(Long memberId);
    void join(Member member);

}
