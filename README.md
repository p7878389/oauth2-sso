## SpringBoot-Oauth2-Security  SSO

### auth-server

- 重写ClientDetailsService实现**loadClientByClientId**方法，加载client信息
- 重写UserDetailsService实现**loadUserByUsername**方法，加载用户信息
- AuthServerConfig配置**ClientDetailsService**实现类，SecurityConfig配置**UserDetailsService**实现类
- SecurityConfig配置相关需要登录才能访问的url与登录的url
