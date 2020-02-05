# Chapter 06 메서드 정리 

## 목차 ##
- [Extract Method](#1)
- [Inline Method](#2)
- [Inline Temp](#3)
- [임시변수를 메서드 호출로 전환](#4)
- [직관적 임시 변수 사용](#5)
- [임시변수 분리](#6)
- [매개변수로의 값 대입 제거](#7)
- [메서드를 메서드 객체로 전환](#8)
- [알고리즘 전환](#9)

---

<a name="1"></a>
## Extract Method (메서드 추출) ##
> 어떤 코드를 그룹으로 묶어도 되겠다고 판단될 땐, 
> 그 코드를 빼내어 목적을 잘 나타내는 직관적 이름의 메서드로 만들자.

- 메서드가 적절히 잘게 쪼개져 있으면 다른 메서드에서 쉽게 사용할 수 있다.
- 효과를 보려면 메서드의 이름을 잘 지어야 한다.
- `메서드 추출`로 코드의 명료성이 향상되기만 한다면, 메서드명이 추출한 코드보다 길어도 `메서드 추출`을 실시해야 한다.
- 임시변수가 많을 경우 추출하기가 어려우므로 [임시변수를 메서드 호출로 전환](#4) 기법을 사용하여 임시변수를 줄인다.
- `메서드 추출`이 어려울 경우, [메서드를 메서드 객체로 전환](#8) 기법을 실시한다.

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

<a name="2"></a>
## Inline Method (메서드 내용 직접 삽입) ##
> 메서드 기능이 너무 단순해서 메서드명만 봐도 너무 뻔할 땐, 
> 그 메서드의 기능을 호출하는 메서드에 넣어버리고 그 메서드는 삭제하자.

- 인다이렉션을 사용하면 해결되지만 불필요한 인다렉션은 오히려 장애물이다.
- [메서드를 메서드 객체로 전환](#8) 기법을 적용하기 전에 이 기법을 사용하자. 온갖 메서드를 옮기는 것보다 메서드 하나만 옮기는 것이 더 간편하다.
- 메서드가 재정의되어 있지 않은지 확인하고 진행한다.

```java
    int numberOfLateDeliveries;
    
    public int getRating() {
        return moreThanFiveLateDeliveries() ? 2 : 1;
    }

    private boolean moreThanFiveLateDeliveries() {
        return numberOfLateDeliveries > 5;
    }
``` 
```java
    public int getRating() {
        return numberOfLateDeliveries > 5 ? 2 : 1;
    }
```

<a name="3"></a>
## Inline Temp (임시변수 내용 직접 삽입) ##
> 간단한 수식을 대입받는 임시변수로 인해 다른 리팩토링 기법 적용이 힘들 땐,
> 그 임시변수를 참조하는 부분을 전부 수식으로 치환하자.

- 만일 임시변수가 [메서드 추출](#1) 등 다른 리팩토링에 방해가 된다면 `임시변수 내용 직접 삽입`을 적용해야 한다.

```java
    double basePrice = anOrder.getBasePrice();
    return (basePrice > 1000);

```
```java
    return anOrder.getBasePrice() > 1000;
```

<a name="4"></a>
## 임시변수를 메서드 호출로 전환 ##


<a name="8"></a>
## 메서드를 메서드 객체로 전환 ##
