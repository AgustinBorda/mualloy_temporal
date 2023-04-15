# MuAlloyT: A Mutation Testing Framework for Alloy Temporal Alloy Models

# Requirements:

* Operating Systems
  - Linux (64 bit)

* Dependencies
  - Java 8: Or higher must be installed and accessible from `PATH`.
  - Alloy 3.0: Must be in the classpath.  `MuAlloyT` comes with Alloy6 
    under `libs`.
  - Commons CLI 1.4: Must be in the classpath.  `MuAlloy` comes with
    Commons CLI under `libs`.

# Quick Start:

## Generate Mutants

To generate mutants, include the following arguments when running MutantGenerator.java

 * `-o,--model-path`: This argument is required.  Pass the model you
   want to mutate as the argument.
 * `-m,--mutant-dir`: This argument is required.  Pass the directory
   to which you want to save mutants as the argument.  If the
   directory does not exist, a new directory will be created.
 * `-s,--scope`: This argument is optional.  Pass the Alloy scope for
   equivalence checking, which is mainly used to prune equivalent
   mutants.  For each non-equivalent mutant, the scope will also be
   used to generate a run command for the corresponding `AUnit` test
   that kills the mutant.  If the argument is not specified, a default
   value of 3 is used.
 * `-t,--test-path`: This argument is optional.  Pass the path to
   which you want to save mutant killing test suite as the argument.
   If the argument is not specified, no mutant killing test suite will
   be generated.  Note that the generated test suite only contains
   unique test predicates and the corresponding run commands.


## Mutation Testing

To run mutation testing, include the following arguments when running MutationTestingRunner.java

 * `-o,--model-path`: This argument is required.  Pass the original
   model as the argument.  `MuAlloy` collects the test satisfiability
   result for the original model and then compare it with the test
   result for a mutant model.  If the results are different, then the
   mutant is killed.  Otherwise, it is not.
 * `-m,--mutant-dir`: This argument is required.  Pass the directory
   to which mutants are saved as the argument.  `MuAlloy` collects
   test results for each of the mutant models and checks if it can be
   killed by the test suite or not.
 * `-t,--test-path`: This argument is required.  Pass the test suite
   you want to run as the argument.  `MuAlloy` runs the test suite
   against the original model and mutant models to compute the
   mutation score for the test suite.  Note that the test suite should
   only contain the test predicates and the corresponding run
   commands.

## Included Examples

The models folder contains the temporal models from the alloy 6 release and unique Alloy4Fun benchmark submissions for the "train" and "trash" temporal models.
