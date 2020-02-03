# Chapter 06 메서드 정리 

## 목차 ##
- [메서드 추출](#1)
- [메서드 내용 직접 삽입](#2)
- [임시변수 내용 직접 삽입](#3)
- [임시변수를 메서드 호출로 전환](#4)
- [직관적 임시 변수 사용](#5)
- [임시변수 분리](#6)
- [매개변수로의 값 대입 제거](#7)
- [메서드를 메서드 객체로 전환](#8)
- [알고리즘 전환](#9)

---

<a name="1"></a>
## 메서드 추출 ##
> 어떤 코드를 그룹으로 묶어도 되겠다고 판단될 땐, 그 코드를 빼내어 목적을 잘 나타내는 직관적 이름의 메서드로 만들자.

- 메서드가 적절히 잘게 쪼개져 있으면 다른 메서드에서 쉽게 사용할 수 있다.
- 효과를 보려면 메서드의 이름을 잘 지어야 한다.
- 메서드 추출로 코드의 명료성이 향상되기만 한다면, 메서드명이 추출한 코드보다 길어도 메서드 추출을 실시해야 한다.
- 임시변수가 많을 경우 추출하기가 어려우므로 [임시변수를 메서드 호출로 전환](#4) 기법을 사용하여 임시변수를 줄인다.
- 메서드 추출이 어려울 경우, [메서드를 메서드 객체로 전환](#8) 기법을 실시한다.

```java
    public void printOwingMesh() {
        List samples = Arrays.asList("1", "2", "3");
        double outstanding = 0.0;

        System.out.println("---------");
        System.out.println("고객 외상");
        System.out.println("---------");
        Iterator iterator = samples.listIterator();

        while (iterator.hasNext()) {
            String sample = String.valueOf(iterator.next());
            outstanding += Integer.parseInt(sample);
        }

        System.out.println("고객명: CU");
        System.out.println("외상액: " + outstanding);
    }
```
```java
    public void printOwing(List samples) {
        printBanner();
        double result = sum(samples);
        printDetails(result);
    }

    private double sum(List samples) {
        double outstanding = 0.0;
        Iterator iterator = samples.listIterator();

        while (iterator.hasNext()) {
            String sample = String.valueOf(iterator.next());
            outstanding += Integer.parseInt(sample);
        }
        return outstanding;
    }

    private void printDetails(double outstanding) {
        System.out.println("고객명: CU");
        System.out.println("외상액: " + outstanding);
    }

    private void printBanner() {
        System.out.println("---------");
        System.out.println("고객 외상");
        System.out.println("---------");
    }
```


<a name="4"></a>
## 임시변수를 메서드 호출로 전환 ##


<a name="8"></a>
## 메서드를 메서드 객체로 전환 ##
