# Java Syntaxes Notes


##### to_string ~ String.valueOf();
##### stoi ~ Integer.parseInt();

## StringBuilder
```
StringBuilder sb =  new StringBuilder("Hello");
sb.append("World");
System.out.Println(sb);// returns Hello World


StringBuilder sb =  new StringBuilder("Hello World");
sb.insert(5, ",");
System.out.Println(sb);// returns Hello, World


StringBuilder sb =  new StringBuilder("Hello World");
sb.delete(5,6);
System.out.Println(sb);// returns  HelloWorld


StringBuilder sb =  new StringBuilder("Hello World");
sb.deleteCharAt(5);
System.out.Println(sb);// returns HelloWorld

StringBuilder sb =  new StringBuilder("Hello World");
sb.replace(6,11,"Java");
System.out.Println(sb);// returns Hello Java


sb.reverse();
sb.substring(0,5);
sb.length();
sb.capacity(); // 16 By Default
sb.ensureCapacity(50);// increasing capacity of StringBuilder

```

## Map 
```
Map<Integer, Integer> mp = new HashMap<>();
insert->        mp.insert({key,value}); // forinsert
put->           mp.put({key,value}); // forupdate
get->           Integer val = mp.get(key);
remove ->       mp.remove(key);
clear ->        mp.clear();
containsKey->   if(mp.containsKey(key))
Iteration ->    for(Map.Entry<Integer,Integer> entry : mp.entrySet()){System.out.Println(entry.getKey() + ":" + entry.getValue());}
mp.putIfAbsent(key,value);
```

## Set

```
Set<Integer> set = new HashSet<>();
set.add("Hello");
set.remove("Hello");
boolean exists = set.contains("Hello");
boolean empty = set.isEmpty();
set.clear();
Interator<String> it = set.iterator();
while(it.hasNext()){System.out.Println(it.next);}

Set<String> anotherSet = new HashSet<>(Arrays.asList("One","Two"));

set.addAll(anotherSet);
boolean allExist = set.containsAll(anotherSet);
set.removeAll(anotherSet);
```

## Stack
```
Stack<Integer> st = new Stack<>();
st.push(10);
st.peek();
st.pop();
st.empty();
int pos = st.search(10);
```

## Queue

```
Queue<Integer> queue = new LinkedList<>();
queue.add(10);//throw ex if NO space
queue.offer(20);//throw false if NO space

### Retrive and removal of head
int head = queue.remove();// throw ex if empty
Integer head = queue.poll(); // throw null if empty

### Only Retireval of head
int headElement = queue.element();// throw ex if empty
Integer head = queue.peek(); throw null if empty

boolean isEmpty = queue.isEmpty();
int size = queue.size();


```


## PriorityQueue
```
PriorityQueue<Integer> pq = new PriorityQueue<>();

#### push
pq.add(30);//throws ex if limit exceeded
pq.offer(5);// throws null if limit exceeded


#### pop
Integer head = pq.remove();//throws ex
Integer head = pq.poll();// throws null if empty

#### top
Integer head = pq.peek();// return null
Integer head = pq.element();// return ex

pq.size();
pq.isEmpty();
Iterator<E> iterator();
```

## List / ArrayList

```
List<String> list = new ArrayList<>();
list.add("Hello"); // inserting to the back
list.add(0,"Hello");// inserting at particular index
list.get(0);
list.set(0,"Hi");
list.remove(0);
Iterator<String> it = list.iterator();
while(it.hasNext()){System.out.Println(it.next());}
```

