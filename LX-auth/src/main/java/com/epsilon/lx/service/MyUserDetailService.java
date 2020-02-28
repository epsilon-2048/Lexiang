package com.epsilon.lx.service;

import com.epsilon.lx.entities.BaseUser;
import com.epsilon.lx.entities.BaseUserExample;
import com.epsilon.lx.entities.UserRole;
import com.epsilon.lx.entities.UserRoleExample;
import com.epsilon.lx.enums.ErrorCode;
import com.epsilon.lx.exception.NotFoundException;
import com.epsilon.lx.mapper.BaseUserMapper;
import com.epsilon.lx.mapper.UserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 处理用户信息获取逻辑
 * 根据用户名获取用户信息，如果没有，抛出用户不存在异常
 * 这里不负责认证，认证由AuthenticationProvider来做，你只要提供正确的密码
 *
 * 客户需要写一个实现UserDetailsService的类和一个实现UserDetails的类
 * 如MyUserDetailService和MyUser
 * 自定义实现
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BaseUserMapper userMapper;

    @Autowired
    private UserRoleMapper roleMapper;

    Logger logger = LoggerFactory.getLogger(getClass());


    public Boolean addUser(String username, String password, String[] roles) throws NotFoundException {

        BaseUserExample userExample = new BaseUserExample();
        BaseUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<BaseUser> user = userMapper.selectByExample(userExample);
        String roleStr ="";
        for (String tmprole : roles) {
            roleStr = tmprole + ",";
        }
        if (user.size() > 0) throw new NotFoundException(ErrorCode.USER_ALREADY_EXIST,username);
        BaseUser baseUser = new BaseUser();
        baseUser.setPassword(passwordEncoder.encode(password));
        baseUser.setUsername(username);
        baseUser.setAccountNonExpired(true);
        baseUser.setAccountNonLocked(true);
        baseUser.setCredentialsNonExpired(true);
        baseUser.setEnabled(true);
        int flag = userMapper.insertSelective(baseUser);
        UserRole role = new UserRole();
        role.setRoles(roleStr);
        role.setBaseUserId(baseUser.getId());
        roleMapper.insertSelective(role);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("登陆的用户名：" + username);

        BaseUserExample userExample = new BaseUserExample();
        BaseUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<BaseUser> user = userMapper.selectByExample(userExample);
        if (user.size() != 0) {
            UserRoleExample userRoleExample = new UserRoleExample();
            UserRoleExample.Criteria criteria1 = userRoleExample.createCriteria();
            criteria1.andBaseUserIdEqualTo(user.get(0).getId());
            List<UserRole> roles = roleMapper.selectByExample(userRoleExample);
            user.get(0).setAuthorities(
                    AuthorityUtils.commaSeparatedStringToAuthorityList(roles.get(0).getRoles()));
            return user.get(0);
        } else {
            throw new UsernameNotFoundException("用户名不存在");
        }


        //根据用户名查找用户信息（任何你需要的手段,数据库、xml等）
        //。。。
        //返回一个实现了UserDetails接口的类即可，User是spring默认实现的类，可以自定义一个你需要的类

/*
        //这个加密方法的调用应该是在注册时将密码加密后放进数据库或XML的，而非在这里调用
        String dbPwd = passwordEncoder.encode("123456");
        //这个密码应该是从数据库或xml中取出的密码，而且是加密过的，而非写死。
        //这里是简单模拟从数据库获取到加密的密码
        String password = dbPwd;
        logger.info("从数据库或xml中获取的加密密码：" + password);
        //权限列表也是由数据库或xml中获取，多个权限由,分隔
        String authorities = "admin,superAdmin";


        *//**
         * 为这个user设置他的状态
         * boolean isAccountNonExpired();
         * 账户是否过期
         * boolean isAccountNonLocked();
         * 账号是否锁定，在业务中一般用来表示账户是否被冻结
         * boolean isCredentialsNonExpired();
         * 密码是否过期，有些需求是要定时更改密码
         * boolean isEnabled();
         * 账户是否可用，一般用来表示账户是否被删除，
         * 一般数据库是不删数据的，
         * 用标志码表示该用户是否被删除（逻辑删除），便可使用此方法表示
         *//*
        return new User(username, password,
                true, true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                */
    }
}
