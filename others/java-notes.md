# Notes(Java)

---

## Collections.sort() function

**It is useful to define a new class named MyComparator, together with the Collections.sort() function to solve sorting problem using Java.**

The function, or method works like below

```java
MyComparator mc = new MyComparator();
Collections.sort(list, mc);
```

**mc is the instance in which we define how to sort the element,** the class needs to be defined by ourselves like the code below

```java
class MyComparator implements Comparator {      
    public int compare(Object a1, Object a2) {
        String s1 = (String)a1;
        String s2 = (String)a2;
        int idx1  = s1.indexOf(" ");
        int idx2  = s2.indexOf(" ");
        String head1 = s1.substring(0, idx1);
        String head2 = s2.substring(0, idx2);
        String body1 = s1.substring(idx1);
        String body2 = s2.substring(idx2);

        if(body1.equals(body2)) {
          	return head1.compareTo(head2);
        }
        else
         	 return body1.compareTo(body2);
    }
}
```

Our own MyComparator class should implement Comparator class. Inside the class, the compare method should be overwritten, **the compare method is responsible for comparing  two elements that pased in,** if we want the ascending order, the return value should be like **head1.compareTo(head2);** or **i1.start - i2.start;**, where head1 and i1 are the first element that passed in, if we want descending order, then add a minus sign infront of the return value.

## Syntax

In java, a series of bit calulation may fails,

```java
if (j < n && abbr[j] >= '0' && abbr[j] <= '9')
```

this statement still reports a error when j == n, it still checks the two elements behind.