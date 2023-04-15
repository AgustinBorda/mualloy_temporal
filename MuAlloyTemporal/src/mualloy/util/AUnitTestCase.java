package mualloy.util;

public class AUnitTestCase {

  String testName;
  String body;
  int scope;
  int expect;
  int length;

  public AUnitTestCase(String testName, String body, int scope, int expect, int length) {
	    this.testName = testName;
	    this.body = body;
	    this.scope = scope;
	    this.expect = expect;
	    this.length = length;
   }
  
  public AUnitTestCase(String testName, String body, int scope, int expect) {
    this.testName = testName;
    this.body = body;
    this.scope = scope;
    this.expect = expect;
    length = -1;
  }
  
  public AUnitTestCase(String testName, String body, int scope) {
	    this.testName = testName;
	    this.body = body;
	    this.scope = scope;
	    expect = -1;
	    length = -1;
   }

  public String toString(int num) {
    String name = testName + num;
    if(length == -1) {
    	if(expect == -1)
        	return "pred " + name + " {\n" + body + "\n}\nrun " + name + " for " + scope;
        return "pred " + name + " {\n" + body + "\n}\nrun " + name + " for " + scope + " expect "
            + expect;
    }
    else {
    	if(expect == -1)
        	return "pred " + name + " {\n" + body + "\n}\nrun " + name + " for " + scope + " but " + length + " steps";
        return "pred " + name + " {\n" + body + "\n}\nrun " + name + " for " + scope + " but " + length + " steps expect "
            + expect;
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof AUnitTestCase)) {
      return false;
    }
    AUnitTestCase testCase = (AUnitTestCase) obj;
    return body.equals(testCase.body);
  }

  @Override
  public int hashCode() {
    return body.hashCode();
  }
}
