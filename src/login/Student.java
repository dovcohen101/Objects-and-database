package login;

public class Student
{
    public String name;
    public String surName;
    public int studentNo;
    public String subject;

    public Student(String name, String surName, int studentNo, String subject)
    {
        this.name = name;
        this.surName = surName;
        this.studentNo = studentNo;
        this.subject = subject;
    }

    public String getName()
    {
        return name;
    }

    public String getSurName()
    {
        return surName;
    }

    public int getStudentNo()
    {
        return studentNo;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSurName(String surName)
    {
        this.surName = surName;
    }

    public void setStudentNo(int studentNo)
    {
        this.studentNo = studentNo;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    
    
}
