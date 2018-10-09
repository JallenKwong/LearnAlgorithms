# 字符串 #

一些基于字符串处理的领域

- 信息处理 给定搜索网页
- 基因组学 根据密码子将DNA转换为由4个碱基(A、C、T、G)组成的字符串
- 通信系统 发短信、电邮等
- 编译系统 编译器

**Java中表示字符串的两种方法**

操作|字符数组|Java字符串
---|---|---
生命|char[] a|String s
根据索引访问字符|a[i]|s.charAt(i)
获取字符串长度|a.length|s.length()
表示方法转换|a=s.toCharArray()|s=new String(a)
子字符串|-|s.substring()
字符串的链接|-| + 的重载用于字符串

---

**字母表**

一些应用会对字符串的字母表作出限制

**Alphabet API**

public class Alphabet|--
---|---
Alphabet(String s)|create a new alphabet from chars in s
char toChar(int index)|convert index to corresponding alphabet char
int toIndex(char c)|convert c to an index between 0 and R-1
boolean contains(char c)|is c in the alphabet?
int R()|radix (number of characters in alphabet)
int lgR()|number of bits to represent an index
int[] toIndices(String s)|convert s to base-R integer
String toChars(int[] indices)|convert base-R integer to string over this alphabet

**标准字母表**

name|R()|lgR()|characters
---|---|---|---
BINARY|2|1|01
DNA|4|2|ACTG
OCTAL|8|3|01234567
DECIMAL|10|4|0123456789
HEXADECIMAL|16|4|0123456789ABCDEF
PROTEIN|20|5|ACDEFGHIKLMNPQRSTVWY
LOWERCASE|26|5|abcdefghijklmnopqrstuvwxyz
UPPERCASE|26|5|ABCDEFGHIJKLMNOPQRSTUVWXYZ
BASE64|64|6|ABCDEFGHIJKLMNOPQRSTUVWXYZ<br/>abcdefghijklmnopqrstuvwxyz0123456789+/
ASCII|128|7|ASCII characters
EXTENDED_ASCII|256|8|extended ASCII characters
UNICODE16|65536|16|Unicode characters

[Alphabet](Alphabet.java)

[Alphabet类的典型用例](Count.java)

没有实现基于通用字母表Alphabet类型得到的字符串类型，这是因为

- 大多应用使用String类型
- 使程序更复杂
- 将字符串转化为索引或是由索引得到字符串常常落入内循环，这会大幅降低实现的性能

**总结，还是用回String类型**

## 字符串排序 ##

### 键索引计数法 ###

该算法是接下来字符串排序算法的基础

场景为学生分组并排序

![](image/Typical-candidate-for-key-indexed-counting.png)

---

**算法步骤**

- 频率统计

![](image/Computing-frequency-counts.png)

- 将频率转换为索引

![](image/Transforming-counts-to-start-indices.png)

- 数据索引

![](image/Distributing-the-data.png)

- 回写

---

![键索引计数法](image/Key-indexed-counting.png)

总的程序

	int N = a.length;
	String[] aux = new String[N];
	int[] count = new int[R+1];

	//1.Compute frequency counts.
	for (int i = 0; i < N; i++)
	count[a[i].key() + 1]++;

	//2.Transform counts to indices.
	for (int r = 0; r < R; r++)
	count[r+1] += count[r];

	//3.Distribute the records.
	for (int i = 0; i < N; i++)
	aux[count[a[i].key()]++] = a[i];

	//4.Copy back.
	for (int i = 0; i < N; i++)
	a[i] = aux[i];

>命题A 键索引计数法排序 N个键为0到R-1之间的整数的元素需要访问数组8N + 3R + 1次

### 低位优先的字符串排序 ###

**LSD** leastsignificant-digit first (LSD) string sort

**一个应用**

假设有一位工程师架设了一个设备来记录给定时间段内某条忙碌的告诉公路上的所有车辆的车牌号，他希望**知道总共有多少辆不同的车辆经过这段高速公路**

![](image/Typical-candidate-for-LSD-string-sort.png)

LSD思想 若字符串长度均为W，那就从右向左以每个位置的字符作为键，用键索引计数法将字符串排序W遍

>**命题B** 低位优先的字符串排序算法能够**稳定地**将定长字符串排序

![LSD字符串排序过程](image/the-trace-of-lsd.png)

![用LSD给扑克牌排序](image/Sorting-a-card-deck-with-LSD-string-sort.png)

>**命题B续** 对于基于R个字符的字母表的N个以长为W的字符串为键的元素，低位优先的字符串排序需要访问~W(7N+3R)次数组，使用额外空间与 N+R 成正比

### 高位优先的字符串排序 ###















