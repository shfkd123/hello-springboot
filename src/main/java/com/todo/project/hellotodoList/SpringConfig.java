package com.todo.project.hellotodoList;

import com.todo.project.hellotodoList.repository.MemberRepository;
import com.todo.project.hellotodoList.repository.MemoryMemberRepository;
import com.todo.project.hellotodoList.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
