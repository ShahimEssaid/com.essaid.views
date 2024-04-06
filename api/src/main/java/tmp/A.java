package tmp;

public interface A {

  String a();

  default String aDefault() {
    return null;
  }

}
