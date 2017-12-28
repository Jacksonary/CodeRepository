package com.hhu.repository;

import com.hhu.dao.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;

@RepositoryDefinition(domainClass = Employee.class,idClass = Integer.class)
public interface EmployeeRepository {
    /*注意，这里是声明成接口的方式，一般情况下，Repository是jpa包下的核心类，会带有
    两个泛型，像这样的形式Repository<T , ID>,其中T表明此Repository需要操作的对象，
    这个实例里面是对Employee的操作，而Employee实体类的主键为id是Integer类型，所以这
    里应该写成Repository<Employee, Integer>,前面定义id为Integer不定义成int，也只考
    虑到泛型这一点,这里一定要带上泛型，否则会报错，默认将id当成varchar类型但他是Integer!!

    Repository是空接口不提供任何方法，是个标记接口，和Serializable和Cloneable一样，但是不
    一样的是，继承这个接口会自动被Spring管理！！

    只要继承这个接口（送一个面试题，接口之间的继承使用的是extends关键字而不是implements
    关键字），那么那都不需要写实现类，当然除了继承这个接口，还可以不用继承的方式可以使用
    RepositoryDefintion这个注解的方式来代替,如下的方式：
    @RepositoryDefinition(domainClass = Employee.class,idClass = Integer.class)
     */


    /**
     * 自动查询的关键，千万不能乱写，jpa有自己的规则，这里如果写成test()那么是无法自动匹配查询的！！
     *
     * Repository的主要结构体系：
     * 1.内置CrudRepository接口(即内部帮我们写好了save,update,delete方法)
     * 具体的方法如下：
     * save(entity) findOne(id) finaAll() delete(entity) deleteAll()
     * save(entities) exists(id) delete(id) delete(entities)
     *
     *
     * 2.PagingAndSortingRepository(分页和排序)
     * 这个接口包含了分页和排序的功能，
     * 2.1带排序功能的查询
     * findAll(Sort sort)
     *
     * 2.2带排序的分页查询
     * findAll(Pageable pageable)
     *
     * 3.JpaRepository接口
     * 这个接口中主要有如下的一些方法：
     * findAll() findAll(Sort sort) save(entities) flush()  deleteInBatch(entities)
     *
     *
     * 4.JpaSpecificationExecutor接口
     * 这个接口封装了JPA Criteria的查询条件
     *
     *
     * jap的基本命名规则：
     * 1.And/Or
     * 即按多个条件进行查询，比如这里按照姓名和年龄去查询，那么方法名必须写成findByNameAndAge()
     * Or同理
     *
     * 2.Between/LessThan/GreaterThan
     * 查询分范围内的数据，必须带上范围字段，比如查询一定范围内的年龄，方法名必须为findByAgeBetween()
     *LessThan(小于)和GreaterThan(大于)同上
     *
     * 3.Like/NotLike
     * 即模糊匹配，必须带上匹配的字段，比如按名字模糊匹配(?ak)，那么方法名为findByNameLike()
     * NotLike同理
     * 等等。。。
     *
     * 4.StartingWith/EndingWith/Containing(较为常用)
     *  这三个是上诉第三点的具体版本，写法一样，它们模糊查询匹配如下：
     *  比如查询行年
     *
     * 5.OrderBy
     * 必须指明按哪个字段的何种方式排序，必须按年龄降序排列
     * 比如先按姓名查找然后按年龄降序
     * findByNameOrderByAgeDesc
     *
     *
     * 当然按照上述的规则定义方法名可能名字会非常长，另外一个是对于复杂的查询难以实现，
     * 这个时候可以利用@Query这个注解进行自定义书写SQL，而且使用这个注解的时候就可以
     * 丢弃上述的一套命名规则了,因为SQL语句此时是自己写的
     *
     *
     *
     * @param name
     * @return
     */
    public Employee findByName(String name);


    /**
     * 查询name模糊匹配并且age小于限定值
     */
    public List<Employee> findByNameStartingWithAndAgeLessThan(String name,Integer age);

    public List<Employee> findByNameEndingWithAndAgeGreaterThan(String name, Integer age);

    public List<Employee> findByNameContaining(String name);

    public List<Employee> findByNameLike(String name);

    /**
     * 查询名字在某一范围内(关键字In)或者年龄小于某一限定值，
     * 所以名字的方查询范围传入List集合
     */
    public List<Employee> findByNameInOrAgeLessThan(List<String> names, Integer age);

    /**
     * 获取id最大员工的信息
     *
     * @return
     */
    //注意：这里的不是写表名而是写类名
    @Query("select e from Employee e where id=(select max(id) from Employee e2)")//这个注解中直接写SQL语句即可
    public Employee getMaxId();

    //方式1：利用注解传入参数查询满足条件的记录，占位符的方式,即索引参数
    @Query("select e from Employee e where e.name=?1 and e.age=?2")
    public List<Employee> getTestRecords(String name,Integer age);


    //方式2：按照命名参数的方式来写SQL，只是将占位符换做了“:参数名”的方式，在形参上加上@Param的注解即可
    @Query("select e from Employee e where e.name=:name and e.age=:age")
    public List<Employee> getTestRecords2(@Param("name") String name, @Param("age")Integer id);

    //方式1：Like关键字,有点类似于containing了，用占位符，即索引参数
    @Query("select e from Employee e where e.name like %?1%")
    public List<Employee> queryLike1(String name);

    //方式2：Like关键字同样可以使用命名参数的方式
    @Query("select e from Employee e where e.name like %:name%")
    public List<Employee> queryLike2(@Param("name") String name);

    //打开原生的方式进行查询，一定要把nativeQuery设置为true，此时就可以用表名去查询
    @Query(nativeQuery = true, value = "select count(id) from employee")
    public long getCount();

    //写操作必须加Transactional和Modifying注解，只是事务注解一般是放在Service层的
    //@Transactional
    @Modifying//允许修改的意思
    @Query("update Employee e set e.age=:age where e.id=:id")
    public void update(@Param("age") Integer age, @Param("id") Integer id);
}
