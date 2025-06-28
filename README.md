# Java Syntaxes Notes


##### to_string ~ String.valueOf();
##### stoi ~ Integer.parseInt();

##### Sorting in desc order on first value [Arrays.sort(valueIndex, (a,b)-> b[0] - a[0]);]
##### Sorting in ascending order of second value from 0 to k [Arrays.sort(valueWithIndex, 0, k, (a, b) -> a[1] - b[1]);]  

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

#### -----------------------------------------------------------------------------------------------------------------------------------------------------------------
# Spring Boot Notes: Convention over Configuration

---

## 1. Spring Features

1. **POJO** (Plain Old Java Object)
2. **Dependency Injection**
3. **Security**
4. **MVC**
5. **REST**
6. **Data**
7. **Batch**
8. **AOP** (Aspect Oriented Programming)
   - Key unit of modularity in AOP is **Aspect**

---

## 2. Spring vs. Spring Boot Architecture

```text
Spring        : App (WAR) <-> Tomcat Server <-> OS <-> Hardware  
Spring Boot   : App (Embedded Tomcat) <-> OS <-> Hardware
```

---

## 3. Starters

- `spring-boot-starter-web`  
- `spring-boot-starter-jdbc`  
- ... _(others like security, data-jpa, batch, etc.)_

---

## 4. Bean Management & Dependency Injection

```java
// 1. Bootstrapping the ApplicationContext
ConfigurableApplicationContext context =
    SpringApplication.run(DemoApplication.class, args);

// 2. Retrieving Beans
Alien a  = context.getBean(Alien.class);
a.show();  // invokes show()

Alien a1 = context.getBean(Alien.class);
a1.show(); // new or same instance depending on scope
```

### 4.1 Defining a Prototype-Scoped Component

```java
@Component
@Scope("prototype")  // each getBean() returns a new instance
public class Alien {
    @Autowired
    @Qualifier("lap1")
    private Laptop laptop;

    public Alien() {
        System.out.println("Alien object created");
    }

    public void show() {
        System.out.println("In show()");
        laptop.compile();
    }
}

@Component("lap1")
public class Laptop {
    private int lid;
    private String brand;

    @Override
    public String toString() {
        return "Laptop [lid=" + lid + ", brand=" + brand + "]";
    }

    public void compile() {
        System.out.println("Compiling...");
    }
}
```

---

## 5. Building a Web App with Spring Boot

### 5.1 Simple Controller

```java
package com.rahul.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    // GET http://localhost:8080/home
    @RequestMapping("/home")
    public @ResponseBody String home() {
        return "home";
    }
}
```

### 5.2 Returning Views (JSP)

1. **Add Tomcat Jasper** to `pom.xml` for JSP support.  
2. **Configure** in `application.properties`:
   ```properties
   spring.mvc.view.prefix = /pages/
   spring.mvc.view.suffix = .jsp
   ```
3. **Controller** returns view name:
   ```java
   @RequestMapping("/home")
   public String home() {
       return "home";  // resolves to /pages/home.jsp
   }
   ```

### 5.3 Handling Request Parameters & Sessions

```java
@RequestMapping("/home")
public String home(@RequestParam("name") String myName,
                   HttpSession session) {
    session.setAttribute("name", myName);
    return "home";
}
```

_In JSP:_
```jsp
<body>
  Welcome ${name}
</body>
```

### 5.4 Using `ModelAndView`

```java
@RequestMapping("/home")
public ModelAndView home(@RequestParam("name") String myName) {
    ModelAndView mv = new ModelAndView("home");
    mv.addObject("name", myName);
    return mv;
}
```

---

## 6. Binding a Model Object

1. **Model Class** (`Alien`)  
2. **Controller**:

   ```java
   @Controller
   public class HomeController {
       @RequestMapping("/home")
       public ModelAndView home(Alien alien) {
           ModelAndView mv = new ModelAndView("home");
           mv.addObject("obj", alien);
           return mv;
       }
   }
   ```

3. **JSP**:
   ```jsp
   <body>
     ${obj.aid} ${obj.aname} ${obj.lang}
   </body>
   ```

---

## 7. Spring Boot + JPA + MVC + H2 Example

### Step 1: Define the Entity

```java
@Entity
public class Alien {
    @Id
    @GeneratedValue
    private int aid;
    private String aname;
    private String tech;

    // getters, setters, toString()
}
```

### Step 2: JSP Forms

```jsp
<body>
  <form action="addAlien">
    <input type="text" name="aid" /><br/>
    <input type="text" name="aname" /><br/>
    <input type="submit" />
  </form>

  <form action="getAlien">
    <input type="text" name="aid" /><br/>
    <input type="submit" />
  </form>
</body>
```

### Step 3: H2 Configuration (`application.properties`)

```properties
spring.h2.console.enabled = true
spring.datasource.platform   = h2
spring.datasource.url        = jdbc:h2:mem:rahul
```

### Step 4: Controller

```java
@Controller
public class AlienController {

    @Autowired
    private AlienRepo repo;

    @RequestMapping("/")
    public String home() {
        return "home.jsp";
    }

    @RequestMapping("/addAlien")
    public String addAlien(Alien alien) {
        repo.save(alien);
        return "home.jsp";
    }

    @RequestMapping("/getAlien")
    public ModelAndView getAlien(@RequestParam int aid) {
        ModelAndView mv = new ModelAndView("showAlien.jsp");
        Alien alien = repo.findById(aid).orElse(new Alien());
        System.out.println(repo.findByTech("Java"));
        System.out.println(repo.findByAidGreaterThan(102));
        System.out.println(repo.findByTechSorted("Java"));
        mv.addObject("alien", alien);
        return mv;
    }

    @RequestMapping("/aliens")
    @ResponseBody
    public List<Alien> getAliens() {
        return repo.findAll();
    }

    @RequestMapping(value = "/aliens", produces = { "application/xml" })
    @ResponseBody
    public List<Alien> getAliensXml() {
        return repo.findAll();
    }

    @RequestMapping("/alien/{aid}")
    @ResponseBody
    public Optional<Alien> getAlienById(@PathVariable int aid) {
        return repo.findById(aid);
    }
}
```

### Step 5: Repository Interface

```java
public interface AlienRepo extends CrudRepository<Alien, Integer> {
    List<Alien> findByTech(String tech);
    List<Alien> findByAidGreaterThan(int aid);

    @Query("from Alien where tech = ?1 order by aname")
    List<Alien> findByTechSorted(String tech);
}
```

### Step 6: Seed Data (`data.sql`)

```sql
insert into alien values (101, 'Rahul', 'Java');
insert into alien values (102, 'Kiran', 'Java');
insert into alien values (103, 'Fevdf', 'Java');
insert into alien values (104, 'Rahul', 'Java');
insert into alien values (105, 'Rahul', 'Java');
```

### Step 7: Display in JSP

```jsp
<body>
  ${alien}
</body>
```

---

## 8. Converting to a REST API

```java
@RestController
public class AlienController {

    @Autowired
    private AlienRepo repo;

    @PostMapping(path = "/alien", consumes = "application/json")
    public Alien addAlien(@RequestBody Alien alien) {
        return repo.save(alien);
    }

    @GetMapping("/aliens")
    public List<Alien> getAliens() {
        return repo.findAll();
    }

    @DeleteMapping("/alien/{aid}")
    public String deleteAlien(@PathVariable int aid) {
        repo.deleteById(aid);
        return "Deleted Successfully";
    }

    @PutMapping(path = "/alien", consumes = "application/json")
    public Alien updateAlien(@RequestBody Alien alien) {
        return repo.save(alien);
    }
}
```

---

## 9. Spring Data REST

Instead of writing controllers for basic CRUD, expose repositories directly:

```java
@RepositoryRestResource(collectionResourceRel = "aliens", path = "aliens")
public interface AlienRepo extends JpaRepository<Alien, Integer> { }
```

- **Endpoint**: `/aliens`  
- Provides full CRUD out of the box.

---

