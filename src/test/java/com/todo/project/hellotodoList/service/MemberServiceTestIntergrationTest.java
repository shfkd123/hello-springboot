package com.todo.project.hellotodoList.service;

import com.todo.project.hellotodoList.domain.Member;
import com.todo.project.hellotodoList.repository.MemberRepository;
import com.todo.project.hellotodoList.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
 class MemberServiceTestIntergrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given -- 무언가가 주어졌는데
        Member member = new Member();
        member.setName("hello2");

        //when -- 이걸 실행했을 때
        Long saveId = memberService.join(member);

        //then -- 결과가 이게 나와야 해!
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring3");

        Member member2 = new Member();
        member2.setName("spring3");

        //when
        memberService.join(member1);
        //assertThrows(IllegalStateException.class, ()-> memberService.join(member2));

        IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //메세지가 같은지 검증

        /*try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}