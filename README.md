# 单点登陆认证服务器

- [介绍](#介绍)
- [流程](#流程)


## 介绍

授权码是在所有请求中附带token，并交由认证服务器验证的一种权限认证模式。

在发送请求时需要在应用中判断是否携带token，以及token是否过期，如何没有token或者已经过期，需要跳转到认证服务器中登陆，
之后重新跳转到应用继续完成业务。

因为在不同应用中使用了同一个登陆服务，所以在第一次登陆之后，再次登陆会自动携带code返回到应用之中。
这样应用就可以通过code重新获取token，已实现单点登陆。

## 流程

1. 登陆以获取code，请求类型(GET):

```
http://localhost:8080/oauth/authorize?response_type=code&client_id=client&state=xyz%20&redirect_uri=http%3A%2F%2Fwww.baidu.com
```

参数 | 是否允许空值 | 介绍
---|---|---
response_type | 否 |此处固定为code
client_id | 否 |认证服务器配置的client_id
redirect_uri | 否 | 登陆成功后跳转的地址
state | 是 |携带的参数，可以为空

正确时跳转页面:

```
https://www.baidu.com/?code=CwBT8F&state=xyz%20
```

参数 | 介绍
---|---
code | 返回的code，可以用来获取token
state | 登陆时附带的参数

2. 通过code获取token，请求类型(POST):

```bash
curl -X POST \
  http://localhost:8080/oauth/token \
  -H 'Authorization: Basic Y2xpZW50OnNlY3JldA==' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'grant_type=authorization_code&code=CwBT8F&redirect_uri=http%3A%2F%2Fwww.baidu.com'
```

参数 | 介绍
---|---
Authorization | 按照client:secret的格式进行base64加密
grant_type | 此处固定为authorization_code
code | 前一步获取的code
redirect_uri | 前一步所传的url

正确时返回的json:

```json
{
    "access_token": "3e2301a9-87ea-4b0d-9f37-847a6cfed4b2",
    "token_type": "bearer",
    "refresh_token": "a33d2862-4849-470c-9724-c23678a79bc6",
    "expires_in": 40375,
    "scope": "app"
}
```

参数 | 介绍
---|---
access_token | 得到的token
token_type | token类型
refresh_token | 刷新token所用的refresh_token
expires_in | 过期时间，单位(秒)
scope | 认证服务器设置的scope

3. 到这一步已经可以通过token发送请求了，下面是一个获取用户信息的示例:

```bash
curl -X GET \
  http://localhost:8080/user \
  -H 'Authorization: bearer 3e2301a9-87ea-4b0d-9f37-847a6cfed4b2'
```

参数 | 介绍
---|---
Authorization | 前一步得到的token_type，加上一个空格，再加上前一步得到的token

正确时返回的json，可以根据自己的需求[修改](src/main/java/com/example/oauthtest/user/UserController.java):

```json
{
    "authorities": [
        {
            "authority": "user"
        }
    ],
    "details": {
        "remoteAddress": "0:0:0:0:0:0:0:1",
        "sessionId": "D04DF1FF47A76BCFEB652ECC47A129D9"
    },
    "authenticated": true,
    "principal": {
        "id": "1",
        "username": "user",
        "enabled": true,
        "roles": [
            {
                "id": "1",
                "name": "user"
            }
        ],
        "authorities": [
            {
                "authority": "user"
            }
        ],
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true
    },
    "credentials": null,
    "name": "user"
}
```

4. 如果token过期或即将过期(我一般设置为剩余200秒之内)，通过refresh_token刷新新的token，请求类型(POST):

```bash
curl -X POST \
  http://localhost:8080/oauth/token \
  -H 'Authorization: Basic Y2xpZW50OnNlY3JldA==' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'grant_type=refresh_token&refresh_token=d218ad40-c6e9-4db6-9ffa-6ba6e57237ad'
```

参数 | 介绍
---|---
Authorization | 按照client:secret的格式进行base64加密
grant_type | 此处固定为refresh_token
refresh_token | 第二步获取的refresh_token

正确时的返回值:

```json
{
    "access_token": "49a58fc2-bd31-4d9b-af1e-b14f8ed211ea",
    "token_type": "bearer",
    "refresh_token": "a33d2862-4849-470c-9724-c23678a79bc6",
    "expires_in": 43199,
    "scope": "app"
}
```

参数同第二步
