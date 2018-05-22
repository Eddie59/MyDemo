package em.utilstream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * StreamAPI class
 *
 * @author Administrator
 * @date
 */
public class StreamAPI {

    @Test
    public void Run() {
        Student stuA = new Student(1, "eddie1", "M", 184, "sh");
        Student stuB = new Student(2, "eddie2", "G", 163, "hn");
        Student stuC = new Student(3, "eddie3", "M", 175, "sh");
        Student stuD = new Student(4, "eddie4", "G", 158, "bj");
        Student stuE = new Student(5, "eddie5", "M", 168, "sh");
        List<Student> list = new ArrayList<>();
        list.add(stuA);
        list.add(stuB);
        list.add(stuC);
        list.add(stuD);
        list.add(stuE);

        //func1
        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getSex().equals("G")) {
                System.out.println(student.getName());
            }
        }

        //func2
        list.stream().filter(x -> x.getSex().equals("G")).forEach(t -> {
            System.out.println(t.getName());
        });

        //func3
        Stream<Student> stuStream = list.stream().filter(x -> {
            System.out.println(x.getName());
            return true;
        });//惰性 这时并没有任务输出
        long cnt = stuStream.count();
        System.out.println(cnt);


        //查询单列
        Stream<String> stu1Steam = list.stream().filter(x -> true).map(x -> x.getName());
        stu1Steam.forEach(x -> System.out.println(x));
        stu1Steam = list.stream().filter(x -> true).map(Student::getName);


        //查询多列存放到另一model中
        Stream<Student1> stu2Steam = list.stream().filter(x -> true).map(x -> new Student1(x.getNo(), x.getName(), x.getSex()));//惰性
        List<Student1> stu1List = stu2Steam.collect(Collectors.toList());

        //查询多列存放到Map中,name为key (这种方法不是很好，应该用stream,collect,见下)
        Map<String, Student1> map = new HashMap<>();
        list.stream().filter(x -> true).map(x -> new Student1(x.getNo(), x.getName(), x.getSex())).forEach(x -> map.put(x.getName(), x));

        //Stream 转换为Collectors
        //Stream 转换为Set
        Set<Student> stuSet = list.stream().collect(Collectors.toSet());
        //Stream 转换为List
        List<Student> stuList = list.stream().collect(Collectors.toList());
        //Stream 转换为其它Collection
        LinkedList<Student> stuLinkList = list.stream().collect(Collectors.toCollection(() -> new LinkedList<>()));
        stuLinkList = list.stream().collect(Collectors.toCollection(LinkedList::new));
        //异常，待查
//        TreeSet<Student> stuTreeSet= list.stream().collect(Collectors.toCollection(()->new TreeSet<>()));
//        stuTreeSet= list.stream().collect(Collectors.toCollection(TreeSet::new));

        //Stream 转换为Map
        //如果Key重复，会异常
        Map<Integer, Student1> stuMap1 = list.stream().collect(Collectors.toMap(
                (x) -> x.getNo(),//key
                (x) -> new Student1(x.getNo(), x.getName(), x.getSex())//value
        ));
        //解决方法
        Map<Integer, Student1> stuMap2 = list.stream().collect(Collectors.toMap(
                (x) -> x.getNo(),
                (x) -> new Student1(x.getNo(), x.getName(), x.getSex()),
                (oldVal, newVal) -> oldVal//如果key重复，用原来的值
        ));
        //返回指定的Map类型
        Map<Integer, Student1> stuMap3 = list.stream().collect(Collectors.toMap(
                (x) -> x.getNo(),
                (x) -> new Student1(x.getNo(), x.getName(), x.getSex()),
                (o, n) -> o,
                () -> new TreeMap<>()
        ));


        //排序1 利用sorted方法 和collection array的sort方法，道理一样的
        Stream<Student> stuSort1 = list.stream().filter(x -> true).sorted(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) o1.height - (int) o2.height;
            }
        });
        stuSort1.forEach(x -> System.out.println(x.getName()));

        //使用lambda实现
        Stream<Student> stuSort2 = list.stream().filter(x -> true).sorted((s1, s2) -> (int) s1.height - (int) s2.height);
        stuSort2.forEach(x -> System.out.println(x.getName()));

        //多列排序
        //(抛弃)
        Stream<Student> stuSort3 = list.stream().filter(x -> true).sorted((s1, s2) -> {
            if (s1.height == s2.height) {
                return s1.hashCode() - s2.hashCode();
            } else {
                return (int) s1.height - (int) s2.height;
            }
        });
        stuSort3.forEach(x -> System.out.println(x.getName()));

        List<Student> stuSort4 = list.stream().filter(x -> true).sorted(
                Comparator.comparingDouble((Student x) -> x.getHeight())
                        .thenComparing((Student x) -> x.getName())
                        .thenComparing((Student x) -> x.getSex())
        ).collect(Collectors.toList());

        //分组
        //按姓别分组，返回的是一个Map,key为性别，value默认返回的是姓别为key的Student集合
        Map<String, List<Student>> stuGroup1 = list.stream().collect(Collectors.groupingBy(
                (x) -> x.getSex()
        ));
        //value返回set
        Map<String, Set<Student>> stuGroup2 = list.stream().collect(Collectors.groupingBy(
                (x) -> x.getSex(),
                Collectors.toSet()
        ));

        //按条件分组
        //身高170以下的一组，以上的一组
        Map<Boolean, List<Student>> stuGroup3 = list.stream().collect(Collectors.partitioningBy(
                (x) -> x.getHeight() <= 170.0F,
                Collectors.toList()
        ));
        //名字里面包含“1”的一组
        Map<Boolean, List<Student>> stuGroup4 = list.stream().collect(Collectors.partitioningBy(
                (x) -> x.getName().contains("1"),
                Collectors.toList()
        ));

        //多条件分组
        Map<List<String>, List<Student>> dualGroup = list.stream().collect(Collectors.groupingBy(
                (x) -> Arrays.asList(x.getSex(), x.getAddress()),
                Collectors.toList()
        ));

        //Group by 以后 把结果 转化为Set<Student1>
        Map<String, Set<Student1>> stuGroup7 = list.stream().collect(Collectors.groupingBy(
                (x) -> x.getSex(),
                Collectors.mapping(x -> new Student1(x.getNo(), x.getName(), x.getSex()), Collectors.toSet())
        ));

        //Group by 以后 求个数
        Map<String, Long> stuGroup5 = list.stream().collect(Collectors.groupingBy(
                (x) -> x.getSex(),
                Collectors.counting()
        ));
        //Group by 以后 求和
        Map<String, Double> stuGroup6 = list.stream().collect(Collectors.groupingBy(
                (x) -> x.getSex(),
                Collectors.summingDouble((x) -> x.getHeight())
        ));


    }
}
