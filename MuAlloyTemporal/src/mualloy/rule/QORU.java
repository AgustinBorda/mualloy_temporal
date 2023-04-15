package mualloy.rule;

import static parser.util.AlloyUtil.getFirstNonNOOPChild;

import java.util.ArrayList;
import java.util.List;

import mualloy.util.MInfo;
import mualloy.util.MutantEquivalenceChecker;
import parser.ast.nodes.BinaryExpr;
import parser.ast.nodes.BinaryFormula;
import parser.ast.nodes.ExprOrFormula;
import parser.ast.nodes.ModelUnit;
import parser.ast.nodes.Paragraph;
import parser.ast.nodes.PredOrFun;
import parser.ast.nodes.QtFormula;
import parser.ast.nodes.UnaryExpr;
import parser.ast.nodes.UnaryFormula;
import parser.ast.nodes.UnaryFormula.UnaryOp;
import parser.ast.visitor.CloneVisitor;
import parser.etc.MutationData;
import parser.exception.UnsupportedOptionException;
import parser.opt.Opt;
import parser.util.AlloyUtil;

/**
 * Unary Operator Deletion.
 */
public class QORU {

  public static MutationData mutate(QtFormula qtFormula, ModelUnit mu, Opt opt, MInfo mi) {
    if (mi.getType() == MInfo.MType.IGNORE) {
      return null;
    }
    MutationData res = null;
    
    if(qtFormula.getOp() == QtFormula.Quantifier.ALL)
    	return res;
    
    UnaryOp unaryOp = null;
    if(qtFormula.getOp() == QtFormula.Quantifier.ONE)
    	unaryOp = UnaryOp.ONE;
    else if(qtFormula.getOp() == QtFormula.Quantifier.LONE)
    	unaryOp = UnaryOp.LONE;
    else if(qtFormula.getOp() == QtFormula.Quantifier.NO)
    	unaryOp = UnaryOp.NO;
    else if(qtFormula.getOp() == QtFormula.Quantifier.SOME)
    	unaryOp = UnaryOp.SOME;
    
    
    UnaryFormula wrapper = new UnaryFormula(qtFormula.getParent(), qtFormula.getType(), qtFormula.getVarDecls().get(0).getExpr(), unaryOp);
	String replace = qtFormula.accept(opt.getPSV(), null);
	String with = wrapper.accept(opt.getPSV(), null);
	String mutatedNodePathAsString = AlloyUtil.computeNodePathAsString(qtFormula, opt.getPSV());
    String mutant = mu.accept(opt.getPSV(), null);
    mutant = mutant.replace(replace,with);

    if (AlloyUtil.isValidModel(mutant)) {
        String equivModel;
        switch (mi.getType()) {
          case PRED:
          case FUN:
            PredOrFun mutatedPredOrFun = (PredOrFun) mi.getNode().accept(new CloneVisitor(), null);
            equivModel = MutantEquivalenceChecker
                    .buildEquivModel(mu, mutatedPredOrFun, opt.getScope());
                String insert [] = equivModel.split(mutatedPredOrFun.getName());
                equivModel = insert[0] + mutatedPredOrFun.getName() + insert[1].replace(replace, with) + mutatedPredOrFun.getName() +  insert[2];
               
            break;
          case FACT:
          case ASSERT:
            Paragraph mutatedFactOrAssertion = (Paragraph) mi.getNode()
                .accept(new CloneVisitor(), null);
            equivModel = MutantEquivalenceChecker
                    .buildEquivModel(mu, mutatedFactOrAssertion, opt.getScope());
                insert = equivModel.split(mutatedFactOrAssertion.getName());
                equivModel = insert[0] + mutatedFactOrAssertion.getName() + insert[1].replace(replace, with) + mutatedFactOrAssertion.getName() +  insert[2];
            break;
          default:
            throw new UnsupportedOptionException(
                UOD.class.getSimpleName() + " for expression is not supported in " + mi.getType());
        }
        res = MutationData.of(mutatedNodePathAsString, mutant, MutantEquivalenceChecker
            .checkEquivalenceAndGenerateTest(equivModel, mi.getNode(), opt));
      }

    return res;
  }
}
