package tmp;

public class B1Impl implements B1, B2 {

  @Override
  public String a() {
    return null;
  }

  @Override
  public String aDefault() {
    return B1.super.aDefault();
  }
}
