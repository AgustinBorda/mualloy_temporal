package mualloy.util;

import static parser.etc.Names.DOLLAR;
import static parser.etc.Names.SLASH;

import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.alloy4.Err;
import edu.mit.csail.sdg.ast.Command;
import edu.mit.csail.sdg.ast.Expr;
import edu.mit.csail.sdg.ast.ExprVar;
import edu.mit.csail.sdg.ast.Func;
import edu.mit.csail.sdg.ast.Sig;
import edu.mit.csail.sdg.parser.CompModule;
import edu.mit.csail.sdg.parser.CompUtil;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Solution;
import edu.mit.csail.sdg.translator.A4Tuple;
import edu.mit.csail.sdg.translator.A4TupleSet;
import edu.mit.csail.sdg.translator.TranslateAlloyToKodkod;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import mualloy.opt.MutantGeneratorOpt;
import parser.ast.nodes.Function;
import parser.ast.nodes.Node;
import parser.ast.nodes.Paragraph;
import parser.ast.nodes.PredOrFun;
import parser.etc.Context;
import parser.etc.Names;
import parser.util.AlloyUtil;
import parser.util.FileUtil;
import parser.util.StringUtil;

public class TestGenerator {

  public static AUnitTestCase translateToTest(CompModule module, A4Solution sol, Node node,
      SpecialCase specialCase, String commandName, int scope) {
    try {
      StringBuilder body = new StringBuilder();
      StringBuilder sigDecls = new StringBuilder();
      StringBuilder endParens = new StringBuilder();
      StringBuilder relAssignments = new StringBuilder();
      boolean has_temporal = false;

      for (Sig sig : sol.getAllReachableSigs()) {
        String moduleAlias = StringUtil.beforeSubstring(sig.label, Names.SLASH, false);
        if (moduleAlias.equals("this")) {
        	int num_states = sol.getTraceLength();
        	String prime = "";
       	 	for(int i = 0; i < num_states; i++) {
       	 		String sigName = StringUtil.afterSubstring(sig.label, Names.SLASH, false);
	            A4TupleSet sigTupleSet = sol.eval(sig, i);

	             if (sigTupleSet.size() == 0) {
	            	 relAssignments.append("no ").append(sigName).append(prime).append(Names.NEW_LINE);
					 prime += "'";
	             } else {
	            	 if(i == 0 || sig.isVariable != null) {	            		   
						relAssignments.append(sigName).append(prime).append(" = ");
						String assignPrefix = "";
						for (A4Tuple tuple : sigTupleSet) {
							String sigValue = tuple.toString().replaceAll("\\" + DOLLAR, "");
							relAssignments.append(assignPrefix).append(sigValue);
							assignPrefix = " + ";
						}
						relAssignments.append(Names.NEW_LINE);
						prime += "'";
	            	 }	 	            	 
		         }
	         }
       	 	
       	 	//Get all variables of type
       	 	HashSet<String> atom_names = new HashSet<String>();
       	 	String name = "";
       	 	for(int i = 0; i < num_states; i++) {
       	 		if(sig.isTopLevel()) {
	       	 		String sigName = StringUtil.afterSubstring(sig.label, Names.SLASH, false);
	       	 		name = sigName;
	       	 		A4TupleSet sigTupleSet = sol.eval(sig, i);
					for (A4Tuple tuple : sigTupleSet) {
						String sigValue = tuple.toString().replaceAll("\\" + DOLLAR, "");
						atom_names.add(sigValue);
					}
       	 		}
       	 	}
       	    if(atom_names.size() > 0) {
       	    	String disj = "some disj ";
				String declPrefix = "";
       	    	for(String atom : atom_names) {
       	    		disj += declPrefix + atom;
       	    		declPrefix = ", ";
       	    	}
       	    	
       	    	disj += ": " + name + " {";
       	    	endParens.append("}");
       	 		sigDecls.append(disj);
       	    }       	
	             
	       	 prime = "";
	       	 for(int i = 0; i < num_states; i++) {
       			for (Sig.Field field : sig.getFields()) {
    	       		if(i == 0 || field.isVariable != null) {
		                 A4TupleSet fieldTupleSet = sol.eval(field, i);
		                 if (fieldTupleSet.size() == 0) {
		                   relAssignments.append("no ").append(field.label).append(prime).append(Names.NEW_LINE);
		                 } else {
		                   relAssignments.append(field.label).append(prime).append(" = ");
		                   String assignPrefix = "";
		                   for (A4Tuple tuple : fieldTupleSet) {
		                     String fieldValue = tuple.toString().replaceAll("\\" + DOLLAR, "");
		                     relAssignments.append(assignPrefix).append(fieldValue);
		                     assignPrefix = " + ";
		                   }
		                   relAssignments.append(Names.NEW_LINE);
		                 }
		             }
	        	 }	 
				prime += "'";
	       	 }
	       	 
	       	//Handle the loop state
	       	if(num_states > 0) {
	       	int loopstate = sol.getLoopState();
	       	String sigName = StringUtil.afterSubstring(sig.label, Names.SLASH, false);
            A4TupleSet sigTupleSet = sol.eval(sig, loopstate);

            if (sigTupleSet.size() == 0) {
           	 relAssignments.append("no ").append(sigName).append(prime).append(" --loop state" + Names.NEW_LINE);
            }
            else if (sigTupleSet.size() != 0) {
            	 if(sig.isVariable != null) {
            		 has_temporal = true;
            		 relAssignments.append(sigName).append(prime).append(" = ");
					String assignPrefix = "";
					for (A4Tuple tuple : sigTupleSet) {
						String sigValue = tuple.toString().replaceAll("\\" + DOLLAR, "");
						relAssignments.append(assignPrefix).append(sigValue);
						assignPrefix = " + ";
					}
					relAssignments.append(" --loop state");
					relAssignments.append(Names.NEW_LINE);
            	 }
	         }
             
             for (Sig.Field field : sig.getFields()) {
	    	        if(field.isVariable != null) {
	    	        	has_temporal = true;
		                 A4TupleSet fieldTupleSet = sol.eval(field, loopstate);
		                 if (fieldTupleSet.size() == 0) {
		                   relAssignments.append("no ").append(field.label).append(prime).append(" --loop state" + Names.NEW_LINE);
		                 } else {
		                   relAssignments.append(field.label).append(prime).append(" = ");
		                   String assignPrefix = "";
		                   for (A4Tuple tuple : fieldTupleSet) {
		                     String fieldValue = tuple.toString().replaceAll("\\" + DOLLAR, "");
		                     relAssignments.append(assignPrefix).append(fieldValue);
		                     assignPrefix = " + ";
		                   }
						   relAssignments.append(" --loop state");
		                   relAssignments.append(Names.NEW_LINE);
		                 }
		             }
	        	 }
	       	 }
          
        } else if (specialCase.getOrderingOpenDecls().stream()
            .anyMatch(openDecl -> openDecl.getAlias().equals(moduleAlias))) {
          for (Sig.Field field : sig.getFields()) {
            A4TupleSet fieldTupleSet = sol.eval(field);
            String fieldName = moduleAlias + Names.SLASH + field.label.toLowerCase();
            if (fieldTupleSet.size() == 0) {
              relAssignments.append("no ").append(fieldName).append(Names.NEW_LINE);
            } else {
              relAssignments.append(fieldName).append(" = ");
              String assignPrefix = "";
              for (A4Tuple tuple : fieldTupleSet) {
                String fieldValue = ignoreFirstKArity(tuple, 1).replaceAll("\\" + DOLLAR, "");
                relAssignments.append(assignPrefix).append(fieldValue);
                assignPrefix = " + ";
              }
              relAssignments.append(Names.NEW_LINE);
            }
          }
        }
        else if (sig.label.contains("Ord")) {
              for (Sig.Field field : sig.getFields()) {
                A4TupleSet fieldTupleSet = sol.eval(field);
                String fieldName = moduleAlias + Names.SLASH + field.label.toLowerCase();
                if (fieldTupleSet.size() == 0) {
                  relAssignments.append("no ").append(fieldName).append(Names.NEW_LINE);
                } else { 
                  relAssignments.append(fieldName).append(" = ");
                  String assignPrefix = "";
                  for (A4Tuple tuple : fieldTupleSet) {
                    String fieldValue = ignoreFirstKArity(tuple, 1).replaceAll("\\" + DOLLAR, "");
                    relAssignments.append(assignPrefix).append(fieldValue);
                    assignPrefix = " + ";
                  }
                  relAssignments.append(Names.NEW_LINE);
                }
              }
            }
      }
      sigDecls.append(Names.NEW_LINE);
      boolean generateCall = node instanceof Paragraph;
      if (generateCall) {
        List<String> args = new ArrayList<>();
        if (node instanceof PredOrFun) {
          List<String> argNames = new ArrayList<>();
          ((PredOrFun) node).getParamList()
              .forEach(paramDecl -> argNames.addAll(paramDecl.getNames()));
          Iterable<ExprVar> skolems = sol.getAllSkolems();
          for (String argName : argNames) {
            boolean foundSkolem = false;
            for (ExprVar skolem : skolems) {	
              String skolemName = skolem.label.substring(commandName.length() + 2);
              //if ((argName).equals(skolemName)) {
              if ((argName + "'").equals(skolemName)) {
                A4TupleSet skolemTupleSet = (A4TupleSet) sol.eval(skolem);
                StringBuilder argVal = new StringBuilder();
                String argPrefix = "";
                for (A4Tuple skolemTuple : skolemTupleSet) {
                  argVal.append(argPrefix).append(skolemTuple.toString());
                  argPrefix = " + ";
                }
                args.add(skolemTupleSet.size() == 0 ? "none" : argVal.toString());
                foundSkolem = true;
                break;
              }
            }
            if(!foundSkolem) {
            	for (ExprVar skolem : skolems) {	
                    String skolemName = skolem.label.substring(commandName.length() + 2);
                    if (argName.equals(skolemName)) {
                      A4TupleSet skolemTupleSet = (A4TupleSet) sol.eval(skolem);
                      StringBuilder argVal = new StringBuilder();
                      String argPrefix = "";
                      for (A4Tuple skolemTuple : skolemTupleSet) {
                        argVal.append(argPrefix).append(skolemTuple.toString());
                        argPrefix = " + ";
                      }
                      args.add(skolemTupleSet.size() == 0 ? "none" : argVal.toString());
                      foundSkolem = true;
                      break;
                    }
                  }
            }
            if (!foundSkolem) {
              args.add("none");
            }
          }
          //System.out.println("--");
          Paragraph paragraph = (Paragraph) node;
          String call =
              paragraph.getName().replaceAll("\\" + DOLLAR, "_") + "[" + String
                  .join(Names.COMMA, args) + "]";
          relAssignments.append(call.replaceAll("\\" + DOLLAR, ""));
          if (node instanceof Function) {
            Func targetFun = null;
            for (Func fun : module.getAllFunc()) {
              String functionName = StringUtil.afterSubstring(fun.label, SLASH, false);
              if (!fun.isPred && functionName.equals(((Function) node).getName())) {
                targetFun = fun;
                break;
              }
            }
            assert targetFun != null;
            List<ExprVar> skolemsList = new ArrayList<>();
            skolems.forEach(skolemsList::add);
            Expr functionCall = targetFun
                .call(skolemsList.toArray(new ExprVar[skolemsList.size()]));
            A4TupleSet resultTupleSet = (A4TupleSet) sol.eval(functionCall);
            StringBuilder value = new StringBuilder();
            String prefix = "";
            for (A4Tuple resultTuple : resultTupleSet) {
              value.append(prefix).append(resultTuple.toString());
              prefix = " + ";
            }
            String resultValuation = resultTupleSet.size() == 0 ? "none" : value.toString();
            relAssignments.append(" = ").append(resultValuation.replaceAll("\\" + DOLLAR, ""));
          }
          relAssignments.append(Names.NEW_LINE);
        }
      }
      body.append(sigDecls).append(relAssignments).append(endParens);
      //System.out.println(body);
      //System.out.println(sol);
      if(has_temporal)
    	  return new AUnitTestCase(Names.TEST_PREFIX, body.toString(), scope, -1, sol.getTraceLength());// + 1);
	  return new AUnitTestCase(Names.TEST_PREFIX, body.toString(), scope, -1, 1);
    } catch (Err er) {
      Context.logger.error("<Evaluator error occurred: " + er + ">");
    }
    return null;
  }

  public static void generateAndSaveAUnitTest(
      CompModule module, A4Solution sol, Node node, MutantGeneratorOpt opt) {
    AUnitTestCase test = translateToTest(module, sol, node, opt.getSpecialCase(),
        Names.EQUIV_ASSERTION_NAME, opt.getScope());
    opt.getTests().add(test);
  }

  private static String ignoreFirstKArity(A4Tuple a4Tuple, int k) {
    StringBuilder sb = new StringBuilder();
    for (int i = k; i < a4Tuple.arity(); i++) {
      if (i > k) {
        sb.append("->");
      }
      sb.append(a4Tuple.atom(i));
    }
    return sb.toString();
  }
}
