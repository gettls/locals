package community.locals.config;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import community.locals.domain.Member;
import community.locals.domain.Post;
import community.locals.service.MemberService;
import community.locals.service.PostService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitData {

	private final InitService initService;
	
	@PostConstruct
	public void init() {
		initService.init();
	}
	
	@Component
	static class InitService {
		
		@PersistenceContext
		EntityManager em;
		
		private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		@Transactional
		public void init() {
			for (int i=0; i<3; i++) {
				String username = "test" + Integer.toString(i + 1);
				String password = passwordEncoder.encode("1234");
				
				Member member = new Member(username, password);
				em.persist(member);
				
				for (int j=0; j<3; j++) {
					String contents = "contents" + Integer.toString(10*i + j + 1);
					String title = "title" + Integer.toString(10*i + j + 1);
				
					em.persist(new Post(contents, title, member));
				}
			}
		}
	}
	
}
