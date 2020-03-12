# Chapter 07 객체간의 기능 이동 

## 목차 ##
- [Move Method](#1)
- [Move Field](#2)
- [Extract Class](#3)
- [Inline Class](#4)
- [Hide Delegate](#5)
- [Remove Middle Man](#6)
- [Introduce Foreign Method](#7)
- [Introduce Local Extension](#8)

---

<a name="1"></a>
## Move Method (메서드 이동) ##

1. 원본 클래스에 정의되어 있는 원본 메서드에 사용된 모든 기능을 검사하자. 그 기능들도 모두 옮겨야 할지 판단하자.
2. 원본 클래스의 상 / 하위 클래스에서 그 메서드에 대한 다른 선언이 있는지 확인하자.
3. 그 메서드를 대상 클래스안에 복사한 후, 잘 돌아가게끔(컴파일되게끔) 수정하자.
4. 원본 객체에서 대상 객체를 참조할 방법을 정하고, 원본 메서드를 위임 메서드로 전환하자. 그리고 테스트
5. 원본 메서드를 삭제하자.

---

<a name="2"></a>
## Move Field (필드 이동) ##

> 어떤 필드가 자신이 속한 클래스보다 다른 클래스에서 더 많이 사용될 때는
> 대상 클래스 안에 새 필드를 선언하고, 대상 클래스에 있는 적절한 메서드를 참조하게 수정하자.
> (필드 캡슐화(Self Encapsulate Field)는 반드시 실시한다.) 

---

<a name="3"></a>
## Extract Class (클래스 추출) ##

```java
    class Person {
        private String name;
        private String areaCode;
        private String officeNumber;
        
        public String getTelePhoneNumber() {
            return areaCode + officeNumber;    
        }
    }
```

1. 새 클래스를 만들고 기존 클래스의 관련 필드와 메서드를 새 클래스로 옮긴다.

```java
    class Person {
        private String name;
        private TelephoneNumber officeTelephone = new TelephoneNumber();
        private String officeNumber;
        
        public String getTelePhoneNumber() {
            return officeTelephone.getNumber();    
        }
    }
    
    class TelephoneNumber {
        private String areaCode;
        private String officeNumber;

        String getNumber() {
            return areaCode + officeNumber;
        }
    }
```

2. 새 클래스를 클라이언트에 어느정도 공개할지 결정한다. 불변객체를 활용하는 것도 좋다.

---

<a name="4"></a>
## 클래스 내용 직접 삽입 (Inline Class) ##

> 클래스에 기능이 너무 적을 땐,
> 그 클래스의 모든 기능을 다른 클래스로 합쳐 넣는다.

```java
            Person cu = new Person();
            cu.getOfficeTelephone().setAreaCode("032");
```
```java
            Person cu = new Person();
            cu.setAreaCode("032");
```

---

<a name="5"></a>
## 대리 객체 은폐 (Hide Delegate) ##

> 클라이언트가 객체의 대리 클래스를 호출할 땐,
> 대리 클래스를 감추는 메서드를 서버에 작성하자.


```java
            Person manageer = cu.getDepartment().getManager();
```
```java
            Person manageer = cu.getManager();
```

<a name="6"></a>
## 과잉 중개 메서드 제거 (Remove Middle Man) ##

> 클라이언트에 자잘한 위임이 너무 많을 땐,
> 대리 객체를 클라이언트가 직접 호출하게 하자.

'대리 객체 은폐'로 인해 기능이 추가될 떄마다 위임 메서드를 작성하게 되어 중개자 역할만 하게 될 수 있다.
은폐의 정도를 어느정도까지 할 것인가에 대한 고민으로, 다시 원래대로 돌리자는 기법이나,
디미터 법칙 위반에 해당되고 개인적으로는 중개자 역할도 의미가 있다고 생각하기에 선호하지 않는다.
 
