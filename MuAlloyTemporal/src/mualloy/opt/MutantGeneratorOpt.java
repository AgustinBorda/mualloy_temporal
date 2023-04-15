package mualloy.opt;

import java.util.LinkedHashSet;
import java.util.Set;
import mualloy.util.AUnitTestCase;
import mualloy.util.SpecialCase;
import parser.opt.Opt;

public class MutantGeneratorOpt extends Opt {

  /**
   * Scope for equivalence checking.
   */
  protected int scope;
  /**
   * Special treatment for modules
   */
  protected SpecialCase specialCase;

  /**
   * Save generated tests.
   */
  protected Set<AUnitTestCase> tests;

  public MutantGeneratorOpt(String modelPath, String mutantDirPath, int scope, String testPath) {
    super(modelPath, mutantDirPath, testPath);
    this.mutantDirPath = mutantDirPath;
    this.scope = scope;
    this.specialCase = new SpecialCase();
    this.tests = new LinkedHashSet<>();
  }

  public int getScope() {
    return scope;
  }

  public SpecialCase getSpecialCase() {
    return specialCase;
  }

  public Set<AUnitTestCase> getTests() {
    return tests;
  }
}
