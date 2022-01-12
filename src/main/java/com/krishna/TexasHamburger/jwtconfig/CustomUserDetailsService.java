//package com.krishna.TexasHamburger.jwtconfig;
//
//import com.krishna.TexasHamburger.service.UserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	UserService userService;
//    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
//
//
////    // This method is used by JWTAuthenticationFilter
////    @Transactional
////    public UserDetails loadUserById(Long id) {
////    	try {
////   		 com.krishna.TexasHamburger.model.User user = userService.getUserById(id);
////   	        return UserPrincipal.create(user);
////	   	}catch(Exception e) {
////	   		throw new UsernameNotFoundException("User not found with id : " + id);
////	   	}
////    }
//
//	@Override
//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//		try {
//			com.krishna.TexasHamburger.model.User user = userService.getUserByUserName(userName);
//			return UserPrincipal.create(user);
//		}catch(Exception e) {
//			throw new UsernameNotFoundException("User not found with id : " + userName);
//		}
//	}
//
//}
