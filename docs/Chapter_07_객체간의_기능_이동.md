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
> 메서드가 자신이 속한 클래스보다 다른 클래스의 기능을 더 많이 이용할 땐,
> 그 메서드가 제일 많이 이용하는 클래스 안에서 비슷한 내용의 새 메서드를 작성하자.
> 기존 메서드는 간단한 대리 메서듣로 전환하든지 아예 삭제하자.

