package lab.board.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lab.board.config.auth.MemberPrincipal;
import lab.board.domain.Member;
import lab.board.repository.MemberRepository;

@Service
public class Oauth2PrincipalService extends DefaultOAuth2UserService{

	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired private MemberRepository memberRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("OAuth2 loadUser()");
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		System.out.println("Token : " +userRequest.getAccessToken());
		
		Member member = processNewMember(oAuth2User);
		
		System.out.println("------------------------------");
		System.out.println(userRequest.getClientRegistration());
		System.out.println(oAuth2User.getAttributes());
		
		return new MemberPrincipal(member, oAuth2User.getAttributes());
	}
	
	public Member processNewMember(OAuth2User oAuth2User) {
		String providerId = (String) oAuth2User.getAttributes().get("sub");
		String provider = "google";
		String username = provider + "_" + providerId;

		Member member = memberRepository.findByUsername(username);
		System.out.println("member = " + member);
		if(member==null) {
			member = registerNewMember(username);
		}
		System.out.println("member = " + member);
		return member;
	}
	
	
	public Member registerNewMember(String username) {
		String encodedPwd = bCryptPasswordEncoder.encode(PASSWORD.CODE);
		Member member = Member.builder()
								.username(username)
								.password(encodedPwd)
								.build();
		memberRepository.save(member);
		return member;
	}
}
