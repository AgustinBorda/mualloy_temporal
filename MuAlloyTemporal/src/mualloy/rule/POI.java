package mualloy.rule;

import edu.mit.csail.sdg.ast.Type;
import java.util.ArrayList;
import java.util.List;
import mualloy.util.MInfo;
import mualloy.util.MutantEquivalenceChecker;
import parser.ast.nodes.ExprOrFormula;
import parser.ast.nodes.FieldExpr;
import parser.ast.nodes.ModelUnit;
import parser.ast.nodes.Paragraph;
import parser.ast.nodes.PredOrFun;
import parser.ast.nodes.SigExpr;
import parser.ast.nodes.UnaryExpr;
import parser.ast.visitor.CloneVisitor;
import parser.etc.MutationData;
import parser.exception.UnsupportedOptionException;
import parser.opt.Opt;
import parser.util.AlloyUtil;

/**
 * Prime Operator Insertion.
 */
public class POI {

  /**
   * Insert ' (prime) on mutable fields/signatures expressions in the model.
   */
  public static List<MutationData> mutate(ExprOrFormula exprOrFormula, ModelUnit mu, Opt opt, MInfo mi) {
	    List<MutationData> res = new ArrayList<>();
	    if (mi.getType() == MInfo.MType.IGNORE) {
	      return res;
	    }
	    // Ignore static fields
	    if (exprOrFormula instanceof FieldExpr && !((FieldExpr)exprOrFormula).isVariable()) {
	      return res;
	    }
	    
	    // Ignore static sigs
	    if (exprOrFormula instanceof SigExpr && !((SigExpr)exprOrFormula).isVariable()) {
	      return res;
	    }
	   	    
	    Type type = exprOrFormula.getType();
	    UnaryExpr parent = (UnaryExpr) exprOrFormula.getParent();
	    
	    UnaryExpr wrapper = new UnaryExpr(parent, type, exprOrFormula, UnaryExpr.UnaryOp.PRIME);
	    parent.setSub(wrapper);
        String mutatedNodePathAsString = AlloyUtil.computeNodePathAsString(wrapper, opt.getPSV());
        String mutant = mu.accept(opt.getPSV(), null);
        
        if (AlloyUtil.isValidModel(mutant)) {
          String equivModel;
          switch (mi.getType()) {
          case FIELD:
        	  //Ignore field type declarations in signature paragraphs
              parent.setSub(exprOrFormula);
        	  return res;
            case PRED:
            case FUN:
              PredOrFun mutatedPredOrFun = (PredOrFun) mi.getNode()
                  .accept(new CloneVisitor(), null);
              parent.setSub(exprOrFormula);
              equivModel = MutantEquivalenceChecker
                  .buildEquivModel(mu, mutatedPredOrFun, opt.getScope());
              break;
            case FACT:
            case ASSERT:
              Paragraph mutatedFactOrAssertion = (Paragraph) mi.getNode()
                  .accept(new CloneVisitor(), null);
              parent.setSub(exprOrFormula);
              equivModel = MutantEquivalenceChecker
                  .buildEquivModel(mu, mutatedFactOrAssertion, opt.getScope());
              break;
            default:
              throw new UnsupportedOptionException(
                  UOI.class.getSimpleName() + " for expression is not supported in " + mi
                      .getType());
          }
                    
          res.add(MutationData.of(mutatedNodePathAsString, mutant, MutantEquivalenceChecker
              .checkEquivalenceAndGenerateTest(equivModel, mi.getNode(), opt)));
        }
      
      parent.setSub(exprOrFormula);
    
    return res;
  }
}
