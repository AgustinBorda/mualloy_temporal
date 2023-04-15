package mualloy.rule;

import java.util.ArrayList;
import java.util.List;
import mualloy.util.MInfo;
import mualloy.util.MutantEquivalenceChecker;
import parser.ast.nodes.FieldDecl;
import parser.ast.nodes.ModelUnit;
import parser.ast.nodes.SigDecl;
import parser.etc.MutationData;
import parser.exception.UnsupportedOptionException;
import parser.opt.Opt;
import parser.util.AlloyUtil;

/**
 * Variable Operator Insertion
 */
public class VOI {

  public static List<MutationData> mutate(SigDecl sigDecl, ModelUnit mu, Opt opt, MInfo mi) {
    List<MutationData> res = new ArrayList<>();
    if (mi.getType() == MInfo.MType.IGNORE) {
      return res;
    }
    boolean original = sigDecl.isVariable();
    
    if(sigDecl.isVariable())
    	return res; //If already dynamic, leave
    
      sigDecl.setVariable(true);
//            String mutatedPartAsString = sigDecl.accept(opt.getPSV(), null);
      String mutatedNodePathAsString = AlloyUtil.computeNodePathAsString(sigDecl, opt.getPSV());
      String mutant = mu.accept(opt.getPSV(), null);
      // Check if the mutant compiles.
      if (AlloyUtil.isValidModel(mutant)) {
        // Check if the mutant is equivalent to the original model.
        String equivModel;
        switch (mi.getType()) {
          case SIG:
            equivModel = MutantEquivalenceChecker
                .buildEquivModel(mu, (SigDecl) mi.getNode(), true, opt.getScope());
            break;
          default:
            throw new UnsupportedOptionException(
                VOI.class.getSimpleName() + " is not supported in " + mi.getType());
        }
        res.add(MutationData.of(mutatedNodePathAsString, mutant, MutantEquivalenceChecker
            .checkEquivalenceAndGenerateTest(equivModel, mi.getNode(), opt)));
      }
    
    sigDecl.setVariable(original);
    return res;
  }
  
  public static List<MutationData> mutate(FieldDecl fieldDecl, ModelUnit mu, Opt opt, MInfo mi) {
	    List<MutationData> res = new ArrayList<>();
	    if (mi.getType() == MInfo.MType.IGNORE) {
	      return res;
	    }
	    boolean original = fieldDecl.isVariable();
	    
	    if(fieldDecl.isVariable())
	    	return res; //If already dynamic, leave
	    
	    fieldDecl.setVariable(true);
//	            String mutatedPartAsString = sigDecl.accept(opt.getPSV(), null);
	      String mutatedNodePathAsString = AlloyUtil.computeNodePathAsString(fieldDecl, opt.getPSV());
	      String mutant = mu.accept(opt.getPSV(), null);
	      // Check if the mutant compiles.
	      if (AlloyUtil.isValidModel(mutant)) {
	        // Check if the mutant is equivalent to the original model.
	        String equivModel;
	        switch (mi.getType()) {
	          case FIELD:
	            equivModel = MutantEquivalenceChecker
	                .buildEquivModel(mu, (FieldDecl) mi.getExtraNode(), true, opt.getScope());
	            break;
	          default:
	            throw new UnsupportedOptionException(
	                VOI.class.getSimpleName() + " is not supported in " + mi.getType());
	        }
	        res.add(MutationData.of(mutatedNodePathAsString, mutant, MutantEquivalenceChecker
	            .checkEquivalenceAndGenerateTest(equivModel, mi.getNode(), opt)));
	      }
	    
	      fieldDecl.setVariable(original);
	    return res;
	  }
}
