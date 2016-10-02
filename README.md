# rock_paper_scissors
The Rock, Paper, Scissors game in Java

See https://en.wikipedia.org/wiki/Rock%E2%80%93paper%E2%80%93scissors for details.
This application allows to play RPS against computer or make two computer players play.
Available in GUI and command line modes.

BUILD
- Requirements: Maven 3, JDK 1.8.
- 'mvn clean package' to build 'rps.jar'.
- 'mvn clean site' to generate Maven site with Checkstyle and Cobertura reports.

USAGE
- Requirements: JRE 1.8.
- GUI application: 'java -jar rps.jar'.
- Command line application: 'java -jar rps.jar -cmd'.

NOTES ON EXTENSIBILITY AND FUTURE DEVELOPMENTS
- A variant with five symbols (as RPS with Spock and Lizard) can be implemented by adding symbols to the com.vladimiro.rps.core.Symbols enum. The command line application won't need any further refactoring while the GUI application would need some code changes in the com.vladimiro.rps.gui package.
- More strategies can be provided via com.vladimiro.rps.core.ComputerStrategyFactory. Meta-strategies, i.e. strategies that are function of one ore more other strategies, can be implemented via the Composite pattern.
- Strategies requiring heavy and parallelizable algorithms on big lists of symbols can take advantage of ExecutorService implementations. 