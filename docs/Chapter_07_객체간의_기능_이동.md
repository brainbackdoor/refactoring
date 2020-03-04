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
