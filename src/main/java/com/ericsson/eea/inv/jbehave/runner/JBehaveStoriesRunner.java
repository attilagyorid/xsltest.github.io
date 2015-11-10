package com.ericsson.eea.inv.jbehave.runner;

import com.ericsson.eea.inv.jbehave.configuration.SpringConfiguration;
import com.ericsson.eea.inv.jbehave.reporters.FailedStepsReporter;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.*;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.jbehave.core.reporters.Format.*;

public class JBehaveStoriesRunner extends JUnitStories {

    private static final List<String> DEFAULT_INCLUDE_STORIES_LIST = new ArrayList<String>(Arrays.asList("**/stories/*.story"));
    private static final List<String> DEFAULT_EXCLUDE_STORIES_LIST = new ArrayList<String>(Arrays.asList("**/stories/excluded*.story"));
    private ConfigurableApplicationContext context;
    private final static StoryReporter storyReporter = new FailedStepsReporter();
    private final static String SPRING_ACTIVE_PROFILE = "firefox";

    public JBehaveStoriesRunner() {
        configuredEmbedder()
                .embedderControls()
                .doGenerateViewAfterStories(true)
                .doIgnoreFailureInStories(false)
                .doIgnoreFailureInView(false)
                .doIgnoreFailureInStories(false)
                .doVerboseFailures(false)
                .doVerboseFiltering(false)
                .useThreads(1);
        configuredEmbedder().useMetaFilters(asList(""));
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        ParameterConverters parameterConverters = new ParameterConverters();
        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(), new LoadFromClasspath(embeddableClass), parameterConverters);
        parameterConverters.addConverters(
                new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
                new ParameterConverters.ExamplesTableConverter(examplesTableFactory)
        );
        ParameterControls parameterControls = new ParameterControls();
        parameterControls.useDelimiterNamedParameters(true);
        StepCollector stepCollector = new MarkUnmatchedStepsAsPending(new StepFinder(new StepFinder.ByLevenshteinDistance()));
        return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(embeddableClass)).usePendingStepStrategy(new FailingUponPendingStep())
                .useStoryParser(new RegexStoryParser(examplesTableFactory))
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                                .withDefaultFormats()
                                .withReporters(storyReporter)
                                .withFormats(HTML, XML, CONSOLE, TXT)
                )
                .useParameterConverters(parameterConverters)
                .useParameterControls(parameterControls)
                .useStepCollector(stepCollector);
    }


    private static List<String> getIncludedStories() {
        String includedFeatures = System.getProperty("includedStories");
        if (!Optional.ofNullable(includedFeatures).isPresent()) {

            return DEFAULT_INCLUDE_STORIES_LIST;
        }
        return new ArrayList<String>(Arrays.asList(includedFeatures.split(",")));
    }

    private static List<String> getExcludedStories() {
        String excludedFeatures = System.getProperty("excludedStories");
        if (!Optional.ofNullable(excludedFeatures).isPresent()) {
            return DEFAULT_EXCLUDE_STORIES_LIST;
        }
        return new ArrayList<String>(Arrays.asList(excludedFeatures.split(",")));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(), context());
    }

    private ApplicationContext context() {
        if (!Optional.ofNullable(context).isPresent()) {
            context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
            setSpringActiveProfile();
        }
        return context;
    }

    private void setSpringActiveProfile() {
        context.getEnvironment().setActiveProfiles(SPRING_ACTIVE_PROFILE);
    }

    @Override
    protected List<String> storyPaths() {
        List<String> paths = new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), getIncludedStories(), getExcludedStories());
        return paths;
    }

}
