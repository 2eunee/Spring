package org.zerock.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


import lombok.Getter;


@Getter
public class CustomUser extends User {
	
	private static final long serialVersionUID = 1L;
	private MemberVO member;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}
	
	public CustomUser(MemberVO vo) {
		
		super(vo.getUserid(), vo.getUserpw(), vo.getAuthList().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth())).
				collect(Collectors.toList())); 
		
	//	super(vo.getUserid(),vo.getUserpw(),
	//			AuthorityUtils.createAuthorityList(vo.getAuthList().toString()));
	// 다른방식으로도 가능
		
		this.member = vo;
	}
	
	

}
