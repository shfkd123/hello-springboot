package com.todo.project.hellotodoList;

import com.todo.project.hellotodoList.repository.JdbcMemberRepository;
import com.todo.project.hellotodoList.repository.JdbcTemplateMemberRepository;
import com.todo.project.hellotodoList.repository.MemberRepository;
import com.todo.project.hellotodoList.repository.MemoryMemberRepository;
import com.todo.project.hellotodoList.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource; //스프링부트가 관리 자체적으로 bean 연결 dataSource생성해주고 주입해준다.
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
