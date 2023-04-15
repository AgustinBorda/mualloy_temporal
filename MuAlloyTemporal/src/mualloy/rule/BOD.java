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
import parser.ast.nodes.UnaryExpr;
import parser.ast.nodes.UnaryFormula;
import parser.ast.visitor.CloneVisitor;
import parser.etc.MutationData;
import parser.exception.UnsupportedOptionException;
import parser.opt.Opt;
import parser.util.AlloyUtil;

/**
 * Unary Operator Deletion.
 */
public class BOD {

  public static List<MutationData> mutate(BinaryExpr binaryExpr, ModelUnit mu, Opt opt, MInfo mi) {
    if (mi.getType() == MInfo.MType.IGNORE) {
      return null;
    }
    List<MutationData> res = new ArrayList<>();
    
	String replace = binaryExpr.accept(opt.getPSV(), null);
	String with = binaryExpr.getLeft().accept(opt.getPSV(), null);
	String mutatedNodePathAsString = AlloyUtil.computeNodePathAsString(binaryExpr.getLeft(), opt.getPSV());
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
        res.add(MutationData.of(mutatedNodePathAsString, mutant, MutantEquivalenceChecker
            .checkEquivalenceAndGenerateTest(equivModel, mi.getNode(), opt)));
      }
      
      with = binaryExpr.getRight().accept(opt.getPSV(), null);
  	  mutatedNodePathAsString = AlloyUtil.computeNodePathAsString(binaryExpr.getRight(), opt.getPSV());
      mutant = mu.accept(opt.getPSV(), null);
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
          res.add(MutationData.of(mutatedNodePathAsString, mutant, MutantEquivalenceChecker
              .checkEquivalenceAndGenerateTest(equivModel, mi.getNode(), opt)));
        }

    return res;
  }
  public static List<MutationData> mutate(BinaryFormula binaryFormula, ModelUnit mu, Opt opt, MInfo mi) {
	    if (mi.getType() == MInfo.MType.IGNORE) {
	      return null;
	    }
	    List<MutationData> res = new ArrayList<>();

		String replace = binaryFormula.accept(opt.getPSV(), null);
		String with = binaryFormula.getLeft().accept(opt.getPSV(), null);
		String mutatedNodePathAsString = AlloyUtil.computeNodePathAsString(binaryFormula.getLeft(), opt.getPSV());
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
	        res.add(MutationData.of(mutatedNodePathAsString, mutant, MutantEquivalenceChecker
	            .checkEquivalenceAndGenerateTest(equivModel, mi.getNode(), opt)));
	      }
	      
	      with = binaryFormula.getRight().accept(opt.getPSV(), null);
	  	  mutatedNodePathAsString = AlloyUtil.computeNodePathAsString(binaryFormula.getRight(), opt.getPSV());
	      mutant = mu.accept(opt.getPSV(), null);
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
	          res.add(MutationData.of(mutatedNodePathAsString, mutant, MutantEquivalenceChecker
	              .checkEquivalenceAndGenerateTest(equivModel, mi.getNode(), opt)));
	        }

	    return res;
	  }
}
