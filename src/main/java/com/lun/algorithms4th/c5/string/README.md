# 字符串 #

[1.字符串排序](#字符串排序)

[字符串排序小结](#字符串排序小结)

[2.单词查找树](#单词查找树)

[](#)


---

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

首先用键索引计数法将所有字符串按照首字母排序，然后递归地再将每个首字母所对应的子数组排序(忽略首字母，因为每一类中的所有字符串的首字母都是相同的)

![](image/Overview-of-MSD-string-sort.png)

![](image/Typical-candidate-for-key-indexed-counting.png)

---

**高位优先的字符串排序中count[]数组的意义**

<table>

<tr>
<td rowspan=2>第d个字符排序的完成阶段</td>
<td colspan=5>count[r]的值</td>
</tr>

<tr>
<td>r=0</td>
<td>r=1</td>
<td>r∈(2, R-1)</td>
<td>r=R</td>
<td>r=R+1</td>
</tr>

<tr>
<td>频率统计</td>
<td>0(未使用)</td>
<td>长度为d的字符串数量</td>
<td colspan=3>第d个字符的 索引值是r-2的字符串的 **数量**</td>
</tr>

<tr>
<td>将频率转换成索引</td>
<td>长度为d的字符串的子数组的起始索引</td>
<td colspan=3>第d个字符的索引值是r-1的字符串的子数组</td>
<td>未使用</td>
</tr>

<tr>
<td rowspan=2>数据分类</td>
<td colspan=3>第d个字符的索引值为r的字符串的子数组的起始索引</td>
<td colspan=2>未使用</td>
</tr>

<tr>
<td>1 + 长度为d的字符串的子数组的结束**索引**</td>
<td colspan=3>1 + 第d个字符串的索引值是r-1的字符串的数组的结束索引</td>
<td>未使用</td>
</tr>

</table>

---

[高位优先的字符串排序](MSD.java)

![高位优先的字符串排序：sort(a, 0, 14, 0)](image/trace-of-msd-string-sort.png)

注意：在这段轨迹中，小数组的插入排序切换阈值M为0，因此可看到完整的过程。

![高位优先的字符串排序的递归调用轨迹(小数组不会切换到插入排序，大小为0和1的子数组已被省略)](image/trace-of-msd-string-sort2.png)

**小型子数组对于高位优先的字符串排序的性能至关重要**

对于过多字符的字符集（如Unicode）,会分配过多的数组进行辅助运算（频率统计等），递归后以此类推，造成性能上的问题

因此，将 小数组切换到插入排序 对于 MSD是必须的。

![MSD中切换小型子数组的排序方法的实际效果](image/Effect-of-cutoff-for-small-subarrays.png)

>**命题C** 要将基于大小为R的字母表的N个字符串排序，MSD平均需要检查N(logN/LogR)


>**命题D** 将基于大小为R的字母表的N个字符串排序，MSD的次数在8N+3R 到 ~w(7N+3R), 其中w是字符串的平均长度；**最坏的情况下**MSD所需的空间与R乘以最长的字符串的长度之积成正比(再加上N)，也就是N+wR

### 三向字符串快速排序 ###

根据键的首字母进行三向切分，仅在中间子数组中的下一个字符(因为键的首字母都与切分字母都与切分字符相等)继续递归排序。

![](image/Overview-of-3-way-string-quicksort.png)

![三向字符串快速排序适用于含有较长公共前缀的字符串](image/Typical-3-way-string-quicksort-candidate.png)

[三向字符串快速排序](Quick3string.java)

![三向字符串快速排序递归调用轨迹](image/Trace-of-recursive-calls-for-3-way-string-quicksort.png)

>**命题E** 要将含有N个随机字符串的数组排序，三向字符串快速排序平均需要比较字符~2N*InN

**对于字符串类型的键**，标准的快速排序以及常用的排序算法实际上都是MSD，这是因为String类的compareTo()方法是从左到右访问字符串的所有字符的。也就是说，compareTo()在首字母不同时只会访问首字母，在首字母相同且第二个字母不同时只会访问前两个字母，等等。


### 字符串排序小结 ###

<table>

<tr>
<td rowspan=2>排序算法</td>
<td rowspan=2>是否稳定</td>
<td rowspan=2>原地排序</td>
<td colspan=2>在将基于大小为**R**的字母表的**N**个字符串排序的过程中调用charAt()方法次数的增长数量级(平均长度为**w**，最大长度为**W**)</td>
<td rowspan=2>优势领域</td>
</tr>

<tr>
<td>运行时间</td>
<td>额外空间</td>
</tr>

<tr>
<td>字符串的插入</td>
<td>是</td>
<td>是</td>
<td>(N, N^2)</td>
<td>1</td>
<td>小数组or已经有序的数组</td>
</tr>

<tr>
<td>快速</td>
<td>否</td>
<td>是</td>
<td>N(logN)^2</td>
<td>logN</td>
<td>通用排序算法，特别适用于空间不足的情况</td>
</tr>

<tr>
<td>归并</td>
<td>是</td>
<td>否</td>
<td>N(logN)^2</td>
<td>N</td>
<td>稳定的通用排序算法</td>
</tr>

<tr>
<td>三向快速排序</td>
<td>否</td>
<td>是</td>
<td>(N, NlogN)</td>
<td>logN</td>
<td>大量重复的键</td>
</tr>

<tr>
<td>低位优先字符串</td>
<td>是</td>
<td>否</td>
<td>NW</td>
<td>N</td>
<td>较短的定长字符串</td>
</tr>

<tr>
<td>高位优先字符串</td>
<td>是</td>
<td>否</td>
<td>(N, Nw)</td>
<td>N+WR</td>
<td>随机字符串</td>
</tr>

<tr>
<td>三向字符串快速排序</td>
<td>否</td>
<td>是</td>
<td>(N, Nw)</td>
<td>W+logN</td>
<td>通用排序算法，特别适用于含有较长公共前缀的字符串</td>
</tr>

</table>

## 单词查找树 ##

接下来算法在应用场景中(甚至对于巨型符号列表)都能够取得以下性能

- 查找命中所需的时间与被查找的键的长度成正比
- 查找未命中只需检查若干个字符

性能惊人

以字符串为键的符号表的API

StringST<Value>|-|-
---|---|---
-|StringST()|create a symbol table
void|put(String key, Value val)|put key-value pair into the table(remove key if value is null)
Value|get(String key)|value paired with key(null if key is absent)
void|delete(String key)|remove key (and its value)
boolean|contains(String key)|is there a value paired with key?
boolean|isEmpty()|is the table empty?
String|longestPrefixOf(String s)|the longest key that is a prefx of s
Iterable<String>|keysWithPrefix(String s)|all the keys having s as a prefx
Iterable<String>|keysThatMatch(String s)|all the keys that match s(where . matches any character)
int|size()|number of key-value pairs
Iterable<String>|keys()|all the keys in the table

### 单词查找树 ###

单词查找树Trie/音try/

![](image/Anatomy-of-a-trie.png)

**值为空的结点在符号表中没有对应的键，它们的存在是为了简化单词查找操作**

---

**单词查找树中的查找操作**

![](image/Trie-search-examples.png)

---

**单词查找树中的插入操作**

![](image/Trie-construction-trace-for-standard-indexing-client.png)

---

**结点的表示**

将空链接考虑进来将会突出单词查找树以下重要性质

- 每个结点都含有R个链接，对应着每个可能出现的字符
- 字符和键均隐式地保存在查询数据结构中

![](image/Trie-representation.png)


---

**大小**

- 即使实现(通过实例变量N,put()时就N++,delete()就N--)
- 延迟实现


	public int size(){
		return size(root); 
	}
	
	private int size(Node x){
		if (x == null) return 0;
		
		int cnt = 0;
		
		if (x.val != null) 
			cnt++;
		
		for (char c = 0; c < R; c++)
			cnt += size(next[c]);
		return cnt;
	}

[基于单词查找树的符号表](TrieST.java)

---

**查找所有键**

![收集一棵单词查找树中的所有键的轨迹](image/the-trace-Collecting-the-keys-in-a-trie.png)

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new Queue<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
        if (x == null) return;
        if (x.val != null) results.enqueue(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

![单词查找树中的前缀匹配](image/Prefix-match-in-a-trie.png)

---

**通配符匹配**

    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> results = new Queue<String>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
        if (x == null) return;
        int d = prefix.length();
        if (d == pattern.length() && x.val != null)
            results.enqueue(prefix.toString());
        if (d == pattern.length())
            return;
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

---

**最长前缀**

    public String longestPrefixOf(String query) {
        if (query == null) throw new IllegalArgumentException("argument to longestPrefixOf() is null");
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) return null;
        else return query.substring(0, length);
    }

    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d+1, length);
    }

![](image/Possibilities-for-longestPrefixOf.png)

---

**删除操作**

![](image/Deleting-a-key-from-a-trie.png)

    public void delete(String key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.val != null) n--;
            x.val = null;
        }
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.val != null) return x;
        for (int c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }

### 单词查找树的性质 ###

>**命题F** 单词查找树的链表结构(形状)和键的插入或删除顺序无关的：对任意给定的一组键，其单词查找树都是唯一的。

>**命题G** 在单词查找树中查找一个键或是插入一个键时，访问数组的次数最多为键的长度 + 1

>**命题H** 字母表大小为R，在一棵由N个随机键构造的单词查找树，未命中查找平均所需检查的结点数量为~logN/logR

>**命题I** 一棵单词查找树的链接总数在RN到RNw之间，其中w为键的平均长度

application|typical key|average length w|alphabet size R|links in trie built<br/>from 1 million keys
---|---|---|---|---
CA license plates|4PGC938|7|256|256 million
account numbers|02400019992993299111|20|256<br/>10|4 billion<br/>256 million
URLs|www.cs.princeton.edu|28|256|4 billion
text processing|seashells|11|256|256 million
proteins in <br/>genomic data|ACTGACTG|8|256<br/>4|256 million<br/>4 million


根据上表，可得出一些经验性的规律

- 当所有键均较短时，链接的总数接近于RN
- 当所有键均较长时，链接的总数接近于RNw
- 因此缩小R能够节省大量空间

**不要用 单词查找树算法 处理来自于 大型字母表 的 大量长键**

### 三向单词查找树 ###

为了避免R向单词查找树过度空间消耗，三向单词查找树TST粉墨登场。

TST中，每个结点都含有一个字符，三条连接和一个值。这三条连接分别对应着当前字母小于、等于和大于结点字母的所有键

![](image/TST-representation-of-a-trie.png)

![](image/TST-search-example.png)

[基于三向单词查找树的符号表](TrieST.java)

![](image/Trie-node-representations.png)

### 三向单词查找树的性质 ###

>**命题J** 由N个平均长度为w的字符串构造的三向单词查找树中的链接总数在3N到3Nw之间。

>**命题K** 在一棵由N个随机字符串构造的三向单词查找树中，查找未命中平均需要比较字符~InN次。除~InN次外，一次插入或命中的查找会比较一次被查找键中的每个字符

>**命题L** 由N个随机字符串构造的根结点进行R^t向分支且不含有外部单向分支的三向单词查找树中，一次插入或查找操作平均需要进行约InT-tInR次字符进行比较

### 单词查找树小结 ###

各种字符串查找算法的性能特点

<table>

<tr>

<td rowspan=2>算法或数据结构</td>
<td colspan=2>处理由大小为R的字母表构造的N个字符串(平均长度为w)的增长数量级</td>
<td rowspan=2>优点</td>

</tr>

<tr>

<td>未命中查找检查的字符数量</td>
<td>内存使用</td>

</tr>


<tr>

<td>二叉查找树BST</td>
<td>c1(lgN)^2</td>
<td>64N</td>
<td>适用于随机排列的键</td>

</tr>

<tr>

<td>红黑树</td>
<td>c2(lgN)^2</td>
<td>64N</td>
<td>性能保证</td>

</tr>

<tr>

<td>线性探测法</td>
<td>w</td>
<td>32N~128N</td>
<td>内置类型 缓存散列值</td>

</tr>

<tr>

<td>R向单词查找树</td>
<td>logN/logR</td>
<td>(8R+56)N~(8R+56)Nw</td>
<td>适用于较短的键和较小字母表</td>

</tr>

<tr>

<td>三向单词查找树</td>
<td>1.39lgN</td>
<td>64N~64Nw</td>
<td>适用于非随机的键</td>

</tr>

</table>

Java的系统排序方法**没有**使用了本节介绍的方法来查找String类型的键

## 子字符串查找 ##



















































































































