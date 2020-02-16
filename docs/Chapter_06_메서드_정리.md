# Chapter 06 메서드 정리 

## 목차 ##
- [Extract Method](#1)
- [Inline Method](#2)
- [Inline Temp](#3)
- [Replace Temp with Query](#4)
- [Introduce Explaining Variable](#5)
- [Split Temporary Variable](#6)
- [Remove Assignments to Parameters](#7)
- [Replace Method with Method Object](#8)
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
## Replace Temp with Query (임시변수를 메서드 호출로 전환) ##
> 수식의 결과를 저장하는 임시변수가 있을 땐, 
> 그 수식을 빼내어 메서드로 만든 후, 임시변수 참조 부분을 전부 수식으로 교체하자.
> 새로 만든 메서드는 다른 메서드에서도 호출 가능하다.

```java
    double getPrice() {
        int basePrice = quantity * itemPrice;
        double discountFactor;
        if (basePrice > 1000) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }
        return basePrice * discountFactor;
    }
```
```java
    double calculatePrice() {
        return calculateBasePrice() * calculateBasePrice();
    }
    private calculateBasePrice() {
        return quantity * itemPrice;
    } 
    private getDiscountFactor() {
        return (calculateBasePrice() > 1000) ? 0.95 : 0.98;       
    }  
```

- 임시변수는 일시적이고 적용이 국소적 범위로 제한된다. 
- 지역변수가 많을수록 [메서드 추출](#1)이 어려우므로 최대한 많은 변수를 메서드 호출로 고쳐야 한다.

<a name="5"></a>
## Introduce Explaining Variable (직관적 임시변수 사용) ##
> 사용된 수식이 복잡할 땐,
> 수식의 결과나 수식의 일부분을 용도에 부합하는 직관적 이름의 임시변수에 대입하자. 

```java
        if ((platform.toUpperCase().indexOf("MAC") > -1) &&
                (browser.toUpperCase().indexOf("IE") > -1) &&
                wasInitialized() && resize > 0) {
        }
```
```java
        final boolean isMacOs = platform.toUpperCase().indexOf("MAC") > -1;
        final boolean isIEBrowser = browser.toUpperCase().indexOf("IE") > -1;
        final boolean wasResized = resize > 0;

        if (isMacOs && isIEBrowser && wasInitialized() && wasResized) {

        }
```
- 임시변수를 사용해 의미를 설명하려 할 때 사용된다.
- 가급적 [메서드 추출](#1)을 통해 [임시변수를 메서드 호출로 전환](#4) 기법을 활용한다. 임시변수를 남용하는 것은 가독성에 좋지 않으며, 메서드를 추출할 경우 재사용이 가능하다.

<a name="6"></a>
## Split Temporary Variable (임시변수 분리) ##
> 루프 변수나 값 누적용 임시변수가 아닌 임시변수에 여러 번 값이 대입될 땐,
> 각 대입마다 다른 임시변수를 사용하자.

- 임시변수 하나를 두 가지 용도로 사용하면 코드를 분석하는 사람에게 혼동을 줄 수 있다.
- 임시변수를 final로 선언하자. 


<a name="7"></a>
## Remove Assignments to Parameters (매개변수로의 값 대입 제거) ##
> 매개변수로 값을 대입하는 코드가 있을 땐,
> 매개변수 대신 임시변수를ㄹ 사용하게 수정하자.

```java
    int discount(int inputValue, int quantity, int yearToDate) {
        if (inputValue > 50)  {
            inputValue -= 2;
        }
    }
```
```java
    int discount(final int inputValue, final int quantity, final int yearToDate) {
        int result = inputValue;
        if (inputValue > 50)  {
            result -= 2;
        }
    }
```
- 값을 통한 전달로만 사용하여야 하며, '참조를 통한 전달'을 할 경우 코드의 명료성이 떨어지고 혼동될 수 있다.
- 매개변수에 final을 붙여주자

<a name="8"></a>
## Replace Method with Method Object (메서드를 메서드 객체로 전환) ##
> 지역변수 때문에 [메서드 추출](#1)을 적용할 수 없는 긴 메서드가 있을 땐,
> 그 메서드 자체를 객체로 전환해서 모든 지역변수를 객체의 필드로 만들자.
> 그런 다음 그 메서드를 객체 안의 여러 메서드로 쪼개면 된다.

1. 전환할 메서드의 이름과 같은 의도를 드러내는 이름으로 새 클래스를 생성한다.
2. 원본 메서드 안의 각 임시변수와 매개변수에 해당하는 속성(final)을 추가한다.
3. 새 클래스에 원본 객체와 각 매개변수를 받는 생성자를 작성한다.
4. 새 클래스에 compute 메서드를 작성하고 원본 메서드의 내용을 복사한다. 
5. 원본 메서드를 새 객체의 메서드를 호출하도록 변경한다.

```java
    class Account {
        int evaluate(int score, int weight, int yearToDate) {
            int scoreByDelta = (score * weight) + delta();
            int scoreByDate = (score * yearToDate) + 100;
            if((yearToDate - scoreByDelta) > 100) {
                scoreByDate -= 20;
            }
            int scoreMultipledByWeight = scoreByDate * 7;
            // 기타 작업
            return scoreMultipledByWeight - 2 * scoreByDelta;
        }
        private int delta() {
            return 0;
        }        
    }
```
```java
    class Account {
        int evaluate(int score, int weight, int yearToDate) {
            return new Evaluation(this, score, weight, yearToDate).compute();
        }

        private int delta() {
            return 0;
        }
    }

    class Evaluation {
        private static final int EVALUATION_WEIGHT = 7;
        private final Account account;
        private int score;
        private int weight;
        private int yearToDate;
        private int scoreByDelta;
        private int scoreByDate;

        public Evaluation(Account account, int score, int weight, int yearToDate) {
            this.account = account;
            this.score = score;
            this.weight = weight;
            this.yearToDate = yearToDate;
        }

        public int compute() {
            scoreByDelta = calculateScoreByDelta();
            scoreByDate = calculateScoreByDate();
            return multipleByWeight() - 2 * scoreByDelta;
        }

        private int multipleByWeight() {
            return scoreByDate * EVALUATION_WEIGHT;
        }

        int calculateScoreByDelta() {
            return (score * weight) + account.delta();
        }

        int calculateScoreByDate() {
            return ((yearToDate - scoreByDelta) > 100)
                    ? (score * yearToDate) + 80
                    : (score * yearToDate) + 100;
        }
    }
```
