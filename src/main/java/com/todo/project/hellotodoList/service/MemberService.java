package com.todo.project.hellotodoList.service;

import com.todo.project.hellotodoList.domain.Member;
import com.todo.project.hellotodoList.repository.MemberRepository;
import com.todo.project.hellotodoList.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     *  회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); //중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     *  전체 회원조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
