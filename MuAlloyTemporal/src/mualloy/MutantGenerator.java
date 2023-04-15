package mualloy;

import static parser.etc.Context.logger;
import static parser.etc.Names.MODEL_PATH;
import static parser.etc.Names.MUTANT_DIR;
import static parser.etc.Names.SCOPE;
import static parser.etc.Names.TEST_PATH;
import static parser.util.Util.printMutantGeneratorUsage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import edu.mit.csail.sdg.parser.CompModule;
import mualloy.opt.MutantGeneratorOpt;
import mualloy.util.AUnitTestCase;
import mualloy.visitor.ModelMutator;
import parser.ast.nodes.ModelUnit;
import parser.ast.nodes.SigDecl;
import parser.etc.Names;
import parser.util.AlloyUtil;
import parser.util.FileUtil;
import parser.util.StringUtil;

public class MutantGenerator {

  public static void mutate(MutantGeneratorOpt opt) {
	long startTime = System.currentTimeMillis();
    CompModule module = AlloyUtil.compileAlloyModule(opt.getModelPath());
    ModelUnit mu = new ModelUnit(null, module);
    // Mutate the model.
    ModelMutator mm = new ModelMutator(opt);
    mu.accept(mm, null);
    StringBuilder testSuite = new StringBuilder();
    int count = 1;
    for (AUnitTestCase testCase : opt.getTests()) {
      testSuite.append(testCase.toString(count++)).append("\n");
    }
    if (!opt.noTest()) { 
  	  String open = "";

      Path openPath = Paths.get(opt.getMutantDirPath()+ File.separatorChar + "open.als").toAbsolutePath();
      if (FileUtil.fileExists(openPath.toString())) {
    	  try {
    	      File myObj = new File(opt.getMutantDirPath()+ File.separatorChar + "open.als");
    	      Scanner myReader = new Scanner(myObj);
    	      while (myReader.hasNextLine()) {
    	        open += myReader.nextLine() + "\n";
    	      }
    	      myReader.close();
    	    } catch (Exception e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	    }   
    	  FileUtil.deleteFile(openPath.toString());
      }
      FileUtil.writeText(open + "\n" + testSuite.toString(), opt.getTestPath(), true);
    }
    long endTime = System.currentTimeMillis();

    String results = "";
    String modelFileName = StringUtil.afterSubstring(opt.getModelPath(), File.separator, true);
    String modelName = StringUtil.beforeSubstring(modelFileName, Names.DOT, true);

//    constrainUnivInTestSuite(opt, mm.getSigDecls());
    logger.info("Equivalent Mutant Number: " + mm.getEquivMutantNum());
    results += "Equivalent Mutant Number: " + mm.getEquivMutantNum() + "\n";
    logger.info("Non-Equivalent Mutant Number: " + mm.getNonEquivMutantNum());
    results += "Non-Equivalent Mutant Number: " + mm.getNonEquivMutantNum() + "\n";
    if (!opt.noTest()) {
      logger.info("Unique Test Number: " + opt.getTests().size());
      results += "Unique Test Number: " + opt.getTests().size() + "\n";
      results += "Generation Time with Tests: " + (endTime - startTime);
    }
    else
    	results += "Generation Time: " + (endTime - startTime) + "\n";
  }

  /**
   * This function is used to avoid the Alloy4.2 bug reported in https://github.com/AlloyTools/org.alloytools.alloy/issues/20
   */
  private static void constrainUnivInTestSuite(MutantGeneratorOpt opt, List<SigDecl> sigDecls) {
    String fact = "fact UnivConstraint {\nuniv = "
        + String.join(" + ", sigDecls.stream().map(SigDecl::getName).collect(Collectors.toList()))
        + "\n}";
    FileUtil.writeText(fact, opt.getTestPath(), true);
  }

  private static MutantGeneratorOpt parseCommandLineArgs(String[] args) {
    Options options = new Options();
    options.addRequiredOption("o", MODEL_PATH, true, "Path of the original model.");
    options.addRequiredOption("m", MUTANT_DIR, true, "Directory to save mutants.");
    options.addOption("s", SCOPE, true, "Scope to run equivalence checking.");
    options.addOption("t", TEST_PATH, true, "Path of mutant killing tests.");

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();

    try {
      CommandLine commandLine = parser.parse(options, args);
      Path modelPath = Paths.get(commandLine.getOptionValue(MODEL_PATH)).toAbsolutePath();
      if (!FileUtil.fileExists(modelPath.toString())) {
        logger.error("Cannot find model at " + modelPath);
        printMutantGeneratorUsage(formatter, options);
        return null;
      }
      //Clear directory for mutants and recreate it
      Path mutantDir = Paths.get(commandLine.getOptionValue(MUTANT_DIR)).toAbsolutePath();
      FileUtil.deleteDirectory(mutantDir.toAbsolutePath().toString());
      FileUtil.createDirsIfNotExist(mutantDir.toString());
      int scope = Integer.parseInt(commandLine.getOptionValue(SCOPE, "3"));
      //Create test path
      String testPath = commandLine.getOptionValue(TEST_PATH, null);
      if (testPath != null) {
        testPath = Paths.get(testPath).toAbsolutePath().toString();
        FileUtil.createDirsIfNotExist(Paths.get(testPath).getParent().toString());
        FileUtil.deleteFile(testPath);
      }
      return new MutantGeneratorOpt(modelPath.toString(), mutantDir.toString(), scope, testPath);
    } catch (ParseException e) {
      logger.error(e.getMessage());
      printMutantGeneratorUsage(formatter, options);
      return null;
    }
  } 

  public static void main(String[] args) {
	MutantGeneratorOpt opt;
	opt = parseCommandLineArgs(args);
	if (opt == null) {
	     return;
	}
	mutate(opt);
  }
}
