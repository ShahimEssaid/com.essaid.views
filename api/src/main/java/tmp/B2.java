package tmp;

public interface B2 extends A {

  String a();

  default String aDefault() {
    return "";
  }


}
