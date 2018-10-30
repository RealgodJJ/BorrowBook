package reagodjj.example.com.borrowbook;

public class Person {
    private String name;
    private String sex;
    private int age;
    private String borrowTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    @Override
    public String toString() {
        return "显示：Person [per_name=" + name + ", sex=" + sex + ", age=" + age + ", time=" + borrowTime + "]";
    }
}
