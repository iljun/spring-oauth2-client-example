social login example을 만들면서 가장 고민되는 부분 
---

#### 대부분의 example은 web, native에서 로그인 성공 후 access_token과 refresh_token을 이용하여 서버에서 JWT Token 또는 session을 발급한다.
- 그러나 JWT Token을 발급하는 과정이 언제나 best practice일까?
- JWT Token을 제대로 쓰려면 refresh_token 발급 및 관리가 필요하다고 생각한다.
    - 물론 logout의 경우 user가 사용하던 JWT Token을 따로 관리해야한다.
- 이런 문제가 있으면 굳이 JWT 토큰을 사용할 필요가 없지 않을까?

#### 만약 JWT Token을 사용하지 않고 내 서버에서 외부 Provider를 통해 전달받은 access_token, refresh_token을 따로 관리하고, 내 Auth Server에서 새롭게 발급한 token을 사용한다면 대응이 가능할듯하다.
- Provider의 access_token과 refresh_token을 관리하는 이유는 언제 Provider의 기능을 사용하는 방향으로 확장 될 지 모르기 때문에
- 앞으로 구현해야 하는 방향은 
    1. web, native에서는 authorization_code만 서버로 전달 
    2. server는 authorization_code를 이용하여 access_token, refresh_token을 이용하여 user 정보 취득
    3. 내 인증 서버에 정보를 저장
    4. 인증 서버를 이용해 access_token, refresh_token을 발급 (provider의 token과 동일한 기간으로)
    
#### 따로 인증 서버를 관리하면서 생기는 문제점 
- user 정보의 동기화 타이밍 
- 구현 난이도가 높다.
    - 일반적인 framework로는 어려움, custom할 부분이 굉장히 많음 
    
#### 2안
- provider에서 인증 후 JWT 토큰 방식을 사용하면서, User DB의 refresh_token만을 관리한다
    - provider의 다양한 기능을 사용하기 위해서 
    
### 이정도의 볼륨으로 개발을 한다면... 자체 인증 서버를 만드는게 맞지 않을까?...

### 그리고 내가 개발하려는건 resource server인데 이정도 볼륨이 맞을까?....
