package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import java.util.List;
//import hello.hellospring.repository.;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
               .ifPresent(member1 -> {
                   try {
                       throw new IllegalAccessException("이미 존재하는 회원입니다");
                   } catch (IllegalAccessException e) {
                       throw new RuntimeException(e);
                   }
               });
    }

    public List<Member> findMembers(){
        return memberRepository.findALL();

    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
