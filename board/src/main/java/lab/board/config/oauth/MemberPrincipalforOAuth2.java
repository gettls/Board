package lab.board.config.oauth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lab.board.domain.Member;
import lombok.Getter;

@Getter
public class MemberPrincipalforOAuth2 implements OAuth2User{
	
	private Member member;
	private Map<String, Object> attributes;
	

	
	
	// Oauth2 로그인
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return member.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public MemberPrincipalforOAuth2(Member member, Map<String, Object> attributes) {
		this.member = member;
		this.attributes = attributes;
	}
}
