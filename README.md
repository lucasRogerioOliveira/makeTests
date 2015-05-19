# makeTests

Uma biblioteca para desenvolvedores Java com o propósito de facilitar a inclusão de testes unitários  em "sistemas legados".

##Como utilizar.

@Entity(name="foo")
public class Foo(){
  @Id
  @GeneratedValue
  private Integer id;
  
  private String name;
  private Date date;
  
  //gets e sets...
}

public class FooModel(){
  //seus métodos com csonexão de banco de dados CRUD e etc...
  static static list();
  static static findById(long id);
}

public static void main(String[] args) {
  Foo foo = new FooModel.findById(1111);
  //aqui utilizamos. Sendo o primeiro parâmetro o objeto a ser replicado como "sets" e o segundo se devem ser criados o "miolo" de cada método com retoro que possui.
  sysout(MakeTests.makeSettersWith(foo, false));
}


###saída:

import classes.foo.Foo;

public class FooTests(){
  public static Foo foo = new Foo();
  
  @Before
  public void beforeTest(){
    foo.setId(1);
    foo.setName("Lucas Rogério de Oliveira");
    Date date1 = new Date(1244323L);
    foo.setDate(date1);
  }
}
