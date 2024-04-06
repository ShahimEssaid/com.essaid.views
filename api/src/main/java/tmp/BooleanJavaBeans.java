package tmp;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

public class BooleanJavaBeans {

  public static void main(String[] args) throws IntrospectionException {
    BeanInfo beanInfo = Introspector.getBeanInfo(Bean.class);

    System.out.println(beanInfo);
  }

  public static class Bean {

    public boolean isBooleanOne() {
      return true;
    }

    public Boolean isBooleanTwo() {
      return null;
    }

    public boolean getBooleanThree() {
      return false;
    }

    public Boolean getBooleanFour() {
      return null;
    }


  }

}
