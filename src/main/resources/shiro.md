anon -- org.apache.shiro.web.filter.authc.AnonymousFilter
authc -- org.apache.shiro.web.filter.authc.FormAuthenticationFilter
authcBasic -- org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
perms -- org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
port -- org.apache.shiro.web.filter.authz.PortFilter
rest -- org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
roles -- org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
ssl -- org.apache.shiro.web.filter.authz.SslFilter
user -- org.apache.shiro.web.filter.authc.UserFilter
logout -- org.apache.shiro.web.filter.authc.LogoutFilter


anon:����/admins/**=anon û�в�������ʾ��������ʹ�á� 

authc:����/admins/user/**=authc��ʾ��Ҫ��֤(��¼)����ʹ�ã�û�в��� 

###roles��
����/admins/user/**=roles[admin],��������д��������ʱ����������ţ����Ҳ���֮���ö��ŷָ���ж������ʱ������admins/user/**=roles["admin,guest"],ÿ������ͨ������ͨ�����൱��hasAllRoles()������ 

###perms��
����/admins/user/**=perms[user:add:*],��������д��������ʱ����������ţ����Ҳ���֮���ö��ŷָ����/admins/user/**=perms["user:add:*,user:modify:*"]�����ж������ʱ����ÿ��������ͨ����ͨ�����뵱��isPermitedAll()������ 

###rest��
����/admins/user/**=rest[user],��������ķ������൱��/admins/user/**=perms[user:method] ,����methodΪpost��get��delete�ȡ� 

###port��
����/admins/user/**=port[8081],�������url�Ķ˿ڲ���8081����ת��schemal://serverName:8081?queryString,����schmal��Э��http��https�ȣ�serverName������ʵ�host,8081��url������port�Ķ˿ڣ�queryString������ʵ�url��ģ�����Ĳ����� 

###authcBasic��
����/admins/user/**=authcBasicû�в�����ʾhttpBasic��֤ 

###ssl:
����/admins/user/**=sslû�в�������ʾ��ȫ��url����Э��Ϊhttps 

###user:
����/admins/user/**=userû�в�����ʾ��������û������������ʱ�������