package edu.eec.grammar;

import edu.eec.reportutils.ReportUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class is a part of the package edu.eec.parser and the package
 * is a part of the project ExpressionGrammar.
 * <p>
 * Semantro/Integrated ICT Pvt. Ltd. Lalitpur, Nepal.
 * https://semantro.com/
 * https://integratedict.com.np/
 * <p>
 * Created by santa on 2022-11-25.
 */
public class ExpressionGrammar implements EduGrammar {

    /**
     * Initialize the grammar (LL(1)) for the arithmetic expression.
     */
    private final List<ProductionRule> grammarProductionRules;

    /**
     * The first non-terminal.
     */
    private final GrammarSymbol firstNT;

    /**
     * Default Constructor.
     */
    public ExpressionGrammar() {
        this.firstNT = firstRule().getLeftPart();
        this.grammarProductionRules = initializeRules();
    }

    /**
     * Presentation view of the Grammar representation.
     *
     * @return string representation of the presentation view.
     */
    @Override
    public String presentation() {
        StringBuffer buffer = new StringBuffer();
        System.out.println();

        buffer.append(ProductionRule.getTitle());
        buffer.append(ReportUtils.getLineSeparator());
        grammarProductionRules.forEach(productionRule -> {
            buffer.append(productionRule.presentation());
            buffer.append("\n");
        });
        buffer.append(ReportUtils.getLineSeparator());
        return buffer.toString();
    }


    /**
     * CFG definitions.
     * Grammar definition.
     * 1. E  -> T E'
     * 2. E' -> + T E' | - T E' | epsilon
     * 3. T  -> F T'
     * 4. T' -> * F T' | / F T' | epsilon
     * for demo,  F  -> (E) | id
     * <p>
     * 5. F  -> U V'
     * 6. V' -> ^ U V' | % U V' | epsilon
     * 7. U  -> (E) | id | constant
     */
    @Override
    public List<ProductionRule> initializeRules() {
        return Arrays.asList(
                firstRule(),
                secondARule(),
                secondBRule(),
                secondCRule(),
                thirdRule(),
                forthARule(),
                forthBRule(),
                forthCRule(),
                fifthRule(),
                sixthARule(),
                sixthBRule(),
                sixthCRule(),
                seventhARule(),
                seventhBRule(),
                seventhCRule()
        );
    }

    /**
     * 1. First ProductionRule: E -> T E'
     */
    private ProductionRule firstRule() {
        return ProductionRule.empty().withLeftPart(buildE()).withRightPart(buildTEPrime());
    }

    /**
     * 2. Second ProductionRule:  E' -> + T E' | - T E' | epsilon
     */
    private ProductionRule secondARule() {
        return ProductionRule.empty().withLeftPart(buildEPrime()).withRightPart(buildPlusTEPrime());
    }

    private ProductionRule secondBRule() {
        return ProductionRule.empty().withLeftPart(buildEPrime()).withRightPart(buildMinusTEPrime());
    }

    private ProductionRule secondCRule() {
        return ProductionRule.empty().withLeftPart(buildEPrime()).withRightPart(buildEpsilon());
    }

    /**
     * 3. Third ProductionRule: T  -> F T'
     */
    private ProductionRule thirdRule() {
        return ProductionRule.empty().withLeftPart(buildT()).withRightPart(buildFTPrime());
    }

    /**
     * 4. Forth ProductionRule: T' -> * F T' | / F T' | epsilon
     */
    private ProductionRule forthARule() {
        return ProductionRule.empty().withLeftPart(buildTPrime()).withRightPart(buildStarFTPrime());
    }

    private ProductionRule forthBRule() {
        return ProductionRule.empty().withLeftPart(buildTPrime()).withRightPart(buildSlashFTPrime());
    }

    private ProductionRule forthCRule() {
        return ProductionRule.empty().withLeftPart(buildTPrime()).withRightPart(buildEpsilon());
    }

    /**
     * 5. Seventh ProductionRule: F -> (E) | id
     */
    private ProductionRule fifthARule() {
        return ProductionRule.empty().withLeftPart(buildU()).withRightPart(buildParenthesisE());
    }

    private ProductionRule fifthBRule() {
        return ProductionRule.empty().withLeftPart(buildU()).withRightPart(buildId());
    }

    /**
     * 5. Fifth ProductionRule: F  -> U V'
     */
    private ProductionRule fifthRule() {
        return ProductionRule.empty().withLeftPart(buildF()).withRightPart(buildUVPrime());
    }

    /**
     * 6. Sixth ProductionRule: V' -> ^ U V' | % U V' | epsilon
     */
    private ProductionRule sixthARule() {
        return ProductionRule.empty().withLeftPart(buildVPrime()).withRightPart(buildExponentUVPrime());
    }

    private ProductionRule sixthBRule() {
        return ProductionRule.empty().withLeftPart(buildVPrime()).withRightPart(buildPeriodUVPrime());
    }

    private ProductionRule sixthCRule() {
        return ProductionRule.empty().withLeftPart(buildVPrime()).withRightPart(buildEpsilon());
    }

    /**
     * 7. Seventh ProductionRule: U -> (E) | id | constant
     */
    private ProductionRule seventhARule() {
        return ProductionRule.empty().withLeftPart(buildU()).withRightPart(buildParenthesisE());
    }

    private ProductionRule seventhBRule() {
        return ProductionRule.empty().withLeftPart(buildU()).withRightPart(buildId());
    }

    private ProductionRule seventhCRule() {
        return ProductionRule.empty().withLeftPart(buildU()).withRightPart(buildConstant());
    }

    /**
     * Build non-terminal with name E.
     */
    private GrammarSymbol buildE() {
        return buildNTWithName(GrammarLiterals.E);
    }

    /**
     * Build non-terminal with name T.
     */
    private GrammarSymbol buildT() {
        return buildNTWithName(GrammarLiterals.T);
    }

    /**
     * Build non-terminal with name F.
     */
    private GrammarSymbol buildF() {
        return buildNTWithName(GrammarLiterals.F);
    }

    /**
     * Build non-terminal with name U.
     */
    private GrammarSymbol buildU() {
        return buildNTWithName(GrammarLiterals.U);
    }

    /**
     * Build non-terminal name E'.
     */
    private GrammarSymbol buildEPrime() {
        return buildNTWithName(GrammarLiterals.EPRIME);
    }

    /**
     * Build non-terminal name T'.
     */
    private GrammarSymbol buildTPrime() {
        return buildNTWithName(GrammarLiterals.TPRIME);
    }

    /**
     * Build non-terminal name V'.
     */
    private GrammarSymbol buildVPrime() {
        return buildNTWithName(GrammarLiterals.VPRIME);
    }

    /**
     * Build terminal name +.
     */
    private GrammarSymbol buildPlus() {
        return buildTWithName(GrammarLiterals.ADD);
    }

    /**
     * Build terminal name -.
     */
    private GrammarSymbol buildMinus() {
        return buildTWithName(GrammarLiterals.SUBTRACT);
    }

    /**
     * Build terminal name *.
     */
    private GrammarSymbol buildStar() {
        return buildTWithName(GrammarLiterals.MULTIPLY);
    }

    /**
     * Build terminal name /.
     */
    private GrammarSymbol buildSlash() {
        return buildTWithName(GrammarLiterals.DIVIDE);
    }

    /**
     * Build terminal name ^.
     */
    private GrammarSymbol buildExponent() {
        return buildTWithName(GrammarLiterals.EXPONENT);
    }

    /**
     * Build terminal name %.
     */
    private GrammarSymbol buildPeriod() {
        return buildTWithName(GrammarLiterals.PERIOD);
    }

    /**
     * Build terminal name (.
     */
    private GrammarSymbol buildLeftParenthesis() {
        return buildTWithName(GrammarLiterals.LEFT_PARENTHESIS);
    }

    /**
     * Build terminal name ).
     */
    private GrammarSymbol buildRightParenthesis() {
        return buildTWithName(GrammarLiterals.RIGHT_PARENTHESIS);
    }

    /**
     * Build right part with epsilon.
     */
    private List<GrammarSymbol> buildEpsilon() {
        return Arrays.asList(buildTEpsilon());
    }

    /**
     * Build right part with identifier.
     */
    private List<GrammarSymbol> buildId() {
        return Arrays.asList(buildTId());
    }

    /**
     * Build right part with constant.
     */
    private List<GrammarSymbol> buildConstant() {
        return Arrays.asList(buildTConstant());
    }

    /**
     * Build terminal name epsilon.
     */
    private GrammarSymbol buildTEpsilon() {
        return buildTWithName(GrammarLiterals.EPSILON);
    }

    /**
     * Build terminal name identifier.
     */
    private GrammarSymbol buildTId() {
        return buildTWithName(GrammarLiterals.ID);
    }

    /**
     * Build terminal name constant.
     */
    private GrammarSymbol buildTConstant() {
        return buildTWithName(GrammarLiterals.CONSTANT);
    }

    /**
     * Build non-terminal with given name.
     */
    private GrammarSymbol buildNTWithName(String name) {
        return NonTerminal.empty().buildWithName(name);
    }

    /**
     * Build terminal of identifier with given name.
     */
    private GrammarSymbol buildTWithName(String name) {
        return Terminal.empty().buildWithName(name);
    }

    /**
     * Build right part with T E'.
     */
    private List<GrammarSymbol> buildTEPrime() {
        return Arrays.asList(buildT(), buildEPrime());
    }

    /**
     * Build right part with + T E'.
     */
    private List<GrammarSymbol> buildPlusTEPrime() {
        return Arrays.asList(buildPlus(), buildT(), buildEPrime());
    }

    /**
     * Build right part with - T E'.
     */
    private List<GrammarSymbol> buildMinusTEPrime() {
        return Arrays.asList(buildMinus(), buildT(), buildEPrime());
    }

    /**
     * Build right part with * F T'.
     */
    private List<GrammarSymbol> buildStarFTPrime() {
        return Arrays.asList(buildStar(), buildF(), buildTPrime());
    }

    /**
     * Build right part with / F T'.
     */
    private List<GrammarSymbol> buildSlashFTPrime() {
        return Arrays.asList(buildSlash(), buildF(), buildTPrime());
    }

    /**
     * Build right part with F T'.
     */
    private List<GrammarSymbol> buildFTPrime() {
        return Arrays.asList(buildF(), buildTPrime());
    }

    /**
     * Build right part with  U V'.
     */
    private List<GrammarSymbol> buildUVPrime() {
        return Arrays.asList(buildU(), buildVPrime());
    }

    /**
     * Build right part with ^ U V'.
     */
    private List<GrammarSymbol> buildExponentUVPrime() {
        return Arrays.asList(buildExponent(), buildU(), buildVPrime());
    }

    /**
     * Build right part with ^ U V'.
     */
    private List<GrammarSymbol> buildPeriodUVPrime() {
        return Arrays.asList(buildPeriod(), buildU(), buildVPrime());
    }

    /**
     * Build right part with (E).
     */
    private List<GrammarSymbol> buildParenthesisE() {
        return Arrays.asList(buildLeftParenthesis(), buildE(), buildRightParenthesis());
    }

    /**
     * A factory for the expression grammar.
     */
    public static ExpressionGrammar init() {
        return new ExpressionGrammar();
    }

    /**
     * Returns all the grammar rules.
     */
    @Override
    public List<ProductionRule> allRules() {
        return this.grammarProductionRules;
    }

    /**
     * Returns all the Non-Terminals.
     */
    @Override
    public List<GrammarSymbol> allNonTerminals() {
        return this.grammarProductionRules.stream().map(ProductionRule::getLeftPart).distinct().collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    /**
     * Returns all the Non-Terminals.
     */
    @Override
    public List<GrammarSymbol> allTerminals() {
        return this.grammarProductionRules.stream()
                .flatMap(productionRule -> productionRule.getRightPart().stream())
                .filter(symbol -> (symbol instanceof Terminal))
                .collect(Collectors.toList());
    }

    /**
     * Check if the grammar is of empty instance or not.
     */
    @Override
    public boolean isEmpty() {
        return this.grammarProductionRules.isEmpty();
    }

    /**
     * Accumulate and group production rule by the Non-terminal symbol.
     */
    @Override
    public Map<GrammarSymbol, List<ProductionRule>> ntProductionRules() {
        return this.grammarProductionRules.stream().collect(Collectors.groupingBy(ProductionRule::getLeftPart));
    }

    /**
     * The first non-terminal.
     */
    @Override
    public GrammarSymbol firstNonTerminal() {
        return this.firstNT;
    }


}
