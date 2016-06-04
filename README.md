JIMDB (Java In Memory DataBase)
-------------------------------
#### What is JIMDB? ####
JIMDB is a lightweight Java In Memory DataBase which can store any Java Bean. We can do any basic sql database operations like select, insert, update and delete. JIMDB especially developed for better performance than normal sql database. Performance improved by creating indexes, efficient data structure used to store the beans and storing the beans in heap memory. When we handle with more than millions of records, JIMDB will be good replacement in performance wise. 

#### JIMDB Syntax with Example ####

> **Note:**
> In the below examples, Employee class is used and the class will be look like

```java
public class Employee {
	private String id;
	private String name;
	private String gender;
	private float salary;
	private String department;
	private String address;
	private String mailId;
	public Employee(String id, String name, String gender, float salary, String department, String address, String mailId) {
		// Initialize fields
	}
	// setters and getters
```

 **Create Table**

*Syntax*
```java
Table<T> table = new Table<T>(maxNoOfRecords, new IndexConfig(<field 1>, <field 2>, ...));
```
 - **T:** Type of the Bean which going to be stored in Table. It can be any Java Bean class.
 - **maxNoOfRecords:** Maximum number of beans going to be stored in Table. This can be approximate value.
 - **IndexConfig:** IndexConfig contains whatever fields going to be used in select operations and indexes will be created for those fields.

*Example*
```java
Table<Employee> table = new Table<Employee>(100, new IndexConfig("department", "address"));
```

**Insert beans into Table**

*Syntax*
```java
table.insert(T);
table.insert(List<T>);
```
- **insert(T):** To insert a Bean object.
- **insert(List<T>)** To insert list of bean objects.

*Example*
```java
table.insert(new Employee("eid02", "Alan", "male", 20000, "HR", "Bangalore", "alan@gmail.com"));

List<Employee> list = new ArrayList<Employee>();
list.add(new Employee("eid02", "Alan", "male", 20000, "HR", "Bangalore", "alan@gmail.com"));
list.add(new Employee("eid16", "Shiny", "female", 60000, "QA", "Chennai", "shiny@gmail.com"));
list.add(new Employee("eid11", "James", "male", 50000, "IT", "Bangalore", "james@gmail.com"));
table.insert(list);
```

**Find beans from Table**

*Syntax*
```java
List<T> list = table.find(new Filter(new Filter(<field 1>, Op.<any operator>, <value>), LogOp.<any operator>, new Filter(...)));
```
- **find:** Method to find bean objects from Table by passing filter criteria.
- **new Filter():** This constructor accepts field name, operator and field value to match bean objects. Inner constructor to perform nested operations and will accept filter objects and logical operator.
- **Op:** enum to have all relational and some sql related operators.
- **LogOp:** enum to have all relational operators.

*Example*
```java
List<Employee> employees = table.find(new Filter(new Filter(new Filter("department", Op.EQ, "IT"), LogOp.OR, new Filter("department", Op.EQ, "QA")), LogOp.AND, new Filter("address", Op.EQ, "Bangalore")));
```

> **Note:**
> - Full example you can find from the above src/test/java/com/jimdb path
> - Currently Table will support only for few operations and the remaining operations are under development. Yo can expect soon.

